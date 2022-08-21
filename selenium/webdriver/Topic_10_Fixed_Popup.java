package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Fixed_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_Ngoai_Ngu_24() {
		driver.get("https://ngoaingu24h.vn/");
		// Kiểm tra popup đăng nhập không hiển thị
		WebElement popup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
		Assert.assertFalse(popup.isDisplayed());
		// Click vào button đăng nhập
		driver.findElement(By.cssSelector("div#button-login-dialog button.login_")).click();
		Assert.assertTrue(popup.isDisplayed());
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']"))
				.sendKeys("automationFc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("123456");
		driver.findElement(
				By.xpath("//div[@id='modal-login-v1'][1]//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		sleepInSecond(3);
		String errorText = driver
				.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText();
		Assert.assertEquals(errorText, "Tài khoản không tồn tại!");
	}

	@Test
	public void TC_02_Kya() {
		driver.get("https://kyna.vn/");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		WebElement popup = driver.findElement(By.cssSelector("div.k-popup-account-mb-content"));
		Assert.assertTrue(popup.isDisplayed());
		driver.findElement(By.cssSelector("input#user-login")).sendKeys("input#user-login");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		sleepInSecond(3);
		String errorMessage = driver.findElement(By.cssSelector("div#password-form-login-message")).getText();
		Assert.assertEquals(errorMessage, "Sai tên đăng nhập hoặc mật khẩu");
		driver.findElement(By.cssSelector("div.modal-header button.k-popup-account-close")).click();
		Assert.assertFalse(popup.isDisplayed());
	}

	@Test
	public void TC_03_TestProject() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(5);
		List<WebElement> popupList = driver.findElements(By.cssSelector("div.mailch-wrap"));
		if (popupList.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		scroll("input.wpcf7-form-control.wpcf7-text.wpcf7-email.wpcf7-validates-as-required.wpcf7-validates-as-email",
				true);

		jsExecutor.executeScript("arguments[0].setAttribute('value','Selenium')",
				driver.findElement(By.cssSelector("input.search-field")));

		// driver.findElement(By.cssSelector("span.glass")).click();
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("span.glass")));
		sleepInSecond(5);

	}

	@Test
	public void TC_04_RandomPopup_InDOM() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(20);
		WebElement popup = driver.findElement(By.cssSelector("div.thrv_wrapper.thrv-columns"));
		if (popup.isDisplayed()) {
			jsExecutor.executeScript("arguments[0].click();",
					driver.findElement(By.cssSelector("div.tve_ea_thrive_leads_form_close.tcb-local-vars-root")));
			sleepInSecond(3);
		}
		Assert.assertFalse(driver.findElement(By.cssSelector("div.thrv_wrapper.thrv-columns")).isDisplayed());

	}
	@Test
	public void TC_05_RandomPopup_Not_InDOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(20);
		List<WebElement> listPopup = driver.findElements(By.cssSelector("div.popup-content"));
		if(listPopup.get(0).isDisplayed()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button.close")));

		}
		listPopup = driver.findElements(By.cssSelector("div.popup-content"));
		Assert.assertEquals(listPopup.size(), 0);
	}

	public void scroll(String cssLocator, boolean status) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(status);",
				driver.findElement(By.cssSelector(cssLocator)));

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
