package com.lenskart.TestNgPractice;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorldPopulation {
	static WebDriver driver;
	public static void main(String[] args) {
		
		ChromeDriver driver = new ChromeDriver();
		
		WebDriverManager.chromedriver().setup();
		System.out.println("Launching google chrome driver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://www.worldometers.info/world-population/");
		
		String currentPopText = driver.findElement(By.xpath("//div[@id='maincounter-wrap']")).getText();
		WebElement currentPopNum = driver.findElement(By.xpath("//div[@class='maincounter-number']"));
		System.out.println(currentPopText);
		boolean flag = true;
		long timestamp = System.currentTimeMillis() / 1000;
		long seconds = timestamp + (timestamp*10);
		while(flag) {
			Calendar calendar = Calendar.getInstance();
			long currentTime = calendar.getTimeInMillis();
			calendar.add(Calendar.SECOND, 5);
			long afterSec = calendar.getTimeInMillis();
			String text = currentPopNum.getText();
			System.out.println(text);
			if(currentTime<afterSec) {
				break;
			}
						 
		}
		
	}

}
