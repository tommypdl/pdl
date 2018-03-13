package pdl.mp3player;

import java.text.SimpleDateFormat;
import java.util.Date;

import pdl.mp3player.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class First extends Activity implements OnClickListener {
	//刚开始的splash界面
	
    /** Called when the activity is first created. */
	private Button bt_login;
	private Button bt_regist;
	private Dialog ipDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);
        
        
        bt_login=(Button) findViewById(R.id.bt_login);
        bt_regist=(Button) findViewById(R.id.bt_regist);
        bt_login.setOnClickListener(this);
        bt_regist.setOnClickListener(this);
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bt_login:
			Intent intent0=new Intent(First.this,LoginActivity.class);
			intent0.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent0);
			break;
       case R.id.bt_regist:
    	   Intent intent1=new Intent(First.this,RegistActivity.class);
    	   intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent1);
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 0, "设置IP");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==1){
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			LayoutInflater inflater = this.getLayoutInflater();
			View view=inflater.inflate(R.layout.prompt_folder_name, null);
			view.findViewById(R.id.btnEnter).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					EditText editText = (EditText) ipDialog.findViewById(R.id.txtFolder);
					String text=editText.getText().toString();
					AppConstant.IpAddress="http://"+text;
					System.out.println(AppConstant.IpAddress);
					SharedPreferences setting=getSharedPreferences("setting", Context.MODE_PRIVATE);
					Editor editor=setting.edit();
					editor.putString("url",AppConstant.IpAddress);
					editor.commit();
					Toast.makeText(First.this, "保存成功!", Toast.LENGTH_SHORT).show();

					ipDialog.dismiss();
				}
			});
			view.findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					ipDialog.cancel();
				}
			});
			builder.setTitle("设置IP");
			builder.setView(view);
			ipDialog=builder.create();
			ipDialog.show();
		}
		return super.onMenuItemSelected(featureId, item);
	}
}