<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="pdl.UserTableDao"%>
<%@page import="java.sql.ResultSet"%>
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
		String id=request.getParameter("id");
		if(id==null||id.length()==0){
			response.sendRedirect("talk_list.jsp");
			return;
		}
		UserTableDao db = new UserTableDao();
		ResultSet rs=null;
		try{
			rs = db.queryTalkbyid(Integer.parseInt(id));
		}catch(Exception e){
			e.printStackTrace();
			response.sendRedirect("talk_list.jsp");
			return;
		}
		String nickname,text,musicname;
		if(rs!=null&&rs.next()){
			nickname = rs.getString("nickname");
			text=rs.getString("text");
			musicname=rs.getString("musicname");
			rs.close();
		}else{
			rs.close();
			response.sendRedirect("talk_list.jsp");
			return;
		}
	%>

	<body>
		<%@ include file="header_navi.html"%>
		<script type="text/javascript">
	$("#page_title").text("评论管理");
	function postInfo() {
		if ($("#nickname").val() === '') {
			Materialize.toast("请输入昵称", 4000);
			return;
		}
		if ($("#musicname").val() === '') {
			Materialize.toast("请输入歌曲", 4000);
			return;
		}
		if ($("#talk_text").val() === '') {
			Materialize.toast("请输入评论", 4000);
			return;
		}
		Materialize.toast("正在提交……", 1500);
		$.post("servlet/EditTalkFromWeb", $("#talk_form").serialize(),
				function(data) {
					Materialize.toast(data, 1500, '', function() {
						window.location.href = "talk_list.jsp";
					});
				});
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
									编辑评论
								</h4>
						</ul>
						<div class="row">
							<form class="col s12" id="talk_form">
							<input type="hidden" value="<%=id %>" name="id">
								<div class="row">
									<div class="input-field col s6">
										<input id="nickname" type="text" class="validate"
											name="nickname" value="<%=nickname %>">
										<label for="nickname">
											昵称
										</label>
									</div>
								</div>
								<div class="row">
									<div class="input-field col s6">
										<input id="musicname" type="text" class="validate"
											name="musicname" value="<%=musicname %>">
										<label for="musicname">
											歌曲
										</label>
									</div>
								</div>
								<div class="row">
									<div class="input-field col s8">
										<textarea id="talk_text" name="text" class="materialize-textarea"><%=text %></textarea>
										<label for="talk_text">
											评论
										</label>
									</div>
								</div>
								<a class="waves-effect waves-light btn" onclick="postInfo();">提交</a>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		</main>
	</body>
</html>
