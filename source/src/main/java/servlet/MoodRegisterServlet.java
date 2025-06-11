package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("/WEB-INF/jsp/mood_record.jsp").forward(request, response);
        int userId = 1; // 本来はセッション等から取得
        Date today = Date.valueOf(LocalDate.now());

        MoodRecordDAO dao = new MoodRecordDAO();
        
        }
     

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int userId = 1;
            String moodStr = request.getParameter("mood");
            String comment = request.getParameter("comment");

            int mood = (moodStr != null && !moodStr.isEmpty()) ? Integer.parseInt(moodStr) : 0;
            Date today = Date.valueOf(LocalDate.now());

            // バリデーション
            if (mood == 0) {
                request.setAttribute("error", "気分は必須です。");
            } else if (comment != null && comment.length() > 140) {
                request.setAttribute("error", "コメントは140文字以内で入力してください。");
            }

            if (request.getAttribute("error") != null) {
                MoodRecord record = new MoodRecord();
                record.setMood(mood);
                record.setComment(comment);
                request.setAttribute("record", record);
                request.getRequestDispatcher("mood_record.jsp").forward(request, response);
                return;
            }

            MoodRecord record = new MoodRecord();
            record.setUser_id(userId);
            record.setRecord_date(today);
            record.setMood(mood);
            record.setComment(comment);

            MoodRecordDAO dao = new MoodRecordDAO();
            dao.insert(record);

            request.setAttribute("record", record);
            request.getRequestDispatcher("mood_record.jsp").forward(request, response);

        } catch (Exception e) {
            String comment = request.getParameter("comment");
            MoodRecord record = new MoodRecord();
            record.setComment(comment);
            request.setAttribute("record", record);
            request.getRequestDispatcher("mood_record.jsp").forward(request, response);
        }
    }
}