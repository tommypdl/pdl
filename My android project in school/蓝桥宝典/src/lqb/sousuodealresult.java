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

public class sousuodealresult extends Activity {
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
		
		setContentView(ll);
		tv.setText("正在处理中，请耐心等待");
		Intent intent=getIntent();
		 p=intent.getStringExtra("pos");
		
		
	              
	               num = intent.getStringExtra("num");
	              
	               long starttime=System.currentTimeMillis();
	       		if(p.equals("1")){
	       			test1();
	       		}else if(p.equals("8")){
	       			test8();
	       		}
	       		long endtime=System.currentTimeMillis();
	       		 time=endtime-starttime;
	            
	       		tv.setText(text);
	       		
	    		Toast.makeText(sousuodealresult.this, "耗时"+time+"ms", Toast.LENGTH_LONG).show();
	              

	        
		
		
	}
	public void getNum(){
		LayoutInflater factory = LayoutInflater.from(sousuodealresult.this);
        final View textEntryView = factory.inflate(R.layout.dialogtest, null);
        AlertDialog dlg = new AlertDialog.Builder(sousuodealresult.this)
       
        .setTitle("测试")
        .setView(textEntryView)
        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 

            	
              EditText nums = (EditText) textEntryView.findViewById(R.id.test_edit);
              
              imm.showSoftInput(nums,InputMethodManager.SHOW_FORCED);
              
               num = nums.getText().toString();
              
               long starttime=System.currentTimeMillis();
       		if(p.equals("1")){
       			test1();
       		}else if(p.equals("8")){
       			test8();
       		}
       		long endtime=System.currentTimeMillis();
       		 time=endtime-starttime;
            
             
              

               
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
	public void test1(){
		int n=Integer.parseInt(num);
		dfs(n);
	}
	public void test8(){
		int n=Integer.parseInt(num);
		N=n;
		int[] t = {1,2,3,4,5,6,7,8,9};
		pai(t,0);
	}
	
	public  boolean isPrime(int n){
		for(int i=2;i<=Math.sqrt(n);i++)
			if(n%i==0)
				return false;
		return true;
	}
	public  void dfs(int n){
		int values[]=new int[n+1];
		for(int i=0;i<n;i++)
			values[i]=i+1;
		values[n]=1;
		StringBuffer sb=new StringBuffer();
		List l=new ArrayList();
		l.add(values);
		while(l.size()>0){
			int temp[]=(int[])l.get(0);
			int index=temp[n];
			for(int k=temp[n];k<n;k++){
				if(isPrime(temp[index-1]+temp[k])){
					if(index==n-1&&isPrime(temp[index]+1)){
						for(int j=0;j<n;j++)
							sb.append(temp[j]+" ");
						sb.append("\n");
						continue;
					}
					int len=temp.length;
					int newvalues[]=new int[len];
					for(int i=0;i<len;i++)
						newvalues[i]=temp[i];
					if(index!=k){
						int t=newvalues[index];
						newvalues[index]=newvalues[k];
						newvalues[k]=t;
					}
					newvalues[n]=newvalues[n]+1;
					l.add(0,newvalues);
				}
			}
			l.remove(temp);
		}
		text+=sb.toString();
	}
	
	public int col(int[] zu, int a, int b)
	{
		int t = 0;
		for(int i=a; i<b; i++) t = t*10 + zu[i];
		return t;
	}
	
	public void g(int[] zu)
	{		
		for(int i=1; i<zu.length-1; i++)
		for(int j=i+1; j<zu.length; j++)
		{
			int a = col(zu,0,i);
			int b = col(zu,i,j);
			int c = col(zu,j,zu.length);
			
			if(b%c!=0) continue;
			
			if(a+b/c==N){
				text+=a + " + " + b + " / " + c+"\n";
				
			}
		}
	}
	
	public void pai(int[] zu, int k)
	{
		if(k==zu.length-1)
		{
			g(zu);
			return;
		}
		
		for(int i=k; i<zu.length; i++)
		{
			{int t=zu[k]; zu[k]=zu[i]; zu[i]=t;}
			pai(zu,k+1);
			{int t=zu[k]; zu[k]=zu[i]; zu[i]=t;}
		}
	}
}
