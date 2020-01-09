<%@ page language="java" import="java.util.*,java.sql.*,Pluto.function"
	pageEncoding="UTF-8"%>
<%
if (session.getAttribute("PlutoAdmin")!=null) {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<link rel="stylesheet" rev="stylesheet" href="control.css" type="text/css" media="all" />
<title>音乐库管理系统</title>
<script>
var LastItem=null
function MenuClick(obj,url){
	if (LastItem!=null){
		LastItem.className="menuA"//没被点中
	}
	obj.className="menuAS"//点中
	LastItem=obj
	obj.blur()//取消指定元素的焦点
	if (url.length>0) parent.MainContent.location=url;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" /></head>
<body class="menuBody">
 <div class="menu">
 <img border="0" src="Control/menuS.jpg"width="152" height="8" /></div>
 <ul class="menuUL">
   <li><a href="javascript:void(0)" class="menuA" onclick="MenuClick(this,'music.jsp')"><img src="Control/icons/icon2.gif" alt="" border="0" class="MenuIcon"/>音乐管理</a></li>
   <li><a href="javascript:void(0)" class="menuA" onclick="MenuClick(this,'user.jsp')"><img src="Control/icons/icon1.gif" alt="" border="0" class="MenuIcon"/>用户管理</a></li>
   <li><a href="javascript:void(0)" class="menuA" onclick="MenuClick(this,'new.jsp')"><img src="Control/icons/icon4.gif" alt="" border="0" class="MenuIcon"/>添加管理员</a></li>
   <li><a href="javascript:void(0)" class="menuA" onclick="MenuClick(this,'changepwd.jsp')"><img src="Control/icons/icon5.gif" alt="" border="0" class="MenuIcon"/>修改密码</a></li>
     <li><a href="index.jsp" class="menuA" target="_top"><img src="Control/icons/icon9.gif" alt="" border="0" class="MenuIcon"/>注销登陆</a></li>
</ul>
</body>
</html>
<%
}else {
	out.println(function.PlutoJump("非法请求或您的登陆已经超时！","index.jsp"));
}
%>