package Pluto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class addComments extends ActionSupport {//封装HTTP请求参数
	// 下面是Action内用于封装用户请求参数的三个属性
	private String name;
	private String comments;
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	// 处理用户请求的execute方法
	public String execute() throws IOException {
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");//设置response的编码//设置页面编码格式
		PrintWriter out = ServletActionContext.getResponse().getWriter();;//获取输出流
		//设置所有浏览器不要缓存，共三个响应头：
		//设置页面的相关信息
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");//no-cache控制页面缓存，向客户端（浏览器）提供响应数据时，缓存都要向服务器评估缓存响应的有效性 Pragma: no-cache可以应用到http 1.0 和http 1.1
		ServletActionContext.getResponse().setHeader("Cache-Control",
				"no-cache");//no-cache控制页面缓存，向客户端（浏览器）提供响应数据时，缓存都要向服务器评估缓存响应的有效性 Cache-Control: no-cache只能应用于http 1.1
		ServletActionContext.getResponse().setDateHeader("Expires", 0);
		// HTTP1.1的客户端和缓存必须将其他非法的日期格式（包括0）看作已经过期
		if (name == null || function.isInvalid(comments)
				|| function.isInvalid(id)) {//判断评论、id是否为空
			out.println("非法访问！");
			return null;
		} else {
			//comments = Escape.unescape(comments);
			if ("".equals(name)) {
				name = "游客";
			}
			long time = new Date().getTime();//得到的是毫秒数  /1000为时间戳

			DBConnection conn = new DBConnection();
			boolean insert = conn
					.execute("insert into comments(value,name,music_id,time) values('"
							+ comments
							+ "','"
							+ name
							+ "',"
							+ id
							+ ",'"
							+ time
							+ "')");
			if (insert) {
				out.println("评论添加成功！");
			} else {
				out.println("评论添加失败！");
			}
		}
		return null;
	}
}
