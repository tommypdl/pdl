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

public class tanxin extends Activity {
		String sousuo[]={"°á×À×Ó","·­Ó²±Ò","Ö½ÅÆ"};
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
					Toast.makeText(tanxin.this, arg2+"", Toast.LENGTH_LONG).show();
					Intent i=new Intent();
					
						i.putExtra("pos", arg2+"");
						i.setClass(tanxin.this, tanxindeal.class);
						startActivity(i);
					
				}
			});
			setContentView(ll);
		}
		
}
