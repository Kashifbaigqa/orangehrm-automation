package com.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangehrm.actiondriver.ActionDriver;
import com.orangehrm.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	// Initialize the ActionDriver object by passing WebDriver instance
	public HomePage(WebDriver driver) {
//		this.actionDriver = new ActionDriver(driver);  
		this.actionDriver = BaseClass.getActionDriver(); // not creating new instace now just getting from BaseClass
	}

	// Define locators using By class
	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userIDButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMlogo = By.xpath("//img[@alt = 'client brand banner']");

	// Method to verify if Admin tab is visible
	public boolean isAdminTabVisible() {

		return actionDriver.isDisplayed(adminTab);
	}

	public boolean verifyOrangeHRMlogo() {
		return actionDriver.isDisplayed(orangeHRMlogo);
	}

	// Method to perform logout operation
	public void logout() {
		actionDriver.click(userIDButton);
		actionDriver.click(logoutButton);
	}
}
