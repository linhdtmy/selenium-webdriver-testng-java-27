package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JsExecutor {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Random random;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		jsExecutor = (JavascriptExecutor) driver;
		random = new Random();
	}

	@Test
	public void TC_01_TechPanda() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(5);

		String domainName = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainName, "live.techpanda.org");

		String urlName = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(urlName, "http://live.techpanda.org/");

		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(3);

		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(3);
		String titleUrl = (String) executeForBrowser("return document.title");
		Assert.assertEquals(titleUrl, "Customer Service");

		scrollToElementOnTop("//input[@id='newsletter']");
		hightlightElement("//input[@id='newsletter']");

		String email = "abc123" + random.nextInt(100) + "@gmail.com";
		sendkeyToElementByJS("//input[@id='newsletter']", email);
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(3);
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(3);
		String domain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domain, "demo.guru99.com");
	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {
		String email = "abc123" + random.nextInt(100) + "@gmail.com";

		navigateToUrlByJS("https://warranty.rode.com/");
		sleepInSecond(3);
		String registrator = "//button[contains(text(),'Register')]";
		clickToElementByJS(registrator);
		sleepInSecond(3);
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='firstname']", "Hoaint");
		clickToElementByJS(registrator);
		Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"), "Please fill out this field.");

		sendkeyToElementByJS("//input[@id='surname']", "Hoaint");
		clickToElementByJS(registrator);
		Assert.assertEquals(getElementValidationMessage("//form[@action='https://warranty.rode.com/register']//input[@id='email']"), "Please fill out this field.");
		sleepInSecond(3);

		sendkeyToElementByJS("//form[@action='https://warranty.rode.com/register']//input[@id='email']", email);
		clickToElementByJS(registrator);
		Assert.assertEquals(getElementValidationMessage(" //form[@action='https://warranty.rode.com/register']//input[@id='password']"), "Please fill out this field.");
		sleepInSecond(3);

		sendkeyToElementByJS("//form[@action='https://warranty.rode.com/register']//input[@id='password']", "abc@12345");
		clickToElementByJS(registrator);
		Assert.assertEquals(getElementValidationMessage("//input[@id='password-confirm']"), "Please fill out this field.");
		sleepInSecond(3);

		sendkeyToElementByJS("//input[@id='password-confirm']", "abc@12345");
		sleepInSecond(3);
		clickToElementByJS(registrator);

	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
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
