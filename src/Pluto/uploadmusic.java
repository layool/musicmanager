package Pluto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class uploadmusic extends ActionSupport {
	private File upload;
	//uploadFileName和uploadContentType，这两个属性分别用于封装上传文件的文件名、上传文件的文件类型。
	// 这两个属性，体现了Struts2设计的灵巧、简化之处，Action类直接通过File类型属性直接封装了上传文件的文件内容，
	// 但这个File属性无法获取上传文件的文件名和文件类型，
	// 所以Struts2直接将文件域中包含的上传文件名和文件类型的信息封装到uploadFileName和uploadContentType属性中。
	private String uploadContentType;
	private String uploadFileName;
	private String savePath;
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setSavePath(String value) {
		this.savePath = value;
	}
	private String getSavePath() throws Exception {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}
	
	public String execute() throws Exception {
		ServletActionContext.getResponse().setCharacterEncoding("gb2312");
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control",
				"no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0);
		System.out.println("wochulaile");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		System.out.println(upload);
		if (upload!=null) {
			//获取文件后缀
			String fileType = getUploadFileName().substring(getUploadFileName().lastIndexOf("."));
			//System.out.println(fileType);
			//System.out.println(getUploadFileName());
			if (fileType.equals(".mp3")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Date dt = new Date();
				Random rd = new Random();
				setUploadFileName(sdf.format(dt) + rd.nextInt(999999) + fileType);
				FileOutputStream fos = new FileOutputStream(getSavePath() + "/"
						+ getUploadFileName());
				System.out.println("文件后缀" + fileType);
				System.out.println("111111111" + getUpload());
				System.out.println("111111111" + getSavePath());
				System.out.println("2222222222" + getUploadFileName());
				System.out.println("111111111" + fos);
				System.out.println(upload);
				FileInputStream fis = new FileInputStream(getUpload());
				byte[] buffer = new byte[102400];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				String filePath = "upload/" + getUploadFileName();
				//out.println(function.PlutoJump("上传成功，请认真填写歌曲内容！", "upload.jsp?path=" + filePath));
				out.println(function.toJump("upload.jsp?path=" + filePath));
			} else {
				out.println(function.PlutoJump("请上传mp3文件", "uploadmusic.jsp"));
			}

		} else {
			out.println(function.PlutoJump("请上传文件", "uploadmusic.jsp"));
		}
		return null;
	}
}
