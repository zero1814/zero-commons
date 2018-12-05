package zero.commons.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 类: ExcelSheet <br>
 * 描述: excel对应sheet相关设置 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月5日 上午9:15:18
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelSheet {

	/**
	 * 
	 * 方法: name <br>
	 * 描述: sheet名称 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午9:15:47
	 * @return
	 */
	String name() default "sheet";

	/**
	 * 
	 * 方法: sort <br>
	 * 描述: sheet排序 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午9:15:59
	 * @return
	 */
	int sort() default 0;
}
