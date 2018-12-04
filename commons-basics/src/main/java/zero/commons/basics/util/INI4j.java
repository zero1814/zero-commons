package zero.commons.basics.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * 类: INI4j <br>
 * 描述: 有序读取 ini配置文件 <br>
 * 作者: zhy<br>
 * 时间: 2017年6月28日 下午10:52:39
 */
public class INI4j {

	/**
	 * 用linked hash map 来保持有序的读取
	 */
	final LinkedHashMap<String, LinkedHashMap<String, String>> coreMap = new LinkedHashMap<String, LinkedHashMap<String, String>>();
	/**
	 * 当前Section的引用
	 */
	String currentSection = null;

	/**
	 * 
	 * 标题: 构造器 <br>
	 * 描述: 读取 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:54:16
	 * 
	 * @param file
	 * @throws FileNotFoundException
	 */
	public INI4j(File file) throws FileNotFoundException {
		this.init(new BufferedReader(new FileReader(file)));
	}

	/**
	 * 
	 * 标题: 构造器 <br>
	 * 描述: 重载读取 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:54:30
	 * 
	 * @param path
	 * @throws FileNotFoundException
	 */
	public INI4j(String path) throws FileNotFoundException {
		this.init(new BufferedReader(new FileReader(path)));
	}

	void init(BufferedReader bufferedReader) {
		try {
			read(bufferedReader);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO Exception:" + e);
		}
	}

	/**
	 * 
	 * 方法: read <br>
	 * 描述: 读取文件 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:54:46
	 * 
	 * @param reader
	 * @throws IOException
	 */
	void read(BufferedReader reader) throws IOException {
		String line = null;
		while ((line = reader.readLine()) != null) {
			parseLine(line);
		}
	}

	/**
	 * 
	 * 方法: parseLine <br>
	 * 描述: 转换 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:54:52
	 * 
	 * @param line
	 */
	void parseLine(String line) {
		line = line.trim();
		// 此部分为注释
		if (line.matches("^\\#.*$")) {
			return;
		} else if (line.matches("^\\[\\S+\\]$")) {
			// section
			String section = line.replaceFirst("^\\[(\\S+)\\]$", "$1");
			addSection(section);
		} else if (line.matches("^\\S+=.*$")) {
			// key ,value
			int i = line.indexOf("=");
			String key = line.substring(0, i).trim();
			String value = line.substring(i + 1).trim();
			addKeyValue(currentSection, key, value);
		}
	}

	/**
	 * 
	 * 方法: addKeyValue <br>
	 * 描述: 增加新的Key和Value <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:55:00
	 * 
	 * @param currentSection
	 * @param key
	 * @param value
	 */
	void addKeyValue(String currentSection, String key, String value) {
		if (!coreMap.containsKey(currentSection)) {
			return;
		}
		Map<String, String> childMap = coreMap.get(currentSection);
		childMap.put(key, value);
	}

	/**
	 * 
	 * 方法: addSection <br>
	 * 描述: 增加Section <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:55:06
	 * 
	 * @param section
	 */
	void addSection(String section) {
		if (!coreMap.containsKey(section)) {
			currentSection = section;
			LinkedHashMap<String, String> childMap = new LinkedHashMap<String, String>();
			coreMap.put(section, childMap);
		}
	}

	/**
	 * 
	 * 方法: get <br>
	 * 描述: 获取配置文件指定Section和指定子键的值 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:55:13
	 * 
	 * @param section
	 * @param key
	 * @return
	 */
	public String get(String section, String key) {
		if (coreMap.containsKey(section)) {
			return get(section).containsKey(key) ? get(section).get(key) : null;
		}
		return null;
	}

	/**
	 * 
	 * 方法: get <br>
	 * 描述: 获取配置文件指定Section的子键和值 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:55:21
	 * 
	 * @param section
	 * @return
	 */
	public Map<String, String> get(String section) {
		return coreMap.containsKey(section) ? coreMap.get(section) : null;
	}

	/**
	 * 
	 * 方法: get <br>
	 * 描述: 获取这个配置文件的节点和值 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年7月21日 下午5:55:29
	 * 
	 * @return
	 */
	public LinkedHashMap<String, LinkedHashMap<String, String>> get() {
		return coreMap;
	}

}