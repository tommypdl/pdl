package com.xiao;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowPhoto extends Activity {
	private TextView mTextView_name,mTextView_time;    //显示相片名称及上传时间
	private ImageView mImageView;   //显示相片
	private ImageButton mImageButton_pre,mImageButton_next,mImageButton_delete,mImageButton_play,mImageButton_back;
	
	private int photo_id;
	private int list_id;
	private String folder_name;
	private int id;
	
	
	private static final String PATH="/sdcard/photo/";
	private  String filePath;
	private List<String> list=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setScreen();
		this.setContentView(R.layout.show_photo);
		//1.获取传递过来的参数
		Bundle bundle=this.getIntent().getExtras();
		folder_name=bundle.getString("folder_name");
		list_id=bundle.getInt("list_id");
		photo_id=bundle.getInt("photo_id");
		id=list_id*6+photo_id;
		System.out.println(folder_name+" "+list_id);
		
		//初始化UI对象
		findViews();
		
		 
	        mImageButton_pre.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					id--;
					viewPhoto(id);
					judge();
				}
			});
	        mImageButton_next.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					id++;
					viewPhoto(id);
					judge();
				}
			});
	       mImageButton_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Dialog dialog=new AlertDialog.Builder(ShowPhoto.this)
				.setTitle("删除提示")
				.setMessage("您确定要删除吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						File file=new File(filePath);
						boolean isDelete=file.delete();
						mMakeText(isDelete+"", true);
						listPhoto(folder_name);
						if(id==0){
							viewPhoto(0);
						}else if(id==list.size()-1){
							viewPhoto(list.size()-1);
						}else if(id>0&&id<list.size()-1){
							viewPhoto(id);
						}
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
	       mImageButton_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ShowPhoto.this.finish();
			}
		});
	       
	       listPhoto(folder_name);
	       viewPhoto(id);
	       judge();
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
	
	
	//初始化UI对象
	public void findViews(){
		mTextView_name = (TextView) findViewById(R.id.TextView_name);
		mTextView_time = (TextView) findViewById(R.id.TextView_time);
		
		mImageView = (ImageView) findViewById(R.id.ImageView_photo);
		
		mImageButton_pre = (ImageButton) findViewById(R.id.ImageBtn_pre);
		mImageButton_next = (ImageButton) findViewById(R.id.ImageBtn_next);
		mImageButton_delete = (ImageButton) findViewById(R.id.ImageBtn_delete);
		mImageButton_play = (ImageButton) findViewById(R.id.ImageBtn_play);
		mImageButton_back = (ImageButton) findViewById(R.id.ImageBtn_back);
		
		mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_nor));
		mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_nor));
		mImageButton_delete.setBackgroundDrawable(getResources().getDrawable(R.drawable.delete_nor));
		mImageButton_play.setBackgroundDrawable(getResources().getDrawable(R.drawable.play_nor));
		mImageButton_back.setBackgroundDrawable(getResources().getDrawable(R.drawable.back_nor));
	}
	
	public boolean  isPhoto(String name){
		name=name.substring(name.indexOf(".")+1);
		if(name.equalsIgnoreCase("jpg")||name.equalsIgnoreCase("png")||name.equalsIgnoreCase("gif")){
			return true;
		}else{
			return false;
		}
	}
	
	public void listPhoto(String folder_name){
		list.clear();
		File file=new File(PATH+folder_name);
		File[] files=file.listFiles();
		if(files.length>0){
			for(File f:files){
				if(f.isFile()&&isPhoto(f.getName())){
					list.add(f.getName());
				}
			}
		}
	}
	public String formatLength(long length){
		String len="";
		if(length<1024){
			len=length+"B";
		}else if(length<1024*1024){
			len=(length/1024)+"K";
		}else if(length<1024*1024*1024){
			len=String.valueOf(length/1024.0/1024.0);
			if(len.contains(".")){
				len=len.substring(0,len.indexOf("0")+2)+"M";
			}
		}
		return len;
	}
	
	public void mMakeText(String string,boolean bool){
		if(bool){
			Toast.makeText(ShowPhoto.this, string, Toast.LENGTH_LONG).show();
		}else{
			Toast.makeText(ShowPhoto.this, string, Toast.LENGTH_LONG).show();
			
		}
	}
	
	public void viewPhoto(int id){
		filePath=PATH+folder_name+File.separator+list.get(id);
		Bitmap bm=BitmapFactory.decodeFile(filePath);
		mImageView.setImageBitmap(bm);
		mTextView_name.setText(list.get(id)+"("+(id+1)+"/"+list.size()+")");
		File file=new File(filePath);
		long time=file.lastModified();
		long length=file.length();
		Date date=new Date(time);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
		mTextView_time.setText("上传于"+sdf.format(date)+"/大小"+formatLength(length));
	}
	
	
	public void judge(){
		if(list.size()==1){
			mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_dis));
    		mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_dis));
    		mImageButton_pre.setEnabled(false);
    		mImageButton_next.setEnabled(false);
		}else if(list.size()>1){
			if(id==0){
				mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_dis));
        		mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_nor));
        		mImageButton_pre.setEnabled(false);
        		mImageButton_next.setEnabled(true);
			}
			if(id==list.size()-1){
				mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_nor));
    			mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_dis));
        		mImageButton_pre.setEnabled(true);
        		mImageButton_next.setEnabled(false);
			}
			if(id>0&&id<list.size()-1){
				mImageButton_pre.setBackgroundDrawable(getResources().getDrawable(R.drawable.pre_nor));
    			mImageButton_next.setBackgroundDrawable(getResources().getDrawable(R.drawable.next_nor));
        		mImageButton_pre.setEnabled(true);
        		mImageButton_next.setEnabled(true);
			}
		}
	}
}
