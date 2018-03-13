<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="MyClass.DBconn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ScoreCount.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  	DBconn db=new DBconn();
  	String sql = "select * from Score";
			ResultSet rs = db.executeQuery(sql);
		int i=0,jige=0;
		float sum=0,max=-100,min=999;
		float score;
		while(rs.next()){
			i++;
			score=rs.getFloat("Score");
			sum+=score;
			if(max<score)
				max=score;
			if(min>score)
				min=score;
			if(score>60)
				jige++;
		}
		rs.close();
		float avg=sum/i;
		jige*=100;
		
		float jigelv=jige/i;
   %>
    平均分:<%=avg %>
    最高分:<%=max %>
    最低分:<%=min %>
    及格率:<%=jigelv %>%
  </body>
</html>
