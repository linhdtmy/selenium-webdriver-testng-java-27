package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Mouse {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	JavascriptExecutor js;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
		js = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement textbox = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(textbox).perform();
		sleepInSecond(3);
		assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	// @Test
	public void TC_02_Hover() {
		driver.get("https://www.myntra.com/");
		WebElement kidLabel = driver.findElement(By.xpath("//a[text()='Kids']"));
		action.moveToElement(kidLabel).perform();
		sleepInSecond(3);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");
	}

	// bài này mk chưa làm xong đang bị lỗi chỗ get ra text trong thẻ a
	 @Test
	public void TC_03_Hover() {
		driver.get("https://www.fahasa.com/");
		WebElement menu = driver.findElement(By.cssSelector("span.icon_menu"));
		action.moveToElement(menu).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Sách Trong Nước'] //span")).getText(),
				"Sách Trong Nước");
		System.out.println("aa" + driver.findElement(By.xpath("//li//a[text()='Tâm Lý']")));
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Tâm Lý']"))
				.getAttribute("href").contains("tam-ly.html"));
	}

	// @Test
	public void TC_04_Click_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-state-default"));
		Assert.assertEquals(listNumber.size(), 12);
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(10)).release().perform();
		sleepInSecond(3);
		listNumber = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
		Assert.assertEquals(listNumber.size(), 9);
	}

	// @Test
	public void TC_05_Click_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.cssSelector("li.ui-state-default"));
		action.keyDown(Keys.CONTROL).perform();
		action.click(listNumber.get(0)).click(listNumber.get(2)).perform();
		listNumber = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
		Assert.assertEquals(listNumber.size(), 2);
	}

	// @Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement doubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));
		js.executeScript("arguments[0].scrollIntoView(true);", doubleClick);
		action.doubleClick(doubleClick).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}

	//@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Delete']"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-item.context-menu-icon-delete.context-menu-hover")).isDisplayed());
		driver.findElement(By.xpath("//span[text()='Delete']")).click();
		Alert alert= driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: delete");
		alert.accept();
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-item")).isDisplayed());
		
		
	}
	@Test
	public void TC_07_Drag_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		WebElement source= driver.findElement(By.cssSelector("div#draggable"));
		WebElement target = driver.findElement(By.cssSelector("div#droptarget"));
		action.dragAndDrop(source, target).perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("div#droptarget")).getText(), "You did great!");
		
		String color = Color.fromString(driver.findElement(By.cssSelector("div#droptarget")).getCssValue("background-color")).asHex();
		Assert.assertEquals(color, "#03a9f4");
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
