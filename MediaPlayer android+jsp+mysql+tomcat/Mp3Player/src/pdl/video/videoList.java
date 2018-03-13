package pdl.video;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import pdl.download.HttpDownloader;
import pdl.model.videoInfo;
import pdl.model.videoInfo;
import pdl.mp3player.AppConstant;
import pdl.mp3player.LoginActivity;
import pdl.mp3player.R;
import pdl.mp3player.losuccess;
import pdl.xml.Mp3ListContentHandler;
import pdl.xml.videoListContentHandler;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class videoList extends Activity{
	private ListView videolist;
	List<videoInfo> vInfos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videolist);
		videolist=(ListView) findViewById(R.id.videolist);
		videolist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				videoInfo vi=vInfos.get(arg2);
				Intent intent=new Intent();
				intent.putExtra("videoname", vi.getVideoName());
				intent.setClass(videoList.this,VideoActivity.class);
				
				startActivity(intent);
			}
		});
		
		videolist.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				videoInfo vi=vInfos.get(arg2);
				Intent intent=new Intent();
				intent.putExtra("videoname", vi.getVideoName());
				intent.setClass(videoList.this,videotalk.class);
				
				startActivity(intent);
				return true;
			}
		});
		
		videoNet vn=new videoNet();
		vn.execute();
	}
	
	
	private String downloadXML(String urlStr) {
		HttpDownloader httpDownloader = new HttpDownloader();
		String result = httpDownloader.download(urlStr);
		return result;
	}

	private List<videoInfo> parse(String xmlStr) {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		List<videoInfo> infos = new ArrayList<videoInfo>();
		try {
			XMLReader xmlReader = saxParserFactory.newSAXParser()
					.getXMLReader();
			videoListContentHandler vListContentHandler = new videoListContentHandler(
					infos);
			xmlReader.setContentHandler(vListContentHandler);
			xmlReader.parse(new InputSource(new StringReader(xmlStr)));
			for (Iterator iterator = infos.iterator(); iterator.hasNext();) {
				videoInfo videoInfo = (videoInfo) iterator.next();
				//System.out.println(videoInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return infos;
	}
	
	
	
	private class videoNet extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String xml = downloadXML(AppConstant.IpAddress+":8080/mp4/resource.xml");
			vInfos=parse(xml);
			return xml;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			List<HashMap<String, java.lang.Object>> list=new ArrayList<HashMap<String,java.lang.Object>>();
			videoInfo vinfo=new videoInfo();
			for(int i=0;i<vInfos.size();i++){
				HashMap<String, java.lang.Object> map=new HashMap<String, java.lang.Object>();
				vinfo=vInfos.get(i);
				map.put("videoname", vinfo.getVideoName());
				
				
				map.put("size", vinfo.getVideoSize());
				String type=vinfo.getType();
				if(type.equals("gaoxiao")){
					map.put("img", R.drawable.videogaoxiao);
					map.put("type", "¸ãÐ¦");
				}else if(type.equals("dongman")){
					map.put("img", R.drawable.videodongman);
					map.put("type", "¶¯Âþ");
				}else if(type.equals("fengjing")){
					map.put("img", R.drawable.videofengjing);
					map.put("type", "·ç¾°");
				}else if(type.equals("mengchong")){
					map.put("img", R.drawable.videomengchong);
					map.put("type", "ÃÈ³è");
				}else if(type.equals("oumei")){
					map.put("img", R.drawable.videooumei);
					map.put("type", "Å·ÃÀ");
				}else{
					map.put("img", R.drawable.ic_type_video);
					map.put("type", vinfo.getType());
				}
				list.add(map);
			}
			SimpleAdapter adapter=new SimpleAdapter(
					videoList.this,
					list,
					R.layout.list_item1,
					new String[]{"img","videoname","type","size"},
					new int[]{R.id.imageView1,R.id.videoname,R.id.file_type,R.id.file_size}
			);
			videolist.setAdapter(adapter);
		}
	}

}
