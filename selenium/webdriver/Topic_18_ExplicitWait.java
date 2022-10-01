package webdriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor je;
	String projectPath = System.getProperty("user.dir");
	String uploadFileFolderPath = projectPath + File.separator + "uploadFile" + File.separator;

	// tên file upload
	String cat = "cat.jpg";
	String goodMorning = "good morning.jpg";
	String room = "room.jpg";
	// image path
	String catFilePath = uploadFileFolderPath + cat;
	String goodMorningFilePath = uploadFileFolderPath + goodMorning;
	String roomFilePath = uploadFileFolderPath + room;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		/*
		 * explicitWait chính là WebDriverWait :Đợi 1 cách tường minh , có trạng thái , điều kiện cụ thể của element . explicitWait sẽ đợi cho đến khi xuất hiện điều kiện thỏa mãn và trong thời gian timeout của
		 * explicitWait Nếu hết thời gian mà điều kiện expect vẫn ko xuất hiện sẽ dân đến bị fail
		 */
		explicitWait = new WebDriverWait(driver, 15);
		je = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Wait_Until_Visible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start button")).click();
		// đợi cho đếnkhi text xuất hiện thì kết thúc testcase , không đợi cho đến hết thời gian timeout
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_02_Wait_Until_Invisible() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.cssSelector("div#start button")).click();
		// đợi cho đếnkhi text xuất hiện thì kết thúc testcase , không đợi cho đến hết thời gian timeout
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading img")));
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
	}

	@Test
	public void TC_03_Ajax_Loading() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		// khởi tạo explicit wait
		explicitWait = new WebDriverWait(driver, 15);
		// Đợi cho đến khi date hiển thị
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.RadCalendar")));

		je.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(By.cssSelector("span.rbText")));

		// Verify cho selected date là không có ngày nào được chọn =>Kiểm tra text hiển thị là No selected
		Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(), "No Selected Dates to display.");
		// đợi cho ngày 28 được phép click
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='28']")));
		// Chọn ngày hiện tại
		driver.findElement(By.xpath("//a[text()='28']")).click();
		// đợi đến khi ajax loading không còn hiển thị
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] div.raDiv")));
		// đợi cho text 30 được selected
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='30']")));
		// Click vào ngày 30
		driver.findElement(By.xpath("//a[text()='30']")).click();
		//Đợi cho đến khi loading không hiển thị
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar1'] div.raDiv")));
		// Verify ngày đã chọn là Tuesday, August 30, 2022
		Assert.assertTrue(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText().contains("Tuesday, August 30, 2022"));

	}

	@Test
	public void TC_04_UploadFile() {
		driver.get("https://gofile.io/uploadFiles");
		explicitWait= new WebDriverWait(driver, 45);
		//đợi đến khi button Add hiển thị và Click vào button Add
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn-lg.uploadButton"))) ;
		
		//Thực hiện upload file
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(catFilePath + "\n"+goodMorningFilePath);
		

		//đợi cho đến khi progressbar không hiển thị nữa 
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));
		//Đợi đến khi text thành công hiển thị và verify text này 
		Assert.assertEquals(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success  h5"))).getText(), "Your files have been successfully uploaded");
		//đợi đến khi button show file được click  và click vào button 
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#rowUploadSuccess-showFiles"))).click();
		//verify cho button download và play hiển thị
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+cat+"']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+goodMorning+"']/parent::a/parent::div/following-sibling::div//button[@id='contentId-download']"))).isDisplayed());

		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+cat+"']/parent::a/parent::div/following-sibling::div//button[@class='rowFolder-option contentPlay btn btn-default btn-xs p-1']"))).isDisplayed());
		Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='"+goodMorning+"']/parent::a/parent::div/following-sibling::div//button[@class='rowFolder-option contentPlay btn btn-default btn-xs p-1']"))).isDisplayed());

		
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
