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

public class FeedBackTestCase extends BaseDriver {
	String name = null, email = null, message = null;
	Properties prop = new Properties();
	InputStream in = null;
	String contact = null;
    String path=null;
    Report report;
    String testcase="4";
 
	
	@Test
	public void feedbackTestCase() {

		report=new Report(testcase);
		report.Info("Feed Back Test Stareted");
		driver.get(baseUrl6);
		String submit = "//input[@type='submit' and @value='Submit']";
		// driver.findElement(By.linkText("FEEDBACK")).click();
		try {

			in = new FileInputStream("feedback.properties");

			// load a properties file
			prop.load(in);

			// get the property value
			name = prop.getProperty("name");
			email = prop.getProperty("email");
			message = prop.getProperty("message");
			contact = prop.getProperty("contact");

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
		driver.findElement(By.id("Name")).sendKeys(name);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Message")).sendKeys(message);
		driver.findElement(By.id("Contact")).sendKeys(contact);
		
		String path = report.takeScreenShot(driver,testcase, "login");
		report.onSuccess("Login to account", path);
		driver.findElement(By.xpath(submit)).click();
		
		DataBaseHandler db = new DataBaseHandler();
		if(db.isFeedBackExist(name, email, message, contact))
		{
			 path = report.takeScreenShot(driver,testcase, "success");
			report.onSuccess("Login to account", path);
		}else
		{
			 path = report.takeScreenShot(driver,testcase, "failure");
			 report.onSuccess("Login to account", path);
		}
		
		path=report.tearDown();
		driver.get(path);
	}
}
