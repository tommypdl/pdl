package RoomAddWizard;

import org.eclipse.jface.wizard.Wizard;
import visualclass.SystemManageShell;
public class RoomAddWizard extends Wizard{
	private RoomAddWizardPage roomAddWp;
	private RoomAddComposite roomAddCp;
	
	public RoomAddWizard(){
		this.setWindowTitle("����°���");
	}
	public boolean performFinish(){
		roomAddCp=roomAddWp.getRoomAddCp();
		String roomNo=roomAddCp.getTextRoomNo().getText().trim();
		String roomType=roomAddCp.getComboRoomType().getText().trim();
		String roomRemarks=roomAddCp.getTextAreaRemarks().getText().trim();
		SystemManageShell.newRoom.setRoomNo(roomNo);
		SystemManageShell.newRoom.setType(roomType);
		SystemManageShell.newRoom.setState("����");
		SystemManageShell.newRoom.setRemarks(roomRemarks);
		return true;
	}
	public void addPage(){
		roomAddWp=new RoomAddWizardPage("����°���");
		this.addPage(roomAddWp);
	}
	public boolean needsPreviousAndNextButtons(){
		return false;
	}
}
