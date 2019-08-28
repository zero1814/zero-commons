package zero.commons.basics.result;

import java.util.List;

/**
 * 
 * 类: DataResult <br>
 * 描述: 集合结果集 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午5:26:02
 */
public class DataResult<T> extends BaseResult {

	/**
	 * 数据集合总数
	 */
	private Long total;
	/**
	 * 数据集合
	 */
	private List<T> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
