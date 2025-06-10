package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lib.DbConnection;
import model.User;

public class UserDAO {

//Userテーブルを検索
	public boolean isRegisteredUser(User user) {
		//SQL文を作成
		String sql = "SELECT COUNT(*) AS count FROM user WHERE email = ? AND password = ?";
		try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt("count") == 1;
				}
			}
		} catch (SQLException e) {
			System.err.println("ユーザー認証時にエラーが発生しました: " + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}
	
	public User insertUser(User user) {
		String sql = "INSERT INTO user (email, password) VALUES (?, ?)";
		try (Connection conn = DbConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());


			int affectedRows = ps.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("ユーザー登録に失敗しました。");
			}

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return selectById(generatedKeys.getInt(1));
				} else {
					throw new SQLException("IDの取得に失敗しました。");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private User selectById(int id) {
		String sql = "SELECT email, password FROM user WHERE id = ?";
		try (Connection conn = DbConnection.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new User(rs.getString("password"),rs.getString("email"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
