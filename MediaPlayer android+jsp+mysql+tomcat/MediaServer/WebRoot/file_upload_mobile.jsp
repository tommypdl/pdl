<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<meta name="viewport"
			content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no, minimal-ui">
		<link rel="stylesheet" href="css/app.min.css">

  </head>
  
  <body>
    <div class="app-page" data-page="file_upload">
			<div class="app-topbar teal">
				<div class="app-button left" data-back data-autotitle onclick="location.href='index_mobile.html'">
					主页
				</div>
				<div class="app-title">
					文件上传
				</div>
			</div>
			<div class="app-content">
				<div class="app-section">
					<form action="UploadServlet" method="post" enctype="multipart/form-data">
						上传文件：
						<input type="file" name="file" />
						<p />
						<input type="submit" value="上传" class="app-button green">
					</form>
				</div>
				<div class="app-section">注：部分手机可能不支持文件上传</div>
			</div>
		</div>
	<script src="js/zepto.js"></script>
	<script src="js/app.min.js"></script>
	<script>
	App.load("file_upload");
</script>
  </body>
</html>
