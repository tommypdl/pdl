import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLClassLoader;

import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class test extends JFrame implements ActionListener{
	String ip = "127.0.0.1";//连接到服务端的ip地址
	int port = 8882;//连接到服务端的端口号
	String userName = "pdl";//用户名
	int type = 0;//0表示未连接，1表示已连接
	
	Image icon;
	Socket socket;
	ObjectOutputStream output;//网络套接字输出流
	ObjectInputStream input;//网络套接字输入流
	
	ClientReceive recvThread;
	
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JLabel jLabel = null;
	private JTextField ipAddress = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField textPort = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JTextField username = null;
	private JLabel jLabel5 = null;
	private JButton loginButton = null;
	private JButton logoffButton = null;
	private JLabel jLabel6 = null;
	private JButton exitButton = null;
	private JLabel jLabel7 = null;
	private JTextArea messageShow = null;
	private JPanel jPanel = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel12 = null;
	private JLabel jLabel13 = null;
	private JLabel jLabel14 = null;
	private JLabel jLabel15 = null;
	private JComboBox combobox = null;
	private JLabel jLabel11 = null;
	private JComboBox actionlist = null;
	private JLabel jLabel16 = null;
	private JCheckBox checkbox = null;
	private JLabel jLabel17 = null;
	private JTextArea clientMessage = null;
	private JButton clientMessageButton = null;
	private JTextField showStatus = null;
	/**
	 * This is the default constructor
	 */
	public test() {
		super();
		initialize();
		icon = getImage("icon.png");
		this.setIconImage(icon); //设置程序图标
		show();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(574, 367);
		this.setContentPane(getJContentPane());
		this.setTitle("多用户聊天系统-客户端");
		
		checkbox = new JCheckBox("悄悄话");
		checkbox.setSelected(false);

		//actionlist = new JComboBox();
		actionlist.addItem("微笑地");
		actionlist.addItem("高兴地");
		actionlist.addItem("轻轻地");
		actionlist.addItem("生气地");
		actionlist.addItem("小心地");
		actionlist.addItem("静静地");
		actionlist.setSelectedIndex(0);
		
		loginButton.addActionListener(this);
		logoffButton.addActionListener(this);
		
		exitButton.addActionListener(this);
		
		//combobox = new JComboBox();
		combobox.insertItemAt("所有人",0);
		combobox.setSelectedIndex(0);
		clientMessageButton.addActionListener(this);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						if(type == 1){
							DisConnect();
						}
						System.exit(0);
					}
				}
			);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getJToolBar(), BorderLayout.NORTH);
			jContentPane.add(getMessageShow(), BorderLayout.CENTER);
			jContentPane.add(getJPanel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJToolBar() {
		if (jToolBar == null) {
			jLabel7 = new JLabel();
			jLabel7.setText("         ");
			jLabel6 = new JLabel();
			jLabel6.setText("  ");
			jLabel5 = new JLabel();
			jLabel5.setText("  ");
			jLabel4 = new JLabel();
			jLabel4.setText("用户名");
			jLabel3 = new JLabel();
			jLabel3.setText("  ");
			jLabel2 = new JLabel();
			jLabel2.setText("端口");
			jLabel1 = new JLabel();
			jLabel1.setText("  ");
			jLabel = new JLabel();
			jLabel.setText("服务器IP");
			jToolBar = new JToolBar();
			jToolBar.add(jLabel);
			jToolBar.add(getIpAddress());
			jToolBar.add(jLabel1);
			jToolBar.add(jLabel2);
			jToolBar.add(getTextPort());
			jToolBar.add(jLabel3);
			jToolBar.add(jLabel4);
			jToolBar.add(getUsername());
			jToolBar.add(jLabel5);
			jToolBar.add(getLoginButton());
			jToolBar.add(getLogoffButton());
			jToolBar.add(jLabel6);
			jToolBar.add(getExitButton());
			jToolBar.add(jLabel7);
		}
		return jToolBar;
	}

	/**
	 * This method initializes ipAddress	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIpAddress() {
		if (ipAddress == null) {
			ipAddress = new JTextField();
			ipAddress.setText("127.0.0.1");
		}
		return ipAddress;
	}

	/**
	 * This method initializes textPort	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextPort() {
		if (textPort == null) {
			textPort = new JTextField();
			textPort.setText("8882");
		}
		return textPort;
	}

	/**
	 * This method initializes username	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getUsername() {
		if (username == null) {
			username = new JTextField();
			username.setSize(new Dimension(80, 30));
		}
		return username;
	}

	/**
	 * This method initializes loginButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLoginButton() {
		if (loginButton == null) {
			loginButton = new JButton();
			loginButton.setText("登录");
		}
		return loginButton;
	}

	/**
	 * This method initializes logoffButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLogoffButton() {
		if (logoffButton == null) {
			logoffButton = new JButton();
			logoffButton.setText("注销");
		}
		return logoffButton;
	}

	/**
	 * This method initializes exitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setText("退出");
		}
		return exitButton;
	}

	/**
	 * This method initializes messageShow	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getMessageShow() {
		if (messageShow == null) {
			messageShow = new JTextArea();
		}
		return messageShow;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints41.gridy = 3;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.gridwidth = 1;
			gridBagConstraints41.weighty = 2.0D;
			gridBagConstraints41.gridx = 2;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 5;
			gridBagConstraints31.gridy = 2;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.BOTH;
			gridBagConstraints21.gridy = 2;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.weighty = 1.0;
			gridBagConstraints21.gridwidth = 3;
			gridBagConstraints21.gridx = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 2;
			jLabel17 = new JLabel();
			jLabel17.setText("发送消息");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 5;
			gridBagConstraints9.gridy = 0;
			jLabel16 = new JLabel();
			jLabel16.setText(" ");
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints8.gridy = 1;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.gridx = 4;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 3;
			gridBagConstraints7.gridy = 1;
			jLabel11 = new JLabel();
			jLabel11.setText("表情");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridx = 2;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 1;
			jLabel15 = new JLabel();
			jLabel15.setText("发送至");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 6;
			gridBagConstraints5.gridy = 0;
			jLabel14 = new JLabel();
			jLabel14.setText(" ");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 4;
			gridBagConstraints4.gridy = 0;
			jLabel13 = new JLabel();
			jLabel13.setText(" ");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.gridy = 0;
			jLabel12 = new JLabel();
			jLabel12.setText(" ");
			jLabel8 = new JLabel();
			jLabel8.setText(" ");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel8, new GridBagConstraints());
			jPanel.add(jLabel12, gridBagConstraints3);
			jPanel.add(jLabel13, gridBagConstraints4);
			jPanel.add(jLabel14, gridBagConstraints5);
			jPanel.add(jLabel15, gridBagConstraints6);
			jPanel.add(getCombobox(), gridBagConstraints);
			jPanel.add(jLabel11, gridBagConstraints7);
			jPanel.add(getActionlist(), gridBagConstraints8);
			jPanel.add(jLabel16, gridBagConstraints9);
			jPanel.add(jLabel17, gridBagConstraints11);
			jPanel.add(getClientMessage(), gridBagConstraints21);
			jPanel.add(getClientMessageButton(), gridBagConstraints31);
			jPanel.add(getShowStatus(), gridBagConstraints41);
		}
		return jPanel;
	}

	/**
	 * This method initializes combobox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCombobox() {
		if (combobox == null) {
			combobox = new JComboBox();
		}
		return combobox;
	}

	/**
	 * This method initializes actionlist	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getActionlist() {
		if (actionlist == null) {
			actionlist = new JComboBox();
		}
		return actionlist;
	}

	/**
	 * This method initializes clientMessage	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getClientMessage() {
		if (clientMessage == null) {
			clientMessage = new JTextArea();
		}
		return clientMessage;
	}

	/**
	 * This method initializes clientMessageButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getClientMessageButton() {
		if (clientMessageButton == null) {
			clientMessageButton = new JButton();
			clientMessageButton.setText("发送");
		}
		return clientMessageButton;
	}

	/**
	 * This method initializes showStatus	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getShowStatus() {
		if (showStatus == null) {
			showStatus = new JTextField();
			showStatus.setPreferredSize(new Dimension(100, 22));
		}
		return showStatus;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
Object obj = e.getSource();
		
		
		
		 if ( obj == loginButton) {
			ip=ipAddress.getText().trim();
			port=Integer.parseInt(textPort.getText().trim());
			userName=username.getText().trim();
			Connect();
		}
		else if ( obj == logoffButton) { //注销
			DisConnect();
			showStatus.setText("");
		}
		else if ( obj == clientMessageButton) { //发送消息
			SendMessage();
			clientMessage.setText("");
		}
		else if (obj == exitButton ) { //退出
			
				if(type == 1){
					DisConnect();
				}
				System.exit(0);
			
		}
		
	}
	public void Connect(){
		try{
			socket = new Socket(ip,port);
		}
		catch (Exception e){
		e.printStackTrace();
		}

		try{
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input  = new ObjectInputStream(socket.getInputStream() );
			
			output.writeObject(userName);
			output.flush();
			
			recvThread = new ClientReceive(socket,output,input,combobox,messageShow,showStatus);
			recvThread.start();
			
			loginButton.setEnabled(false);
			
			logoffButton.setEnabled(true);
			
			clientMessage.setEnabled(true);
			messageShow.append("连接服务器 "+ip+":"+port+" 成功...\n");
			type = 1;//标志位设为已连接
		}
		catch (Exception e){
			System.out.println(e);
			return;
		}
	}
	
	public void DisConnect(){
		loginButton.setEnabled(true);
		
		logoffButton.setEnabled(false);
		
		clientMessage.setEnabled(false);
		
		if(socket.isClosed()){
			return ;
		}
		
		try{
			output.writeObject("用户下线");
			output.flush();
		
			input.close();
			output.close();
			socket.close();
			messageShow.append("已经与服务器断开连接...\n");
			type = 0;//标志位设为未连接
		}
		catch (Exception e){
			//
		}
	}
	
	public void SendMessage(){
		String toSomebody = combobox.getSelectedItem().toString();
		String status  = "";
		
		
		String action = actionlist.getSelectedItem().toString();
		String message = clientMessage.getText();
		
		if(socket.isClosed()){
			return ;
		}
		
		try{
			output.writeObject("聊天信息");
			output.flush();
			output.writeObject(toSomebody);
			output.flush();
			output.writeObject(status);
			output.flush();
			output.writeObject(action);
			output.flush();
			output.writeObject(message);
			output.flush();
		}
		catch (Exception e){
			//
		}
	}

	/**
	 * 通过给定的文件名获得图像
	 */
	Image getImage(String filename) {
		URLClassLoader urlLoader = (URLClassLoader)this.getClass().
			getClassLoader();
		URL url = null;
		Image image = null;
		url = urlLoader.findResource(filename);
		image = Toolkit.getDefaultToolkit().getImage(url);
		MediaTracker mediatracker = new MediaTracker(this);
		try {
			mediatracker.addImage(image, 0);
			mediatracker.waitForID(0);
		}
		catch (InterruptedException _ex) {
			image = null;
		}
		if (mediatracker.isErrorID(0)) {
			image = null;
		}

		return image;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
