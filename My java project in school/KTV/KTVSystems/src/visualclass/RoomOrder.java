package visualclass;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;

import dataclass.CommonADO;

public class RoomOrder {

	private Shell sShell = null;  //  @jve:decl-index=0:visual-constraint="10,10"
	private Text textArea = null;

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData.verticalAlignment = org.eclipse.swt.layout.GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		sShell = new Shell();
		sShell.setText("开房记录");
		sShell.setSize(new Point(748, 202));
		sShell.setLayout(new GridLayout());
		textArea = new Text(sShell, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL);
		textArea.setLayoutData(gridData);
		init();
	}
	public void init(){
		CommonADO ado=CommonADO.getCommonADO();
		String sql="select * from RoomOrder";
		ResultSet rs=ado.executeSelect(sql);
		StringBuffer sb=new StringBuffer();
		try {
			while(rs.next()){
				sb.append("房间号:"+rs.getString("RoomNo"));
				sb.append(" 开始时间："+rs.getString("StartTime"));
				sb.append(" 预定时间："+rs.getString("ExpectTime"));
				sb.append(" 结束时间："+rs.getString("EndTime"));
				sb.append(" 押金："+rs.getString("PrePayMoney"));
				sb.append(" 付款："+rs.getString("PayMoney"));
				sb.append("\n");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str=new String(sb);
		textArea.setText(str);
	}
	public RoomOrder(){
		createSShell();
	}
	public Shell getsShell(){
		return sShell;
	}
}
