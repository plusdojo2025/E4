package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
//--------和田追加----------------------------
import java.util.List;

//-------------------------------------------
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoodRecordDAO;
import model.MoodRecord;

@WebServlet("/MoodRegisterServlet")
public class MoodRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 気分登録画面の表示
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログインチェック
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/E4/LoginServlet");
//			return;
//		}

		// 現在時刻を取得（時:分表示用）
		LocalTime now = LocalTime.now();
		String currentTime = now.format(DateTimeFormatter.ofPattern("H : mm"));
		
		//-------------和田追加--------------------------
		int userId = 1;/*テストのため仮置き、後で消す*/
		
		 // DAOで登録済みログ一覧を取得
	    MoodRecordDAO dao = new MoodRecordDAO();
	    List<MoodRecord> moodList = dao.findAllByUser(userId);
	    request.setAttribute("moodList", moodList);
		//----------------------------------------------------

		// 画面に渡す
		request.setAttribute("currentTime", currentTime);

		// JSPへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mood_record.jsp");
		dispatcher.forward(request, response);
	}

	// 気分登録処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// ログインチェック
//		HttpSession session = request.getSession();
//		if (session.getAttribute("user_id") == null) {
//			response.sendRedirect("/E4/LoginServlet");
//			return;
//		}

		// セッションからuser_id取得
		int userId = 1;/*テストのため仮置き、後で消す*/
		//Integer userId = (Integer) session.getAttribute("user_id");/*後でコメントアウト取り消す*/

		// パラメータ取得
		int mood = Integer.parseInt(request.getParameter("mood"));
		String comment = request.getParameter("comment");

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
			doGet(request, response);
			//----------------------------------
		} else {
			request.setAttribute("error", "気分の登録に失敗しました");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/mood_record.jsp");
			dispatcher.forward(request, response);
		}
	}
}
