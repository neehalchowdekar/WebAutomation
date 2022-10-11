package com.testing.playwright;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class TraceViewerScript {

	
	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();

			// Start tracing before creating / navigating a page.
			context.tracing().start(new Tracing.StartOptions()
					  .setScreenshots(true)
					  .setSnapshots(true));
			
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
//
//			page.pause();
//			// Click text=OKAY
//			//page1.hover(".ok-btn.padding-5.fs14.letter-spacing-1.bg-white.text-topaz.text-uppercase");
//			page1.locator(".ok-btn.padding-5.fs14.letter-spacing-1.bg-white.text-topaz.text-uppercase").first().click();
			
			// Stop tracing and export it into a zip archive.
			context.tracing().stop(new Tracing.StopOptions()
			  .setPath(Paths.get("trace.zip")));
		}
	}
}
