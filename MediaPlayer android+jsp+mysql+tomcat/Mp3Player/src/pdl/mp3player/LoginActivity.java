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
//输入账号密码  登录界面
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
	//定义两个sharePreference对象，
	//preference0  editor0让登陆界面实现  “记住账号”这个功能
	//preference1  editor1让登录界面实现“记住密码”这个功能
	//preference2  editor2让登陆界面实现“隐身登录”这个功能
	//preference3  editor3让登陆界面实现“静音登录”这个功能
	//preference4  editor4让用户在退出程序以后，客户端向服务器发送ID号，声明此ID已经下线
	private SharedPreferences preference0,preference1,preference2,preference3,preference4;
	private SharedPreferences.Editor editor0,editor1,editor2,editor3,editor4;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.login);
	//把当前的activity添加到集合中，以便销毁
	
	
	
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
	//隐身登录
	preference2=getSharedPreferences("invisibleLogin",MODE_PRIVATE);
	editor2=preference2.edit();
	//静音登录
	preference3=getSharedPreferences("muteLogin",MODE_PRIVATE);
	editor3=preference3.edit();
	
	preference4=getSharedPreferences("loginID",MODE_PRIVATE);
	editor4=preference4.edit();
	//用preference读取存在data文件夹下的DB文件，如果username这个键不存在，即软件为第一次登陆，返回notexist
	String name=preference0.getString("username","notexist");
