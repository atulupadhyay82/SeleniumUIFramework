package com.uiFramework.companyName.projectName.helper.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;

import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.pageObject.LoginPage;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

/*
 * It will get all the methods and variable of testbase class. No need to define afterclass(), beforeclass().
 * 
 * Will have neat and clean script. Benefit of POM
 */
public class LoginTest extends TestBase{
	
	private final Logger log= LoggerHelper.getLogger(LoginTest.class);
	
	@Test(description="Login test with valid credentials")
	public void testLoginToApplication() {
		/*
		 * Move db params in properties file from DatabaseHelper class and create getter method for them in ConfigReader class
		 */
		
		getApplicationURL(ObjectReader.reader.getURL());
		
		/*
		 * through constructor, I am going to initialize the driver object and make sure page gets
		 * loaded properly using explicit wait.
		 * in beforeTest() of testbase, we are initializing the broswer object
		 */
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApplication(ObjectReader.reader.getUserName(), ObjectReader.reader.getPassword());
		boolean status=loginPage.verifySuccessLoginMsg();
		AssertionHelper.verfiyTrue(status);
		
		
	}

}
