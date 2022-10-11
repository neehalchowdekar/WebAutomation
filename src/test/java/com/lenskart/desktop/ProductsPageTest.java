package com.lenskart.desktop;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lenskart.base.WebBaseTest;

public class ProductsPageTest extends WebBaseTest{
	
	
	

	@Test(enabled = true)
	public void navigate_to_PDP() throws InterruptedException {
		String mainWindowHandle = getDriver().getWindowHandle();
		System.out.println("Main Window handle: " + mainWindowHandle);
		WebElement wb =  getDriver().findElement(By.xpath("//*[text()='Lenskart Air1']"));
		fluentWait(wb);
//		Alert alt = getDriver().switchTo().alert();
//		alt.accept();
		
		clickElement(wb, "clicked on webElement");
		
		Set<String> allHandles = getDriver().getWindowHandles();
		getDriver().switchTo().alert();
		
		
		System.out.println("Window opening after click the products: " + allHandles.size());
		
		for(String windowHandle: allHandles) {
			if(mainWindowHandle.equals(windowHandle)) {
				Thread.sleep(1000);
				getDriver().switchTo().window(windowHandle);
				System.out.println("Main Window ID 1: " + windowHandle + 
						"\n" + "URL: " + getDriver().getCurrentUrl() + "\n" + 
						"\n" + "Title: " + getDriver().getTitle()
			);
			}else {
				Thread.sleep(1000);
				getDriver().switchTo().window(windowHandle);
				System.out.println("Main Window ID 2: " + windowHandle + 
						"\n" + "URL: " + getDriver().getCurrentUrl() + "\n" + 
						"\n" + "Title: " + getDriver().getTitle());
				
			}
			
		}
		
		
		
	}
	
	@Test(enabled = false)
	public void getTextFromCMSpage() throws IOException {
		JavascriptExecutor ex = (JavascriptExecutor)getDriver();
		
		//Actions actions = new Actions(getDriver());
		List<String> names = new ArrayList<>();
		
		WebElement wb =  getDriver().findElement(By.xpath("//div[@class='desktop']//button"));
		fluentWait(wb);
		List<WebElement> elements = getDriver().findElements(By.xpath("//div[@class='desktop']//button[starts-with(@class,'btn-roll')]"));
		
		for(WebElement element : elements) {
			String name = element.getText();
			names.add(name);
			ex.executeScript("arguments[0].click();", element);
			String color = element.getCssValue("background-color");
			WebElement woBtnColor =  getDriver().findElement(By.xpath("//a[@id='new']"));
			String withoutpowerBtnColor =  woBtnColor.getCssValue("background-color");
			assertEquals(color, withoutpowerBtnColor, "Color did not match");
			
			
			
			}
		TakesScreenshot screenShoot = (TakesScreenshot) getDriver();
		File srcFile = screenShoot.getScreenshotAs(OutputType.FILE);
		File desFile = new File(System.getProperty("user.dir") + File.separator +"image.png");
		FileUtils.copyFile(srcFile, desFile);
		System.out.println(names.toString());
		Set<Cookie> cookie = getDriver().manage().getCookies();
		System.out.println(cookie.toString());
		
//		Cookie cookie = getDriver().manage().getCookieNamed("frontend");
//		System.out.println(cookie);
		
	}
	
//	@Test
//	public void getSessionToken() {
//		getDriver().manage()
//	}

}
