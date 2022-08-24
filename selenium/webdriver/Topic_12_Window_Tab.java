package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Window_Tab {
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
	public void TC_01_Selenium() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(3);
		switchToTabWindowByTitle("Google");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Google");
		switchToTabWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(5);
		switchToTabWindowByTitle("Facebook – log in or sign up");
		sleepInSecond(5);
		switchToTabWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		switchToTabWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		closeAnotherTab("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(5);
		switchToTabWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	@Test
	public void TC_02_TechPanda() {
		driver.get("http://live.techpanda.org/");
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div//a[@class='link-compare']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div//a[@class='link-compare']")).click();
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(3);
		switchToTabWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(),"Products Comparison List - Magento Commerce");
		driver.close();
		switchToTabWindowByTitle("Mobile");
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());

	}

	@Test
	public void TC_03_Cambridge() {
		driver.get("https://dictionary.cambridge.org/vi/");
		sleepInSecond(3);
		driver.findElement(By.xpath("//span[text()='Đăng nhập'][1]")).click();
		switchToTabWindowByTitle("Login");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		sleepInSecond(3);
		List<WebElement> listError = driver.findElements(By.xpath("//span[@role='alert']"));
		Assert.assertEquals(listError.size(), 2);
		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password *']")).sendKeys("Automation000***");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		switchToTabWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("span.cdo-username")).getText(), "Automation FC");
		
	}

	public void switchToTabWindowByTitle(String expectPageTitle) {
		//lấy ra danh sách tất cả các tab
		Set<String>listTab= driver.getWindowHandles();
		//duyệt danh sách để lấy ra page có title cần lấy
		for(String id : listTab) {
			
			//switch qua từng tab để lấy title
			driver.switchTo().window(id);
			
			String title = driver.getTitle();
			//nếu title bằng với title expect thì thoát khỏi vòng lặp
			if(title.equals(expectPageTitle)) {
				break;
			}
		}
	}
	public void closeAnotherTab(String parentTabTitle) {
		//lấy ra danh sách tất cả các tab
				Set<String>listTab= driver.getWindowHandles();
				//duyệt danh sách để lấy ra page có title cần lấy
				for(String id : listTab) {
					
					//switch qua từng tab để lấy title
					driver.switchTo().window(id);
					
					String title = driver.getTitle();
					//nếu title bằng với title expect thì thoát khỏi vòng lặp
					if(!title.equals(parentTabTitle)) {
						driver.close();
					}
				}
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
