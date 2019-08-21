package com.uiFramework.companyName.projectName.helper.testScripts;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;
import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.pageObject.LoginPage;
import com.uiFramework.companyName.projectName.helper.pageObject.NavigationMenu;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

/**
 * How to design the scripts for data driven testcase like reading the data from external source
 * I have defined one method getExcelData() in testbase which will read the data from excel file and give me the data in 2D array
 * Testing sign-In page for multiple users with different emailId and password
 * In data driven testscripts, emailable report is more helpful than extent report as it gives for which parameters you test get passed/failed/skipped
 * @author atupadhy
 *
 */

public class LoginTestWithDataDrivenApproach extends TestBase{

	private Logger log = LoggerHelper.getLogger(LoginTestWithDataDrivenApproach.class);
	
	LoginPage loginPage;
	NavigationMenu navMenu;
	
	/**
	 * Mark a method as supplying data for a test method. The data provider name defaults to method name. The annotated method must return an Object[][]
	 * where each Object[] can be assigned the parameter list of the test method.  .
	 * @return
	 */
	
	@DataProvider(name="testData")
	public Object[][] testdata(){
		Object[][] data= getExcelData("testData.xlsx", "loginData");
		return data;
	}
	
	/**
	 * The @Test method that wants to receive data from above DataProvider, then it should use a dataProvider name equals to the name of this annotation.
	 * Also param in methods should match the columns in the excel file otherwise it will throws an error. Here dataprovider had 3 columns. 1st was blank, so get ignored.
	 * This will run as many time as the rows in the excel file. 
	 * @param username
	 * @param password
	 * @param runMode
	 */
	
	@Test(dataProvider="testData")
	public void testLoginPage(String username, String password, String runMode) {
		if(runMode.equalsIgnoreCase("N")) {
			throw new SkipException("Run mode for this this set of data is marked N");
		}
		/*
		 * it will the login using email and password provided in the testData file. Also the record for which runMode is marked N in the excel file, those will get skipped. Here
		 * we are throwing testNG exception for them.
		 * Here i need to open the browser with the application URL, calling getApplicationURL() will launch the browser as many time as the no of rows in this data array.This problem is get solvve
		 * by write beforeClass() method for this which will launch the browser.
		 */
		log.info("Username is :"+ username+" and password is: "+password);
		loginPage.loginToApplication(username, password);
		
		/*
		 * to verify if we have succesfully loggedin
		 */
		AssertionHelper.verfiyTrue(loginPage.verifySuccessLoginMsg());
		
		loginPage.logout(username);
	
	}
	
	@BeforeClass
	public void beforeClass() {
		getApplicationURL(ObjectReader.reader.getURL());
		loginPage= new LoginPage(driver);
		
	}
}
