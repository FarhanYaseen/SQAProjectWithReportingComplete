package com.baseDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseDriver {
	protected WebDriver driver = null;
	protected String baseUrl0 = null;
	protected String baseUrl1 = null;
	protected String baseUrl2 = null;
	protected String baseUrl3 = null;
	protected String baseUrl4 = null;
	protected String baseUrl5 = null;
	protected String baseUrl6 = null;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			System.out.println("Firefox Running..");
			driver = new FirefoxDriver();
		}

		if (browser.equalsIgnoreCase("chrome")) {
			System.out.println("Chrome Running..");
			System.setProperty("webdriver.chrome.driver",
					"resources/chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (browser.equalsIgnoreCase("InternetExplorer")) {
			System.out.println("IExplorer Running..");
			System.setProperty("webdriver.ie.driver",
					"resources/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		if (browser.equalsIgnoreCase("safari")) {
			System.out.println("Safari Running..");
			System.setProperty("webdriver.safari.driver",
					"resources/SafariDriver.safariextension");
			driver = new SafariDriver();
		}

		baseUrl0 = "http://bitf12m002.apphb.com/";
		baseUrl1 = "https://bitf12m036mobilearena.apphb.com/Home/SignUp";
		baseUrl5 = "https://bitf12m036mobilearena.apphb.com/Home/Login";
		baseUrl3 = "http://bitf12m036mobilearena.apphb.com/";
		baseUrl6 = "http://bitf12m036mobilearena.apphb.com/Home/Feedback";
		baseUrl2 = "http://farhanyaseensocialpedia.apphb.com/Home/SignIn";
		baseUrl4 = "http://bitf12m051.apphb.com/Home/SignUp";

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}

}
