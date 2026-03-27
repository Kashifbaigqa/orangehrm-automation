package com.orangehrm.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.BaseClass;
import com.orangehrm.pages.HomePage;
import com.orangehrm.pages.LoginPage;

public class NewLoginPageTest extends BaseClass {

	// Initialize objects of the Page classes
	private LoginPage loginPage;
	private HomePage homePage;

	@BeforeMethod // to getDriver and initialize page objects before running TC
	public void setupPages() {
		loginPage = new LoginPage(getDriver()); // class extends BaseClass so it can access driver
		homePage = new HomePage(getDriver());
	}

	@Test
	public void verifyValidLoginTest() {

		loginPage.login("admin", "admin123");
		Assert.assertTrue(homePage.isAdminTabVisible(), "Admin tab should be visible after successfull login");
		homePage.logout();
		staticWait(2); // we can use this method from BaseClass directly becasue we have extended the
						// base class here
	}

	@Test
	public void inValidLoginTest() {

		loginPage.login("admin", "admin");
		String exectedErrorMessage = "Invalid credentials";
		Assert.assertTrue(loginPage.verifyErrorMessage(exectedErrorMessage), "Test Failed: Invalid Error message");
	}

}
