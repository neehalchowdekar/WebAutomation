package com.vsm;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lenskart.base.WebBaseTest;
import com.lenskart.utils.TestUtils;
import com.lenskart.vsm.VSMLoginPage;
import com.lenskart.vsm.VSMOrdersPage;
import com.lenskartAttendence.pages.AttendencePage;
import com.lenskartAttendence.pages.HomePage;
import com.lenskartAttendence.pages.LoginPage;

public class OrderCancellation extends WebBaseTest{
	
	VSMLoginPage vsmLoginPage;
	VSMOrdersPage vsmOrdersPage;
	TestUtils utils = new TestUtils();
	
	
	@BeforeMethod
	public void beforeMethod(Method m) {
		vsmLoginPage = new VSMLoginPage();
		utils.log().info("\n"+ "*****Starting test:" + m.getName()+ "*****" + "\n");
		
	}
	
	
	
	@Parameters({"name","password","pageSize"})
	@Test(priority = 1)
	public void login(String name, String password, String PageSize) {
		vsmOrdersPage = vsmLoginPage.vsmLogin(name, password);
		openUrl("http://vsm.lenskart.com/getAllOrders/1/20");
		vsmOrdersPage.enterPhoneNumber("9967378964").clickOnSearch();
		//int size = Integer.parseInt(vsmOrdersPage.getPageSize());
		Integer totalOrders = vsmOrdersPage.getOrderCount();
		if (totalOrders < 300) {
			PageSize = String.valueOf(totalOrders);
			} 
		vsmOrdersPage.enterPageSize(PageSize);
		int pageCount = Integer.parseInt(PageSize);
		for(int i = 1; i <= pageCount; i++) {
			System.out.println(vsmOrdersPage.getStatusText(i));
		}
	}

}
