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

public class lijie extends Activity {
		String sousuo[]={"出栈次序","国王的烦恼","海盗分金币","横向打印二叉树","剪格子","九宫重排","矩阵翻硬币","李白打酒","六角幻方","买不到的数目","牌型总数","日期问题","稍大的串","危险系数","邮局","正则","最大乘积"};
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
					Toast.makeText(lijie.this, arg2+"", Toast.LENGTH_LONG).show();
					Intent i=new Intent();
					
						i.putExtra("pos", arg2+"");
						i.setClass(lijie.this, lijiedeal.class);
						startActivity(i);
					
				}
			});
			setContentView(ll);
		}
		
}
