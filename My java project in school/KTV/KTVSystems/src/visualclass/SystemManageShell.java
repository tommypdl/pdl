package visualclass;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Group;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import java.lang.Object;
import org.eclipse.jface.viewers.Viewer;

import dataclass.CommonADO;
import dataclass.GoodsEntity;
import dataclass.GoodsShow;
import dataclass.KTVRoom;
import dataclass.Room;
import dataclass.RoomFactory;
import org.eclipse.jface.viewers.ITableLabelProvider;
import java.lang.String;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.wizard.WizardDialog;

import RoomAddWizard.RoomAddWizard;
import org.eclipse.swt.custom.ScrolledComposite;

import visualclass.SystemMainShell.MyMouseListener;
import org.eclipse.swt.widgets.Text;

public class SystemManageShell {

	public static  Room newRoom = null;
	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private CLabel cLabel = null;
	private TabFolder tabFolder = null;
	private Composite compositeRoomMange = null;
	private Composite compositeGoodsMange = null;
	private Composite compositeStaffMange = null;
	private Composite compositeUserMange = null;
	private Table tableRoom = null;
	private Button buttonAddRoom = null;
	private Button buttonDeleteRoom = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Button buttonAddStaff = null;
	private Button buttonDeleteStaff = null;
	private Button buttonAddUser = null;
	private Button buttonDeleteUser = null;
	public TableViewer tableViewer = null;
	private Text textAreaStaff = null;
	private Text textAreaUser = null;
	public String input="";  //  @jve:decl-index=0:
	private Button buttonAddGoods = null;
	private Label label = null;
	private Button buttonDeleteGoods = null;
	private Composite compositeGoodsShow = null;
	/**
	 * This method initializes sShell
	 */
	public void createSShell() {
		GridData gridData = new GridData();
		gridData.widthHint = 500;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 80;
		sShell = new Shell();
		sShell.setText("系统管理");
		sShell.setSize(new Point(703, 656));
		sShell.setLayout(new GridLayout());
		cLabel = new CLabel(sShell, SWT.CENTER);
		cLabel.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/news.png")));
		cLabel.setFont(new Font(Display.getDefault(), "微软雅黑", 18, SWT.NORMAL));
		cLabel.setLayoutData(gridData);
		cLabel.setText("系统管理");
		createTabFolder();
		if(SystemMainShell.userType.equals("管理员")){
			buttonAddRoom.setEnabled(true);
			buttonDeleteRoom.setEnabled(true);
		//	buttonAddGoods.setEnabled(true);
		//	buttonDeleteGoods.setEnabled(true);
			buttonAddStaff.setEnabled(true);
			buttonDeleteStaff.setEnabled(true);
			buttonAddUser.setEnabled(true);
			buttonDeleteUser.setEnabled(true);
		}else if(SystemMainShell.userType.equals("操作员")){
			buttonAddRoom.setEnabled(false);
			buttonDeleteRoom.setEnabled(false);
		//	buttonAddGoods.setEnabled(false);
		//	buttonDeleteGoods.setEnabled(false);
			buttonAddStaff.setEnabled(false);
			buttonDeleteStaff.setEnabled(false);
			buttonAddUser.setEnabled(false);
			buttonDeleteUser.setEnabled(false);
		}
	}
	/**
	 * This method initializes tabFolder	
	 *
	 */
	private void createTabFolder() {
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.heightHint = 500;
		gridData1.grabExcessVerticalSpace = true;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.BEGINNING;
		gridData1.widthHint = 600;
		tabFolder = new TabFolder(sShell, SWT.NONE);
		tabFolder.setLayoutData(gridData1);
		createCompositeRoomMange();
		createCompositeGoodsMange();
		createCompositeStaffMange();
		createCompositeUserMange();
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("房间管理");
		tabItem.setControl(compositeRoomMange);
		TabItem tabItem1 = new TabItem(tabFolder, SWT.NONE);
		tabItem1.setText("商品管理");
		tabItem1.setControl(compositeGoodsMange);
		TabItem tabItem2 = new TabItem(tabFolder, SWT.NONE);
		tabItem2.setText("员工管理");
		tabItem2.setControl(compositeStaffMange);
		TabItem tabItem3 = new TabItem(tabFolder, SWT.NONE);
		tabItem3.setText("系统用户管理");
		tabItem3.setControl(compositeUserMange);
	}
	/**
	 * This method initializes compositeRoomMange	
	 *
	 */
	public void createCompositeRoomMange() {
		compositeRoomMange = new Composite(tabFolder, SWT.V_SCROLL);
		compositeRoomMange.setLayout(null);
		compositeRoomMange.setSize(new Point(500, 340));
		tableRoom = new Table(compositeRoomMange, SWT.NONE);
		tableRoom.setHeaderVisible(true);
		tableRoom.setLocation(new Point(5, 5));
		tableRoom.setLinesVisible(true);
		tableRoom.setSize(new Point(540, 330));
		tableViewer = new TableViewer(tableRoom);
		
		TableColumn tableColumn = new TableColumn(tableRoom, SWT.NONE);
		tableColumn.setWidth(60);
		tableColumn.setText("房间编号");
		TableColumn tableColumn1 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn1.setWidth(60);
		tableColumn1.setText("房间类型");
		TableColumn tableColumn2 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn2.setWidth(60);
		tableColumn2.setText("容纳人数");
		TableColumn tableColumn3 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn3.setWidth(60);
		tableColumn3.setText("包房单价");
		TableColumn tableColumn4 = new TableColumn(tableRoom, SWT.NONE);
		tableColumn4.setWidth(200);
		tableColumn4.setText("房间评价");
		buttonAddRoom = new Button(compositeRoomMange, SWT.NONE);
		buttonAddRoom.setBounds(new Rectangle(79, 382, 81, 27));
		buttonAddRoom.setText("增加房间");
		buttonAddRoom
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						//RoomAddWizard roomAddWizard=new RoomAddWizard();
						//WizardDialog wDialog=new WizardDialog(sShell,roomAddWizard);
						RoomAdd ra=new RoomAdd();
						ra.getsShell().open();
						
						sShell.setMinimized(true);
						/*if(ra.flag){
							newRoom=ra.getRoom();
							tableViewer.add(SystemManageShell.newRoom);
						}*/
						}
					}
				);
		buttonDeleteRoom = new Button(compositeRoomMange, SWT.NONE);
		buttonDeleteRoom.setBounds(new Rectangle(324, 381, 96, 27));
		buttonDeleteRoom.setText("删除房间");
		buttonDeleteRoom
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						int deleteIndex=tableRoom.getSelectionIndex();
						Room roomToDel=(Room)tableRoom.getItem(deleteIndex).getData();
						tableViewer.remove(roomToDel);
						RoomFactory.deleteDB(roomToDel);
					}
				});
	}
	/**
	 * This method initializes compositeGoodsMange	
	 *
	 */
	private void createCompositeGoodsMange() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		compositeGoodsMange = new Composite(tabFolder, SWT.NONE);
		compositeGoodsMange.setLayout(gridLayout);
		compositeGoodsMange.setSize(new Point(500, 400));
		
		buttonAddGoods = new Button(compositeGoodsMange, SWT.NONE);
		buttonAddGoods.setText("增加商品");
		buttonAddGoods.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				InputDialog id1=new InputDialog(sShell,"新增商品","输入商品信息，用空格分开,例如：001 方便面  6.8","",null);
				if(id1.open()==0){
					input=id1.getValue();
					if(input.equals("")) return;
				}
				else return;
				String str[]=input.split(" ");
				CommonADO ado=CommonADO.getCommonADO();
				String sql="insert into Goods values('"+str[0]+"','"+str[1]+"','"+str[2]+"')";
				ado.executeUpdate(sql);
				
				
				compositeGoodsShow.dispose();				
				createCompositeGoodsShow();
				
				compositeGoodsMange.layout(true);
				//compositeGoodsShow.layout(true);
		
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		label = new Label(compositeGoodsMange, SWT.NONE);
		label.setText("                   ");
		buttonDeleteGoods = new Button(compositeGoodsMange, SWT.NONE);
		buttonDeleteGoods.setText("删除商品");
		buttonDeleteGoods.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				InputDialog id1=new InputDialog(sShell,"删除商品","输入商品编号","",null);
				if(id1.open()==0){
					input=id1.getValue();
					if(input.equals("")) return;
				}
				else return;
				
				CommonADO ado=CommonADO.getCommonADO();
				String sql="delete from Goods where GoodsNo='"+input+"'";
				ado.executeUpdate(sql);
				//createCompositeGoodsShow();
				compositeGoodsShow.dispose();				
				createCompositeGoodsShow();
				
				compositeGoodsMange.layout(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		createCompositeGoodsShow();
	}
	/**
	 * This method initializes compositeStaffMange	
	 *
	 */
	private void createCompositeStaffMange() {
		GridData gridData2 = new GridData();
		gridData2.horizontalSpan = 14;
		gridData2.heightHint = 300;
		gridData2.widthHint = 500;
		gridData2.grabExcessHorizontalSpace = true;
		GridData gridData8 = new GridData();
		gridData8.widthHint = 100;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 18;
		compositeStaffMange = new Composite(tabFolder, SWT.NONE);
		compositeStaffMange.setLayout(gridLayout1);
		textAreaStaff = new Text(compositeStaffMange, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		textAreaStaff.setLayoutData(gridData2);
		label2 = new Label(compositeStaffMange, SWT.NONE);
		label2.setText("");
		label3 = new Label(compositeStaffMange, SWT.NONE);
		label3.setText("");
		label3.setLayoutData(gridData8);
		label4 = new Label(compositeStaffMange, SWT.NONE);
		label4.setText("");
		Label filler = new Label(compositeStaffMange, SWT.NONE);
		tableViewer.setContentProvider(new IStructuredContentProvider(){

			public Object[] getElements(Object arg0) {
				// TODO Auto-generated method stub
				RoomFactory roomFactory=(RoomFactory)arg0;
				return roomFactory.getRoomList().toArray();
				
				
				
			}

			public void dispose() {
			}

			public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
			}});
				tableViewer.setInput(new RoomFactory());
				tableViewer.setLabelProvider(new ITableLabelProvider(){

			public Image getColumnImage(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				return null;
				
			}

			public String getColumnText(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				Room room=(Room)arg0;
				CommonADO ado=CommonADO.getCommonADO();
				String roomInfoQueryStr="select * from RoomType where Type='"+room.getType()+"'";
				ResultSet rs;
				rs=ado.executeSelect(roomInfoQueryStr);
				int peopleNum=0;
				float price=0;
				try {
					if(rs.next()){
						peopleNum=rs.getInt("PeopleNums");
						price=rs.getFloat("Price");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(arg1==0)
					return room.getRoomNo();
				if(arg1==1)
					return room.getType();
				
				if(arg1==2)
					return peopleNum+"";
				if(arg1==3)
					return price+"";
				if(arg1==4)
					return room.getRemarks();
				System.out.println(room.getType()+room.getState()+room.getRoomNo());
				return null;
				
			}

			public void addListener(ILabelProviderListener arg0) {
			}

			public void dispose() {
			}

			public boolean isLabelProperty(Object arg0, String arg1) {
				// TODO Auto-generated method stub
				return false;
				
			}

			public void removeListener(ILabelProviderListener arg0) {
			}});
		buttonAddStaff = new Button(compositeStaffMange, SWT.NONE);
		buttonAddStaff.setText("新增员工");
		buttonAddStaff.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				InputDialog id1=new InputDialog(sShell,"新增员工","输入员工信息，用空格分开,例如：001 Manager Tommy f 312039","",null);
				if(id1.open()==0){
					input=id1.getValue();
					if(input.equals("")) return;
				}
				else return;
				String str[]=input.split(" ");
				CommonADO ado=CommonADO.getCommonADO();
				String sql="insert into Staff values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"','"+str[4]+"')";
				ado.executeUpdate(sql);
				showStaff();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		Label filler7 = new Label(compositeStaffMange, SWT.NONE);
		Label filler4 = new Label(compositeStaffMange, SWT.NONE);
		Label filler1 = new Label(compositeStaffMange, SWT.NONE);
		buttonDeleteStaff = new Button(compositeStaffMange, SWT.NONE);
		buttonDeleteStaff.setText("删除员工");
		buttonDeleteStaff.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				InputDialog id1=new InputDialog(sShell,"删除员工","输入员工编号","",null);
				if(id1.open()==0){
					input=id1.getValue();
					if(input.equals("")) return;
				}
				else return;
				
				CommonADO ado=CommonADO.getCommonADO();
				String sql="delete from Staff where StaffNo='"+input+"'";
				ado.executeUpdate(sql);
				showStaff();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		showStaff();
	}
	private void showStaff() {
		// TODO Auto-generated method stub
		CommonADO ado=CommonADO.getCommonADO();
		String sql="select * from Staff";
		ResultSet rs=ado.executeSelect(sql);
		String str="";
		str+="员工编号："+"   职位："+"   姓名："+"   性别: "+"  电话号码："+"\n";
		try {
			while(rs.next()){
				String no=rs.getString("StaffNo");
				String jobname=rs.getString("JobName");
				String name=rs.getString("Name");
				String sex=rs.getString("Sex");
				String phone=rs.getString("Phone");
				if(no.length()<8){
					for(int i=no.length();i<=12;i++)
						no+=" ";
				}
				if(jobname.length()<8){
					for(int i=jobname.length();i<=8;i++)
						jobname+=" ";
				}
				if(name.length()<8){
					for(int i=name.length();i<=8;i++)
						name+=" ";
				}
				if(sex.length()<8){
					for(int i=sex.length();i<=8;i++)
						sex+=" ";
				}
				if(phone.length()<12){
					for(int i=phone.length();i<=12;i++)
						phone+=" ";
				}
				str+=no+jobname+name+sex+phone+"\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textAreaStaff.setText(str);
	}
	/**
	 * This method initializes compositeUserMange	
	 *
	 */
	private void createCompositeUserMange() {
		GridData gridData3 = new GridData();
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.heightHint = 300;
		gridData3.widthHint = 400;
		gridData3.horizontalSpan = 3;
		GridData gridData11 = new GridData();
		gridData11.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData11.grabExcessHorizontalSpace = true;
		GridData gridData10 = new GridData();
		gridData10.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData10.grabExcessHorizontalSpace = true;
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		compositeUserMange = new Composite(tabFolder, SWT.NONE);
		compositeUserMange.setLayout(gridLayout2);
		textAreaUser = new Text(compositeUserMange, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		textAreaUser.setLayoutData(gridData3);
		buttonAddUser = new Button(compositeUserMange, SWT.NONE);
		buttonAddUser.setText("新增用户");
		buttonAddUser.setLayoutData(gridData10);
		buttonAddUser.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("新增用户");
				InputDialog id1=new InputDialog(sShell,"新增用户","输入用户信息，用空格分开,例如：pdl 666 管理员","",null);
				if(id1.open()==0){
					input=id1.getValue();
					if(input.equals("")) return;
				}
				else return;
				String str[]=input.split(" ");
				CommonADO ado=CommonADO.getCommonADO();
				String sql="insert into Users values('"+str[0]+"','"+str[1]+"','"+str[2]+"')";
				ado.executeUpdate(sql);
				showUser();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		buttonDeleteUser = new Button(compositeUserMange, SWT.NONE);
		buttonDeleteUser.setText("删除用户");
		buttonDeleteUser.setLayoutData(gridData11);
		buttonDeleteUser.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				InputDialog id1=new InputDialog(sShell,"删除用户","输入用户名","",null);
				if(id1.open()==0){
					input=id1.getValue();
					if(input.equals("")) return;
				}
				else return;
				
				CommonADO ado=CommonADO.getCommonADO();
				String sql="delete from Users where UserName='"+input+"'";
				ado.executeUpdate(sql);
				showUser();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		showUser();
			
		
	}
	private void showUser() {
		// TODO Auto-generated method stub
		CommonADO ado=CommonADO.getCommonADO();
		String sql="select * from Users";
		ResultSet rs=ado.executeSelect(sql);
		String str="";
		try {
			while(rs.next()){
				String user=rs.getString("UserName");
				String pass=rs.getString("Password");
				String type=rs.getString("UserType");
				if(type.equals("管理员"))
					pass="******";
				if(user.length()<12){
					for(int i=user.length();i<=12;i++)
						user+=" ";
				}
				if(pass.length()<12){
					for(int i=pass.length();i<=12;i++)
						pass+=" ";
				}
				if(type.length()<12){
					for(int i=type.length();i<=12;i++)
						type+=" ";
				}
				str+="用户名："+user;
				str+=" 密码："+pass;
				str+=" 用户类型："+type+"\n";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		textAreaUser.setText(str);
	}
	public SystemManageShell(){
		createSShell();
	}
	public Shell getsShell(){
		return sShell;
	}
	public void displayRoom(Composite compositeRoomDisplay){
		String sqlStr="select * from Goods";
		CommonADO ado=CommonADO.getCommonADO();
		ResultSet rs=ado.executeSelect(sqlStr);
		try {
			rs.last();
			int littleNum=rs.getRow();
			System.out.println(littleNum);
			rs.beforeFirst();
			rs.next();
			GoodsShow[] ktvGoods=new GoodsShow[littleNum];
			for(int i=0;i<littleNum;i++){
				GoodsEntity good=new GoodsEntity();
				good.setGoodsNo(rs.getString("GoodsNo"));
				good.setGoodsName(rs.getString("GoodsName"));
				good.setGoodsPrice(rs.getFloat("GoodsPrice"));
				ktvGoods[i]=new GoodsShow(compositeRoomDisplay,SWT.NONE);
				ktvGoods[i].setGood(good);
				ktvGoods[i].setText(good.getGoodsNo()+"\n"+good.getGoodsName()+"\n"+good.getGoodsPrice()+"\n");
				String str="";
				System.out.println(ktvGoods[i].getGood().getGoodsName());
				str+=ktvGoods[i].getGood().getGoodsName()+"\n";
				if(ktvGoods[i].getGood().getGoodsName().trim().equals("啤酒"))
					ktvGoods[i].setImage(new Image(Display.getCurrent(),
							getClass().getResourceAsStream("/images/pijiu.png")));
				else if(ktvGoods[i].getGood().getGoodsName().trim().equals("牛肉干"))
					ktvGoods[i].setImage(new Image(Display.getCurrent(),
							getClass().getResourceAsStream("/images/niurougan.png")));
				else if(ktvGoods[i].getGood().getGoodsName().trim().equals("爆米花"))
					ktvGoods[i].setImage(new Image(Display.getCurrent(),
							getClass().getResourceAsStream("/images/baomihua.jpg")));
				else if(ktvGoods[i].getGood().getGoodsName().trim().equals("葵花籽"))
					ktvGoods[i].setImage(new Image(Display.getCurrent(),
							getClass().getResourceAsStream("/images/guazi.jpg")));
				else if(ktvGoods[i].getGood().getGoodsName().trim().equals("百事可乐"))
					ktvGoods[i].setImage(new Image(Display.getCurrent(),
							getClass().getResourceAsStream("/images/kele.jpg")));
				else{
					String st=ktvGoods[i].getGood().getGoodsName().trim();
					st=st.substring(st.length()-1,st.length());
					if(st.equals("面"))
						ktvGoods[i].setImage(new Image(Display.getCurrent(),
								getClass().getResourceAsStream("/images/fangbianmian.png")));
					else if(st.equals("包"))
						ktvGoods[i].setImage(new Image(Display.getCurrent(),
								getClass().getResourceAsStream("/images/mianbao.png")));
					else if(st.equals("果"))
						ktvGoods[i].setImage(new Image(Display.getCurrent(),
								getClass().getResourceAsStream("/images/shuiguo.png")));
					else
						ktvGoods[i].setImage(new Image(Display.getCurrent(),
								getClass().getResourceAsStream("/images/qita.jpg")));
				}
					
				//textAreaGoods.setText(str);
			//	ktvGoods[i].setCursor(new org.eclipse.swt.graphics.Cursor(null,
			//			SWT.CURSOR_HAND));
				//MyMouseListener mm=new MyMouseListener();
				//ktvGoods[i].addMouseListener(mm);
				rs.next();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * This method initializes compositeGoodsShow	
	 *
	 */
	private void createCompositeGoodsShow() {
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		gridData4.horizontalSpan = 3;
		gridData4.heightHint = 380;
		gridData4.widthHint = 560;
		gridData4.grabExcessVerticalSpace = true;
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 3;
		compositeGoodsShow = new Composite(compositeGoodsMange, SWT.NONE);
		compositeGoodsShow.setLayout(gridLayout3);
		compositeGoodsShow.setLayoutData(gridData4);
		displayRoom(compositeGoodsShow);
	}
}
