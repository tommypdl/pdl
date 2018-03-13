package pdl.mp3player;

import pdl.mp3player.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
//注册成功界面
public class RegistSuccessActivity extends Activity implements OnClickListener {
	
	private TextView tv_registsuccess_number;
	private Button bt1_registsuccess_login;
@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.registsuccess);
	
	tv_registsuccess_number=(TextView) findViewById(R.id.tv_registsuccess_number);
	bt1_registsuccess_login=(Button) findViewById(R.id.bt1_registsuccess_login);
	bt1_registsuccess_login.setOnClickListener(this);
	Intent intent=getIntent();
	String number=intent.getStringExtra("qqnumber");
	tv_registsuccess_number.setText(number);
}
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch(v.getId()){
	case R.id.bt1_registsuccess_login:
		Intent intent=new Intent();
		intent.setClass(RegistSuccessActivity.this,LoginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
		this.finish();
		break;
	}
}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
	// TODO Auto-generated method stub
	if(keyCode==KeyEvent.KEYCODE_BACK){
		Intent intent=new Intent(this,LoginActivity.class);
		startActivity(intent);
		this.finish();
	}
	return super.onKeyDown(keyCode, event);
}
}
