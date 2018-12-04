package zero.commons.basics;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 类: DateUtil <br>
 * 描述: 时间帮助类 <br>
 * 作者: zhy<br>
 * 时间: 2018年10月23日 上午11:33:00
 */
public class DateUtil {

	// 年-月-日 小时:分钟:秒
	private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat TIME_SDF = new SimpleDateFormat(TIME_FORMAT);

	/**
	 * 
	 * 方法: curTime <br>
	 * 描述: 获取当前时间 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年10月23日 下午2:11:55
	 * 
	 * @return
	 */
	public static String curSystemTime() {
		return TIME_SDF.format(new Date());
	}

	/**
	 * 
	 * 方法: getTime <br>
	 * 描述: 时间格式化字符串 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年10月23日 下午2:12:26
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return TIME_SDF.format(date);
	}

	public static Date nextDateTime(Integer day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
		return cal.getTime();
	}

	public static String nextDateTimeStr(Integer day) {
		return TIME_SDF.format(nextDateTime(day));
	}

	public static String nextDateTimeStr(Date date, Integer day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + day);
		return TIME_SDF.format(cal.getTime());
	}

	/**
	 * 
	 * 方法: stringToDate <br>
	 * 描述: 字符串格式化时间格式 <br>
	 * 作者: zhy<br>
	 * 时间: 2018年10月23日 下午2:13:37
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String time) throws ParseException {
		return TIME_SDF.parse(time);
	}

	public static String difftime(String date) {
		if (StringUtils.isBlank(date)) {
			return null;
		}
		String interval = null;
		ParsePosition pos = new ParsePosition(0);
		Date d1 = (Date) TIME_SDF.parse(date, pos);
		// 用现在距离1970年的时间间隔new
		// Date().getTime()减去以前的时间距离1970年的时间间隔d1.getTime()得出的就是以前的时间与现在时间的时间间隔
		long time = new Date().getTime() - d1.getTime();// 得出的时间间隔是毫秒

		if (time / 1000 < 10 && time / 1000 >= 0) {
			// 如果时间间隔小于10秒则显示“刚刚”time/10得出的时间间隔的单位是秒
			interval = "刚刚";
		} else if (time / 3600000 < 24 && time / 3600000 >= 0) {
			// 如果时间间隔小于24小时则显示多少小时前
			int h = (int) (time / 3600000);// 得出的时间间隔的单位是小时
			interval = h + "小时前";
		} else if (time / 60000 < 60 && time / 60000 > 0) {
			// 如果时间间隔小于60分钟则显示多少分钟前
			int m = (int) ((time % 3600000) / 60000);// 得出的时间间隔的单位是分钟
			interval = m + "分钟前";
		} else if (time / 1000 < 60 && time / 1000 > 0) {
			// 如果时间间隔小于60秒则显示多少秒前
			int se = (int) ((time % 60000) / 1000);
			interval = se + "秒前";
		} else {
			// 大于24小时，则显示正常的时间，但是不显示秒
			ParsePosition pos2 = new ParsePosition(0);
			Date d2 = (Date) TIME_SDF.parse(date, pos2);
			interval = TIME_SDF.format(d2);
		}
		return interval;
	}
}
