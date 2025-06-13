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
import javax.servlet.http.HttpSession;

import dao.MoodRecordDAO;
import dao.RewardsDAO;
import model.MoodRecord;
import model.Rewards;

@WebServlet("/GachaServlet")
public class GachaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        int userId = 1; // テストの際は１を仮置き

        // セッションからユーザーIDを取得する場合はこちらを使用
//        if (session == null || session.getAttribute("user_id") == null) {
//            response.sendRedirect("LoginServlet");
//            return;
//        } else {
//            userId = (int) session.getAttribute("user_id");
//        }

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

        if (!todayRewards.isEmpty()) {
            // ご褒美は既に引いている場合、最初のご褒美を表示
            request.setAttribute("alreadyDrawn", true);
            // ご褒美IDだけでなく名前がほしい場合はDAOの拡張が必要です
            request.setAttribute("rewardItem", getGachaItemNameById(todayRewards.get(0).getGacha_id()));
        } else {
            // 今日まだ引いていなければガチャを実行
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

    /**
     * 今日の気分をMoodRecordDAOから取得
     */
    private int getTodayMood(int userId) {
        MoodRecordDAO moodDao = new MoodRecordDAO();
        Date today = new Date(System.currentTimeMillis());
        List<MoodRecord> records = moodDao.findAllByUser(userId);
        for (MoodRecord r : records) {
            if (today.equals(r.getRecord_date())) {
                return r.getMood();
            }
        }
        return 3; // デフォルトの気分（無記録時）
    }

    /**
     * ご褒美IDからご褒美名を取得するメソッド（RewardsDAOにないのでここで簡易実装例）
     * もしDAOで取得できるメソッドがあればそちらを使ってください。
     */
    private String getGachaItemNameById(int gachaId) {
        RewardsDAO rewardsDAO = new RewardsDAO();
        // ここはDAOにgachaId→gachaItem名を取るメソッドがない場合は別途実装が必要
        // 例として全件取得して探すなどの処理を想定（効率悪いので本番ではDAO拡張推奨）
        // ここでは簡略化のため「ご褒美ID:◯◯」と表示するだけにします。
        return "ご褒美ID:" + gachaId;
    }

    /**
     * 気分ごとに便箋画像のパスを返す
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