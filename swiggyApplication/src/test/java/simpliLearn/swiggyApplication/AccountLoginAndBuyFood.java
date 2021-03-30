package simpliLearn.swiggyApplication;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class AccountLoginAndBuyFood extends TestCase {

	private AndroidDriver driver;
	private Scanner inPut;

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

	@Test(priority = 1)
	@Parameters({ "phoneNumber" })
	public void runAccountLogin(String phoneNumber) throws Throwable {
		if (!checkPhoneNumber(phoneNumber) || phoneNumber.length() != 10) {
			throw new RuntimeException("Phone number entered is either invalid or is not 10 digits");
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
				MobileElement manualLocSearch_emu7 = (MobileElement) driver
						.findElementById("in.swiggy.android:id/dialog_negative_layout_text");
				manualLocSearch_emu7.click();
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

			MobileElement account = (MobileElement) driver.findElementById("in.swiggy.android:id/bottom_bar_account");
			account.click();

			MobileElement loginButton = (MobileElement) driver.findElementByXPath("//*[@text=\"LOGIN\"]");
			loginButton.click();

			driver.navigate().back();
			MobileElement number = (MobileElement) driver
					.findElementById("in.swiggy.android:id/loginCheckPhoneNumberEditText");
			number.click();
			number.sendKeys(phoneNumber);
			MobileElement continueLogin = (MobileElement) driver
					.findElementById("in.swiggy.android:id/loginCheckButton");
			continueLogin.click();
			System.out.println("Enter the One Time Pin received to login via phone number");
			inPut = new Scanner(System.in);
			int otp = inPut.nextInt();
			MobileElement otpField = (MobileElement) driver.findElementById("in.swiggy.android:id/otpField");
			otpField.sendKeys(String.valueOf(otp));
			MobileElement otpSubmit = (MobileElement) driver
					.findElementById("in.swiggy.android:id/forgotPasswordSubmitBtn");
			otpSubmit.click();

			MobileElement userDetails = (MobileElement) driver.findElementById("USERNAME");
			String loggedInUser = userDetails.getText();
			System.out.println(loggedInUser);
			// Assert.assertEquals(userDetails, phoneNumber);
		}
	}

	@Test(priority = 2)
	@Parameters({ "searchType", "searchTypeValue" })
	public void buyFood(String searchType, String searchTypeValue) throws Throwable {
		if (!searchType.equalsIgnoreCase("resto") && !searchType.equalsIgnoreCase("restaurant")
				&& !searchType.equalsIgnoreCase("dish") && !searchType.equalsIgnoreCase("food")) {
			throw new RuntimeException(
					"Parameter passed for Search Type is incorrect and should be either Resto or Restaurant or Dish or Food (ignore case)");
		} else {
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
				List<MobileElement> searchResults = driver
						.findElementsByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout"
								+ "/android.widget.FrameLayout/android.widget.LinearLayout/"
								+ "android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/"
								+ "android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout"
								+ "/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout"
								+ "/androidx.recyclerview.widget.RecyclerView[2]/android.view.ViewGroup");
				System.out.println("Number of elements found: " + searchResults.size());
				takeScreenShot("SearchResultsFound", driver);
				MobileElement restaurantSearch;

				if (searchResults.size() >= 1) {
					restaurantSearch = (MobileElement) searchResults.get(1);
					restaurantSearch.click();
					if (checkRatingDialogPopup())
						driver.navigate().back();
					// String restName =
					// driver.findElementById("in.swiggy.android:id/restaurant_name").getText();
					takeScreenShot("RestaurantClicked", driver);

					TouchAction action = new TouchAction(driver);
					action.longPress(PointOption.point(310, 1160)).moveTo(PointOption.point(310, 1000)).release()
							.perform();

					MobileElement addItem = (MobileElement) driver
							.findElementByXPath("//*[@content-desc=\"Add Item\"]");
					addItem.click();
					while (checkElementDisplayedById("in.swiggy.android:id/progressive_variants_continue_button")) {
						MobileElement continueButton = (MobileElement) driver
								.findElementById("in.swiggy.android:id/progressive_variants_continue_button");
						continueButton.click();
					}
					if (checkElementDisplayedByXpath("//*[@text=\"ADD ITEM\"]")) {
						MobileElement addToCart = (MobileElement) driver.findElementByXPath("//*[@text=\"ADD ITEM\"]");
						addToCart.click();
					}

					MobileElement viewCart = (MobileElement) driver.findElementById("in.swiggy.android:id/cart_icon");
					viewCart.click();
					if (checkElementDisplayedByXpath("//*[contains(@text,\"SELECT ADDRESS\")]")) {
						MobileElement selectAddress = (MobileElement) driver
								.findElementByXPath("//*[contains(@text,\"SELECT ADDRESS\")]");
						selectAddress.click();
					}

					MobileElement proceedToPay = (MobileElement) driver
							.findElementByXPath("//*[contains(@text, 'PAY')]");
					proceedToPay.click();
					if (checkElementDisplayedByXpath("//*[contains(@text,\"UNDERSTAND\")]")) {
						MobileElement cancelationPolicyUnderstand = (MobileElement) driver
								.findElementByXPath("//*[contains(@text,\"UNDERSTAND\")]");
						cancelationPolicyUnderstand.click();
					}
					MobileElement billTotal = (MobileElement) driver
							.findElementByXPath("//*[contains(@text, 'BILL TOTAL')]");
					System.out.println("Total Bill to be paid is: " + billTotal.getText());
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

	private boolean checkElementDisplayedById(String id) {
		try {
			driver.findElementById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean checkElementDisplayedByXpath(String xpath) {
		try {
			driver.findElementByXPath(xpath);
			return true;
		} catch (Exception e) {
			return false;
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

	private boolean checkPhoneNumber(String phoneNumber) {
		try {
			Long.parseLong(phoneNumber);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@AfterTest
	public void tearDown() throws Throwable {
		if (!driver.equals(null))
			driver.quit();
		inPut.close();
	}
}
