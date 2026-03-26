package com.orangehrm.actiondriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangehrm.base.BaseClass;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait; // to apply our explicit wait

	// constructor to set these private objects(driver and wait) automatically
	// class is called
	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	// Method to click an element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();

		} catch (Exception e) {

			System.out.println("unable to click element:" + e.getMessage());
		}

	}

	// Method to enter text into an input field
	public void enterText(By by, String value) {
		try {
			waitForElementToBeVisible(by);
			/*
			 * rewitten these lines using by to avoid code duplication
			 * driver.findElement(by).clear(); driver.findElement(by).sendKeys(value);
			 */
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);

		} catch (Exception e) {
			System.out.println("Unable to enter the value:" + e.getMessage());
		}
	}

	// Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			System.out.println("Unable to get the text: " + e.getMessage());
			return "";
		}
	}

	// Method to comapare text with expected text
	public boolean compareText(By by, String expectedText) {

		try {
			waitForElementToBeVisible(by);
			String actualText = driver.findElement(by).getText();

			if (expectedText.equals(actualText)) {
				System.out.println("Text are matching :" + actualText + " equals " + expectedText);
				return true;
			} else {
				System.out.println("Text are not matching :" + actualText + " not equals" + expectedText);
				return false;
			}

		} catch (Exception e) {
			System.out.println("unable to compare Texts" + e.getMessage());

		}
		return false;
	}

	// Method to check if an element is displayed

	public boolean isDisplayed(By by) {
		try {
			waitForElementToBeVisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			System.out.println("Element not displayed: " + e.getMessage());
			return false;
		}
	}

	// Wait for the page to load
	public void waitForPageLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));

			System.out.println("Page loaded successfully");
		} catch (Exception e) {
			System.out.println("Page did not load within" + timeOutInSec + "seconds. Exception: " + e.getMessage());
		}

	}

	// scroll to an element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println("Unable to locate element(scroll): " + e.getMessage());
		}
	}

	// private as used inside class only
	// Wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("element is not clickable: " + e.getMessage());
		}
	}

	// private as used inside class only
	// Wait for Element to be visible
	private void waitForElementToBeVisible(By by) {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
			System.out.println("element is not visible: " + e.getMessage());
		}

	}
}
