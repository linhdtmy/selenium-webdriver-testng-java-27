package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Iframe_Frame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Kyna() {
		driver.get("https://kyna.vn/");
		sleepInSecond(3);
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content iframe ")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "166K likes");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("cs_chat_iframe");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("div.button_bar")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("aaaaaa");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("123456");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("ssssssss");
		driver.switchTo().defaultContent();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(3);
		List<WebElement> listText = driver.findElements(By.cssSelector("div.content h4"));
		Assert.assertEquals(listText.size(),9);
		for (WebElement text : listText) {
			System.out.println(text.getText());
			Assert.assertTrue(text.getText().contains("Excel"));
		}

	}

	@Test
	public void TC_02_HDFC() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("123");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input[name='fldPassword']")).sendKeys("12345");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(5);
		driver.switchTo().defaultContent();
		Assert.assertEquals(driver.findElement(By.cssSelector("span[style='color:red']")).getText(), "Your ID and IPIN do not match. Please try again");
		

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
