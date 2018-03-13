<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="java.sql.*"%>
<%@page import="MyClass.StrConvert"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.net.InetAddress"%>
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

		<title>成绩统计</title>

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
		&nbsp;
		<jsp:useBean id="db" class="MyClass.DBconn" scope="page"></jsp:useBean>
		<%
			//String dbPath = "jdbc:odbc:banji"; //数据库连接odbc
			String RealPath = getServletContext().getRealPath("\\"); //当前物理路径
			String dbPath = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ="
					+ RealPath + "Data\\student.mdb"; //数据库连接方式：直接连接Access数据库文件
			//db.setConnStr(dbPath); //设定数据库连接方式

			String StudentNo = "", StudentName = "";

			if (request.getParameter("StudentName") != null) { //如果提交了文本框内容
				StrConvert ch = new StrConvert(); //创建JavaBean ch
				StudentNo = request.getParameter("StudentNo"); //取得文本框内容
				StudentName = request.getParameter("StudentName"); //取得文本框内容
				StudentName = ch.chStr(StudentName, "UTF-8"); //转换编码
			}

			String sql = "";

			if (!StudentNo.equals("") && !StudentName.equals(""))
				sql = " where StudentNo like '%" + StudentNo
						+ "%' and StudentName like '%" + StudentName + "%' "; //模糊查询
			else if (!StudentNo.equals(""))
				sql = " where StudentNo like '%" + StudentNo + "%' ";
			else if (!StudentName.equals(""))
				sql = " where StudentName like '%" + StudentName + "%' ";

			if (!sql.equals(""))
				sql = "select * from Score " + sql + " order by StudentNo"; //加入查询条件
			else
				sql = "select * from Score order by StudentNo";

			ResultSet rs = db.executeQuery(sql); //执行查询，得到数据集
		%>

		<p>
			<div align="center">
				<a href="index.jsp">返回首页</a> &nbsp;&nbsp;
				<a href="ScoreInsert.jsp">添加成绩</a>
				<br />
				<br />

				<form method="post">
					<p>
						&nbsp;学号：
						<input type="text" name="StudentNo" value="<%=StudentNo%>">
						姓名：
						<input type="text" name="StudentName" value="<%=StudentName%>">
						<input type="submit" name="button1" value="查询">
						<br>
					</p>
				</form>
			</div>
		</p>
		<br />
		<br />
		<table width="400" cellpadding="5" border="0" align="center">
			<tbody>
				<tr bgcolor="#EEEEFF">
					<td>
						序号
					</td>
					<td>
						学号
					</td>
					<td>
						姓名
					</td>
					<td>
						成绩
					</td>
					<td>
						编辑
					</td>
					<td>
						删除
					</td>
				</tr>
				<%
					int i = 0;
					String ID, Score; //, StudentNo, StudentName
					float fScore, Sum = 0, Max = 0, Min = 9999, StudentNum = 0, StudentNum60 = 0;

					while (rs.next()) { //如果能读到数据
						i++; //序号
						ID = rs.getString("ID");
						StudentNo = rs.getString("StudentNo");
						StudentName = rs.getString("StudentName");
						Score = rs.getString("Score");

						fScore = Float.parseFloat(Score); //分数
						if (Max < fScore)
							Max = fScore; //最大值
						if (Min > fScore)
							Min = fScore; //最小值
						if (fScore >= 60)
							StudentNum60++; //及格人数
						StudentNum++; //总人数
						Sum += fScore; //总分
				%>
				<tr>
					<td>
						<%=i%>
					</td>
					<td>
						<%=StudentNo%>
					</td>
					<td>
						<%=StudentName%>
					</td>
					<td>
						<%=Score%>
					</td>
					<td>
						<a href="ScoreEdit.jsp?ID=<%=ID%>">编辑</a>
					</td>
					<td>
						<a href="ScoreDelete.jsp?ID=<%=ID%>"returnconfirm('确定要删除吗？');">删除</a>
					</td>
				</tr>
				<%
					}

					rs.close(); //关闭数据集
					db.close(); //关闭数据库连接

					java.text.DecimalFormat df = new java.text.DecimalFormat("#.0"); //小数位数为1的格式
					String Average = df.format(Sum / StudentNum); //格式化得到平均值
					String JiGeLv = df.format(StudentNum60 / StudentNum * 100); //及格率

				//	String IP = request.getRemoteAddr(); //获取客户端IP
				//	String Desk = IP.substring(IP.lastIndexOf(".") + 1);
				InetAddress inet = InetAddress.getLocalHost();
            System.out.println("本机的ip=" + inet.getHostAddress());
            String ip=inet.getHostAddress();
            String ips=ip.substring(ip.length()-2,ip.length());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //日期时间格式
					String Time = sdf.format(new Date()); //格式化当前时间
				%>
			</tbody>
		</table>
		<div align="center">
			<br>
			平均分：<%=Average%>
			<br>
			最高分：<%=Max%>
			<br>
			最低分：<%=Min%>
			<br>
			及格率：<%=JiGeLv%>%
			<br><br>
			您的座位号：<%=ips %>（IP：<%=ip%>）
			<br>
			当前时间：<%=Time%>
			<br>
		</div>
	</body>
</html>
