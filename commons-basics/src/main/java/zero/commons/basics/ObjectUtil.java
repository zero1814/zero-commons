package zero.commons.basics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
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
	 * 方法: isExistsFiled <br>
	 * 描述: 判断对象是否包含某个属性 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月14日 下午1:45:43
	 * 
	 * @param name
	 * @param obj
	 * @return
	 */
	public static boolean isExistsFiled(String name, Object obj) {
		boolean flag = false;
		try {
			Field f = getFieldByName(obj.getClass(), name);
			if (f != null) {
				flag = true;
			}
		} catch (Exception e) {
			return false;
		}
		return flag;
	}

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
		List<Field> fields = getFields(o.getClass());
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> infoMap = null;
		for (int i = 0; i < fields.size(); i++) {
			Field f = fields.get(i);
			if (Modifier.isStatic(f.getModifiers())) {
				continue;
			}
			infoMap = new HashMap<String, Object>();
			infoMap.put("type", f.getType().toString());
			infoMap.put("name", f.getName());
			infoMap.put("value", getFieldValueByName(f.getName(), o));
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
			if (!isExistsFiled(fieldName, o)) {
				return null;
			}
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			if (method == null) {
				return null;
			}
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 
	 * 方法: setFieldValueByName <br>
	 * 描述: 为属性赋值 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月14日 下午1:59:59
	 * 
	 * @param fieldName
	 * @param fieldValue
	 * @param o
	 */
	public static void setFieldValueByName(String fieldName, Object fieldValue, Object o) {
		Field f = null;
		try {
			f = getFieldByName(o.getClass(), fieldName);
			f.setAccessible(true);
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String setter = "set" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(setter, fieldValue.getClass());
			if (method != null) {
				method.invoke(o, fieldValue);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
		List<Field> fields = getFields(o.getClass());
		String[] fieldNames = new String[fields.size()];
		for (int i = 0; i < fields.size(); i++) {
			fieldNames[i] = fields.get(i).getName();
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
	 * @param line
	 *            源字符串
	 * @param smallCamel
	 *            大小驼峰,是否为小驼峰(驼峰，第一个字符是大写还是小写)
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
	 * @param line
	 *            源字符串
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

	public static Object newObject(Object obj) {
		try {
			Object _obj = obj.getClass().newInstance();
			List<Map<String, Object>> list = ObjectUtil.getFiledsInfo(obj);
			if (!list.isEmpty()) {
				for (Map<String, Object> map : list) {
					String name = map.get("name").toString();
					if (map.get("value") != null && StringUtils.isNotBlank(map.get("value").toString())) {
						Field field = getFieldByName(_obj.getClass(), name);
						field.setAccessible(true);
						field.set(_obj, map.get("value"));
					}
				}
			}
			return _obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * 方法: prefix <br>
	 * 描述: 获取类名称的首字母 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年5月14日 上午11:26:00
	 * 
	 * @param clazz
	 * @return
	 */
	public static String prefix(Class<?> clazz) {
		String className = clazz.getSimpleName();
		char[] chars = className.toCharArray();
		StringBuffer name = new StringBuffer();
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				name.append(c);
			}
		}
		return name.toString();
	}

	public static List<Field> getFields(Class<?> clazz) {
		List<Field> list = new ArrayList<Field>();
		while (clazz != null) {
			list.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
			clazz = clazz.getSuperclass();
		}
		return list;
	}

	public static List<Map<String, Object>> getFieldsNotNull(Object obj) {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		List<Field> list = getFields(obj.getClass());
		for (Field field : list) {
			if (StringUtils.equalsAny(field.getName(), "serialVersionUID")) {
				continue;
			}
			String name = field.getName();
			Object value = getFieldValueByName(name, obj);
			if (value != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", name);
				map.put("value", value);
				data.add(map);
			}
		}
		return data;
	}

	public static Field getFieldByName(Class<?> clazz, String fieldName) {
		while (clazz != null) {
			List<Field> list = new ArrayList<>(Arrays.asList(clazz.getDeclaredFields()));
			for (Field field : list) {
				if (StringUtils.equals(field.getName(), fieldName)) {
					return field;
				}
			}
			clazz = clazz.getSuperclass();
		}
		return null;
	}
}
