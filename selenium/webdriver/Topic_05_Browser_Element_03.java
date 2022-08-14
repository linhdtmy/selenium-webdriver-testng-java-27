package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Browser_Element_03 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement email = driver.findElement(By.xpath("//input[@id='mail']"));
		if (email.isDisplayed()) {
			email.sendKeys("abc@gmail.com");
			System.out.println("Email is displayed ");
		} else {
			System.out.println("Email is not displayed ");
		}
		WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (age.isDisplayed()) {
			age.click();
			System.out.println(" age is displayed ");
		} else {
			System.out.println(" age is not displayed ");
		}
		WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if (education.isDisplayed()) {
			education.sendKeys("education");
			System.out.println(" education is displayed ");
		} else {
			System.out.println(" education is not displayed ");
		}

	}

	@Test
	public void TC_02_Enable() {

		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement email = driver.findElement(By.xpath("//input[@id='mail']"));
		if (email.isEnabled()) {
			email.sendKeys("abc@gmail.com");
			System.out.println("Email is enable ");
		} else {
			System.out.println("Email is not enable ");
		}
		WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (age.isEnabled()) {
			age.click();
			System.out.println(" age is enable ");
		} else {
			System.out.println(" age is not enable ");
		}
		WebElement education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if (education.isEnabled()) {

			System.out.println(" education is enable ");
		} else {
			System.out.println(" education is not enable ");
		}

		WebElement job01 = driver.findElement(By.xpath("//select[@id='job1']"));
		if (job01.isEnabled()) {
			System.out.println("Job 01 is enable");
		} else {
			System.out.println("Job 01 is not enable");
		}
		WebElement job02 = driver.findElement(By.xpath("//select[@id='job2']"));
		if (job02.isEnabled()) {
			System.out.println("Job 02 is enable");
		} else {
			System.out.println("Job 02 is not enable");
		}
		WebElement developmentOption = driver.findElement(By.xpath("//input[@value='interest_development']"));
		if (developmentOption.isEnabled()) {
			System.out.println("developmentOption is enable");
		} else {
			System.out.println("developmentOption is not enable");
		}
		WebElement slider = driver.findElement(By.xpath("//input[@id='slider-1']"));
		if (slider.isEnabled()) {
			System.out.println("slider is enable");
		} else {
			System.out.println("slider is not enable");
		}
	}

	@Test
	public void TC_03_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement age = driver.findElement(By.xpath("//input[@id='under_18']"));
		age.click();
		WebElement java = driver.findElement(By.cssSelector("input#java"));
		java.click();
		if (age.isSelected()) {
			System.out.println("age is selected");
		} else {
			System.out.println("age is not selected");
		}
		if (java.isSelected()) {
			System.out.println("checkbox Java is selected");
		} else {
			System.out.println("checkbox Java is not selected");
		}
		java.click();
		if (java.isSelected()) {
			System.out.println("checkbox Java is selected");
		} else {
			System.out.println("checkbox Java is not selected");
		}

	}

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		WebElement email = driver.findElement(By.cssSelector("input#email"));
		email.sendKeys("abc@gmail.com");
		sleepInSecond(3);
		WebElement password = driver.findElement(By.cssSelector("input#new_password"));
		password.sendKeys("aaaaaa");
		sleepInSecond(3);
		WebElement message = driver.findElement(By.cssSelector(".completed"));
		String color = message.getCssValue("color");
		if (color.equals("rgb(0, 133, 71)")) {
			System.out.println("pass is lowercase");
		}
		password.clear();
		password.sendKeys("aaaaAa");
		sleepInSecond(3);
		message = driver.findElement(By.cssSelector("li.uppercase-char.completed span"));
		color = message.getCssValue("color");
		if (color.equals("rgb(0, 133, 71)")) {
			System.out.println("pass is uppercase");
		}
		password.clear();
		password.sendKeys("aaaaAa1");
		sleepInSecond(3);
		message = driver.findElement(By.xpath("//span[text()='One number']"));
		color = message.getCssValue("color");
		if (color.equals("rgb(0, 133, 71)")) {
			System.out.println("pass is number");
		}
		password.clear();
		password.sendKeys("aaaaAa1@");
		sleepInSecond(3);
		message = driver.findElement(By.cssSelector("li.special-char.completed span"));
		color = message.getCssValue("color");
		if (color.equals("rgb(0, 133, 71)")) {
			System.out.println("pass is special char");
		}
		password.clear();
		password.sendKeys("aaaaAa1@12");
		message = driver.findElement(By.xpath("//span[text()='8 characters minimum']"));
		color = message.getCssValue("color");
		if (color.equals("rgb(0, 133, 71)")) {
			System.out.println("pass is 8 char");
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
