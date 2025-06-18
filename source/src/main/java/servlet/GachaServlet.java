package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoodRecordDAO;
import dao.RewardsDAO;
import model.MoodRecord;
import model.Rewards;

@WebServlet("/GachaServlet")
public class GachaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        int userId = 1; // テスト用に仮置き(本番は必ず消す)

        // 実運用ではセッションからユーザーID取得を有効にする

//    	HttpSession session = request.getSession(false);
//      User user = (User) session.getAttribute("user");
//      int userId = user.getId();

        int mood = getTodayMood(userId);

        String[] images = getEnvelopeImages(mood);
        if (images == null) {
            images = new String[] { "images/binsen_close_default.png", "images/binsen_open_default.png" };
        }
        request.setAttribute("closedImage", images[0]);
        request.setAttribute("openedImage", images[1]);

        RewardsDAO rewardsDAO = new RewardsDAO();
        Date today = new Date(System.currentTimeMillis());

        // 今日のご褒美が既に引かれているかチェック
        List<Rewards> todayRewards = rewardsDAO.getTodayRewards(userId, today);

        // ★ クエリパラメータで強制的に「alreadyDrawn」をtrueにする(テスト時コメントアウト)
        String forceDrawn = request.getParameter("forceDrawn");
        boolean alreadyDrawn = false;
        if ("1".equals(forceDrawn)) {
            alreadyDrawn = true;
    	} else if ("0".equals(forceDrawn)) {
        	alreadyDrawn = false;
        } else {
            alreadyDrawn = !todayRewards.isEmpty();
        }
        
     // ↓ テスト中は毎回引けるように固定(本番では必ず消す)
//        boolean alreadyDrawn = false;

        if (alreadyDrawn) {
            request.setAttribute("alreadyDrawn", true);
            if (!todayRewards.isEmpty()) {
                request.setAttribute("rewardItem", todayRewards.get(0).getGacha_item());
            } else {
                request.setAttribute("rewardItem", "ご褒美が見つかりませんでした");
            }
        } else {
            // 引いていなければガチャを引く
            String rewardItem;
            try {
                rewardItem = rewardsDAO.taikinGacha(mood, userId);
            } catch (Exception e) {
                rewardItem = "エラーが発生しました";
                e.printStackTrace();
            }
            request.setAttribute("alreadyDrawn", false);
            request.setAttribute("rewardItem", rewardItem);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gacha.jsp");
        dispatcher.forward(request, response);
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
