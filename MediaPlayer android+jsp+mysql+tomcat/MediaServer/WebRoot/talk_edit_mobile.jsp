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
		String id=request.getParameter("id");
		if(id==null||id.length()==0){
			response.sendRedirect("talk_list.jsp");
			return;
		}
		pdl.UserTableDao db = new pdl.UserTableDao();
		java.sql.ResultSet rs=null;
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
	<div class="app-page" data-page="talk_edit">
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
					修改评论
				</p>
				<div class="app-section">
					<form id="talk_form">
					<input type="hidden" value="<%=id %>" name="id">
						<ul class="app-list">
						<li><input class="app-input" placeholder="昵称" id="nickname" name="nickname" value="<%=nickname %>"></li>
						<li><input class="app-input" placeholder="歌曲" id="musicname" name="musicname" value="<%=nickname %>"></li>
						<li><textarea class="app-input" placeholder="评论" id="talk_text" name="text"><%=text %></textarea></li>
						<li><div class="app-button green" onclick="postInfo();">提交</div>
						</ul>
					</form>
				</div>
			</div>
		</div>
		<script src="js/zepto.js"></script>
		<script src="js/app.min.js"></script>
		<script>
	App.load("talk_edit");
	function showDialog(title,funct){
		App.dialog({title:title,okButton:"确定"},funct);
	}
	function postInfo(){
		if($("#nickname").val()===''){showDialog("请输入昵称");return;}
		if($("#musicname").val()===''){showDialog("请输入歌曲名");return;}
		if($("#talk_text").val()===''){showDialog("请输入评论");return;}
		$.post("servlet/EditTalkFromWeb", $("#talk_form").serialize(),function(data){
			showDialog(data,function(){window.location.href="talk_list_mobile.jsp";});
		});
	}
</script>
	</body>
</html>
