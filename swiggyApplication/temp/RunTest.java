package simpliLearn.swiggyApplication;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appium.AppiumSetup;
import appium.AppiumSetup.DeviceTypes;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class RunTest
{
	private AndroidDriver driver;
	
	@BeforeMethod
	public void setup() throws Throwable
	{
		DesiredCapabilities desiredCapabilities = AppiumSetup.getCapabilitiesOfDevice(DeviceTypes.GalaxyA30s_swiggy);
		URL remoteUrl = new URL("http://localhost:4723/wd/hub");
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void executeTest() throws Throwable
	{
		MobileElement el1 = (MobileElement) driver.findElementById("in.swiggy.android:id/set_location_text");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementById("in.swiggy.android:id/google_place_search_title_text1");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementById("in.swiggy.android:id/bottom_bar_explore");
		el4.click();
		MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText");
		el5.click();
		el5.sendKeys("Pizza Hut");
		MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]");
		el6.click();
		MobileElement el7 = (MobileElement) driver.findElementById("in.swiggy.android:id/restaurant");
		el7.click();
		Thread.sleep(5000);
	}
	
	@Test
	public void runPizzaHutTest()
	{
		MobileElement el1 = (MobileElement) driver.findElementById("in.swiggy.android:id/set_location_text");
		el1.click();
		MobileElement el2 = (MobileElement) driver.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
		el2.click();
		MobileElement el3 = (MobileElement) driver.findElementById("in.swiggy.android:id/google_place_search_title_text1");
		el3.click();
		MobileElement el4 = (MobileElement) driver.findElementById("in.swiggy.android:id/bottom_bar_explore");
		el4.click();
		MobileElement el5 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText");
		el5.click();
		el5.sendKeys("Pizza Hut");
		MobileElement el6 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]");
		el6.click();
		MobileElement el7 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.widget.TextView[1]");
		el7.click();
/*		(new TouchAction(driver))
		  .press(PointOption.point((310, 1160)))
		  .moveTo(323,779)
		  .release()
		  .perform();*/
		
		TouchAction action = new TouchAction(driver);
		action.longPress(PointOption.point(310, 1160)).moveTo(PointOption.point(310, 779)).release().perform();
		  
		MobileElement el8 = (MobileElement) driver.findElementByXPath("(//android.widget.RelativeLayout[@content-desc=\"Add Item\"])[1]");
		el8.click();
		MobileElement el9 = (MobileElement) driver.findElementById("in.swiggy.android:id/progressive_variants_continue_button");
		el9.click();
		el9.click();
		MobileElement el10 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.TextView[2]");
		el10.click();
		MobileElement el11 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[5]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.TextView");
		el11.click();
		MobileElement el12 = (MobileElement) driver.findElementById("in.swiggy.android:id/cartLoginToProceedLayoutButton");
		el12.click();
	}
	
	@AfterTest
	public void exit()
	{
		driver.quit();
	}
}
