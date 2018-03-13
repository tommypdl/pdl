package lqb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import com.qsa.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class sousuodeal extends Activity {
	String path="";
	private static final int TEST_ID=Menu.FIRST;
	ScrollView sv;
	TextView tv;
	String text="";
	int p=1;
	Intent i=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sv=(ScrollView) LayoutInflater.from(getBaseContext()).inflate(R.layout.scroll, null);
		tv=(TextView) sv.getChildAt(0);
		setContentView(sv);
		Intent iget=getIntent();
		String pos=iget.getStringExtra("pos");
		 p=Integer.parseInt(pos);
		if(p==1){
			path="sushuhaun.txt";
			 try {  			
		            InputStream is = this.getAssets().open("sushuhuan.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
			
		}else if(p==2){
			path="beibaosousuo.txt";
			 try {  			
		            InputStream is = this.getAssets().open("beibaosousuo.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}else if(p==3){
			path="eightqueen.txt";
			 try {  			
		            InputStream is = this.getAssets().open("eightqueen.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}else if(p==4){
			path="digongqubao.txt";
			 try {  			
		            InputStream is = this.getAssets().open("digongqubao.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}
		else if(p==5){
			path="kanmugun.txt";
			 try {  			
		            InputStream is = this.getAssets().open("kanmugun.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}else if(p==6){
			path="dachenlvfei.txt";
			 try {  			
		            InputStream is = this.getAssets().open("dachenlvfei.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}else if(p==7){
			path="avcow.txt";
			 try {  			
		            InputStream is = this.getAssets().open("avcow.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}else if(p==8){
			path="daifenshu.txt";
			 try {  			
		            InputStream is = this.getAssets().open("daifenshu.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}else if(p==9){
			path="quanpailie.txt";
			 try {  			
		            InputStream is = this.getAssets().open("quanpailie.txt");  
		            int size = is.available();  			  			           
		            byte[] buffer = new byte[size];  
		            is.read(buffer);  
		            is.close();  			  			          
		            text = new String(buffer, "GB2312");  			  			           			           
		        } catch (IOException e) {  			           
		            throw new RuntimeException(e);  
		        }  
		}
		 

		
		
		tv.setText(text);
		
	
			
	}
	public static String readAssetTxt(Context context, String fileName) {
		  try {
		   InputStreamReader inputReader = new InputStreamReader(context

		   .getResources().getAssets().open(fileName));

		   BufferedReader bufReader = new BufferedReader(inputReader);

		   LineNumberReader reader = new LineNumberReader(bufReader);

		   String s = reader.readLine();

		   String result = "";

		   int lines = 0;
		   while (s != null) {
		    lines++;
		    s = reader.readLine();
		    result += s;
//		    Log.i(TAG, "lines" + lines + ":" + s);
		    
		   }

		   reader.close();

		   bufReader.close();

		  // Log.i(TAG, "getFromAssets:" + s);

		   return result;

		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return null;
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
			
			if(p==1||p==8){
				LayoutInflater factory = LayoutInflater.from(sousuodeal.this);
				final View textEntryView = factory.inflate(R.layout.dialogtest,
						null);
				AlertDialog dlg = new AlertDialog.Builder(sousuodeal.this)

						.setTitle("测试")
						.setView(textEntryView)
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

										EditText nums = (EditText) textEntryView
												.findViewById(R.id.test_edit);

										imm.showSoftInput(nums,
												InputMethodManager.SHOW_FORCED);

										String num = nums.getText().toString();

										i = new Intent();
										String pos = p + "";
										i.putExtra("pos", pos);
										i.setClass(sousuodeal.this,
												sousuodealresult.class);
										i.putExtra("num", num);
										startActivity(i);

										// setContentView(ll);

									}
								})
						.setNegativeButton("Cancel",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int whichButton) {
										System.out.println("-------------->2");

									}
								}).create();
				dlg.show();
			}
			else{
				Toast.makeText(sousuodeal.this, "本题暂不支持测试", Toast.LENGTH_LONG).show();
			}
			return true;
		
		
	}
		return super.onMenuItemSelected(featureId, item);
	}
	
	
}
