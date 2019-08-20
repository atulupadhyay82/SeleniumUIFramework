package com.uiFramework.companyName.projectName.helper.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.browserConfig.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * After sign in page, this is page for your personal information
 * either element are clickable or text for which can verify methods
 */
public class MyAccountPage {
	
	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(MyAccountPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[text()='Welcome to your account. Here you can manage all of your personal information and orders.']")
	public WebElement yourAccountMessage;
	
	@FindBy(xpath="//*[text()='Order history and details']")
	public WebElement orderHistoryAndDetails;
	
	@FindBy(xpath="//*[text()='My personal information']")
	public WebElement myPersonalInformation;
	
	@FindBy(xpath="//*[text()='My wishlists']")
	public WebElement myWishlist;
	
	@FindBy(xpath="//*[@id='center_column']/h1")
	public WebElement myAccountText;
	
	

	public MyAccountPage(WebDriver driver) {
		this.driver=driver;
		
		/*
		 * this will initialize all the web element above at the runtime
		 * this- current class object
		 */
		PageFactory.initElements(driver, this);
		waitHelper=new WaitHelper(driver);
		waitHelper.waitForElement(orderHistoryAndDetails, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.loggedInExtentReport("MyAccountPage object created");
	}
	
	public void clickOnWishLists() {
		myWishlist.click();
		log.info("clicked on "+myWishlist.getText());
		TestBase.loggedInExtentReport("clicked on "+myWishlist.getText());
	}
	
	public void clickOrderHistoryAndDetails() {
		orderHistoryAndDetails.click();
		log.info("clicked on "+orderHistoryAndDetails.getText());
		TestBase.loggedInExtentReport("clicked on "+orderHistoryAndDetails.getText());
	}
	
	
}
