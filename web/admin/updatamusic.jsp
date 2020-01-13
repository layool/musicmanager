<%@ page language="java" import="java.util.*,java.sql.*,Pluto.function"
         pageEncoding="utf-8"%>
<%
    if (session.getAttribute("PlutoAdmin")!=null) {
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
</head>

<body  style="background-image: url(/images/imag1.jpg);background-size: 100%">
<%
    final String id=request.getParameter("id");
    session.setAttribute("Plutoid", id);
%>
<form id="form1" name="form1" method="post" action="admin_updatamusic.action">

    <label>title：
        <input type="text" name="title" id="textfield" />
    </label>
    <br />
    <label>singer：
        <input type="text" name="singer" id="textfield2" />
    </label>
    <br />
    <label>special：
        <input type="text" name="special" id="textfield3" />
    </label>
    <p>
        <label>
            <input type="submit" name="button" id="button" value="提交" />
        </label>
        <label>
            <input type="reset" name="button2" id="button2" value="重置" />
        </label>
    </p>
</form>
</body>
</html>
<%
    }else {
        out.println(function.PlutoJump("非法请求或您的登陆已经超时！","index.jsp"));
    }
%>