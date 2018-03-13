package lqb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.qsa.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class diguishowtest extends Activity {
	LinearLayout ll;
	TextView tv;
	String text="",num="20";
	int N=0;
	String p="";
	long time=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ll=(LinearLayout) LayoutInflater.from(getBaseContext()).inflate(R.layout.showresult, null);
		tv=(TextView) ll.getChildAt(0);
		tv.setText("正在处理中，请耐心等待");
		setContentView(ll);
		Intent intent=getIntent();
		 p=intent.getStringExtra("pos");
		
		 LayoutInflater factory = LayoutInflater.from(diguishowtest.this);
	        final View textEntryView = factory.inflate(R.layout.dialogtest, null);
	        AlertDialog dlg = new AlertDialog.Builder(diguishowtest.this)
	       
	        .setTitle("测试")
	        .setView(textEntryView)
	        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	            	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 

	            	
	              EditText nums = (EditText) textEntryView.findViewById(R.id.test_edit);
	              
	              imm.showSoftInput(nums,InputMethodManager.SHOW_FORCED);
	              
	               num = nums.getText().toString();
	              
	               long starttime=System.currentTimeMillis();
	       		if(p.equals("0")){
	       			test0();
	       		}else if(p.equals("1")){
	       			test1();
	       		}
	       		
	       		long endtime=System.currentTimeMillis();
	       		 time=endtime-starttime;
	            
	       		tv.setText(text);
	    		//setContentView(ll);
	    		Toast.makeText(diguishowtest.this, "耗时"+time+"ms", Toast.LENGTH_LONG).show();
	              

	               
	            }
	        })
	        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int whichButton) {
	             System.out.println("-------------->2");
	               
	            }
	        })
	        .create();
	        dlg.show();
		
		
	}
	
	public void test0(){
		
		int n=Integer.parseInt(num);
		text=fi(n)+"";
		
		
	}
	public void test1(){
		String[] str=num.split(" ");
		int k=Integer.parseInt(str[0]);
		int l=Integer.parseInt(str[1]);
		text=f(k,l)+"";
	}
	public int fi(int n){
		if(n==1||n==2)return 1;
		return fi(n-1)+fi(n-2);
	}
	public  int f(int a,int b){
		if(b==0)return a;
		else return f(b,a%b);
	}
}
