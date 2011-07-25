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

import org.apache.log4j.Logger;

import jp.web.sync.dao.LocationInfoDao;
import jp.web.sync.relax.response.ResponseXML;

/**
 * @author m-shichi
 *
 */

@Path("/location")
public class LocationInfoResource
{
	protected static Logger log = Logger.getLogger(LocationInfoResource.class);

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("{id}/list")
	public String getGroupLocation(@PathParam("id") int id, @QueryParam("GroupId") int groupId, @QueryParam("Lattitude") String lattitude, @QueryParam("Longitude") String longitude)
	{
		log.info(String.format("[id]%s", String.valueOf(id)));
		log.info(String.format("[GroupId]%s", String.valueOf(groupId)));
		log.info(String.format("[Lattitude]%s", lattitude));
		log.info(String.format("[Longitude]%s", longitude));

		ResponseXML responseXML = null;
		// DAO
		LocationInfoDao locDao = new LocationInfoDao();

		responseXML = locDao.getLocationInfo(id, groupId, new Double(lattitude), new Double(longitude));

		return responseXML.makeTextDocument();
	}
}
