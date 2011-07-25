/**
 *
 */
package jp.web.sync.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.web.sync.relax.response.Data;
import jp.web.sync.relax.response.GroupInfo;
import jp.web.sync.relax.response.ResponseXML;
import jp.web.sync.util.Constant;

import org.apache.log4j.Logger;

/**
 * @author sync
 *
 */
public class GroupInfoDao extends BaseDao
{
	protected static Logger log = Logger.getLogger(GroupInfoDao.class);

	/**
	 *
	 * @param userId
	 * @param groupName
	 * @return
	 */
	public ResponseXML groupNew(int userId, String groupName)
	{
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rst = null;
		ResponseXML resXML = new ResponseXML();
		Data data = new Data();
		try
		{
			String sql = "call group_new(?, ?);";

			conn = getConnection();
			cstmt = conn.prepareCall(sql);

			cstmt.setInt(1, userId);
			cstmt.setString(2, groupName);

			rst = cstmt.executeQuery();

			while (rst.next())
			{
				resXML.setCode(rst.getString("code"));
				GroupInfo groupInfo = new GroupInfo();
				groupInfo.setId(rst.getInt("id"));
				groupInfo.setGroupNameByString(rst.getString("group_name"));
				data.addGroupInfo(groupInfo);
			}
			resXML.setData(data);
		}
		catch (SQLException ex)
		{
			log.error("[groupNew]", ex);
		}
		finally
		{
			endProsess(conn, null, cstmt, rst);
		}
		return resXML;
	}

	/**
	 *
	 * @param groupId
	 * @param userId
	 * @return
	 */
	public ResponseXML groupEnter(int groupId, int userId)
	{
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rst = null;
		ResponseXML resXML = new ResponseXML();
		Data data = new Data();
		try
		{
			String sql = "call group_enter(?, ?);";

			conn = getConnection();
			cstmt = conn.prepareCall(sql);

			cstmt.setInt(2, groupId);
			cstmt.setInt(2, userId);

			rst = cstmt.executeQuery();

			while (rst.next())
			{
				resXML.setCode(rst.getString("code"));
				if (rst.getString("code").equals(Constant.CODE_GROUP_ADD_SUCCESS))
				{
					GroupInfo groupInfo = new GroupInfo();
					groupInfo.setId(rst.getInt("id"));
					groupInfo.setGroupNameByString(rst.getString("group_name"));
					data.addGroupInfo(groupInfo);
				}
			}
			resXML.setData(data);
		}
		catch (SQLException ex)
		{
			log.error("[groupEnter]", ex);
		}
		finally
		{
			endProsess(conn, null, cstmt, rst);
		}
		return resXML;
	}

	/**
	 *
	 * @param userId
	 * @return
	 */
	public ResponseXML getGroupList(int userId)
	{
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rst = null;
		ResponseXML resXML = new ResponseXML();
		Data data = new Data();
		try
		{
			String sql = "SELECT a.group_id as id, b.group_name FROM KDDB.group_info_list a, KDDB.group_info b WHERE a.group_id=b.id AND a.user_id=?";

			conn = getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, userId);

			rst = psmt.executeQuery();

			while (rst.next())
			{
				GroupInfo groupInfo = new GroupInfo();
				groupInfo.setId(rst.getInt("id"));
				groupInfo.setGroupName(rst.getString("group_name"));
				data.addGroupInfo(groupInfo);
			}
			resXML.setData(data);

		}
		catch (SQLException ex)
		{
			log.error("[getGroupList]", ex);
		}
		finally
		{
			endProsess(conn, psmt, null, rst);
		}
		return resXML;
	}
}
