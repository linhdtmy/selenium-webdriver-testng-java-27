package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Radio_Checkbox_Custom_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Alert alert;
	String autoFirefox= projectPath+"/authen/authen_firefox.exe";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		sleepInSecond(3);
		By checkbox = By.xpath("//span[text()='Checked']/preceding-sibling::span//input");
		By radio = By.xpath("//span[text()='Before']/preceding-sibling::span//input");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(checkbox));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(checkbox).isSelected());

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(radio));
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(radio).isSelected());
	}

	// @Test
	public void TC_02() {
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		By radio = By.xpath("//div[@aria-label='Hà Nội']");
		driver.findElement(radio).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(radio).getAttribute("aria-checked"), "true");
		// scroll("//span[text()='Gửi']", false)
		By checkbox = By.xpath("//div[@aria-label='Mì Quảng']");
		driver.findElement(checkbox).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(radio).getAttribute("aria-checked"), "true");

	}

	// @Test
	public void TC_03_Accept_Allert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		scroll("span.number", false);
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		sleepInSecond(3);

		// Muốn thao tác được với Alert đang bật lên thì cần phải switch vào nó
		alert = driver.switchTo().alert();
		// verify text của alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		// accept alert
		alert.accept();
		scroll("span.number", true);
		sleepInSecond(3);
		// verify alert được accept thành công
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(),
				"You clicked an alert successfully");
	}

	// @Test
	public void TC_04_Demiss_Allert() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		scroll("textarea#address", false);
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		scroll("span.number", true);
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");

	}

	// @Test
	public void TC_05_Prompt_Allert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(3);
		scroll("textarea#address", false);
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		alert.sendKeys("aaaaaaaaaa");
		sleepInSecond(3);
		alert.accept();
		sleepInSecond(3);
		scroll("span.number", true);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: aaaaaaaaaa");
	}

	// @Test
	public void TC_06_Authentication_Allert() {
		// selenium cho phép gán user/pass trực tiếp vào url trước khi open ra
		// format :http://username:password@domain
		// Không bật aert lên tự điền vào url luôn nên ko có allert hiện lên

		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		String textCongratulation = driver.findElement(By.cssSelector("div.example p")).getText();
		Assert.assertTrue(textCongratulation.contains("Congratulations! You must have the proper credentials"));
	}

	//@Test
	public void TC_07_Authentication_Allert_BeforeGetLink() {
		String username = "admin";
		String password = "admin";
		driver.get("https://the-internet.herokuapp.com/");
		String link = driver.findElement(By.xpath("//a[text()=\"Basic Auth\"]")).getAttribute("href");

		String[] divideLink = link.split("//");
		String url = divideLink[0] + "//" + username + ":" + password + "@" + divideLink[1];
		driver.get(url);
		String textCongratulation = driver.findElement(By.cssSelector("div.example p")).getText();
		Assert.assertTrue(textCongratulation.contains("Congratulations! You must have the proper credentials"));
	}
	@Test
	public void TC_07_Authentication_Allert_Exception_AutoIT() throws IOException {
		//trước khi mở URl lên  cho nó execute cái file exe đó để chờ alert bật lên trước
		String username = "admin";
		String password = "admin";
		Runtime.getRuntime().exec(new String[] {autoFirefox,username,password});
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		//autoIT chỉ chạy được trên window
		sleepInSecond(5);
		
		String textCongratulation = driver.findElement(By.cssSelector("div.example p")).getText();
		Assert.assertTrue(textCongratulation.contains("Congratulations! You must have the proper credentials"));
	
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeSeconds) {
		try {
			Thread.sleep(timeSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void scroll(String cssLocator, boolean status) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(status);",
				driver.findElement(By.cssSelector(cssLocator)));

	}
}
