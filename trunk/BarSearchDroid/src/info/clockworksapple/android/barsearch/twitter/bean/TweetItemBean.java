/**
 *
 */
package info.clockworksapple.android.barsearch.twitter.bean;

/**
 * @author ibmpck62u
 *
 */
public class TweetItemBean {

    private String id = "";
    private String time = "";
    private String contents = "";

    /**
     * @return id
     */
    public String getId() {
	return id;
    }

    /**
     * @param id
     *            セットする id
     */
    public void setId(String id) {
	this.id = id;
    }

    /**
     * @return time
     */
    public String getTime() {
	return time;
    }

    /**
     * @param time
     *            セットする time
     */
    public void setTime(String time) {
	this.time = time;
    }

    /**
     * @return contents
     */
    public String getContents() {
	return contents;
    }

    /**
     * @param contents
     *            セットする contents
     */
    public void setContents(String contents) {
	this.contents = contents;
    }

}
