package pdl.mp3player;

import pdl.mp3player.R;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
//注册界面
public class RegistActivity extends Activity implements OnClickListener {
	
	private EditText ed_regist_nickname;
	private EditText ed_regist_password;
	private EditText ed_regist_surepassword;
    private EditText ed_regist_year;
	private EditText ed_regist_month;
	private EditText ed_regist_day;
	private EditText ed_regist_province;
	private EditText ed_regist_city;
	private RadioButton rb_regist_man;
	private RadioButton rb_regist_woman;
	private Button bt_sure_regist;
	NetUtil util=new NetUtil();
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.regist);
	
	ed_regist_nickname=(EditText) findViewById(R.id.ed_regist_nickname);
	ed_regist_password=(EditText) findViewById(R.id.ed_regist_password);
	ed_regist_surepassword=(EditText) findViewById(R.id.ed_regist_surepassword);
	ed_regist_year=(EditText) findViewById(R.id.ed_regist_year);
    ed_regist_month=(EditText) findViewById(R.id.ed_regist_month);
    ed_regist_day=(EditText) findViewById(R.id.ed_regist_day);
    ed_regist_province=(EditText) findViewById(R.id.ed_regist_province);
    ed_regist_city=(EditText) findViewById(R.id.ed_regist_city);
    rb_regist_man=(RadioButton) findViewById(R.id.rb_regist_man);
    rb_regist_woman=(RadioButton) findViewById(R.id.rb_regist_woman);

	bt_sure_regist=(Button) findViewById(R.id.bt_sure_regist);

	bt_sure_regist.setOnClickListener(this);
	rb_regist_man.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				rb_regist_woman.setChecked(false);
			}
		}
	});
	rb_regist_woman.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if(isChecked){
				rb_regist_man.setChecked(false);
			}
		}
	});
}
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.bt_sure_regist:
		//得到输入框的昵称
		  String nickname=ed_regist_nickname.getText().toString().trim();
		  //得到输入框的密码
		  String password1=ed_regist_password.getText().toString().trim();
		  String password2=ed_regist_surepassword.getText().toString().trim();
		  //得到输入框的  年份
		  String year=ed_regist_year.getText().toString().trim();
		 //得到  输入框的月份
		  String month=ed_regist_month.getText().toString().trim();
		//得到输入框的 天数
		  String day=ed_regist_day.getText().toString().trim();
		 //得到输入框的 省份名
		  String province=ed_regist_province.getText().toString().trim();
		  //得到输入框的 城市名
		  String city=ed_regist_city.getText().toString().trim();
	   
		  //如果昵称  是空的
		if("".equals(nickname)){
			Toast.makeText(getApplicationContext(), "昵称不能为空", 0).show();
			//昵称没有空情况
		}else{
			//如果密码是空的或者确认密码是空的
			if("".equals(password1)||"".equals(password2)){
				Toast.makeText(getApplicationContext(), "密码不能为空", 0).show();
			}else{
				//如果两次密码输入的不相同
				if(!password1.equals(password2)){
					Toast.makeText(getApplicationContext(), "两次输入的密码不相同", 0).show();
				}else{
					//如果密码长度少于6位
					if(password1.length()<6){
						Toast.makeText(getApplicationContext(), "密码至少6位", 0).show();
					}else{
					//如果 两个radiobutton都没有被选择，即没有选择性别
				     	if(!rb_regist_man.isChecked()&&!rb_regist_woman.isChecked()){
						Toast.makeText(getApplicationContext(), "请选择性别", 0).show();
					}else{
						//如果年是空的或者月份是空的或者天数是空的
						if("".equals(year)||"".equals(month)||"".equals(day)){
							Toast.makeText(getApplicationContext(), "年月日不能为空！！",0).show();
						}else{
							//如果年月日不是数字，用户有可能调皮的输入了数字
							if(isNum(year)==false||isNum(month)==false||isNum(day)==false){
								Toast.makeText(getApplicationContext(), "年月日必须是数字！！",0).show();
							}else{
								//如果输入的出生年小于1900年或者大于2015年。，很显然这两个时间段出生的人是非法的
								if(Integer.parseInt(year)>2015||Integer.parseInt(year)<1900){
									Toast.makeText(getApplicationContext(), "请输入正常的年份", 0).show();
								}else{
									//如果年份不是四位数
									if(year.length()!=4){
										Toast.makeText(getApplicationContext(), "请输入规范的年份", 0).show();
									}else{
									//如果月份大于12月，，，，因为月份根本不可能大于12月
									if(Integer.parseInt(month)>12){
										Toast.makeText(getApplicationContext(), "月份必须是1到12月", 0).show();
									}else{
										//如果天数大于31天，，很显然  一个月最多不超过31天
										if(Integer.parseInt(day)>31){
											Toast.makeText(getApplicationContext(), "天数必须是31号以内", 0).show();
										}else{
											//如果省份名或者城市名是空的
											if("".equals(province)||"".equals(city)){
												Toast.makeText(getApplicationContext(), "省份城市不能为空", 0).show();
											}else{
												//如果省份名或者城市名 是数字，，很显然不可能的
												if(isNum(province)==true||isNum(city)==true){
													Toast.makeText(getApplicationContext(), "省份城市名称不能为数字 ", 0).show();
												}else{
													//如果 男性  按钮 被选择，
												if(rb_regist_man.isChecked()){
														String sex="man"; 
													/*	try{
													     String result=util.registInfo(password1, nickname,sex, year, month, day, province, city);
												
													     System.out.println(result);
													     if(result.equals("error")){
											    		Toast.makeText(getApplicationContext(), "服务器忙，请重试", 0).show(); 	
												     }else{
											        Intent intent=new Intent();
												        intent.putExtra("qqnumber", result);
											        	intent.setClass(RegistActivity.this, RegistSuccessActivity.class);
													        startActivity(intent);
													     }
													     }catch(Exception e){
													    	Toast.makeText(getApplicationContext(), "服务器连接失败", 0).show(); 
													     }*/
														RegistAsync re=new RegistAsync(sex, nickname, password1, year, month, day, province, city);
														re.execute();
														}
													else{
														//如果女性按钮被选择
														if(rb_regist_woman.isChecked()){
														       String sex="woman"; 
														   /*    try{
														       String result=util.registInfo(password1, nickname, sex, year, month, day, province, city);
														       if(result.equals("error")){
													    		Toast.makeText(getApplicationContext(), "服务器忙，请重试", 0).show(); 
														     }else{
													              Intent intent=new Intent();
														          intent.putExtra("qqnumber", result);
													        	  intent.setClass(RegistActivity.this, RegistSuccessActivity.class);
						                                           startActivity(intent);
														     } 
														     }catch(Exception e){
														    	 Toast.makeText(getApplicationContext(), "服务器连接失败", 0).show(); 
														     }*/
														       RegistAsync re=new RegistAsync(sex, nickname, password1, year, month, day, province, city);
																re.execute();
															}
													}
														
														
													
													
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	     
	}
}	
		
		break;
	
	}
}
//判断输入的年月日是否是数字  而不是字母，
public static boolean isNum(String s){
	try{
		Double.parseDouble(s);
		return true;
	}catch(Exception e){
		return false;
	}
	
}

private class RegistAsync extends AsyncTask<String, String, String>{
	
	private String nickname,password1,year,month,day,province,city,sex;
	public RegistAsync(String sex,String nickname,String password1,String year,String month,String day,String province,String city){
		this.city=city;
		this.day=day;
		this.nickname=nickname;
		this.password1=password1;
		this.year=year;
		this.month=month;
		this.province=province;
		this.sex=sex;
	}
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		 String result=util.registInfo(password1, nickname,sex, year, month, day, province, city);
			
	     System.out.println(result);
		return result;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		 if(result.equals("error")){
	    		Toast.makeText(getApplicationContext(), "服务器忙，请重试", 0).show(); 	
		     }else{
	        Intent intent=new Intent();
		        intent.putExtra("qqnumber", result);
	        	intent.setClass(RegistActivity.this, RegistSuccessActivity.class);
			        startActivity(intent);
			        RegistActivity.this.finish();
			     }
	}
}
}
