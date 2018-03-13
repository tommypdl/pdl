package pdl.service;

import pdl.download.HttpDownloader;
import pdl.model.Mp3Info;
import pdl.mp3player.AppConstant;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DownloadService extends Service{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Mp3Info mp3Info = (Mp3Info)intent.getSerializableExtra("mp3Info");
		DownloadThread downloadThread = new DownloadThread(mp3Info);
		//�������߳�
		Thread thread = new Thread(downloadThread);
		thread.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	class DownloadThread implements Runnable{

		private Mp3Info mp3Info = null;
		public DownloadThread(Mp3Info mp3Info){
		this.mp3Info = mp3Info;
		}
		@Override
		public void run() {
		//���ص�ַhttp://192.168.1.107:8081/mp3/a1.mp3
		//����MP3�ļ������֣��������ص�ַ
		String mp3Url = AppConstant.IpAddress+":8080/mp3/" + mp3Info.getMp3Name();
		String mp3lrc = AppConstant.IpAddress+":8080/mp3/" + mp3Info.getLrcName();
		//���������ļ����õĶ���
		HttpDownloader httpDownloader = new HttpDownloader();
		//���ļ��������������洢��SDCard����
		int result = httpDownloader.downFile(mp3Url, "mp3/", mp3Info.getMp3Name());
		int result2=httpDownloader.downFile(mp3lrc, "mp3/", mp3Info.getLrcName());
		String resultMessage = null;
		if(result == -1){
		resultMessage = "����ʧ��";
		}
		else if(result == 0){
		resultMessage = "�ļ��Ѿ����ڣ�����Ҫ�ظ�����";
		}
		else if(result == 1){
		resultMessage = "�ļ����سɹ�";
		}
		//ʹ��Notification��ʾ�ͻ����ؽ��
		}
	}
}
