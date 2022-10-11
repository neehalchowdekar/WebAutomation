package com.testing.playwright;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ShadowDomElement {

	@Test
	public void shadowDom() throws InterruptedException {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		// Open new page
		Page page = context.newPage();
		page.navigate("https://books-pwakit.appspot.com/");
		Thread.sleep(5000);
		page.locator("book-app[apptitle='BOOKS'] #input").fill("Testing books");
		String text = page.locator("book-app[apptitle='BOOKS'] .books-desc").textContent();
		System.out.println(text);
	}
	
	@Test
	public void iframeShadowDom() throws InterruptedException {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		// Open new page
		Page page = context.newPage();
		Thread.sleep(5000);
		page.navigate("https://selectorshub.com/xpath-practice-page/");
		page.frameLocator("#pact").locator("div#snacktime #tea").fill("normal tea");
	}

}
