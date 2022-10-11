package com.selenide.practice;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelenideTest {
	
	static WebDriver driver;
	
	@Test
	public void setup() throws InterruptedException {
		Configuration.pageLoadTimeout = 60000;
		Configuration.browser = "chrome";
		Selenide.open("https://selectorshub.com/xpath-practice-page/");
		
		Selenide.$(Selectors.byXpath("//*[@id='pact']")).click();
		Thread.sleep(4000);
		Selenide.actions().sendKeys(Keys.TAB).sendKeys("Something").perform();
		
//		Selenide.$(Selectors.byLinkText("Eyeglasses")).click();
//		Selenide.$("div.home.fullWidthContainer div.sectionComponents div.component div.row.section-component:nth-child(2) section.catSlider.second div:nth-child(1) center:nth-child(3) div:nth-child(1) a.catSlider_btn > div.catSlider_btn_view-range2").click();
		
	}
	
	@Test
	public void appiumTest() {
		
	}
	
	@Test
	public void LenskartTest() {
		Configuration.pageLoadTimeout = 60000;
		Configuration.browser = "chrome";
		Selenide.open("https://www.lenskart.com/");
		String str = Selenide.title();
		System.out.println("title: " + str);
	}
	
	
	@Test
	public void test3() {
		Configuration.pageLoadTimeout = 60000;
		Configuration.browser = "chrome";
		Selenide.open("https://devweb.caseworthy.com/caseworthy_8_0_nightly.caseworthy");
		Selenide.switchTo().frame(Selenide.$(By.xpath("//frame")));
		SelenideElement l = Selenide.$("#UserNameField");
		l.sendKeys("something");
	}
	
	@Test
	public void test1() throws InterruptedException {
		
		//This Element is inside 2 nested shadow DOM.
//		String cssSelectorForHost1 = "#snacktime";
//		String cssSelectorForHost2 = "#app2";
//		Thread.sleep(1000);
//		WebElement shadowDomHostElement0 = driver.findElement(By.cssSelector("#snacktime"));
//		WebElement last0 = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowDomHostElement0);
//		Thread.sleep(1000);
//		WebElement shadowDomHostElement1 = last0.findElement(By.cssSelector("#app2"));
//		WebElement last1 = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowDomHostElement1);
//		Thread.sleep(1000);
//		last1.findElement(By.cssSelector("#pizza"));
		
		
		//This Element is inside single shadow DOM.
//		String cssSelectorForHost1 = "#snacktime";
//		Thread.sleep(1000);
//		WebElement shadowDomHostElement = driver.findElement(By.cssSelector("#snacktime"));
//		WebElement last = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowDomHostElement);
//		Thread.sleep(1000);
//		last.findElement(By.cssSelector("#tea"));
		
		Configuration.pageLoadTimeout = 60000;
		Configuration.browser = "chrome";
		Selenide.open("https://selectorshub.com/xpath-practice-page/");
		
		Selenide.switchTo().frame("pact");
		WebElement element = Selenide.$(Selectors.byCssSelector("#snacktime"));
		SelenideElement last =  (SelenideElement) expandRootElement(element);
		//WebElement last =(WebElement) Selenide.executeJavaScript("return arguments[0].shadowRoot", Selenide.$("#snacktime"));
		//Selenide.actions().sendKeys(Keys.TAB).sendKeys("Something").perform();
		Thread.sleep(2000);
	//	last.findElement(By.cssSelector("#tea")).sendKeys("hi");
		last.$("#tea").sendKeys("Something");;
		Thread.sleep(5000);
	}
	
	@Test
	public void test2() throws InterruptedException {
		//WebDriver driver;
		WebDriverManager.chromedriver().setup();
		System.out.println("Launching google chrome driver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://selectorshub.com/xpath-practice-page/");
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='pact']")));
		String cssSelectorForHost1 = "#snacktime";
		Thread.sleep(2000);
		WebElement shadowDomHostElement = driver.findElement(By.xpath("//div[@id='snacktime']"));
		WebElement l = expandRootElement(shadowDomHostElement);
	//	WebElement last = (WebElement)((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowDomHostElement);
		Thread.sleep(2000);
		l.findElement(By.cssSelector("#tea")).sendKeys("hi");
	}
	
	
	 public static WebElement expandRootElement(WebElement shadowHost) {
	        WebElement returnObj = null;
	        Object shadowRoot = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowHost);
	        if (shadowRoot instanceof WebElement) {
	            // ChromeDriver 95
	            returnObj = (WebElement) shadowRoot;
	        }
	        else if (shadowRoot instanceof Map)  {
	            // ChromeDriver 96+
	            // Based on https://github.com/SeleniumHQ/selenium/issues/10050#issuecomment-974231601
	            Map<String, Object> shadowRootMap = (Map<String, Object>) shadowRoot;
	            String shadowRootKey = (String) shadowRootMap.keySet().toArray()[0];
	            String id = (String) shadowRootMap.get(shadowRootKey);
	            RemoteWebElement remoteWebElement = new RemoteWebElement();
	            remoteWebElement.setParent((RemoteWebDriver) driver);
	            remoteWebElement.setId(id);
	            returnObj = remoteWebElement;
	        }
	        else {
	            Assert.fail("Unexpected return type for shadowRoot in expandRootElement()");
	        }
	        return returnObj;
	    }

}
