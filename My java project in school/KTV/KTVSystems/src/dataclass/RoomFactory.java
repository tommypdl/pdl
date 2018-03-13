package dataclass;
import java.sql.*;
import java.util.*;
public class RoomFactory {
	private List roomList=new ArrayList();
	public List getRoomList(){
		return roomList;
	}
	public RoomFactory(){
		CommonADO ado=CommonADO.getCommonADO();
		String roomQueryStr="select * from Room";
		ResultSet rs=ado.executeSelect(roomQueryStr);
		try {
			while(rs.next()){
				Room room=new Room();
				room.setRoomNo(rs.getString("RoomNo"));
				room.setType(rs.getString("Type").trim());
				room.setState(rs.getString("State"));
				room.setRemarks(rs.getString("Remarks"));
				roomList.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean InsertDB(Room room){
		CommonADO ado=CommonADO.getCommonADO();
		String querySql="select * from Room where RoomNo='"+room.getRoomNo()+"'";
		String insertSql="insert into Room values('"+room.getRoomNo()+"','"+room.getType()+"','"+room.getState()+"','"+room.getRemarks()+"')";
		ResultSet rs=ado.executeSelect(querySql);
		try {
			if(!rs.next()){
				ado.executeUpdate(insertSql);
				return true;
			}
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static void deleteDB(Room room){
		CommonADO con=CommonADO.getCommonADO();
		String deleteSql="delete from Room where RoomNo='"+room.getRoomNo()+"'";
		con.executeUpdate(deleteSql);
	}
	
}
