package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass {

	// protected so we can use these object withing the pakage and also outside the
	// pakage
	protected static Properties prop; // making prop static so we can run multiple classes at once, not necessary to
										// make driver static because we are quiting it every time
	protected WebDriver driver;

	// Step 1:Load the configruation file it runs only once so applied BeforeSuite
	@BeforeSuite
	public void loadConfig() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		prop.load(fis);

	}

	@BeforeMethod
	public void setup() throws IOException {

		System.out.println("Setting up WebDriver for:" + this.getClass().getSimpleName()); // prints for which test
																							// class its executing
		launchBrowser();
		configureBrowser();
		staticWait(5);

	}

	// Step 2: Initialize the WebDriver based on browser defined in
	// config.properties file
	private void launchBrowser() {

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Browser not supported:" + browser);
		}
	}

	// Configure browser settings such as implicit wait, maximize the browser and
	// navigate to the url

	private void configureBrowser() {
		// implicit wait
		int implicitwait = Integer.parseInt(prop.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitwait));

		// Step 3:Navigate to URL
		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to Navigate to the URL:" + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				System.out.println("Failed to quit browser:" + e.getMessage());
			}
		}
	}

	// setting getter and setter methods to access protected objects outside package
	// Driver getter method
	public WebDriver getDriver() {
		return driver;
	}

	// Driver setter method
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	// adding some static wait so we can actually see title on page before closing
	public void staticWait(int seconds) {

		// thread.sleep adds wait in miliseconds but parkNanos adds wait in nanos
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}
}
