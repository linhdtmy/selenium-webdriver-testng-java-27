package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Register {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email = "abc123" + generate_Random() + "@gmail.com";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Register_Empty_Data() {

		driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();
		driver.findElement(By.cssSelector("button.register-next-step-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");

	}

	@Test
	public void TC_02_Invalid_Email() {
		driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("First");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Last");
		driver.findElement(By.cssSelector("input#Email")).sendKeys("123");
		driver.findElement(By.cssSelector("button.register-next-step-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
	}

	@Test
	public void TC_03_Valid_Inform() {
		driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();

		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("First");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Last");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Abc@12345");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Abc@12345");
		driver.findElement(By.cssSelector("button.register-next-step-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-logout")).click();
		driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();

	}

	@Test
	public void TC_04_Existed_Email() {
		driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("First");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Last");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Abc@12345");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Abc@12345");
		driver.findElement(By.cssSelector("button.register-next-step-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.validation-summary-errors li")).getText(), "The specified email already exists");

	}

	@Test
	public void TC_05_Password_Less_Than_6_Charactor() {
		driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("First");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Last");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Abc");
		driver.findElement(By.cssSelector("button.register-next-step-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password must meet the following rules:\nmust have at least 6 characters");
	}

	@Test
	public void TC_06_Passconfirm() {
		driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("First");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Last");
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Password")).sendKeys("Abc@12345");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("Abc@123");
		driver.findElement(By.cssSelector("button.register-next-step-button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int generate_Random() {
		Random random = new Random();
		return random.nextInt();
	}
}
