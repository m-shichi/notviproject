/**
 *
 */
package jp.web.sync.android.bean;

/**
 * @author m-shichi
 *
 */
public class SelfGroupInfoBean
{
	private int id;
	private int userId;
	private String groupName = null;

	/**
	 * @return id
	 */
	public int getId()
	{
		return id;
	}
	/**
	 * @return userId
	 */
	public int getUserId()
	{
		return userId;
	}
	/**
	 * @return groupName
	 */
	public String getGroupName()
	{
		return groupName;
	}
	/**
	 * @param id
	 *            セットする id
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	/**
	 * @param userId
	 *            セットする userId
	 */
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	/**
	 * @param groupName
	 *            セットする groupName
	 */
	public void setGroupName(String groupName)
	{
		this.groupName = groupName;
	}

}
