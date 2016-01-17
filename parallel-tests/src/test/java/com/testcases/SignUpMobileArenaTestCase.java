package com.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import com.baseDriver.BaseDriver;
import com.databases.DataBaseHandler;

import com.report.Report;

public class SignUpMobileArenaTestCase extends BaseDriver {

	String fname = null, lname = null, city = null, country = null,
			email = null;
	String password = null, confirmPassword = null, DOB = null;
	Properties prop = null;
	InputStream in = null;
	Report report;
	String testcase = "2";

	String path = null;

	@Test
	public void signUpMobileArenaTestCase() {
		driver.get(baseUrl1);
		String submitButton = "//input[@type='submit' and @value='Sign Up']";

		report = new Report("2");

		report.Info("Starting testcase Signup Mobile");

		System.out.print("starting signup");

		try {

			System.out.print("starting Reading");
			in = new FileInputStream("mobileArenaSignUp.properties");
			prop = new Properties();
			// load a properties file
			prop.load(in);

			// get the property value
			fname = prop.getProperty("fname");
			lname = prop.getProperty("lname");
			email = prop.getProperty("email");
			password = prop.getProperty("password");
			confirmPassword = prop.getProperty("confirmPassword");
			country = prop.getProperty("country");
			DOB = prop.getProperty("DOB");
			city = prop.getProperty("city");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		driver.findElement(By.id("FirstName")).sendKeys(fname);
		driver.findElement(By.id("LastName")).sendKeys(lname);
		driver.findElement(By.id("UserName")).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(confirmPassword);

		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		if (cap.getBrowserName().equalsIgnoreCase("chrome"))
			driver.findElement(By.id("DateOfBirth")).sendKeys(DOB);
		else
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].value=arguments[1]",
					driver.findElement(By.id("DateOfBirth")), DOB);
		driver.findElement(By.id("City")).sendKeys(city);
		driver.findElement(By.id("Country")).sendKeys(country);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		path = report.takeScreenShot(driver, testcase, "SignUpValues");
		report.onSuccess("Added Question", path);
		driver.findElement(By.xpath(submitButton)).submit();
		DataBaseHandler db = new DataBaseHandler();
		if (db.isUserExistInMA(email, confirmPassword)) {
			path = report.takeScreenShot(driver, testcase, "Sgnuppass");
			report.onSuccess("Added Question", path);
		} else {
			path = report.takeScreenShot(driver, testcase, "SignupFail");
			report.onFailure("Added Question", path);
		}
		// login();

		path = report.tearDown();
		driver.get(path);
	}

	public void login() {

		String signIn = "//input[@type='submit' and @value='Login']";
		String logout = "//input[@type='submit' and @value='Logout']";
		driver.findElement(By.id("name")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);

		path = report.takeScreenShot(driver, testcase, "Login");
		report.onSuccess("Added Question", path);
		driver.findElement(By.xpath(signIn)).click();
		driver.findElement(By.xpath(logout)).click();
		DataBaseHandler db = new DataBaseHandler();
		boolean isvalid = db.isUserExistInMA(email, confirmPassword);

		if (isvalid) {
			path = report.takeScreenShot(driver, testcase, "Sgnuppass");
			report.onSuccess("Added Question", path);
		} else {
			path = report.takeScreenShot(driver, testcase, "SignupFail");
			report.onFailure("Added Question", path);
		}
	}

}