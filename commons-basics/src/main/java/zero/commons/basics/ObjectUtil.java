package zero.commons.basics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
