package zero.commons.basics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类: ObjectUtil <br>
 * 描述: 对象帮助类 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午4:18:26
 */
public class ObjectUtil {

	private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

	/**
	 * 
	 * 方法: getFiledsInfo <br>
	 * 描述: 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:10:00
	 * 
	 * @param o
	 * @return
	 */
	public static List<Map<String, Object>> getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> infoMap = null;
		for (int i = 0; i < fields.length; i++) {
			infoMap = new HashMap<String, Object>();
			infoMap.put("type", fields[i].getType().toString());
			infoMap.put("name", fields[i].getName());
			infoMap.put("value", getFieldValueByName(fields[i].getName(), o));
			list.add(infoMap);
		}
		return list;
	}

	/**
	 * 
	 * 方法: getFiledValues <br>
	 * 描述: 获取对象的所有属性值，返回一个对象数组 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:09:52
	 * 
	 * @param o
	 * @return
	 */
	public static Object[] getFiledValues(Object o) {
		String[] fieldNames = getFiledName(o);
		Object[] value = new Object[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			value[i] = getFieldValueByName(fieldNames[i], o);
		}
		return value;
	}

	/**
	 * 
	 * 方法: getFieldValueByName <br>
	 * 描述: 根据属性名获取属性值 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:10:09
	 * 
	 * @param fieldName
	 * @param o
	 * @return
	 */
	public static Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 
	 * 方法: getFiledName <br>
	 * 描述: 获取属性名数组 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:10:17
	 * 
	 * @param o
	 * @return
	 */
	private static String[] getFiledName(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		String[] fieldNames = new String[fields.length];
		for (int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getType());
			fieldNames[i] = fields[i].getName();
		}
		return fieldNames;
	}

	/**
	 * 
	 * 方法: underline2Camel <br>
	 * 描述: 下划线转驼峰法(默认小驼峰) <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月7日 下午3:50:04
	 * 
	 * @param line       源字符串
	 * @param smallCamel 大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
	 * @return 转换后的字符串
	 */
	public static String underline2Camel(String line, boolean... smallCamel) {
		if (line == null || "".equals(line)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
		Matcher matcher = pattern.matcher(line);
		// 匹配正则表达式
		while (matcher.find()) {
			String word = matcher.group();
			// 当是true 或则是空的情况
			if ((smallCamel.length == 0 || smallCamel[0]) && matcher.start() == 0) {
				sb.append(Character.toLowerCase(word.charAt(0)));
			} else {
				sb.append(Character.toUpperCase(word.charAt(0)));
			}
			int index = word.lastIndexOf('_');
			if (index > 0) {
				sb.append(word.substring(1, index).toLowerCase());
			} else {
				sb.append(word.substring(1).toLowerCase());
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 方法: camel2Underline <br>
	 * 描述: 驼峰法转下划线 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月7日 下午3:53:19
	 * 
	 * @param line 源字符串
	 * @return 转换后的字符串
	 */
	public static String camel2Underline(String line, boolean... smallCamel) {
		if (line == null || "".equals(line)) {
			return "";
		}
		line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
		StringBuffer sb = new StringBuffer();
		Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			String word = matcher.group();
			sb.append(word.toLowerCase());
			sb.append(matcher.end() == line.length() ? "" : "_");
		}
		return sb.toString();
	}
}
