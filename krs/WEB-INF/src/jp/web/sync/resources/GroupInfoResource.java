/**
 *
 */
package jp.web.sync.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.web.sync.dao.GroupInfoDao;
import jp.web.sync.relax.response.ResponseXML;

/**
 * @author sync
 *
 */
@Path("group")
public class GroupInfoResource
{
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("{id}/new")
	public String doGroupNew(@PathParam("id") int userId, @QueryParam("GroupName") String groupName)
	{
		GroupInfoDao groupDao = new GroupInfoDao();

		ResponseXML responseXML = groupDao.groupNew(userId, groupName);

		return responseXML.makeTextDocument();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("{id}/enter")
	public String doGroupEnter(@PathParam("id") int id, @QueryParam("GroupId") int groupId)
	{
		// DAO
		GroupInfoDao groupDao = new GroupInfoDao();

		ResponseXML responseXML = groupDao.groupEnter(groupId, id);

		return responseXML.makeTextDocument();
	}

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("{id}/list")
	public String doGetGroupList(@PathParam("id") int id)
	{
		// DAO
		GroupInfoDao groupDao = new GroupInfoDao();

		ResponseXML responseXML = groupDao.getGroupList(id);

		return responseXML.makeTextDocument();
	}
}
