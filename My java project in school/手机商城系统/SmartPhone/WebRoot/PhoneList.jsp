<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="java.sql.ResultSet"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ScoreList.jsp' starting page</title>
    
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
			//String dbPath = "jdbc:odbc:banji"; //数据库连接odbc
			String RealPath = getServletContext().getRealPath("\\"); //当前物理路径
			String dbPath = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
					+ RealPath + "Data\\student.mdb"; //数据库连接方式：直接连接Access数据库文件
			//db.setConnStr(dbPath); //设定数据库连接方式

			String sql = "select * from Score";
			ResultSet rs = db.executeQuery(sql); //执行查询，得到数据集
		%>

		<p>
			<div align="center">
				<a href="index.jsp">返回首页</a> &nbsp;&nbsp;
				<a href="ScoreInsert.jsp">添加成绩</a>
			</div>
		</p>
		<br />
		<br />
		<table width="400" cellpadding="5" border="0" align="center">
			<tbody>
				<tr bgcolor="#EEEEFF">
					<td>
						学号
					</td>
					<td>
						姓名
						<br>
					</td>
					<td>
						成绩
						<br>
					</td>
					<td>
						编辑
						<br>
					</td>
					<td>
						删除
						<br>
					</td>
				</tr>
				<%
					int i = 0;
					String ID,stuNo, Name,score;

					while (rs.next()) { //如果能读到数据
						i++; //序号
						ID = rs.getString("ID");
						stuNo=rs.getString("StudentNo");
						
						Name = rs.getString("StudentName");
						score=rs.getString("score");
				%>
				<tr>
					
					<td>
						<%=stuNo%>
					</td>
					<td>
						<%=Name%>
					</td>
					<td>
						<%=score%>
					</td>
					<td>
						<a href="ScoreEdit.jsp?ID=<%=ID%>">编辑</a>
					</td>
					<td>
						<a href="ScoreDelete.jsp?ID=<%=ID%>"
							onclick="return confirm('确定要删除吗？');">删除</a>
					</td>
				</tr>
				<%
					}

					rs.close(); //关闭数据集
					db.close(); //关闭数据库连接
				%>
			</tbody>
		</table>
  </body>
</html>
