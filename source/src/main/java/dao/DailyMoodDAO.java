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

public class DailyMoodDAO {

	//指定日の気分記録を取得
	public List<MoodRecord> findByUserDate(int userId, Date recordDate) {
		List<MoodRecord> list = new ArrayList<>();
		
		String sql = "SELECT * FROM mood_records WHERE user_id = ? AND record_date >= ? AND record_date < ? ORDER BY created_at DESC";
		
		try(Connection conn = DbConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
					
					
					pstmt.setInt(1, userId);
					// 範囲の開始
					pstmt.setDate(2, recordDate); 
					// 範囲の終了
				    pstmt.setDate(3, new Date(recordDate.getTime() + 24 * 60 * 60 * 1000)); 
					
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

		}catch (SQLException e) {
			System.err.println("データの取得に失敗しました:" + e.getMessage());
			e.printStackTrace();
	}
 
	 return list;
	 }
}
 

