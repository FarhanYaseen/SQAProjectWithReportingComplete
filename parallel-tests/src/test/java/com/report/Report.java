package com.report;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	ExtentReports report = null;
	ExtentTest test = null;

	String reportDest = null;

	public Report(String testcase) {
		reportDest = "D:\\Reports\\testcase" + testcase + ".html";
		report = new ExtentReports(reportDest);

		test = report.startTest("SQA Project Report-Test Case # " + testcase);
		report.config().documentTitle("SQA Report ");
		report.config().reportName("my first report");

	}

	public String takeScreenShot(WebDriver driver, String testcase, String name) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		String dest = null;
		File file = ts.getScreenshotAs(OutputType.FILE);
		try {
			dest = "D:\\Reports\\" + testcase + "\\" + name + ".png";
			FileUtils.copyFile(file, new File(dest));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dest;
	}

	public void Info(String info) {
		test.log(LogStatus.INFO, info);

	}

	public void onSuccess(String info, String path) {
		String abc = test.addScreenCapture(path);
		test.log(LogStatus.PASS, info, abc);
	}

	public void onFailure(String info, String path) {
		String abc = test.addScreenCapture(path);
		test.log(LogStatus.FAIL, info, abc);
	}

	public String tearDown() {
		report.endTest(test);
		report.flush();

		return reportDest;
	}

}
