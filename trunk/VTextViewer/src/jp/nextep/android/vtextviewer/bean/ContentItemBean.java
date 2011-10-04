package jp.nextep.android.vtextviewer.bean;

public class ContentItemBean {

	private String id = "";
	private String page = "";
	private int content_index;
	private String contents = "";
	private String path = "";
	private int image_flag;
	private String image_path;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public int getContent_index() {
		return content_index;
	}
	public void setContent_index(int content_index) {
		this.content_index = content_index;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getImage_flag() {
		return image_flag;
	}
	public void setImage_flag(int image_flag) {
		this.image_flag = image_flag;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
}
