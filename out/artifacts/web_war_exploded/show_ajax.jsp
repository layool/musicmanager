<%@ page language="java"
	import="java.util.*,java.sql.*,Pluto.function,java.util.Date,java.text.SimpleDateFormat"
	pageEncoding="UTF-8"%>
<jsp:useBean id="conn" class="Pluto.DBConnection" scope="session" />
<TABLE width="100%" align="center" class="mytable">

	<%
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		int id = function.strToInt(request.getParameter("id"));
        ResultSet rs = conn.executeQuery("select * from comments where music_id="
				+ id + "");
		if (rs.next()) {
			do {
				String value = rs.getString("value");
				String name = rs.getString("name");
				long time = rs.getLong("time");
				System.out.println(time);
				Date date = new Date(time);
				System.out.println(date);
				SimpleDateFormat sdf = new SimpleDateFormat(//格式化时间
						"yyyy年MM月dd日 HH:mm:ss");
				String comments_time = sdf.format(date);
	%>
	<TBODY>
		<TR class=odd>
			<TD>
				<div align="left">
					[<%=name%>] 发表于
					<%=comments_time%>
				</div>
			</TD>
		</TR>
	</TBODY>
	<TFOOT></TFOOT>
	<TBODY>
		<TR>
			<TD>
				<div align="left">
					<%=value%>
				</div>
			</TD>
		</TR>
	</TBODY>
	<%
		} while (rs.next());
		} else {
	%>
	<TBODY>
		<TR class=odd>
			<TD>
				<div align="left">
					对不起，暂无任何评论！
				</div>
			</TD>
		</TR>
	</TBODY>
</TABLE>
<%
	}
%>

