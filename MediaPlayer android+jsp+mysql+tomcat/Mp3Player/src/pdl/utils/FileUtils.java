package pdl.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import pdl.model.Mp3Info;


import android.os.Environment;

public class FileUtils {
	
		private String  SDCardRoot;
		public FileUtils() {
		//�õ���ǰ�ⲿ�洢�豸��Ŀ¼
		SDCardRoot=Environment.getExternalStorageDirectory().getAbsolutePath()
		+ File.separator;
		}
		/**
		*��SD���ϴ����ļ�

		 
		 
		*
		*  @throws  IOException
		*/
		public File createFileInSDCard(String fileName,String dir)  throws
		IOException {
		File file=   new File(SDCardRoot+dir+File.separator+fileName);
		System.out.println("file---->" + file);
		file.createNewFile();
		return  file;
		}
		/**
		*��SD���ϴ���Ŀ¼
		*
		*  @param  dirName
		*/
		public File creatSDDir(String dir) {
		File dirFile =  new File(SDCardRoot + dir + File.separator);
		System.out.println(dirFile.mkdirs());
		return  dirFile;
		}
		/**
		*�ж�SD���ϵ��ļ����Ƿ����
		*/
		public  boolean isFileExist(String fileName,String path){
		File file=  new File(SDCardRoot+path+File.separator+fileName);
		return  file.exists();
		}
		/**
		*��һ��InputStream���������д�뵽SD����
		*/
		public File write2SDFromInput(String path,String
		fileName,InputStream input){
		File file =  null;
		OutputStream output =  null;
		try{
		creatSDDir(path);
		file = createFileInSDCard(fileName, path);
		output =  new  FileOutputStream(file);
		byte buffer [] =  new  byte[4 * 1024];
		int temp ;
		while((temp = input.read(buffer)) != -1){
		output.write(buffer,0,temp);

		 
		 
		}
		output.flush();
		}
		catch(Exception e){
		e.printStackTrace();
		}
		finally{
		try{
		output.close();
		}
		catch(Exception e){
		e.printStackTrace();
		}
		}
		return  file;
		}
		
		public List<Mp3Info> getMp3Files(String path){
			List<Mp3Info> mp3Infos=new LinkedList<Mp3Info>();
			String sdCardRoot=Environment.getExternalStorageDirectory().getAbsolutePath();
			File file=new File(sdCardRoot+File.separator+path);
			File[] files=file.listFiles();
			for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith("mp3")) {
			Mp3Info mp3Info=new Mp3Info();
			mp3Info.setMp3Name(files[i].getName());
			mp3Info.setMp3Size(files[i].length()+"");
			mp3Info.setLrcName(mp3Info.getMp3Name().replace(".mp3", ".lrc"));
			mp3Infos.add(mp3Info);
			}
			}
			return mp3Infos;
		}
	}

