package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_UploadFile {
	WebDriver driver;
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(3);
		//upload file
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(catFilePath + "\n" + goodMorningFilePath + "\n" + roomFilePath);
		//verify upload file success
		List<WebElement> listNameFile= driver.findElements(By.cssSelector("p.name"));
		Assert.assertEquals(listNameFile.size(), 3);
		//Click button start
		List<WebElement> listButtonStart = driver.findElements(By.cssSelector("table button.start"));
		for(WebElement e :listButtonStart) {
			e.click();
			sleepInSecond(3);
			
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='cat.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='room.jpg']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']//a[text()='good morning.jpg']")).isDisplayed());

	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {
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
