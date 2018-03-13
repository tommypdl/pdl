package visualclass;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Font;

import dataclass.CommonADO;

public class CheckIn {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Label label6 = null;
	private Label label7 = null;
	private Label labelRoomNo = null;
	private Label labelRoomType = null;
	private Label labelCheckInTime = null;
	private Text textExpectTime = null;
	private Text textPrePayMoney = null;
	private Label label8 = null;
	private Button buttonCheckIn = null;
	private Button buttonCancel = null;
	private Label label9 = null;
	private Label label10 = null;
	private Label label11 = null;
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData4 = new GridData();
		gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		GridData gridData3 = new GridData();
		gridData3.verticalSpan = 2;
		GridData gridData2 = new GridData();
		gridData2.verticalSpan = 2;
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		GridData gridData = new GridData();
		gridData.verticalSpan = 2;
		sShell = new Shell();
		sShell.setText("顾客开房");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(326, 312));
		label = new Label(sShell, SWT.NONE);
		label.setText("               ");
		label.setLayoutData(gridData);
		label10 = new Label(sShell, SWT.NONE);
		label10.setText("Label");
		label10.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/peoples.png")));
		label10.setLayoutData(gridData2);
		Label filler13 = new Label(sShell, SWT.NONE);
		label11 = new Label(sShell, SWT.NONE);
		label11.setText("顾客开房");
		label11.setFont(new Font(Display.getDefault(), "楷体", 12, SWT.NORMAL));
		label11.setLayoutData(gridData3);
		label9 = new Label(sShell, SWT.NONE);
		label9.setText("");
		label9.setLayoutData(gridData1);
		Label filler12 = new Label(sShell, SWT.NONE);
		Label filler30 = new Label(sShell, SWT.NONE);
		Label filler2 = new Label(sShell, SWT.NONE);
		label1 = new Label(sShell, SWT.NONE);
		label1.setText("");
		Label filler11 = new Label(sShell, SWT.NONE);
		Label filler17 = new Label(sShell, SWT.NONE);
		Label filler29 = new Label(sShell, SWT.NONE);
		Label filler3 = new Label(sShell, SWT.NONE);
		label2 = new Label(sShell, SWT.NONE);
		label2.setText("包房编号");
		label7 = new Label(sShell, SWT.NONE);
		label7.setText(" ");
		labelRoomNo = new Label(sShell, SWT.NONE);
		labelRoomNo.setText("           ");
		Label filler28 = new Label(sShell, SWT.NONE);
		Label filler4 = new Label(sShell, SWT.NONE);
		label3 = new Label(sShell, SWT.NONE);
		label3.setText("包房类型");
		Label filler10 = new Label(sShell, SWT.NONE);
		labelRoomType = new Label(sShell, SWT.NONE);
		labelRoomType.setText("            ");
		Label filler27 = new Label(sShell, SWT.NONE);
		Label filler5 = new Label(sShell, SWT.NONE);
		label4 = new Label(sShell, SWT.NONE);
		label4.setText("开房时间");
		Label filler9 = new Label(sShell, SWT.NONE);
		labelCheckInTime = new Label(sShell, SWT.NONE);
		labelCheckInTime.setText("           ");
		Label filler26 = new Label(sShell, SWT.NONE);
		Label filler6 = new Label(sShell, SWT.NONE);
		label5 = new Label(sShell, SWT.NONE);
		label5.setText("预定时间");
		Label filler8 = new Label(sShell, SWT.NONE);
		textExpectTime = new Text(sShell, SWT.BORDER);
		Label filler25 = new Label(sShell, SWT.NONE);
		Label filler7 = new Label(sShell, SWT.NONE);
		label6 = new Label(sShell, SWT.NONE);
		label6.setText("预交押金");
		Label filler14 = new Label(sShell, SWT.NONE);
		textPrePayMoney = new Text(sShell, SWT.BORDER);
		Label filler24 = new Label(sShell, SWT.NONE);
		label8 = new Label(sShell, SWT.NONE);
		label8.setText("");
		Label filler15 = new Label(sShell, SWT.NONE);
		Label filler16 = new Label(sShell, SWT.NONE);
		Label filler20 = new Label(sShell, SWT.NONE);
		Label filler23 = new Label(sShell, SWT.NONE);
		Label filler21 = new Label(sShell, SWT.NONE);
		buttonCheckIn = new Button(sShell, SWT.NONE);
		buttonCheckIn.setText("提交订单");
		buttonCheckIn
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						String roomNo=labelRoomNo.getText().trim();
						String checkInTime=labelCheckInTime.getText().trim();
						String expectTime=textExpectTime.getText().trim();
						
						/*SystemMainShell sm=new SystemMainShell();
						sm.labelExpectTime.setText(expectTime);
						sm.labelStartTime.setText(checkInTime);*/
						float prePayMoney=Float.parseFloat(textPrePayMoney.getText().trim());
						String insertStr="insert into RoomOrder(RoomNo,StartTime,ExpectTime,PrePayMoney) values('"+roomNo+"'," +
								"'"+checkInTime+"','"+expectTime+"',"+prePayMoney+")";
						CommonADO ado=CommonADO.getCommonADO();
						if(ado.executeUpdate(insertStr)>0){
							String updateStr="update room set State='使用' where RoomNo='"+roomNo+"'";
							if(ado.executeUpdate(updateStr)>0){
								SystemMainShell.lastSelectedRoom.setImage(new Image(Display.getCurrent(),
										getClass().getResourceAsStream("/images/redht.png")));
								SystemMainShell.lastSelectedRoom.getRoom().setState("使用");
								String roomType=SystemMainShell.lastSelectedRoom.getRoom().getType();
								if(roomType.equals("小包房")){
									SystemMainShell.littleLeft-=1;
									SystemMainShell.labelLittleLeft.setText(SystemMainShell.littleLeft+"");
								}
								else if(roomType.equals("中包房")){
									SystemMainShell.midLeft-=1;
									SystemMainShell.labelMidLeft.setText(SystemMainShell.midLeft+"");
								}
								else{
									SystemMainShell.largeLeft-=1;
									SystemMainShell.labelLargeLeft.setText(SystemMainShell.largeLeft+"");
								}
								MessageDialog.openInformation(sShell, "信息提示", "开房成功");
								sShell.dispose();
							}
						}
					}
				});
		Label filler22 = new Label(sShell, SWT.NONE);
		buttonCancel = new Button(sShell, SWT.NONE);
		buttonCancel.setText("取消");
		buttonCancel.setLayoutData(gridData4);
		labelRoomNo.setText(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo());
		labelRoomType.setText(SystemMainShell.lastSelectedRoom.getRoom().getType());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM--dd HH:mm");
		Date dateTime=new Date();
		String time=sdf.format(dateTime);
		labelCheckInTime.setText(time);
	}
	public CheckIn(){
		createSShell();
	}
	public Shell getsShell(){
		return sShell;
	}
}
