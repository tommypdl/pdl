package lqb;

import com.qsa.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class lqblist extends Activity {
		String lqb[]={"动态规划","递归","分治","贪心","模拟","搜索","高精度运算","历届试题"};
		ListView lv;
		ArrayAdapter<String> adapter;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.lqblist);
			setTitle("竞赛常用算法");
			lv=(ListView) findViewById(R.id.listViewlqb);
			adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lqb);
			lv.setAdapter(adapter);
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(lqblist.this, arg2+"", Toast.LENGTH_LONG).show();
					Intent i=new Intent();
					if(arg2==5){
						i.setClass(lqblist.this, sousuo.class);
						
					}
					if(arg2==0){
						i.setClass(lqblist.this, dp.class);
						
					}
					if(arg2==1){
						i.setClass(lqblist.this, digui.class);
						
					}
					if(arg2==2){
						i.setClass(lqblist.this, fenzhi.class);
						
					}
					if(arg2==4){
						i.setClass(lqblist.this, moni.class);
						
					}
					if(arg2==3){
						i.setClass(lqblist.this, tanxin.class);
						
					}
					if(arg2==7){
						i.setClass(lqblist.this, lijie.class);
						
					}
					if(arg2==6){
						i.setClass(lqblist.this, gaojingdu.class);
						
					}
					startActivity(i);
				}
			});
		}
		
}
