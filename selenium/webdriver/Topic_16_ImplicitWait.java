package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_ImplicitWait {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		/*
		 * implicitwait set ở đâu thì sẽ được apply từ đó Nếu bị gán lại giá trị thì sẽ dùng giá trị mới đc gán , không dùng giá trị cũ nữa Nếu ko gán giá trị thì deefault sẽ là 0 giây
		 * 
		 * Implicit ảnh hưởng đến findElement , findElement ảnh hưởng đến locator Cái này kiểu như implicit là thời gian đến mk tìm đc element , xong tìm được element rồi thì mới xác định được locator (Mk hiểu như này ko
		 * biết có đúng ko nữa )
		 */
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("div#start button")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
