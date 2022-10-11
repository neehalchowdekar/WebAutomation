package com.lenskart.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lenskart.base.WebBaseTest;

public class PDPage extends WebBaseTest{
	
	@FindBy(xpath = "//*[text()='OKAY']")
	private static WebElement OKAY_XPATH;
	
	
	public void clickOkay() {
		if(isElementVisible(OKAY_XPATH)) {
			waitForClickable(OKAY_XPATH);
			clickElement(OKAY_XPATH, "clicked on OKAY button");
			//waitForInVisbility(OKAY_XPATH);
		}
		
	}

}
