package lib;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
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

    public static java.sql.Connection conn() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }
}