package proactivechecker.auxiliary;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class Utility {
	
	private static String fileName = "util/config.properties";
	private static Properties properties;
	
	private static void loadPropertiesInstance(){
		try {
			if (properties == null){
				properties = new Properties();
				properties.load(new FileInputStream(fileName));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key, String value) {
		loadPropertiesInstance();
		String result = properties.getProperty(key);
		return (result != null ? result : value);
	}

}
