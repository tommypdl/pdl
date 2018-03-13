package dataclass;
import java.sql.*;
public class CommonADO {
	private String DBDriver=null;
	private String url=null;
	private String user=null;
	private String password=null;
	private Connection conn=null;
	private Statement stmt=null;
	private ResultSet rs=null;
	private static final CommonADO com=new CommonADO();
	private CommonADO(){
		DBDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=KTV";
		user="sa";
		password="123456";
		try {
			Class.forName(DBDriver);
			conn=DriverManager.getConnection(url, user, password);
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public static CommonADO getCommonADO(){
		return com;
	}
	public ResultSet executeSelect(String sql){
		if(sql.toLowerCase().indexOf("select")!=-1){
			try {
				rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	return rs;
	}
	public int executeUpdate(String sql){
		int result=0;
		if(sql.toLowerCase().indexOf("update")!=-1|
				sql.toLowerCase().indexOf("insert")!=-1|
				sql.toLowerCase().indexOf("delete")!=-1){
			try {
				result=stmt.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	public Connection getConn(){
		return conn;
	}
	public Statement getStmt(){
		return stmt;
	}
	public void closeDB(){
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
