<%@ page language="java" import="java.util.*,Pluto.function,java.sql.*"
	pageEncoding="UTF-8"%>
<jsp:useBean id="conn" class="Pluto.DBConnection" scope="session" />
<%
	if (session.getAttribute("PlutoUser") != null) {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>音乐库管理系统</title>
		<meta name="keywords" content="" />
		<meta name="description" content="" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/nicejforms.js"></script>
		<script type="text/javascript" src="js/thickbox.js"></script>
		<link href="css/default.css" rel="stylesheet" type="text/css" />
		<link href="css/thickbox.css" rel="stylesheet" type="text/css" />
		<style type="text/css" media="screen">
@import url(css/niceforms.css);
</style>
		<script type="text/javascript">
$(document).ready(function(){
	$.NiceJForms.build()
});
</script>
	</head>
	<body>
		<div id="header">
			<div id="logo">
				<h1>
					音乐库管理系统
				</h1>
			</div>
			<div id="menu">
				<ul>
					<li class="active">
						<a href="index.jsp" accesskey="1" title="">首页</a>
					</li>
					<li>
						<a href="musicbox.jsp" accesskey="2" title="">音乐盒</a>
					</li>
					<li>
						<a href="uploadmusic.jsp" accesskey="3" title="">分享歌曲</a>
					</li>
				</ul>
			</div>
		</div>
		<hr />
		<div id="page">
			<div id="bg">
				<div id="content">
					<div class="post">
						<h2 class="title">
							上传音乐第一步（上传音乐）
						</h2>

						<div class="entry">
							<p>
							<!--是设置表单的MIME编码。默认情况，这个编码格式是application/x-www-form-urlencoded
							不能用于文件上传；只有使用了multipart/form-data，才能完整的传递文件数据-->
							<form id="form1" name="form1" method="post"
								enctype="multipart/form-data" action="uploadmusic.action"
								class="niceform">
								<table width="80%" border="0" align="center">
									<tr>
										<td height="31">
											<label>
												<div align="center">
													请上传歌曲：
													<input type="file" name="upload" id="fileField" />
												</div>
											</label>
										</td>
									</tr>
									<tr>
										<td>
											<label>
												<div align="center">
													<input type="submit" name="button" id="button" value="下一步" />
												</div>
											</label>
										</td>
									</tr>
								</table>
							</form>
						</div>
						<p class="meta">

						</p>
					</div>
				</div>
				<!-- end content -->
				<div id="sidebar">
					<div id="about-box" style="font-size: 12px">
						<p>
							<%=session.getAttribute("PlutoUser").toString()%>，欢迎您回来！
						</p>
						<p>
							如果您有音乐分享，您可以点我进行
							<a href="uploadmusic.jsp" style="color: red">[上传音乐]</a>！
							<br />
						</p>
						<p>
							退出请点
							<a href="logout.action" style="color: #FF0000">[注销登陆]</a>！
						</p>
					</div>
					<ul>
						<li>
							<h2>
								最新消息
							</h2>
							<ul>
								<%
									ResultSet tip_rs = conn
												.executeQuery("SELECT * FROM `tip` ORDER BY id DESC LIMIT 10");
										while (tip_rs.next()) {
											String tip = tip_rs.getString("value");
											out.println("<li>");
											out.println(tip);
											out.println("</li>");
										}
								%>
							</ul>
						</li>
					</ul>
				</div>
				<!-- end sidebar -->
				<div style="clear: both;">
					&nbsp;
				</div>
			</div>
		</div>
		<!-- end page -->
		<hr />
	</body>
</html>
<%
	} else {
		out.println(function.PlutoJump("请登陆后再访问!", "index.jsp"));
	}
%>
