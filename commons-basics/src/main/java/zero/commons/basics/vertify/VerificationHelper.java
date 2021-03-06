package zero.commons.basics.vertify;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zero.commons.basics.ObjectUtil;

/**
 * 
 * 类: VerificationHelper <br>
 * 描述: 验证帮助类 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月19日 下午10:30:56
 */
public class VerificationHelper {
	private static final Logger logger = LoggerFactory.getLogger(VerificationHelper.class);

	/**
	 * 
	 * 方法: vertifyParam <br>
	 * 描述: 根据验证类型，验证对象属性值 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月19日 下午10:31:10
	 * 
	 * @param obj  验证对象
	 * @param type 验证类型
	 * @return
	 */
	public static boolean vertifyParam(Object obj, VertifyType type) {
		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				// 判断对象属性是否包含注解Verify
				boolean isPresned = field.isAnnotationPresent(Verify.class);
				if (isPresned) {
					if (type == VertifyType.INSERT) {
						// 添加操作
						if (field.getAnnotation(Verify.class).insertable()) {
							Object o = ObjectUtil.getFieldValueByName(field.getName(), obj);
							if (field.getAnnotation(Verify.class).nullable() == false && o == null) {
								return false;
							}
						}
					} else if (type == VertifyType.UPDATE) {
						// 编辑操作
						if (field.getAnnotation(Verify.class).updateable()) {
							Object o = ObjectUtil.getFieldValueByName(field.getName(), obj);
							if (field.getAnnotation(Verify.class).nullable() == false && o == null) {
								return false;
							}
						}

					} else if (type == VertifyType.DELETE) {
						// 删除操作
						if (field.getAnnotation(Verify.class).deleteable()) {
							Object o = ObjectUtil.getFieldValueByName(field.getName(), obj);
							if (field.getAnnotation(Verify.class).nullable() == false && o == null) {
								return false;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), VerificationHelper.class);
		}
		return true;
	}
}
