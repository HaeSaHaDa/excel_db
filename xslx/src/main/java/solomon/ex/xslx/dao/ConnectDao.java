package solomon.ex.xslx.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;



public class ConnectDao {
	public Connection getConn() {
		// 이것도 조만간 mybatis로 바꿔야겠다 너무 느리다..
		Connection conn = null;
		String dbUrl = "jdbc:mariadb://localhost:3306/test1";
		String id = "root";
		String pw = "6382";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getMessage();
			System.out.println("연결되지 않았습니다.");
		} // 드라이버 연결

		try {
			conn = (Connection) DriverManager.getConnection(dbUrl, id, pw);
			System.out.println("연결에 성공했습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
