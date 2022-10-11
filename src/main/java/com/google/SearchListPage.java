package com.google;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.google.common.util.concurrent.Uninterruptibles;
import com.lenskart.base.WebBaseTest;

public class SearchListPage extends WebBaseTest {
	
	@FindBy(name = "q") WebElement searchEditBox;
	
	@FindBy(xpath = "//button[@type='submit']") WebElement searchIcon;
	
	@FindBy(xpath = "//div[@class='MUFPAc']/div") List<WebElement> searchOptions;
	
	@FindBy(xpath = "//ul[@class='G43f7e']/li") List<WebElement> autoSuggestionList;
	
	@FindBy(id = "result-stats") WebElement resultStat;
	
	public SearchListPage enterSearchText(String searchText) {
		enterText(searchEditBox, searchText);
		return this;
	}
	
	public SearchListPage clickOnAutoSuggestion(int nth) {
		Uninterruptibles.sleepUninterruptibly(5000, TimeUnit.MILLISECONDS);
		clickElement(autoSuggestionList.get(nth), nth + "");
		return this;
	}
	
	public SearchListPage verifyResultstat() {
		Assert.assertTrue(isElementVisible(resultStat));
		return this;
	}
	
	public String getResultStat() {
		return getText(resultStat);
	}

}
