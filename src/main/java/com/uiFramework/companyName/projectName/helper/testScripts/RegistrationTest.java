package com.uiFramework.companyName.projectName.helper.testScripts;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;
import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.pageObject.LoginPage;
import com.uiFramework.companyName.projectName.helper.pageObject.MyAccountPage;
import com.uiFramework.companyName.projectName.helper.pageObject.RegistrationPage;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

public class RegistrationTest extends TestBase {

	private final Logger log = LoggerHelper.getLogger(RegistrationTest.class);
	LoginPage loginPage;
	RegistrationPage registrationPage;

	@Test
	public void testToRegisterNewUser() {

		// Go to the application
		getApplicationURL(ObjectReader.reader.getURL());

		// open the sign page
		loginPage = new LoginPage(driver);
		loginPage.clickOnSignInLink();

		// enter runtime generated email there
		loginPage.enterRegisterationOnEmail();

		// click on create account
		loginPage.clickOnCreateAccount();

		registrationPage = new RegistrationPage(driver);
		registrationPage.setMrRadioButton();
		registrationPage.setFirstName("firstName");
		registrationPage.setLastName("lastName");
		registrationPage.setPassword("password");
		
		registrationPage.setDay("5");
		registrationPage.setMonth("March");
		registrationPage.setYear("2017");

		registrationPage.setAddress1("address1");
		registrationPage.setAddress2("address2");
		registrationPage.setCity("city");
		registrationPage.setState("Alaska");
		registrationPage.setPincode("99501");
		registrationPage.setPhoneNo("9999999999");
		registrationPage.setAliasAddress("aliasAddress");

		registrationPage.clickOnRegister();

		MyAccountPage myAccountPage = new MyAccountPage(driver);
		boolean status = myAccountPage.isYourAccountPageMessage();
		AssertionHelper.verfiyTrue(status);

	}

}
