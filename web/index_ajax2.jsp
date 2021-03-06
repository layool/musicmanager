<%@ page language="java"
		 import="java.util.*,java.sql.*,Pluto.function,java.util.Date,java.text.SimpleDateFormat"
		 pageEncoding="utf-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="java.io.OutputStream" %>
<jsp:useBean id="conn" class="Pluto.DBConnection" scope="session" />
<link href="css/thickbox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>
<script type="text/javascript" src="js/nicejforms.js"></script>
<script type="text/javascript">
    function addbox(music_id){
        $.ajax({url: 'addtobox.action?music_id=' + music_id,
            type: 'GET',
            dataType: 'html',
            timeout: 30000,
            async : false,
            error: function(){
                alert("出现错误！");
            },
            success: function(html){
                //window.location="#article_md";
                alert(html);
            }
        });
    }
</script>
<%
	response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
	response.setHeader("Cache-Control",
			"no-store, no-cache, must-revalidate");
	response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	response.setHeader("Pragma", "no-cache");
	ResultSet rs = conn.executeQuery("select * from music order by id ASC");
	if (rs.next()) {
		do {
			String id = rs.getString("id");
			String title = rs.getString("title");
			String singer = rs.getString("singer");
			String special = rs.getString("special");
			String value = rs.getString("value");
			long time = rs.getLong("time");
			String url=rs.getString("url");
			Date date = new Date(time);
			//System.out.println(title+singer+special+value);
			//System.out.println(upload.getEncoding(title));
			SimpleDateFormat sdf = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			String music_time = sdf.format(date);
			String click = rs.getString("click");
			ResultSet comments_rs = conn
					.executeQuery("select count(id) as count from comments where music_id = "
							+ id + "");
			comments_rs.next();
			String comments = comments_rs.getString("count");
			String filePath = rs.getString("url");
			System.out.println(filePath);
			Random rd = new Random();
			int rd_id = rd.nextInt(9999);
%>
<div class="post">
	<h2 class="title">
		<%=title%>
	</h2>
	<h3 class="date">
		<%=music_time%>
	</h3>
	<div class="entry">
		<p>
			试听：
			<object type="application/x-shockwave-flash"
					data="player/audioplayer.swf" width="290" height="24"
					id="audioplayer<%=rd_id%>">
				<param name="movie" value="player/audioplayer.swf" />
				<param name="FlashVars"
					   value="playerID=<%=rd_id%>&soundFile=<%=filePath%>" />
				<param name="quality" value="high" />
				<param name="menu" value="false" />
				<param name="wmode" value="transparent" />
			</object>
			<br />
			歌曲名：
			<%=title%><br />
			歌手：
			<%=singer%><br />
			所在专辑：
			<%=special%><br />
			<%=value%><br />
			<%
				if (session.getAttribute("PlutoUser") != null) {
			%>
			下载：
			<a href="/ServletDownload?filename=<%=url%>&title=<%=title%>">点此下载</a>
			<%
				}
			%>
		</p>
	</div>
	<p class="meta">
		<a href="show.jsp?id=<%=id%>" class="more">阅读全文</a>
		<b>|</b>
		<span class="comments"><%=comments%>条评论</span>
		<b>|</b>
		<span class="comments"><%=click%>次点击</span>
		<%
			if (session.getAttribute("PlutoUser") != null) {
		%>
		<b>|</b>
		<a href="JavaScript:addbox('<%=id%>');" class="comments">添加到我的音乐盒</a>
		<b>|</b>
		&nbsp;
		<%
			}
		%>
	</p>
</div>
<%
	} while (rs.next());
} else {
%>
<div class="post">
	暂无任何歌曲，快来分享给大家喔~！
</div>
<%
	}
%>