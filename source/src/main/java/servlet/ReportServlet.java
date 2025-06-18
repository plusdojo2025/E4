package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MoodRecordDAO;
import dao.RewardsDAO;
import model.MoodRecord;
import model.Rewards;

@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    	
    	int userId = 1; // テスト用に仮置き
    	
    	// セッションの取得（ログイン情報の取得）
//    	HttpSession session = request.getSession(false);
//      User user = (User) session.getAttribute("user");
//      int userId = user.getId();

    	// 今日を含む過去七日間の期間
        LocalDate today = LocalDate.now();
        LocalDate startOfPeriod = today.minusDays(6);
        LocalDate endOfPeriod = today;

        // java.sql.Date に変換
        Date startOfPeriodDay = Date.valueOf(startOfPeriod);
        Date endOfPeriodDay = Date.valueOf(endOfPeriod);

        // DAOのインスタンス生成
        MoodRecordDAO moodDao = new MoodRecordDAO();
        RewardsDAO rewardsDao = new RewardsDAO();

        // 今週の月曜～金曜の間の記録を取得
        List<MoodRecord> allMoodList = moodDao.findAllByUser(userId);
        
        //  moodList に絞り込む
        List<MoodRecord> weekMoodList = allMoodList.stream()
        	// !とbefore, afterで両端の日付を含むようにする
        	.filter(m -> !m.getRecord_date().before(startOfPeriodDay) && !m.getRecord_date().after(endOfPeriodDay))
            .collect(Collectors.toList());

        // 平均疲労度を計算 
        double fatigueLevel = weekMoodList.stream()
            .mapToInt(MoodRecord::getMood)
            .average()
            .orElse(0);

        // 小数点以下2桁
        String fatigueLevelStr = String.format("%.2f", fatigueLevel);

        // 最も疲れた日を計算（気分が最小値の日）
        MoodRecord mostTired = weekMoodList.stream()
            .min((m1, m2) -> Integer.compare(m1.getMood(), m2.getMood()))
            .orElse(null);
        String tiredDay = mostTired != null ? mostTired.getRecord_date().toString() : "該当なし";

        // 今週のご褒美を取得
		List<Rewards> rewardList = rewardsDao.getWeeklyRewards(userId, startOfPeriodDay, endOfPeriodDay);

        // ガチャ回数を rewardList.size() で取得 
        int gachaCount = rewardList.size();

        // JSPに渡す変数名を JSP と一致させる
        request.setAttribute("moodList", weekMoodList);        // グラフ用
		request.setAttribute("weeklyReward", rewardList);      // ご褒美一覧
		request.setAttribute("fatigueLevel", fatigueLevelStr); // 平均疲労度（文字列）
		request.setAttribute("tiredDay", tiredDay);            // 最も疲れた日
		request.setAttribute("Gacha", gachaCount);             // ガチャ回数
		request.setAttribute("weekStart", startOfPeriodDay);
		request.setAttribute("weekEnd", endOfPeriodDay);
    	
		// report.jspにフォワード
        request.getRequestDispatcher("/WEB-INF/jsp/report.jsp").forward(request, response);
    }
}
