package visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import dataclass.CommonADO;
import dataclass.KTVRoom;

public class MyMouseListener implements MouseListener{
	SystemMainShell sm=new SystemMainShell();
	public static KTVRoom lastSelectedRoom=null;
	public MyMouseListener(){
		super();
	}
	public void mouseDoubleClick(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDown(MouseEvent arg0) {
		// TODO Auto-generated method stub
		KTVRoom selectedktvRoom=(KTVRoom)arg0.getSource();
		
		if(lastSelectedRoom!=null){
			if(lastSelectedRoom.getRoom().getState().trim().equals("ø’œ–"))
				lastSelectedRoom.setImage(new Image(Display.getCurrent()
						,getClass().getResourceAsStream("/images/blue.png")));
			else
				lastSelectedRoom.setImage(new Image(Display.getCurrent()
						,getClass().getResourceAsStream("/images/red.png")));
			selectedktvRoom.setImage(new Image(Display.getCurrent()
					,getClass().getResourceAsStream("/images/green.png")));
			lastSelectedRoom=selectedktvRoom;
			sm.labelRoomNo.setText(selectedktvRoom.getRoom().getRoomNo());
			System.out.println(selectedktvRoom.getRoom().getRoomNo());
			System.out.println("click");
			sm.labelRoomType.setText(selectedktvRoom.getRoom().getType());
			sm.labelRoomState.setText(selectedktvRoom.getRoom().getState());
			CommonADO ado=CommonADO.getCommonADO();
			String peopleNumQuery="select * from RoomType where Type='"
				+selectedktvRoom.getRoom().getType()+"'";
			ResultSet rs=ado.executeSelect(peopleNumQuery);
			try {
				rs.next();
				sm.labelPeopleNum.setText(rs.getInt("peopleNums")+"");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(selectedktvRoom.getRoom().getState().trim().equals("ø’œ–")){
				sm.labelStartTime.setText("");
				sm.labelExpectTime.setText("");
				sm.labelPrePayMoney.setText("");
			}
			else{
				String roomUseQuery="select * from RoomOrder where RoomNo='"
					+selectedktvRoom.getRoom().getRoomNo()+"' ";
				try {
					rs.next();
					sm.labelStartTime.setText(rs.getString("StartTime"));
					sm.labelExpectTime.setText(rs.getString("ExpectTime"));
					sm.labelPrePayMoney.setText(rs.getFloat("PrePayMoney")+"");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	public void mouseUp(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
