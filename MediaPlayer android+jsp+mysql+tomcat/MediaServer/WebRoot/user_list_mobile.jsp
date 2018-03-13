<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

		<title>用户管理</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui">
		<link rel="stylesheet" href="css/app.min.css">

	</head>

	<body>
		<div class="app-page" data-page="user_list">
			<div class="app-topbar teal">
				<div class="app-button left" data-back data-autotitle onclick="location.href='index_mobile.html'">
					主页
				</div>
				<div class="app-title">
					用户管理
				</div>
			</div>
			<div class="app-content">
				<p class="app-section">
					用户列表
				</p>
				<jsp:useBean id="db" class="pdl.UserTableDao" scope="page" />
				<%
					String sql = "select * from chat";
					java.sql.ResultSet rs = db.queryTalkmofu(sql); //执行查询，得到数据集
					String id, username, password, nickname, sex, birthday, city;
					while (rs.next()) { //如果能读到数据
						id = rs.getString("ID");
						username = rs.getString("username");
						password = rs.getString("password");
						nickname = rs.getString("nickname");
						sex = rs.getString("sex");
						birthday = rs.getString("birthday");
						city = rs.getString("city");
				%>
				<div class="app-section">
					<div>
						账号：<%=username%><br>
						密码：<%=password%><br>
						昵称：<%=nickname%><br>
						性别：<%=sex%><br>
						生日：<%=birthday%><br>
						城市：<%=city%>
					</div>
					<div class="right app-button red" onclick="deleteUser('<%=id%>');">
						删除
					</div>
				</div>
				<%
					}
					rs.close();
				%>
			</div>
		</div>
		<script src="js/zepto.js"></script>
		<script src="js/app.min.js"></script>
		<script>
	App.load('user_list');
	function deleteUser(id){
		App.dialog({title:"确定删除吗？",okButton:"确定",cancelButton:"取消"}, function (ok) {
  			if(ok){
  				$.post("servlet/DeleteUserFormWeb",{id:id}, function(data){
  					App.dialog({title:data,okButton:"确定"}, function(){
  						window.location.reload();
					});
				});
			}
		});
	}
</script>
	</body>
</html>
