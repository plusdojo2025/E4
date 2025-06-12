package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();

		request.setAttribute("calender", generateCalendar(year, month));
		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	private static List<List<String>> generateCalendar(int year, int month) {
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