package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MoodRecordDAO;
import dao.RewardsDAO;
import model.MoodRecord;
import model.Rewards;
import model.User;

@WebServlet("/GachaServlet")
public class GachaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 通常ページ表示（まだ引いてない）
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		// actionが"draw"の場合は、ガチャ実行の処理を行う
		if ("draw".equals(action)) {
			handleDraw(request, response);
			return;
		}

		// セッションからログイン中のユーザー情報を取得
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int userId = user.getId();

		// 今日の気分を取得し、それに応じた便箋画像のパスを取得
		int mood = getTodayMood(userId);
		String[] images = getEnvelopeImages(mood);
		if (images == null) {
			images = new String[] { "images/binsen_close_default.png", "images/binsen_open_default.png" };
		}
		
		// 便箋画像パスをリクエストスコープにセット
		request.setAttribute("closedImage", images[0]);
		request.setAttribute("openedImage", images[1]);

		// この段階ではガチャを引かず、引いたかどうかだけ判断
		RewardsDAO rewardsDAO = new RewardsDAO();
		Date today = new Date(System.currentTimeMillis());
		List<Rewards> todayRewards = rewardsDAO.getTodayRewards(userId, today);
		
		
		// ↓ テスト中は毎回引けるように固定（本番では必ず消す）
//		boolean alreadyDrawn = !todayRewards.isEmpty();
		boolean alreadyDrawn = false;

		request.setAttribute("alreadyDrawn", alreadyDrawn);

		// すでに引いている場合
		if (alreadyDrawn) {
			request.setAttribute("rewardItem", todayRewards.get(0).getGacha_item());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gacha.jsp");
		dispatcher.forward(request, response);
	}

	// JavaScriptからfetchで呼ばれたときのガチャ実行処理
	private void handleDraw(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("application/json; charset=UTF-8");

		// セッションからユーザー情報を取得
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		int userId = user.getId();
		
		// 気分取得
		int mood = getTodayMood(userId);

		// RewardsDAOで今日のガチャ結果を取得
		RewardsDAO rewardsDAO = new RewardsDAO();
		Date today = new Date(System.currentTimeMillis());
		List<Rewards> todayRewards = rewardsDAO.getTodayRewards(userId, today);

		String rewardItem;

		// すでに引いていたら既存の景品を返す
		if (!todayRewards.isEmpty()) {
			rewardItem = todayRewards.get(0).getGacha_item();
		} else {
			try {
				rewardItem = rewardsDAO.taikinGacha(mood, userId);
			} catch (Exception e) {
				rewardItem = "エラーが発生しました";
				e.printStackTrace();
			}
		}

		// JSON文字列を手動で作成（Gsonなし）
		String json = "{\"rewardItem\": \"" + escapeJson(rewardItem) + "\"}";

		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}

	// JSON文字列用に簡単なエスケープ処理
	private String escapeJson(String str) {
		if (str == null) return "";
		return str.replace("\\", "\\\\").replace("\"", "\\\"");
	}

	// 今日の気分をMoodRecordDAOから取得するメソッド
	private int getTodayMood(int userId) {
		MoodRecordDAO moodDao = new MoodRecordDAO();
		Date today = new Date(System.currentTimeMillis());
		List<MoodRecord> records = moodDao.findAllByUser(userId);
		for (MoodRecord r : records) {
			if (today.equals(r.getRecord_date())) {
				return r.getMood();
			}
		}
		return 3; // デフォルト気分
	}

	/**
	 * 気分(mood)に応じて便箋画像のパスを返す
	 */
	private String[] getEnvelopeImages(int mood) {
		switch (mood) {
		case 1:
			return new String[] { "images/binsen_close_red.png", "images/binsen_open_red.png" };
		case 2:
		case 3:
		case 4:
			return new String[] { "images/binsen_close_yellow.png", "images/binsen_open_yellow.png" };
		case 5:
			return new String[] { "images/binsen_close_blue.png", "images/binsen_open_blue.png" };
		default:
			return null;
		}
	}
}
