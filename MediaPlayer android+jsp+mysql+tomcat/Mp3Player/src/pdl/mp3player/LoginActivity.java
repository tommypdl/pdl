package pdl.mp3player;

import pdl.mp3player.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;
//�����˺�����  ��¼����
public class LoginActivity extends Activity implements OnClickListener {
	
	private EditText ed_login_username;
	private EditText ed_login_password;
	private Button bt_login;
	private Button bt_login_regist;
	private CheckBox login_check1;
	private CheckBox login_check2;
	private CheckBox login_check3;
	private String tempresult;
	NetUtil util=new NetUtil();
	//��������sharePreference����
	//preference0  editor0�õ�½����ʵ��  ����ס�˺š��������
	//preference1  editor1�õ�¼����ʵ�֡���ס���롱�������
	//preference2  editor2�õ�½����ʵ�֡������¼���������
	//preference3  editor3�õ�½����ʵ�֡�������¼���������
	//preference4  editor4���û����˳������Ժ󣬿ͻ��������������ID�ţ�������ID�Ѿ�����
	private SharedPreferences preference0,preference1,preference2,preference3,preference4;
	private SharedPreferences.Editor editor0,editor1,editor2,editor3,editor4;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.login);
	//�ѵ�ǰ��activity��ӵ������У��Ա�����
	
	
	
	ed_login_username=(EditText) findViewById(R.id.ed_login_username);
	ed_login_password=(EditText) findViewById(R.id.ed_login_password);
	bt_login=(Button) findViewById(R.id.bt_login);
	bt_login_regist=(Button) findViewById(R.id.bt_login_regist);
	login_check1=(CheckBox) findViewById(R.id.login_check1);
	login_check2=(CheckBox) findViewById(R.id.login_check2);
	login_check3=(CheckBox) findViewById(R.id.login_check3);
	
	preference0=getSharedPreferences("account",MODE_PRIVATE);
	editor0=preference0.edit();
	
	preference1=getSharedPreferences("password",MODE_PRIVATE);
	editor1=preference1.edit();
	//�����¼
	preference2=getSharedPreferences("invisibleLogin",MODE_PRIVATE);
	editor2=preference2.edit();
	//������¼
	preference3=getSharedPreferences("muteLogin",MODE_PRIVATE);
	editor3=preference3.edit();
	
	preference4=getSharedPreferences("loginID",MODE_PRIVATE);
	editor4=preference4.edit();
	//��preference��ȡ����data�ļ����µ�DB�ļ������username����������ڣ������Ϊ��һ�ε�½������notexist
	String name=preference0.getString("username","notexist");
