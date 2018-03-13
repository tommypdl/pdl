package visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Font;

import dataclass.CommonADO;
import dataclass.DateTimeUtil;

public class CheckOutShell {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Label label = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Label label6 = null;
	private Label label7 = null;
	private Label label8 = null;
	private Label label10 = null;
	private Label label11 = null;
	private Label label1 = null;
	private Label label12 = null;
	private Label label13 = null;
	private Label label9 = null;
	private Label label2 = null;
	private Label label14 = null;
	private Label labelRoomNo = null;
	private Label labelCheckOutTime = null;
	private Label labelRoomMoney = null;
	private Label labelGoodsMoney = null;
	private Label labelTotalMoney = null;
	private Label labelPrePayMoney = null;
	private Label labelToPay = null;
	private Label label15 = null;
	private Button buttonCheckOut = null;
	private Button buttonCancel = null;
	private Button buttonConsumeList = null;
	private Label label16 = null;
	private String roomNo=null;
	private String checkOutTime=null;
	private float roomMoney=0;
	private float goodsMoney=0;
	private float totalMoney=0;
	private float prePayMoney=0;
	private float toPayMoney=0;

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData20 = new GridData();
		gridData20.grabExcessHorizontalSpace = true;
		gridData20.grabExcessVerticalSpace = true;
		GridData gridData7 = new GridData();
		gridData7.grabExcessHorizontalSpace = true;
		gridData7.grabExcessVerticalSpace = true;
		GridData gridData6 = new GridData();
		gridData6.grabExcessHorizontalSpace = true;
		gridData6.grabExcessVerticalSpace = true;
		GridData gridData5 = new GridData();
		gridData5.grabExcessHorizontalSpace = true;
		gridData5.grabExcessVerticalSpace = true;
		GridData gridData4 = new GridData();
		gridData4.horizontalSpan = 2;
		GridData gridData3 = new GridData();
		gridData3.horizontalSpan = 2;
		gridData3.heightHint = 60;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.widthHint = 56;
		GridData gridData2 = new GridData();
		gridData2.widthHint = 60;
		GridData gridData1 = new GridData();
		gridData1.widthHint = 60;
		GridData gridData = new GridData();
		gridData.heightHint = 30;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 9;
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(441, 299));
		label = new Label(sShell, SWT.NONE);
		label.setText("         ");
		label.setLayoutData(gridData20);
		Label filler25 = new Label(sShell, SWT.NONE);
		label3 = new Label(sShell, SWT.NONE);
		label3.setText("");
		label13 = new Label(sShell, SWT.NONE);
		label13.setText("Label");
		label13.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/shop.png")));
		label13.setLayoutData(gridData3);
		label16 = new Label(sShell, SWT.NONE);
		label16.setText("顾客结账");
		label16.setFont(new Font(Display.getDefault(), "微软雅黑", 14, SWT.NORMAL));
		label16.setLayoutData(gridData4);
		label9 = new Label(sShell, SWT.NONE);
		label9.setText("");
		Label filler33 = new Label(sShell, SWT.NONE);
		label4 = new Label(sShell, SWT.NONE);
		label4.setText("");
		label4.setLayoutData(gridData);
		Label filler = new Label(sShell, SWT.NONE);
		Label filler2 = new Label(sShell, SWT.NONE);
		Label filler15 = new Label(sShell, SWT.NONE);
		Label filler19 = new Label(sShell, SWT.NONE);
		Label filler23 = new Label(sShell, SWT.NONE);
		Label filler7 = new Label(sShell, SWT.NONE);
		Label filler28 = new Label(sShell, SWT.NONE);
		Label filler32 = new Label(sShell, SWT.NONE);
		Label filler3 = new Label(sShell, SWT.NONE);
		label5 = new Label(sShell, SWT.NONE);
		label5.setText("结账包房");
		label5.setLayoutData(gridData1);
		Label filler5 = new Label(sShell, SWT.NONE);
		labelRoomNo = new Label(sShell, SWT.NONE);
		labelRoomNo.setText("          ");
		label10 = new Label(sShell, SWT.NONE);
		label10.setText("               ");
		label11 = new Label(sShell, SWT.NONE);
		label11.setText("结账时间");
		label11.setLayoutData(gridData2);
		label1 = new Label(sShell, SWT.NONE);
		label1.setText("");
		labelCheckOutTime = new Label(sShell, SWT.NONE);
		labelCheckOutTime.setText("");
		label12 = new Label(sShell, SWT.NONE);
		label12.setText("");
		Label filler6 = new Label(sShell, SWT.NONE);
		label6 = new Label(sShell, SWT.NONE);
		label6.setText("包房消费金额");
		Label filler8 = new Label(sShell, SWT.NONE);
		labelRoomMoney = new Label(sShell, SWT.NONE);
		labelRoomMoney.setText("");
		Label filler18 = new Label(sShell, SWT.NONE);
		label2 = new Label(sShell, SWT.NONE);
		label2.setText("商品消费金额");
		Label filler4 = new Label(sShell, SWT.NONE);
		labelGoodsMoney = new Label(sShell, SWT.NONE);
		labelGoodsMoney.setText("");
		Label filler31 = new Label(sShell, SWT.NONE);
		Label filler9 = new Label(sShell, SWT.NONE);
		label7 = new Label(sShell, SWT.NONE);
		label7.setText("消费总额");
		Label filler11 = new Label(sShell, SWT.NONE);
		labelTotalMoney = new Label(sShell, SWT.NONE);
		labelTotalMoney.setText("");
		Label filler17 = new Label(sShell, SWT.NONE);
		label14 = new Label(sShell, SWT.NONE);
		label14.setText("已收押金");
		Label filler1 = new Label(sShell, SWT.NONE);
		labelPrePayMoney = new Label(sShell, SWT.NONE);
		labelPrePayMoney.setText("");
		Label filler30 = new Label(sShell, SWT.NONE);
		Label filler12 = new Label(sShell, SWT.NONE);
		label8 = new Label(sShell, SWT.NONE);
		label8.setText("顾客应付");
		Label filler131 = new Label(sShell, SWT.NONE);
		labelToPay = new Label(sShell, SWT.NONE);
		labelToPay.setText("");
		Label filler132 = new Label(sShell, SWT.NONE);
		Label filler133 = new Label(sShell, SWT.NONE);
		Label filler134 = new Label(sShell, SWT.NONE);
		Label filler135 = new Label(sShell, SWT.NONE);
		Label filler136 = new Label(sShell, SWT.NONE);
		label15 = new Label(sShell, SWT.NONE);
		label15.setText("");
		Label filler137 = new Label(sShell, SWT.NONE);
		Label filler138 = new Label(sShell, SWT.NONE);
		Label filler139 = new Label(sShell, SWT.NONE);
		Label filler1310 = new Label(sShell, SWT.NONE);
		Label filler1311 = new Label(sShell, SWT.NONE);
		Label filler1312 = new Label(sShell, SWT.NONE);
		Label filler1313 = new Label(sShell, SWT.NONE);
		Label filler1314 = new Label(sShell, SWT.NONE);
		Label filler1315 = new Label(sShell, SWT.NONE);
		Label filler1316 = new Label(sShell, SWT.NONE);
		Label filler1317 = new Label(sShell, SWT.NONE);
		Label filler1318 = new Label(sShell, SWT.NONE);
		buttonCheckOut = new Button(sShell, SWT.NONE);
		buttonCheckOut.setText("结账");
		buttonCheckOut.setLayoutData(gridData5);
		buttonCheckOut
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						String insertStr="insert into Acountcheck(RoomNo,ConsumeMoney,CheckOutTime) values('"+roomNo+"','"+totalMoney+"','"+checkOutTime+"')";
						String updateRoomOrder="update RoomOrder set EndTime='"+checkOutTime+"' ,PayMoney='"+totalMoney+"' where RoomNo='"+roomNo+"'";
						String updateGoodsOrder="update GoodsOrder set PayState='已结账' where RoomNo='"+roomNo+"'";
						String updateRoom="update Room set State='空闲' where RoomNo='"+roomNo+"'";
						CommonADO ado=CommonADO.getCommonADO();
						if(ado.executeUpdate(insertStr)>0){
							ado.executeUpdate(updateRoomOrder);
							ado.executeUpdate(updateGoodsOrder);
							ado.executeUpdate(updateRoom);
							SystemMainShell.lastSelectedRoom.getRoom().setState("空闲");
							SystemMainShell.lastSelectedRoom.setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/blackht.png")));
							String roomType=SystemMainShell.lastSelectedRoom.getRoom().getType();
							if(roomType.equals("小包房")){
								SystemMainShell.littleLeft+=1;
								SystemMainShell.labelLittleLeft.setText(SystemMainShell.littleLeft+"");
							}
							else if(roomType.equals("中包房")){
								SystemMainShell.midLeft+=1;
								SystemMainShell.labelMidLeft.setText(SystemMainShell.midLeft+"");
							}
							else{
								SystemMainShell.largeLeft+=1;
								SystemMainShell.labelLargeLeft.setText(SystemMainShell.largeLeft+"");
							}
							MessageDialog.openInformation(sShell, "信息提示", "结账成功");
							sShell.dispose();
						}
						else
							MessageDialog.openInformation(sShell, "信息提示", "结账不成功");
					}
				});
		buttonCancel = new Button(sShell, SWT.NONE);
		buttonCancel.setText("取消");
		buttonCancel.setLayoutData(gridData6);
		Label filler1319 = new Label(sShell, SWT.NONE);
		buttonConsumeList = new Button(sShell, SWT.NONE);
		buttonConsumeList.setText("消费明细<<");
		buttonConsumeList.setLayoutData(gridData7);
		buttonConsumeList
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						new ConsumeShell().getsShell().open();
					}
				});
		
		roomNo=SystemMainShell.lastSelectedRoom.getRoom().getRoomNo();
		labelRoomNo.setText(roomNo);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date dateTime=new Date();
		checkOutTime=format.format(dateTime);
		labelCheckOutTime.setText(checkOutTime);
		CommonADO ado=CommonADO.getCommonADO();
		String querySql1="select * from RoomOrder where RoomNo='"+roomNo+"' and EndTime is NULL";
		String querySql2="select * from RoomType where Type='"+SystemMainShell.lastSelectedRoom.getRoom().getType()+"'";
		ResultSet rs=null;
		rs=ado.executeSelect(querySql1);
		try {
			rs.next();
			prePayMoney=rs.getFloat("PrePayMoney");
			labelPrePayMoney.setText(prePayMoney+"");
			String checkInTime=rs.getString("StartTime");
			rs=ado.executeSelect(querySql2);
			rs.next();
			float roomPrice=rs.getFloat("Price");
			DateTimeUtil dateTimeUtil1=new DateTimeUtil(checkInTime);
			DateTimeUtil dateTimeUtil2=new DateTimeUtil(checkOutTime);
			int userTime=(dateTimeUtil2.getHour()*60+dateTimeUtil2.getMinute())-(dateTimeUtil1.getHour()*60+dateTimeUtil1.getMinute());
			int userHour=0;
			if(userTime%60!=0)
				userHour=userTime/60+1;
			else
				userHour=userTime/60;
			roomMoney=roomPrice*userHour;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labelRoomMoney.setText(roomMoney+"");
		String goodsQueryStr="select sum(GoodsTotalPrice) as GoodsMoney from GoodsOrder where RoomNo='"+roomNo+"' and PayState='未结账'";
		rs=ado.executeSelect(goodsQueryStr);
		try {
			rs.next();
			goodsMoney=rs.getFloat("GoodsMoney");
			labelGoodsMoney.setText(goodsMoney+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		totalMoney=roomMoney+goodsMoney;
		labelTotalMoney.setText(totalMoney+"");
		toPayMoney=totalMoney-prePayMoney;
		labelToPay.setText(toPayMoney+"");
	}
	public CheckOutShell(){
		super();
		createSShell();
	}
	public Shell getsShell(){
		return sShell;
	}
}
