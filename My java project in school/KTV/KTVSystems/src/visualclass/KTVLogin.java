package visualclass;

import java.sql.ResultSet;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import dataclass.CommonADO;

public class KTVLogin {

	private Shell sShell = null;
	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Text text = null;
	private Text text1 = null;
	private Button button = null;
	private Button button1 = null;

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData5 = new GridData();
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.grabExcessVerticalSpace = true;
		GridData gridData3 = new GridData();
		gridData3.verticalSpan = 2;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.grabExcessHorizontalSpace = true;
		GridData gridData2 = new GridData();
		gridData2.verticalSpan = 2;
		gridData2.grabExcessVerticalSpace = true;
		gridData2.grabExcessHorizontalSpace = true;
		GridData gridData1 = new GridData();
		gridData1.verticalSpan = 2;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		GridData gridData = new GridData();
		gridData.verticalSpan = 2;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/logins.jpg")));
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(400, 280));
		label = new Label(sShell, SWT.NONE);
		label.setText("       ");
		Label filler = new Label(sShell, SWT.NONE);
		Label filler5 = new Label(sShell, SWT.NONE);
		Label filler7 = new Label(sShell, SWT.NONE);
		Label filler1 = new Label(sShell, SWT.NONE);
		label1 = new Label(sShell, SWT.NONE);
		label1.setText("用户名");
		label1.setLayoutData(gridData);
		label3 = new Label(sShell, SWT.NONE);
		label3.setText("");
		text = new Text(sShell, SWT.BORDER);
		text.setLayoutData(gridData2);
		Label filler2 = new Label(sShell, SWT.NONE);
		Label filler4 = new Label(sShell, SWT.NONE);
		Label filler3 = new Label(sShell, SWT.NONE);
		label2 = new Label(sShell, SWT.NONE);
		label2.setText("密码");
		label2.setLayoutData(gridData1);
		Label filler6 = new Label(sShell, SWT.NONE);
		text1 = new Text(sShell, SWT.BORDER);
		text1.setLayoutData(gridData3);
		Label filler8 = new Label(sShell, SWT.NONE);
		Label filler9 = new Label(sShell, SWT.NONE);
		Label filler10 = new Label(sShell, SWT.NONE);
		button = new Button(sShell, SWT.NONE);
		button.setText("登录");
		button.setLayoutData(gridData5);
		Label filler11 = new Label(sShell, SWT.NONE);
		button1 = new Button(sShell, SWT.NONE);
		button1.setText("退出");
		button1.setLayoutData(gridData4);
		sShell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		sShell.setBackgroundImage(new Image(Display.getCurrent(),
		getClass().getResourceAsStream("/images/loginss.png")));
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				String username=text.getText().trim();
				String password=text1.getText().trim();
				CommonADO ado=CommonADO.getCommonADO();
				String query="select * from Users where UserName='"+username+"'";
				ResultSet rs=ado.executeSelect(query);
				try{
					if(rs.next()){
						SystemMainShell.userType=rs.getString("UserType");
						Shell oldShell=sShell;
						sShell=new SystemMainShell().getsShell();
						sShell.open();
						//oldShell.close();
						oldShell.setMinimized(true);
						
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		button1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				sShell.close();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	public KTVLogin(){
		createSShell();
	}
	public Shell getsShell(){
		return sShell;
	}
}
