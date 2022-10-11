package com.lenskart.TestNgPractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonLinkTest {

	static WebDriver driver;
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		System.out.println("Launching google chrome driver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		int count  = 0;
		driver.get("https://www.amazon.in/");
		
		List<WebElement> list = driver.findElements(By.xpath("//a"));
		
		Consumer<WebElement> consumer = (e) -> {
			if(!e.getText().isEmpty()) {
				System.out.println(count + " " + e.getText());
			}
			
		};
		
		list.forEach(consumer);
		
		
		
		
		
		
		
		
		
		
//		driver.findElements(By.xpath("//a"))
//				.stream()
//				.map(e->e.getText())
//				.filter(s->!s.isEmpty())
//				.distinct()
//				.sorted()
//				.filter(s->s.startsWith("C") || s.startsWith("D"))
//				.forEach(s->System.out.println(s));
//		
//		List<String> list = new ArrayList<>();
//		for(WebElement l : links) {
//			Set<String> set = new HashSet<>();
//			set.add(l.getText());
//			if(!l.getText().isEmpty()) {
//				list.add(l.getText());
//			}
//		}
//		List<String> listAfterDuplicates = new ArrayList<>(new HashSet<>(list));
//		Collections.sort(listAfterDuplicates);
//		for(String li : listAfterDuplicates) {
//			System.out.println(count++ +"->"+ li);
//		}
		
		
		driver.quit();
	}
}
