package pdl.mp3player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;

import android.content.Context;
import android.util.Xml;
import android.widget.Toast;
//此类作用在与客户端向服务器提交数据时用到的的方法
public class NetUtil {
	String addr=AppConstant.IpAddress;
public String loginInfo(String username,String password){
//用GET方法把表单的密码和用户名传过去
	String address=addr+":8080/MediaServer/LoginServlet";
	String path=address+"?"+"username="+username+"&password="+password;
	try {
		URL url=new URL(path);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		int code=conn.getResponseCode();
		if(code==200){
			//并且得到服务器返回来的信息
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte []buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
				
			}
			bos.flush();
			is.close();
			byte []res=bos.toByteArray();
			String result=new String(res);
	
			return result;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
	return null;
	
}

public String getnickname(String username){
	String path=addr+":8080/MediaServer/getNickNameServlet?";
	String path2=path+"username="+username;
	System.out.println(path2);
	try {
		URL url=new URL(path2);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		int code=conn.getResponseCode();
		if(code==200){
			//并且得到服务器返回来的信息
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte []buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
				
			}
			bos.flush();
			is.close();
			byte []res=bos.toByteArray();
			String result=new String(res);
			
			return result;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
	return "pdl";
}
public String addtalk(String nickname,String text,String musicname,String time){
	String path=addr+":8080/MediaServer/AddTalkServlet?";
	String path2=path+"nickname="+nickname+"&text="+text+"&musicname="+musicname+"&time="+time;
	try {
		path2=new String(path2.getBytes("utf-8"),"ISO-8859-1");
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}


	System.out.println(path2);
	try {
		URL url=new URL(path2);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		int code=conn.getResponseCode();
		System.out.println("code:"+code);
		if(code==200){
			//并且得到服务器返回来的信息
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte []buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
				
			}
			bos.flush();
			is.close();
			byte []res=bos.toByteArray();
			String result=new String(res);
			
			return result;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
	return "bad service";
}

public String registInfo(String password,String nickname,String sex,String year,
		String month,String day,String province,String city ){
	String path=addr+":8080/MediaServer/RegistServlet?";
	StringBuffer buffer0=new StringBuffer(path);
	try {
		//把中文字段转码，拼成一个带有表单的地址
		buffer0.append("password=").append(URLEncoder.encode(password,"utf-8"))
		.append("&nickname=").append(URLEncoder.encode(nickname,"utf-8"))
		.append("&sex=").append(URLEncoder.encode(sex,"utf-8")).append("&year=")
		.append(URLEncoder.encode(year,"utf-8")).append("&month=")
		.append(URLEncoder.encode(month,"utf-8")).append("&day=")
		.append(URLEncoder.encode(day,"utf-8")).append("&province=")
		.append(URLEncoder.encode(province,"utf-8")).append("&city=")
		.append(URLEncoder.encode(city,"utf-8"));
	
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}                                    
   String newpath=new String(buffer0);
 
	try {
		URL url=new URL(newpath);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		//特别注意下面这句，不设置这句就无法接收服务器发送回来的消息。。。当初因为少了这一句代码卡了我快两天时间
		conn.setDoInput(true);
		int code=conn.getResponseCode();
		if(code==200){
			 
			//并且得到服务器的回应
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte []buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			bos.flush();
			is.close();
			byte res[]=bos.toByteArray();
			String result=new String(res);
			return result;
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

public String queryTalk(String musicname){
	String path=addr+":8080/MediaServer/TalkServlet?";
	String path2=path+"musicname="+musicname;
	try {
		URL url=new URL(path2);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		
		conn.setDoInput(true);
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		int code=conn.getResponseCode();
		if(code==200){
			//并且得到服务器返回来的信息
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte []buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
				
			}
			bos.flush();
			is.close();
			byte []res=bos.toByteArray();
			String result=new String(res);
			//result = URLDecoder.decode(result, "UTF-8");  
			//result=new String(result.getBytes("ISO-8859-1"),"utf-8");
			String[] ss=result.split("huanhang");
			StringBuilder sb=new StringBuilder();
			for(int i=ss.length-1;i>=0;i--){
				sb.append(ss[i]+"\n");
				
			}
			return sb.toString();
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
	return "can not find object";
}

public String setOnlineState(String ID){

	//此方法作用在于处理用户上线 下线隐身状态，其中，mysql中的online列 0代表下线，1代表上线，2代表隐身
	String path=addr+":8080/AaServer/SetOnlineStateServlet";
	
	
	try {
		URL url=new URL(path);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setReadTimeout(5000);
		conn.setDoOutput(true);
		conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
		conn.setRequestProperty("content-length", ID.length()+"");
		OutputStream out=conn.getOutputStream();
		out.write(ID.getBytes());
		out.flush();
		out.close();
		int code=conn.getResponseCode();
		if(code==200){
			//并且得到服务器的回应
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte []buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			bos.flush();
			is.close();
			byte res[]=bos.toByteArray();
			String result=new String(res);
			return result;
			
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
//查询QQ号码是否存在，如果存在，返回这个QQ号码的信息，如果不存在，返回一个没有该账号的信息
public User queryNumberExist(String username){
	String address=addr+":8080/AaServer/QueryNumberExistServlet?username="+username;
	try {
		URL url=new URL(address);
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		conn.setDoInput(true);
		int code=conn.getResponseCode();
		if(code==200){
			User user=new User();
			InputStream is=conn.getInputStream();
			
			XmlPullParser xpp=Xml.newPullParser();
		    xpp.setInput(is, "utf-8");
		//解析xml文件
				int type=xpp.getEventType();
				while(type!=XmlPullParser.END_DOCUMENT){
					if(type==XmlPullParser.START_TAG){
						if("id".equals(xpp.getName())){
						user.setId(xpp.nextText());
						}
						else if("username".equals(xpp.getName())){
							user.setUsername(xpp.nextText());
						}
						else if("password".equals(xpp.getName())){
							user.setPassword((xpp.nextText()));
						}
						else if("online".equals(xpp.getName())){
							user.setOnline((xpp.nextText()));
						}
						else if("nickname".equals(xpp.getName())){
							user.setNickname((xpp.nextText()));
						}
						else if("sex".equals(xpp.getName())){
							user.setSex((xpp.nextText()));
						}
						else if("birthday".equals(xpp.getName())){
							user.setBirthday((xpp.nextText()));
						}
						else if("province".equals(xpp.getName())){
							user.setProvince((xpp.nextText()));
						}
						else if("city".equals(xpp.getName())){
							user.setCity((xpp.nextText()));
						}
						else if("introduce".equals(xpp.getName())){
							user.setIntroduce((xpp.nextText()));
						}
						else if("school".equals(xpp.getName())){
							user.setSchool((xpp.nextText()));
						}
						else if("email".equals(xpp.getName())){
							user.setEmail((xpp.nextText()));
						}
						else if("constellation".equals(xpp.getName())){
							user.setConstellation((xpp.nextText()));
						}
					
					}
					if(type==XmlPullParser.END_TAG){
						if("user".equals(xpp.getName())){
							
						}
					}
					type=xpp.next();
				}
				return user;
			}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	return null;
}
//我想要添加别人为好友，那么就把我的 ID号，我的验证信息,对方的QQ号码   传到服务器，让服务器做处理
public String addFriendNumberToFolder(String ID,String number,String verification){

	String address=addr+":8080/AaServer/AddFriendNumberToFolderServlet?number="+number+"&ID="+ID+"&verification=";
	try {
		StringBuffer buffer0=new StringBuffer(address);
		buffer0.append(URLEncoder.encode(verification,"utf-8"));
		URL url=new URL(buffer0.toString());
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setReadTimeout(5000);
		int code=conn.getResponseCode();
		if(code==200){
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			int len=0;
			byte []buffer=new byte[1024];
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			bos.flush();
			is.close();
			byte res[]=bos.toByteArray();
			String result=new String(res);
			return result;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
	
}
//检测是否有人 要添加自己为好友
public String checkAddFriendMsg(String ID){
	String address=addr+":8080/AaServer/CheckIfHaveAddFriendMsgServlet?ID="+ID;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);
		return result;
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
//通过传进来的ID号，向数据库查询,得到QQ号码
public String findUsernameById(String ID){
	String address=addr+":8080/AaServer/FindUsernameByIdServlet?ID="+ID;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);
		return result;
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
//同意添加好友，把当前登录的帐号名，和同意添加好友的ID号 发送到服务器
public String agreeToAddFriend(String ID,String personUsername,String myId){
	String address=addr+":8080/AaServer/AgreeToAddFriendServlet?ID="+ID+"&username="+personUsername+"&myId="+myId;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);
		return result;
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
//每次在tab2  OnResume的时候调用此方法
public String scanFriendList(String personUsername){
	String address=addr+":8080/AaServer/ScanFriendListServlet?username="+personUsername;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);
		return result;
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
//把自己的id号和图片的名称发送到服务器端相应的文件夹
public String sendPictureNameToFolder(String id,String pictureName){
	String address=addr+":8080/AaServer/SavePictureNameToFolderServlet?id="+id+"&picturename="+pictureName;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);
		return result;
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
public String scanMySelfPortrait(String id){
	String address=addr+":8080/AaServer/ScanMySelfPortraitServlet?id="+id;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
//把自己的id号和QQ号码都发过去
public String scanQQfriendsPortrait(String username){
	String address=addr+":8080/AaServer/ScanFriendsPortraitServlet?username="+username;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
//加载我的信息 到 mydatacard这个界面上
public String loadMyInfoToMyDataCard(String id){
	String address=addr+":8080/AaServer/LoadMyInfoToMyDataCardServlet?id="+id;
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
//在我的资料界面  修改个人信息时候，需要把修改的信息发送到服务器
public String editorMyInfo(String id,String nickname,String sex,String birthday,
		String province,String city,String school,String email,String introduce ){
	
	String path=addr+":8080/AaServer/EditorMyInfoServlet?";
	StringBuffer buf=new StringBuffer(path);
	
	try {
		buf.append("id=").append(id)
		.append("&nickname=").append(URLEncoder.encode(nickname,"utf-8"))
		.append("&sex=").append(URLEncoder.encode(sex,"utf-8"))
		.append("&birthday=").append(birthday)
		.append("&province=").append(URLEncoder.encode(province,"utf-8"))
		.append("&city=").append(URLEncoder.encode(city,"utf-8"))
		.append("&school=").append(URLEncoder.encode(school,"utf-8"))
		.append("&email=").append(URLEncoder.encode(email,"utf-8"))
		.append("&introduce=").append(URLEncoder.encode(introduce,"utf-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     try {
		URL url=new URL(buf.toString());
		System.out.println("netutil:"+buf.toString());
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();
		conn.setReadTimeout(5000);
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		//setdooutput 不能乱加啊！！！！！
		int code=conn.getResponseCode();
		if(code==200){
			InputStream is=conn.getInputStream();
			ByteArrayOutputStream bos=new ByteArrayOutputStream();
			byte buffer[]=new byte[1024];
			int len=0;
			while((len=is.read(buffer))!=-1){
				bos.write(buffer,0,len);
			}
			bos.flush();
			is.close();
			byte res[]=bos.toByteArray();
			String result=new String(res);
			return result;
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	return null;
	
}
public String sendMyMessageToFriend(String myid,String friendID){
	String address=addr+":8080/AaServer/SendMyMessageToFriendServlet?myid="+myid+"&friendID="+friendID;
	
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}

//扫描服务器上的文件夹是否有好友给我发消息
public String scanIfHaveMessage(String myid){
	String address=addr+":8080/AaServer/ScanIfHaveNewMessageServlet?myid="+myid;
	
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
public String queryNicknameById(String friendid){
	String address=addr+":8080/AaServer/QueryNiakNameByIDServlet?friendid="+friendid;
	
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
public String queryOnlineStateById(String friendid){
	String address=addr+":8080/AaServer/QueryOnlineStateServlet?friendid="+friendid;
	
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
public String appearFriendMsgOnTab1(String myid,String friendid,String message){
	String address=addr+":8080/AaServer/AppearFriendMsgOnTab1Servlet?friendid="+friendid+"&myid="+myid+"&message=";
	StringBuffer buff=new StringBuffer(address);
	try {
		buff.append(URLEncoder.encode(message,"utf-8"));
	} catch (UnsupportedEncodingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try{
	URL url=new URL(buff.toString());
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
public String scanHistoryMsg(String myid,String friendid){
	String address=addr+":8080/AaServer/ScanHistroyMsgServlet?friendid="+friendid+"&myid="+myid;
	
	try{
	URL url=new URL(address);
	HttpURLConnection conn=(HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setReadTimeout(5000);
	int code=conn.getResponseCode();
	if(code==200){
		InputStream is=conn.getInputStream();
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int len=0;
		byte []buffer=new byte[1024];
		while((len=is.read(buffer))!=-1){
			bos.write(buffer,0,len);
		}
		bos.flush();
		is.close();
		byte res[]=bos.toByteArray();
		String result=new String(res);

		return result;
		
	}
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return null;

	
}
}
