package zero.commons.basics.result;

/**
 * 
 * 类: FileResult <br>
 * 描述: 文件处理结果集 <br>
 * 作者: zhy<br>
 * 时间: 2019年1月15日 上午9:52:53
 */
public class FileResult extends BaseResult {

	private String path;

	private String url;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
