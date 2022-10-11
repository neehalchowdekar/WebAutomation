package com.testing.playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightBasics {

	@Test
	public void openChromeBrowser() {
		Playwright playwright = Playwright.create();
		
		LaunchOptions lp = new LaunchOptions();
		lp.setChannel("chrome");
		lp.setHeadless(false);
		//lp.setTimeout(120000);
		
		Browser browser = playwright.chromium().launch(lp);
		Page page = browser.newPage();
		
		page.navigate("https://www.lenskart.com/");
		
		String title = page.title();
		System.out.println("title is: " + title);
	}
}
