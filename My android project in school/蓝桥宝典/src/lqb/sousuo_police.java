package lqb;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Vector;

import com.qsa.R;





import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class sousuo_police extends Activity {
	private static final int TEST_ID=Menu.FIRST;
	String texts="";
	String text="�˾��벦110,��ʹ�ֻ�Ƿ��Ҳ�ɲ�ͨ��\n"

   +" Ϊ�˱���������򣬱�������Ⱥ�������Ʋ���ȫ������������Ҫ���ﷸ���Ƕ��£������Ҫ�����Եؽ�������ѵ��������ѵ����\n"

+"    ĳ�������������ڽ�������ѵ����\n"

+"    1 2 3 4 5 6 7 8 9 = 110;\n"

+"    �뿴�ϱߵ���ʽ��Ϊ��ʹ��ʽ��������Ҫ�����ּ�����ӺŻ��߼��ţ����Բ�������������������ţ���֮��û��������ŵ�������ϳ�һ���������磺12+34+56+7-8+9 ����һ�ֺϸ�����123+4+5+67-89 ����һ�����ܵĴ𰸡�\n"

+"   �������ü���������ƣ�����������������ҵ����д𰸡�\n"

 +"   ÿ����ռһ�С����磺\n"

+"12+34+56+7-8+9\n"
+"123+4+5+67-89\n"
+"......\n"

+"    ��֪�������𰸿�������������Ʒ֡�\n"
    
+"    �����𰸵�ǰ��˳����Ҫ��\n"

+"   ע�⣺\n"

+"    ����ϸ���ԣ����ĳ���ֻ�������г���ȷ�����ʱ����л���÷֣�\n"
    
 +"   ���������д��ͬһ���ļ��У����Ժú󣬴����롾�����ļ��С��¶�Ӧ��ŵġ����.txt���м��ɡ�\n"
    
 +"   ��صĹ����ļ���Ҫ���롣\n"
    
+"    �벻Ҫʹ��package��䡣\n"
	+"	import java.util.*;\n"

+"	public class B33\n"
+"	{\n"
+"		public static void f(String cur, int goal, List<Integer> lst)\n"
+"		{	\n"
+"			if(lst.size()==0) return;\n"
			
+"			int a = lst.remove(lst.size()-1);\n"
+"			if(lst.size()==0)\n"
+"			{\n"
+"				if(goal==a) System.out.println(a + cur);\n"
+"				return;\n"
+"			}\n"
		
+"			List<Integer> lst2 = new Vector<Integer>();\n"
+"			lst2.addAll(lst);\n"
+"			List<Integer> lst3 = new Vector<Integer>();\n"
+"			lst3.addAll(lst);\n"
					
	
			
			
+"			int b = lst.remove(lst.size()-1);\n"

+"			f(cur, goal, lst);\n"
+"		} \n"
		
+"		public static void main(String[] args)\n"
+"		{\n"
+"			List<Integer> lst = new Vector<Integer>();\n"
	+"			for(int i=1; i<=9; i++) lst.add(i);\n"
			

+"		}\n"
+"	}\n";
    

	ScrollView sv;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		sv=(ScrollView) LayoutInflater.from(getBaseContext()).inflate(R.layout.scroll, null);
		tv=(TextView) sv.getChildAt(0);
		
		 try {  
			//Return an AssetManager instance for your application's package  
			            InputStream is = getAssets().open("quweisuanshi.txt");  
			            int size = is.available();  
			  
			            // Read the entire asset into a local byte buffer.  
			            byte[] buffer = new byte[size];  
			            is.read(buffer);  
			            is.close();  
			  
			            // Convert the buffer into a string.  
			            texts = new String(buffer, "GB2312");  
			  
			            // Finally stick the string into the text view.  
			           
			        } catch (IOException e) {  
			            // Should never happen!  
			            throw new RuntimeException(e);  
			        }  
		
		tv.setText(texts);
		setContentView(sv);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		 menu.add(0,TEST_ID,0,"����");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case TEST_ID:
			test();
			return true;
		
		
	}
		return super.onMenuItemSelected(featureId, item);
	}
	public void test(){
		
		 LayoutInflater factory = LayoutInflater.from(sousuo_police.this);
         final View textEntryView = factory.inflate(R.layout.dialogtest, null);
         AlertDialog dlg = new AlertDialog.Builder(sousuo_police.this)
        
         .setTitle("����")
         .setView(textEntryView)
         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
             public void onClick(DialogInterface dialog, int whichButton) {
             	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 

             	
               EditText nums = (EditText) textEntryView.findViewById(R.id.test_edit);
               
               imm.showSoftInput(nums,InputMethodManager.SHOW_FORCED);
               
               String numss = nums.getText().toString();
               
               int num=Integer.parseInt(numss);
               long starttime=System.currentTimeMillis();
               String st=quwei(num);
               long endtime=System.currentTimeMillis();
               long time=endtime-starttime;
               String ti=time+"";
               Intent i=new Intent();
               i.putExtra("test", st);
               i.putExtra("time", ti);
               i.setClass(sousuo_police.this, showresult.class);
               startActivity(i);
              
               

                
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
	static String strings="";
	public String quwei(int num){
		List<Integer> lst = new Vector<Integer>();
		for(int i=1; i<=9; i++) lst.add(i);
		
		f("",num, lst);
		return strings;
	}
	public static void f(String cur, int goal, List<Integer> lst)
	{	
		if(lst.size()==0) return;
		
		int a = lst.remove(lst.size()-1);
		if(lst.size()==0)
		{
			if(goal==a) strings+=a +""+ cur+"\n";
			return;
		}
	
		List<Integer> lst2 = new Vector<Integer>();
		lst2.addAll(lst);
		List<Integer> lst3 = new Vector<Integer>();
		lst3.addAll(lst);
				
		f("+" + a + "" + cur, goal-a, lst2);
		f("-" + a + "" + cur, goal+a, lst3);
		
		int b = lst.remove(lst.size()-1);
		lst.add(Integer.parseInt(b+""+a));
		f(cur, goal, lst);
	} 
}
