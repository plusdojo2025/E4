package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoodRecordDAO;
import model.MoodRecord;

@WebServlet("/GachaServlet")
public class GachaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 今日の気分（疲労度）を取得するメソッド
    private int getTodayMood(int userId) {
        MoodRecordDAO dao = new MoodRecordDAO();
        List<MoodRecord> records = dao.findAllByUser(userId);
        Date today = Date.valueOf(LocalDate.now());

        for (MoodRecord record : records) {
            if (today.equals(record.getRecord_date())) {
                return record.getMood();
            }
        }
        return 1; // デフォルト疲労度1(テスト用)
    }

    // 疲労度に応じた封筒画像を取得
    private String[] getEnvelopeImages(int mood) {
        String closeImage;
        String openImage;

        if (mood == 1) {
            closeImage = "/img/binsen_close_red.png";
            openImage = "/img/binsen_open_red.png";
        } else if (2 <= mood && mood <= 4) {
            closeImage = "/img/binsen_close_yellow.png";
            openImage = "/img/binsen_open_yellow.png";
        } else {
            closeImage = "/img/binsen_close_blue.png";
            openImage = "/img/binsen_open_blue.png";
        }

        return new String[] { closeImage, openImage };
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	// セッションの取得とログインチェック(テスト時コメントアウト)
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute("user_id") == null) {
//            response.sendRedirect("LoginServlet");
//            return;
//        }
        
        // ログインIDと今日の気分取得
        
        int userId = 1;  // テスト用の固定ID(あとで消す)
        
//        int userId = (int) session.getAttribute("user_id");
        int todayMood = getTodayMood(userId);

        String[] images = getEnvelopeImages(todayMood);

        request.setAttribute("closedImage", images[0]);
        request.setAttribute("openedImage", images[1]);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/gacha.jsp");
        dispatcher.forward(request, response);
    }
}