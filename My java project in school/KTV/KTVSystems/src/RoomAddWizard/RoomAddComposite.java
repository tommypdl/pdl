package RoomAddWizard;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;

public class RoomAddComposite extends Composite {

	private Label label = null;
	private Label label1 = null;
	private Label label2 = null;
	private Text textRoomNo = null;
	private Label label3 = null;
	private Label label4 = null;
	private Label label5 = null;
	private Combo comboRoomType = null;
	private Text textPeopleNums = null;
	private Text textAreaRemarks = null;
	private Label label6 = null;
	

	public Text getTextRoomNo() {
		return textRoomNo;
	}

	public void setTextRoomNo(Text textRoomNo) {
		this.textRoomNo = textRoomNo;
	}

	public Combo getComboRoomType() {
		return comboRoomType;
	}

	public void setComboRoomType(Combo comboRoomType) {
		this.comboRoomType = comboRoomType;
	}

	public Text getTextPeopleNums() {
		return textPeopleNums;
	}

	public void setTextPeopleNums(Text textPeopleNums) {
		this.textPeopleNums = textPeopleNums;
	}

	public Text getTextAreaRemarks() {
		return textAreaRemarks;
	}

	public void setTextAreaRemarks(Text textAreaRemarks) {
		this.textAreaRemarks = textAreaRemarks;
	}

	public RoomAddComposite(Composite parent, int style) {
		super(parent, style);
		initialize();
	}

	private void initialize() {
		GridData gridData1 = new GridData();
		gridData1.grabExcessHorizontalSpace = true;
		gridData1.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData1.grabExcessVerticalSpace = true;
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 5;
		label = new Label(this, SWT.NONE);
		label.setText("          ");
		Label filler = new Label(this, SWT.NONE);
		Label filler2 = new Label(this, SWT.NONE);
		Label filler3 = new Label(this, SWT.NONE);
		label6 = new Label(this, SWT.NONE);
		label6.setText("           ");
		Label filler1 = new Label(this, SWT.NONE);
		label1 = new Label(this, SWT.NONE);
		label1.setText("包房编号");
		label2 = new Label(this, SWT.NONE);
		label2.setText("");
		textRoomNo = new Text(this, SWT.BORDER);
		Label filler12 = new Label(this, SWT.NONE);
		Label filler4 = new Label(this, SWT.NONE);
		label3 = new Label(this, SWT.NONE);
		label3.setText("包房类型");
		Label filler5 = new Label(this, SWT.NONE);
		createComboRoomType();
		Label filler11 = new Label(this, SWT.NONE);
		Label filler7 = new Label(this, SWT.NONE);
		label4 = new Label(this, SWT.NONE);
		label4.setText("容纳人数");
		Label filler8 = new Label(this, SWT.NONE);
		textPeopleNums = new Text(this, SWT.BORDER);
		Label filler9 = new Label(this, SWT.NONE);
		Label filler10 = new Label(this, SWT.NONE);
		label5 = new Label(this, SWT.NONE);
		label5.setText("房间评价");
		label5.setLayoutData(gridData);
		Label filler6 = new Label(this, SWT.NONE);
		textAreaRemarks = new Text(this, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		textAreaRemarks.setLayoutData(gridData1);
		this.setLayout(gridLayout);
		this.setToolTipText("添加新包房");
		setSize(new Point(300, 200));
	}

	/**
	 * This method initializes comboRoomType	
	 *
	 */
	private void createComboRoomType() {
		comboRoomType = new Combo(this, SWT.NONE);
	}

}
