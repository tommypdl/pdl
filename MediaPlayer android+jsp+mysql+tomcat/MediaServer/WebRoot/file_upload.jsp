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

		<title>文件上传</title>

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

	<body>

		<%@ include file="header_navi.html"%>
		<script type="text/javascript">
	$("#page_title").text("文件上传");
</script>

		<main>
		<div class="container">
			<div class="row">
				<div class="col s12 m9 l10">

					<div id="usage" class="row scrollspy">
						<ul class="collection with-header">
							<li class="collection-header">
								<h4>
									文件上传
								</h4>
						</ul>
						<div class="row">
							<form action="UploadServlet" method="post"
								class="input-field col s12" enctype="multipart/form-data">
								上传文件：
								<input type="file" name="file" />
								<p />
								<button class="btn waves-effect waves-light" type="submit">
									上传
									<i class="material-icons right">send</i>
								</button>
							</form>
						</div>
						<br>
					</div>
				</div>
			</div>
		</div>

		</main>
	</body>
</html>
