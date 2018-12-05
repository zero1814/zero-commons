package zero.commons.basics.result;

import java.util.List;

public class PageResult<T> extends BaseResult{

	private Long total;
	
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
