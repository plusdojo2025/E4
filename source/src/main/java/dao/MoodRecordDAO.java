package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lib.DbConnection;
import model.MoodRecord;

public class MoodRecordDAO {

	// 気分記録を1件登録（記録時刻はDB側で自動記録）
	public boolean insert(MoodRecord record) {
		String sql = "INSERT INTO mood_records (user_id, record_date, mood, comment) VALUES (?, ?, ?, ?)";
		try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, record.getUser_id());
			pstmt.setDate(2, record.getRecord_date());
			pstmt.setInt(3, record.getMood());
			pstmt.setString(4, record.getComment());

			int rows = pstmt.executeUpdate();
			return rows == 1;

		} catch (SQLException e) {
			System.err.println("気分記録の登録に失敗: " + e.getMessage());
			return false;
		}
	}

	// 指定ユーザーの気分記録をcreated_atの降順で取得する
	// created_atにしたのはCURRENT_TIMESTAMPを使うならtimeだと時：分；秒しか取得出来ず、年月日と時:分:秒（日時すべて）を取得出来ないからです。
	// 日時から時刻だけを切り出して使うならtime、日時を丸ごと使うならcreated_at
	public List<MoodRecord> findAllByUser(int userId) {
		List<MoodRecord> list = new ArrayList<>();
		String sql = "SELECT * FROM mood_records WHERE user_id = ? ORDER BY created_at DESC";

		try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					MoodRecord record = new MoodRecord();
					record.setUser_id(rs.getInt("user_id"));
					record.setRecord_date(rs.getDate("record_date"));
					record.setMood(rs.getInt("mood"));
					record.setComment(rs.getString("comment"));
					record.setCreated_at(rs.getTimestamp("created_at")); // 時間記録用です
					list.add(record);
				}
			}

		} catch (SQLException e) {
			System.err.println("気分記録の取得に失敗: " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	public List<MoodRecord> findByUserAndDate(int userId, Date date) {
		List<MoodRecord> list = new ArrayList<>();
		String sql = "SELECT * FROM mood_records WHERE user_id = ? AND record_date = ?";

		try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			pstmt.setDate(2, date);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					MoodRecord record = new MoodRecord();
					record.setUser_id(rs.getInt("user_id"));
					record.setRecord_date(rs.getDate("record_date"));
					record.setMood(rs.getInt("mood"));
					record.setComment(rs.getString("comment"));
					record.setCreated_at(rs.getTimestamp("created_at"));
					list.add(record);
				}
			}

		} catch (SQLException e) {
			System.err.println("指定日の気分記録の取得に失敗: " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

}
