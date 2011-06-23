/**
 *
 */
package jp.web.sync.android.bean;

import jp.web.sync.android.util.CustomStringUtils;

/**
 * @author m-shichi
 *
 */
public class SelfInfoBean {

	private int id;
	private String mailAddr = null;
	private String password = null;
	private String hexPassword = null;
	private String handleName = null;
	private String message = null;
	private String imgPath = null;
	private String terminalId = null;

	public int getId() {
		return id;
	}
	public String getMailAddr() {
		return mailAddr;
	}
	public String getPassword() {
		return password;
	}
	/**
	 * @return hexPassword
	 */
	public String getHexPassword() {
		return hexPassword;
	}
	public String getHandleName() {
		return handleName;
	}
	public String getMessage() {
		return message;
	}
	public String getImgPath() {
		return imgPath;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setMailAddr(String mailAddr) {
		this.mailAddr = mailAddr;
	}
	public void setPassword(String password) {
		this.password = password;
		this.hexPassword = CustomStringUtils.digestMd5(CustomStringUtils.nullValue(this.mailAddr) + password);
		// this.password =
		// DigestUtils.md5Hex(CustomStringUtils.nullValue(this.mailAddr) +
		// password);
	}

	public void setHandleName(String handleName) {
		this.handleName = handleName;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

}
