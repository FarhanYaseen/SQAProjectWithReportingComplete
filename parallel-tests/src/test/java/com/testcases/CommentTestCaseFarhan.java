package com.testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.baseDriver.BaseDriver;
import com.databases.DataBaseHandler;
import com.report.Report;

public class CommentTestCaseFarhan extends BaseDriver {
	String password;
	String name;
	String comment;

	Properties prop = new Properties();
	InputStream in = null;

	String path;
	String testcase = "5";
	Report report = null;

	@Test
	public void commentTestCase() {

		report = new Report(testcase);
		report.Info("Comment  Test Stareted");
		driver.get(baseUrl2);
		String signIn = "//input[@type='submit' and @value='Sign In']";
		String commentClick = "//input[@type='text' and @name='Comment']";

		try {

			in = new FileInputStream("signIn.properties");

			// load a properties file
			prop.load(in);

			// get the property value
			name = prop.getProperty("Name");
			password = prop.getProperty("Password");
			comment = prop.getProperty("Comment");

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

		driver.findElement(By.id("inputName")).sendKeys(name);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		String path = report.takeScreenShot(driver, testcase, "login");
		report.onSuccess("Login to account", path);

		driver.findElement(By.xpath(signIn)).click();
		driver.findElement(By.name("Comment")).sendKeys(comment);
		driver.findElement(By.xpath(commentClick)).submit();
		path = report.takeScreenShot(driver, testcase, "comment");
		report.onSuccess("Login to account", path);

		driver.findElement(By.linkText("Logout")).click();

		path = report.takeScreenShot(driver, testcase, "loout");
		report.onSuccess("Login to account", path);

		DataBaseHandler db = new DataBaseHandler();
		if (db.isCommentExist(name, comment)) {
			path = report.takeScreenShot(driver, testcase, "success");
			report.onSuccess("Login to account", path);
		} else {
			path = report.takeScreenShot(driver, testcase, "failure");
			report.onSuccess("Login to account", path);
		}
		path = report.tearDown();
		driver.get(path);
	}

}
