package com.lenskart.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIActions {

	public static void waitForElementVisibility(WebDriver driver, WebElement element, long timeoutInSeconds) {

		try {
			new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			System.out.println(element + " not visible..");
		}
	}

	public static boolean isElementPresent(WebDriver driver, WebElement element) {
		boolean isPresent = false;
		try {
			return isPresent = element.isDisplayed();
		} catch (NoSuchElementException e) {
			return isPresent;
		}
	}

	public static void waitTillElementIsClickable(WebDriver driver, WebElement element, long timeoutInSeconds) {
		try {
			new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			System.out.println(element + " not visible..");
		}

	}

	public static void mouseHower(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}

	@SuppressWarnings("deprecation")
	public static void pageloaded(WebDriver driver) {
		new WebDriverWait(driver, 20).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}

}
