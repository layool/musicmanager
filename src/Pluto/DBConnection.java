package Pluto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	static private String strDriver = "com.mysql.jdbc.Driver";

	static private String strUrl = "jdbc:mysql://localhost:3306/music?useUnicode=yes&characterEncoding=utf8";

	static private String strUser = "root";

	static private String strPwd = "root";

	private Connection conn = null;

	private Statement stmt = null;

	private PreparedStatement pstmt = null;

	private ResultSet rs = null;
	static {
		try {
			Class.forName(strDriver);
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
			ex.printStackTrace();
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
			pstmt = getConnection().prepareStatement(sql);
			resultNum = pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.err.println("update error:" + ex.getMessage());
		} finally {
		}
		return resultNum;
	}
}
