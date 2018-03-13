package lqb;

import com.qsa.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class sousuo extends Activity {
		String sousuo[]={"趣味算式","素数环","背包问题","8皇后问题","地宫取宝","砍木棍","大臣的旅费","安慰奶牛","带分数","全排列"};
		LinearLayout ll;
		ListView lv;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			ll=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.sonlist, null);
			lv=(ListView) ll.getChildAt(0);
			ArrayAdapter<String> a=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sousuo);
			lv.setAdapter(a);
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(sousuo.this, arg2+"", Toast.LENGTH_LONG).show();
					Intent i=new Intent();
					if(arg2==0){
						i.setClass(sousuo.this, sousuo_police.class);
						startActivity(i);
					}
					else{
						i.putExtra("pos", arg2+"");
						i.setClass(sousuo.this, sousuodeal.class);
						startActivity(i);
					}
				}
			});
			setContentView(ll);
		}
		
}
