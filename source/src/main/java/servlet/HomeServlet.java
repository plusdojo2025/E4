package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoodRecordDAO;
import model.MoodRecord;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MoodRecordDAO dao = new MoodRecordDAO();

		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();

//		HttpSession session = request.getSession(false);
//		User user = (User) session.getAttribute("user");
//		String email = user.getUserId();

		List<List<String>> calendar = generateCalendar(year, month);
		List<MoodRecord> moodRecords = dao.findAllByUser(1);
		List<Map<String, String>> calwithmood = mapCalendarWithMood(calendar, moodRecords);

		request.setAttribute("calwithmood", calwithmood);

		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	public static List<Map<String, String>> mapCalendarWithMood(List<List<String>> calendar,
			List<MoodRecord> moodRecords) {
		Map<String, Integer> moodMap = new HashMap<>();

		for (MoodRecord record : moodRecords) {
			Date date = record.getRecord_date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int dateNum = cal.get(Calendar.DAY_OF_MONTH); 
			// 最新のmoodで上書き
			moodMap.put(String.valueOf(dateNum), record.getMood());
		}

		List<Map<String, String>> result = new ArrayList<>();

		for (List<String> week : calendar) {
			for (String dayStr : week) {
				Map<String, String> cell = new HashMap<>();
				
				if (dayStr.isEmpty()) {
					cell.put("", "");
				} else {
					String moodStr = moodMap.containsKey(dayStr) ? String.valueOf(moodMap.get(dayStr)) : "";
					cell.put(dayStr, moodStr);
				}
				result.add(cell);
			}
		}
		return result;
	}

	public static List<List<String>> generateCalendar(int year, int month) {
		List<List<String>> calendar = new ArrayList<>();

		YearMonth ym = YearMonth.of(year, month);
		int daysInMonth = ym.lengthOfMonth();
		LocalDate firstDay = ym.atDay(1);
		int firstDayOfWeek = firstDay.getDayOfWeek().getValue(); // 月=1, 日=7

		List<String> week = new ArrayList<>();

		// 最初の週の空欄
		for (int i = 1; i < firstDayOfWeek; i++) {
			week.add("");
		}

		for (int day = 1; day <= daysInMonth; day++) {
			week.add(String.valueOf(day));

			if (week.size() == 7) {
				calendar.add(week);
				week = new ArrayList<>();
			}
		}

		// 最後の週の空欄
		if (!week.isEmpty()) {
			while (week.size() < 7) {
				week.add("");
			}
			calendar.add(week);
		}

		return calendar;
	}
}