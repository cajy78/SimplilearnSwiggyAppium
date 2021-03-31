package simpliLearn.swiggyApplication;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
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

	@BeforeSuite
	public void setup() throws Throwable {
		testDeviceType = initiateDeviceType();
		DesiredCapabilities desiredCapabilities = AppiumSetup.getCapabilitiesOfDevice(testDeviceType);
		URL remoteUrl = new URL(TestProperties.getAppiumServerURL());
		driver = new AndroidDriver(remoteUrl, desiredCapabilities);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(TestProperties.getImplicitWaitTimeoutConfig()),
				TimeUnit.SECONDS);
	}

	@Test(priority = 1)
	@Parameters({ "searchType", "searchTypeValue" })
	public void runSearchTest(String searchType, String searchTypeValue) throws Throwable {

		if (!searchType.equalsIgnoreCase("resto") && !searchType.equalsIgnoreCase("restaurant")
				&& !searchType.equalsIgnoreCase("dish") && !searchType.equalsIgnoreCase("food")) {
			throw new RuntimeException(
					"Parameter passed for Search Type is incorrect and should be either Resto or Restaurant or Dish or Food (ignore case)");
		} else {

			Thread.sleep(2000);
			takeScreenShot("01_Splash_Screen", driver);
			MobileElement initialLocation = (MobileElement) driver
					.findElementById("in.swiggy.android:id/set_location_text");
			initialLocation.click();
			Thread.sleep(2000);
			takeScreenShot("02_Location_Permission_Popup", driver);

			switch (testDeviceType) {
			case GalaxyA30s_swiggy:
				MobileElement initLocationPermission_g = (MobileElement) driver
						.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
				initLocationPermission_g.click();
				break;
			case Oneplus8t_A11_swiggy:
				MobileElement initLocationPermission_op = (MobileElement) driver
						.findElementById("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
				initLocationPermission_op.click();
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
				takeScreenShot("03_Location_emu_entered", driver);
				MobileElement selectLoc = (MobileElement) driver.findElementByXPath("//*[@text=\"Thane West\"]");
				selectLoc.click();
				break;
			default:
				break;
			}

			MobileElement confLocation = (MobileElement) driver
					.findElementById("in.swiggy.android:id/google_place_search_title_text1");
			takeScreenShot("04_Location_set", driver);
			confLocation.click();
			MobileElement initiateSearch = (MobileElement) driver
					.findElementById("in.swiggy.android:id/bottom_bar_explore");
			initiateSearch.click();
			MobileElement searchBar = (MobileElement) driver
					.findElementByXPath("//*[@text=\"Search for restaurants and food\"]");
			searchBar.click();
			takeScreenShot("05_Search_screen", driver);

			if (searchType.equalsIgnoreCase("Restaurant") || searchType.equalsIgnoreCase("Resto")) {
				searchBar.sendKeys(searchTypeValue);
				driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "Search"));
				List<MobileElement> searchResults = driver
						.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
								+ "/android.widget.FrameLayout/android.widget.LinearLayout/"
								+ "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/"
								+ "android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout"
								+ "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout"
								+ "/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup");
				System.out.println("Number of elements found: " + searchResults.size());
				takeScreenShot("06_Search_by_restaurant_results", driver);
				MobileElement restaurantSearch;

				if (searchResults.size() >= 1) {
					restaurantSearch = (MobileElement) searchResults.get(0);
					restaurantSearch.click();
					if (checkRatingDialogPopup()) {
						takeScreenShot("07_Rating_dialog_popup", driver);
						driver.navigate().back();
					}
					String restName = driver.findElementById("in.swiggy.android:id/restaurant_name").getText();
					if (restName.contains(searchTypeValue)) {
						takeScreenShot("08_Restaurant_search_completed", driver);
						Assert.assertTrue(true);
					} else
						Assert.assertTrue(false);
				} else if (searchResults.size() < 1) {
					throw new RuntimeException("Search was incomplete or found no results");
				} else {
					throw new RuntimeException("Search was incomplete or found no results");
				}

			} else if (searchType.equalsIgnoreCase("Dish") || searchType.equalsIgnoreCase("Food")) {
				searchBar.sendKeys(searchTypeValue);
				driver.executeScript("mobile:performEditorAction", ImmutableMap.of("action", "Search"));
				List<MobileElement> searchResults = driver
						.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
								+ "/android.widget.FrameLayout/android.widget.LinearLayout/"
								+ "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/"
								+ "android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout"
								+ "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout"
								+ "/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup");
				System.out.println("Number of elements found: " + searchResults.size());
				takeScreenShot("06_Search_by_dish_name_results", driver);

				if (searchResults.size() >= 1) {
					takeScreenShot("07_Dishes_found_successfully", driver);
					Assert.assertTrue(true);
				} else if (searchResults.size() < 1) {
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
