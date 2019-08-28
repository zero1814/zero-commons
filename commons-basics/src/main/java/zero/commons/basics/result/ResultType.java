package zero.commons.basics.result;

/**
 * 
 * 类: ResultType <br>
 * 描述: 结果集类型 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午5:24:07
 */
public enum ResultType {

	SUCCESS(200, "操作成功"),
	ERROR(500, "操作失败"),
	NULL(404,"查询为空"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");
	private long code;
	private String message;

	private ResultType(long code, String message) {
	        this.code = code;
	        this.message = message;
	    }

	public long getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
