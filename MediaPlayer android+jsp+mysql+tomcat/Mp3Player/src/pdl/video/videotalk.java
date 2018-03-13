package pdl.video;

import java.text.SimpleDateFormat;
import java.util.Date;

import pdl.mp3player.AppConstant;
import pdl.mp3player.NetUtil;
import pdl.mp3player.PlayerActivity;
import pdl.mp3player.R;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class videotalk extends Activity{
	private TextView videotalk;
	private TextView vname;
	String videoname;
	Dialog videoDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videotalk);
		videotalk=(TextView) findViewById(R.id.videotalk);
		vname=(TextView) findViewById(R.id.newvideotalk);
		videoname=getIntent().getStringExtra("videoname");
		vname.setText(videoname+"的最新评论");
		findvideotalk fv=new findvideotalk();
		fv.execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0,1,0,"评论");
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
					EditText editText = (EditText) videoDialog.findViewById(R.id.txtFolder);
					String text=editText.getText().toString();
					SimpleDateFormat dateformat=new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
					Date    curDate    =   new    Date(System.currentTimeMillis());
					String time=dateformat.format(curDate);
					
					String nickname=AppConstant.user;
					addvideotalk at=new addvideotalk(nickname, text, videoname, time);
					at.execute();
					videoDialog.dismiss();
				}
			});
			view.findViewById(R.id.btnCancel).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					videoDialog.cancel();
				}
			});
			builder.setTitle("添加评论");
			builder.setView(view);
			videoDialog=builder.create();
			videoDialog.show();
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	private class findvideotalk extends AsyncTask<String, String, String>{		
		
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			NetUtil net=new NetUtil();
			String name=videoname;
			name="'"+name+"'";
			System.out.println(name);
			String result=net.queryTalk(name);
			return result;
			
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result!=null&&!result.equals(""))
				videotalk.setText(result.toString());
			else
				videotalk.setText("暂无评论");
		}
	}
	
	private class addvideotalk extends AsyncTask<String, String, String>{
		private String nickname,text,name,time;
		public addvideotalk(String nickname,String text,String name,String time){
			this.nickname=nickname;
			this.text=text;
			this.name=name;
			this.time=time;
		}
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			NetUtil net=new NetUtil();
			String result=net.addtalk(nickname, text, name, time);
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(videotalk.this, result, 1).show();
			findvideotalk ft=new findvideotalk();
			ft.execute();
		}
	}
}
