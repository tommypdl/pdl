package com.example.timecontrol;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button startBtn;  
    private Button stopBtn;  
    private Button bindBtn;  
    private Button unBindBtn;  
    private Button addtime;
    private static final String TAG = "MainActivity";  
    private LocalService myService;  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn=(Button) findViewById(R.id.button1);
        stopBtn=(Button) findViewById(R.id.button2);
        bindBtn=(Button) findViewById(R.id.button3);
        unBindBtn=(Button) findViewById(R.id.button4);
        MyOnClickListener c=new MyOnClickListener();
        startBtn.setOnClickListener(c);
        stopBtn.setOnClickListener(c);
        bindBtn.setOnClickListener(c);
        unBindBtn.setOnClickListener(c);
        addtime=(Button) findViewById(R.id.button5);
        addtime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(MainActivity.this, DiaryMainActivity.class);
				startActivity(intent);
			}
		});
    }

class MyOnClickListener implements OnClickListener{

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		intent.setClass(MainActivity.this, LocalService.class);
		switch(v.getId()){
		 case R.id.button1:  
             // ����Service  
             startService(intent);  
             break;  
         case R.id.button2:  
             // ֹͣService  
             stopService(intent);  
             break;  
         case R.id.button3:  
             // ��Service  
             bindService(intent, conn, Service.BIND_AUTO_CREATE);  
             break;  
         case R.id.button4:  
             // ���Service  
             unbindService(conn);  
             break;  
		}
	}
	
}
  
private ServiceConnection conn = new ServiceConnection() {  
    @Override  
    public void onServiceConnected(ComponentName name, IBinder service) {  
        Log.e(TAG, "���ӳɹ�");  
        // ��Service���ӽ����ɹ����ṩ���ͻ�����Service�����Ķ��󣨸���Android Doc����ģ���֪��׼ȷ�񡣡�������  
        myService = ((LocalService.LocalBinder) service).getService();  
    }  

    @Override  
    public void onServiceDisconnected(ComponentName name) {  
        Log.e(TAG, "�Ͽ�����");  
        myService = null;  
    }  
};  

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
