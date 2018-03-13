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
//ע�����
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
		//�õ��������ǳ�
		  String nickname=ed_regist_nickname.getText().toString().trim();
		  //�õ�����������
		  String password1=ed_regist_password.getText().toString().trim();
		  String password2=ed_regist_surepassword.getText().toString().trim();
		  //�õ�������  ���
		  String year=ed_regist_year.getText().toString().trim();
		 //�õ�  �������·�
		  String month=ed_regist_month.getText().toString().trim();
		//�õ������� ����
		  String day=ed_regist_day.getText().toString().trim();
		 //�õ������� ʡ����
		  String province=ed_regist_province.getText().toString().trim();
		  //�õ������� ������
		  String city=ed_regist_city.getText().toString().trim();
	   
		  //����ǳ�  �ǿյ�
		if("".equals(nickname)){
			Toast.makeText(getApplicationContext(), "�ǳƲ���Ϊ��", 0).show();
			//�ǳ�û�п����
		}else{
			//��������ǿյĻ���ȷ�������ǿյ�
			if("".equals(password1)||"".equals(password2)){
				Toast.makeText(getApplicationContext(), "���벻��Ϊ��", 0).show();
			}else{
				//���������������Ĳ���ͬ
				if(!password1.equals(password2)){
					Toast.makeText(getApplicationContext(), "������������벻��ͬ", 0).show();
				}else{
					//������볤������6λ
					if(password1.length()<6){
						Toast.makeText(getApplicationContext(), "��������6λ", 0).show();
					}else{
					//��� ����radiobutton��û�б�ѡ�񣬼�û��ѡ���Ա�
				     	if(!rb_regist_man.isChecked()&&!rb_regist_woman.isChecked()){
						Toast.makeText(getApplicationContext(), "��ѡ���Ա�", 0).show();
					}else{
						//������ǿյĻ����·��ǿյĻ��������ǿյ�
						if("".equals(year)||"".equals(month)||"".equals(day)){
							Toast.makeText(getApplicationContext(), "�����ղ���Ϊ�գ���",0).show();
						}else{
							//��������ղ������֣��û��п��ܵ�Ƥ������������
							if(isNum(year)==false||isNum(month)==false||isNum(day)==false){
								Toast.makeText(getApplicationContext(), "�����ձ��������֣���",0).show();
							}else{
								//�������ĳ�����С��1900����ߴ���2015�ꡣ������Ȼ������ʱ��γ��������ǷǷ���
								if(Integer.parseInt(year)>2015||Integer.parseInt(year)<1900){
									Toast.makeText(getApplicationContext(), "���������������", 0).show();
								}else{
									//�����ݲ�����λ��
									if(year.length()!=4){
										Toast.makeText(getApplicationContext(), "������淶�����", 0).show();
									}else{
									//����·ݴ���12�£���������Ϊ�·ݸ��������ܴ���12��
									if(Integer.parseInt(month)>12){
										Toast.makeText(getApplicationContext(), "�·ݱ�����1��12��", 0).show();
									}else{
										//�����������31�죬������Ȼ  һ������಻����31��
										if(Integer.parseInt(day)>31){
											Toast.makeText(getApplicationContext(), "����������31������", 0).show();
										}else{
											//���ʡ�������߳������ǿյ�
											if("".equals(province)||"".equals(city)){
												Toast.makeText(getApplicationContext(), "ʡ�ݳ��в���Ϊ��", 0).show();
											}else{
												//���ʡ�������߳����� �����֣�������Ȼ�����ܵ�
												if(isNum(province)==true||isNum(city)==true){
													Toast.makeText(getApplicationContext(), "ʡ�ݳ������Ʋ���Ϊ���� ", 0).show();
												}else{
													//��� ����  ��ť ��ѡ��
												if(rb_regist_man.isChecked()){
														String sex="man"; 
													/*	try{
													     String result=util.registInfo(password1, nickname,sex, year, month, day, province, city);
												
													     System.out.println(result);
													     if(result.equals("error")){
											    		Toast.makeText(getApplicationContext(), "������æ��������", 0).show(); 	
												     }else{
											        Intent intent=new Intent();
												        intent.putExtra("qqnumber", result);
											        	intent.setClass(RegistActivity.this, RegistSuccessActivity.class);
													        startActivity(intent);
													     }
													     }catch(Exception e){
													    	Toast.makeText(getApplicationContext(), "����������ʧ��", 0).show(); 
													     }*/
														RegistAsync re=new RegistAsync(sex, nickname, password1, year, month, day, province, city);
														re.execute();
														}
													else{
														//���Ů�԰�ť��ѡ��
														if(rb_regist_woman.isChecked()){
														       String sex="woman"; 
														   /*    try{
														       String result=util.registInfo(password1, nickname, sex, year, month, day, province, city);
														       if(result.equals("error")){
													    		Toast.makeText(getApplicationContext(), "������æ��������", 0).show(); 
														     }else{
													              Intent intent=new Intent();
														          intent.putExtra("qqnumber", result);
													        	  intent.setClass(RegistActivity.this, RegistSuccessActivity.class);
						                                           startActivity(intent);
														     } 
														     }catch(Exception e){
														    	 Toast.makeText(getApplicationContext(), "����������ʧ��", 0).show(); 
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
//�ж�������������Ƿ�������  ��������ĸ��
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
	    		Toast.makeText(getApplicationContext(), "������æ��������", 0).show(); 	
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