if(name.equals("notexist")){
	//第一次登陆，不做任何动作
}else{
	//否则，设置QQ号码为上一次输入过的帐号
	ed_login_username.setText(name);
}
//判断 记住密码  是否已经打钩  如果打钩 则 记住密码，密码框内默认上次输入的密码
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
//如果sharepreference返回的值是真，那就让 隐身登录打钩，否则不打勾
boolean visibleState=preference2.getBoolean("invisible", false);
	login_check2.setChecked(visibleState);

	
	//为 登录  按钮设置 点击监听事件
	bt_login.setOnClickListener(this);
	bt_login_regist.setOnClickListener(this);
	//为帐号 一栏 添加  文本改变监听事件
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
			//如果帐号 一栏文字长度为0，也就是没有文字，这代表用户想要换账号，随之的动作是清空密码
			if(ed_login_username.getText().toString().length()==0){
				ed_login_password.setText("");
			}
		}
	});
	
	
	//为  记住密码  设置监听器
	login_check1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				//如果打勾了，向DB文件里写入一个密码，写入一个布尔值用来下次登录判断
				editor1.putString("remPassword", ed_login_password.getText().toString().trim());
				editor1.putBoolean("judgePassword", true);
			  editor1.commit();
			}else{
				editor1.clear();
				  editor1.commit();
			}
		}
	});
	//为  隐身登录  设置监听器
	login_check2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			//如果点击了隐身登录，那么在data文件中写入一个布尔值，值为true
			if(isChecked){
				editor2.putBoolean("invisible", true);
				editor2.commit();
				//如果把 选中的   隐身登陆的对号又去掉了，那么把这个布尔值改为 false
			}else{
				editor2.putBoolean("invisible", false);
				editor2.commit();
			}
		}
	});
	//为  静音登录  设置监听器
	login_check3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				Toast.makeText(getApplicationContext(), "选择了", 0).show();
			}else{
				Toast.makeText(getApplicationContext(), "没选择", 0).show();
			}
		}
	});
}
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.bt_login:
		
		//得到 输入框内的 帐号密码
		String username=ed_login_username.getText().toString().trim();
		String password=ed_login_password.getText().toString().trim();
		if(password.length()>0){
			
		}else{
			//点击登录，软键盘消失
			InputMethodManager imm=(InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		}
		//把帐号写到db文件里，以便实现“记住账号”这个功能
		editor0.putString("username", username);
		//提交
		editor0.commit();
		//如果记住密码在登录的时候已经被选中，然而用户很可能换账号，那么把当前的密码框内的数据再次写入sharepreference文件
		if(login_check1.isChecked()){
			editor1.putString("remPassword", ed_login_password.getText().toString().trim());
			editor1.putBoolean("judgePassword", true);
		  editor1.commit();
		}
		if("".equals(username)||"".equals(password)){
			Toast.makeText(getApplicationContext(), "帐号或者密码不能为空", 0).show();
		}
		else{
			LoginAsync lo=new LoginAsync(username, password);
			lo.execute();
			getnick ni=new getnick(username);
			ni.execute();
		}
/* try{
	String result=util.loginInfo(username, password);
	    //得到服务端返回来的字符串，进行判断
		System.out.println("result:"+result);
		
			//如果返回来的是errorpassword，即第一个字母是e
		
		if(result.substring(0,1).equals("e")){
		         ed_login_password.setText("");
				//提示重新输入密码
				Toast.makeText(getApplicationContext(), "密码错误，请重新输入", 0).show();	
				//如果返回来的数字，是开头第一个或者开头两个数字都是，即登录成功
			}else if(cutString(result)){
				Intent intent=new Intent();
				intent.setClass(LoginActivity.this,LoginSuccessActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
				//result的长度大于13，即服务器返回的字符串是 "xx"+NotFoundUsername;xx代表ID号，在这里我们只设定上限是100个QQ号码
				} else if(result.length()>13){ 
				ed_login_username.setText("");
		         ed_login_password.setText("");
				//提示重新输入密码
				Toast.makeText(getApplicationContext(), "QQ号码不存在，请先注册", 0).show();	
				//如果返回来的是loginsuccess，即登录成功
			}
		//提取出result里面的ID号
				String ID=extraID(result);
				//把当前登录的ID写到手机data里，以便用户退出软件的时候调用此ID号，向服务器发过去，表示下线
				editor4.putString("currentID", ID);
				editor4.commit();
				
				boolean visibleState1=preference2.getBoolean("invisible", false);
				//如果隐身登录打勾了，则给服务器发送"ID_2",否则发送"ID_1"
				if(visibleState1){
					String id_invisible=ID+"_2";
					util.setOnlineState(id_invisible);
				}
				//如果隐身登陆没打勾，就按照正常上线登录,即给服务器发送"ID_1"
				else{
					String id_online=ID+"_1";
			        	util.setOnlineState(id_online);
				}
}catch(Exception e){
	Toast.makeText(getApplicationContext(), "服务器连接失败", 0).show();
}*/
     	
		break;
	case R.id.bt_login_regist:
		//点击注册，跳转到注册界面
		Intent intent=new Intent();
		intent.setClass(LoginActivity.this, RegistActivity.class);
		//清空这个activity的信息，这样摁返回键不会回到这个界面
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		break;
	}
}
//当用户登录成功的时候，服务器会返回这个帐号的ID号，然后客户端再发送到服务端用来设置是否在线状态
//这个方法就是判断服务端返回来的是不是数字，即ID号
public static boolean isNum(String s){
	try{
		//如果可以转换成double型，即是数字，则返回true
		Double.parseDouble(s);
	}
	catch(Exception e){
		//不能转换成double，即使erroepassword或者NotFoundUsername之类的字母，返回假
		return false;
	}
	return true;
	
}
//分割字符串的方法
public boolean cutString(String result){
	//如果从服务器返回来的字符串长度小于等于2，指的就是返回的ID号小于100
	//这地方有点多余，，不用调用isNum函数判断了，返回长度是2，那肯定就是数字咯，直接返回真true也可以
	if(result.length()<=2){
	String result1=result.substring(0, 1);
     boolean flag1=isNum(result1);
	return flag1;
	}
	//如果长度是大于等于2神马的，也就指的是从服务器返回的字符串包含"errorPassword"或者 "NotFoundUsername"
	//或者"7NotFoundUsername"之类的字符串，在这里只截取前面一个字符，或者两个字符
	else{
		//截取第一个字符，判断它是不是数字，调用isNum方法  ，如果是数字  返回真，如果是字谜，返回假
     String result1=result.substring(0, 1);
	  boolean flag1=isNum(result1);

		//截取第二个字符，判断它是不是数字，调用isNum方法  ，如果是数字  返回真，如果是字谜，返回假
	String result2=result.substring(1,2);
	boolean flag2=isNum(result2);
//如果字符串是这样的形式"14NotFoundUsername"
	if(flag1&&flag2){
		return true;
		//如果字符串是这样的形式"7NotFoundUsername"
	}else if(flag1==true&&flag2==false){
		return true;
		//如果字符串是这样的"NotFoundUsername"
	}else if(flag1==false){
		return false;
	}
	//这个return看着真烦，去掉又不行，它根本没用
	return true;
	}
	
	
}
public String extraID(String result){
	if(result.length()<=2){
		return result;
	}else {
	     String s1=result.substring(1,2);
			//如果截取的第二个是数字
			if(isNum(s1)){
				//那么第一个肯定也是数字，直接截取前两位，发送过去
				String s1_1=result.substring(0,2);
				return s1_1;
			
				
			}//否则，只截取第一个
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
	    //得到服务端返回来的字符串，进行判断
		System.out.println("result:"+result);
		return result;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
//		if(result.substring(0,1).equals("e")){
//	         ed_login_password.setText("");
//			//提示重新输入密码
//			Toast.makeText(getApplicationContext(), "密码错误，请重新输入", 0).show();	
//			//如果返回来的数字，是开头第一个或者开头两个数字都是，即登录成功
//		}else if(cutString(result)){
			
			
			Intent intent=new Intent();
			intent.setClass(LoginActivity.this,losuccess.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			
			//result的长度大于13，即服务器返回的字符串是 "xx"+NotFoundUsername;xx代表ID号，在这里我们只设定上限是100个QQ号码
//			} else if(result.length()>13){ 
//			ed_login_username.setText("");
//	         ed_login_password.setText("");
//			//提示重新输入密码
//			Toast.makeText(getApplicationContext(), "号码不存在，请先注册", 0).show();	
//			//如果返回来的是loginsuccess，即登录成功
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
