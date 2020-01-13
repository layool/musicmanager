package Pluto.admin;
import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import Pluto.DBConnection;
import Pluto.function;

public class updatamusic extends ActionSupport {
    private String id;
    private String title;
    private String singer;
    private String special;
    private String time;
    private int id_int;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer= singer;
    }
    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public String execute() throws Exception {
        ServletActionContext.getResponse().setCharacterEncoding("utf-8");
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
        ServletActionContext.getResponse().setHeader("Cache-Control",
                "no-cache");
        HttpSession session = ServletActionContext.getRequest().getSession();//从该用户请求中得到用户的Session.
        id=(String)session.getAttribute("Plutoid");
        id_int= Integer.parseInt(id);
        ServletActionContext.getResponse().setDateHeader("Expires", 0);
        System.out.println("qqqq"+id);
        if (function.isInvalid(id)) {
            out.println(function.PlutoJump("出现错误！", "music.jsp"));
        }
        else if (function.isInvalid(this.title) || function.isInvalid(this.singer) || function.isInvalid(this.special)) {
            out.println(function.PlutoJump("请填写信息！", "music.jsp"));
        }
        else{
            System.out.println("5656"+id_int);
            DBConnection conn = new DBConnection();

            boolean upd = conn.execute("update music set title = '"
                    + title + "'" + ",singer='" + singer + "'" + ",special='" + special + "'" + "where id='"
                    + id_int

                    + "'");
            if (upd) {
                out.println(function.PlutoJump("修改成功", "music.jsp"));
            } else {
                out.println(function.PlutoJump("修改失败", "music.jsp"));
            }
        }
        return null;
    }
}