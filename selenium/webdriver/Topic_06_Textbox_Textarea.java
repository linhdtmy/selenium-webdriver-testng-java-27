package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, employeeId, editFirstName, editLastName, immigrate, textArea;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_textBox_textArea() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		// enter username + password
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		
		// Click button login
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		sleepInSecond(5);
		
		// open add screen
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		// construct value for parameter
		firstName = "A1";
		lastName = "B1";
		editFirstName = "A2";
		editLastName = "B2";
		
		// enter fistName + lastName
		driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
		
		// get value Id field
		employeeId = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// verify disable fields
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// verify actual value the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"), employeeId);
		
		// Click button edit
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// Verify the fields are enable
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// edit the field firstName,lastName
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).sendKeys(editFirstName);
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).sendKeys(editLastName);
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// verify disable fields
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// verify actual value the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), editLastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"), employeeId);
		
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		sleepInSecond(3);
		immigrate = "456789012";
		textArea = "test1\n test2 \n test3";
		driver.findElement(By.cssSelector("input#immigration_number")).sendKeys(immigrate);
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys(textArea);
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#immigration_number")).getAttribute("value"), immigrate);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), textArea);
		
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
}
