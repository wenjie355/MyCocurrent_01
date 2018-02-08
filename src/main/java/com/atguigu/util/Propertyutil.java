package com.atguigu.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Propertyutil {
	public static String  getProperty(String key,String path) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = Propertyutil.class.getClassLoader().getResourceAsStream(path);
		properties.load(inputStream);
		String property = properties.getProperty(key);
		return property;
	}
}
