package pdl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserTableDao {
	ConnectionDataBase cd;
	Connection conn;

	public UserTableDao() {
		cd = new ConnectionDataBase();
		conn = cd.connectionMySQLDataBase();
	}

	// 注册帐号的时候，要用到这个方法，把数据插入到数据库中
	public int add(int username, String password, String nickname, String sex,
			int birthday, String province, String city, String constellation) {
		String sql = "INSERT INTO CHAT(username,password,nickname,sex,birthday,province,city,constellation) VALUES(?,?,?,?,?,?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, username);
			ps.setString(2, password);
			ps.setString(3, nickname);
			ps.setString(4, sex);
			ps.setInt(5, birthday);
			ps.setString(6, province);
			ps.setString(7, city);
			ps.setString(8, constellation);
			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	public int addtalk(String nickname, String text, String musicname,
			String time) {
		String sql = "INSERT INTO TALK(nickname,text,musicname,time) VALUES(?,?,?,?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nickname);
			ps.setString(2, text);
			ps.setString(3, musicname);
			ps.setString(4, time);

			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	public int deleteTalk(int id) {
		String sql = "delete from talk where id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(0, id);

			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}

	public int editTalk(String sql) {
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// ps.setInt(0, id);

			int n = ps.executeUpdate();
			return n;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}

	// 查一个
	public User find(int username) {
		User user = new User();
		String sql = "select * from chat where username=" + username;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// 把查询的结果存在rs这个对象里
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// 一个一个把对应列的数据取出来 然后set到user这个对象里 并且返回
				int id = rs.getInt("id");
				int name = rs.getInt("username");
				String password = rs.getString("password");
				int online = rs.getInt("online");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				int birthday = rs.getInt("birthday");
				String province = rs.getString("province");
				String city = rs.getString("city");
				String introduce = rs.getString("introduce");
				String school = rs.getString("school");
				String email = rs.getString("email");
				String constellation = rs.getString("constellation");

				user.setId(id);
				user.setUsername(name);
				user.setPassword(password);
				user.setOnline(online);
				user.setNickname(nickname);
				user.setSex(sex);
				user.setBirthday(birthday);
				user.setProvince(province);
				user.setCity(city);
				user.setIntroduce(introduce);
				user.setSchool(school);
				user.setEmail(email);
				user.setConstellation(constellation);

				return user;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("没有该用户");
			return user = null;
		}
		return null;
	}

	public ArrayList<User> findAll() {
		ArrayList<User> users = new ArrayList<User>();

		String sql = "select * from chat";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();

				int id = rs.getInt("id");
				user.setId(id);

				int username = rs.getInt("username");
				user.setUsername(username);

				String password = rs.getString("password");
				user.setPassword(password);

				int online = rs.getInt("online");
				user.setOnline(online);

				users.add(user);
				user = null;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;

	}

	// 更新密码，即用户发出请求要修改密码
	public void updatePassword(String newPwd, Integer id) {
		String sql = "update chat set password=" + newPwd + " where id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 当online为0，表示下线，当 online为1表示上线，当online为2表示隐身。
	public void updateOnline(Integer newOnline, Integer id) {
		String sql = "update chat set online=" + newOnline + " where id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 此语句作用在于查询数据库中所有条目的个数
	public int counts() {
		String sql = "select count(*) from chat";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int counts = rs.getInt(1);
				return counts;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// 此类作用在于根据 客户端传过来的ID号查询帐号
	public int queryUsernameByID(String id) {
		String sql = "select * from chat where id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int username = rs.getInt("username");
				return username;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	public String queryNicknameById(String id) {
		String sql = "select * from chat where id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String nickname = rs.getString("nickname");
				return nickname;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 通过传入的ID号查询在线状态
	public String queryOnlineStateById(String id) {
		String sql = "select * from chat where id=" + id;
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int online = rs.getInt("online");
				String stronline = online + "";
				return stronline;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public String queryTalk(String musicname) {
		String sql = "select * from talk where musicname=" + musicname;
		PreparedStatement ps;
		StringBuilder sb = new StringBuilder();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String text = rs.getString("text");
				sb.append(text + "huanhang");
				String name = rs.getString("nickname");
				sb.append(name + " ");
				String time = rs.getString("time");
				sb.append(time + "huanhang");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public ResultSet queryTalkbyid(int id) {
		String sql = "select * from talk where id=" + id;
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet queryTalkbynickname(String nickname) {
		String sql = "select * from talk where nickname=" + nickname;
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet queryTalkbymusicname(String musicname) {
		String sql = "select * from talk where musicname=" + musicname;
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet queryTalkmofu(String sql) {

		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public ResultSet queryallTalk() {
		String sql = "select * from talk ";
		PreparedStatement ps;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	public String getNickName(String username) {
		String sql = "select * from chat ";
		PreparedStatement ps;
		String nickname = "";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("username");
				if (name.equals(username)) {
					nickname = rs.getString("nickname");
					break;
				}
			}

			System.out.println("nick:" + nickname);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nickname;
	}

	// 在tab2的 OnResume方法里刷新好友列表的时候，调用此方法
	public User creatFriendListInfo(String id) {
		int ID = Integer.parseInt(id);
		String sql = "select * from chat where id=" + id;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				User user = new User();
				String nickname = rs.getString("nickname");
				user.setNickname(nickname);
				int online = rs.getInt("online");
				user.setOnline(online);
				String introduce = rs.getString("introduce");
				user.setIntroduce(introduce);

				user.setId(ID);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public User findMyInfo(String myid) {
		User user = new User();
		String sql = "select * from chat where id=" + myid;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			// 把查询的结果存在rs这个对象里
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// 一个一个把对应列的数据取出来 然后set到user这个对象里 并且返回
				int id = rs.getInt("id");
				int name = rs.getInt("username");
				String password = rs.getString("password");
				int online = rs.getInt("online");
				String nickname = rs.getString("nickname");
				String sex = rs.getString("sex");
				int birthday = rs.getInt("birthday");
				String province = rs.getString("province");
				String city = rs.getString("city");
				String introduce = rs.getString("introduce");
				String school = rs.getString("school");
				String email = rs.getString("email");
				String constellation = rs.getString("constellation");

				user.setId(id);
				user.setUsername(name);
				user.setPassword(password);
				user.setOnline(online);
				user.setNickname(nickname);
				user.setSex(sex);
				user.setBirthday(birthday);
				user.setProvince(province);
				user.setCity(city);
				user.setIntroduce(introduce);
				user.setSchool(school);
				user.setEmail(email);
				user.setConstellation(constellation);

				return user;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("没有该用户");
			return user = null;
		}
		return null;
	}

	public void updateNickname(String nickname, String id) {
		// 切记！！！！mysql中字符串是要用单引号加双引号区分的！！！！
		// 如 '"+nickname+"'
		String sql = "update chat set nickname='" + nickname + "' where id='"
				+ id + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateSex(String sex, String id) {
		String sql = "update chat set sex='" + sex + "' where id='" + id + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateBirthday(String birthday, String id) {
		String sql = "update chat set birthday='" + birthday + "' where id='"
				+ id + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateProvince(String province, String id) {
		String sql = "update chat set province='" + province + "' where id='"
				+ id + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCity(String city, String id) {
		String sql = "update chat set city='" + city + "' where id='" + id
				+ "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateSchool(String school, String id) {
		String sql = "update chat set school='" + school + "' where id='" + id
				+ "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateEmail(String email, String id) {
		String sql = "update chat set email='" + email + "' where id='" + id
				+ "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateIntroduce(String introduce, String id) {
		String sql = "update chat set introduce='" + introduce + "' where id='"
				+ id + "'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateCollstellation(String collstellation, String id) {
		String sql = "update chat set constellation='" + collstellation
				+ "' where id='" + id + "'";
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
