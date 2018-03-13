package pdl.mp3player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import pdl.video.videoList;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class losuccess extends Activity{
	private TextView showuser;
	private Button music;
	private Button video;
	private String srcPath = "/sdcard/mp3/a1.mp3";
	private String actionUrl = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.losuccess);
		showuser=(TextView) findViewById(R.id.showuser);
		music=(Button) findViewById(R.id.choicemusic);
		video=(Button) findViewById(R.id.choicevideo);
		
		showuser.setText("Hello,"+AppConstant.user);
		music.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(losuccess.this,MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
		video.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(losuccess.this,videoList.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0,66,0,"ÉÏ´«");
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if(item.getItemId()==66){
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setType("*/*");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(intent, 1100);
		}
		return super.onMenuItemSelected(featureId, item);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch(requestCode){
		case 1100:
			if(resultCode==Activity.RESULT_OK){
				Uri uri=data.getData();
				String path=uri.toString();
				path=Uri.decode(path);
				path=path.substring(path.indexOf("/storage"));
				System.out.println("filepath:"+path);
				srcPath=path;
				actionUrl=AppConstant.IpAddress+":8080/MediaServer/UploadServlet";
				uptask up=new uptask();
				up.execute();
				
			}
			break;
		}
	}
	
	private class uptask extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			uploadFile();
			return "success";
		}
		 @Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(losuccess.this, "upload success", 1).show();
		}
	 }
	private void uploadFile()
	  {
		 System.out.println("begin up");
	    String uploadUrl = actionUrl;
	    String end = "\r\n";
	    String twoHyphens = "--";
	    String boundary = "******";
	    try
	    {
	      URL url = new URL(uploadUrl);
	      HttpURLConnection httpURLConnection = (HttpURLConnection) url
	          .openConnection();
	      httpURLConnection.setDoInput(true);
	      httpURLConnection.setDoOutput(true);
	      httpURLConnection.setUseCaches(false);
	      httpURLConnection.setRequestMethod("POST");
	      httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
	      httpURLConnection.setRequestProperty("Charset", "UTF-8");
	      httpURLConnection.setRequestProperty("Content-Type",
	          "multipart/form-data;boundary=" + boundary);

	      DataOutputStream dos = new DataOutputStream(httpURLConnection
	          .getOutputStream());
	      dos.writeBytes(twoHyphens + boundary + end);
	      dos
	          .writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\""
	              + srcPath.substring(srcPath.lastIndexOf("/") + 1)
	              + "\"" + end);
	      dos.writeBytes(end);

	      FileInputStream fis = new FileInputStream(srcPath);
	      byte[] buffer = new byte[1024]; // 8k
	      int count = 0;
	      while ((count = fis.read(buffer)) != -1)
	      {
	        dos.write(buffer, 0, count);

	      }
	      fis.close();

	      dos.writeBytes(end);
	      dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
	      dos.flush();
	      dos.close();
	      
	      InputStream is = httpURLConnection.getInputStream();
	      System.out.println("mid up");
	      InputStreamReader isr = new InputStreamReader(is, "utf-8");
	      BufferedReader br = new BufferedReader(isr);
	      String result = br.readLine();
	     
	      Toast.makeText(this, result, Toast.LENGTH_LONG).show();
	      
	      is.close();
	      System.out.println("end up");
	    } catch (Exception e)
	    {
	      e.printStackTrace();
	      
	    }

	  }
}
