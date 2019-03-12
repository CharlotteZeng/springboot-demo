/**
 * 
 */
package com.springbootdemo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Administrator
 *
 */
public class PropertiesUtils {
	private static Logger log = LoggerFactory.getLogger(PropertiesUtils.class);
	
	
	public static Properties prop;
	
	public static Properties getProp() {
		return prop;
	}

	
	public static String  getProperty(String key) {
		return prop.getProperty(key);
	}



	public static void setProp(Properties prop) {
		PropertiesUtils.prop = prop;
	}


	public static final String FILE_NAME_URL="ftp.properties";
	public static Properties props =null;
	public static  long lastModifyTime=0;
	
	public static Properties getProperties(String fileName){
		File file = new File(PropertiesUtils.class.getClassLoader().getResource(fileName).getFile());
		long time = file.lastModified();
		if(lastModifyTime==0||lastModifyTime==time){
			props = getPropertiesFirstTime(fileName);
			lastModifyTime = time;
		}else{
			props = reloadProperties(fileName);
			lastModifyTime = time;
		}
		file=null;
		return props;
	}
	
	
	
	public static Properties getPropertiesFirstTime(String fileName){
		if(props==null){
			InputStream is = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
			try {
				props = new Properties();
				props.load(is);
				log.info("PropertiesUtils#getPropertiesFirstTime the properties file");
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}
	public static Properties reloadProperties(String fileName){
		InputStream is = PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName);
		try {
			props = new Properties();
			props.load(is);
			log.info("PropertiesUtils#reloadProperties RELOAD the properties file");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}
	
	
	public static void main(String[] args) {
//		getProperties(FILE_NAME_URL);
		String  path = PropertiesUtils.class.getClassLoader().getResource(FILE_NAME_URL).getFile();
		System.out.println(path);
	}
	

}
