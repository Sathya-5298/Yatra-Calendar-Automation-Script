package com.yatra.automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YatraAutomationScript {

	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");

		WebDriver driver = new ChromeDriver(chromeOptions); // Launch the Browser
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(11L)); //Synchronizing the web driver

		driver.get("https://www.yatra.com/");

		driver.manage().window().maximize();

		closePopup(wait);

		By departureDateLocator = By.xpath("//div[@aria-label='Departure Date inputbox' and @role='button']");
		WebElement departureDate = wait.until(ExpectedConditions.elementToBeClickable(departureDateLocator));
		departureDate.click();

		WebElement currentMonthElement = selectCalendarMonth(wait, 0);
		WebElement nextMonthElement = selectCalendarMonth(wait, 1);

		Thread.sleep(2500);

		String lowestPriceforCurrentMonth = getMeLowestPrice(currentMonthElement);
		String lowestPriceforNextMonth = getMeLowestPrice(nextMonthElement);

		System.out.println(lowestPriceforCurrentMonth);
		System.out.println(lowestPriceforNextMonth);

		compareTwoMonthsPrices(lowestPriceforCurrentMonth, lowestPriceforNextMonth);

	}

	private static void closePopup(WebDriverWait wait) {
		By crossMarkLocator = By.xpath("//span[@class='style_cross__q1ZoV']/img[@alt='cross']");

		try {

			WebElement crossMark = wait.until(ExpectedConditions.visibilityOfElementLocated(crossMarkLocator));
			crossMark.click();
		} catch (TimeoutException e) {
			System.out.println("Pop Up is not shown in the Web Page");
		}
	}

	private static String getMeLowestPrice(WebElement monthElement) {
		By priceLocator = By.xpath(".//span[contains(@class, 'custom-day-content')]");
		List<WebElement> currentMonthPriceList = monthElement.findElements(priceLocator);

		int lowestPrice = Integer.MAX_VALUE;
		WebElement priceElement = null;

		for (WebElement price : currentMonthPriceList) {

			String priceValue = price.getText();
			if (!priceValue.isEmpty()) {
				priceValue = priceValue.replace("₹", "").replace(",", "");

				int priceInt = Integer.parseInt(priceValue);

				if (priceInt < lowestPrice) {
					lowestPrice = priceInt;
					priceElement = price;
				}
			}
		}

		WebElement dataElement = priceElement.findElement(By.xpath(".//../.."));
		String result = dataElement.getAttribute("aria-label") + "Price is Rs" + lowestPrice;
		return result;
	}

	public static WebElement selectCalendarMonth(WebDriverWait wait, int index) {
		By calendarMonthsLocator = By.xpath("//div[@class='react-datepicker__month-container']");
		List<WebElement> calendarMonthsList = wait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarMonthsLocator));
		System.out.println(calendarMonthsList.size());
		WebElement MonthCalendarElement = calendarMonthsList.get(index);
		return MonthCalendarElement;
	}

	public static void compareTwoMonthsPrices(String currentMonthPrice, String nextMonthPrice) {
		int currentMonthRsIndex = currentMonthPrice.indexOf("Rs");
		int nextMonthRsIndex = nextMonthPrice.indexOf("Rs");

		String currentPrice = currentMonthPrice.substring(currentMonthRsIndex + 2);
		String nextPrice = nextMonthPrice.substring(nextMonthRsIndex + 2);

		int current = Integer.parseInt(currentPrice);
		int next = Integer.parseInt(nextPrice);

		if (current < next) {
			System.out.println("Lowest price in two months is in current month Rs" + current);
		} else if (current == next) {
			System.out.println("Price is same for both months");
		} else {
			System.out.println("Lowest price in two months is in next month Rs" + next);
		}
	}
}
