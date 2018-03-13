package visualclass;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import java.lang.Object;
import org.eclipse.jface.viewers.Viewer;

import dataclass.CommonADO;
import dataclass.ConsumeEntity;
import dataclass.ConsumeFactory;
import dataclass.GoodsEntity;
import dataclass.GoodsFactory;
import org.eclipse.jface.viewers.ITableLabelProvider;
import java.lang.String;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.widgets.TableColumn;

public class ConsumeShell {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Composite composite = null;
	private Composite composite1 = null;
	private Composite composite2 = null;
	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Label label3 = null;
	private Label label4 = null;
	private Table goodsTable = null;
	private TableViewer tableViewer = null;
	private Label label5 = null;
	public static Label labelRoom = null;
	
	private Table ConsumeTable = null;
	private Group group = null;
	private Button buttonAdd = null;
	private Label label6 = null;
	private Button buttonDelete = null;
	private TableViewer tableViewer1 = null;
	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		sShell = new Shell();
		sShell.setText("Shell");
		sShell.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
		sShell.setLayout(gridLayout);
		sShell.setSize(new Point(512, 420));
		createComposite();
		createComposite1();
		createComposite2();
	}

	/**
	 * This method initializes composite	
	 *
	 */
	private void createComposite() {
		GridData gridData31 = new GridData();
		gridData31.widthHint = 50;
		GridData gridData21 = new GridData();
		gridData21.widthHint = 100;
		GridData gridData11 = new GridData();
		gridData11.widthHint = 200;
		gridData11.heightHint = 60;
		GridData gridData3 = new GridData();
		gridData3.heightHint = 60;
		gridData3.horizontalAlignment = org.eclipse.swt.layout.GridData.CENTER;
		gridData3.grabExcessHorizontalSpace = true;
		gridData3.grabExcessVerticalSpace = true;
		gridData3.widthHint = 56;
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 4;
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.widthHint = 500;
		gridData.horizontalSpan = 2;
		gridData.verticalSpan = 3;
		gridData.heightHint = 80;
		composite = new Composite(sShell, SWT.NONE);
		composite.setLayoutData(gridData);
		composite.setLayout(gridLayout1);
		label = new Label(composite, SWT.NONE);
		label.setText("");
		label.setLayoutData(gridData21);
		label1 = new Label(composite, SWT.NONE);
		label1.setImage(new Image(Display.getCurrent(), getClass().getResourceAsStream("/images/add.png")));
		label1.setLayoutData(gridData3);
		label1.setText("");
		label2 = new Label(composite, SWT.NONE);
		label2.setText("增加消费");
		label2.setFont(new Font(Display.getDefault(), "楷体", 24, SWT.NORMAL));
		label2.setLayoutData(gridData11);
		label3 = new Label(composite, SWT.NONE);
		label3.setText("");
		label3.setLayoutData(gridData31);
	}

	/**
	 * This method initializes composite1	
	 *
	 */
	private void createComposite1() {
		GridData gridData5 = new GridData();
		gridData5.grabExcessHorizontalSpace = true;
		gridData5.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData5.grabExcessVerticalSpace = true;
		GridData gridData4 = new GridData();
		gridData4.grabExcessHorizontalSpace = true;
		GridData gridData1 = new GridData();
		gridData1.grabExcessVerticalSpace = true;
		gridData1.widthHint = 240;
		gridData1.heightHint = 340;
		composite1 = new Composite(sShell, SWT.NONE);
		composite1.setLayout(new GridLayout());
		composite1.setLayoutData(gridData1);
		label4 = new Label(composite1, SWT.NONE);
		label4.setText("商品清单");
		label4.setLayoutData(gridData4);
		goodsTable = new Table(composite1, SWT.NONE);
		goodsTable.setHeaderVisible(true);
		goodsTable.setLayoutData(gridData5);
		goodsTable.setLinesVisible(true);
		TableColumn tableColumn = new TableColumn(goodsTable, SWT.NONE);
		tableColumn.setWidth(60);
		tableColumn.setText("商品编号");
		TableColumn tableColumn1 = new TableColumn(goodsTable, SWT.NONE);
		tableColumn1.setWidth(108);
		tableColumn1.setText("商品名");
		TableColumn tableColumn2 = new TableColumn(goodsTable, SWT.NONE);
		tableColumn2.setWidth(60);
		tableColumn2.setText("商品单价");
		tableViewer = new TableViewer(goodsTable);
		
		tableViewer.setContentProvider(new IStructuredContentProvider(){

	public Object[] getElements(Object arg0) {
		// TODO Auto-generated method stub
		GoodsFactory goodsFactory=(GoodsFactory)arg0;
		return goodsFactory.getGoodsList().toArray();
		
		
	}

	public void dispose() {
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	}});
		tableViewer.setInput(new GoodsFactory());
		tableViewer.setLabelProvider(new ITableLabelProvider(){

	public Image getColumnImage(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public String getColumnText(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		GoodsEntity goods=(GoodsEntity)arg0;
		if(arg1==0)
			return goods.getGoodsNo();
		if(arg1==1)
			return goods.getGoodsName();
		if(arg1==2)
			return goods.getGoodsPrice()+"";
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
		
	}

	/**
	 * This method initializes composite2	
	 *
	 */
	private void createComposite2() {
		GridData gridData7 = new GridData();
		gridData7.grabExcessHorizontalSpace = true;
		gridData7.widthHint = 200;
		gridData7.heightHint = 180;
		gridData7.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		GridData gridData6 = new GridData();
		gridData6.grabExcessHorizontalSpace = true;
		GridData gridData2 = new GridData();
		gridData2.grabExcessVerticalSpace = true;
		gridData2.widthHint = 240;
		gridData2.heightHint = 340;
		composite2 = new Composite(sShell, SWT.NONE);
		composite2.setLayout(new GridLayout());
		composite2.setLayoutData(gridData2);
		label5 = new Label(composite2, SWT.NONE);
		label5.setText("消费清单");
		label5.setLayoutData(gridData6);
		labelRoom = new Label(composite2, SWT.NONE);
		labelRoom.setText(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo()+"包房消费清单");
		ConsumeTable = new Table(composite2, SWT.NONE);
		ConsumeTable.setHeaderVisible(true);
		ConsumeTable.setLayoutData(gridData7);
		ConsumeTable.setLinesVisible(true);
		TableColumn tableColumn3 = new TableColumn(ConsumeTable, SWT.NONE);
		tableColumn3.setWidth(100);
		tableColumn3.setText("商品名");
		TableColumn tableColumn4 = new TableColumn(ConsumeTable, SWT.NONE);
		tableColumn4.setWidth(60);
		tableColumn4.setText("数量");
		TableColumn tableColumn5 = new TableColumn(ConsumeTable, SWT.NONE);
		tableColumn5.setWidth(60);
		tableColumn5.setText("小计");
		tableViewer1 = new TableViewer(ConsumeTable);
		tableViewer1.setContentProvider(new IStructuredContentProvider(){

	public Object[] getElements(Object arg0) {
		// TODO Auto-generated method stub
		ConsumeFactory consumeFactory=(ConsumeFactory)arg0;
		return consumeFactory.getConsuemList().toArray();
		
	}

	public void dispose() {
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
	}});
		tableViewer1.setInput(new ConsumeFactory(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo()));
		tableViewer1.setLabelProvider(new ITableLabelProvider(){

	public Image getColumnImage(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
		
	}

	public String getColumnText(Object arg0, int arg1) {
		// TODO Auto-generated method stub
		ConsumeEntity consumeEntity=(ConsumeEntity)arg0;
		if(arg1==0)
			return consumeEntity.getGoodsName();
		if(arg1==1)
			return consumeEntity.getConsumeNum()+"";
		if(arg1==2)
			return consumeEntity.getGoodsTotalPrice()+"";
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
		createGroup();
	}
	
	/**
	 * This method initializes group	
	 *
	 */
	private void createGroup() {
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;
		GridData gridData8 = new GridData();
		gridData8.grabExcessHorizontalSpace = true;
		gridData8.widthHint = 240;
		gridData8.heightHint = 30;
		gridData8.grabExcessVerticalSpace = true;
		group = new Group(composite2, SWT.NONE);
		group.setLayoutData(gridData8);
		group.setLayout(gridLayout2);
		buttonAdd = new Button(group, SWT.NONE);
		buttonAdd.setText("增加消费");
		buttonAdd.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
			public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
				int selectedIndex=goodsTable.getSelectionIndex();
				if(selectedIndex<0){
					MessageDialog.openInformation(sShell, "提示", "请选择要消费的商品");
					return;
				}
				GoodsEntity goods=(GoodsEntity)goodsTable.getItem(selectedIndex).getData();
				ConsumeEntity consumeEntity=new ConsumeEntity();
				consumeEntity.setRoomNo(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo());
				consumeEntity.setGoodsName(goods.getGoodsName());
				InputDialog input=new InputDialog(sShell,"商品数量输入窗口","请输入商品数量","",null);
				int consumeNum=0;
				if(input.open()==0)
					consumeNum=Integer.parseInt(input.getValue());
				consumeEntity.setConsumeNum(consumeNum);
				consumeEntity.setGoodsTotalPrice(goods.getGoodsPrice()*consumeNum);
				consumeEntity.setPayState("未结账");
				if(ConsumeFactory.InsertDB(consumeEntity))
					tableViewer1.add(consumeEntity);
				else
					MessageDialog.openInformation(sShell, "提示", "未实现商品消费");
			}
		});
		label6 = new Label(group, SWT.NONE);
		label6.setText("          ");
		buttonDelete = new Button(group, SWT.NONE);
		buttonDelete.setText("删除消费");
		buttonDelete
				.addSelectionListener(new org.eclipse.swt.events.SelectionAdapter() {
					public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
						int deleteIndex=ConsumeTable.getSelectionIndex();
						ConsumeEntity deleteConsume=(ConsumeEntity)ConsumeTable.getItem(deleteIndex).getData();
						int no=deleteConsume.getGoodsOrderNo();
						String name=deleteConsume.getGoodsName();
						String roomno=deleteConsume.getRoomNo();
						System.out.println(no+"goodno"+ " "+name+" "+ " "+roomno);
						CommonADO ado=CommonADO.getCommonADO();
						String delSql="delete from GoodsOrder where GoodsName='"+name+"' and RoomNo='"+roomno+"'";
						ado.executeUpdate(delSql);
						//if(ConsumeFactory.deleteDB(deleteConsume))
							tableViewer1.remove(deleteConsume);
							//tableViewer1.setInput(new ConsumeFactory(SystemMainShell.lastSelectedRoom.getRoom().getRoomNo()));
						//ConsumeFactory.deleteDB(deleteConsume);
						//tableViewer1.remove(deleteConsume);
						//else
						//	MessageDialog.openInformation(sShell, "提示", "删除消费商品未成功");
						
					}
				});
	}
	public ConsumeShell(){
		super();
		createSShell();
	}
	public Shell getsShell(){
		return sShell;
	}
}
