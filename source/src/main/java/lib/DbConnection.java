package lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/e4?"
			+ "characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2B9&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "password";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("JDBCドライバのロードに失敗しました", e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	}
}