if(name.equals("notexist")){
	//��һ�ε�½�������κζ���
}else{
	//��������QQ����Ϊ��һ����������ʺ�
	ed_login_username.setText(name);
}
//�ж� ��ס����  �Ƿ��Ѿ���  ����� �� ��ס���룬�������Ĭ���ϴ����������
String password=preference1.getString("remPassword",null);
boolean flag1=preference1.getBoolean("judgePassword", false);
if(flag1){
	ed_login_password.setText(password);
	login_check1.setChecked(true);
}else{
	ed_login_password.setText("");
	login_check1.setChecked(false);
}
if(login_check1.isChecked()){
	String password1=preference1.getString("remPassword", null);
	ed_login_password.setText(password1);

}
//���sharepreference���ص�ֵ���棬�Ǿ��� �����¼�򹳣����򲻴�
boolean visibleState=preference2.getBoolean("invisible", false);
	login_check2.setChecked(visibleState);

	
	//Ϊ ��¼  ��ť���� ��������¼�
	bt_login.setOnClickListener(this);
	bt_login_regist.setOnClickListener(this);
	//Ϊ�ʺ� һ�� ���  �ı��ı�����¼�
	ed_login_username.addTextChangedListener(new TextWatcher() {
		
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO Auto-generated method stub
		
		}
		
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}
		
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			//����ʺ� һ�����ֳ���Ϊ0��Ҳ����û�����֣�������û���Ҫ���˺ţ���֮�Ķ������������
			if(ed_login_username.getText().toString().length()==0){
				ed_login_password.setText("");
			}
		}
	});
	
	
	//Ϊ  ��ס����  ���ü�����
	login_check1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				//������ˣ���DB�ļ���д��һ�����룬д��һ������ֵ�����´ε�¼�ж�
				editor1.putString("remPassword", ed_login_password.getText().toString().trim());
				editor1.putBoolean("judgePassword", true);
			  editor1.commit();
			}else{
				editor1.clear();
				  editor1.commit();
			}
		}
	});
	//Ϊ  �����¼  ���ü�����
	login_check2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			//�������������¼����ô��data�ļ���д��һ������ֵ��ֵΪtrue
			if(isChecked){
				editor2.putBoolean("invisible", true);
				editor2.commit();
				//����� ѡ�е�   �����½�ĶԺ���ȥ���ˣ���ô���������ֵ��Ϊ false
			}else{
				editor2.putBoolean("invisible", false);
				editor2.commit();
			}
		}
	});
	//Ϊ  ������¼  ���ü�����
	login_check3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				Toast.makeText(getApplicationContext(), "ѡ����", 0).show();
			}else{
				Toast.makeText(getApplicationContext(), "ûѡ��", 0).show();
			}
		}
	});
}
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.bt_login:
		
		//�õ� ������ڵ� �ʺ�����
		String username=ed_login_username.getText().toString().trim();
		String password=ed_login_password.getText().toString().trim();
		if(password.length()>0){
			
		}else{
			//�����¼���������ʧ
			InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		}
		//���ʺ�д��db�ļ���Ա�ʵ�֡���ס�˺š��������
		editor0.putString("username", username);
		//�ύ
		editor0.commit();
		//�����ס�����ڵ�¼��ʱ���Ѿ���ѡ�У�Ȼ���û��ܿ��ܻ��˺ţ���ô�ѵ�ǰ��������ڵ������ٴ�д��sharepreference�ļ�
		if(login_check1.isChecked()){
			editor1.putString("remPassword", ed_login_password.getText().toString().trim());
			editor1.putBoolean("judgePassword", true);
		  editor1.commit();
		}
		if("".equals(username)||"".equals(password)){
			Toast.makeText(getApplicationContext(), "�ʺŻ������벻��Ϊ��", 0).show();
		}
		else{
			LoginAsync lo=new LoginAsync(username, password);
			lo.execute();
			getnick ni=new getnick(username);
			ni.execute();
		}
