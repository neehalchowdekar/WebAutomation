package com.lenskart.desktop;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.SearchPage;
import com.lenskart.base.WebBaseTest;
import com.lenskart.page.PDPage;

public class GoogleTest extends WebBaseTest{
	
	SearchPage searchPage;
	PDPage pdPage;
	
	@BeforeMethod
	public void beforeMethod() {
		searchPage = new SearchPage();
		pdPage = new PDPage();
		
	}
	
	@Test
	public void searchText() {
		String resultStat = searchPage
		.enterSearchText("Something")
		.clickOnAutoSuggestion(3)
		.verifyResultstat()
		.enterSearchText("Something")
		.clickOnAutoSuggestion(2)
		.getResultStat();
		System.out.println("The result stat is: " + resultStat);
	}
	@Test
	public void clickOnOKay() {
		pdPage.clickOkay();
	}
}
