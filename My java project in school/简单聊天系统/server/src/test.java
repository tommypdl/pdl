import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;

import javax.swing.JComboBox;
import java.awt.Dimension;

public class test extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jToolBar = null;
	private JButton portSet = null;
	private JTextField textPort = null;
	private JButton jButton1 = null;
	private JButton startServer = null;
	private JButton stopServer = null;
	private JButton exitButton = null;
	private JTextArea messageShow = null;
	public static int port = 8882;
	ServerListen listenThread;
	ServerSocket serverSocket;
	UserLinkList userLinkList;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel7 = null;
	private JComboBox combobox = null;
	private JLabel jLabel2 = null;
	private JTextArea sysMessage = null;
	private JButton sysMessageButton = null;
	private JTextField showStatus = null;
	/**
	 * This is the default constructor
	 */
	public test() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(500, 400);
		this.setContentPane(getJContentPane());
		this.setTitle("���û�����ϵͳ-��������");
		startServer.addActionListener(this);
		stopServer.addActionListener(this);
		exitButton.addActionListener(this);
		
		combobox.insertItemAt("������",0);
		
		combobox.setSelectedIndex(0);
		
		
		sysMessageButton.addActionListener(this);
		this.addWindowListener(
				new WindowAdapter(){
					public void windowClosing(WindowEvent e){
						stopService();
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
			jContentPane.add(getJPanel2(), BorderLayout.SOUTH);
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
			jToolBar = new JToolBar();
			jToolBar.add(getPortSet());
			jToolBar.add(getTextPort());
			jToolBar.add(getJButton1());
			jToolBar.add(getStartServer());
			jToolBar.add(getStopServer());
			jToolBar.add(getExitButton());
		}
		return jToolBar;
	}

	/**
	 * This method initializes portSet	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPortSet() {
		if (portSet == null) {
			portSet = new JButton();
			portSet.setText("�˿�");
		}
		return portSet;
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
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
		}
		return jButton1;
	}

	/**
	 * This method initializes startServer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStartServer() {
		if (startServer == null) {
			startServer = new JButton();
			startServer.setText("����");
		}
		return startServer;
	}

	/**
	 * This method initializes stopServer	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getStopServer() {
		if (stopServer == null) {
			stopServer = new JButton();
			stopServer.setText("ֹͣ");
		}
		return stopServer;
	}

	/**
	 * This method initializes exitButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getExitButton() {
		if (exitButton == null) {
			exitButton = new JButton();
			exitButton.setText("�˳�");
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
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.weightx = 1.0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.weightx = 1.0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			jPanel.add(getCombobox(), gridBagConstraints1);
			jPanel.add(getSysMessage(), gridBagConstraints2);
			jPanel.add(getSysMessageButton(), new GridBagConstraints());
			jPanel.add(getShowStatus(), gridBagConstraints11);
		}
		return jPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj == startServer ) { //���������
			startService();
		}
		else if (obj == stopServer ) { //ֹͣ�����
			
				stopService();
			}
		
		
		else if (obj == exitButton) { //�˳�����
			
				stopService();
				System.exit(0);
			}
		
		
		else if (obj == sysMessageButton ) { //����ϵͳ��Ϣ
			sendSystemMessage();
		}
	}
	public void startService(){
		port=Integer.parseInt(textPort.getText().trim());
		try{
			serverSocket = new ServerSocket(port,10);
			messageShow.append("������Ѿ���������"+port+"�˿�����...\n");
			
			startServer.setEnabled(false);
		
			//portSet.setEnabled(false);
			

			stopServer .setEnabled(true);
		
			sysMessage.setEnabled(true);
		}
		catch (Exception e){
			//System.out.println(e);
		}
		userLinkList = new UserLinkList();
		
		listenThread = new ServerListen(serverSocket,combobox,
			messageShow,showStatus,userLinkList);
		listenThread.start();
	}
	
	/**
	 * �رշ����
	 */
	public void stopService(){
		try{
			//�������˷��ͷ������رյ���Ϣ
			sendStopToAll();
			listenThread.isStop = true;
			serverSocket.close();
			
			int count = userLinkList.getCount();
			
			int i =0;
			while( i < count){
				Node node = userLinkList.findUser(i);
				
				node.input .close();
				node.output.close();
				node.socket.close();
				
				i ++;
			}

			stopServer .setEnabled(false);
			
			startServer.setEnabled(true);
			
			portSet.setEnabled(true);
			
			sysMessage.setEnabled(false);

			messageShow.append("������Ѿ��ر�\n");

			combobox.removeAllItems();
			combobox.addItem("������");
		}
		catch(Exception e){
			//System.out.println(e);
		}
	}
	
	/**
	 * �������˷��ͷ������رյ���Ϣ
	 */
	public void sendStopToAll(){
		int count = userLinkList.getCount();
		
		int i = 0;
		while(i < count){
			Node node = userLinkList.findUser(i);
			if(node == null) {
				i ++;
				continue;
			}
			
			try{
				node.output.writeObject("����ر�");
				node.output.flush();
			}
			catch (Exception e){
				//System.out.println("$$$"+e);
			}
			
			i++;
		}
	}
	
	/**
	 * �������˷�����Ϣ
	 */
	public void sendMsgToAll(String msg){
		int count = userLinkList.getCount();//�û�����
		
		int i = 0;
		while(i < count){
			Node node = userLinkList.findUser(i);
			if(node == null) {
				i ++;
				continue;
			}
			
			try{
				node.output.writeObject("ϵͳ��Ϣ");
				node.output.flush();
				node.output.writeObject(msg);
				node.output.flush();
			}
			catch (Exception e){
				//System.out.println("@@@"+e);
			}
			
			i++;
		}

		sysMessage.setText("");
	}

	/**
	 * ��ͻ����û�������Ϣ
	 */
	public void sendSystemMessage(){
		String toSomebody = combobox.getSelectedItem().toString();
		String message = sysMessage.getText() + "\n";
		
		messageShow.append(message);
		
		//�������˷�����Ϣ
		if(toSomebody.equalsIgnoreCase("������")){
			sendMsgToAll(message);
		}
		else{
			//��ĳ���û�������Ϣ
			Node node = userLinkList.findUser(toSomebody);
			
			try{
				node.output.writeObject("ϵͳ��Ϣ");
				node.output.flush();
				node.output.writeObject(message);
				node.output.flush();
			}
			catch(Exception e){
				//System.out.println("!!!"+e);
			}
			sysMessage.setText("");//��������Ϣ������Ϣ���
		}
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel == null) {
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.gridx = 2;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 3;
			gridBagConstraints12.gridy = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.weighty = 1.0;
			gridBagConstraints10.gridx = 2;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 2;
			gridBagConstraints9.gridy = 0;
			jLabel2 = new JLabel();
			jLabel2.setText(" ");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 3;
			gridBagConstraints8.gridy = 0;
			jLabel7 = new JLabel();
			jLabel7.setText(" ");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.gridy = 1;
			jLabel5 = new JLabel();
			jLabel5.setText("������");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.gridy = 4;
			jLabel4 = new JLabel();
			jLabel4.setText(" ");
			jLabel1 = new JLabel();
			jLabel1.setText(" ");
			jLabel = new JLabel();
			jLabel.setText(" ");
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
			jPanel.add(jLabel, new GridBagConstraints());
			jPanel.add(jLabel1, new GridBagConstraints());
			jPanel.add(jLabel4, gridBagConstraints5);
			jPanel.add(jLabel5, gridBagConstraints6);
			jPanel.add(jLabel7, gridBagConstraints8);
			jPanel.add(getCombobox(), gridBagConstraints3);
			jPanel.add(jLabel2, gridBagConstraints9);
			jPanel.add(getSysMessage(), gridBagConstraints10);
			jPanel.add(getSysMessageButton(), gridBagConstraints12);
			jPanel.add(getShowStatus(), gridBagConstraints7);
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
	 * This method initializes sysMessage	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getSysMessage() {
		if (sysMessage == null) {
			sysMessage = new JTextArea();
		}
		return sysMessage;
	}

	/**
	 * This method initializes sysMessageButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSysMessageButton() {
		if (sysMessageButton == null) {
			sysMessageButton = new JButton();
			sysMessageButton.setText("����");
		}
		return sysMessageButton;
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
}
