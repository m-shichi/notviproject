/**
 *
 */
package jp.web.sync.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import jp.web.sync.dao.UserInfoDao;
import jp.web.sync.relax.response.ResponseXML;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

/**
 * @author sync
 *
 */
@Path("user")
public class UserInfoResource
{
	protected static Logger log = Logger.getLogger(UserInfoResource.class);

	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("/new")
	public String doSignUp(@QueryParam("Address") String mailAddr, @QueryParam("Pass") String password)
	{
		log.info(String.format("[MAIL]%s", mailAddr));
		log.info(String.format("[PASS]%s", password));

		String terminalId = null;
		ResponseXML resXML = null;
		try
		{
			// DAO
			UserInfoDao uDao = new UserInfoDao();
			// ユーザ情報追加
			terminalId = RandomStringUtils.randomAlphanumeric(30);

			resXML = uDao.userNew(mailAddr, password, terminalId);

		}
		catch (Exception ex)
		{
			log.error("[ユーザー登録]", ex);
		}
		return resXML.makeTextDocument();
	}
	@GET
	@Produces(MediaType.TEXT_XML)
	@Path("{id}/signin")
	public String doSignin(@PathParam("id") int id, @QueryParam("Address") String mailAddr, @QueryParam("Pass") String password, @QueryParam("TerminalId") String terminalId)
	{
		ResponseXML resXML = null;
		// DAO
		UserInfoDao uDao = new UserInfoDao();
		// ユーザ情報取得
		if (null != terminalId)
		{
			resXML = uDao.userSignin(id, mailAddr, password, terminalId);
		}
		return resXML.makeTextDocument();
	}

	@POST
	@Produces(MediaType.TEXT_XML)
	@Path("{id}/edit")
	public String doEditUser(@PathParam("id") int id, @QueryParam("Address") String mailAddr, @QueryParam("Pass") String password, @QueryParam("Handle") String handleName,
			@QueryParam("Message") String message)
	{
		ResponseXML resXML = null;
		// DAO
		UserInfoDao uDao = new UserInfoDao();

		resXML = uDao.userEdit(id, mailAddr, password, handleName, message);

		return resXML.makeTextDocument();
	}
}
