package Pluto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	static private String strDriver = "com.mysql.jdbc.Driver";//驱动列表

	static private String strUrl = "jdbc:mysql://localhost:3306/music?useUnicode=yes&characterEncoding=utf8";//连接数据库

	static private String strUser = "root";//数据库用户名

	static private String strPwd = "root";//密码和数据库一致

	private Connection conn = null;//数据库的连接对象

	private Statement stmt = null;//创建Statement对象

	private PreparedStatement pstmt = null;//设置预编译对象

	private ResultSet rs = null;//数据中查询结果返回的一种对象
	static {
		try {
			Class.forName(strDriver);//加载驱动程序
		} catch (ClassNotFoundException ex) {
			System.out.println("Error load" + strDriver);
		}
	}

	public DBConnection() {
	}
	//获取数据库连接
	private Connection getConnection() {
		try {
			if (conn == null || conn.isClosed())
				conn = DriverManager.getConnection(strUrl, strUser, strPwd);
		} catch (Exception ex) {
			ex.printStackTrace();//表示打印异常堆栈信息
			return null;
		}
		return conn;
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (Exception ex) {
			System.err.println("close error:" + ex.getMessage());
		}
	}
	/*executeQuery只能用于查询，execute方法才可以执行insert，update，delete操作。
	*/
	public ResultSet executeQuery(String sql) {
		try {
			pstmt = getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
		} catch (SQLException ex) {
			System.err.println("query error:" + ex.getMessage());
		}
		return rs;
	}

	public boolean execute(String sql) {
		try {
			pstmt = getConnection().prepareStatement(sql);
			if (pstmt.execute()) {
				return true;
			}
		} catch (SQLException ex) {
			System.err.println("query error:" + ex.getMessage());
			return false;
		}
		return true;
	}

	public int executeUpdate(String sql) {
		int resultNum = 0;
		try {
			pstmt = getConnection().prepareStatement(sql);//创建Statement语句
			resultNum = pstmt.executeUpdate();//执行更新语句
		} catch (SQLException ex) {
			System.err.println("update error:" + ex.getMessage());
		} finally {
		}
		return resultNum;
	}
}
