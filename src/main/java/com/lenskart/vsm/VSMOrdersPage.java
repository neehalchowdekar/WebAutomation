package com.lenskart.vsm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lenskart.base.WebBaseTest;

public class VSMOrdersPage extends WebBaseTest{
	
	@FindBy(xpath = "//*[@id='phone']")
	private WebElement phoneXpath;
	
	@FindBy(xpath = "//*[@id='email']")
	private WebElement emailXpath;
	
	@FindBy(xpath = "//*[@id='searchForm']/span[4]/input")
	private WebElement searchButton;
	
	@FindBy(xpath = "//*[@id='rounded-corner']/tbody/tr[1]/td[2]")
	private WebElement orderDateXpath;
	
	@FindBy(css = "#pageNo")
	private WebElement pageNo;
	
	@FindBy(css = "#pageSize")
	private WebElement pageSizeId;
	
	
	@FindBy(xpath = "//*[@id='TABLE_1']/tbody/tr/td[1]")
	private WebElement countText;
	
	public VSMOrdersPage enterPhoneNumber(String phoneNumber) {
		enterText(phoneXpath, phoneNumber);
		return this;
	}
	
	public VSMOrdersPage clickOnSearch() {
		clickElement(searchButton, "Clicked on search button");
		return this;
	}
	
	
	public String getStatusText(int j) {
		return getText(getDriver().findElement(getStatus(j)));
	}
	
	public By getStatus(int j) {
		return By.xpath("//*[@id='rounded-corner']/tbody/tr[" + j + "]/td[6]");
	}
	
	public Integer getOrderCount() {
		String text = getText(countText);
		int index = text.indexOf("of");
		text = text.substring(index + 2, text.length()).trim();
		text = text.substring(0, text.indexOf(" ")).trim();
		  Integer totalOrders = Integer.parseInt(text);
		  return totalOrders;
	}
	
	public VSMOrdersPage enterPageSize(String pageSize) {
		clearContent(pageSizeId);
		enterText(pageSizeId, pageSize);
		pressEnter(pageSizeId);
		return this;
	}
	

}
