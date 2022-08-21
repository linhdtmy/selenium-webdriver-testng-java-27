package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Radio_Checkbox_Button_Default {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	//@Test
	public void TC_01() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		By loginButton = By.cssSelector("button.fhs-btn-login");
		// Verify button is disable
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
		// enter value for username + password
		driver.findElement(By.cssSelector("input#login_username")).sendKeys("abcmn123@gmail.com");
		driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
		// verify button is enable
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		sleepInSecond(3);
		String color = driver.findElement(loginButton).getCssValue("background-color");
		// verify back-ground color
		color = Color.fromString(color).asRgb();
		Assert.assertEquals(color, "rgb(201, 33, 39)");

	}

	@Test
	public void TC_02() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSecond(3);
		By checkboxInput = By.cssSelector("input#eq5");
		By radioInput = By.cssSelector("input#engine3");
		//Click vào checkbox
		driver.findElement(checkboxInput).click();
		//Verify  checkbox is selected
		Assert.assertTrue(driver.findElement(checkboxInput).isSelected());
		//deselect 
		driver.findElement(checkboxInput).click();
		//Verify checkbox is not selected
		Assert.assertFalse(driver.findElement(checkboxInput).isSelected());
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		sleepInSecond(3);
		//click radio button
		driver.findElement(radioInput).click();
		//verify radio is selected
		if(!driver.findElement(radioInput).isSelected()) {
			driver.findElement(radioInput).click();
		}
	}

	@Test
	public void TC_03() {
	}

	public void sleepInSecond(long timeSeconds) {
		try {
			Thread.sleep(timeSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
