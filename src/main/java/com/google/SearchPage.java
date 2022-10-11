package com.google;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.lenskart.base.WebBaseTest;

public class SearchPage extends WebBaseTest{
	
	@FindBy(name = "q") WebElement searchEditBox;
	
	@FindBy(xpath = "//ul[@class='G43f7e']/li") List<WebElement> autoSuggestionList;
	
	@FindBy(name = "btnK") WebElement googleSearchButton;
	
	public SearchPage enterSearchText(String searchText) {
		enterText(searchEditBox, searchText);
		return this;
	}
	
	public SearchListPage clickOnAutoSuggestion(int nth) {
		clickElement(autoSuggestionList.get(nth), nth + " element");
		return new SearchListPage();
	}
	
}
