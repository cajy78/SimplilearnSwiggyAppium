package simpliLearn.swiggyApplication;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import appium.TestProperties;
import appium.AppiumSetup.DeviceTypes;
import io.appium.java_client.android.AndroidDriver;

public class TestCase {

	private DeviceTypes deviceType;
	
	public DeviceTypes initiateDeviceType()
	{
		deviceType = DeviceTypes.GalaxyA30s_swiggy;
		return deviceType;
	}
	
	public void takeScreenShot(String fileName, AndroidDriver driver) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		String destFile = fileName + "-" + dateFormat.format(new Date()) + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(TestProperties.getScreenshotDir() + "\\" + destFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
