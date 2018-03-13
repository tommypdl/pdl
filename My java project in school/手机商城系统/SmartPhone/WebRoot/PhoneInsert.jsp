<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="MyClass.DBconn"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="MyClass.StrConvert"%>
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

		<title>新添</title>

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
		<form method="post">
			<div align="center">
				<%
					String StudentName = "",stuNo="";
					float score=0;
					int id=0;
					DBconn db = new DBconn(); //创建JavaBean db

					//String dbPath = "jdbc:odbc:banji"; //数据库连接odbc
					//String RealPath = getServletContext().getRealPath("\\"); //当前物理路径
					//String dbPath = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
					//		+ RealPath + "Data\\student.mdb"; //数据库连接方式：直接连接Access数据库文件
					//db.setConnStr(dbPath); //设定数据库连接方式

					if (request.getParameter("StudentName") != null) { //如果提交了文本框内容
						StrConvert ch = new StrConvert(); //创建JavaBean ch
						StudentName = request.getParameter("StudentName"); //取得文本框内容
						StudentName = ch.chStr(StudentName, "UTF-8"); //转换编码
						stuNo = request.getParameter("stuNo");
						score = Float.valueOf(request.getParameter("score"));
						if (StudentName.equals("")) { //为空						
							out.write("<script lan='ja'>alert('所输入的球队名称为空！请输入球队名称。');"); //警告
							out.write("window.history.back(-1);</script>"); //网页后退

							db.close();
							return;
						}

						String sql = "select * from Score where StudentName='"
								+ StudentName + "'";
						ResultSet rs = db.executeQuery(sql); //执行查询

						if (rs.next()) { //如果能读到数据
							out.write("<script lan='ja'>alert('所输入的名称已经存在！请重新输入。');"); //警告
							out.write("window.history.back(-1);</script>"); //网页后退

							rs.close();
							db.close();
							return;
						}

						rs.close();

						sql = "insert into Score (StudentNo,StudentName,Score) values ('" + stuNo + "','"+StudentName+"',"+score+")";
						db.executeUpdate(sql); //执行插入操作

						out.write("<script lan='ja'>alert('添加成功！');"); //警告
						out.write("window.location.href='ScoreList.jsp';</script>"); //网页转向
						db.close();
						return;

					}
				%>
				&nbsp;
				<br>
				新添
				<br>
				<br>
				姓名：
				<input type="text" maxlength="30" name="StudentName">
				学号：
				<input type="text" maxlength="30" name="stuNo">
				成绩：
				<input type="text" maxlength="30" name="score">
				<input type="submit" value="提交" name="button1">
				<p>
					<a href="ScoreList.jsp">成绩列表</a>
				</p>
			</div>
			<p>
				&nbsp;
			</p>

		</form>
	</body>
</html>
