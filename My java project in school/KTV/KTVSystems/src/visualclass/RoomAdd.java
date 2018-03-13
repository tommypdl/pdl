package visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

import dataclass.CommonADO;
import dataclass.Room;

import org.eclipse.swt.widgets.Combo;

public class RoomAdd {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Label label6 = null;
	private Label label7 = null;
	private Label label8 = null;
	private Label label9 = null;
	private Label label10 = null;
	private Button button = null;
	private Label label11 = null;
	private Label label12 = null;
	private Text RoomNo = null;
	private Text PeopleNum = null;
	private Text remark = null;
	private Button button1 = null;
	public boolean flag=false;
	private Combo RoomType = null;
	Room room=new Room();
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData13 = new GridData();
		gridData13.verticalSpan = 2;
		gridData13.widthHint = 200;
		gridData13.heightHint = 50;
		gridData13.horizontalSpan = 2;
		GridData gridData12 = new GridData();
		gridData12.verticalSpan = 2;
		GridData gridData10 = new GridData();
		gridData10.verticalSpan = 2;
		GridData gridData9 = new GridData();
		gridData9.grabExcessHorizontalSpace = true;
		GridData gridData8 = new GridData();
		gridData8.verticalSpan = 2;
		GridData gridData7 = new GridData();
		gridData7.verticalSpan = 2;
		GridData gridData6 = new GridData();
		gridData6.verticalSpan = 2;
		GridData gridData5 = new GridData();
		gridData5.verticalSpan = 2;
		GridData gridData4 = new GridData();
		gridData4.verticalSpan = 2;
		GridData gridData3 = new GridData();
		gridData3.verticalSpan = 2;
		GridData gridData2 = new GridData();
		gridData2.verticalSpan = 2;
		GridData gridData1 = new GridData();
		gridData1.verticalSpan = 2;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		GridData gridData = new GridData();
		gridData.verticalSpan = 3;
		gridData.horizontalSpan = 2;
		gridData.grabExcessHorizontalSpace = false;
		sShell = new Shell();
		sShell.setText("增加房间");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(386, 320));
		label = new Label(sShell, SWT.NONE);
		label.setText("         ");
		label.setLayoutData(gridData);
		Label filler17 = new Label(sShell, SWT.NONE);
		label11 = new Label(sShell, SWT.NONE);
		label11.setText(" ");
		label12 = new Label(sShell, SWT.NONE);
		label12.setText(" ");
		label12.setLayoutData(gridData9);
		Label filler18 = new Label(sShell, SWT.NONE);
		Label filler28 = new Label(sShell, SWT.NONE);
		Label filler40 = new Label(sShell, SWT.NONE);
		Label filler19 = new Label(sShell, SWT.NONE);
		Label filler27 = new Label(sShell, SWT.NONE);
		Label filler39 = new Label(sShell, SWT.NONE);
		label1 = new Label(sShell, SWT.NONE);
		label1.setText(" ");
		Label filler = new Label(sShell, SWT.NONE);
		Label filler16 = new Label(sShell, SWT.NONE);
		Label filler26 = new Label(sShell, SWT.NONE);
		Label filler38 = new Label(sShell, SWT.NONE);
		label2 = new Label(sShell, SWT.NONE);
		label2.setText(" ");
		label2.setLayoutData(gridData1);
		Label filler1 = new Label(sShell, SWT.NONE);
		label7 = new Label(sShell, SWT.NONE);
		label7.setText("包房编号：");
		label7.setLayoutData(gridData5);
		RoomNo = new Text(sShell, SWT.BORDER);
		RoomNo.setLayoutData(gridData10);
		Label filler37 = new Label(sShell, SWT.NONE);
		Label filler2 = new Label(sShell, SWT.NONE);
		Label filler36 = new Label(sShell, SWT.NONE);
		label3 = new Label(sShell, SWT.NONE);
		label3.setText(" ");
		label3.setLayoutData(gridData2);
		Label filler3 = new Label(sShell, SWT.NONE);
		label8 = new Label(sShell, SWT.NONE);
		label8.setText("包房类型：");
		label8.setLayoutData(gridData6);
		createRoomType();
		Label filler35 = new Label(sShell, SWT.NONE);
		Label filler6 = new Label(sShell, SWT.NONE);
		Label filler14 = new Label(getsShell(), SWT.NONE);
		Label filler34 = new Label(sShell, SWT.NONE);
		label4 = new Label(sShell, SWT.NONE);
		label4.setText(" ");
		label4.setLayoutData(gridData3);
		Label filler4 = new Label(sShell, SWT.NONE);
		label9 = new Label(sShell, SWT.NONE);
		label9.setText("容纳人数：");
		label9.setLayoutData(gridData7);
		PeopleNum = new Text(sShell, SWT.BORDER);
		PeopleNum.setLayoutData(gridData12);
		Label filler33 = new Label(sShell, SWT.NONE);
		Label filler7 = new Label(sShell, SWT.NONE);
		Label filler32 = new Label(sShell, SWT.NONE);
		label5 = new Label(sShell, SWT.NONE);
		label5.setText(" ");
		label5.setLayoutData(gridData4);
		Label filler5 = new Label(sShell, SWT.NONE);
		label10 = new Label(sShell, SWT.NONE);
		label10.setText("房间评价：");
		label10.setLayoutData(gridData8);
		remark = new Text(sShell, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		remark.setLayoutData(gridData13);
		Label filler8 = new Label(sShell, SWT.NONE);
		label6 = new Label(sShell, SWT.NONE);
		label6.setText(" ");
		Label filler9 = new Label(sShell, SWT.NONE);
		Label filler10 = new Label(sShell, SWT.NONE);
		Label filler13 = new Label(sShell, SWT.NONE);
		Label filler29 = new Label(sShell, SWT.NONE);
		Label filler11 = new Label(sShell, SWT.NONE);
		Label filler12 = new Label(sShell, SWT.NONE);
		button = new Button(sShell, SWT.NONE);
		button.setText("提交");
		button.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				String roomno=RoomNo.getText().trim();
				String type=RoomType.getText().trim();
				String num=PeopleNum.getText().trim();
				String mark=remark.getText().trim();
				String state="空闲";
				CommonADO ado=CommonADO.getCommonADO();
				String querySql="select * from Room where RoomNo='"+roomno+"'";
				ResultSet rs=ado.executeSelect(querySql);
				try {
					if(rs.next()){
						MessageDialog.openInformation(sShell, "信息提示", "该包房已存在");
					}
					else{
						String insertSql="insert into Room values('"+roomno+"','"+type+"','"+state+"','"+mark+"')";
						ado.executeUpdate(insertSql);
						MessageDialog.openInformation(sShell, "信息提示", "成功添加");
						//sShell.close();
						flag=true;
						SystemManageShell sm=new SystemManageShell();
						
						room.setRemarks(mark);
						room.setRoomNo(roomno);
						room.setState(state);
						room.setType(type);
						//sm.tableViewer.add(room);
						//sm.tableViewer.setInput(room);
						sm.getsShell().open();
						
						
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		button1 = new Button(sShell, SWT.NONE);
		button1.setText("取消");
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
public RoomAdd(){
	createSShell();
}
public Shell getsShell(){
	return sShell;
	
}
public boolean getFlag(){
	return flag;
}
/**
 * This method initializes RoomType	
 *
 */
private void createRoomType() {
	RoomType = new Combo(getsShell(), SWT.NONE);
	RoomType.add("小包房");
	RoomType.add("中包房");
	RoomType.add("大包房");
}
public Room getRoom(){
	return room;
}
}
