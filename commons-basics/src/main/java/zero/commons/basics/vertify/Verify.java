package zero.commons.basics.vertify;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 类: Verify <br>
 * 描述: 验证注解 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午4:45:26
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Verify {

	/**
	 * 
	 * 方法: nullable <br>
	 * 描述: 判断是否为空 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:48:25
	 * 
	 * @return
	 */
	boolean nullable() default true;

	/**
	 * 
	 * 方法: insertable <br>
	 * 描述: 添加操作是否验证 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:51:34
	 * 
	 * @return
	 */
	boolean insertable() default false;

	/**
	 * 
	 * 方法: updatetable <br>
	 * 描述: 编辑操作是否验证 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:51:48
	 * 
	 * @return
	 */
	boolean updateable() default false;

	/**
	 * 
	 * 方法: deleteable <br>
	 * 描述: 删除操作是否验证 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午4:52:22
	 * 
	 * @return
	 */
	boolean deleteable() default false;
}
