package appium;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperties {
	private static Properties prop;

	private static void getPropertiesFile() {
		prop = new Properties();
		try {
			String projDir = System.getProperty("user.dir");
			FileInputStream fin = new FileInputStream(projDir + "/src/test/java/appium/conf.properties");
			prop.load(fin);
			fin.close();
		} catch (Exception e) {
			System.err.println("An exception has occurred while reading the properties file");
			e.printStackTrace();
		}
	}

	public static String getAppiumServerURL() {
		getPropertiesFile();
		return prop.getProperty("appium.serverurl");
	}
	
	public static String getScreenshotDir()
	{
		getPropertiesFile();
		return prop.getProperty("screenshot.location");
	}
	
	public static String getWaitTimeoutConfig()
	{
		getPropertiesFile();
		return prop.getProperty("wait.timeout");
	}
}
