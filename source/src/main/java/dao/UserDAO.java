package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lib.DbConnection;
import model.User;

public class UserDAO {

	public boolean isRegisteredUser(User user) {
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

}
