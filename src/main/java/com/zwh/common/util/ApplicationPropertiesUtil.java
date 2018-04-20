package com.zwh.common.util;

import java.util.ResourceBundle;

/**
 * properties配置文件读取 alter by luolm 2017-03-17
 */
public class ApplicationPropertiesUtil {
	public static final ResourceBundle SpringBoot = ResourceBundle.getBundle("application");

	public static final ResourceBundle ACTIVE_PROPERTIES = getActiveProperty();

	private static ResourceBundle getActiveProperty() {
		String active = getRootPropertity();
		if (StringUtils.isEmpty(active)) {
			return SpringBoot;
		}
		return ResourceBundle.getBundle("application-" + active.trim());
	}

	private static String getRootPropertity() {
		try {
			return SpringBoot.getString("spring.profiles.active");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断是否生产环境
	 * 
	 * @return
	 */
	public static boolean isProductionEnvironment() {
		return "prod".equals(getRootPropertity());
	}

	/**
	 * 判断是否测试环境
	 * 
	 * @return
	 */
	public static boolean isTestEnvironment() {
		return "test".equals(getRootPropertity());
	}

	/**
	 * 判断是否开发环境
	 * 
	 * @return
	 */
	public static boolean isDevelopmentEnvironment() {
		return "dev".equals(getRootPropertity());
	}

}
