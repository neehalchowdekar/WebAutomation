package com.vsm;

import java.io.IOException;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.lenskart.utils.UIActions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VSMCancellation {
	private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return webDriver.get();
	}

	public static void setDriver(WebDriver driver) {
		webDriver.set(driver);
	}

	@Test(dataProvider = "emailOrPno", threadPoolSize = 2)
	public void vsmCanelation(String emailOrPhone) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("disable-gpu");
		setDriver(new ChromeDriver(chromeOptions));
		getDriver().get("http://vsm.lenskart.com/");
		System.out.println(getDriver().getCurrentUrl());
		getDriver().manage().window().maximize();

		getDriver().findElement(By.id("username")).sendKeys("asmaa@valyoo.in");
		getDriver().findElement(By.id("password")).sendKeys("1234");
		Thread.sleep(2000);
		getDriver().findElement(By.xpath("//*[@id='login']/form/div/dl[5]/dd/input")).click();

		UIActions.waitTillElementIsClickable(getDriver(), getDriver().findElement(By.partialLinkText("CS")), 20);
		UIActions.mouseHower(getDriver(), getDriver().findElement(By.partialLinkText("CS")));

		UIActions.waitForElementVisibility(getDriver(),
				getDriver().findElement(By.cssSelector("[title='View Orders']")), 20);
		getDriver().findElement(By.cssSelector("[title='View Orders']")).click();
		UIActions.pageloaded(getDriver());
		getDriver().findElement(By.xpath(".//*[@id='oldSearch']")).click();
		UIActions.pageloaded(getDriver());

		// String emailIdOrPhoneNumber = "testing_test_test@gmail.com";

		String regex = "(0/91)?[1-9][0-9]{9}";

		if (emailOrPhone.matches(regex)) {
			getDriver().findElement(By.xpath("//*[@id='phone']")).sendKeys(emailOrPhone);
		} else {
			getDriver().findElement(By.xpath(".//*[@id='email']")).sendKeys(emailOrPhone);
		}

		getDriver().findElement(By.xpath("//*[@id='searchForm']//*[@id='Validate']")).click();
		UIActions.pageloaded(getDriver());

		By noResultXpath = By.xpath("//*[contains(text(), 'No results')]");
		boolean noResult = false;

		try {
			noResult = UIActions.isElementPresent(getDriver(), getDriver().findElement(noResultXpath));
		} catch (Exception e) {
		}
		if (!noResult) {

			int year = YearMonth.now().getYear();

			boolean orderListYear = getDriver().findElement(By.xpath("//*[@id='rounded-corner']/tbody/tr[1]/td[2]"))
					.getText().contains(String.valueOf(year));

			if (orderListYear) {
				By element = By.xpath("//*[@id='TABLE_1']/tbody/tr/td[1]");
				String text = getDriver().findElement(element).getText();
				int index = text.indexOf("of");
				text = text.substring(index + 2, text.length()).trim();
				text = text.substring(0, text.indexOf(" ")).trim();
				Integer totalOrders = Integer.parseInt(text);
				String PageSize;
				if (totalOrders > 300) {
					PageSize = "300";
				} else {
					PageSize = text.trim();
				}
				int pageSize = Integer.parseInt(PageSize);
				By by = By.cssSelector("#pageSize");
				getDriver().findElement(by).clear();
				By by1 = By.cssSelector("#pageSize");
				getDriver().findElement(by1).sendKeys(PageSize);
				getDriver().findElement(by1).sendKeys(Keys.ENTER);
				for (int j = 1; j <= pageSize; j++) {
					By statusxpath = By.xpath("//*[@id='rounded-corner']/tbody/tr[" + j + "]/td[6]");
					By orderIdXpath = By.xpath("//*[@id='rounded-corner']/tbody/tr[" + j + "]/td[1]/a");
					By orderYearXapth = By.xpath("//*[@id='rounded-corner']/tbody/tr[" + j + "]/td[2]");
					String status = getDriver().findElement(statusxpath).getText();
					String orderId = getDriver().findElement(orderIdXpath).getText();
					String orderYear = getDriver().findElement(orderYearXapth).getText();
					String lowerCase = status.toLowerCase();
					List<String> status1 = Arrays.asList("processing", "processing_new", "processing_blacklist",
							"pending", "processing_power_followup");
					if (status1.contains(lowerCase)) {
						int previousYear = year - 1;
						if (orderYear.contains(String.valueOf(previousYear))) {
							System.out.println("old orders");
							break;
						}
						getDriver().findElement(orderIdXpath).click();
						System.out.println("its clicking " + orderId);
						UIActions.pageloaded(getDriver());

						By notSyncXpath = By.xpath(
								"//div[contains(text(),'Order is not Sync')]|//div[contains(text(),'Order is not Synced')]");
						boolean notSyncFlag = false;
						try {
							notSyncFlag = UIActions.isElementPresent(getDriver(),
									getDriver().findElement(notSyncXpath));
						} catch (Exception e) {}
						if (notSyncFlag) {
							System.out.println("Order not synced - " + orderId);
						} else {
							By clientXpath = By.xpath("//span[@style='color:green;font-size:20px;']");
							boolean client = getDriver().findElement(clientXpath).getText()
									.equalsIgnoreCase("POS Web App");

							By orderCancelButton = By.xpath("//*[@id='orderCancelSpan']/span/span[2]");
							boolean booleanResult = false;

							try {
								booleanResult = UIActions.isElementPresent(getDriver(),
										getDriver().findElement(orderCancelButton));
							} catch (Exception e) {}
							System.out.println(booleanResult + "  -------");
							if (!client && booleanResult) {
								getDriver().findElement(orderCancelButton).click();
								Thread.sleep(500);
								By cancelCommnetTextBox = By
										.xpath("//*[@id='cancelcommenttextbox']/option[. = 'Test order']");
								getDriver().findElement(cancelCommnetTextBox).click();
								try {
									By SC_REFUND_METHOD = By.xpath(
											"//*[@id='refundMethodonline']/option[. = 'STORECREDIT']|//*[@id='refundMethodoffline']/option[. = 'STORECREDIT']|//*[@id='refundMethodOld']/option[. = 'STORECREDIT']");
									getDriver().findElement(SC_REFUND_METHOD).click();
								} catch (Exception e) {}
								By orderCancelSubmitButton = By.xpath("//*[@id='ordercancelbutton']");
								getDriver().findElement(orderCancelSubmitButton).click();
							} else {
								System.out.println("Bulk order/order already cancelled - " + orderId);
							}

						}

						getDriver().navigate().back();
					}
				}
				By logout = By.xpath("//*[text() = 'Logout']");
				getDriver().findElement(logout).click();
			} else {
				System.out.println("No 2021 orders.. hence logging out");
				By by = By.xpath("//*[text() = 'Logout']");
				getDriver().findElement(by).click();
			}
		} else {
			By logout = By.xpath("//*[text() = 'Logout']");
			getDriver().findElement(logout).click();
		}
		getDriver().close();
	}

	@AfterSuite
	public void quitDriver() {
		try {
			if (getDriver() != null) {
				getDriver().quit();
				setDriver(null);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
	}

	@DataProvider(name = "emailOrPno", parallel = true)
	public static Iterator<String[]> packageOrderData() throws IOException {
		return com.lenskart.utils.TestUtils.getDataFromCSV("/src/test/resources/emailOrPhoneNo.csv");
	}

}
