package zero.commons.basics.result;

/**
 * 
 * 类: BaseResult <br>
 * 描述: 结果集 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午5:23:38
 */
public class BaseResult {

	/**
	 * 消息编码
	 */
	private ResultType code;
	/**
	 * 消息信息
	 */
	private String message;

	public ResultType getCode() {
		return code;
	}

	public void setCode(ResultType code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
