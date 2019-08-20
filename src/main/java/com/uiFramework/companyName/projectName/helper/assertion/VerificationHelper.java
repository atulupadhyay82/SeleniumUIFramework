package com.uiFramework.companyName.projectName.helper.assertion;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

/**
 * Very important class in the framework. When we write test script, we do write
 * a lot of verification/assertion. It is different from AssertionHelper 
 * here we are returning true of false or the string value of element 
 * 
 * @author atupadhy
 *
 */
public class VerificationHelper {

	// define logger class object
	private Logger log = LoggerHelper.getLogger(VerificationHelper.class);

	// Define WebDriver class object
	private WebDriver driver;

	// Calling class will supply WebDriver class object to this via the constructor.
	// Hence object defined private.
	public VerificationHelper(WebDriver driver) {
		this.driver = driver;
	}
	/**
	 * Whether or not the element is displayed
	 * @param element
	 * @return
	 */
	public boolean isDisplayed(WebElement element)
	{
		
		try{
			element.isDisplayed();
			log.info("element is displayed: "+element.toString());
			TestBase.loggedInExtentReport("element is displayed: "+element.toString());
			return true;
		}
		catch(Exception e)
		{
			log.error("element is not present: "+e.getCause());
			TestBase.loggedInExtentReport("element is not present: "+e.getCause());
			return false;
		}
	}
	/**
	 * Wants to read the data from the element
	 * @param element
	 * @return
	 */
	public String getText(WebElement element)
	{
		if(element == null) {
			log.info("element is null..");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status) {
			log.info("element text is.."+element.getText());
			return element.getText();
		}
		else
			return null;
	}
	
	public String getTitle() {
		log.info("the title of the page is: "+driver.getTitle());
		return driver.getTitle();
	}
	
	
}
