package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MoodRecordDAO;
import model.MoodRecord;
import model.User;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("user") == null) {
			response.sendRedirect("/E4/LoginServlet");
			return;
		}
		User user = (User) session.getAttribute("user");
		int userId = user.getId();

		LocalDate today = LocalDate.now();

		// 当月のムードレコードを取得
		List<MoodRecord> currentMonthRecords = getCurrentMonthMoodRecords(userId, today);

		// カレンダーを生成し、週ごとにグループ化
		List<List<String[]>> weeklyCalendar = createWeeklyCalendarWithMood(today, currentMonthRecords);

		request.setAttribute("calwithmood", weeklyCalendar);
		request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

	/**
	 * 指定された月のムードレコードを取得
	 */
	private List<MoodRecord> getCurrentMonthMoodRecords(int userId, LocalDate date) {
		MoodRecordDAO dao = new MoodRecordDAO();
		List<MoodRecord> allRecords = dao.findAllByUser(userId);

		return allRecords.stream().filter(record -> {
			LocalDate recordDate = record.getRecord_date().toLocalDate();
			return recordDate.getYear() == date.getYear() && recordDate.getMonthValue() == date.getMonthValue();
		}).collect(Collectors.toList());
	}

	/**
	 * カレンダーを生成し、ムード情報をマッピングして週ごとにグループ化
	 */
	private List<List<String[]>> createWeeklyCalendarWithMood(LocalDate date, List<MoodRecord> moodRecords) {
		// ムードマップを作成
		Map<String, Integer> moodMap = moodRecords.stream().collect(Collectors.toMap(
				record -> String.valueOf(record.getRecord_date().toLocalDate().getDayOfMonth()), MoodRecord::getMood, // メソッド参照、valueの部分
				(existing, replacement) -> replacement // 最新のムードで上書き
		));

		// カレンダーを生成
		List<List<String>> calendar = generateCalendar(date.getYear(), date.getMonthValue());

		// フラットなリストを作成
		List<String[]> flatList = new ArrayList<>();
		for (List<String> week : calendar) {
			for (String day : week) {
				String moodValue = day.isEmpty() ? ""
						: moodMap.containsKey(day) ? String.valueOf(moodMap.get(day)) : "";
				flatList.add(new String[] { day, moodValue });
			}
		}

		// 週ごとにグループ化
		List<List<String[]>> weeklyGroups = new ArrayList<>();
		for (int i = 0; i < flatList.size(); i += 7) {
			int endIndex = Math.min(i + 7, flatList.size());
			weeklyGroups.add(flatList.subList(i, endIndex));
		}

		return weeklyGroups;
	}

	/**
	 * カレンダーを生成（年と月を指定）
	 */
	private List<List<String>> generateCalendar(int year, int month) {
		List<List<String>> calendar = new ArrayList<>();

		YearMonth yearMonth = YearMonth.of(year, month);
		int daysInMonth = yearMonth.lengthOfMonth();
		LocalDate firstDay = yearMonth.atDay(1);
		int firstDayOfWeek = firstDay.getDayOfWeek().getValue(); // 月=1, 日=7

		List<String> week = new ArrayList<>();

		// 月の最初の週の空欄を追加
		for (int i = 1; i < firstDayOfWeek; i++) {
			week.add("");
		}

		// 各日を追加
		for (int day = 1; day <= daysInMonth; day++) {
			week.add(String.valueOf(day));

			if (week.size() == 7) {
				calendar.add(new ArrayList<>(week));
				week.clear();
			}
		}

		// 月の最後の週の空欄を追加
		if (!week.isEmpty()) {
			while (week.size() < 7) {
				week.add("");
			}
			calendar.add(week);
		}

		return calendar;
	}
}