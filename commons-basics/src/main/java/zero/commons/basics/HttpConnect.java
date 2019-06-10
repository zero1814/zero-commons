package zero.commons.basics;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 
 * 类: HttpConnect <br>
 * 描述: http请求公共类 <br>
 * 作者: zhy<br>
 * 时间: 2019年6月3日 下午1:49:31
 */
public class HttpConnect {
	/**
	 * 执行请求
	 * 
	 * @param url
	 * @param postData
	 * @return String
	 */
	public static String doPost(String url, String postData) {
		String result = null;
		HttpPost post = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			post = new HttpPost(url);
			post.setHeader(HTTP.CONTENT_TYPE, "application/json; charset=UTF-8");
			post.setHeader("Accept", "application/json; charset=UTF-8");
			HttpResponse response = client.execute(post);
			int rspCode = response.getStatusLine().getStatusCode();
			if (rspCode == 200) {
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
				return result;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (post != null) {
				post.releaseConnection();
			}
		}
		return null;
	}

	public static String doGet(String url) {
		String result = null;
		HttpGet get = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			get = new HttpGet(url);
			get.setHeader(HTTP.CONTENT_TYPE, "application/json; charset=UTF-8");
			get.setHeader("Accept", "application/json; charset=UTF-8");
			HttpResponse response = client.execute(get);
			int rspCode = response.getStatusLine().getStatusCode();
			if (rspCode == 200) {
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
				return result;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (get != null) {
				get.releaseConnection();
			}
		}
		return null;
	}

	public static String doGet(String url, Map<String, String> params) {
		String result = null;
		HttpGet get = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			String _url = getUrlWithQueryString(url, params);
			get = new HttpGet(_url);
			get.setHeader(HTTP.CONTENT_TYPE, "application/json; charset=UTF-8");
			get.setHeader("Accept", "application/json; charset=UTF-8");
			HttpResponse response = client.execute(get);
			int rspCode = response.getStatusLine().getStatusCode();
			if (rspCode == 200) {
				result = EntityUtils.toString(response.getEntity(), "UTF-8");
				return result;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (get != null) {
				get.releaseConnection();
			}
		}
		return null;
	}

	public static String getUrlWithQueryString(String url, Map<String, String> params) {
		if (params == null) {
			return url;
		}
		StringBuilder builder = new StringBuilder(url);
		if (url.contains("?")) {
			builder.append("&");
		} else {
			builder.append("?");
		}
		int i = 0;
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value == null) { // 过滤空的key
				continue;
			}
			if (i != 0) {
				builder.append('&');
			}
			builder.append(key);
			builder.append('=');
			builder.append(encode(value));
			i++;
		}
		return builder.toString();
	}

	/**
	 * 对输入的字符串进行URL编码, 即转换为%20这种形式
	 * 
	 * @param input
	 *            原文
	 * @return URL编码. 如果编码失败, 则返回原文
	 */
	public static String encode(String input) {
		if (input == null) {
			return "";
		}
		try {
			return URLEncoder.encode(input, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return input;
	}
}
