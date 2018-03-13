package visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.custom.ScrolledComposite;


import dataclass.CommonADO;
import dataclass.KTVRoom;
import dataclass.Room;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Color;

public class SystemMainShell {

	private Shell sShell = null;
	private ToolBar toolBar = null;
	private Composite compositeRoomInfo = null;
	private Composite compositeInfo = null;
	private Composite compositeRoomDisplay = null;
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
	public static Label labelRoomNo = null;
	public static Label labelRoomType = null;
	public static Label labelRoomState = null;
	public static Label labelPeopleNum = null;
	public static Label labelStartTime = null;
	public static Label labelExpectTime = null;
	public static Label labelPrePayMoney = null;
	private Label label10 = null;
	private Group group = null;
	private static Label labelSystemTime = null;
	private Label label11 = null;
	private Label label12 = null;
	private Label label13 = null;
	private Label label14 = null;
	private Label label15 = null;
	private Label label16 = null;
	private Label label17 = null;
	public static Label labelLittleTotal = null;
	private Label label18 = null;
	private Label label19 = null;
	private Label label20 = null;
	public static Label labelLittleLeft = null;
	public static Label labelMidTotal = null;
	private Label label21 = null;
	public static Label labelMidLeft = null;
	public static Label labelLargeTotal = null;
	private Label label22 = null;
	public static Label labelLargeLeft = null;
	private Label label23 = null;
	private TabFolder tabFolder = null;
	private Composite compositeLittle = null;
	private ScrolledComposite scrolledComposite1 = null;
	private Composite compositeLittleRooms = null;
	private Composite compositeMid = null;
	private ScrolledComposite scrolledComposite2 = null;
	private Composite compositeMidRooms = null;
	private Composite compositeLarge = null;
	private ScrolledComposite scrolledComposite3 = null;
	private Composite compositeLargeRooms = null;
	public static KTVRoom lastSelectedRoom=null;
	public static String userType="";
	
	public static int littleTotal=0;
	public static int littleLeft=0;
	public static int midTotal=0;
	public static int midLeft=0;
	public static int largeTotal=0;
	public static int largeLeft=0;
	private Button kaifangjilu = null;
	
	private Tray tray=null;
	private TrayItem trayitem=null;
	private ToolTip tip=null;
	
	
	/**
	 * This method initializes sShell
	 * @return 
	 */
	
