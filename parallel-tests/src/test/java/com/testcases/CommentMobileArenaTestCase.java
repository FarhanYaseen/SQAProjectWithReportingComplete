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

public class CommentMobileArenaTestCase extends BaseDriver {

	Properties prop = new Properties();
	InputStream in = null;
	
	String name;
	String password;
	String comment ;
	String searchMobile;

	String testcase="3";
	Report report=null;
	
	@Test
	public void commentMobileArenaTestCase() {
		driver.get(baseUrl5);
		report=new Report(testcase);
		report.Info("Starting comment Mobile TestCase");
		String signIn = "//input[@type='submit' and @value='Login']";
		String commentBar = "//input[@type='text' and @id='name']";
		String searchBar = "//input[@type='text' and @name='mobileName']";
		
		try {

			in = new FileInputStream("mobileArenaSignUp.properties");

			// load a properties file
			prop.load(in);

			// get the property value
			name = prop.getProperty("email");
			password = prop.getProperty("password");
			comment  = prop.getProperty("comment");
			searchMobile  = prop.getProperty("searchMobile");

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

		driver.findElement(By.id("name")).sendKeys(name);
		driver.findElement(By.id("pass")).sendKeys(password);
		String path = report.takeScreenShot(driver,testcase, "login"+testcase);
		report.onSuccess("Login to account", path);

		driver.findElement(By.xpath(signIn)).click();
		// driver.findElement(By.name("mobileName")).sendKeys("s4");

		driver.findElement(By.xpath(searchBar)).sendKeys(searchMobile);
		driver.findElement(By.xpath(searchBar)).submit();

		driver.findElement(By.xpath(commentBar)).sendKeys(comment);
		driver.findElement(By.xpath(commentBar)).submit();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    path = report.takeScreenShot(driver,testcase, "comment"+testcase);
		report.onSuccess("Login to account", path);


		DataBaseHandler db = new DataBaseHandler();
	if(	db.isCommentInMAExist(name, comment))
	{
		report.onSuccess("Comment successfully", path);
	}else
	{
		report.onFailure("Comment successfully", path);
	}
		
	path=report.tearDown();
	driver.get(path);
	}
}
