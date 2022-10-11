package com.lenskartAttendence.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lenskart.base.WebBaseTest;

public class LoginPage extends WebBaseTest{
	
	
	@FindBy(name = "e_code") private WebElement enterLoginId;
	@FindBy(name = "password") private WebElement enterPwd;
	@FindBy(id = "submitbtn") private WebElement loginBtn;
	
	
	public LoginPage enterLoginId(String loginId) {
		enterText(enterLoginId, loginId);
		return this;
	}
	
	public LoginPage enterPwd(String password) {
		enterText(enterPwd, password);
		return this;
	}
	
	public void clickLoginBtn() {
		clickElement(loginBtn, "Login Button");
	}
	
	public HomePage loginToWebSite(String loginId, String password) {
		enterLoginId(loginId);
		enterPwd(password);
		clickLoginBtn();
		return new HomePage();
	}

	

}
