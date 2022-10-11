package com.testing.playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FrameHandle {
	
	@Test
	public void frameHandle() {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();
		page.navigate("http://www.londonfreelance.org/courses/frames/index.html");
		
		//1st option to use frame in playwright
		String header = page.frameLocator("frame[name='main']").locator("h2").textContent();
		System.out.println(header);
		
		//2nd option is to use directly frame method
		String header1 = page.frame("main").locator("h2").textContent();
		System.out.println(header1);
		
		//3rd option is can use xpath as well
		String header2 = page.frameLocator("//frame[@name='main']").locator("h2").textContent();
		System.out.println(header2);
		
	}
	
	@Test
	public void iframeHandle() {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		
		Page page = browser.newPage();
		page.navigate("https://www.formsite.com/templates/registration-form-templates/vehicle-registration-form/");
		
		page.locator("img[title='vehicle-registration-forms-and-templates']").click();
		page.frameLocator("//iframe[contains(@id,'frame-one')]").locator("#RESULT_TextField-8").fill("Neehal");
	}

}
