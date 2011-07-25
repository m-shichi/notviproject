/**
 *
 */
package jp.web.sync.util;

/**
 * @author m-shichi
 *
 */
public class StringUtils
{

	private StringUtils()
	{
	}

	public static String nullString(Object o)
	{
		if (null == o)
		{
			return "";
		}
		return o.toString();
	}
}
