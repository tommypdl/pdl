package lqb;

import com.qsa.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class showresult extends Activity {
	LinearLayout ll;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ll=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.showresult, null);
		tv=(TextView) ll.getChildAt(0);
		Intent intent=getIntent();
		String value=intent.getStringExtra("test");
		String time=intent.getStringExtra("time");
		tv.setText(value);
		setContentView(ll);
		 Toast.makeText(showresult.this, "ºÄÊ±:"+time+"ms", Toast.LENGTH_LONG).show();
	}
		
}
