package pdl.mp3player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import pdl.model.Mp3Info;
import pdl.service.PlayerService;
import pdl.utils.FileUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import pdl.mp3player.R;

public class PlayerActivity extends Activity{
	List<Mp3Info> mp3Infos;
	
	private ImageButton beginButton;
	private ImageButton pauseButton;
	private ImageButton stopButton;
	
	private ImageButton pre;
	private ImageButton danqu;
	private ImageButton next;
	
	private TextView lrcText;
	private Mp3Info mp3Info;
	private IntentFilter intentFilter;
	private BroadcastReceiver receiver;
	
	private TextView talk;
	private TextView showtalk;
	private Dialog talkDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		Intent intent= getIntent();
		mp3Info=(Mp3Info) intent.getSerializableExtra("mp3Info");
		beginButton=(ImageButton) findViewById(R.id.begin);
		pauseButton=(ImageButton) findViewById(R.id.pause);
		stopButton=(ImageButton) findViewById(R.id.stop);
		talk=(TextView) findViewById(R.id.talk);
		showtalk=(TextView) findViewById(R.id.showtalk);
		
		pre=(ImageButton) findViewById(R.id.imageButtonpre);
		danqu=(ImageButton) findViewById(R.id.imageButtondanqu);
		next=(ImageButton) findViewById(R.id.imageButtonnext);
		
		lrcText=(TextView) findViewById(R.id.lrcText);
		OnClickListener listener=new Listener();
		beginButton.setOnClickListener(listener);
		pauseButton.setOnClickListener(listener);
		stopButton.setOnClickListener(listener);
		pre.setOnClickListener(listener);
		danqu.setOnClickListener(listener);
		next.setOnClickListener(listener);
		
		findtalk ft=new findtalk();
		ft.execute();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		receiver=new LrcMessageBroadcastReceiver();
		registerReceiver(receiver, getIntentFilter());
		FileUtils fileUtils=new FileUtils();
		mp3Infos=fileUtils.getMp3Files("mp3/");
		System.out.println("mp3infos size:"+mp3Infos.size());
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		unregisterReceiver(receiver);
	}
	class Listener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent=new Intent();
			intent.setClass(PlayerActivity.this, PlayerService.class);
			ImageButton button=(ImageButton)v;
			switch (button.getId()) {
			case R.id.begin:
				System.out.println("begin play");
			intent.putExtra("mp3Info", mp3Info);
			intent.putExtra("MSG", AppConstant.PLAY_MSG);
			
			startService(intent);
			break;
			case R.id.pause:
			intent.putExtra("MSG", AppConstant.PAUSE_MSG);
			startService(intent);
			break;
			case R.id.stop:
			intent.putExtra("MSG", AppConstant.STOP_MSG);
			startService(intent);
			break;
			case R.id.imageButtonpre:
				System.out.println("pre");
				intent.putExtra("MSG", AppConstant.STOP_MSG);
				startService(intent);
				int i=0;
				for(;i<mp3Infos.size();i++){
					if(mp3Info.getMp3Name().equals(mp3Infos.get(i).getMp3Name()))
						break;
				}
				if(i>0){
					i--;
					mp3Info=mp3Infos.get(i);
					System.out.println(mp3Infos.get(i).getMp3Name());
					intent.putExtra("mp3Info", mp3Infos.get(i));
					intent.putExtra("MSG", AppConstant.PLAY_MSG);
				
					startService(intent);
					findtalk ft=new findtalk();
					ft.execute();
				}else{
					Toast.makeText(PlayerActivity.this, "已经是第一首", 1).show();
				}
				break;
			case R.id.imageButtondanqu:
				System.out.println("danqu");
				intent.putExtra("MSG", AppConstant.STOP_MSG);
				startService(intent);
				
				intent.putExtra("mp3Info", mp3Info);
				intent.putExtra("MSG", AppConstant.PLAY_MSG);
				
				startService(intent);
				break;
			case R.id.imageButtonnext:
				System.out.println("next");
				intent.putExtra("MSG", AppConstant.STOP_MSG);
				startService(intent);
				int j=0;
				for(;j<mp3Infos.size();j++){
					if(mp3Info.getMp3Name().equals(mp3Infos.get(j).getMp3Name()))
						break;
				}
				if(j<mp3Infos.size()-1){
					j++;
					mp3Info=mp3Infos.get(j);
					System.out.println(mp3Infos.get(j).getMp3Name());
					intent.putExtra("mp3Info", mp3Infos.get(j));
					intent.putExtra("MSG", AppConstant.PLAY_MSG);
				
					startService(intent);
					findtalk ft=new findtalk();
					ft.execute();
				}else{
					Toast.makeText(PlayerActivity.this, "已经是最后一首", 1).show();
				}
				break;
			default:
			break;
			}
		}
		
		
		}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0,66,0,"评论");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case 66:
		/*	final EditText txtName=new EditText(context);
			
			talkDialog=new AlertDialog.Builder(context)
			.setTitle("添加评论")
			.setView(txtName)
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String text=txtName.getText().toString();
					SimpleDateFormat dateformat=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
					Date    curDate    =   new    Date(System.currentTimeMillis());
					String time=dateformat.format(curDate);
					String musicname=mp3Info.getMp3Name();
					String nickname=AppConstant.user;
					addtalk at=new addtalk(nickname, text, musicname, time);
					at.execute();
				}
			})
			.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					talkDialog.cancel();
				}
			}).create();
			talkDialog.show();*/
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			LayoutInflater inflater = this.getLayoutInflater();
			View view=inflater.inflate(R.layout.prompt_folder_name, null);
			view.findViewById(R.id.btnEnter).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					EditText editText = (EditText) talkDialog.findViewById(R.id.txtFolder);
					String text=editText.getText().toString();
					SimpleDateFormat dateformat=new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
					Date    curDate    =   new    Date(System.currentTimeMillis());
					String time=dateformat.format(curDate);
					String musicname=mp3Info.getMp3Name();
					String nickname=AppConstant.user;
					addtalk at=new addtalk(nickname, text, musicname, time);
					at.execute();
					talkDialog.dismiss();
				}
			});
			view.findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					talkDialog.cancel();
				}
			});
			builder.setTitle("添加评论");
			builder.setView(view);
			talkDialog=builder.create();
			talkDialog.show();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	class LrcMessageBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String lrcMessageString=intent.getStringExtra("lrcMessage");
			lrcText.setText(lrcMessageString);
		}
		
	}
	
	private IntentFilter getIntentFilter() {
		if (intentFilter==null) {
		intentFilter=new IntentFilter();
		intentFilter.addAction(AppConstant.LRC_MESSAGE_ACTION);
		}
		return intentFilter;
		}
	
	private class findtalk extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			NetUtil net=new NetUtil();
			String musicname=mp3Info.getMp3Name();
			musicname="'"+musicname+"'";
			System.out.println(musicname);
			String result=net.queryTalk(musicname);
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			showtalk.setText(result);
		}
	}
	private class addtalk extends AsyncTask<String, String, String>{
		private String nickname,text,musicname,time;
		public addtalk(String nickname,String text,String musicname,String time){
			this.nickname=nickname;
			this.text=text;
			this.musicname=musicname;
			this.time=time;
		}
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			NetUtil net=new NetUtil();
			String result=net.addtalk(nickname, text, musicname, time);
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(PlayerActivity.this, result, 1).show();
			findtalk ft=new findtalk();
			ft.execute();
		}
	}
}
