package com.lenskart.attendenceTest;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lenskart.base.WebBaseTest;
import com.lenskart.utils.GetDataProvider;
import com.lenskart.utils.TestUtils;
import com.lenskartAttendence.pages.AttendencePage;
import com.lenskartAttendence.pages.HomePage;
import com.lenskartAttendence.pages.LoginPage;

public class AttendanceTest extends WebBaseTest{
	LoginPage loginPage;
	HomePage homePage;
	AttendencePage attendencePage;
	TestUtils utils = new TestUtils();
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		loginPage = new LoginPage();
		homePage = new HomePage();
		attendencePage = new AttendencePage();
		utils.log().info("\n"+ "*****Starting test:" + m.getName()+ "*****" + "\n");
		
	}
	@Parameters({"name","password"})
	@Test(priority = 1)
	public void login(String name, String password) {
		loginPage.loginToWebSite(name, password);
	}
	
	@Test(priority = 2, dataProvider = "AttendenceData")
	public void enterAttendenceDetails(String xldate, String desc, String hour ) throws Throwable {
		Thread.sleep(1000);
		homePage.clickOnAddBtn();
		Thread.sleep(1000);
		boolean dateElement = attendencePage.isDatePresent();
		Assert.assertTrue(dateElement, "unable to load add page");
		SimpleDateFormat sdf1 = new SimpleDateFormat("M/dd/yy");
		Date d = sdf1.parse(xldate);
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
		String date = sdf2.format(d);
		attendencePage.enterAttendenceDetailsAndSubmit(date, desc, hour);
		Thread.sleep(500);
		String dateText = homePage.getDateText();
		Assert.assertEquals(date, dateText, "Updated attendence date does not match ");
		}
	
	@DataProvider(name = "AttendenceData")
	public String[][] getXLData() throws IOException{
		GetDataProvider data = new GetDataProvider("/data/attendence.xlsx");
		return data.getData();
	}
}
