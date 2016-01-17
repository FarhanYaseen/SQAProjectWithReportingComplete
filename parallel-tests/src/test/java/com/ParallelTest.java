package com;

import com.baseDriver.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTest extends BaseDriver {
	String fName;
	String lName;
	String uName;
	String email;
	String password;
	String confirmPassword;

	InputStream input = null;
	Properties properties = new Properties();

	@Parameters("browser")
	@Test
	public void mytest1(String browser) {
		driver.get(baseUrl0);

		try {
			input = new FileInputStream("data.properties");

			// load a properties file
			properties.load(input);

			// SignUp Content
			fName = properties.getProperty("firstName");
			lName = properties.getProperty("lastName");
			uName = properties.getProperty("userName") + "-" + browser;
			email = properties.getProperty("email");
			password = properties.getProperty("password");
			confirmPassword = properties.getProperty("password");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		System.out.println(fName + " " + lName + " " + uName + " " + email
				+ " " + password + " " + browser);

		// Sign-up here
		driver.findElement(By.linkText("Sign Up Here")).click();
		driver.findElement(By.id("firstName")).sendKeys(fName);
		driver.findElement(By.id("lastName")).sendKeys(lName);
		driver.findElement(By.id("UserName")).sendKeys(uName);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmPassword")).sendKeys(confirmPassword);

		String create = "//input[@type='submit' and @value='Create']";
		driver.findElement(By.xpath(create)).click();

	}

}
