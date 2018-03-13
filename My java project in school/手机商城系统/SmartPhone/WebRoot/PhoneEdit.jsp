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

		<title>编辑</title>

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
					if (request.getParameter("ID") == null) { //如果地址栏没有传递参数ID
						response.sendRedirect("ScoreList.jsp"); //网页转向
						return;
					}

					String ID = request.getParameter("ID");
					String StudentName = "",stuNo="",score="";

					DBconn db = new DBconn(); //创建一个新的JavaBean实例

					//String dbPath = "jdbc:odbc:banji"; //数据库连接odbc
					String RealPath = getServletContext().getRealPath("\\"); //当前物理路径
					String dbPath = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
							+ RealPath + "Data\\student.mdb"; //数据库连接方式：直接连接Access数据库文件
					//db.setConnStr(dbPath); //设定数据库连接方式

					if (request.getParameter("StudentName") != null) { //如果提交了文本框内容
						StrConvert ch = new StrConvert(); //创建JavaBean ch
						StudentName = request.getParameter("StudentName"); //取得文本框内容
						StudentName = ch.chStr(StudentName, "UTF-8"); //转换编码
						stuNo = request.getParameter("stuNo");
						score = request.getParameter("score");
						if (StudentName.equals("")) { //为空字符串
							out.write("<script lan='ja'>alert('所输入的球队名称为空！请输入球队名称。');"); //警告
							out.write("window.history.back(-1);</script>"); //网页后退
							db.close();
							return;
						}

						String sql = "select * from Score where StudentName='"
								+ StudentName + "' and  ID<>" + ID; //TeamName相同，但TeamID与原来的不相同
						ResultSet rs = db.executeQuery(sql); //执行查询

						if (rs.next()) { //如果能读到数据
							out.write("<script lan='ja'>alert('所输入的球队名称已经存在！请重新输入。');"); //警告
							out.write("window.history.back(-1);</script>"); //网页后退

							rs.close();
							db.close();
							return;
						}

						rs.close();

						sql = "update Score set StudentNo='" + stuNo
								+ "', StudentName='" + StudentName
								+ "',  Score='" + score
								+ "' where ID=" + ID;
						db.executeUpdate(sql); //执行更新

						out.write("<script lan='ja'>alert('更改成功！');"); //警告
						out.write("window.location.href='ScoreList.jsp';</script>"); //网页转向
						db.close();
						return;

					} else { //刚打开此网页

						String sql = "select * from Score where ID=" + ID;
						ResultSet rs = db.executeQuery(sql); //执行查询

						if (!rs.next()) { //如果查询不到数据
							out.write("<script lan='ja'>alert('所要编辑的记录已不存在。');"); //警告
							out.write("window.location.href='ScoreList.jsp';</script>"); //网页转向
							rs.close();
							db.close();
							return;
						}

						StudentName = rs.getString("StudentName");
						stuNo=rs.getString("StudentNo");
						score=rs.getString("Score");
						rs.close();
						db.close();
					}
				%>
				&nbsp;
				<br>
				编辑
				<br>
				<br>
				
				姓名：
				<input type="text" maxlength="30" name="StudentName" value="<%=StudentName%>">
				学号：
				<input type="text" maxlength="30" name="stuNo" value="<%=stuNo%>">
				成绩：
				<input type="text" maxlength="30" name="score" value="<%=score%>">
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
