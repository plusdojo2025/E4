package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//-------------------------------------------
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DailyMoodDAO;
import dao.MoodRecordDAO;
import model.MoodRecord;

@WebServlet("/MoodRegisterServlet")
public class MoodRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 気分登録画面の表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


//    	HttpSession session = request.getSession(false);
//      User user = (User) session.getAttribute("user");
//      int userId = user.getId();

		// 現在時刻を取得（時:分表示用）
		LocalTime now = LocalTime.now();
		String currentTime = now.format(DateTimeFormatter.ofPattern("H : mm"));
		
		//-------------和田追加-------↓↓↓↓↓↓↓↓↓↓↓↓↓--------↓↓↓↓↓↓↓↓↓↓↓↓↓--------↓↓↓↓↓↓↓↓↓↓↓↓↓
		int userId = 1;/*テストのため仮置き、後で消す*/
		
		
		 // DAOで登録済みログ一覧を取得
		String dayStr = request.getParameter("day");
		List<MoodRecord> moodList;

		if (dayStr != null && !dayStr.trim().isEmpty()) {
	    	
	    	try {
	    	//今月・今年の年月と組み合わせてDateを作る
            LocalDate today = LocalDate.now();
            int day = Integer.parseInt(dayStr);//dayStrをintに変換
            
            // ここでtargetDateを作成
            LocalDate targetDate = LocalDate.of(today.getYear(), today.getMonth(), day);
            Date sqlDate = Date.valueOf(targetDate);
            
            //年月のday取得
            DailyMoodDAO dailyDao = new DailyMoodDAO();
            moodList = dailyDao.findByUserDate(userId, sqlDate);
            
	    } catch(Exception e) {
            //パラメーター不正時や日付エラーは例外敏江全権表示
            e.printStackTrace();
            MoodRecordDAO dao = new MoodRecordDAO();
            moodList = dao.findAllByUser(userId);
	    } 
	} else {
		 // dayパラメータが無ければ全件取得
        MoodRecordDAO dao = new MoodRecordDAO();
        moodList = dao.findAllByUser(userId);
    }
	
	    
	    
	 // 新しい順に表示
	    request.setAttribute("moodList", moodList);
	    

		//---------↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑---------↑↑↑↑↑↑↑↑↑↑↑↑↑↑-----------↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	    
		// 画面に渡す
	    request.setAttribute("selectedDay", dayStr);//日付
		request.setAttribute("currentTime", currentTime);//現在時刻

		// リダイレクト後の mood/comment 表示用
		String moodParam = request.getParameter("mood");
		String commentParam = request.getParameter("comment");

		if (moodParam != null) {
			request.setAttribute("registeredMood", Integer.parseInt(moodParam));
		}
		if (commentParam != null) {
			request.setAttribute("registeredComment", commentParam);
		}
		
		// JSPへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mood_record.jsp");
		dispatcher.forward(request, response);
	}

	// 気分登録処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

//    	HttpSession session = request.getSession(false);
//      User user = (User) session.getAttribute("user");
//      int userId = user.getId();

		// セッションからuser_id取得
		int userId = 1;/*テストのため仮置き、後で消す*/

		// パラメータ取得
		int mood = Integer.parseInt(request.getParameter("mood"));
		String comment = request.getParameter("comment");
		String dayStr = request.getParameter("day");

		// 登録レコード生成
		MoodRecord record = new MoodRecord();
		record.setUser_id(userId);
		record.setRecord_date(Date.valueOf(LocalDate.now()));
		record.setMood(mood);
		record.setComment(comment);

		// 登録実行
		MoodRecordDAO dao = new MoodRecordDAO();
		boolean success = dao.insert(record);

		// 成功・失敗（ご褒美表示は今後追加）
		if (success) {
			//-------------和田追加----------------
			//response.sendRedirect("MoodRegisterServlet");
			//response.sendRedirect("MoodRegisterServlet?day=" + dayStr);
			
			// --- パラメータをURLに含めてリダイレクト ---
						String redirectURL = "MoodRegisterServlet?day=" + dayStr +
							"&mood=" + mood +
							"&comment=" + URLEncoder.encode(comment, "UTF-8");

						response.sendRedirect(redirectURL);
			
			// 入力内容をリクエストスコープに保存（再表示用）
		   // request.setAttribute("registeredMood", mood);
		    //request.setAttribute("registeredComment", comment);
		    
		 // dayパラメータも引き継ぐ
		    //request.setAttribute("day", dayStr);
		    
		   // doGet(request, response);

			//----------------------------------
		} else {
			request.setAttribute("error", "気分の登録に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mood_record.jsp");
			dispatcher.forward(request, response);
		}
	}
}
