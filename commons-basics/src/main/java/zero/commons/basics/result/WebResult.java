package zero.commons.basics.result;

public class WebResult {

	private static final Integer SUCCESS = 200;
	private static final Integer NONE = 404;
	private static final Integer ERROR = 500;
	private Integer code;
	private String message;
	private Object obj;

	public static WebResult result(ResultType type, String message) {
		Integer flag = SUCCESS;
		if (type == ResultType.ERROR) {
			flag = ERROR;
		} else if (type == ResultType.NULL) {
			flag = NONE;
		}
		WebResult _result = new WebResult();
		_result.setCode(flag);
		_result.setMessage(message);
		return _result;
	}

	public static WebResult result(ResultType type, String message, Object obj) {
		Integer flag = SUCCESS;
		if (type == ResultType.ERROR) {
			flag = ERROR;
		} else if (type == ResultType.NULL) {
			flag = NONE;
		}
		WebResult _result = new WebResult();
		_result.setCode(flag);
		_result.setMessage(message);
		_result.setObj(obj);
		return _result;
	}

	/**
	 * 
	 * 方法: success <br>
	 * 描述: 调用接口成功 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月10日 下午4:24:46
	 * 
	 * @param message
	 * @return
	 */
	public static WebResult success(String message) {
		WebResult result = new WebResult();
		result.setCode(SUCCESS);
		result.setMessage(message);
		return result;
	}

	/**
	 * 
	 * 方法: success <br>
	 * 描述: 调用接口成功 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月10日 下午4:25:08
	 * 
	 * @param message
	 * @param obj
	 * @return
	 */
	public static WebResult success(String message, Object obj) {
		WebResult result = new WebResult();
		result.setCode(SUCCESS);
		result.setObj(obj);
		result.setMessage(message);
		return result;
	}

	/**
	 * 
	 * 方法: none <br>
	 * 描述: 访问为空 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月10日 下午4:24:25
	 * 
	 * @param message
	 * @return
	 */
	public static WebResult none(String message) {
		WebResult result = new WebResult();
		result.setCode(NONE);
		result.setMessage(message);
		return result;
	}

	/**
	 * 
	 * 方法: error <br>
	 * 描述: 调用接口失败 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月10日 下午4:24:36
	 * 
	 * @param message
	 * @return
	 */
	public static WebResult error(String message) {
		WebResult result = new WebResult();
		result.setCode(ERROR);
		result.setMessage(message);
		return result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
