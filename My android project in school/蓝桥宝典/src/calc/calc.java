package calc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qsa.R;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calc extends Activity {
	EditText result;
	TextView content;
	Button back;
	Button clear;
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	Button b5;
	Button baifen;
	Button b6;
	Button fx;
	Button b7;
	Button b8;
	Button b9;
	Button b0;
	Button jian;
	Button jia;
	Button cheng;
	Button chu;
	Button kaifang;
	Button daoshu;
	Button dian;
	Button enter;
	Button root;
	Button mi;
	Button fi;
	Button zhi;
	Button kh;
	Button jinzhi;
	String str="";
	String str1="";
	boolean rootflag=false;
	boolean miflag;
	int khcount=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("高级计算器");
        result=(EditText)findViewById(R.id.editText1);
        content=(TextView)findViewById(R.id.textView2);
        back=(Button)findViewById(R.id.buttonfirst);
        clear=(Button)findViewById(R.id.button2);
        b7=(Button)findViewById(R.id.button3);
        b8=(Button)findViewById(R.id.button4);
        b9=(Button)findViewById(R.id.button5);
        chu=(Button)findViewById(R.id.button6);
        b4=(Button)findViewById(R.id.button7);
        b5=(Button)findViewById(R.id.button8);
        b6=(Button)findViewById(R.id.button9);
        cheng=(Button)findViewById(R.id.button10);
        b1=(Button)findViewById(R.id.button11);
        b2=(Button)findViewById(R.id.button12);
        b3=(Button)findViewById(R.id.button13);
        jian=(Button)findViewById(R.id.button14);
        b0=(Button)findViewById(R.id.button15);
        fx=(Button)findViewById(R.id.button16);
        dian=(Button)findViewById(R.id.button17);
        jia=(Button)findViewById(R.id.button18);
        baifen=(Button)findViewById(R.id.button19);
        kaifang=(Button)findViewById(R.id.button20);
        daoshu=(Button)findViewById(R.id.button21);
        enter=(Button)findViewById(R.id.button22);
        root=(Button)findViewById(R.id.button23);
        mi=(Button)findViewById(R.id.button24);
        fi=(Button)findViewById(R.id.button25);
        zhi=(Button)findViewById(R.id.button26);
        kh=(Button)findViewById(R.id.button27);
        jinzhi=(Button)findViewById(R.id.button28);
        OnClickListener on=new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				int t,sum=0;
				if(arg0==kh){
					
					if(khcount%2!=0){
						str1+="(";
						str+="(";
						content.setText(str1);
					}else{
						str1+=")";
						str+=")";
						content.setText(str1);
					}
					khcount++;
				}
				if(arg0==b7){
					str1+="7";
					str+="7";
					
					content.setText(str1);
				}
				if(arg0==b8){
					str1+="8";
					str+="8";
					content.setText(str1);
				}
				if(arg0==b9){
					str1+="9";
					str+="9";
					content.setText(str1);
				}
				if(arg0==b4){
					str1+="4";
					str+="4";
					content.setText(str1);
				}
				if(arg0==b5){
					str1+="5";
					str+="5";
					content.setText(str1);
				}
				if(arg0==b6){
					str1+="6";
					str+="6";
					content.setText(str1);
				}
				if(arg0==b1){
					str1+="1";
					str+="1";
					content.setText(str1);
				}
				if(arg0==b2){
					str1+="2";
					str+="2";
					content.setText(str1);
				}
				if(arg0==b3){
					str1+="3";
					str+="3";
					content.setText(str1);
				}
				if(arg0==jia){
					str1+="+";
					str+="+";
					content.setText(str1);
				}
				if(arg0==jian){
					str1+="-";
					str+="-";
					content.setText(str1);
				}
				if(arg0==cheng){
					str1+="*";
					str+="*";
					content.setText(str1);
				}
				if(arg0==chu){
					str1+="/";
					str+="/";
					content.setText(str1);
				}
				if(arg0==b0){
					str1+="0";
					str+="0";
					content.setText(str1);
				}
				if(arg0==dian){
					str1+=".";
					str+=".";
					content.setText(str1);
				}
				if(arg0==baifen){
					str1+="%";
					str+="*0.01";
					content.setText(str1);
				}
				if(arg0==kaifang){
					str1+="π";
					str+="*3.14";
					content.setText(str1);
				}
				if(arg0==daoshu){
					str1+="1/";
					str+="1/";
					content.setText(str1);
				}
				if(arg0==fx){
					
					str1+="!";
					String temp="";
					for(int i=str.length()-1;i>=0;i--){
						if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/')
							break;
						temp+=str.charAt(i);
					}
					str=str.substring(0, str.length()-temp.length());
					
					String temp1="";
					for(int i=temp.length()-1;i>=0;i--)
						temp1+=temp.charAt(i);
					int flag;
					for(flag=0;flag<temp1.length();flag++){
						if(temp1.charAt(flag)=='.')
							break;
					}
					
					temp1=temp1.substring(0, flag);
					
					int n=Integer.parseInt(temp1);
					long r=1;
					for(int i=1;i<=n;i++)
						r*=i;
					str+=r+"";
					
					content.setText(str1);
				}
				if(arg0==back){
					
					if(str1.length()==1){
						str+=str;
						str1+=str1;
						Toast.makeText(calc.this, "已经是最后一个", Toast.LENGTH_LONG).show();
					}
					else{
					int n=str.length();
					int n1=str1.length();
					if(str1.charAt(n1-1)=='π')
						str=str.substring(0,n-4);
					if(str1.charAt(n1-1)=='%')
						str=str.substring(0,n-4);
					if(str1.charAt(n1-1)=='!'){
						int i;
						for( i=n-1;i>=0;i--){
							if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/')
								break;
						}
						int j;
						for(j=n1-2;j>=0;j--){
							if(str1.charAt(i)=='+'||str1.charAt(i)=='-'||str1.charAt(i)=='*'||str1.charAt(i)=='/')
								break;
						}
						String temp=str1.substring(j, n1-1);
						str=str.substring(0, i+1);
						str+=temp;
					}
					else
					str=str.substring(0, str.length()-1);
					str1=str1.substring(0, str1.length()-1);
					content.setText(str1);
					}
				}
				if(arg0==clear){
					str1="";
					str="";
					content.setText(str1);
					khcount=1;
					result.setText(null);
				}if(arg0==enter){
					
						if(rootflag){
							String ss=str1.charAt(0)+"";
							if(ss.equals("√")){
								int flag1=1;
								for(int i=0;i<str.length();i++){
									if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/'){
										str="";
										str1="";
										flag1=0;
										Toast.makeText(calc.this, "部分公式需要单独使用", Toast.LENGTH_LONG).show();
										content.setText("");
										break;
									}
								}
								if(flag1==1){
								String s=str1.substring(1, str1.length());
								int n=Integer.parseInt(s);
								double u=Math.sqrt(n);
								String uu=u+"";
								result.setText(uu);
								
								str="";
								str1="";
								Toast.makeText(calc.this, "root="+uu, Toast.LENGTH_LONG).show();
							}
							}
						}
							 if(miflag){
								 int flag2=1;
								 for(int i=0;i<str.length();i++){
										if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/'){
											str="";
											str1="";
											flag2=0;
											Toast.makeText(calc.this, "部分公式需要单独使用", Toast.LENGTH_LONG).show();
											content.setText("");
											break;
										}
									}
								 if(flag2==1){
								int i;
								for(i=0;i<str1.length();i++){
									String sss=str1.charAt(i)+"";
									if(sss.equals("^"))
										break;
								}
								String s1=str1.substring(0, i);
								String s2=str1.substring(i+1, str1.length());
								int n1=Integer.parseInt(s1);
								int n2=Integer.parseInt(s2);
								long r=1;
								for(int j=0;j<n2;j++){
									r*=n1;
								}
								String rr=r+"";
								result.setText(rr);
								miflag=false;
								str="";
								str1="";
						}
							 }
					else if(str.equals("")==false){
						
					for(int i=0;i<str1.length();i++){
						if(str1.charAt(i)=='√'||str1.charAt(i)=='^'){
							Toast.makeText(calc.this, "部分公式需要单独使用", Toast.LENGTH_LONG).show();
							str="";
							str1="";
							break;
						}
					}
					content.setText(str1);
					result.setText(replace(str));
					}
				}
				if(arg0==root){
					if(str.equals("")==false){
						Toast.makeText(calc.this, "本公式需要单独使用", Toast.LENGTH_LONG).show();
						str="";
						str1="";
						
						str1+="√";
						
						content.setText(str1);
						rootflag=true;
					}else{
						rootflag=true;
						str1+="√";
						
						content.setText(str1);
					}
				}
				if(arg0==mi){
					int flag=1;
					for(int i=0;i<str.length();i++){
						if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/'){
							flag=0;
							break;
						}
					}
					if(flag==0){
						Toast.makeText(calc.this, "本公式需要单独使用", Toast.LENGTH_LONG).show();
						str="";
						str1="";
						
						miflag=true;
						content.setText(str1);
					}else{
						miflag=true;
						str1+="^";
						content.setText(str1);
					}
				}
				if(arg0==fi){
					int flag=1;
					for(int i=0;i<str.length();i++){
						if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/'){
							flag=0;
							break;
						}
					}
					if(flag==0){
						Toast.makeText(calc.this, "本公式需要单独使用", Toast.LENGTH_LONG).show();
						str="";
						str1="";
						
						
						content.setText(str1);
					}else{
						str1="";
						String s=str;
						str1="Fibonacci("+s+")";
						content.setText(str1);
						int n=Integer.parseInt(s);
						int a=1,b=1,c=0;
						for(int i=0;i<n-2;i++){
							c=a+b;
							a=b;
							b=c;
						}
						result.setText(c+"");
					}
				}
				if(arg0==zhi){
					int flag=1;
					for(int i=0;i<str.length();i++){
						if(str.charAt(i)=='+'||str.charAt(i)=='-'||str.charAt(i)=='*'||str.charAt(i)=='/'){
							flag=0;
							break;
						}
					}
					if(flag==0){
						Toast.makeText(calc.this, "本公式需要单独使用", Toast.LENGTH_LONG).show();
						str="";
						str1="";
						
						
						content.setText(str1);
					}else{
						int b=1;
						int n=Integer.parseInt(str);
						for(int i=2;i<=Math.sqrt(n);i++){
							if(n%i==0){
								b=0;
								break;
							}
						}
						if(b==0)
							result.setText(str+"不是质数");
						else
							result.setText(str+"是质数");
						content.setText(str1);
					}
				}
			}
		};
		b7.setOnClickListener(on);
		b8.setOnClickListener(on);
		b9.setOnClickListener(on);
		b4.setOnClickListener(on);
		b5.setOnClickListener(on);
		b6.setOnClickListener(on);
		b1.setOnClickListener(on);
		b2.setOnClickListener(on);
		b3.setOnClickListener(on);
		chu.setOnClickListener(on);
		cheng.setOnClickListener(on);
		jia.setOnClickListener(on);
		jian.setOnClickListener(on);
		b0.setOnClickListener(on);
		dian.setOnClickListener(on);
		baifen.setOnClickListener(on);
		daoshu.setOnClickListener(on);
		kaifang.setOnClickListener(on);
		enter.setOnClickListener(on);
		back.setOnClickListener(on);
		clear.setOnClickListener(on);
		fx.setOnClickListener(on);
		root.setOnClickListener(on);
		mi.setOnClickListener(on);
		fi.setOnClickListener(on);
		zhi.setOnClickListener(on);
		kh.setOnClickListener(on);
		jinzhi.setOnClickListener(
				new OnClickListener(){

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						
		                LayoutInflater factory = LayoutInflater.from(calc.this);
		                final View textEntryView = factory.inflate(R.layout.dialog, null);
		                AlertDialog dlg = new AlertDialog.Builder(calc.this)
		               
		                .setTitle("进制转换")
		                .setView(textEntryView)
		                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                    public void onClick(DialogInterface dialog, int whichButton) {
		                    	InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 

		                    	
		                      EditText nums = (EditText) textEntryView.findViewById(R.id.username_edit);
		                      EditText ns = (EditText) textEntryView.findViewById(R.id.password_edit);
		                      imm.showSoftInput(nums,InputMethodManager.SHOW_FORCED);
		                      imm.showSoftInput(ns,InputMethodManager.SHOW_FORCED);
		                      String numss = nums.getText().toString();
		                      String nss=ns.getText().toString();
		                      int num=Integer.parseInt(numss);
		                      int n=Integer.parseInt(nss);
		                    
		                      
		                      result.setText(change(num,n));
		
		                       
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
					
				}
				);
    }


   
    public static String replace(String str){    
    	 String ret = str;    
    	 Pattern p = Pattern.compile("\\((.+?)\\)");   
    	  Matcher m = p.matcher(str);    
    	 while(m.find()){       
    	String param = calculate(m.group(1));       
    	ret = ret.replaceFirst("\\("+m.group(1).replaceAll("[*+]", "\\\\$0")+"\\)", param);             }    
    	 if(ret.matches("\\((.+?)\\)")) return replace(ret);        
    	   return calculate(ret);   }    
    	   public static String calculate(String str){ 
//    	    System.out.println(str);     
    	String ret = "";     
    	Pattern p = Pattern.compile("(\\d+(\\.\\d+)?)([/*])(\\d+(\\.\\d+)?)");    
    	 Matcher m = p.matcher(str);   
    	  if(m.find()){      
    	 double pre = new Double(m.group(1)).doubleValue();    
    	   String code = m.group(3);    
    	   double pos = new Double(m.group(4)).doubleValue();      
    	 if("*".equals(code)) ret = pre * pos + "";     
    	  if("/".equals(code)) ret = pre / pos + "";      
    	 return calculate(str.replaceFirst(m.group(0).replaceFirst("\\*", "\\\\*"), ret));  
    	   }else {  
    	     p = Pattern.compile("(\\d+(\\.\\d+)?)([+-])(\\d+(\\.\\d+)?)");     
    	  m = p.matcher(str);     
    	  if(m.find()){    
    	     double pre = new Double(m.group(1)).doubleValue();    
    	     String code = m.group(3);         
    	double pos = new Double(m.group(4)).doubleValue();      
    	   if("+".equals(code)) ret = pre + pos + "";     
    	    if("-".equals(code)) ret = pre - pos + "";        
    	 return calculate(str.replaceFirst(m.group(0).replaceFirst("\\+", "\\\\+"), ret));       }     }          
    	 return str;   }
    	
    	   public static String change(int num,int n){
    			if(n>=2&&n<=9){
    				String t="";
    				while(num!=0){
    					t+=num%n;
    					num/=n;
    				}
    				String tt="";
    				for(int i=t.length()-1;i>=0;i--)
    					tt+=t.charAt(i);
    				return tt;
    			}else if(n==16){
    				return Integer.toHexString(num)+"";
    			}else
    				return "暂不支持该类型转换";
    		}

}
