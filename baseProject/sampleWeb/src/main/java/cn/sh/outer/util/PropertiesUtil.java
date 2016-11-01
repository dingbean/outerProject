package cn.sh.outer.util;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件工具
 * @modify yan
 * @date 2014/5/19
 */
public class PropertiesUtil {

	private static final Logger LOGGER = Logger.getLogger(PropertiesUtil.class);

	private static Map<String, Properties> cache = new HashMap<String, Properties>();
	
	public static final String OPENWEIXIN = "conf/openweixin.properties";
	
	public static final String CONFIG = "conf/config.properties";
	
	public static final String WEIBO = "conf/weibo.properties";
	
	public static final String APP = "conf/app.properties";
	
	
	/**
	 * 加载配置文件
	 * 
	 * @param fileName
	 *            系统路径
	 * @return 返回Properties对象
	 */
	public static Properties loadProperties(String fileName) {
		try {
			
			Properties properties = cache.get(fileName);
			
			if(properties == null) {
				
				properties = new Properties();
				properties.load(PropertiesUtil.class.getClassLoader()
						.getResourceAsStream(fileName));
				cache.put(fileName, properties);
			}
			
			return properties;
			
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("加载Properties[" + fileName + "]配置文件失败：", e);
		}
		return null;
	}

	/**
	 * 用key获取Properties配置文件么个属性的值
	 * 
	 * @param fileName
	 *            要加载的Properties配置文件名
	 * @param key
	 *            键
	 * @return 返回获取结果
	 */
	public static String getProperty(String fileName, String key) {
		Properties loadProperties = loadProperties(fileName);
		return loadProperties.getProperty(key);

	}

	/**
	 * 用key给Properties配置文件么个属性赋值
	 * 
	 * @param fileName
	 *            要加载的Properties配置文件名
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @return 返回获取结果
	 */
	public static void setProperty(String fileName, String key, String value) {
		Properties loadProperties = loadProperties(fileName);
		loadProperties.setProperty(key, value);
	}

}
