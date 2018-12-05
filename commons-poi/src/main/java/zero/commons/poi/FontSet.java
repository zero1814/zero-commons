package zero.commons.poi;

import org.apache.poi.xssf.usermodel.XSSFFont;

public class FontSet {

	public XSSFFont font(FontType type) {
		XSSFFont font = new XSSFFont();
		font.setFontName("宋体");
		font.setBold(false);
		return font;
	}
}
