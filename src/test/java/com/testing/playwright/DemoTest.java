package com.testing.playwright;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Response;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.WaitForSelectorState;

public class DemoTest {
	
	private static Playwright playwright;
	private static Browser browser;
	
	private BrowserContext context;
	private Page page;
	
//	public static void main(String[] args) {
//		
//			Playwright playwright = Playwright.create();
//			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//			Page page = browser.newPage();
//			page.navigate("https://www.lenskart.com/");
//			System.out.println(page.title()); 
//			browser.close();
//			playwright.close();
//	}
	
	@BeforeTest
	public void startBrowser() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
		
	}
	
	@AfterTest
	public void closeBrowser() {
		browser.close();
		playwright.close();
	}
	
	@BeforeMethod
	public void startPage() {
		context = browser.newContext();
		page = context.newPage();
	}
	
	@AfterMethod
	public void closePage() {
		context.close();
	}
	
	
	@Test
	public void testLogin() {
		
//		Playwright playwright = Playwright.create();
//		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
//		Page page = browser.newPage();
		page.navigate("https://demo.applitools.com/");
		page.fill("id=username", "andy");
		page.fill("id=password", "panda<3");
		page.click("id=log-in");
		assertThat(page.locator(".element-header >> nth=0")).hasText("Financial Overview");
		
	}
	
	@Test
	public void testDropDown() {
		page.navigate("https://kitchen.applitools.com/ingredients/select");
		String[] values = new String [] {"chili-powder", "garlic", "ginger", "paprika"};
		//page.selectOption("id=spices-select-single", new String[] {"chili-powder", "garlic", "ginger", "paprika"});
		//page.selectOption("id=spices-select-single", "ginger");
		//page.selectOption("id=spices-select-single", values);
//		assertThat(page.locator("id=spices-select-single")).hasValue(values[0]);
//		assertThat(page.locator("id=spices-select-single")).hasValue(values[1]);
//		assertThat(page.locator("id=spices-select-single")).hasValue(values[2]);
//		assertThat(page.locator("id=spices-select-single")).hasValue(values[3]);
		
		for(String s : values) {
			page.selectOption("id=spices-select-single", s);
			assertThat(page.locator("id=spices-select-single")).hasValue(s);
		}
		
	}
	
	@Test
	public void testMultiSelect() {
		page.navigate("https://kitchen.applitools.com/ingredients/select");
		String[] values = new String [] {"chili-powder", "garlic", "ginger", "paprika"};
		page.selectOption("id=spices-select-multi", values[1]);
		assertThat(page.locator("id=spices-select-multi")).hasValue(values[1]);
//		for(String s : values) {
//		page.selectOption("id=spices-select-multi", s);
//		assertThat(page.locator("id=spices-select-multi")).hasValue(s);
//		}
	}
	
	
	@Test
	public void uploadPic() {
		Path path = Paths.get("src","test", "resources","pic.jpg");
		page.navigate("https://kitchen.applitools.com/ingredients/file-picker");
		page.setInputFiles("id=photo-upload", path);
	}
	
	@Test
	public void testIFrame() {
		page.navigate("https://kitchen.applitools.com/ingredients/iframe");
		assertThat(page.frameLocator("id=the-kitchen-table").locator("id=fruits-vegetables")).isVisible();
	}
	
	@Test
	public void testExplicitWait() {
		page.navigate("https://automationbookstore.dev/");
		page.fill("id=searchBar", "testing");
		page.locator("li.ui-screen-hidden >> nth=0").waitFor(
				new Locator.WaitForOptions()
							.setState(WaitForSelectorState.HIDDEN)
							.setTimeout(5000));
		
		assertThat(page.locator("li:not(.ui-screen-hidden)")).hasCount(1, new LocatorAssertions.HasCountOptions().setTimeout(5000));
	}
	
	@Test
	public void testAcceptAlert() {
		page.navigate("https://kitchen.applitools.com/ingredients/alert");
	//	page.onDialog(Dialog :: accept);
		page.click("id=alert-button");
	}
	
	@Test
	public void testDismissAlert() {
		page.navigate("https://kitchen.applitools.com/ingredients/alert");
		page.onDialog(Dialog :: dismiss);
		page.locator("id=prompt-button");
	}
	@Test
	public void testAnswePrompt() {
		page.navigate("https://kitchen.applitools.com/ingredients/alert");
		page.onDialog(dialog -> dialog.accept("nachos"));
		page.click("id=prompt-button");
	}
	
	@Test
	public void testNewTab() {
		page.navigate("https://kitchen.applitools.com/ingredients/links");
		Page newTab = context.waitForPage(() -> page.click("id=button-the-kitchen-table"));
		assertThat(newTab.locator("id=fruits-vegetables")).isVisible();
	}
	
	@Test
	public void testApi() {
		APIResponse response =  context.request().get("https://kitchen.applitools.com/api/recipes");
		 JsonObject json =  new Gson().fromJson(response.text(), JsonObject.class);
		assertTrue(response.ok());
		System.out.println(json.get("data[0]"));
		
	}
}
