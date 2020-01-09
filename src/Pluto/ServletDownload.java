package Pluto;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDownload
 */
//指定一组 Servlet 的 URL 匹配模式。等价于<url-pattern>标签。
//@WebServlet(asyncSupported = true, urlPatterns = { "/ServletDownload" })
public class ServletDownload extends HttpServlet {
    //一般情况下，建议序列化的class都给一个序列化的ID，这样可以保证序列化的成功，版本的兼容性。
    private static final long serialVersionUID = 1L;
    private String saveName;//后缀名

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
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //获得请求文件名
            int i=0;
            String filRoute = request.getParameter("filename");//文件路径
            String filtitle = request.getParameter("title");//文件名
            String[] splita=filRoute.split("\\.");//正则
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
        // TODO Auto-generated method stub
    }

