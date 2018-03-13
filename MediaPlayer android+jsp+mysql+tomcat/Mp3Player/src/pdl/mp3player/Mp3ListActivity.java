package pdl.mp3player;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import pdl.download.HttpDownloader;
import pdl.model.Mp3Info;
import pdl.service.DownloadService;
import pdl.xml.Mp3ListContentHandler;

import pdl.mp3player.R;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Mp3ListActivity extends ListActivity {
	private static final int UPDATE = 1;// ���ڱ�ʶ���²˵���
	private static final int ABOUT = 2;// ���ڱ�ʶ���ڲ˵���
	List<Mp3Info> mp3Infos;
	
	private String srcPath = "/sdcard/mp3/a1.mp3";
	private String actionUrl = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.remote_mp3_list);
		updateListView();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, UPDATE, 1, R.string.mp3list_update);
		menu.add(0, ABOUT, 2, R.string.mp3list_about);
		menu.add(0,1,0,"�˳�");
		menu.add(0,66,0,"�ϴ�");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == UPDATE) {
			// �û�����˸����б�˵���
			updateListView();

		} else if (item.getItemId() == ABOUT) {
			// �û�����˹��ڲ˵���
		}else if(item.getItemId()==66){
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_GET_CONTENT);
			intent.setType("*/*");
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(intent, 1100);
		}else if(item.getItemId()==1){
			android.os.Process.killProcess(android.os.Process.myPid());
			System.exit(0);
		}

		return super.onMenuItemSelected(featureId, item);
	}

	
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);
			
			Mp3Info mp3Info = mp3Infos.get(position);
			Toast.makeText(this, mp3Info.getMp3Name()+"��������", 1).show();
			Intent intent = new Intent();
			//��Mp3Info������뵽Intent������
			intent.putExtra("mp3Info", mp3Info);
			
			intent.setClass(this, DownloadService.class);
			//����Service
			startService(intent);
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
					uploadtask up=new uploadtask();
					up.execute();
					
				}
				break;
			}
		}
		
	private String downloadXML(String urlStr) {
		HttpDownloader httpDownloader = new HttpDownloader();
		String result = httpDownloader.download(urlStr);
		return result;
	}

	private List<Mp3Info> parse(String xmlStr) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		List<Mp3Info> infos = new ArrayList<Mp3Info>();
		try {
			XMLReader xmlReader = saxParserFactory.newSAXParser()
					.getXMLReader();
			Mp3ListContentHandler mp3ListContentHandler = new Mp3ListContentHandler(
					infos);
			xmlReader.setContentHandler(mp3ListContentHandler);
			xmlReader.parse(new InputSource(new StringReader(xmlStr)));
			for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
				Mp3Info mp3Info = (Mp3Info) iterator.next();
				//System.out.println(mp3Info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infos;
	}

	
	private SimpleAdapter buildSimpleAdapter(List<Mp3Info> mp3Infos){
		//����һ��   List���󣬲�����  SimpleAdapter�ı�׼����  mp3Infos���е��������
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		for (Iterator iterator = mp3Infos.iterator(); iterator.hasNext();) {

		 
		 
		Mp3Info mp3Info = (Mp3Info) iterator.next();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("mp3_name", mp3Info.getMp3Name());
		map.put("mp3_size", mp3Info.getMp3Size());
		list.add(map);
		}
		//����һ��   SimpleAdapter����
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
		R.layout.mp3info_item, new String[] { "mp3_name", "mp3_size" },
		new int[] { R.id.mp3_name, R.id.mp3_size });
		//�����   SimpleAdapter�������õ�  ListActivity����
		return simpleAdapter;
		}
	private void updateListView() {
		ConnectNet con=new ConnectNet();
		con.execute();
		
		}

	 private class ConnectNet extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			//�û�����˸����б�ť
			//���ذ�������   Mp3������Ϣ��  xml�ļ�
			String xml = downloadXML(AppConstant.IpAddress+":8080/mp3/resource.xml");
			//System.out.println(AppConstant.ip+":8080/mp3/resource.xml");
			//��   xml�ļ����н��������������Ľ�����õ�  Mp3Info�����У������Щ
			//System.out.println("xml:"+xml);
			 mp3Infos = parse(xml);
			
			return xml;
		}
		 @Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			SimpleAdapter simpleAdapter = buildSimpleAdapter(mp3Infos);
			setListAdapter(simpleAdapter);
			//System.out.println("success "+result);
		}
	 }
	 
	 private class uploadtask extends AsyncTask<String, String, String>{

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
			Toast.makeText(Mp3ListActivity.this, "upload success", 1).show();
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
	      setTitle(e.getMessage());
	    }

	  }
}
