package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Topic_07_DropDown_Custom {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Select select;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		// khỏi tạo
		jsExecutor = (JavascriptExecutor) driver;

		// driver.manage().window().setSize(new Dimension(1366,768));

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		// khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30);
	}
   //@Test
	public void TC_01_Jquery_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectedValueDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "7");

		selectedValueDropdown("span#number-button", "ul#number-menu div", "12");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "12");

		selectedValueDropdown("span#speed-button", "ul#speed-menu div", "Faster");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Faster");


		selectedValueDropdown("span#files-button", "ul#files-menu div", "Some unknown file");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),"Some unknown file");


		selectedValueDropdown("span#salutation-button", "ul#salutation-menu div", "Mr.");
		sleepInSecond(3);
		/*
		 * //click vào 1 phần tử để dropdown hiển thị ra
		 * driver.findElement(By.cssSelector("span#number-button")).click(); //chờ cho
		 * tất các item hiển thị, bắt 1 locator để đại diện cho tất cả các item
		 * explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.
		 * cssSelector("ul#number-menu div"))); //Nếu như item cần chọn đang hiển thị
		 * //Nếu item cần chọn không hiển thị thì cuộc chuột xuống //kiểm tra text của
		 * item có đúng cái cần chọn không , nếu đúng thì click vào //viết code để duyệt
		 * qua từng item và kiểm tra theo nhiều điều kiện
		 * 
		 * //lưu trữ tất các item thì mới duyệt được List<WebElement>allItems =
		 * driver.findElements(By.cssSelector("ul#number-menu div")); //duyệt qua từng
		 * item =>Sử dụng vòng lặp for (WebElement webElement : allItems) { String
		 * textItem = webElement.getText(); if (textItem.equals("7")) {
		 * webElement.click(); } }
		 * 
		 */

	}

	//@Test
	public void TC_02_Jquery_02() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		sleepInSecond(5);
		scroll("select#registration_fee", false);
		sleepInSecond(3);
		selectedValueDropdown("button#selectize-input", "div.dropdown-menu.show a", "CITY RS");
		sleepInSecond(3);

		select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Hà Nội");

		scroll("div.container-fluid", false);
		sleepInSecond(3);
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực I");
	}
	
	//@Test
	public void TC_03_RectJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(5);
		selectedValueDropdown("div#root", "div.visible.menu.transition div span", "Elliot Fu");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui.fluid.selection.dropdown")).getText(), "Elliot Fu");
	}
	//@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(3);
		selectedValueDropdown("div.btn-group", "div.btn-group a", "Second Option");
		Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group li.dropdown-toggle")).getText(), "Second Option");
	}

	//@Test
	public void TC_05_Select() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(3);
		selectedValueDropdown("div#root", "div#root div.item span", "Australia");
		
	}
	//@Test
	public void TC_05_Enter() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		sleepInSecond(3);
		enterValueDropdown("input.search", "div#root div.item span", "Australia");
	}
	public void sleepInSecond(long timeSeconds) {
		try {
			Thread.sleep(timeSeconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectedValueDropdown(String parentItem, String childItem, String expectedValue) {

		driver.findElement(By.cssSelector(parentItem)).click();
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItem)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childItem));
		for (WebElement webElement : allItems) {
			String actualValue = webElement.getText();
			if (actualValue.equals(expectedValue)) {
				webElement.click();
				break;
			}
		}
	}
	public void enterValueDropdown(String parentItem, String childItem, String expectedValue) {
		driver.findElement(By.cssSelector(parentItem)).clear();
		driver.findElement(By.cssSelector(parentItem)).sendKeys(expectedValue);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItem)));
		List<WebElement> allItems = driver.findElements(By.cssSelector(childItem));
		for (WebElement webElement : allItems) {
			String actualValue = webElement.getText();
			if (actualValue.equals(expectedValue)) {
				webElement.click();
				break;
			}
		}
	}

	public void scroll(String cssLocator, boolean status) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(status);",
				driver.findElement(By.cssSelector(cssLocator)));

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
