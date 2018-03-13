package com.example.timecontrol;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocalService extends Service{
	boolean flag=false;
	AudioManager audio;
	public static final int RINGER_MODE_SILENT = 0; 
	public static final int RINGER_MODE_VIBRATE = 1; 
	public static final int RINGER_MODE_NORMAL = 2; 
	public boolean isStop=false;
	private static final String TAG = "MyService";  
    private final IBinder myBinder = new LocalBinder();  
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		Log.e(TAG, "onBind()");  
        Toast.makeText(this, "onBind()", Toast.LENGTH_SHORT).show();  
        return myBinder;  
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		 Log.e(TAG, "onCreate()");  
	        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show();  
	       
	        new Thread(){
	        	public void run() {
	        		while(!isStop){
	        		System.out.println("thread is running");
	        		setSilence();
	        		if(flag){
	        			
	    	        }
	        		try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		}
	        	};
	        
	        }.start();
	       
	        
	}
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		 Log.e(TAG, "onStart()");  
	        Toast.makeText(this, "onStart()", Toast.LENGTH_SHORT).show();  
	       
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		 Log.e(TAG, "onDestroy()");  
	        Toast.makeText(this, "onDestroy()", Toast.LENGTH_SHORT).show(); 
	        isStop=true;
	        audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL); 
	        audio.setStreamVolume(AudioManager.RINGER_MODE_NORMAL,5, 0);
	}
	
	public void setSilence(){
		
		SQLiteDatabase db=SQLiteDatabase.openOrCreateDatabase("/data/data/com.example.timecontrol/databases/database.db", null);
		
		Cursor c=db.query("diary", new String[]{ "title", "body"}, null, null, null, null, null);
		c.moveToFirst();
		String sb="";
		while(c.moveToNext()){
			//System.out.println(c.getString(0));
			String ss=c.getString(0);
			System.out.println(ss);
			sb+=ss;
			sb+=" ";
		}
		c.moveToLast();
		String sse=c.getString(0);
		System.out.println(sse);
		sb+=sse;
		
		
		System.out.println(sb);
		String[] timelist=sb.split(" ");
		
		Calendar cal = Calendar.getInstance();// 当前日期
        int hour = cal.get(Calendar.HOUR_OF_DAY);// 获取小时
        int minute = cal.get(Calendar.MINUTE);// 获取分钟
        int minuteOfDay = hour * 60 + minute;// 从0:00分开是到目前为止的分钟数
        for(int i=0;i<timelist.length;i++){
        	String ss=timelist[i];
        	System.out.println(ss);
        	int fh=Integer.parseInt(ss.substring(0, 2));
        	int fm=Integer.parseInt(ss.substring(3,5));
        	int lh=Integer.parseInt(ss.substring(6, 8));
        	int lm=Integer.parseInt(ss.substring(9, 11));
        	System.out.println(fh+":"+fm+"-"+lh+":"+lm);
         int start = fh * 60+fm;// 起始时间 14:00的分钟数
         int end = lh * 60 + lm;// 结束时间 17:15的分钟数
        if (minuteOfDay >= start && minuteOfDay <= end) {
            System.out.println("outside of time");
            flag=false;
            audio = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE); 
            audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);  
            break;
        }
        else{
        	flag=true;
        	audio = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE); 
        	audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL); 
	        audio.setStreamVolume(AudioManager.RINGER_MODE_NORMAL,6, 0);
        }
        }
	}
	
	public class LocalBinder extends Binder {  
        LocalService getService() {  
            return LocalService.this;  
        }  
    }  
}
