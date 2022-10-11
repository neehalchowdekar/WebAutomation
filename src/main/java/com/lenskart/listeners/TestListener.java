package com.lenskart.listeners;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.lenskart.base.WebBaseTest;

import com.lenskart.reports.ExtendReport;
import com.lenskart.utils.TestUtils;


public class TestListener implements ITestListener{
	WebBaseTest webBaseTest = new WebBaseTest();
	TestUtils utils = new TestUtils();
	public void onTestFailure(ITestResult result) {
		if(result.getThrowable() != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			result.getThrowable().printStackTrace(pw);
			utils.log(sw.toString());
		}
			
			TakesScreenshot ts= (TakesScreenshot) webBaseTest.getDriver();
			
			
			
			byte[] encoded = null;
			File screenShotSrc = ts.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "/Folder" +"/failed.png";
			
			System.out.println(path);
			
			File screenShotDesc = new File(path);
			
			try {
				FileUtils.copyFile(screenShotSrc, screenShotDesc);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				ExtendReport.getTest().fail("Test Failed", 
						MediaEntityBuilder.createScreenCaptureFromPath(path).build());
					} catch (IOException e) {
				e.printStackTrace();
				}
			ExtendReport.getTest().fail(result.getThrowable());
			ExtendReport.getTest().log(Status.FAIL, "Test Failed");
			ExtendReport.getReporter().flush();
		}
	
		
	

	public void onTestStart(ITestResult result) {
		WebBaseTest base = new WebBaseTest();
		ExtendReport.startTest(result.getName(), result.getMethod().getDescription())
		.assignCategory("WebAutomation")
		.assignAuthor("Neehal");
	}

	public void onTestSuccess(ITestResult result) {
		ExtendReport.getTest().log(Status.PASS, "Test Passed");
		
	}

	public void onTestSkipped(ITestResult result) {
		ExtendReport.getTest().log(Status.SKIP, "Test Skipped");
		
	} 

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		ExtendReport.getReporter().flush();
		
	}
}
