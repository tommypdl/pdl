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

		<title>后台管理系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
  		<meta name="msapplication-tap-highlight" content="no">
  		
  		<script type="text/javascript">
  		function uaredirect(f){try{if(document.getElementById("bdmark")!=null){return}var b=false;if(arguments[1]){var e=window.location.host;var a=window.location.href;if(isSubdomain(arguments[1],e)==1){f=f+"/#m/"+a;b=true}else{if(isSubdomain(arguments[1],e)==2){f=f+"/#m/"+a;b=true}else{f=a;b=false}}}else{b=true}if(b){var c=window.location.hash;if(!c.match("fromapp")){if((navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i))){location.replace(f)}}}}catch(d){}}function isSubdomain(c,d){this.getdomain=function(f){var e=f.indexOf("://");if(e>0){var h=f.substr(e+3)}else{var h=f}var g=/^www\./;if(g.test(h)){h=h.substr(4)}return h};if(c==d){return 1}else{var c=this.getdomain(c);var b=this.getdomain(d);if(c==b){return 1}else{c=c.replace(".","\\.");var a=new RegExp("\\."+c+"$");if(b.match(a)){return 2}else{return 0}}}};
  		uaredirect("index_mobile.html"); /* 如果用手机访问网站则跳转到wap网页 */
  		</script>

		<link href="css/ghpages-materialize.css" type="text/css"
			rel="stylesheet" media="screen,projection">
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/materialize.min.js"></script>
	</head>

	<body>
		<%@ include file="header_navi.html"%>
		<script type="text/javascript">
	$("#page_title").text("后台管理系统 - 主页");
</script>
		<main>
		<div class="container">
			<div class="row">
				<div class="col s12 m9 l10">

					<div id="usage" class="row scrollspy">
						<p class="caption">
						<p>
							<a class="waves-effect waves-light btn-large"
								href="user_list.jsp">用户管理</a>
						</p>
						<p>
							<a class="dropdown-button btn btn-large" href="#"
								data-activates="talk_dropdown">评论管理</a>
						</p>
						<ul id="talk_dropdown" class="dropdown-content">
							<li>
								<a href="talk_list.jsp">评论列表</a>
							</li>
							<li>
								<a href="talk_search.jsp">搜索评论</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="talk_insert.jsp">添加评论</a>
							</li>
						</ul>
						<p>
							<a class="waves-effect waves-light btn-large"
								href="file_upload.jsp">上传文件</a>
						</p>
						<br>
					</div>
				</div>

			</div>
		</div>

		</main>
	</body>
</html>
