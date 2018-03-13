<%@ page language="java" import="java.util.* , java.sql.*" pageEncoding="UTF-8"%>
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
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
  		<meta name="msapplication-tap-highlight" content="no">

		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/materialize.min.js"></script>
		<link href="css/materialize.min.css" type="text/css" rel="stylesheet">
		<link href="css/ghpages-materialize.css" type="text/css"
			rel="stylesheet" media="screen,projection">
	</head>

	<body>
		<%@ include file="header_navi.html"%>
		<script type="text/javascript">
	$("#page_title").text("用户管理");
	function deleteUser(id) {
		Materialize.toast("正在提交……", 1500);
		$("#button_cofirm").click(function(){
			$.post("servlet/DeleteUserFormWeb", {id:id}, function(data) {
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
									用户列表
								</h4>
						</ul>
						<table class="highlight">
							<thead>
								<tr>
									<th data-field="id">
										账号
									</th>
									<th data-field="name">
										密码
									</th>
									<th data-field="price">
										昵称
									</th>
									<th data-field="price">
										性别
									</th>
									<th data-field="price">
										生日
									</th>
									<th data-field="price">
										城市
									</th>
									<th data-field="price">
										删除
									</th>
								</tr>
							</thead>

							<tbody>

								<jsp:useBean id="db" class="pdl.UserTableDao" scope="page" />
								<%
									String sql = "select * from chat";
									ResultSet rs = db.queryTalkmofu(sql); //执行查询，得到数据集
									String id,username,password,nickname,sex,birthday,city;
									while(rs.next()){  //如果能读到数据
										id = rs.getString("ID");
										username=rs.getString("username");
										password = rs.getString("password");
										nickname=rs.getString("nickname");
										sex=rs.getString("sex");
										birthday=rs.getString("birthday");
										city=rs.getString("city");
								%>
								<tr>
									<td>
										<%=username %>
									</td>
									<td>
										<%=password %>
									</td>
									<td>
										<%=nickname %>
									</td>
									<td>
										<%=sex %>
									</td>
									<td>
										<%=birthday %>
									</td>
									<td>
										<%=city %>
									</td>
									<td>
										<a class="btn" onclick="deleteUser('<%=id %>');">删除</a>
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
					</div>
				</div>

			</div>
		</div>

		</main>
	</body>
</html>
