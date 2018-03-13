package MyClass;

import java.sql.*;

public class DBconn {
	private Connection conn = null; // 连接
	private Statement stmt = null; // 接口实例
	private ResultSet rs = null; // 数据集
	private String DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver"; // 驱动程序
	private String ConnStr = "jdbc:odbc:banji"; // 数据库连接odbc
	private String username = ""; // 数据库用户名
	private String password = ""; // 数据库密码

	/*
	 *设置数据库连接字符串、用户名、密码
	 */	
	public void setConnStr(String ConnStrArg) {
		ConnStr = ConnStrArg; //设置数据库连接字符串
	}
	
	public void setConnStrUser(String ConnStrArg, String usernameArt, String passwordArg) {
		ConnStr = ConnStrArg; //设置数据库连接字符串
		username = usernameArt;
		password = passwordArg;
	}
	
	public Connection getConnecion() { //创建数据库连接
		Connection myConn = null; 
		
		try {
			Class.forName(DBDriver); // 加载驱动程序
			myConn = DriverManager.getConnection(ConnStr); // 连接数据库
			//myConn = DriverManager.getConnection(ConnStr, username, password); // 连接数据库
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (myConn == null){
			System.err.println("警告：获得数据库连接失败！ ");
		}
		
		return myConn;
	}

	/*
	 *数据查询
	 */
	public ResultSet executeQuery(String sql) {
		rs = null;

		try {
			conn = getConnecion(); // 连接数据库
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);// 创建接口stmt，数据游标能灵活前后移动，只读
			//stmt = conn.createStatement();// 创建接口stmt
			rs = stmt.executeQuery(sql);// 执行查询
		} catch (SQLException e) {
			System.err.println("executeQuery: " + e.getMessage());
		}
		
		return rs; //返回数据集
	}


	/*
	 *数据插入、更新或删除
	 */
	public int executeUpdate(String sql) {
		int i = 0;

		try {
			conn = getConnecion(); // 连接数据库
			stmt = conn.createStatement();
			i = stmt.executeUpdate(sql); // 执行数据插入、更新或删除，得到被影响的记录数
		} catch (SQLException e) {
			System.err.println("executeUpdate: " + e.getMessage());
		}

		return i; //返回被影响的记录数，如果没有记录被更新或删除则得到0
	}

	/*
	 *关闭数据库连接
	 */
	public void close() {
		try { // 注意关闭的顺序
			if (rs != null) // 非空才关闭
				rs.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.err.println("close: " + e.getMessage());
		}
	}
}
