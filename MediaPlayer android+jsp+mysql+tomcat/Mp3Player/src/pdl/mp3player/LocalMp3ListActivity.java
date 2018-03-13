package pdl.mp3player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import pdl.model.Mp3Info;
import pdl.utils.FileUtils;

import pdl.mp3player.R;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class LocalMp3ListActivity extends ListActivity{
	List<Mp3Info> mp3Infos;
	private static final String[] sizeSuffixes = { "B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB" };

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_mp3_list);
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		FileUtils fileUtils=new FileUtils();
		mp3Infos=fileUtils.getMp3Files("mp3/");
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		for (Iterator iterator=mp3Infos.iterator();iterator.hasNext();) {
		Mp3Info mp3Info=(Mp3Info) iterator.next();
		HashMap<String, String> map=new HashMap<String, String>();
		map.put("mp3_name", mp3Info.getMp3Name());
		long l=Long.valueOf(mp3Info.getMp3Size());
		map.put("mp3_size", bytesToHumanReadable(l));
		list.add(map);
		}
		SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,
		R.layout.mp3info_item,new String[]{"mp3_name","mp3_size"},
		new int[]{R.id.mp3_name,R.id.mp3_size});
		setListAdapter(simpleAdapter);
		super.onResume();
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if(mp3Infos!=null){
			Mp3Info mp3Info=mp3Infos.get(position);
			Intent intent=new Intent();
			intent.putExtra("mp3Info", mp3Info);
			intent.setClass(this, PlayerActivity.class);
			startActivity(intent);
		}
	}
	 public static String bytesToHumanReadable(long bytes) {
	        double result = bytes;
	        int attachedsuff = 0;
	        while (result > 1024 && attachedsuff < sizeSuffixes.length) {
	            result /= 1024.;
	            attachedsuff++;
	        }
	        result = ((int) (result * 100)) / 100.0;
	        return result + " " + sizeSuffixes[attachedsuff];
	    }
}
