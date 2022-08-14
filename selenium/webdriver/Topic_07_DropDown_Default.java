package webdriver;

import java.util.List;
import java.util.Random;
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

public class Topic_07_DropDown_Default {
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");
	Select select;
	Random random = new Random();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_03_dropdownDefault() {
		driver.get("https://demo.nopcommerce.com/register");
		driver.findElement(By.cssSelector("a.ico-register")).click();
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Khanh");
		driver.findElement(By.cssSelector("input#LastName")).sendKeys("Vy");

		// khởi tạo select để thao tác với day dropdown
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		select.selectByVisibleText("12");

		// khởi tạo select để thao tác với month
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		select.selectByVisibleText("January");

		// khởi tạo select để thao tác với year
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		select.selectByVisibleText("1960");

		String email = "khanhvy" + random.nextInt(999) + "@gmail.com";
		driver.findElement(By.cssSelector("input#Email")).sendKeys(email);
		driver.findElement(By.cssSelector("input#Company")).sendKeys("A Company");
		driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
		driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
		driver.findElement(By.cssSelector("button#register-button")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
		driver.findElement(By.cssSelector("a.ico-account")).click();
		sleepInSecond(3);
		 //khởi tạo lại giá trị cho select
		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']")));
		//kiểm tra ngày đúng
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "12");

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']")));
		//kiểm tra tháng đúng
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "January");

		select = new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']")));
		//kiểm tra năm đúng
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "1960");

		Assert.assertEquals(driver.findElement(By.cssSelector("input#Email")).getAttribute("value"), email);

	}
	@Test
	public void TC_04_dropdownDefault() {
		driver.get("https://www.rode.com/wheretobuy");
		sleepInSecond(5);
		select = new Select(driver.findElement(By.cssSelector("select#country")));
		//kiểm tra dropdown không hỗ trợ thuộc tính multi
		Assert.assertFalse(select.isMultiple());
		//chọn giá trị Vietnam trong dropdown
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		driver.findElement(By.xpath("//button[text()='Search']")).click();
		sleepInSecond(3);
		List<WebElement> listDealer=  driver.findElements(By.cssSelector("div#map h4")); 
		for (WebElement webElement : listDealer) {
			System.out.println(webElement.getText());
			
		}
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
