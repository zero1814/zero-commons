package zero.commons.basics.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类: SerializeUtil <br>
 * 描述: Java原生版的 Serialize <br>
 * 作者: zhy<br>
 * 时间: 2017年6月27日 下午1:41:45
 */
@SuppressWarnings("unchecked")
public class SerializeUtil {

	private static final Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

	/**
	 * 
	 * 方法: serialize <br>
	 * 描述: 序列化 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年6月27日 下午2:20:50
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("Can't serialize null");
		}
		byte[] rv = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream os = null;
		try {
			bos = new ByteArrayOutputStream();
			os = new ObjectOutputStream(bos);
			os.writeObject(value);
			os.close();
			bos.close();
			rv = bos.toByteArray();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			close(os);
			close(bos);
		}
		return rv;
	}

	/**
	 * 
	 * 方法: deserialize <br>
	 * 描述: 反序列化 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年6月27日 下午2:20:41
	 * 
	 * @param in
	 * @return
	 */
	public static Object deserialize(byte[] in) {
		return deserialize(in, Object.class);
	}

	/**
	 * 
	 * 方法: deserialize <br>
	 * 描述: 反序列化 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年6月27日 下午2:20:35
	 * 
	 * @param in
	 * @param requiredType
	 * @return
	 */
	public static <T> T deserialize(byte[] in, Class<T>... requiredType) {
		Object rv = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream is = null;
		try {
			if (in != null) {
				bis = new ByteArrayInputStream(in);
				is = new ObjectInputStream(bis);
				rv = is.readObject();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			close(is);
			close(bis);
		}
		return (T) rv;
	}

	private static void close(Closeable closeable) {
		if (closeable != null)
			try {
				closeable.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
	}
}
