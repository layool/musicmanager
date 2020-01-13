<%@ page language="java" import="java.util.*,java.sql.*,Pluto.function,Pluto.DBConnection"
	pageEncoding="utf-8"%>
<%@ page import="java.awt.*" %>
<jsp:useBean id="conn" class="Pluto.DBConnection" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>音乐库管理系统</title>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/nicejforms.js"></script>
		<script type="text/javascript" src="js/thickbox.js"></script>
		<script type="text/javascript" src="js/audioplayer.js"></script>
		<link href="css/page.css" rel="stylesheet" type="text/css" />
		<link href="css/default.css" rel="stylesheet" type="text/css" />
		<link href="css/thickbox.css" rel="stylesheet" type="text/css" />
        <link href="css/search.css" rel="stylesheet" type="text/css" />
		<style type="text/css" media="screen">
			@import url(css/niceforms.css);
		</style>
        <%!
        	String flag;
			int flag_int;
        %>
		<script type="text/javascript">
		$(document).ready(function(){
			$.NiceJForms.build();
            <% flag=request.getParameter("flag");
            	flag_int=Integer.parseInt(flag == null || "".equals(flag)?"0":flag);
            	String name = request.getParameter("name");
//                application.setAttribute("name", name);
                String clickdy = request.getParameter("clickdy");
//            	System.out.println(clickdy);
            	if(clickdy!=null){
            		String ss="UPDATE user SET dingyue=1  where name ='" +session.getAttribute("PlutoUser").toString()+"'";

					DBConnection db = new DBConnection();
					db.executeUpdate(ss);
            	}
                if(name!=null){
                	flag_int=4;
                	application.setAttribute("name", name);
                }
            	System.out.println(flag_int);
                 if(flag_int==0){%>
                     dopage('index_ajax.jsp');
                 <%}
                 if(flag_int==1){%>
                     dopage('index_ajax1.jsp');
			     <% }
			     if(flag_int==2){%>
            		dopage('index_ajax2.jsp');
            		<%}
            		if(flag_int==3){%>
					dopage('index_ajax3.jsp?page=1');
					<%}
					if(flag_int==4){%>
					dopage('index_search.jsp?page=1');
					<%}
					//name=null;
					%>

         });
         function dopage(ajaxurl){
			$.ajax({url: ajaxurl,
			type: 'GET',
			dataType: 'html',
			timeout: 30000,
			async : false,
			error: function(){$('#content').html('<table  width="50%" border="0" align="center"> <tr> <td class="center_article" align="center">获取文章失败，请刷新此页面！！！</td></tr></table>');
			},
			success: function(html){
				$('#content').html(html);
			}
			});
		}
		</script>
	</head>
	<!--<body style="background-image: url(/images/imag1.jpg);background-size: 100%">-->
	<body>
		<div id="header">
			<div id="logo">
				<h1>
					音乐库管理系统
				</h1>
			</div>
			<form action="index.jsp" method="post">
				<div class="boxss">
					<input type="text"style="height:20px;width:160px;" name="name" placeholder="查询歌曲或歌手" />
					<input type="submit" size=40 value="搜索" onclick=""/>
				</div>
			</form>
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
					<select onchange="window.location=this.value" style="background-color:transparent">
						<option  value="" selected:disabled style="diaplay:none">排序</option>
						<option value="index.jsp?flag=0">按时间降序</option>
						<option value="index.jsp?flag=1">按点击量降序</option>
						<option value="index.jsp?flag=2">按时间升序</option>
						<option value="index.jsp?flag=3">按点击量升序</option>
					</select>
				</ul>
			</div>
		</div>
		<hr />
		<div id="page">
			<div id="bg">
				<a name="article_md"></a>
				<div id="content">
				</div>
				<!-- end contentn -->
				<div id="sidebar">
					<div id="about-box" style="font-size: 12px">
						<p>
							<%
								if (session.getAttribute("PlutoUser") == null) {
							%>
						<form action="login.action" method="post" class="niceform">
							<label for="textinput">
								&nbsp;&nbsp;用户名：
							</label>
							<br />
							&nbsp;&nbsp;
							<input type="text" id="textinput" name="userName" size="15"
								maxlength="16" />
							<br />
							<label for="passwordinput">
								&nbsp;&nbsp;密 码：
							</label>
							<br />
							&nbsp;&nbsp
							<%-- name为连接login.action的形参--%>
							<input type="password" id="passwordinput" name="userPwd"
								size="15" maxlength="16" />
							<br />
							<br />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<a href="register.jsp?height=175&width=300&modal=true"
								class="thickbox" title="我要注册">我要注册</a> &nbsp;&nbsp;
							<input type="submit" value="登  陆" />

						</form>


						<p></p>
                        <%
                        } else {
                        %>

                        <%=session.getAttribute("PlutoUser").toString()%>，欢迎您回来！
                        <br>

                        如果您有音乐分享，您可以点我进行
                        <a href="uploadmusic.jsp" style="color: red">[上传音乐]</a>！
                        <br>

                        如果忘记密码可以选择
                        <a href="changepwd.jsp?height=175&width=300&modal=true"
                           class="thickbox" style="color: red" title="修改密码">[修改密码]</a>！
                        <br>
                        <%
                            //System.out.println(getType(session.getAttribute("PlutoUser").toString()));
                            ResultSet dystate=conn.executeQuery("select * from user where name = '"+session.getAttribute("PlutoUser").toString()+"'");
                            if(dystate.next()){
                                if(dystate.getInt("dingyue")==0){
//								System.out.println(dystate.getInt("dingyue"));
                        %>
                            <form action="" method="post">
                            <div id="dingyue"style="display: block">
                                <input type = "hidden" name="clickdy"/>
                                <input type="submit" size=40 value="订阅" />
                            </div>
                            <div id="dy"style="display: none">
                                已订阅
                            </div>
                            </form>
                        <%
                        }else{
//								System.out.println(dystate.getInt("dingyue"));
                        %>
                           <form action="" method="post">
                            <div id="dingyue"style="display: none">
                                <input type = "hidden" name="clickdy"/>
                                <input type="submit"  size=40 value="订阅" />
                            </div>
                            <div id="dy"style="display: block">
                                <p>已订阅</p>
                            </div>
                           </form>
                        <%
                                }
                            }

                        %>
                        <p>
                            退出请点
                            <a href="logout.action" style="color: red">[注销登陆]</a>！
                        </p>
                        <%
                            }
                        %>
                    </div>
                    <ul>
                        <li>
                            <h2>
                                最新消息
                            </h2>
                            <ul>
                                <%
                                    ResultSet tip_rs = conn.executeQuery("SELECT * FROM `tip` ORDER BY id DESC");
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