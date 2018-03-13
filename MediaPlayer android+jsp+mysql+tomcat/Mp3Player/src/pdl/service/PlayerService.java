package pdl.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Queue;

import pdl.lrc.LrcProcessor;
import pdl.model.Mp3Info;
import pdl.mp3player.AppConstant;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class PlayerService extends Service {

	private MediaPlayer mediaPlayer = null;
	private boolean isPlaying = false;
	private boolean isPause = false;
	private boolean isReleased = false;
	
	private ArrayList<Queue> queues=null;
	private Handler handler=new Handler();
	private UpdateTimeCallback updateTimeCallback=null;
	private long begin=0;
	private long nextTimeMill=0;
	private long currentTimeMill=0;
	private String message=null;
	private long pauseTimeMills=0;
	
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("playservice");
		Mp3Info mp3Info = (Mp3Info) intent.getSerializableExtra("mp3Info");
		//String musicname=mp3Info.getMp3Name();
		//System.out.println("startcom:"+musicname);
		int MSG = intent.getIntExtra("MSG", 0);
		if (mp3Info != null) {
			if (MSG == AppConstant.PLAY_MSG) {
				System.out.println("playmsg");
				System.out.println("mp3info "+mp3Info.getMp3Name());
				play(mp3Info);
			}
		} else {
			if (MSG == AppConstant.PAUSE_MSG) {
				pause();
			} else if (MSG == AppConstant.STOP_MSG) {
				stop();
			}
		}
//		String message=intent.getStringExtra("message");
//		if(message.equals("play")){
//			play(mp3Info);
//		}
		return super.onStartCommand(intent, flags, startId);
	}

	private void play(Mp3Info mp3Info) {
		if (!isPlaying) {
			Toast.makeText(this, "play", 1).show();
			System.out.println("play");
			prepareLrc(mp3Info.getLrcName());
			
			System.out.println("lrc:"+mp3Info.getLrcName());
			begin=System.currentTimeMillis();
			handler.postDelayed(updateTimeCallback, 5);
			String path = getMp3Path(mp3Info);
			mediaPlayer = MediaPlayer.create(this, Uri.parse("file://" + path));
			mediaPlayer.setLooping(false);
			mediaPlayer.start();
			isPlaying = true;
			isReleased = false;
		}
	}

	private void pause() {
		if (mediaPlayer != null) {
			if (!isReleased) {
				if (!isPause) {
					handler.removeCallbacks(updateTimeCallback);
					pauseTimeMills=System.currentTimeMillis();
					mediaPlayer.pause();
					isPause = true;
					isPlaying = true;
				} else {
					handler.postDelayed(updateTimeCallback,5); 
					begin=System.currentTimeMillis()-pauseTimeMills+begin;
					mediaPlayer.start();
					isPause = false;
				}
			}
		}
	}

	private void stop() {
		if (mediaPlayer != null) {
			if (isPlaying) {
				if (!isReleased) {
					mediaPlayer.stop();
					mediaPlayer.release();
					handler.removeCallbacks(updateTimeCallback);
					isReleased = true;
				}
				isPlaying = false;
			}
		}
	}

	private String getMp3Path(Mp3Info mp3Info) {
		String sdCardRootString = Environment.getExternalStorageDirectory()
				.getAbsolutePath();
		String path = sdCardRootString + File.separator + "mp3"
				+ File.separator + mp3Info.getMp3Name();
		return path;
	}

	
	class UpdateTimeCallback implements Runnable{
		ArrayList<Queue> queues=null;
		Queue times;
		Queue messages;
		public UpdateTimeCallback(ArrayList<Queue> queues){
		this.queues=queues;
		times=queues.get(0);
		System.out.println(times);
		messages=queues.get(1);
		}
		/* (non-Javadoc)
		* @see java.lang.Runnable#run()
		*/
		@Override
		public void run() {
		long offeset=System.currentTimeMillis()-begin;
		//System.out.println(offeset);
		if (currentTimeMill==0) {
		nextTimeMill=(Long) times.poll();
		message=(String) messages.poll();
		}
		if (offeset>=nextTimeMill) {
		Intent intent=new Intent();
		intent.setAction(AppConstant.LRC_MESSAGE_ACTION);
		intent.putExtra("lrcMessage", message);
		sendBroadcast(intent);
		message=(String) messages.poll();
		if(times.size()>0){
		long time=(Long) times.poll();
		System.out.println(time);
		
			nextTimeMill=time;
		}
		else
			nextTimeMill=3500;
		}
		currentTimeMill+=10;
		handler.postDelayed(updateTimeCallback, 10);
		}
	}
	
	private void prepareLrc(String lrcName) {
		try {
		InputStream inputStream=new FileInputStream(
		Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+
		"mp3"+File.separator+lrcName);
		System.out.println("lrcpath:"+Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+
		"mp3"+File.separator+lrcName);
		LrcProcessor lrcProcessor=new LrcProcessor();
		queues=lrcProcessor.process(inputStream);
		updateTimeCallback=new UpdateTimeCallback(queues);
		begin=0;
		currentTimeMill=0;
		nextTimeMill=0;
		} catch (FileNotFoundException e) {
		e.printStackTrace();
		}
		}
}
