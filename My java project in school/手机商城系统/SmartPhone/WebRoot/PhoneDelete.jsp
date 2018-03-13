+<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'TeamDelete.jsp' starting page</title>

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
		<jsp:useBean id="db" class="MyClass.DBconn" scope="page"></jsp:useBean>
		<%
			if (request.getParameter("ID") == null) { //如果地址栏没有传递参数ID
				response.sendRedirect("ScoreList.jsp"); //网页转向
				return;
			}

			String ID = request.getParameter("ID");

			//String dbPath = "jdbc:odbc:banji"; //数据库连接odbc
			//String RealPath = getServletContext().getRealPath("\\"); //当前物理路径
			//String dbPath = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
				//	+ RealPath + "Data\\student.mdb"; //数据库连接方式：直接连接Access数据库文件
			//db.setConnStr(dbPath); //设定数据库连接方式

			String sql = "delete from Score where ID=" + ID;
			db.executeUpdate(sql); //执行删除操作	
			db.close(); 

			response.sendRedirect("ScoreList.jsp"); //网页转向
		%>
	</body>
</html>
