package zero.commons.basics.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CookieUtil {

	private static final Logger logger = LoggerFactory.getLogger(CookieUtil.class);

	/**
	 * 
	 * 方法: addCookie <br>
	 * 描述: 设置cookie <br>
	 * 作者: zhy<br>
	 * 时间: 2017年6月28日 上午9:14:32
	 * 
	 * @param response
	 * @param name     cookie名字
	 * @param value    cookie值
	 * @param maxAge   cookie生命周期 以秒为单位
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		try {
			Cookie cookie = new Cookie(name, value);
			if (maxAge > 0)
				cookie.setMaxAge(maxAge);
			cookie.setPath("/");
			response.addCookie(cookie);
		} catch (Exception ex) {
			logger.error("创建Cookies发生异常！" + ex.getMessage());
		}
	}

	/**
	 * 
	 * 方法: clearCookie <br>
	 * 描述: 清空Cookie操作 clearCookie <br>
	 * 作者: zhy<br>
	 * 时间: 2017年6月28日 上午9:15:12
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @return
	 */
	public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		boolean bool = false;
		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length == 0)
			return bool;
		try {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = new Cookie(name, null);
				cookie.setMaxAge(0);
				cookie.setPath("/");// 根据你创建cookie的路径进行填写
				response.addCookie(cookie);
				bool = true;
			}
		} catch (Exception ex) {
			logger.error("清空Cookies发生异常！" + ex.getMessage());
		}
		return bool;
	}

	/**
	 * 
	 * 方法: clearCookie <br>
	 * 描述: 清空Cookie操作 clearCookie <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:58:21
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param domain
	 * @return
	 */
	public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response, String name,
			String domain) {
		boolean bool = false;
		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length == 0)
			return bool;
		try {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = new Cookie(name, null);
				cookie.setMaxAge(0);
				cookie.setPath("/");// 根据你创建cookie的路径进行填写
				cookie.setDomain(domain);
				response.addCookie(cookie);
				bool = true;
			}
		} catch (Exception ex) {
			logger.error("清空Cookies发生异常！" + ex.getMessage());
		}
		return bool;
	}

	/**
	 * 
	 * 方法: findCookieByName <br>
	 * 描述: 获取指定cookies的值 findCookieByName <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:58:31
	 * 
	 * @param request
	 * @param name
	 * @return
	 */
	public static String findCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies || cookies.length == 0)
			return null;
		String string = null;
		try {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				String cname = cookie.getName();
				if (!StringUtils.isBlank(cname) && cname.equals(name)) {
					string = cookie.getValue();
				}

			}
		} catch (Exception ex) {
			logger.error("获取Cookies发生异常！" + ex.getMessage());
		}
		return string;
	}

}
