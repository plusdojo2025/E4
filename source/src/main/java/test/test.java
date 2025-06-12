package test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.MoodRecordDAO;
import model.MoodRecord;
import servlet.HomeServlet;

public class test {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		MoodRecordDAO dao = new MoodRecordDAO();

		LocalDate today = LocalDate.now();
		int year = today.getYear();
		int month = today.getMonthValue();

//		HttpSession session = request.getSession(false);
//		User user = (User) session.getAttribute("user");
//		String email = user.getUserId();

		List<List<String>> calendar = HomeServlet.generateCalendar(year, month);
		List<MoodRecord> moodRecords = dao.findAllByUser(1);
		List<Map<String, String>> calwithmood = HomeServlet.mapCalendarWithMood(calendar, moodRecords);
		
		System.out.println( gson.toJson(calwithmood));
	}

}
