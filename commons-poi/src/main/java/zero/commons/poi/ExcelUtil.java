package zero.commons.poi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelUtil {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

	private XSSFWorkbook book;

	private List<Object> data;

	public ExcelUtil() {

	}

	public ExcelUtil(XSSFWorkbook book, List<Object> data) {
		this.book = book;
		this.data = data;
	}

	public void createSheet(XSSFSheet sheet) {
		for (int i = 0; i < data.size(); i++) {
			Object obj = data.get(i);
			if (obj.getClass().isAnnotationPresent(ExcelSheet.class)) {
				XSSFRow row = sheet.createRow(i);
				Field[] fields = obj.getClass().getFields();
				for (int j = 0; j < fields.length; j++) {
					Field f = fields[j];
					if (f.isAnnotationPresent(ExcelField.class)) {
						ExcelField ef = f.getAnnotation(ExcelField.class);
						Object value = getFieldValueByName(f.getName(), obj);
						XSSFCell cell = row.createCell(j);
						setCellValue(cell, value, ef.style(), ef.font());
					}
				}
			}
		}
	}

	/**
	 * 
	 * 方法: setCellStyle <br>
	 * 描述: 设置单元格样式 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午9:43:06
	 * 
	 * @param cell
	 */
	private CellStyle setCellStyle(CellStyleType type, FontType fontType) {
		XSSFDataFormat format = book.createDataFormat();
		CellStyle style = book.createCellStyle();
		style.setFont(setFont(FontType.SONG));
		// 单元格底部样式
		style.setBorderBottom(BorderStyle.THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.index);
		// 单元格左侧样式
		style.setBorderLeft(BorderStyle.THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.index);
		// 单元格顶部样式
		style.setBorderTop(BorderStyle.THIN);
		style.setTopBorderColor(IndexedColors.BLACK.index);
		// 单元格右侧样式
		style.setBorderRight(BorderStyle.THIN);
		style.setRightBorderColor(IndexedColors.BLACK.index);
		if (type == CellStyleType.STRING) {
			style.setAlignment(HorizontalAlignment.LEFT);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
		} else if (type == CellStyleType.NUMBER) {
			style.setAlignment(HorizontalAlignment.RIGHT);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
		} else if (type == CellStyleType.DATE) {
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
		} else if (type == CellStyleType.SPECIFIC_FINANCIAL) {
			style.setAlignment(HorizontalAlignment.RIGHT);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			style.setDataFormat(format.getFormat("_ * #,##0.00_ ;_ * -#,##0.00_ ;_ * \\\"-\\\"??_ ;_ @_ "));
		}
		return style;
	}

	/**
	 * 
	 * 方法: setCellValue <br>
	 * 描述: 设置单元格的值 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午9:43:22
	 * 
	 * @param cell
	 */
	private void setCellValue(XSSFCell cell, Object value, CellStyleType type, FontType fontType) {
		CellStyle style = setCellStyle(type, fontType);
		cell.setCellStyle(style);
		if (value instanceof String) {
			cell.setCellValue(String.valueOf(value));
		} else if (value instanceof Integer) {
			cell.setCellValue(Integer.valueOf(value.toString()));
		} else if (value instanceof Double) {
			cell.setCellValue(Double.valueOf(value.toString()));
		} else if (value instanceof BigDecimal) {
			BigDecimal d = BigDecimal.valueOf(Double.valueOf(value.toString()));
			cell.setCellValue(d.doubleValue());
		}
	}

	/**
	 * 
	 * 方法: setFont <br>
	 * 描述: 设置单元格字体 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午9:43:56
	 * 
	 * @param cell
	 */
	private XSSFFont setFont(FontType type) {
		XSSFFont font = book.createFont();
		font.setBold(false);
		if (type == FontType.SONG) {
			font.setFontName("宋体");
		}
		return null;
	}

	/**
	 * 
	 * 方法: getFieldValueByName <br>
	 * 描述: 获取对象属性值 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年12月5日 上午9:44:43
	 * 
	 * @param fieldName
	 * @param o
	 * @return
	 */
	private Object getFieldValueByName(String fieldName, Object o) {
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
}
