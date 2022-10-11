package com.lenskartAttendence.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lenskart.base.WebBaseTest;

public class HomePage extends WebBaseTest {
	@FindBy(xpath = "//a[@class = 'mdc-button mdc-button--raised mdc-ripple-upgraded']") WebElement addBtn;
	@FindBy(xpath = "//tr[@role='row']/td[2]") WebElement dateText;
	
	
	public boolean isAddBtnVisible() {
		return isElementVisible(addBtn);
	}
	
	
	public AttendencePage clickOnAddBtn() {
		clickElement(addBtn, "Add button");
		return new AttendencePage();
	}
	
	public String getDateText() {
		return getText(dateText);
	}

}
