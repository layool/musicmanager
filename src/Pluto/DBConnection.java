package Pluto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	/**
	 * 锟斤拷菘锟斤拷锟斤拷锟斤拷锟�
	 */
	static private String strDriver = "com.mysql.jdbc.Driver";
	/**
	 * 锟斤拷菘锟絣锟斤拷url
	 */
	static private String strUrl = "jdbc:mysql://localhost:3306/music?useUnicode=yes&characterEncoding=utf8";
	/**
	 * 锟斤拷菘锟絣锟斤拷锟矫伙拷锟斤拷
	 */
	static private String strUser = "root";
	/**
	 * 锟斤拷菘锟絣锟斤拷锟斤拷锟斤拷
	 */
	static private String strPwd = "root";
	/**
	 * connection 锟斤拷锟斤拷
	 */
	private Connection conn = null;
	/**
	 * Statement锟斤拷锟斤拷锟斤拷connection锟斤拷锟斤拷锟斤拷锟�
	 */
	private Statement stmt = null;
	/**
	 * PreparedStatement锟斤拷锟斤拷锟斤拷connection锟斤拷锟斤拷锟斤拷锟�
	 */
	private PreparedStatement pstmt = null;
	/**
	 * ResultSet 锟斤拷锟�
	 */
	private ResultSet rs = null;
	/**
	 * @des 锟洁静态锟斤拷始锟斤拷锟解，装锟斤拷锟斤拷菘锟斤拷锟�
	 */
	static {
		try {
			Class.forName(strDriver);
		} catch (ClassNotFoundException ex) {
			System.out.println("Error load" + strDriver);
		}
	}

	public DBConnection() {
	}

	/**
	 * @des 锟斤拷锟斤拷锟捷匡拷l锟斤拷
	 */
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

	/**
	 * @des 锟截憋拷锟斤拷菘锟絣锟斤拷
	 */
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

	/**
	 * @des锟斤拷锟絪ql 锟斤拷询
	 * @param sql
	 *            锟斤拷询锟斤拷sql锟斤拷锟�
	 * @return ResultSet(锟斤拷锟�)
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

	/**
	 * @des锟斤拷锟絪ql 执锟斤拷
	 * @param sql
	 *            执锟叫碉拷sql锟斤拷锟�
	 * @return 锟角凤拷锟斤拷确
	 */
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

	/**
	 * @des 锟斤拷锟斤拷
	 * @param 锟斤拷锟铰碉拷sql
	 *            锟斤拷锟�
	 * @return int 锟斤拷锟截斤拷锟斤拷锟接帮拷锟斤拷锟斤拷锟斤拷)
	 */
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
