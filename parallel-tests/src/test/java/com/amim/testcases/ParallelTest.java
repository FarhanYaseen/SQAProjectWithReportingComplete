package com.amim.testcases;

import com.baseDriver.*;

import com.report.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import Database.DBHandlerAmim;

public class ParallelTest extends BaseDriver {

	Report report = null;

	String testcaseNo = "1";
	public String question = null;
	public String opA = null, opB = null, opC = null, opD = null,
			corectOp = null, sub = null;
	InputStream in = null;
	public String subj = null, searchName = null;
	Properties prop = new Properties();
	String path = null;

	@Test
	public void mytest1() throws InterruptedException {
		driver.get(baseUrl0);

		driver.manage().window().maximize();

		report = new Report("1");
		report.Info("Starting Adding Question Test");
		try {

			in = new FileInputStream("parameter.properties");

			// load a properties file
			prop.load(in);

			// get the property value
			question = prop.getProperty("question");
			opA = prop.getProperty("optionA");
			opB = prop.getProperty("optionB");
			opC = prop.getProperty("optionC");
			opD = prop.getProperty("optionD");
			sub = prop.getProperty("subject");
			subj = prop.getProperty("sn");
			corectOp = prop.getProperty("optionCorrect");
			searchName = prop.getProperty("sname");

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

		driver.findElement(By.name("username")).sendKeys("jsonukhan");
		driver.findElement(By.name("password")).sendKeys("123");

		String path = report.takeScreenShot(driver, testcaseNo, "login");
		report.onSuccess("Login to account", path);

		driver.findElement(By.name("signin")).click();

		driver.findElement(By.linkText("SEARCH STUDENT")).click();
		SearchName(searchName);
		path = report.takeScreenShot(driver, testcaseNo, "SaarchStudent");
		report.onSuccess("Search By Name ", path);

		Thread.sleep(1000);

		path = report.takeScreenShot(driver, testcaseNo, "SaarchStudentName");
		report.onSuccess("Search By Name ", path);

		boolean isvalid = AddQustion();
		driver.findElement(By.name("signout")).click();

		if (isvalid) {
			path = report.takeScreenShot(driver, testcaseNo,
					"AddesQuestionPass");
			report.onSuccess("Added Question", path);

		} else {

			path = report.takeScreenShot(driver, testcaseNo,
					"AddesQuestionFail");
			report.onSuccess("Added Question", path);
		}

		path = report.tearDown();
		driver.get(path);

	}

	public void SearchName(String s) throws InterruptedException {
		Thread.sleep(5000);
		// driver.findElement(By.cssSelector("a[href=/Admin/EditQuestion/1]")).click();
		driver.findElement(By.id("search")).sendKeys(s);

		driver.findElement(By.id("click")).click();

	}

	public Boolean AddQustion() throws InterruptedException {
		driver.findElement(By.linkText("ADD QUESTION")).click();

		driver.findElement(By.name("question")).sendKeys(question);
		driver.findElement(By.name("optionA")).sendKeys(opA);
		driver.findElement(By.name("optionB")).sendKeys(opB);
		driver.findElement(By.name("optionC")).sendKeys(opC);
		driver.findElement(By.name("optionD")).sendKeys(opD);
		driver.findElement(By.name("correctOption")).sendKeys(corectOp);

		driver.findElement(By.name("subject")).sendKeys(sub);

		String path = report
				.takeScreenShot(driver, testcaseNo, "AddesQuestion");
		report.onSuccess("Added Question", path);

		driver.findElement(By.name("add")).click();
		Thread.sleep(3000);

		return AddQustionDBTest();
	}

	public Boolean AddQustionDBTest() {
		Boolean valid = false;
		DBHandlerAmim obj = new DBHandlerAmim();
		ResultSet rs = null;
		Statement stmt = null;
		Connection conn = null;
		try {
			conn = obj.ExamPortaralConnection();
			stmt = conn.createStatement();
			String strSelect = "select * from QuestionBank "
					+ "where question = '" + question
					+ "' and correctOption = '" + corectOp
					+ "' and subject = '" + sub + "';";
			rs = stmt.executeQuery(strSelect);
			if (rs != null) {
				System.out.println("In database.....Test Passed");
				valid = true;
			} else {
				System.out.println("not in Database.....Test Failed");
				valid = false;
			}

			conn.close();
			rs.close();
			obj.ExamPortaralConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return valid;
	}

}
