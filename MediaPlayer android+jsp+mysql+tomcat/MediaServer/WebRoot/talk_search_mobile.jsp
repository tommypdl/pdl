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

	<%
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			keyword = new String(keyword.getBytes("ISO-8859-1"), "utf-8");
		}
	%>

	<body>
		<div class="app-page" data-page="talk_search">
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
					评论搜索
				</p>
				<div class="app-section">
					<form method="get">
						<input type="search" class="app-input" name="keyword"
							value="<%=keyword == null ? "" : keyword%>" id="search_input">
					</form>
				</div>
				<%
					int count=0;
					if (keyword != null && keyword.length() != 0) {
						pdl.UserTableDao db = new pdl.UserTableDao();
						String sql = "select * from talk where nickname like '%"
								+ keyword + "%' or musicname like '%" + keyword
								+ "%' or text like '%" + keyword + "%' or time like '%"
								+ keyword + "%'";
						java.sql.ResultSet rs = db.queryTalkmofu(sql);
						String id, nickname, text, musicname, time;
						while (rs.next()) { //如果能读到数据
							count++;
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
					}
				%>
			</div>
		</div>
		<script src="js/zepto.js"></script>
		<script src="js/app.min.js"></script>
		<script>
	App.load("talk_search");
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
	<%
		if(keyword != null && keyword.length() != 0){out.write("$('#search_input').after('<p>"+count+"条搜索结果</p>')");}
	%>
</script>
	</body>
</html>
