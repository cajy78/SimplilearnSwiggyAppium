package simpliLearn.swiggyApplication;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import appium.AppiumSetup;
import appium.AppiumSetup.DeviceTypes;
import appium.TestProperties;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Search extends TestCase {

	private AndroidDriver driver;
	private DeviceTypes testDeviceType;

	@BeforeTest
	public void setup() throws Throwable {
		testDeviceType = initiateDeviceType();
		DesiredCapabilities desiredCapabilities = AppiumSetup.getCapabilitiesOfDevice(testDeviceType);
		URL remoteUrl = new URL(TestProperties.getAppiumServerURL());
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(TestProperties.getWaitTimeoutConfig()),
				TimeUnit.SECONDS);
	}

	@Test
	@Parameters({ "searchType", "searchTypeValue" })
	public void runSearchTest(String searchType, String searchTypeValue) throws Throwable {

		if (!searchType.equalsIgnoreCase("resto") && !searchType.equalsIgnoreCase("restaurant")
				&& !searchType.equalsIgnoreCase("dish") && !searchType.equalsIgnoreCase("food")) {
			throw new RuntimeException(
					"Parameter passed for Search Type is incorrect and should be either Resto or Restaurant or Dish or Food (ignore case)");
		} else {

			Thread.sleep(3000);
			takeScreenShot("SplashScreen", driver);
			MobileElement initialLocation = (MobileElement) driver
					.findElementById("in.swiggy.android:id/set_location_text");
			takeScreenShot("Location", driver);
			initialLocation.click();

			switch (testDeviceType) {
			case GalaxyA30s_swiggy:
				MobileElement initLocationPermission = (MobileElement) driver
						.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
				initLocationPermission.click();
				break;
			case Android10:
				break;
			case Android10_Dialer:
				break;
			case Android7:
				MobileElement initLocationPermission_emu7 = (MobileElement) driver
						.findElementById("com.android.packageinstaller:id/permission_allow_button");
				initLocationPermission_emu7.click();
				MobileElement manualSearch_emu7 = (MobileElement) driver
						.findElementById("in.swiggy.android:id/dialog_negative_layout_text");
				manualSearch_emu7.click();
				MobileElement loc_emu7 = (MobileElement) driver
						.findElementById("in.swiggy.android:id/location_description");
				loc_emu7.click();
				loc_emu7.sendKeys("Thane");
				MobileElement selectLoc = (MobileElement) driver.findElementByXPath("//*[@text=\"Thane West\"]");
				selectLoc.click();
				break;
			case iOS:
				break;
			default:
				break;
			}

			MobileElement confLocation = (MobileElement) driver
					.findElementById("in.swiggy.android:id/google_place_search_title_text1");
			takeScreenShot("LocationFound", driver);
			confLocation.click();
			MobileElement initiateSearch = (MobileElement) driver
					.findElementById("in.swiggy.android:id/bottom_bar_explore");
			initiateSearch.click();
			takeScreenShot("SearchClicked", driver);
			MobileElement searchBar = (MobileElement) driver
					.findElementByXPath("//*[@text=\"Search for restaurants and food\"]"); // ("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.EditText");
			searchBar.click();

			if (searchType.equalsIgnoreCase("Restaurant") || searchType.equalsIgnoreCase("Resto")) {
				searchBar.sendKeys(searchTypeValue);
				takeScreenShot("SearchValueEntered", driver);
				driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "Search"));
				WebDriverWait wait = new WebDriverWait(driver, Integer.parseInt(TestProperties.getWaitTimeoutConfig()));
				wait.until(ExpectedConditions
						.visibilityOfAllElementsLocatedBy(By.xpath("//*[@text='" + searchTypeValue + "']")));
				List<MobileElement> searchResults = driver.findElementsByXPath(
						"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
						+ "/android.widget.FrameLayout/android.widget.LinearLayout/"
						+ "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/"
						+ "android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout"
						+ "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout"
						+ "/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup");//// *[@id='in.swiggy.android:id/search_results']/android.view.ViewGroup");
																																																																																																																											//// ///hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup
				System.out.println("Number of elements found: " + searchResults.size());
				takeScreenShot("SearchResultsFound", driver);
				MobileElement restaurantSearch;

				if (searchResults.size() >= 1) {
					restaurantSearch = (MobileElement) searchResults.get(0);
					restaurantSearch.click();
					if (checkRatingDialogPopup())
						driver.navigate().back();
					String restName = driver.findElementById("in.swiggy.android:id/restaurant_name").getText();
					takeScreenShot("RestaurantClicked", driver);
					if (restName.contains(searchTypeValue))
						Assert.assertTrue(true);
					else
						Assert.assertTrue(false);
				} else if (searchResults.size() < 1) {
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(false);
				}
			} else if (searchType.equalsIgnoreCase("Dish") || searchType.equalsIgnoreCase("Food")) {
				searchBar.sendKeys(searchTypeValue);
				takeScreenShot("SearchValueEntered", driver);
				driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "Search"));
				Thread.sleep(5000);
				List<MobileElement> searchResults = driver
						.findElementsByXPath("//*[contains(@text,'" + searchTypeValue + "')]");
				System.out.println("Number of elements found: " + searchResults.size());
				takeScreenShot("SearchResultsFound", driver);

				if (searchResults.size() > 1) {
					Assert.assertTrue(true);
				} else if (searchResults.size() <= 1) {
					throw new RuntimeException(
							"A problem occurred due to which no search results were found or search value has a problem");
				}
			}
		}
	}

	public boolean checkRatingDialogPopup() {
		try {
			driver.findElementById("in.swiggy.android:id/dialog_content");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@AfterTest
	public void tearDown() throws Throwable {
		if (!driver.equals(null))
			driver.quit();
	}
}
