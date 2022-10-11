package com.lenskartAttendence.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lenskart.base.WebBaseTest;

public class AttendencePage extends WebBaseTest{
	
	
	@FindBy(id = "date_status") WebElement dateTextBox;
	@FindBy(id = "add-section") WebElement addSectionBtn;
	@FindBy(xpath = "//ancestor::div[@class='col-sm-12 nopadding']//input[@type='text']") WebElement workTextBox;
	@FindBy(xpath = "//ancestor::div[@class='col-sm-12 nopadding']//input[@type='number']") WebElement hourTextBox;
	@FindBy(xpath = "//button[@class ='submitworks btn btn-primary']") WebElement submitBtn;
	
	public boolean isDatePresent() {
		return isElementVisible(dateTextBox);
	}
	
	public AttendencePage enterDateText(String date) {
		enterText(dateTextBox, date);
		return this;
	}
	
	public AttendencePage enterWorkText(String text) {
		enterText(workTextBox, text);
		return this;
	}
	
	public AttendencePage enterHourText(String hour) {
		enterText(hourTextBox, hour);
		return this;
	}
	public void clickOnSubmitBtn() {
		clickElement(submitBtn, "Submit button");
		
	}
	
	public HomePage enterAttendenceDetailsAndSubmit(String xldate,String description, String hour ) throws Throwable {
//		SimpleDateFormat sdf1 = new SimpleDateFormat("M/dd/yy");
//		Date d = sdf1.parse(xldate);
//		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
//		String date = sdf2.format(d);
		enterDateText(xldate);
		clickElement(addSectionBtn, "Add your work button");
		enterWorkText(description);
		enterHourText(hour);
		clickOnSubmitBtn();
		return new HomePage();
		
	}
	
	

}
