package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Browser_Element_01 {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.manage().window().maximize();
//		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_Browser() {
		//Các hàm tương tác với browser qua biến driver
		//đóng tab hiện tại
		driver.close();
		//Đóng cả browser 
		driver.quit();
		WebElement login=driver.findElement(By.cssSelector(""));
		//mở ra cái URL được truyền vào
		
		driver.get("");
		//trả về url tại page đang đứng
		driver.getCurrentUrl();
		String title=driver.getTitle();
		//lấy source code
		driver.getPageSource();
		//lấy ra id của tabwindown đnag đứng
		driver.getWindowHandle();
		//trả về  1 interface. 
		driver.manage();
		//chờ element được tìm thấy sau xx giây
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		//chờ script được inject thành công vào browser/element sau xx giây
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.navigate().back();
		driver.navigate().to("");
		driver.switchTo().alert();
		driver.switchTo().frame(0);
		driver.switchTo().window("");
		
		
	}

	@Test
	public void TC_02_Element() {
    //các hàm tương tác với element thông qua biến element
	//khai báo biến cùng kiểu dữ liệu trả về
	WebElement emailAddressTextbox  = driver.findElement(By.id("email"));
	emailAddressTextbox.clear();
	emailAddressTextbox.sendKeys("");
	//dùng 1 lần
	driver.findElement(By.id("")).sendKeys("");
	//xóa dữ liệu trong 1 field 
	element.clear();
	element.sendKeys("");
	element.sendKeys(Keys.ENTER);
	//trả về giá trị nằm trong atribute
	element.getAttribute("placeholder");
	//trả về thuộc tính css
	element.getCssValue("background-color");
	//laasi vị trí.size..
	element.getLocation();
	element.getRect();
	//chụp hình và attach và HTML
	element.getScreenshotAs(OutputType.FILE);
	//trả về HTML của element
	element.getTagName();
	//trả về text của 1 element
	element.getText();
	//trả về giá trị đúng hoặc sai
	element.isDisplayed();
	
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
