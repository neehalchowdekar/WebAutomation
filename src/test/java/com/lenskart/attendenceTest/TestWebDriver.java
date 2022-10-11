package com.lenskart.attendenceTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestWebDriver {
	
	@Test
	public void demoWebDriver() {
		//WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		
		//WebDriver driver = new ChromeDriver();
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://lenskart.com");
		System.out.println(driver.getTitle());
		driver.quit();
		
	}

}
