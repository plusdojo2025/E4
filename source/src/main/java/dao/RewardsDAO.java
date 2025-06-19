package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import lib.DbConnection;
import model.Rewards;

public class RewardsDAO {
	// 気分記録画面用、その日引いたご褒美を取得するメソッド
	public List<Rewards> getTodayRewards(int user_id, Date todayDate) {
		String sql = """
				SELECT user_id, gacha_time, gacha_item 
				FROM rewards_result AS r INNER JOIN rewards_collection AS c
				ON r.gacha_id = c.id
				WHERE user_id = ? AND gacha_time = ?
				""";
		List<Rewards> list = new ArrayList<>();

		try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, user_id);
			pstmt.setDate(2, todayDate);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					list.add(new Rewards(rs.getInt("user_id"), rs.getDate("gacha_time"), rs.getString("gacha_item")));
				}
			}
		} catch (SQLException e) {
			System.err.println("ご褒美履歴（日ごと）取得時にエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	// 今週のレポート用、今週のご褒美を取得するメソッド
	public List<Rewards> getWeeklyRewards(int user_id, Date weekStartDate, Date weekEndDate) {
		String sql =  """
				SELECT user_id, gacha_time, gacha_item 
				FROM rewards_result AS r INNER JOIN rewards_collection AS c
				ON r.gacha_id = c.id
				WHERE user_id = ? AND gacha_time BETWEEN ? AND ?
				""";
		List<Rewards> list = new ArrayList<>();
		try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, user_id);
			pstmt.setDate(2, weekStartDate);
			pstmt.setDate(3, weekEndDate);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					list.add(new Rewards(rs.getInt("user_id"), rs.getDate("gacha_time"), rs.getString("gacha_item")));
				}
			}
		} catch (SQLException e) {
			System.err.println("ご褒美履歴（週間）取得時にエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}
		return list;
	}

	// 退勤ガチャ用、レアリティごとにガチャ結果を返して、記録するメソッド
	public String taikinGacha(int mood, int user_id) {
	    int gachaRarity;

	    // mood に応じてガチャのレアリティ（内容カテゴリ）を決定
	    switch (mood) {
	        case 1:
	            gachaRarity = 3; // 癒し系
	            break;
	        case 5:
	            gachaRarity = 1; // 挑戦系
	            break;
	        default:
	            gachaRarity = 2; // 標準
	            break;
	    }

	    String sql = "SELECT gacha_item FROM rewards_collection WHERE gacha_rarity = ?";
	    List<String> list = new ArrayList<>();

	    try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setInt(1, gachaRarity);
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                list.add(rs.getString("gacha_item"));
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("ガチャアイテム取得時にエラーが発生しました: " + e.getMessage());
	        e.printStackTrace();
	    }

	    if (list.isEmpty()) {
	        return "該当カテゴリのご褒美がありませんでした";
	    }

	    int index = ThreadLocalRandom.current().nextInt(list.size());
	    String randomElement = list.get(index);

	    insertRewardsResult(user_id, randomElement);
	    return randomElement;
	}


	private boolean insertRewardsResult(int user_id, String gachaItem) {
		String selectSql = "SELECT id FROM rewards_collection WHERE gacha_item = ?";
		String insertSql = "INSERT INTO rewards_result (user_id, gacha_time, gacha_id) VALUES (?, CURRENT_TIMESTAMP, ?)";

		try (Connection conn = DbConnection.getConnection()) {
			// gacha_item から gacha_id を取得
			int gachaId = -1;
			try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
				selectStmt.setString(1, gachaItem);
				try (ResultSet rs = selectStmt.executeQuery()) {
					if (rs.next()) {
						gachaId = rs.getInt("id");
					} else {
						throw new SQLException("該当するgacha_itemが見つかりません: " + gachaItem);
					}
				}
			}

			// contact テーブルに挿入
			try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
				insertStmt.setInt(1, user_id);
				insertStmt.setInt(2, gachaId);

				int affectedRows = insertStmt.executeUpdate();
				return affectedRows > 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
