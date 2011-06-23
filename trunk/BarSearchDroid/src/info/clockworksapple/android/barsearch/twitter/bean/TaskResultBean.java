package info.clockworksapple.android.barsearch.twitter.bean;

public class TaskResultBean {

	private boolean isResult = false;
	/** 処理結果 */
	private String status = null;
	/** メッセージ */
	private String message = null;

	/**
	 *
	 * @return
	 */
	public boolean isResult() {
		return isResult;
	}

	/**
	 *
	 * @param isResult
	 */
	public void setResult(boolean isResult) {
		this.isResult = isResult;
	}

	/**
	 *
	 * @return
	 */
	public String getStatus() {
		return status;
	}

	/**
	 *
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 *
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 *
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