	private void createSShell() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.horizontalSpacing = 3;
		gridLayout.verticalSpacing = 3;
		sShell = new Shell(SWT.NO_TRIM);
		sShell.setText("Shell");
		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_CYAN));
		sShell.setLayout(gridLayout);
		createToolBar();
		createCompositeRoomInfo();
		createCompositeRoomDisplay();
		createCompositeInfo();
		sShell.setSize(new Point(920, 600));
		labelRoomNo.setText("");
		createTray();
	}

	/**
	 * This method initializes toolBar	
	 *
	 */
	private void createToolBar() {
		GridData gridData = new GridData();
		gridData.heightHint = 88;
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		toolBar = new ToolBar(sShell, SWT.NONE);
		toolBar.setBackground(new Color(Display.getCurrent(), 14, 180, 246));
		toolBar.setLayoutData(gridData);
		//toolBar.setBackground(color);
		ToolItem toolItemCheckIn = new ToolItem(toolBar, SWT.PUSH);
		toolItemCheckIn.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/kaifangs.png")));
		toolItemCheckIn.setText("顾客开房");
		toolItemCheckIn
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						if(lastSelectedRoom!=null){
							if(lastSelectedRoom.getRoom().getState().trim().equals("使用")){
								MessageDialog.openInformation(sShell, "信息提示", "该包房正在使用,请重新选择");
								return;
							}
							CheckIn checkIn=new CheckIn();
							checkIn.getsShell().open();
						}else
							MessageDialog.openInformation(sShell, "信息提示", "请选择一个包房");
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolItemComsume = new ToolItem(toolBar, SWT.PUSH);
		toolItemComsume.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/xiaofeis.png")));
		toolItemComsume.setText("顾客消费");
		toolItemComsume
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						if(SystemMainShell.lastSelectedRoom==null||SystemMainShell.lastSelectedRoom.getRoom().getState().equals("空闲"))
							MessageDialog.openInformation(sShell, "信息提示", "未选中包房或包房不在使用");
						else
							new ConsumeShell().getsShell().open();
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolItemPay = new ToolItem(toolBar, SWT.PUSH);
		toolItemPay.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/jiezhangs.png")));
		toolItemPay.setText("顾客结账");
		toolItemPay
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						if(SystemMainShell.lastSelectedRoom==null||SystemMainShell.lastSelectedRoom.getRoom().getState().equals("空闲")){
							MessageDialog.openInformation(sShell, "信息提示", "请选择正在使用的包房结账");
						}
						else{
							new CheckOutShell().getsShell().open();
						}
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolItemSystem = new ToolItem(toolBar, SWT.PUSH);
		toolItemSystem.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/guanlis.png")));
		toolItemSystem.setText("系统管理");
		ToolItem toolItemSeperator = new ToolItem(toolBar, SWT.SEPARATOR);
		toolItemSeperator.setWidth(450);
		toolItemSystem
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						new SystemManageShell().getsShell().open();
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
		ToolItem toolItemLoginAgain = new ToolItem(toolBar, SWT.PUSH);
		toolItemLoginAgain.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/chongdengs.png")));
		toolItemLoginAgain.setText("重新登录");
		toolItemLoginAgain.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				new KTVLogin().getsShell().open();
				//sShell.close();
				sShell.setMinimized(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		ToolItem toolItemMinilize = new ToolItem(toolBar, SWT.PUSH);
		toolItemMinilize.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/zuixiaohuas.png")));
		toolItemMinilize.setText("最小化");
		toolItemMinilize.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				//sShell.setMinimized(true);
				sShell.setVisible(!sShell.isVisible());
				trayitem.setVisible(!sShell.isVisible());
				tip.setText("KTV管理系统");
				tip.setMessage("右键单击图标,可以选择菜单");
				tip.setVisible(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		ToolItem toolItemExit = new ToolItem(toolBar, SWT.PUSH);
		toolItemExit.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/guanbis.png")));
		toolItemExit.setText("关闭");
		toolItemExit
				.addSelectionListener(new org.eclipse.swt.events.SelectionListener() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						System.exit(0);
					}
					public void widgetDefaultSelected(
							org.eclipse.swt.events.SelectionEvent e) {
					}
				});
	}

	/**
	 * This method initializes compositeRoomInfo	
	 *
	 */
	private void createCompositeRoomInfo() {
		GridData gridData5 = new GridData();
		gridData5.grabExcessHorizontalSpace = true;
		GridData gridData4 = new GridData();
		gridData4.verticalSpan = 2;
		gridData4.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData4.horizontalSpan = 3;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 5;
		GridData gridData1 = new GridData();
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.widthHint = 260;
		gridData1.heightHint = 250;
		compositeRoomInfo = new Composite(sShell, SWT.NONE);
		compositeRoomInfo.setLayoutData(gridData1);
		compositeRoomInfo.setLayout(gridLayout1);
		label = new Label(compositeRoomInfo, SWT.NONE);
		label.setText("              ");
		Label filler = new Label(compositeRoomInfo, SWT.NONE);
		Label filler17 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler25 = new Label(compositeRoomInfo, SWT.NONE);
		label10 = new Label(compositeRoomInfo, SWT.NONE);
		label10.setText("");
		label10.setLayoutData(gridData5);
		Label filler1 = new Label(compositeRoomInfo, SWT.NONE);
		label1 = new Label(compositeRoomInfo, SWT.NONE);
		label1.setText("包房信息");
		label1.setFont(new Font(Display.getDefault(), "楷体", 12, SWT.NORMAL));
		label1.setLayoutData(gridData4);
		Label filler29 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler6 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler28 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler2 = new Label(compositeRoomInfo, SWT.NONE);
		label2 = new Label(compositeRoomInfo, SWT.NONE);
		label2.setText("包房编号");
		label9 = new Label(compositeRoomInfo, SWT.NONE);
		label9.setText("");
		labelRoomNo = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomNo.setText("             ");
		Label filler27 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler3 = new Label(compositeRoomInfo, SWT.NONE);
		label3 = new Label(compositeRoomInfo, SWT.NONE);
		label3.setText("包房类型");
		Label filler14 = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomType = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomType.setText("              ");
		Label filler26 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler4 = new Label(compositeRoomInfo, SWT.NONE);
		label4 = new Label(compositeRoomInfo, SWT.NONE);
		label4.setText("包房状态");
		Label filler13 = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomState = new Label(compositeRoomInfo, SWT.NONE);
		labelRoomState.setText("             ");
		Label filler22 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler5 = new Label(compositeRoomInfo, SWT.NONE);
		label5 = new Label(compositeRoomInfo, SWT.NONE);
		label5.setText("容纳人数");
		Label filler12 = new Label(compositeRoomInfo, SWT.NONE);
		labelPeopleNum = new Label(compositeRoomInfo, SWT.NONE);
		labelPeopleNum.setText("              ");
		Label filler21 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler7 = new Label(compositeRoomInfo, SWT.NONE);
		label6 = new Label(compositeRoomInfo, SWT.NONE);
		label6.setText("开始时间");
		Label filler11 = new Label(compositeRoomInfo, SWT.NONE);
		labelStartTime = new Label(compositeRoomInfo, SWT.NONE);
		labelStartTime.setText("              ");
		Label filler20 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler8 = new Label(compositeRoomInfo, SWT.NONE);
		label7 = new Label(compositeRoomInfo, SWT.NONE);
		label7.setText("预定时间");
		Label filler10 = new Label(compositeRoomInfo, SWT.NONE);
		labelExpectTime = new Label(compositeRoomInfo, SWT.NONE);
		labelExpectTime.setText("              ");
		Label filler19 = new Label(compositeRoomInfo, SWT.NONE);
		Label filler9 = new Label(compositeRoomInfo, SWT.NONE);
		label8 = new Label(compositeRoomInfo, SWT.NONE);
		label8.setText("已交押金");
		Label filler18 = new Label(compositeRoomInfo, SWT.NONE);
		labelPrePayMoney = new Label(compositeRoomInfo, SWT.NONE);
		labelPrePayMoney.setText("              ");
		Label filler15 = new Label(compositeRoomInfo, SWT.NONE);
		createGroup();
	}

	/**
	 * This method initializes compositeInfo	
	 *
	 */
	private void createCompositeInfo() {
		GridData gridData9 = new GridData();
		gridData9.grabExcessHorizontalSpace = true;
		GridData gridData8 = new GridData();
		gridData8.verticalSpan = 2;
		gridData8.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData8.horizontalSpan = 7;
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 9;
		GridData gridData3 = new GridData();
		gridData3.grabExcessVerticalSpace = true;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData3.widthHint = 260;
		gridData3.heightHint = 150;
		compositeInfo = new Composite(sShell, SWT.NONE);
		compositeInfo.setLayoutData(gridData3);
		compositeInfo.setLayout(gridLayout3);
		label11 = new Label(compositeInfo, SWT.NONE);
		label11.setText("   ");
		Label filler16 = new Label(compositeInfo, SWT.NONE);
		Label filler37 = new Label(compositeInfo, SWT.NONE);
		Label filler42 = new Label(compositeInfo, SWT.NONE);
		Label filler47 = new Label(compositeInfo, SWT.NONE);
		Label filler52 = new Label(compositeInfo, SWT.NONE);
		Label filler57 = new Label(compositeInfo, SWT.NONE);
		Label filler62 = new Label(compositeInfo, SWT.NONE);
		label23 = new Label(compositeInfo, SWT.NONE);
		label23.setText(" ");
		label23.setLayoutData(gridData9);
		Label filler23 = new Label(compositeInfo, SWT.NONE);
		label12 = new Label(compositeInfo, SWT.NONE);
		label12.setText("包房总信息");
		label12.setFont(new Font(Display.getDefault(), "新宋体", 12, SWT.NORMAL));
		label12.setLayoutData(gridData8);
		Label filler40 = new Label(compositeInfo, SWT.NONE);
		Label filler32 = new Label(compositeInfo, SWT.NONE);
		Label filler39 = new Label(compositeInfo, SWT.NONE);
		Label filler24 = new Label(compositeInfo, SWT.NONE);
		label13 = new Label(compositeInfo, SWT.NONE);
		label13.setText("小包房总数");
		label17 = new Label(compositeInfo, SWT.NONE);
		label17.setText("");
		labelLittleTotal = new Label(compositeInfo, SWT.NONE);
		labelLittleTotal.setText("");
		label18 = new Label(compositeInfo, SWT.NONE);
		label18.setText("");
		label19 = new Label(compositeInfo, SWT.NONE);
		label19.setText("剩余");
		label20 = new Label(compositeInfo, SWT.NONE);
		label20.setText("");
		labelLittleLeft = new Label(compositeInfo, SWT.NONE);
		labelLittleLeft.setText("          ");
		Label filler38 = new Label(compositeInfo, SWT.NONE);
		Label filler30 = new Label(compositeInfo, SWT.NONE);
		label14 = new Label(compositeInfo, SWT.NONE);
		label14.setText("中包房总数");
		Label filler34 = new Label(compositeInfo, SWT.NONE);
		labelMidTotal = new Label(compositeInfo, SWT.NONE);
		labelMidTotal.setText("       ");
		Label filler44 = new Label(compositeInfo, SWT.NONE);
		label21 = new Label(compositeInfo, SWT.NONE);
		label21.setText("剩余");
		Label filler54 = new Label(compositeInfo, SWT.NONE);
		labelMidLeft = new Label(compositeInfo, SWT.NONE);
		labelMidLeft.setText("         ");
		Label filler36 = new Label(compositeInfo, SWT.NONE);
		Label filler31 = new Label(compositeInfo, SWT.NONE);
		label15 = new Label(compositeInfo, SWT.NONE);
		label15.setText("大包房总数");
		Label filler33 = new Label(compositeInfo, SWT.NONE);
		labelLargeTotal = new Label(compositeInfo, SWT.NONE);
		labelLargeTotal.setText("      ");
		Label filler43 = new Label(compositeInfo, SWT.NONE);
		label22 = new Label(compositeInfo, SWT.NONE);
		label22.setText("剩余");
		Label filler53 = new Label(compositeInfo, SWT.NONE);
		labelLargeLeft = new Label(compositeInfo, SWT.NONE);
		labelLargeLeft.setText("        ");
		Label filler35 = new Label(compositeInfo, SWT.NONE);
		label16 = new Label(compositeInfo, SWT.NONE);
		label16.setText("   ");
		kaifangjilu = new Button(compositeInfo, SWT.NONE);
		kaifangjilu.setText("开房记录");
		kaifangjilu.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				new RoomOrder().getsShell().open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		String littleTotalQueryStr="select count(*) as littleTotal from room where Type='小包房'";
		String littleLeftQueryStr="select count(*) as littleLeft from room where Type='小包房' and State='空闲'";
		String midTotalQueryStr="select count(*) as midTotal from room where Type='中包房'";
		String midLeftQueryStr="select count(*) as midLeft from room where Type='中包房' and State='空闲'";
		String largeTotalQueryStr="select count(*) as largeTotal from room where Type='大包房'";
		String largeLeftQueryStr="select count(*) as largeLeft from room where Type='大包房' and State='空闲'";
		CommonADO ado=CommonADO.getCommonADO();
		ResultSet rs=null;
		
		try {
			rs=ado.executeSelect(littleTotalQueryStr);
			rs.next();
			littleTotal=rs.getInt("littleTotal");
			labelLittleTotal.setText(littleTotal+"");
			
			rs=ado.executeSelect(littleLeftQueryStr);
			rs.next();
			littleLeft=rs.getInt("littleLeft");
			labelLittleLeft.setText(littleLeft+"");
			
			rs=ado.executeSelect(midTotalQueryStr);
			rs.next();
			midTotal=rs.getInt("midTotal");
			labelMidTotal.setText(midTotal+"");
			
			rs=ado.executeSelect(midLeftQueryStr);
			rs.next();
			midLeft=rs.getInt("midLeft");
			labelMidLeft.setText(midLeft+"");
			
			rs=ado.executeSelect(largeTotalQueryStr);
			rs.next();
			largeTotal=rs.getInt("largeTotal");
			labelLargeTotal.setText(largeTotal+"");
			
			rs=ado.executeSelect(largeLeftQueryStr);
			rs.next();
			largeLeft=rs.getInt("largeLeft");
			labelLargeLeft.setText(largeLeft+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
				
	}

	/**
	 * This method initializes compositeRoomDisplay	
	 *
	 */
	private void createCompositeRoomDisplay() {
		GridData gridData2 = new GridData();
		gridData2.grabExcessHorizontalSpace = true;
		gridData2.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData2.verticalSpan = 2;
		gridData2.grabExcessVerticalSpace = true;
		compositeRoomDisplay = new Composite(sShell, SWT.NONE);
		compositeRoomDisplay.setLayout(new FillLayout());
		createTabFolder();
		compositeRoomDisplay.setLayoutData(gridData2);
	}

	/**
	 * This method initializes group	
	 *
	 */
	private void createGroup() {
		GridData gridData7 = new GridData();
		gridData7.horizontalSpan = 3;
		gridData7.widthHint = 140;
		gridData7.heightHint = 40;
		gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData7.grabExcessHorizontalSpace = true;
		gridData7.verticalSpan = 2;
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		GridData gridData6 = new GridData();
		gridData6.horizontalSpan = 5;
		gridData6.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData6.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData6.grabExcessVerticalSpace = true;
		group = new Group(compositeRoomInfo, SWT.NONE);
		group.setText("当前时间");
		group.setLayout(gridLayout2);
		group.setLayoutData(gridData6);
		labelSystemTime = new Label(group, SWT.NONE);
		labelSystemTime.setText("                                 ");
		labelSystemTime.setLayoutData(gridData7);
		TimeThread tt=new TimeThread();
		tt.start();
	}

	/**
	 * This method initializes tabFolder	
	 *
	 */
	private void createTabFolder() {
		tabFolder = new TabFolder(compositeRoomDisplay, SWT.NONE);
		createCompositeLittle();
		createCompositeMid();
		createCompositeLarge();
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("小包房");
		tabItem.setControl(compositeLittle);
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("中包房");
		tabItem3.setControl(compositeMid);
		TabItem tabItem4 = new TabItem(tabFolder, SWT.NONE);
		tabItem4.setText("大包房");
		tabItem4.setControl(compositeLarge);
	}

	/**
	 * This method initializes compositeLittle	
	 *
	 */
	private void createCompositeLittle() {
		compositeLittle = new Composite(tabFolder, SWT.V_SCROLL);
		compositeLittle.setLayout(new GridLayout());
		createScrolledComposite1();
	}

	/**
	 * This method initializes scrolledComposite1	
	 *
	 */
	private void createScrolledComposite1() {
		scrolledComposite1 = new ScrolledComposite(compositeLittle, SWT.V_SCROLL);
		scrolledComposite1.setAlwaysShowScrollBars(true);
		scrolledComposite1.setExpandVertical(true);
		createCompositeLittleRooms();
		scrolledComposite1.setExpandHorizontal(true);
		scrolledComposite1.setContent(compositeLittleRooms);
		scrolledComposite1.setMinSize(new Point(480, 480));
		
	}

	/**
	 * This method initializes compositeLittleRooms	
	 *
	 */
	private void createCompositeLittleRooms() {
		GridLayout gridLayout4 = new GridLayout();
		gridLayout4.horizontalSpacing = 8;
		gridLayout4.verticalSpacing = 8;
		gridLayout4.numColumns = 5;
		compositeLittleRooms = new Composite(scrolledComposite1, SWT.NONE);
		compositeLittleRooms.setLayout(gridLayout4);
		compositeLittleRooms
				.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
					public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
						System.out.println("mouseDown()"); // TODO Auto-generated Event stub mouseDown()
						labelRoomType.setText("小包房");
						labelPeopleNum.setText("5");
					}
				});
		displayRoom("小包房",compositeLittleRooms);
	}

	/**
	 * This method initializes compositeMid	
	 *
	 */
	private void createCompositeMid() {
		compositeMid = new Composite(tabFolder, SWT.V_SCROLL);
		compositeMid.setLayout(new GridLayout());
		createScrolledComposite2();
		compositeMid.addMouseListener(new org.eclipse.swt.events.MouseAdapter() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				System.out.println("mouseDown()"); // TODO Auto-generated Event stub mouseDown()
				labelRoomType.setText("中包房");
				labelPeopleNum.setText("10");
			}
		});
		
	}

	/**
	 * This method initializes scrolledComposite2	
	 *
	 */
	private void createScrolledComposite2() {
		scrolledComposite2 = new ScrolledComposite(compositeMid, SWT.V_SCROLL);
		scrolledComposite2.setAlwaysShowScrollBars(true);
		scrolledComposite2.setExpandVertical(true);
		createCompositeMidRooms();
		scrolledComposite2.setExpandHorizontal(true);
		scrolledComposite2.setContent(compositeMidRooms);
		scrolledComposite2.setMinSize(compositeMidRooms.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	/**
	 * This method initializes compositeMidRooms	
	 *
	 */
	private void createCompositeMidRooms() {
		compositeMidRooms = new Composite(scrolledComposite2, SWT.NONE);
		compositeMidRooms.setLayout(new GridLayout());
		displayRoom("中包房",compositeMidRooms);
	}

	/**
	 * This method initializes compositeLarge	
	 *
	 */
	private void createCompositeLarge() {
		compositeLarge = new Composite(tabFolder, SWT.V_SCROLL);
		compositeLarge.setLayout(new GridLayout());
		createScrolledComposite3();
		compositeLarge.addMouseListener(new org.eclipse.swt.events.MouseListener() {
			public void mouseDown(org.eclipse.swt.events.MouseEvent e) {
				System.out.println("mouseDown()"); // TODO Auto-generated Event stub mouseDown()
				labelRoomType.setText("大包房");
				labelPeopleNum.setText("15");
			}
			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent e) {
			}
			public void mouseUp(org.eclipse.swt.events.MouseEvent e) {
			}
		});
	}

	/**
	 * This method initializes scrolledComposite3	
	 *
	 */
	private void createScrolledComposite3() {
		scrolledComposite3 = new ScrolledComposite(compositeLarge, SWT.V_SCROLL);
		scrolledComposite3.setAlwaysShowScrollBars(true);
		scrolledComposite3.setExpandVertical(true);
		createCompositeLargeRooms();
		scrolledComposite3.setExpandHorizontal(true);
		scrolledComposite3.setContent(compositeLargeRooms);
		scrolledComposite3.setMinSize(compositeLargeRooms.computeSize(SWT.DEFAULT, SWT.DEFAULT));
	}

	/**
	 * This method initializes compositeLargeRooms	
	 *
	 */
	private void createCompositeLargeRooms() {
		GridLayout gridLayout5 = new GridLayout();
		gridLayout5.horizontalSpacing = 8;
		gridLayout5.verticalSpacing = 8;
		gridLayout5.numColumns = 5;
		compositeLargeRooms = new Composite(scrolledComposite3, SWT.NONE);
		compositeLargeRooms.setLayout(gridLayout5);
		displayRoom("大包房",compositeLargeRooms);
	}
	public void displayRoom(String roomType,Composite compositeRoomDisplay){
		String sqlStr="select * from room where Type='"+roomType+"'";
		CommonADO ado=CommonADO.getCommonADO();
		ResultSet rs=ado.executeSelect(sqlStr);
		try {
			rs.last();
			int littleNum=rs.getRow();
			rs.beforeFirst();
			rs.next();
			KTVRoom[] ktvRooms=new KTVRoom[littleNum];
			for(int i=0;i<littleNum;i++){
				Room room=new Room();
				room.setRoomNo(rs.getString("RoomNo"));
				room.setType(rs.getString("Type"));
				room.setState(rs.getString("State"));
				ktvRooms[i]=new KTVRoom(compositeRoomDisplay,SWT.NONE);
				ktvRooms[i].setRoom(room);
				ktvRooms[i].setText(room.getRoomNo());
				if(ktvRooms[i].getRoom().getState().trim().equals("空闲"))
					ktvRooms[i].setImage(new Image(Display.getCurrent(),
							getClass().getResourceAsStream("/images/blackht.png")));
				else
					ktvRooms[i].setImage(new Image(Display.getCurrent(),
							getClass().getResourceAsStream("/images/redht.png")));
				
				ktvRooms[i].setCursor(new org.eclipse.swt.graphics.Cursor(null,
						SWT.CURSOR_HAND));
				MyMouseListener mm=new MyMouseListener();
				ktvRooms[i].addMouseListener(mm);
				rs.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	class MyMouseListener implements MouseListener{
		public MyMouseListener(){
			super();
		}
		
		public void mouseDoubleClick(MouseEvent arg0){
			
		}
		public void mouseDown(MouseEvent arg0) {
			KTVRoom selectedktvRoom=(KTVRoom)arg0.getSource();
			if(lastSelectedRoom==null)
				lastSelectedRoom=(KTVRoom)arg0.getSource();
			if(lastSelectedRoom!=null){
				if(lastSelectedRoom.getRoom().getState().trim().equals("空闲"))
					lastSelectedRoom.setImage(new Image(Display.getCurrent()
							,getClass().getResourceAsStream("/images/blackht.png")));
				else
					lastSelectedRoom.setImage(new Image(Display.getCurrent()
							,getClass().getResourceAsStream("/images/redht.png")));
				selectedktvRoom.setImage(new Image(Display.getCurrent()
						,getClass().getResourceAsStream("/images/goldht.png")));
				lastSelectedRoom=selectedktvRoom;
				labelRoomNo.setText(selectedktvRoom.getRoom().getRoomNo());
				System.out.println(selectedktvRoom.getRoom().getRoomNo());
				System.out.println("click");
				labelRoomType.setText(selectedktvRoom.getRoom().getType());
				labelRoomState.setText(selectedktvRoom.getRoom().getState());
				CommonADO ado=CommonADO.getCommonADO();
				String peopleNumQuery="select * from RoomType where Type='"
					+selectedktvRoom.getRoom().getType()+"'";
				ResultSet rs=ado.executeSelect(peopleNumQuery);
				try {
					rs.next();
					labelPeopleNum.setText(rs.getInt("peopleNums")+"");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(selectedktvRoom.getRoom().getState().trim().equals("空闲")){
					labelStartTime.setText("");
					labelExpectTime.setText("");
					labelPrePayMoney.setText("");
				}
				else{
					String roomUseQuery="select * from RoomOrder where RoomNo='"
						+selectedktvRoom.getRoom().getRoomNo()+"' order by RoomOrderNo DESC";
					rs=ado.executeSelect(roomUseQuery);
					try {
						rs.next();
						labelStartTime.setText(rs.getString("StartTime"));
						labelExpectTime.setText(rs.getString("ExpectTime"));
						labelPrePayMoney.setText(rs.getFloat("PrePayMoney")+"");
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
	class TimeThread extends Thread{
		public void run(){
			Display disp=sShell.getDisplay();
			while(true){
				disp.syncExec(new Runnable(){
					public void run(){
						SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
						Date dateTime = new Date();
						String time=sdf.format(dateTime);
						labelSystemTime.setText(time);
					}
				});
				try{
					Thread.sleep(1000);
				}catch(InterruptedException e){}
			}
		}
	}
	public SystemMainShell(){
		createSShell();
	}
	public Shell getsShell(){
		return sShell;
	}
	void createTray(){
		tip=new ToolTip(sShell,SWT.BALLOON|SWT.ICON_INFORMATION);
		tray=Display.getDefault().getSystemTray();
		if(tray!=null){
			trayitem=new TrayItem(tray, SWT.NONE);
			trayitem.setVisible(false);
			trayitem.setToolTipText("KTV管理系统");
			trayitem.setToolTip(tip);
			trayitem.setImage(new Image(Display.getCurrent(),getClass().getResourceAsStream("/images/home.png")));
			final Menu menuTray=new Menu(sShell,SWT.POP_UP);
			MenuItem pushDisplay=new MenuItem(menuTray, SWT.PUSH);
			pushDisplay.setText("显示");
			pushDisplay.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					sShell.setVisible(!sShell.isVisible());
					if(sShell.getVisible()){
						sShell.setActive();
					}
					tip.setText("KTV系统图标");
					tip.setMessage("右键单击图标,可以选择菜单");
					tip.setVisible(true);
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			MenuItem pushExit=new MenuItem(menuTray, SWT.PUSH);
			pushExit.setText("退出");
			pushExit.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					System.exit(0);
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			trayitem.addMenuDetectListener(new MenuDetectListener() {
				
				@Override
				public void menuDetected(MenuDetectEvent arg0) {
					// TODO Auto-generated method stub
					menuTray.setVisible(true);
				}
			});
			trayitem.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					sShell.setVisible(!sShell.isVisible());
					if(sShell.getVisible())
						sShell.setActive();
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
}
