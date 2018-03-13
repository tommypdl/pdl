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

		<title>评论管理</title>

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
		<div class="app-page" data-page="talk_list">
			<div class="app-topbar teal">
				<div class="app-button left" data-back data-autotitle
					onclick="location.href='index_mobile.html'">
					主页
				</div>
				<div class="app-title">
					评论管理
				</div>
			</div>
			<div class="app-content">
				<p class="app-section">
					评论列表
				</p>
				<jsp:useBean id="db" class="pdl.UserTableDao" scope="page" />
				<%
					java.sql.ResultSet rs = db.queryallTalk();
					String id, nickname, text, musicname, time;
					while (rs.next()) { //如果能读到数据
						id = rs.getString("ID");
						nickname = rs.getString("nickname");
						text = rs.getString("text");
						musicname = rs.getString("musicname");
						time = rs.getString("time");
				%>
				<div class="app-section">
					<div>
						昵称：<%=nickname%><br>
						音乐：<%=musicname%><br>
						评论：<%=text%><br>
						时间：<%=time%><br>
					</div>
					<div class="left app-button blue"
						onclick="location.href='talk_edit_mobile.jsp?id=<%=id%>';">
						编辑
					</div>
					<div class="right app-button red" onclick="deleteTalk('<%=id%>');">
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
	App.load("talk_list");
	function deleteTalk(id) {
		App.dialog({
			title : "确定删除吗？",
			okButton : "确定",
			cancelButton : "取消"
		}, function(ok) {
			if (ok) {
				$.post("servlet/DeleteTalkFormWeb", {
					id : id
				}, function(data) {
					App.dialog({
						title : data,
						okButton : "确定"
					}, function() {
						window.location.reload();
					});
				});
			}
		});
	}
</script>
	</body>
</html>
