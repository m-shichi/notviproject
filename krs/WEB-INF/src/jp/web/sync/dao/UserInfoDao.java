/**
 *
 */
package jp.web.sync.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import jp.web.sync.relax.response.Data;
import jp.web.sync.relax.response.ResponseXML;
import jp.web.sync.relax.response.UserInfo;
import jp.web.sync.util.Constant;

/**
 * @author sync
 *
 */
public class UserInfoDao extends BaseDao {

	protected static Logger log = Logger.getLogger(UserInfoDao.class);

	/**
	 *
	 * @param id
	 * @param mailAddr
	 * @param password
	 * @param terminalId
	 * @return
	 */
	public ResponseXML userSignin(int id, String mailAddr, String password, String terminalId) {

		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rst = null;
		ResponseXML resXML = new ResponseXML();
		UserInfo userInfo = new UserInfo();
		Data data = new Data();
		try {
			conn = getConnection();

			String sql = "call user_signin(?, ?, ?, ?)";
			cstmt = conn.prepareCall(sql);

			cstmt.setInt(1, id);
			cstmt.setString(2, mailAddr);
			cstmt.setString(3, password);
			cstmt.setString(4, terminalId);

			rst = cstmt.executeQuery();

			while (rst.next()) {
				resXML.setCode(rst.getString("code"));
				if (rst.getString("code").equals(Constant.CODE_SIGNIN_SUCCESS)) {
					userInfo.setId(rst.getInt("id"));
					userInfo.setMailAddr(rst.getString("mail_addr"));
					userInfo.setHandleName(rst.getString("handle_name"));
					userInfo.setTerminalId(rst.getString("terminal_id"));
				}
			}
			data.setUserInfo(userInfo);
			resXML.setData(data);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endProsess(conn, null, cstmt, rst);
		}
		return resXML;
	}

	/**
	 *
	 * @param mailAddr
	 * @param password
	 * @param terminalId
	 * @return
	 */
	public ResponseXML userNew(String mailAddr, String password, String terminalId) {

		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rst = null;
		ResponseXML resXML = new ResponseXML();
		Data data = new Data();
		UserInfo userInfo = new UserInfo();
		try {
			String sql = "call user_new(?, ?, ?);";

			conn = getConnection();
			cstmt = conn.prepareCall(sql);

			cstmt.setString(1, mailAddr);
			cstmt.setString(2, password);
			cstmt.setString(3, terminalId);

			rst = cstmt.executeQuery();
			while (rst.next()) {
				resXML.setCodeByString(rst.getString("code"));
				if (rst.getString("code").equals(Constant.CODE_SIGNUP_SUCCESS)) {
					userInfo.setId(rst.getInt("id"));
					userInfo.setMailAddrByString(rst.getString("mail_addr"));
					userInfo.setTerminalIdByString(rst.getString("terminal_id"));
				}
			}

			data.setUserInfo(userInfo);
			resXML.setData(data);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endProsess(conn, null, cstmt, rst);
		}
		return resXML;
	}

	/**
	 *
	 * @param id
	 * @param mailAddr
	 * @param password
	 * @param handleName
	 * @param message
	 * @return
	 */
	public ResponseXML userEdit(int id, String mailAddr, String password, String handleName, String message) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResponseXML resXML = new ResponseXML();
		Data data = new Data();
		UserInfo userInfo = new UserInfo();
		try {
			String sql = "UPDATE kddb.user_info SET mail_addr=?, password=?, handle_name=?, message=? WHERE id=?;";

			conn = getConnection();
			pstmt = conn.prepareCall(sql);

			pstmt.setString(1, mailAddr);
			pstmt.setString(2, password);
			pstmt.setString(3, handleName);
			pstmt.setString(4, message);
			pstmt.setInt(5, id);

			int ret = pstmt.executeUpdate();
			if (ret > 0) {
				resXML.setCode(Constant.CODE_USER_UPDATE_SUCCESS);
			} else {
				resXML.setCode(Constant.CODE_USER_UPDATE_FAILED);
			}

			data.setUserInfo(userInfo);
			resXML.setData(data);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			endProsess(conn, pstmt, null, null);
		}
		return resXML;
	}
}
