package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoodRecordDAO;
import dao.RewardsDAO;
import model.MoodRecord;
import model.Rewards;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
    	int userId = 1; // テスト用に仮置き
    	
    	// セッションの取得（ログイン情報の確認）
//    	HttpSession session = request.getSession(false);
//    	if (session == null || session.getAttribute("user") == null) {
//    		// セッションなしはログインページへ
//    		response.sendRedirect("/E4/LoginServlet");
//    		return;
//    	}
//    	 // ログインユーザー情報を取得
//        User user = (User) session.getAttribute("user");
//        int userId = user.getId();
        
        // 今週の開始日（月曜日）と終了日（金曜日）を計算
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.FRIDAY);

        // java.sql.Date に変換
        Date weekStartDate = Date.valueOf(startOfWeek);
        Date weekEndDate = Date.valueOf(endOfWeek);

        // DAOのインスタンス生成
        MoodRecordDAO moodDao = new MoodRecordDAO();
        RewardsDAO rewardsDao = new RewardsDAO();

        // 今週の月曜～金曜の間の記録を取得
        List<MoodRecord> moodList = moodDao.findAllByUser(userId);
		List<Rewards> rewardList = rewardsDao.getWeeklyRewards(userId, weekStartDate, weekEndDate);
        
        // リクエストスコープにセット
        request.setAttribute("moodList", moodList);
		request.setAttribute("rewardList", rewardList);
		request.setAttribute("weekStart", weekStartDate);
		request.setAttribute("weekEnd", weekEndDate);
    	
		// report.jspにフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/report.jsp").forward(request, response);
    }
}
