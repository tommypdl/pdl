package lqb;

import java.math.BigInteger;
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

public class fenzhishowtest extends Activity {
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
		
		
	              
	               num = intent.getStringExtra("num");
	              
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
	    		Toast.makeText(fenzhishowtest.this, "耗时"+time+"ms", Toast.LENGTH_LONG).show();
	              

	         
		
		
	}
	
	public void test0(){
		
		char c[]=num.toCharArray();
		text=f(c,0,c.length)+"";
		
		
	}
	public void test1(){
		String[] str=num.split(" ");
		int k=Integer.parseInt(str[0]);
		int n=Integer.parseInt(str[1]);
		int mod=Integer.parseInt(str[2]);
		BigInteger re=gb(n,k);
		text=re+"";
		re=re.mod(BigInteger.valueOf(mod));
		
		text+=" 取模后的值："+re+"";
	}
	public int f(char s[], int begin, int end)
	{
		int mid;
		if(end-begin==1) return s[begin] - '0';
		mid = (end+begin) / 2;
		return f(s,mid,end)+f(s,begin,mid); 
	}
	public  int g(int n,int k,int mod)
	{
		if(n==0) return 1;
		if(n==1) return k;
		
		int m = n/2;
		int a = g(m,k,mod);
		if(n%2==0)
			return a * a % mod;
		else
			return a * a * k % mod;
	}
	public  BigInteger gb(int n,int k){
		if(n==0)return BigInteger.ONE;
		if(n==1)return BigInteger.valueOf(k);
		int m=n/2;
		BigInteger a=gb(m,k);
		if(n%2==0)
			return a.multiply(a);
		else
			return a.multiply(a).multiply(BigInteger.valueOf(k));
	}
}
