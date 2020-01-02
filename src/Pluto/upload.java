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
	private String special;
	private String path;
	private String value;

	/*public static String gb2312ToUtf8(String str) {
		String urlEncode = "";
		String url = "";
		try {
			urlEncode = URLEncoder.encode(str, "GBK" );
			System.out.println(urlEncode);
			url=URLEncoder.encode(urlEncode, "UTF-8" );
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	public static String getEncoding(String str) {
		String encode = "GB2312";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GB2312
				String s = encode;
				return s; //是的话，返回“GB2312“，以下代码同理
			}
		} catch (Exception exception) {
		}
		String encode = "ISO-8859-1";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是ISO-8859-1
				String s1 = encode;
				return s1;
			}
		} catch (Exception exception1) {
		}
		encode = "GBK";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是GBK
				String s3 = encode;
				return s3;
			}
		} catch (Exception exception3) {
		}
		encode = "UTF-8";
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) { //判断是不是UTF-8
				String s2 = encode;
				return s2;
			}
		} catch (Exception exception2) {
		}
		return ""; //如果都不是，说明输入的内容不属于常见的编码格式。
	}*/
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

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	@Override
	public String execute() throws Exception {
		ServletActionContext.getResponse().setCharacterEncoding("GB2312");
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

			//String title_str = toUtf8String(title);
			//String singer_str = toUtf8String(singer);
			//String special_str = toUtf8String(special);
			//String value_str = toUtf8String(value);
			//System.out.println(getEncoding(title));
			//System.out.println(title);
			//System.out.println(singer);
			//System.out.println(getEncoding("我是谁"));
			//System.out.println(getEncoding(title_str));
			//System.out.println(getEncoding(singer_str));
			//System.out.println(getEncoding(special_str));
			//System.out.println(title.defaultCharset().name());
			if (conn.execute("insert into music(title,singer,special,value,time,click,url) values('"
							+ title
							+ "','"
							+ singer
							+ "','"
							+ special
							+ "','"
							+ value+ "','" + time + "',0,'" + filePath + "')")) {

				// 添加TIP

				String tip = "[" + session.getAttribute("PlutoUser").toString()
						+ "] 分享了歌曲 [" + title + "]";
				conn.execute("insert into tip(value) values('" + tip + "')");
				out.println(function.PlutoJump("提交成功！", "uploadmusic.jsp"));

			} else {
				out.println(function.PlutoJump("提交失败！", "upload.jsp?path="
						+ filePath));
			}
		}
		return null;
	}

}
