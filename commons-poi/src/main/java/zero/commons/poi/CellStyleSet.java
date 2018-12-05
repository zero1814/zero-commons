package zero.commons.poi;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class CellStyleSet {

	public static XSSFCellStyle style(CellStyleType type) {
		if (type == CellStyleType.STRING) {
			return string();
		} else if (type == CellStyleType.NUMBER) {
			return number();
		} else if (type == CellStyleType.DATE) {
			return date();
		}
		return null;
	}

	/**
	 * 
	 * 方法: string <br>
	 * 描述: 字符串样式 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午8:48:55
	 * 
	 * @return
	 */
	private static XSSFCellStyle string() {
		XSSFCellStyle style = new XSSFCellStyle(new StylesTable());
		style.setAlignment(HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}

	/**
	 * 
	 * 方法: number <br>
	 * 描述: 数字样式 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午8:49:05
	 * 
	 * @return
	 */
	private static XSSFCellStyle number() {
		XSSFCellStyle style = new XSSFCellStyle(new StylesTable());
		style.setAlignment(HorizontalAlignment.RIGHT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}

	private static XSSFCellStyle date() {
		XSSFCellStyle style = new XSSFCellStyle(new StylesTable());
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		return style;
	}
}
