package com.uiFramework.companyName.projectName.helper.pageObject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.javaScript.JavaScriptHelper;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * http://automationpractice.com/index.php?controller=authentication&back=my-account#account-creation
 * Want to register new users.
 */
public class RegistrationPage {
	
	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(RegistrationPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='id_gender1']")
	public WebElement mrRadioButton;
	
	@FindBy(xpath="//*[@id='id_gender2']")
	public WebElement mrsRadioButton;
	
	@FindBy(xpath="//*[@id='customer_firstname']")
	public WebElement firstName;
	
	@FindBy(xpath="//*[@id='customer_lastname']")
	public WebElement lastName;
	
	@FindBy(xpath="//*[@id='email']")
	public WebElement emailAddress;
	
	@FindBy(xpath="//*[@id='passwd']")
	public WebElement password;
	
	@FindBy(xpath="//*[@id='days']")
	public WebElement daysInDOB;
	
	@FindBy(xpath="//*[@id='months']")
	public WebElement monthInDOB;
	
	@FindBy(xpath="//*[@id='years']")
	public WebElement yearsInDOB;
	
	@FindBy(xpath="//*[@id='address1']")
	public WebElement address1;
	
	@FindBy(xpath="//*[@id='address2']")
	public WebElement address2;
	
	@FindBy(xpath="//*[@id='city']")
	public WebElement city;
	
	@FindBy(xpath="//*[@id='id_state']")
	public WebElement state;
	
	@FindBy(xpath="//*[@id='postcode']")
	public WebElement pincode;
		
	
	@FindBy(xpath="//*[@id='id_country']")
	public WebElement country;
	
	@FindBy(xpath="//*[@id='phone_mobile']")
	public WebElement phoneNo;
	
	@FindBy(xpath="//*[@id='alias']")
	public WebElement aliasAddress;
	
	@FindBy(xpath="//*[@id='submitAccount']")
	public WebElement register;
	
	public RegistrationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		waitHelper=new WaitHelper(driver);
		waitHelper.waitForElement(mrRadioButton, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.loggedInExtentReport("RegistrationPage class object is created");
		
	}
	
	public void setMrsRadioButton() {
		log.info("electing mrs checkbox");
		TestBase.loggedInExtentReport("selecting mrs checkbox");
		mrsRadioButton.click();
	}

	public void setFirstName(String firstName) {
		log.info("entering the firstname: "+firstName);
		TestBase.loggedInExtentReport("entering the firstname: "+firstName);
		this.firstName.sendKeys(firstName);
		
	}

	public void setLastName(String lastName) {
		log.info("entering the lastName: "+lastName);
		TestBase.loggedInExtentReport("entering the lastName: "+lastName);
		this.lastName.sendKeys(lastName);
	}

	public void setEmailAddress(String emailAddress) {
		log.info("entering the email address: "+emailAddress);
		TestBase.loggedInExtentReport("entering the email address: "+emailAddress);
		this.emailAddress.sendKeys(emailAddress);
	}

	public void setPassword(String password) {
		log.info("entering the password: "+password );
		TestBase.loggedInExtentReport("entering the password: "+password);
		this.password.sendKeys(password);
	}

	public void setAddress1(String address1) {
		log.info("entering the address1: "+address1);
		TestBase.loggedInExtentReport("entering the address1: "+address1);
		this.address1.sendKeys(address1);
	}

	public void setAddress2(String address2) {
		log.info("entering the address2: "+address2);
		TestBase.loggedInExtentReport("entering the address2: "+address2);
		this.address2.sendKeys(address2);
	}

	public void setCity(String city) {
		log.info("entering the city: "+city);
		TestBase.loggedInExtentReport("entering the city: "+city);
		this.city.sendKeys(city);
	}

	public void setState(String state) {
		log.info("entering the state: "+state);
		TestBase.loggedInExtentReport("entering the state: "+ state);
		this.state.sendKeys(state);
	}

	public void setPincode(String pincode) {
		log.info("entering the pincode: "+ pincode);
		TestBase.loggedInExtentReport("entering the pincode: "+ pincode);
		this.pincode.sendKeys(pincode);
	}

	public void setCountry(String country) {
		log.info("entering the country: "+country);
		TestBase.loggedInExtentReport("entering the country: "+country);
		this.country.sendKeys(country);
	}

	public void setPhoneNo(String phoneNo) {
		log.info("entering the phoneNo: "+phoneNo);
		TestBase.loggedInExtentReport("entering the phoneNo: "+phoneNo);
		this.phoneNo.sendKeys(phoneNo);
	}

	public void setAliasAddress(String aliasAddress) {
		log.info("entering the aliasAddress: "+aliasAddress);
		TestBase.loggedInExtentReport("entering the aliasAddress: "+aliasAddress);
		this.aliasAddress.sendKeys(aliasAddress);
	}

	public void clickOnRegister() {
		log.info("Clicking on register button...");
		TestBase.loggedInExtentReport("Clicking on register button...");
		this.register.click();
	}

	public void setMrRadioButton() {
		log.info("selecting mr checkbox");
		TestBase.loggedInExtentReport("selecting mr checkbox");
		mrRadioButton.click();
		}

	/*
	 * to select the date in the dropdown based on param, first we will find that option tag. Now it will consist of 31 elements. Iterate over it and compare with the param date
	 */
	public void setDay(String date) {
		
		List<WebElement> days = driver.findElements(By.xpath("//*[@id='days']/option"));
		Iterator<WebElement> iterator = days.iterator();
		
		while(iterator.hasNext()) {
			WebElement day=iterator.next();
			String text= day.getText().trim().toString();
			if(text.equalsIgnoreCase(date)){
				day.click();
				log.info("date is selected now...");
				
			}
		}
	}
	
	public void setMonth(String month) {
		List<WebElement> days = driver.findElements(By.xpath("//*[@id='months']/option"));
		Iterator<WebElement> iterator = days.iterator();
		
		while(iterator.hasNext()) {
			WebElement day=iterator.next();
			String text= day.getText().trim().toString();
			if(text.equalsIgnoreCase(month)){
				day.click();
				log.info("month is selected now...");
				
			}
		}
	}
	
	public void setYear(String year) {
		List<WebElement> days = driver.findElements(By.xpath("//*[@id='years']/option"));
		Iterator<WebElement> iterator = days.iterator();
		
		while(iterator.hasNext()) {
			WebElement day=iterator.next();
			String text= day.getText().trim().toString();
			if(text.equalsIgnoreCase(year)){
				day.click();
				log.info("date is selected now...");
				
			}
		}
	}
	

}
