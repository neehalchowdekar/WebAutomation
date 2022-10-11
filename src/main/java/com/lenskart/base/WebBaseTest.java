package com.lenskart.base;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.function.Function;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.lenskart.utils.TestUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebBaseTest {
	public static final long WAIT = 30;
	protected static WebDriver driver;
	TestUtils utils = new TestUtils();

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(String browseType, String appURL) {
		switch (browseType.toLowerCase()) {
		case "chrome":
			driver = initChromeDriver(appURL);

			break;

		default:
			System.out.println("browser : " + browseType + " is invalid");
			break;
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		WebDriverManager.chromedriver().setup();
		System.out.println("Launching google chrome driver");
//		System.setProperty("webdriver.chrome.driver",
//				Thread.currentThread().getContextClassLoader().getResource("chromedriver.exe").getFile());
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	public WebBaseTest() {
		PageFactory.initElements(getDriver(), this);
	}

	public WebBaseTest(WebDriver driver) {
		this.driver = driver;
	}

	public void enterText(WebElement wb, String text) {
		waitForVisbility(wb);
		wb.sendKeys(text);
		utils.log().info("Entered text is: " + text);
	}
	
	public void clearContent(WebElement wb) {
		waitForVisbility(wb);
		wb.clear();
	}

	public void clickElement(WebElement wb, String msg) {
		waitForVisbility(wb);
		wb.click();
		utils.log().info("Clicked on element: " + msg);
	}

	public void pressEnter(WebElement wb) {
		wb.sendKeys(Keys.ENTER);
	}

	public void waitForVisbility(WebElement wb) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.visibilityOf(wb));
	}
	
	public void waitForClickable(WebElement wb) {
		WebDriverWait wait = new WebDriverWait(getDriver(), TestUtils.WAIT);
		wait.until(ExpectedConditions.elementToBeClickable(wb));
	}
	
	public void waitForInVisbility(WebElement wb) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 5);
		wait.until(ExpectedConditions.invisibilityOf(wb));
	}
	
	public void fluentWait(final WebElement wb) {
		FluentWait<WebDriver> wait =  new FluentWait<>(getDriver()).
				withTimeout(Duration.ofSeconds(WAIT)).
				pollingEvery(Duration.ofSeconds(5)).
				ignoring(NoSuchElementException.class);
		
		wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				// TODO Auto-generated method stub
				return wb;
			}
		});
	}

	public void clearText(WebElement wb) {
		wb.clear();
	}

	public String getText(WebElement wb) {
		String txt = wb.getText();
		utils.log().info("the text is: " + txt);
		return txt;
	}

	public boolean isElementVisible(WebElement wb) {
		try {
			if (wb.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public void openUrl(String url) {
		getDriver().navigate().to(url);
	}

	@Parameters({ "browserType", "appURL" })
	@BeforeTest
	public void initializeBaseSetup(String browserType, String appURL) {
		utils = new TestUtils();
		String strFile = "logs";
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		ThreadContext.put("ROUTINGKEY", strFile);
		try {
			setDriver(browserType, appURL);
		} catch (Exception e) {
			System.out.println("Error...." + e.getStackTrace());
		}
	}

	@AfterTest
	public void tearDown() {
		getDriver().quit();
	}

}