/* try{
	String result=util.loginInfo(username, password);
	    //�õ�����˷��������ַ����������ж�
		System.out.println("result:"+result);
		
			//�������������errorpassword������һ����ĸ��e
		
		if(result.substring(0,1).equals("e")){
		         ed_login_password.setText("");
				//��ʾ������������
				Toast.makeText(getApplicationContext(), "�����������������", 0).show();	
				//��������������֣��ǿ�ͷ��һ�����߿�ͷ�������ֶ��ǣ�����¼�ɹ�
			}else if(cutString(result)){
				Intent intent=new Intent();
				intent.setClass(LoginActivity.this,LoginSuccessActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
				//result�ĳ��ȴ���13�������������ص��ַ����� "xx"+NotFoundUsername;xx����ID�ţ�����������ֻ�趨������100��QQ����
				} else if(result.length()>13){ 
				ed_login_username.setText("");
		         ed_login_password.setText("");
				//��ʾ������������
				Toast.makeText(getApplicationContext(), "QQ���벻���ڣ�����ע��", 0).show();	
				//�������������loginsuccess������¼�ɹ�
			}
		//��ȡ��result�����ID��
				String ID=extraID(result);
				//�ѵ�ǰ��¼��IDд���ֻ�data��Ա��û��˳������ʱ����ô�ID�ţ������������ȥ����ʾ����
				editor4.putString("currentID", ID);
				editor4.commit();
				
				boolean visibleState1=preference2.getBoolean("invisible", false);
				//��������¼���ˣ��������������"ID_2",������"ID_1"
				if(visibleState1){
					String id_invisible=ID+"_2";
					util.setOnlineState(id_invisible);
				}
				//��������½û�򹴣��Ͱ����������ߵ�¼,��������������"ID_1"
				else{
					String id_online=ID+"_1";
			        	util.setOnlineState(id_online);
				}
}catch(Exception e){
	Toast.makeText(getApplicationContext(), "����������ʧ��", 0).show();
}*/
     	
		break;
	case R.id.bt_login_regist:
		//���ע�ᣬ��ת��ע�����
		Intent intent=new Intent();
		intent.setClass(LoginActivity.this, RegistActivity.class);
		//������activity����Ϣ�����������ؼ�����ص��������
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		break;
	}
}
//���û���¼�ɹ���ʱ�򣬷������᷵������ʺŵ�ID�ţ�Ȼ��ͻ����ٷ��͵���������������Ƿ�����״̬
//������������жϷ���˷��������ǲ������֣���ID��
public static boolean isNum(String s){
	try{
		//�������ת����double�ͣ��������֣��򷵻�true
		Double.parseDouble(s);
	}
	catch(Exception e){
		//����ת����double����ʹerroepassword����NotFoundUsername֮�����ĸ�����ؼ�
		return false;
	}
	return true;
	
}
//�ָ��ַ����ķ���
public boolean cutString(String result){
	//����ӷ��������������ַ�������С�ڵ���2��ָ�ľ��Ƿ��ص�ID��С��100
	//��ط��е���࣬�����õ���isNum�����ж��ˣ����س�����2���ǿ϶��������ֿ���ֱ�ӷ�����trueҲ����
	if(result.length()<=2){
	String result1=result.substring(0, 1);
     boolean flag1=isNum(result1);
	return flag1;
	}
	//��������Ǵ��ڵ���2����ģ�Ҳ��ָ���Ǵӷ��������ص��ַ�������"errorPassword"���� "NotFoundUsername"
	//����"7NotFoundUsername"֮����ַ�����������ֻ��ȡǰ��һ���ַ������������ַ�
	else{
		//��ȡ��һ���ַ����ж����ǲ������֣�����isNum����  �����������  �����棬��������գ����ؼ�
     String result1=result.substring(0, 1);
	  boolean flag1=isNum(result1);

		//��ȡ�ڶ����ַ����ж����ǲ������֣�����isNum����  �����������  �����棬��������գ����ؼ�
	String result2=result.substring(1,2);
	boolean flag2=isNum(result2);
//����ַ�������������ʽ"14NotFoundUsername"
	if(flag1&&flag2){
		return true;
		//����ַ�������������ʽ"7NotFoundUsername"
	}else if(flag1==true&&flag2==false){
		return true;
		//����ַ�����������"NotFoundUsername"
	}else if(flag1==false){
		return false;
	}
	//���return�����淳��ȥ���ֲ��У�������û��
	return true;
	}
	
	
}
public String extraID(String result){
	if(result.length()<=2){
		return result;
	}else {
	     String s1=result.substring(1,2);
			//�����ȡ�ĵڶ���������
			if(isNum(s1)){
				//��ô��һ���϶�Ҳ�����֣�ֱ�ӽ�ȡǰ��λ�����͹�ȥ
				String s1_1=result.substring(0,2);
				return s1_1;
			
				
			}//����ֻ��ȡ��һ��
			else{
				String s2=result.substring(0, 1);
				return s2;
			}
		}
	
	
}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	if(keyCode==KeyEvent.KEYCODE_BACK){
		
	}
	return super.onKeyDown(keyCode, event);
	
}

private class LoginAsync extends AsyncTask<String, String, String>{
	private String username,password;
	public LoginAsync(String username,String password){
		this.username=username;
		this.password=password;
	}
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String result=util.loginInfo(username, password);
	    //�õ�����˷��������ַ����������ж�
		System.out.println("result:"+result);
		return result;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
//		if(result.substring(0,1).equals("e")){
//	         ed_login_password.setText("");
//			//��ʾ������������
//			Toast.makeText(getApplicationContext(), "�����������������", 0).show();	
//			//��������������֣��ǿ�ͷ��һ�����߿�ͷ�������ֶ��ǣ�����¼�ɹ�
//		}else if(cutString(result)){
			
			
			Intent intent=new Intent();
			intent.setClass(LoginActivity.this,losuccess.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			
			//result�ĳ��ȴ���13�������������ص��ַ����� "xx"+NotFoundUsername;xx����ID�ţ�����������ֻ�趨������100��QQ����
//			} else if(result.length()>13){ 
//			ed_login_username.setText("");
//	         ed_login_password.setText("");
//			//��ʾ������������
//			Toast.makeText(getApplicationContext(), "���벻���ڣ�����ע��", 0).show();	
//			//�������������loginsuccess������¼�ɹ�
//			
//		}
	}
}
private class getnick extends AsyncTask<String, String, String>{
	private String username;
	public getnick(String username){
		this.username=username;
	}
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		String nick=util.getnickname(username);
		return nick;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		AppConstant.user=result;
		System.out.println(result);
	}
}
}
