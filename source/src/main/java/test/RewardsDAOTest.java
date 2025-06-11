package test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dao.RewardsDAO;
import model.Rewards;

public class RewardsDAOTest {

	public static void main(String[] args) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		RewardsDAO dao = new RewardsDAO();

		System.out.println("10連ガチャ1:" + dao.taikinGacha(1, 1));
		System.out.println("10連ガチャ2:" + dao.taikinGacha(1, 1));
		System.out.println("10連ガチャ3:" + dao.taikinGacha(2, 1));
		System.out.println("10連ガチャ4:" + dao.taikinGacha(2, 1));
		System.out.println("10連ガチャ5:" + dao.taikinGacha(2, 1));
		System.out.println("10連ガチャ6:" + dao.taikinGacha(2, 1));
		System.out.println("10連ガチャ7:" + dao.taikinGacha(3, 1));
		System.out.println("10連ガチャ8:" + dao.taikinGacha(3, 1));
		System.out.println("10連ガチャ9:" + dao.taikinGacha(3, 1));
		System.out.println("10連ガチャ10:" + dao.taikinGacha(3, 1));

		Date todayDate = Date.valueOf(java.time.LocalDate.now());
		List<Rewards> todayRewards = dao.getTodayRewards(1, todayDate);
		System.out.println("今日のご褒美:" + gson.toJson(todayRewards));
		
		
		LocalDate now = java.time.LocalDate.now();
		LocalDate weekStart = now.with(java.time.DayOfWeek.MONDAY);
		LocalDate weekEnd = now.with(java.time.DayOfWeek.SUNDAY);
		Date weekStartDate = Date.valueOf(weekStart);
		Date weekEndDate = Date.valueOf(weekEnd);
		List<Rewards> weeklyRewards =dao.getWeeklyRewards(1, weekStartDate,  weekEndDate) ;
		System.out.println("今週のご褒美:" + gson.toJson(weeklyRewards));
	}
}
