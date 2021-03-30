package appium;

import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumSetup {
	public enum DeviceTypes {
		Android10, iOS, Android7, Android10_Dialer, GalaxyA30s_swiggy, Oneplus8t_A11_swiggy
	}

	public static DesiredCapabilities getCapabilitiesOfDevice(DeviceTypes type) {
		switch (type) {
		case Android7:
			return createAndroid7Capabilities();
		case Android10:
			return createAndroid10Capabilities();
		case Android10_Dialer:
			return createAndroid10CapabilitiesForDialer();
		case GalaxyA30s_swiggy:
			return createGalaxyA30sSwiggyCapabilities();
		case Oneplus8t_A11_swiggy:
			return createOneplus8tSwiggyCapabilities();
		default:
			throw new RuntimeException("Device type invalid" + type);
		}
	}

	private static DesiredCapabilities createAndroid7Capabilities() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "7.1.1");
		desiredCapabilities.setCapability("deviceName", "Android SDK built for x86");
		desiredCapabilities.setCapability("appPackage", "in.swiggy.android");
		desiredCapabilities.setCapability("appActivity", "in.swiggy.android.activities.HomeActivity");
		desiredCapabilities.setCapability("appWaitActivity", "in.swiggy.android.activities.NewUserExperienceActivity");
		desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
		return desiredCapabilities;
	}
	
	private static DesiredCapabilities createGalaxyA30sSwiggyCapabilities() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "10");
		desiredCapabilities.setCapability("deviceName", "Galaxy A30s");
		desiredCapabilities.setCapability("appPackage", "in.swiggy.android");
		desiredCapabilities.setCapability("appActivity", "in.swiggy.android.activities.HomeActivity");
		desiredCapabilities.setCapability("appWaitActivity", "in.swiggy.android.activities.NewUserExperienceActivity");
		desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
		return desiredCapabilities;
	}
	
	private static DesiredCapabilities createOneplus8tSwiggyCapabilities() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "11");
		desiredCapabilities.setCapability("deviceName", "Cajy's OnePlus 8T");
		desiredCapabilities.setCapability("appPackage", "in.swiggy.android");
		desiredCapabilities.setCapability("appActivity", "in.swiggy.android.activities.HomeActivity");
		desiredCapabilities.setCapability("appWaitActivity", "in.swiggy.android.activities.NewUserExperienceActivity");
		desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
		return desiredCapabilities;
	}

	private static DesiredCapabilities createAndroid10Capabilities() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "10");
		desiredCapabilities.setCapability("deviceName", "Android SDK built for x86");
		desiredCapabilities.setCapability("appPackage", "com.google.android.calculator");
		desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
		return desiredCapabilities;
	}
	
	private static DesiredCapabilities createAndroid10CapabilitiesForDialer() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "10");
		desiredCapabilities.setCapability("deviceName", "Android SDK built for x86");
		desiredCapabilities.setCapability("appPackage", "com.android.dialer");
		desiredCapabilities.setCapability("appActivity", "com.android.dialer.main.impl.MainActivity");
		desiredCapabilities.setCapability("ensureWebviewsHavePages", true);
		return desiredCapabilities;
	}
}
