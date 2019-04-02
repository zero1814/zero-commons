package zero.commons.basics;

import javax.servlet.http.HttpServletRequest;

import zero.commons.basics.server.UdpGetClientMacAddr;

/**
 * 
 * 类: ServerUtil <br>
 * 描述: 服务器相关公共类 <br>
 * 作者: zhy<br>
 * 时间: 2019年4月2日 上午9:21:51
 */
public class ServerUtil {

	/**
	 * 
	 * 方法: getClientIpAddr <br>
	 * 描述: 获取客户端ip地址 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年4月2日 上午9:11:01
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_VIA");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 
	 * 方法: getMac <br>
	 * 描述: 获取客户端的mac地址 <br>
	 * 作者: zhy<br>
	 * 时间: 2019年4月2日 上午9:23:00
	 * 
	 * @param ip
	 * @return
	 * @throws Exception
	 */
	public String getMac(HttpServletRequest request) throws Exception {
		String ip = getIp(request);
		if (StringUtils.isBlank(ip)) {
			return null;
		}
		UdpGetClientMacAddr umac = new UdpGetClientMacAddr(ip);
		String mac = umac.GetRemoteMacAddr();
		return mac;
	}

	public String getMac(String ip) throws Exception {
		UdpGetClientMacAddr umac = new UdpGetClientMacAddr(ip);
		String mac = umac.GetRemoteMacAddr();
		return mac;
	}
}
