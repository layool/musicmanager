package Pluto;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import Pluto.DBConnection;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class upload extends ActionSupport {

	private String title;
	private String singer;
	private String price;
	private String special;
	private String path;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	@Override
	public String execute() throws Exception {
		ServletActionContext.getResponse().setCharacterEncoding("gb2312");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control",
				"no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0);

		String filePath = request.getParameter("path");
		if (function.isInvalid(title) || function.isInvalid(singer)
				|| function.isInvalid(special) || function.isInvalid(path)) {
			out.println(function.PlutoJump("任何一项都不能为空！", "upload.jsp?path="
					+ filePath));
		} else {
			// 获取文件后缀
			filePath = filePath.replace("upload", "upload\\");
			DBConnection conn = new DBConnection();
			long time = new Date().getTime();
			if (conn.execute("insert into music(title,singer,price,special,value,time,click,url) values('"
							+ title
							+ "','"
							+ singer
							+ "','"
							+ price
							+ "','"
							+ special
							+ "','"
							+ value+ "','" + time + "',0,'" + filePath + "')")) {

				// 添加TIP

				String tip = "[" + session.getAttribute("PlutoUser").toString()
						+ "] 分享了歌曲 [" + title + "]";
				conn.execute("insert into tip(value) values('" + tip + "')");
				out.println(function.PlutoJump("上传成功！", "uploadmusic.jsp"));

			} else {
				out.println(function.PlutoJump("上传失败！", "upload.jsp?path="
						+ filePath));
			}
		}
		return null;
	}
}
