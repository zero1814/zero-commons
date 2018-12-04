package zero.commons.basics;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 类: MD5Util <br>
 * 描述: md5生成 <br>
 * 作者: zhy<br>
 * 时间: 2016年8月9日 上午8:15:44
 */
public class MD5Util {

	private MD5Util() {
	}

	static MessageDigest getDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] md5(byte[] data) {
		return getDigest().digest(data);
	}

	public static byte[] md5(String data) {
		return md5(data.getBytes());
	}

	public static String md5Hex(byte[] data) {
		return HexUtil.toHexString(md5(data));
	}

	public static String md5Hex(String data) {
		return HexUtil.toHexString(md5(data));
	}
}
