package dataclass;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;

public class KTVRoom extends CLabel{
	private Room room=null;
	public KTVRoom(Composite parent,int style){
		super(parent,style);
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	
}
