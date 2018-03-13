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

public class dpshowtest extends Activity {
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
		
		 LayoutInflater factory = LayoutInflater.from(dpshowtest.this);
	        final View textEntryView = factory.inflate(R.layout.dialogtest, null);
	        AlertDialog dlg = new AlertDialog.Builder(dpshowtest.this)
	       
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
	       		}else if(p.equals("3")){
	       			test3();
	       		}
	       		else if(p.equals("5")){
	       			test5();
	       		}
	       		long endtime=System.currentTimeMillis();
	       		 time=endtime-starttime;
	            
	       		tv.setText(text);
	    		//setContentView(ll);
	    		Toast.makeText(dpshowtest.this, "耗时"+time+"ms", Toast.LENGTH_LONG).show();
	              

	               
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
		String[] str=num.split(" ");
		int n=Integer.parseInt(str[0]);
		int m=Integer.parseInt(str[1]);
		int a[][]=new int[m+1][n+1];
		a[1][2]=a[1][n]=1;
		for(int i=2;i<=m;i++)
			for(int j=1;j<=n;j++)
				a[i][j]=a[i-1][f(j-1,n)]+a[i-1][f(j+1,n)];
		text=a[m][1]+"";
		
	}
	public void test3(){
		String[] str=num.split(" ");
		int k=Integer.parseInt(str[0]);
		int l=Integer.parseInt(str[1]);
		int i,j,p,sum=0;
		int f[][]=new int[l+1][k];
		 for(i=0;i<=k-1;i++)  
		        f[1][i]=1;  
		  
		    for(i=2;i<=l;i++)  
		    {  
		        for(j=0;j<=k-1;j++)  
		        {  
		            for(p=0;p<=k-1;p++)  
		  
		                if(p!=j-1&&p!=j+1)  
		  
		                f[i][j]+=f[i-1][p];  
		              
		        }  
		    }  
		  
		  
		    for(i=1;i<=k-1;i++)  
		        sum+=f[l][i]; 
		text=sum+"";
	
	}
	public void test5(){
		String[] str=num.split(" ");
		int len=Integer.parseInt(str[0]);
		int d=Integer.parseInt(str[1]);
		long[][][] A=new long[len+1][10][10];
		for(int i=1;i<=d;i++){
			for(int j=0;i+j<=d;j++){
				A[2][i][j]=1;
			}
		}
		int n=3;
		do{
			for(int i=0;i<=d;i++)
				for(int j=0;i+j<=d;j++)
					if(A[n-1][i][j]!=0)
						for(int k=0;i+j+k<=d;k++)
							A[n][j][k]+=A[n-1][i][j];
			n++;
		}while(n<=len);
		long total=0;
		for(int i=0;i<=d;i++)
			for(int j=0;i+j<=d;j++)
				total+=A[len][i][j];
		text=total+"";

	}
	
	public static int f(int x,int n){
		if(x<1)
			return x+n;
		if(x>n)
			return x-n;
		return x;
	}
}
