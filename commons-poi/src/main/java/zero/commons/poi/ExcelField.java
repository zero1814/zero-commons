package zero.commons.poi;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 类: ExcelField <br>
 * 描述: excel操作列注解 <br>
 * 作者: zhy<br>
 * 时间: 2018年12月4日 下午5:33:05
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

	/**
	 *
	 * 方法: title <br>
	 * 描述: 标题 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月4日 下午5:33:49
	 * 
	 * @return
	 */
	String title() default "";

	/**
	 * 
	 * 方法: style <br>
	 * 描述: 列样式 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午8:15:36
	 * 
	 * @return
	 */
	CellStyleType style() default CellStyleType.STRING;
	
	/**
	 * 
	 * 方法: font <br>
	 * 描述: 列字体 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午8:24:55
	 * @return
	 */
    FontType font() default FontType.SONG;
}
