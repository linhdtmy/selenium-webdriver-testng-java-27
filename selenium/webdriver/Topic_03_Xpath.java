package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Register_Empty_Data() {

		// action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("");
		driver.findElement(By.id("txtEmail")).sendKeys("");
		driver.findElement(By.id("txtCEmail")).sendKeys("");
		driver.findElement(By.id("txtPassword")).sendKeys("");
		driver.findElement(By.id("txtCPassword")).sendKeys("");
		driver.findElement(By.id("txtPhone")).sendKeys("");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// verify (actual data =expect data)
		Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Vui lòng nhập số điện thoại.");

	}

	@Test
	public void TC_02_Register_Invalid_Email() {
		// action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Văn An");
		driver.findElement(By.id("txtEmail")).sendKeys("sjahfjdfh@gmail.maibfd@");
		driver.findElement(By.id("txtCEmail")).sendKeys("sjahfjdfh@gmail.maibfd@");
		driver.findElement(By.id("txtPassword")).sendKeys("An@12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("An@12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0983754654");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// verify (actual data =expect data)

		Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Vui lòng nhập email hợp lệ");

	}

	@Test
	public void TC_03_Register_Invalid_Confirm_Email() {
		// action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Văn An");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyenvanan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("sjahfjdfh@gmail.maibfd@");
		driver.findElement(By.id("txtPassword")).sendKeys("An@12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("An@12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0983754654");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// verify (actual data =expect data)
		
		Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(), "Email nhập lại không đúng");
		
	}

	@Test
	public void TC_04_Register_Password_Less_Than_6Chars() {
		// action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Văn An");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyenvanan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("sjahfjdfh@gmail.maibfd@");
		driver.findElement(By.id("txtPassword")).sendKeys("An@12");
		driver.findElement(By.id("txtCPassword")).sendKeys("An@12");
		driver.findElement(By.id("txtPhone")).sendKeys("0983754654");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// verify (actual data =expect data)
		
		Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
	}

	@Test
	public void TC_05_Register_Incorect_Confirm_Pasword() {
		// action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Văn An");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyenvanan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("sjahfjdfh@gmail.maibfd@");
		driver.findElement(By.id("txtPassword")).sendKeys("An@12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("An@123456789");
		driver.findElement(By.id("txtPhone")).sendKeys("0983754654");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// verify (actual data =expect data)
		Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(), "Mật khẩu bạn nhập không khớp");
		
	}

	@Test
	public void TC_06_Register_Invalid_Phone_Number() {
		// action
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		driver.findElement(By.id("txtFirstname")).sendKeys("Nguyễn Văn An");
		driver.findElement(By.id("txtEmail")).sendKeys("nguyenvanan@gmail.com");
		driver.findElement(By.id("txtCEmail")).sendKeys("sjahfjdfh@gmail.maibfd@");
		driver.findElement(By.id("txtPassword")).sendKeys("An@12345678");
		driver.findElement(By.id("txtCPassword")).sendKeys("An@12345678");
		driver.findElement(By.id("txtPhone")).sendKeys("0984786785765835354");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		// verify (actual data =expect data)
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("0984");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(By.id("txtPhone")).clear();
		driver.findElement(By.id("txtPhone")).sendKeys("1984");
		driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();
		Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
