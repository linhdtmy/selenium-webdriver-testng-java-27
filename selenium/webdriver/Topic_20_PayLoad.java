package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_PayLoad {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);

	}

	/*
	 * Hiện tại trang web đang thay đổi giao diện nên chưa xử lí phần này được
	 */
	@Test
	public void TC_01() {
		driver.get("https://opensource-demo.orangehrmlive.com");
		driver.findElement(By.name("username")).sendKeys("Admin");
		driver.findElement(By.name("password")).sendKeys("admin123");
		driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
	}

	@Test
	public void TC_02() {
		driver.get("https://blog.testproject.io/");
		if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		// hover chuột và textbox search
		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());

		// Nhập giá trị vào textbox search và thực hiện search

		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
		//driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		clickToElementByJS("//section[@id='search-2']//span[@class='glass']");
		Assert.assertTrue(isPageLoadedSuccess());
		Assert.assertEquals(driver.findElement(By.cssSelector("h2.page-title")).getText(), "Search Results for: \"Selenium\":");
		// lấy ra danh sách title có chứa text Selenium

		List<WebElement> listLabel = driver.findElements(By.cssSelector("h3.post-title a"));
		for (WebElement e : listLabel) {
			System.out.println(e.getText());
		}

	}

	@Test
	public void TC_03() {
	}

//jquery +Js
	public boolean isPageLoadedSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery !=null)&&(jQuery.active===0);");

			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");

			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);

	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}
}
