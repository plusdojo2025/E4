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

	// mood_records に1件の気分記録を登録する
	public boolean insert(MoodRecord record) {
		String sql = "INSERT INTO mood_records (user_id, record_date, mood, comment, time) VALUES (?, ?, ?, ?, CURRENT_TIME)";
		try (Connection conn = DbConnection.getConnection(); 
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, record.getUser_id());
			pstmt.setDate(2, record.getRecord_date());
			pstmt.setInt(3, record.getMood());
			pstmt.setString(4, record.getComment());

			int rows = pstmt.executeUpdate();
			return rows == 1;

		} catch (SQLException e) {
			System.err.println("気分記録の登録中にエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	// 特定のユーザーと日付に一致する気分記録を取得する
	public MoodRecord findByUserAndDate(int userId, Date date) {
		String sql = "SELECT * FROM mood_records WHERE user_id = ? AND record_date = ?";
		try (Connection conn = DbConnection.getConnection(); 
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			pstmt.setDate(2, date);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					MoodRecord record = new MoodRecord();
					record.setUser_id(rs.getInt("user_id"));
					record.setRecord_date(rs.getDate("record_date"));
					record.setMood(rs.getInt("mood"));
					record.setComment(rs.getString("comment"));
					return record;
				}
			}

		} catch (SQLException e) {
			System.err.println("気分記録の取得中にエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	// 特定ユーザーの1週間分の気分記録を取得する（最新日から7日分）
	public List<MoodRecord> findWeeklyRecords(int userId, Date fromDate) {
		String sql = "SELECT * FROM mood_records WHERE user_id = ? AND record_date >= DATE_SUB(?, INTERVAL 6 DAY) ORDER BY record_date";
		List<MoodRecord> list = new ArrayList<>();

		try (Connection conn = DbConnection.getConnection(); 
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			pstmt.setDate(2, fromDate);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					MoodRecord record = new MoodRecord();
					record.setUser_id(rs.getInt("user_id"));
					record.setRecord_date(rs.getDate("record_date"));
					record.setMood(rs.getInt("mood"));
					record.setComment(rs.getString("comment"));
					list.add(record);
				}
			}

		} catch (SQLException e) {
			System.err.println("1週間分の気分記録の取得中にエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
}
