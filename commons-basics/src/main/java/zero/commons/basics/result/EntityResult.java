package zero.commons.basics.result;

/**
 * 
 * 类: EntityResult <br>
 * 描述: 对象结果集 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午5:26:34
 * 
 * @param <T>
 */
public class EntityResult<T> extends BaseResult {

	/**
	 * 返回结果集的对象
	 */
	private T entity;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

}
