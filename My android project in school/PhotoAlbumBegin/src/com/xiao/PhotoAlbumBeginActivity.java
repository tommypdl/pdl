package com.xiao;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PhotoAlbumBeginActivity extends Activity {
	private Spinner mSpinner;
	private ImageView mImageView1,mImageView2,mImageView3,mImageView4,mImageView5,mImageView6;
	private ImageButton mImageButton_help,mImageButton_close;
	private ImageButton mImageButton_pre,mImageButton_next;
	private ImageButton mImageButton_take_photo,mImageButton_synchro_photo;
	private TextView mTextView_photo_num;
	private TextView mTextView_hour,mTextView_minute;
	private ImageView[] imageViews;
	private int H,m,s;
	private Calendar c;
	
	private static final String PATH="/sdcard/photo/";
	private String[] photo_folder;
	private ArrayAdapter<String> adapter;
	
	private String folder_name;
	private List<List<String>> listParent=new ArrayList<List<String>>();
	private List<String> listchild=new ArrayList<String>();
	private int list_id=0;
	private int size;
	
	
	
	private Handler handler=new Handler();
	private Runnable task=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			handler.postDelayed(task, 1000);
			if(s<60){
				s++;
			}else if(m<60){
				s=0;
				m++;
			}else if(H<24){
				m=0;
				H++;
			}else{
				H=0;
			}
			mTextView_hour.setText(format(H));
			mTextView_minute.setText(format(m));
		}
	};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置屏幕的格式
        this.setScreen();
        setContentView(R.layout.main);
        //把xml文件中的布局组件引入进各个成员变量
        findViews();
        getCurrentTime();
        handler.postDelayed(task, 1000);
        listPhotoFolder(PATH);
        listPhoto(photo_folder[0]);
        showPhotos(photo_folder[0], listParent.get(0));
        Spinner sp=(Spinner) findViewById(R.id.Spinner);
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				folder_name=photo_folder[arg2];
				list_id=0;
				listParent.clear();
				listchild.clear();
				mImageButton_pre.setVisibility(View.VISIBLE);
				mImageButton_next.setVisibility(View.VISIBLE);
				listPhoto(folder_name);
				showPhotos(folder_name, listParent.get(arg2));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
       
        mImageButton_pre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				list_id--;
				showPhotos(folder_name, listParent.get(list_id));
				judge();
			}
		});
        mImageButton_next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				list_id++;
				showPhotos(folder_name, listParent.get(list_id));
				judge();
			}
		});
        mImageButton_help.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(PhotoAlbumBeginActivity.this, "此功能尚未完善", Toast.LENGTH_LONG).show();
			}
		});
        mImageButton_close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Dialog dialog=new AlertDialog.Builder(PhotoAlbumBeginActivity.this)
					.setTitle("退出提示")
					.setMessage("您确定要退出吗？")
					.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							PhotoAlbumBeginActivity.this.finish();
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							// TODO Auto-generated method stub
							
						}
					}).create();
				dialog.show();
			}
		});
        
        setOnListener();
        
    }
    
    public void setScreen(){
    	//设置全屏窗口
    	requestWindowFeature(Window.FEATURE_NO_TITLE);     
    	//全屏     
    	getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN ,       
    			WindowManager.LayoutParams. FLAG_FULLSCREEN);  
    	//横屏显示
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
    
    public void findViews(){
    	//显示系统时间
    	mTextView_hour = (TextView) findViewById(R.id.TextView_hour);
    	mTextView_minute = (TextView) findViewById(R.id.TextView_minute);
    	mSpinner = (Spinner) findViewById(R.id.Spinner);
    	mImageView1 = (ImageView) findViewById(R.id.ImageView1);
    	mImageView2 = (ImageView) findViewById(R.id.ImageView2);
    	mImageView3 = (ImageView) findViewById(R.id.ImageView3);
    	mImageView4 = (ImageView) findViewById(R.id.ImageView4);
    	mImageView5 = (ImageView) findViewById(R.id.ImageView5);
    	mImageView6 = (ImageView) findViewById(R.id.ImageView6);
    	
    	imageViews = new ImageView[]{mImageView1,mImageView2,mImageView3,mImageView4,mImageView5,mImageView6};
    	
    	mImageButton_help = (ImageButton) findViewById(R.id.ImageButton_help);
		mImageButton_close = (ImageButton) findViewById(R.id.ImageButton_close);
    	mImageButton_pre = (ImageButton) findViewById(R.id.ImageButton_pre);
    	mImageButton_next = (ImageButton) findViewById(R.id.ImageButton_next);
    	mImageButton_take_photo = (ImageButton) findViewById(R.id.ImageButton_take_photo);
    	mImageButton_synchro_photo = (ImageButton) findViewById(R.id.ImageButton_synchro_photo);
    	
    	mImageButton_pre.setVisibility(View.INVISIBLE);
    	mImageButton_next.setVisibility(View.INVISIBLE);
    	for(int i = 0;i < 6;i ++){
    		imageViews[i].setVisibility(View.GONE);
			imageViews[i].setEnabled(false);
		}    	
    	mImageButton_help.setBackgroundDrawable(getResources().getDrawable(R.drawable.help));
		mImageButton_close.setBackgroundDrawable(getResources().getDrawable(R.drawable.close));
    	mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_nor));
    	mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_nor));
    	mImageButton_take_photo.setBackgroundDrawable(getResources().getDrawable(R.drawable.take_photo_normal));
    	mImageButton_synchro_photo.setBackgroundDrawable(getResources().getDrawable(R.drawable.synchro_photo_normal));
    	mTextView_photo_num = (TextView) findViewById(R.id.TextView_photo_num);
    }
    
    public void getCurrentTime(){
    	c=Calendar.getInstance();
    	H=c.get(Calendar.HOUR_OF_DAY);
    	m=c.get(Calendar.MINUTE);
    	s=c.get(Calendar.SECOND);
    	mTextView_hour.setText(format(H));
    	mTextView_minute.setText(format(m));
    }
    
    private String format(int x){
    	String s=""+x;
    	if(s.length()==1){
    		s="0"+s;
    	}
    	return s;
    }
    
    public void listPhotoFolder(String path){
    	File file=new File(path);
    	File[] files=file.listFiles();
    	if(files.length>0){
    		int i=0;
    		photo_folder=new String[files.length];
    		for(File f:files){
    			photo_folder[i++]=f.getName();
    			System.out.println("filename:"+f.getName());
    		}
    		System.out.println("filesize:"+files.length);
    	}
    	adapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,photo_folder);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	mSpinner.setAdapter(adapter);
    }
    
    public void mMakeText(String string,boolean bool){
    	if(bool){
    		Toast.makeText(PhotoAlbumBeginActivity.this, string, Toast.LENGTH_LONG).show();
    	}
    	else{
    		Toast.makeText(PhotoAlbumBeginActivity.this, string, Toast.LENGTH_LONG).show();
    	}
    }
    
    public void listPhoto(String folder_name){
    	File file=new File(PATH+folder_name);
    	File[] files=file.listFiles();
    	size=files.length;
    	if(size>0){
    		if(size<=6){
    			for(int i=0;i<size;i++){
    				listchild.add(files[i].getName());
    			}
    			listParent.add(listchild);
    		}else{
    			int num=size/6;
    			if(size%6==0){
    				for(int i=0;i<num;i++){
    					listchild=new ArrayList<String>();
    					for(int j=i*6;j<6*(i+1);j++){
    						listchild.add(files[i].getName());
    					}
    					listParent.add(listchild);
    				}
    			}else{
    				for(int i=0;i<num;i++){
    					listchild=new ArrayList<String>();
    					for(int j=0;j<num;j++){
    						listchild.add(files[j].getName());
    					}
    					listParent.add(listchild);
    				}
    				listchild=new ArrayList<String>();
    				for(int k=6*num;k<size;k++){
    					listchild.add(files[k].getName());
    				}
    				listParent.add(listchild);
    			}
    		}
    	}
    }
    
    public void showPhotos(String folder_name,List<String> list){
    	judge();
    	if(list.size()<6){
    		for(int i=0;i<list.size();i++){
    			imageViews[i].setVisibility(View.VISIBLE);
    			imageViews[i].setEnabled(true);
    		}
    		for(int i=list.size();i<6;i++){
    			imageViews[i].setVisibility(View.GONE);
    			imageViews[i].setEnabled(false);
    		}
    	}else if(list.size()==6){
    		for(int i=0;i<6;i++){
    			imageViews[i].setVisibility(View.VISIBLE);
    			imageViews[i].setEnabled(true);
    		}
    	}
    	
    	for(int i=0;i<list.size();i++){
    		Bitmap bm=BitmapFactory.decodeFile(PATH+folder_name+File.separator+list.get(i));
    		imageViews[i].setImageBitmap(bm);
    	}
    	mTextView_photo_num.setText("has "+size+" photos");
    	System.out.println("size:"+size);
    }
    
    public void judge(){
    	if(listParent.size()<=1){
    		mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_dis));
    		mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_dis));
    		mImageButton_pre.setEnabled(false);
    		mImageButton_next.setEnabled(false);
    	}else{
    		if(list_id==0){
    			mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_dis));
        		mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_nor));
        		mImageButton_pre.setEnabled(false);
        		mImageButton_next.setEnabled(true);
    		}
    		if(list_id==listParent.size()-1){
    			mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_nor));
    			mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_dis));
        		mImageButton_pre.setEnabled(true);
        		mImageButton_next.setEnabled(false);
    		}
    		if(list_id>0&&list_id<listParent.size()-1){
    			mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_nor));
    			mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_nor));
        		mImageButton_pre.setEnabled(true);
        		mImageButton_next.setEnabled(true);
    		}
    	}
    }
    
    public void setOnListener(){
    	for(int i=0;i<6;i++){
    		final int n=i;
    		imageViews[n].setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					System.out.println("photoclick");
					mMakeText(list_id+"--"+n,true);
					Intent intent=new Intent();
					intent.setClass(PhotoAlbumBeginActivity.this, ShowPhoto.class);
					Bundle bundle=new Bundle();
					bundle.putInt("list_id", list_id);
					bundle.putInt("photo_id",n);
					bundle.putString("folder_name", folder_name);
					intent.putExtras(bundle);
					
					startActivity(intent);
				}
    			
    		});
    	}
    }
    
    
}
















