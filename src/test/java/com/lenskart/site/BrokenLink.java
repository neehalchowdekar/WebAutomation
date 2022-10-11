package com.lenskart.site;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrokenLink {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get("https://www.lenskart.sg/");
		
		
		List<String> urlList = new ArrayList<>();
		urlList.add("https://lenskart.sg/");
		urlList.add("https://lenskart.sg/pages/stores");
		urlList.add("https://lenskart.sg/collections/contact-lenses");
		urlList.add("https://www.lenskart.sg/pages/track-order");
		urlList.add("https://lenskart.sg/collections/tokai-lenses");
		
		urlList.parallelStream().forEach(e -> checkBrokenLinks(e));

//		List<WebElement> links = 
//				driver
//				.findElements(By.tagName("a"))
//				.parallelStream()
//				.map(e -> e.getAttribute("href"))
//				.forEach(e -> checkBrokenLinks(e));
				
				driver.quit()
				
									;
//		List<String> urlList = new ArrayList<>();
//		System.out.println("No of links present: " + links.size());
//		for (WebElement e : links) {
//			String url = e.getAttribute("href");
//			urlList.add(url);
//			
//		}
		
//		urlList.parallelStream().forEach(e -> checkBrokenLinks(e));
	}

	public static void checkBrokenLinks(String linkUrl) {
		try {
			URL url = new URL(linkUrl);
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setConnectTimeout(5000);
			httpUrlConnection.connect();
			if (httpUrlConnection.getResponseCode() >= 400) {
				System.out.println(linkUrl + " ---> " + httpUrlConnection.getResponseMessage() + " is a broken link");
			} else {
				System.out.println(linkUrl + " ---> " + httpUrlConnection.getResponseMessage());
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
//	@Test
//	public void brokenlinkTest() {
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.get(null);
//	}

}
