package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MoodRecordDAO;
import model.MoodRecord;
import model.User;

@WebServlet("/MoodCheckServlet")
public class MoodCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);
		boolean notRegistered = true;

		if (session != null) {
			User user = (User) session.getAttribute("user");
			if (user != null) {
				MoodRecordDAO dao = new MoodRecordDAO();
				Date today = Date.valueOf(LocalDate.now());
				List<MoodRecord> records = dao.findByUserDate(user.getId(), today);
				notRegistered = records.isEmpty();
			}
		}
		response.getWriter().write("{\"moodNotRegistered\": " + notRegistered + "}");
	}
}
