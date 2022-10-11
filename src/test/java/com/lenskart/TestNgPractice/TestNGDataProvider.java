package com.lenskart.TestNgPractice;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class TestNGDataProvider {
	
	@BeforeMethod()
	public void beforeMethod() {
		System.out.println("before Method");
	}
	
	@AfterMethod
	public void AfterMethod() {
		System.out.println("after Method");
	}
	
	@Test(dataProvider = "getData2")
	public void showData2(String str2, String str3) {
		System.out.println("showData2 -> str2 = " + str2 + ", str3 = " + str3);
	}
	
	@Test(dataProvider = "getData1")
	public void showData33(String str2, String str3) {
		System.out.println("showData2 -> str2 = " + str2 + ", str3 = " + str3);
	}

	@DataProvider
	public Object[][] getData1() {
		return new Object[][] { { 1, "Java" }, { 2, "Python" }, {3, "Ruby"} };
	}

	@DataProvider
	public Iterator<Object[]> getData2() {
		Object[] obj1 = { "JavaSript", "Kotlin",};
		Object[] obj2 = {"python", "Ruby"};
		Object[] obj3 = {"Java", "J2e"};
		ArrayList<Object[]> testData = new ArrayList<>();
		testData.add(obj1);
		testData.add(obj2);
		return testData.iterator();
	}

}
