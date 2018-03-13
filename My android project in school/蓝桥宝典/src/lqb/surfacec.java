package lqb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class surfacec extends SurfaceView implements SurfaceHolder.Callback,Runnable{
	public surfacec(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		
			msf=this.getHolder();
			msf.addCallback(this);
			this.setFocusable(true);
			loop=true;
		
	}
	boolean loop=false;
	SurfaceHolder msf=null;
	int micount=0;
	int y=50;
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(loop){
			try{
				Thread.sleep(200);
			}catch(Exception e){
				
			}
			synchronized (msf) {
				draw();
			}
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		new Thread(this).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		loop=false;
	}
	public void draw(){
		Canvas canvas=msf.lockCanvas();
		if(msf==null||canvas==null)
			return;
		if(micount<100)
			micount++;
		else
			micount=0;
		
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		
		canvas.drawRect(0, 0, 320, 480, paint);
		switch(micount%4){
		case 0:
			paint.setColor(Color.BLUE);
			break;
		case 1:
			paint.setColor(Color.GREEN);
			break;
		case 2:
			paint.setColor(Color.RED);
			break;
		case 3:
			paint.setColor(Color.YELLOW);
			break;
		default:
			paint.setColor(Color.WHITE);
			break;
		}
		canvas.drawCircle((320-25)/2, y, 50, paint);
		msf.unlockCanvasAndPost(canvas);
	}
}
