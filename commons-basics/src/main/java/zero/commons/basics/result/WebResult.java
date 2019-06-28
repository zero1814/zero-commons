package zero.commons.basics.result;

import zero.commons.basics.Page;

public class WebResult {

	private static final Integer SUCCESS = 200;
	private static final Integer NONE = 404;
	private static final Integer ERROR = 500;
	private Integer code;
	private String message;
	private Object obj;

	/**
	 * 
	 * 方法: result <br>
	 * 描述: 基础结果集 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月13日 上午9:18:31
	 * 
	 * @param base
	 * @return
	 */
	public static WebResult result(BaseResult base) {
		WebResult result = new WebResult();
		Integer code = changeCode(base.getCode());
		result.setCode(code);
		result.setMessage(base.getMessage());
		return result;
	}

	/**
	 * 
	 * 方法: obj <br>
	 * 描述: 返回对象结果集 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月13日 上午9:18:48
	 * 
	 * @param entity
	 * @return
	 */
	public static WebResult obj(EntityResult<?> entity) {
		WebResult result = new WebResult();
		Integer code = changeCode(entity.getCode());
		result.setCode(code);
		result.setMessage(entity.getMessage());
		result.setObj(entity.getEntity());
		return result;
	}

	public static WebResult obj(RootResult root) {
		WebResult result = new WebResult();
		Integer code = changeCode(root.getCode());
		result.setCode(code);
		result.setMessage(root.getMessage());
		result.setObj(root.getObj());
		return result;
	}

	/**
	 * 
	 * 方法: data <br>
	 * 描述: 返回对象集合结果集 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月13日 上午9:18:58
	 * 
	 * @param data
	 * @return
	 */
	public static WebResult data(DataResult<?> data) {
		WebResult result = new WebResult();
		Integer code = changeCode(data.getCode());
		result.setCode(code);
		result.setMessage(data.getMessage());
		result.setObj(data.getData());
		return result;
	}

	/**
	 * 
	 * 方法: page <br>
	 * 描述: 分页查询结果集 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月13日 上午9:11:07
	 * 
	 * @param page
	 * @return
	 */
	public static WebResult page(PageResult<?> page) {
		WebResult result = new WebResult();
		if (page.getCode() != ResultType.SUCCESS) {
			return result(page);
		}
		Page _page = new Page();
		_page.setTotal(page.getTotal());
		_page.setData(page.getData());
		result.setObj(_page);
		result.setCode(SUCCESS);
		result.setMessage(page.getMessage());
		return result;
	}

	public static WebResult success(String message) {
		WebResult result = new WebResult();
		result.setMessage(message);
		result.setCode(SUCCESS);
		return result;
	}

	public static WebResult error(String message) {
		WebResult result = new WebResult();
		result.setMessage(message);
		result.setCode(ERROR);
		return result;
	}

	public static WebResult none(String message) {
		WebResult result = new WebResult();
		result.setMessage(message);
		result.setCode(NONE);
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

	private static Integer changeCode(ResultType type) {
		Integer code = ERROR;
		if (type == ResultType.SUCCESS) {
			code = SUCCESS;
		} else if (type == ResultType.NULL) {
			code = NONE;
		}
		return code;
	}
}
