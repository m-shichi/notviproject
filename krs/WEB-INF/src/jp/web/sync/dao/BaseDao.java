/**
 *
 */
package jp.web.sync.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author sync
 *
 */
public abstract class BaseDao {

	/**
	 *
	 * @return
	 */
	protected Connection getConnection() {
		Connection con = null;
		try {
			InitialContext initCon = new InitialContext();
			DataSource ds = (DataSource) initCon.lookup("java:comp/env/jdbc/MySQL");
			con = ds.getConnection();
			con.setAutoCommit(false);
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 *
	 * @param conn
	 * @param psmt
	 * @param rst
	 */
	protected void endProsess(Connection conn, PreparedStatement psmt, CallableStatement csmt, ResultSet rst) {

		if (null != rst) {
			try {
				rst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != psmt) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != csmt) {
			try {
				csmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
