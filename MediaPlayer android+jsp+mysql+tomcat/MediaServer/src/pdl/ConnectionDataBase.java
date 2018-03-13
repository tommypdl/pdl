package pdl;

import java.sql.DriverManager;

import java.sql.Connection;

public class ConnectionDataBase {
	Connection conn = null;

	public Connection connectionMySQLDataBase() {
		String drive = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/chatserver";
		String username = "root";
		String password = "123456";
		try {
			Class.forName(drive);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
		return conn;
	}
}
