package RoomAddWizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class RoomAddWizardPage extends WizardPage{
	private Shell sShell=null;
	private RoomAddComposite roomAddCp=null;
	public RoomAddWizardPage(String pageName){
		super(pageName);
	}
	public void createControl(Composite arg0){
		sShell=arg0.getShell();
		this.setTitle("���������Ϣ");
		this.setDescription("����������������Ϣ");
		roomAddCp=new RoomAddComposite(arg0,SWT.NONE);
		this.setControl(roomAddCp);
	}
	public RoomAddComposite getRoomAddCp(){
		return roomAddCp;
	}
}
