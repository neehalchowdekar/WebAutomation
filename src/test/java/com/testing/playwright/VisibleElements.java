package com.testing.playwright;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class VisibleElements {
	
	//we have to use 
	// button:visible
	// button >>visible=true
	
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		// Open new page
		Page page = context.newPage();
		page.navigate("https://www.amazon.in/");
		page.locator("a:visible").allInnerTexts().forEach(System.out::println);
		
		int count = page.locator("xpath=//img >> visible=true").count();
		System.out.println(count);
	}

}
