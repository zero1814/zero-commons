package zero.commons.basics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 类: RegexUtil <br>
 * 描述: 正则表达式验证 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午4:16:42
 */
public class RegexUtil {

	/**
	 * 
	 * 方法: isNumeric <br>
	 * 描述: 利用正则表达式判断字符串是否是数字 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:18:48
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		String regex = "[0-9]*";
		return match(str, regex);
	}

	/**
	 * 
	 * 方法: isMail <br>
	 * 描述: 验证邮箱地址<br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:23:27
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMail(String str) {
		String regex = "^[A-Za-z\\\\d]+([-_.][A-Za-z\\\\d]+)*@([A-Za-z\\\\d]+[-.])+[A-Za-z\\\\d]{2,4}$";
		return match(str, regex);
	}

	/**
	 * 
	 * 方法: isPhone <br>
	 * 描述: 验证手机号 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:24:47
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPhone(String str) {
		String regex = "^1(3|4|5|7|8)\\d{9}$";
		return match(str, regex);
	}

	/**
	 * 
	 * 方法: isContainZh <br>
	 * 描述: 是否包含中文 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:28:34
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isContainZh(String str) {
		String regex = "[\\u4e00-\\u9fa5]";
		return match(str, regex);
	}

	/**
	 * 
	 * 方法: match <br>
	 * 描述: 正则验证 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:30:20
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	private static boolean match(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher isPhone = pattern.matcher(str);
		if (!isPhone.matches()) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(RegexUtil.isPhone("134299931"));
	}

}
