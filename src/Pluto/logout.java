package Pluto;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class logout extends ActionSupport {
	public String execute() throws IOException{
		ServletActionContext.getResponse().setCharacterEncoding("GB2312");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession();
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control",
				"no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0);
		//session.removeAttribute()适用于清空指定的属性
		//session.invalidate()是清除当前session的所有相关信息
		session.removeAttribute("PlutoUser");
		out.println(function.toJump( "index.jsp"));
		return null;
	}
}
