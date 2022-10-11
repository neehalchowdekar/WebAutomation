package com.lenskart.vsm;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lenskart.base.WebBaseTest;

public class VSMLoginPage extends WebBaseTest {
	
	@FindBy(css = "#username")
	private WebElement username;
	
	@FindBy(css = "#password")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id='login']/form/div/dl[5]/dd/input")
	private WebElement loginButton;
	
	
	public VSMOrdersPage vsmLogin(String userName, String vsmPassword) {
		enterText(username, userName);
		enterText(password, vsmPassword);
		clickElement(loginButton, "Clicked on login button");
		return new VSMOrdersPage();
	}

}
