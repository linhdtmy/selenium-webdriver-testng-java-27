package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Element_Condition_Status {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		//khởi tạo wait , thời gian đợi là 10 s
		explicitWait = new WebDriverWait(driver,10); 
	}

	@Test
	public void TC_01_Visible_Display_Visibility() {
		driver.get("https://www.facebook.com/");
		/*
		 * Có trên giao diện và có trong HTML
		 */
		//Chờ 10s cho đến  khi emelment hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

	}
//
	@Test
	public void TC_02_Invisible_Undisplay_Invisibility() {
		/*
		 * Không có trên giao diện nhưng có trong HTML
		 */
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		//chờ trong vòng 10s và kiểm tra element không hiển thị 
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		

	}

	/*
	 * Có trong UI và có trong case HTML
	 */
	@Test
	public void TC_03_Present_01() {
		driver.get("https://www.facebook.com/");
		//Chờ  cho đến khi element hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	

	
/*
 * Không có trên UI nhưng có trong cây HTML
 */
	@Test
	public void TC_03_Present_02() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
		
	}
/*
 * Không có trên UI và không có trong HTML
 */
	@Test
	public void TC_04_Stabless() {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
		WebElement element = driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"));

		driver.findElement(By.cssSelector("img._8idr.img")).click();
		//Chờ cho đến khi element biến mất trong 10 s
		explicitWait.until(ExpectedConditions.stalenessOf(element));
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
