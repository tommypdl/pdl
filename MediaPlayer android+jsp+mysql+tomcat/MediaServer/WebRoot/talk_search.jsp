<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
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
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
  		<meta name="msapplication-tap-highlight" content="no">

		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/materialize.min.js"></script>
		<link href="css/ghpages-materialize.css" type="text/css"
			rel="stylesheet" media="screen,projection">
		<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
			rel="stylesheet">
	</head>

	<%
		String keyword = request.getParameter("keyword");
		if (keyword != null) {
			keyword = new String(keyword.getBytes("ISO-8859-1"), "utf-8");
		}
	%>

	<body>
		<%@ include file="header_navi.html"%>

		<script type="text/javascript">
	$("#page_title").text("评论管理");
	function deleteTalk(id) {
		$("#button_cofirm").click(function(){
			Materialize.toast("正在提交……", 1500);
			$.post("servlet/DeleteTalkFormWeb", {id:id}, function(data) {
				Materialize.toast(data, 1500, '', function() {
					window.location.reload();
				});
			});
		});
		$("#delete_modal").openModal();
	}
</script>

		<main>
		<div class="container">
			<div class="row">
				<div class="col s12 m9 l10">

					<div id="usage" class="row scrollspy">
						<ul class="collection with-header">
							<li class="collection-header">
								<h4>
									搜索评论
								</h4>
						</ul>
						<nav>
						<div class="nav-wrapper">
							<form>
								<div class="input-field">
									<input id="search" type="search" name="keyword" required
										value="<%=keyword == null ? "" : keyword%>">
									<label for="search">
										<i class="material-icons">search</i>
									</label>
									<i class="material-icons">close</i>
								</div>
							</form>
						</div>
						</nav>
						<%
							if(keyword!=null){
						%>
						<table class="highlight">
							<thead>
								<tr>
									<th data-field="id">
										昵称
									</th>
									<th data-field="name">
										音乐
									</th>
									<th data-field="price">
										评论
									</th>
									<th data-field="price">
										时间
									</th>
									<th data-field="price">
										编辑
									</th>
									<th data-field="price">
										删除
									</th>
								</tr>
							</thead>
							<tbody>
								<jsp:useBean id="db" class="pdl.UserTableDao" scope="page" />
								<%
									String sql = "select * from talk where nickname like '%" + keyword
											+ "%' or musicname like '%" + keyword
											+ "%' or text like '%" + keyword + "%' or time like '%"
											+ keyword + "%'";
									ResultSet rs = db.queryTalkmofu(sql);
									String id, nickname, text, musicname, time;
									while (rs.next()) { //如果能读到数据
										id = rs.getString("ID");
										nickname = rs.getString("nickname");
										text = rs.getString("text");
										musicname = rs.getString("musicname");
										time = rs.getString("time");
								%>
								<tr>
									<td>
										<%=nickname%>
									</td>
									<td>
										<%=musicname%>
									</td>
									<td>
										<%=text%>
									</td>
									<td>
										<%=time%>
									</td>
									<td>
										<a class="btn"
											onclick="location.href='talk_edit.jsp?id=<%=id%>';">编辑</a>
									</td>
									<td>
										<a class="btn" onclick="deleteTalk('<%=id%>');">删除</a>
									</td>
								</tr>
								<%
									}
									rs.close();
								%>
							</tbody>
						</table>
						<div id="delete_modal" class="modal" dismissible="true">
    						<div class="modal-content">
      						<h3>确定删除吗？</h3>
    						</div>
    						<div class="modal-footer">
     				 		<a href="javascript:void(0);" class="modal-action modal-close waves-effect waves-green btn-flat" id="button_cofirm">确定</a>
     				 		<a href="javascript:void(0);" class="modal-action modal-close waves-effect waves-red btn-flat">取消</a>
    						</div>
  						</div>
						<%
							}
						%>
					</div>
				</div>

			</div>
		</div>

		</main>
	</body>
</html>
