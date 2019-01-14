package zero.commons.basics.helper;

import java.util.UUID;

import zero.commons.basics.CodeUtil;

/**
 * 
 * 类: CodeHelper <br>
 * 描述: 编码生成帮助类 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月24日 上午8:59:51
 */
public class CodeHelper {
	/**
	 * 
	 * 方法: getUUID <br>
	 * 描述: 获取uid <br>
	 * 作者: zhy<br>
	 * 时间: 2018年4月8日 下午5:15:50
	 * 
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 
	 * 方法: getCode <br>
	 * 描述: 获取唯一编码 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年10月26日 上午11:56:06
	 * 
	 * @param prefix
	 * @return
	 */
	public static String getCode(String prefix) {
		String code = prefix + CodeUtil.getInstance().nextId();
		return code;
	}
}
