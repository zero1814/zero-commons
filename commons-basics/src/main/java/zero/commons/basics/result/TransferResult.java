package zero.commons.basics.result;

import java.util.List;

/**
 * 
 * 类: TransferResult <br>
 * 描述: 穿梭框结果集 <br>
 * 作者: zhy<br>
 * 时间: 2019年1月17日 下午4:57:55
 * 
 * @param <T>
 */
public class TransferResult<T> extends BaseResult {

	/**
	 * 左侧显示框结果集
	 */
	private List<T> left;

	/**
	 * 右侧显示框结果集
	 */
	private List<T> right;

	public List<T> getLeft() {
		return left;
	}

	public void setLeft(List<T> left) {
		this.left = left;
	}

	public List<T> getRight() {
		return right;
	}

	public void setRight(List<T> right) {
		this.right = right;
	}

}
