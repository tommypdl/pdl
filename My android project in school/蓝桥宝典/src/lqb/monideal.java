package lqb;

import java.io.IOException;
import java.io.InputStream;

import com.qsa.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class monideal extends Activity {
	private static final int TEST_ID = Menu.FIRST;
	ScrollView sv;
	TextView tv;
	String text = "";
	int p = 1;
	Intent i = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sv = (ScrollView) LayoutInflater.from(getBaseContext()).inflate(
				R.layout.scroll, null);
		tv = (TextView) sv.getChildAt(0);
		tv.setText("正在加载中...");
		setContentView(sv);
		Intent iget = getIntent();
		String pos = iget.getStringExtra("pos");
		p = Integer.parseInt(pos);
		switch (p) {
		case 0:
			text=getText(monideal.this,"duoguan.txt");
			break;
		case 1:
			text=getText(monideal.this,"gudaiduju.txt");
			break;
		
		default:
			break;

		}
		tv.setText(text);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0,TEST_ID,0,"测试");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case TEST_ID:
			
		
				Toast.makeText(monideal.this, "本题暂不支持测试", Toast.LENGTH_LONG).show();
			
			return true;
		
		
	}
		return super.onMenuItemSelected(featureId, item);
	}

	public String getText(Context context, String path) {
		String temptext = "";
		try {
			InputStream is = context.getAssets().open(path);
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			temptext = new String(buffer, "GB2312");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return temptext;
	}

}
