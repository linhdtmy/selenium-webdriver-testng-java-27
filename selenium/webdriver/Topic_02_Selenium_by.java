package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_by {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
		//Bước 2 :get URL
		driver.get("https://www.facebook.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Bước 3 :Tìm 1 thành phần trên trang web
		driver.findElement(By.id("email"));
		driver.findElement(By.linkText("Log In"));
		//get CSS
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input[name='email']"));
		//get Xpath
		driver.findElement(By.xpath("//input[@id='email']"));
		//driver.findElement(By.xpath("//button[@id='u_0_b_3V']"));
		driver.findElement(By.xpath("//input[@type='password']"));
	}

	@Test
	public void TC_02() {
    // driver.get("https://www.facebook.com/");
     //driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    // driver.findElement(By.cssSelector("a[id='u_0_0_pZ']"));
    // driver.findElement(By.xpath("//a[@id='u_0_0_pZ']"));
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
