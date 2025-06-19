package servlet;

//Javaの基本的ナクラスやライブラリ
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//Servlet用のライブラリ
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//DAOとモデルのクラスを読み込む
import dao.MoodRecordDAO;
import dao.RewardsDAO;
import model.MoodRecord;
import model.Rewards;
import model.User;

@WebServlet("/MoodRegisterServlet")
public class MoodRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	// 気分登録画面の表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッションからログイン中のユーザー情報を取得
    	HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        
		// 現在時刻を取得（時:分表示用）
		LocalTime now = LocalTime.now();
		String currentTime = now.format(DateTimeFormatter.ofPattern("H : mm"));
		
		// 日付と登録ログの取得
		String dayStr = request.getParameter("day");
		String registered = request.getParameter("registered");

		// デフォルトは今日
		LocalDate selectedDate = LocalDate.now(); 
		
		// 登録直後の場合は必ず今日の日付を使う
			if ("true".equals(registered)) {
			    // 登録直後の戻りなら必ず今日で絞り込み
			    selectedDate = LocalDate.now();
			} else if (dayStr != null && !dayStr.trim().isEmpty()) {
			    int day = Integer.parseInt(dayStr);
			    selectedDate = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), day);
			} else {
			    selectedDate = LocalDate.now();
			}
			
		// 選択された日付の気分記録を取得
		List<MoodRecord> moodList;
			try {
			    Date sqlDate = Date.valueOf(selectedDate);
			    MoodRecordDAO dailyDao = new MoodRecordDAO();
			    moodList = dailyDao.findByUserDate(userId, sqlDate);
			} catch (Exception e) {
			    e.printStackTrace();
			    MoodRecordDAO dao = new MoodRecordDAO();
			    moodList = dao.findAllByUser(userId);
			}
		
		//----当日以外の画面表示----
		// 今日かどうかのフラグをJSPに渡す
		boolean isToday = selectedDate.equals(LocalDate.now());
		request.setAttribute("isToday", isToday);
	    
		// 新しい順に気分リストをセット
	    request.setAttribute("moodList", moodList);
	    
		// 画面に渡す
	    Date sqlDateForView = Date.valueOf(selectedDate);
	    request.setAttribute("selectedDate", sqlDateForView);
	    
		request.setAttribute("currentTime", currentTime);//現在時刻

		// リダイレクト後の気分とコメントを受け取ってセット
		String moodParam = request.getParameter("mood");
		String commentParam = request.getParameter("comment");

			if (moodParam != null) {
				request.setAttribute("registeredMood", Integer.parseInt(moodParam));
			}
			if (commentParam != null) {
				request.setAttribute("registeredComment", commentParam);
			}
			
		// 今日のご褒美情報を取得
        RewardsDAO rewardsDAO = new RewardsDAO();
        Date todayDate = Date.valueOf(selectedDate);
        List<Rewards> rewards = rewardsDAO.getTodayRewards(userId, todayDate);
        
        //ガチャ前後の分岐
	        if (rewards.isEmpty()) {
	            // 今日まだ引いていない
	        	  request.setAttribute("rewardItem", "まだご褒美はありません");
	              request.setAttribute("alreadyDrawn", false);
	        } else {
	            // 今日すでに引いている
	        	 request.setAttribute("rewardItem", rewards.get(0).getGacha_item());
	             request.setAttribute("alreadyDrawn", true);
	         }
		// JSPへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mood_record.jsp");
		dispatcher.forward(request, response);
		
			}
	
	
	
	// 登録ボタン処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// セッションからユーザー情報を取得
    	HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        int userId = user.getId();

		// パラメータ取得
		int mood = Integer.parseInt(request.getParameter("mood"));
		String comment = request.getParameter("comment");
		String dayStr = request.getParameter("day");
		
		//登録後の返答は今日登録した気分を表示
			if (dayStr == null || dayStr.trim().isEmpty()) {
			    dayStr = String.valueOf(LocalDate.now().getDayOfMonth());
			}
		
		
		//dayをDateに変換（登録と絞り込みをDateで行うため）
		 LocalDate recordDate;
		    try {
		        if (dayStr != null && !dayStr.trim().isEmpty()) {
		            int day = Integer.parseInt(dayStr);
		            LocalDate now = LocalDate.now();
		            recordDate = LocalDate.of(now.getYear(), now.getMonth(), day);
		        } else {
		            recordDate = LocalDate.now();
		        }
		    } catch (Exception e) {
		        recordDate = LocalDate.now();
		    }

		// 登録レコード生成
		MoodRecord record = new MoodRecord();
		record.setUser_id(userId);
		record.setRecord_date(Date.valueOf(recordDate)); // 
		record.setMood(mood);
		record.setComment(comment);

		// 登録実行
		MoodRecordDAO dao = new MoodRecordDAO();
		boolean success = dao.insert(record);

		// 成功・失敗（ご褒美表示は今後追加）
			if (success) {
				// --- パラメータをURLに含めてリダイレクト ---
					String redirectURL = "MoodRegisterServlet?day=" + LocalDate.now().getDayOfMonth() + "&registered=true" +
						"&mood=" + mood +
						"&comment=" + URLEncoder.encode(comment, "UTF-8");
	
					response.sendRedirect(redirectURL);
			} else {
				request.setAttribute("error", "気分の登録に失敗しました");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mood_record.jsp");
				dispatcher.forward(request, response);
			}
	}
}
