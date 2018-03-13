package dataclass;
import java.text.*;
import java.util.*;
public class DateTimeUtil {
	private int hour;
	private int minute;
	private String day;
	public DateTimeUtil(String dateTimeStr){
		StringTokenizer stDateTime=new StringTokenizer(dateTimeStr," ");
		day=stDateTime.nextToken();
		String timeStr=stDateTime.nextToken();
		StringTokenizer stTime=new StringTokenizer(timeStr,":");
		hour=Integer.parseInt(stTime.nextToken());
		minute=Integer.parseInt(stTime.nextToken());
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
}
