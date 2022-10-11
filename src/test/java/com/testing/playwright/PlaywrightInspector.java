package com.testing.playwright;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class PlaywrightInspector {

	@Test
	public void playwrightTest() {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			// Open new page
			Page page = context.newPage();
			// Go to https://selectorshub.com/xpath-practice-page/
			page.navigate("https://selectorshub.com/xpath-practice-page/");
			// Click [placeholder="Enter email"]
			page.locator("[placeholder=\"Enter email\"]").click();
			// Fill [placeholder="Enter email"]
			page.locator("[placeholder=\"Enter email\"]").fill("test@gmail.com");
			// Click [placeholder="Enter Password"]
			page.locator("[placeholder=\"Enter Password\"]").click();
			// Fill [placeholder="Enter Password"]
			page.locator("[placeholder=\"Enter Password\"]").fill("test123");
			// Click [placeholder="Enter your company"] >> nth=0
			page.locator("[placeholder=\"Enter your company\"]").first().click();
			// Fill [placeholder="Enter your company"] >> nth=0

			page.pause();

			page.locator("[placeholder=\"Enter your company\"]").first().fill("");

			page.locator("[placeholder=\"Enter your company\"]").first().fill("testing.com");

			page.locator("[placeholder=\"Enter your company\"]").first().fill("");

			// Click text=Submit
			page.locator("text=Submit").click();
			// assertThat(page).hasURL("https://selectorshub.com/xpath-practice-page/?email=test%40gmail.com&Password=test123&company=testing.com&company=&company=&company=");
			// Click text=Next boot camp is happening on 03 Apr, click to register.
			page.locator("text=Next boot camp is happening on 03 Apr, click to register.").click();
			// assertThat(page).hasURL("https://selectorshub.com/selectorshub-training/");
			// Go to
			// https://selectorshub.com/xpath-practice-page/?email=test%40gmail.com&Password=test123&company=testing.com&company=&company=&company=
			page.navigate(
					"https://selectorshub.com/xpath-practice-page/?email=test%40gmail.com&Password=test123&company=testing.com&company=&company=&company=");
		}
	}

	@Test
	public void lenskartLogin() {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();

//			// Start tracing before creating / navigating a page.
//			context.tracing().start(new Tracing.StartOptions()
//					  .setScreenshots(true)
//					  .setSnapshots(true));
			
			// Open new page
			Page page = context.newPage();

			// Go to https://www.lenskart.com/eyeglasses.html
			page.navigate("https://www.lenskart.com/eyeglasses.html");

			// Click text=No thanks
			if(page.locator("text=No thanks").isVisible())
			page.locator("text=No thanks").click();

			// Click text=Sign In &
			page.locator("text=Sign In &").click();

			// Click text=ProceedGet updates on Whatsapp*T&C Apply >> span >> nth=0
			page.locator("text=ProceedGet updates on Whatsapp*T&C Apply >> span").first().click();

			// Click div[role="document"] >> text=Sign Up
			page.locator("div[role=\"document\"] >> text=Sign Up").click();

			// Click div[role="document"] >> text=Sign In
			page.locator("div[role=\"document\"] >> text=Sign In").click();

			// Click [placeholder="Mobile \/ Email"]
			page.locator("[placeholder=\"Mobile \\/ Email\"]").click();

			// Fill [placeholder="Mobile \/ Email"]
			page.locator("[placeholder=\"Mobile \\/ Email\"]").fill("lenskart.test3110@gmail.com");

			// Click text=Proceed
			page.locator("text=Proceed").click();

			// Click [placeholder="Please enter password"]
			page.locator("[placeholder=\"Please enter password\"]").click();

			// Fill [placeholder="Please enter password"]
			page.locator("[placeholder=\"Please enter password\"]").fill("123456");

			// Click text=Proceed
			page.locator("text=Proceed").click();

			// Click .slick-slide.slick-active div .catSlider_carouselItem a .getGaData >>
			// nth=0
			Page page1 = page.waitForPopup(() -> {
				page.locator(".slick-slide.slick-active div .catSlider_carouselItem a .getGaData").first().click();
			});

			//page.pause();
			// Click text=OKAY
			page1.hover("text=OKAY");
			page1.locator("text=OKAY").first().click();
			
//			// Stop tracing and export it into a zip archive.
//			context.tracing().stop(new Tracing.StopOptions()
//			  .setPath(Paths.get("trace.zip")));
		}

	}

}
