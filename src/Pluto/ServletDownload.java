package Pluto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDownload
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/ServletDownload" })
public class ServletDownload extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String saveName;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        //获得请求文件名
        int i=0;
        String filRoute = request.getParameter("filename");
        String filtitle = request.getParameter("title");
        String[] splita=filRoute.split("\\.");
        for(String ij:splita){
            if(i==1) {
                saveName =ij;
            }
            i++;
        }
        //System.out.println("55555555555"+filRoute);
        //设置文件MIME类型
        response.setContentType(getServletContext().getMimeType(filRoute));
        //设置Content-Disposition
        System.out.println(filtitle);
        System.out.println(filRoute);
        response.setHeader("Content-Disposition", "attachment;filename="+new String(filtitle.getBytes("gb2312"), "ISO8859-1")+"."+saveName);
        //读取目标文件，通过response将目标文件写到客户端
        //获取目标文件的绝对路径
        String fullFileName = getServletContext().getRealPath(filRoute);
        //System.out.println("888888888888"+fullFileName);
        //读取文件
        InputStream in = new FileInputStream(fullFileName);
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[102400];
        //写文件
        int b;
        while((b=in.read(buffer))!= -1)
        {
            //输出缓冲区的内容到浏览器，实现文件下载
            //out.write(b);
            out.write(buffer, 0, b);
        }

        in.close();
        out.close();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
