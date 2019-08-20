package com.uiFramework.companyName.projectName.helper.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.assertion.VerificationHelper;
import com.uiFramework.companyName.projectName.helper.browserConfig.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.javaScript.JavaScriptHelper;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * Designed login class based on the login page of http://automationpractice.com/index.php?controller=authentication.
 * FindBy-Used to mark a field on a Page Object to indicate an alternative mechanism for locating the element or a list of elements. 
 * You can either use this annotation by specifying both "how" and "using" or by specifying one of the location strategies (eg: "id") with an appropriate value to use
 * this is a very high level design but in real, you have to create variable for each webelement that can be seen on the login page
 */
public class LoginPage {
	
	/*
	 * calling script will initialize the driver here at the runtime
	 */
	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(LoginPage.class);
	
	WaitHelper waitHelper;
	
	
	@FindBy(xpath="//*[@id=\"header\"]/div[2]/div/div/nav/div[1]/a")
	WebElement signIn;
	
	@FindBy(xpath="//*[@id='email']")
	WebElement emailAddress;
	
	@FindBy(xpath="//*[@id='passwd']")
	WebElement password;
	
	@FindBy(xpath="//*[@title='Recover your forgotten password']")
	WebElement forgetPasswordLink;
	
	//@FindBy(xpath="//*[@id='SubmitLogin']") -both will give same element
	@FindBy(how=How.ID, using="SubmitLogin")
	WebElement submitLogin;
	
	@FindBy(xpath="//*[@id='center_column']/p")
	WebElement succesMsgObject;
	
	@FindBy(xpath="//*[@id='email_create']")
	WebElement registerationEmailAddress;
	
	@FindBy(xpath="//*[@id='SubmitCreate']")
	WebElement createAccount;
	
	@FindBy(xpath="//*[@id='center_column']/h1")
	WebElement authenticationText;
	
	@FindBy(xpath="//*[@id='create-account_form']/div/p")
	WebElement createAnAccountText;
	
	/*
	 * Calling script page will call this pageoject class via his constructor
	 * it will make sure your page gets load and all your web elements get initialized
	 * It will keep on capturing the screenshot and will add it to extent and testNG report	 * 
	 */
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		
		/*
		 * this will initialize all the web element above at the runtime
		 * this- current class object
		 */
		PageFactory.initElements(driver, this);
		waitHelper=new WaitHelper(driver);
		waitHelper.waitForElement(signIn, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
	}
	
	public void clickOnSignInLink() {
		log.info("clicking on sign in link..");
		signIn.click();
	}
	
	public void enterEmailAddress(String emailAddress) {
		log.info("entering email address.."+emailAddress);
		this.emailAddress.sendKeys(emailAddress);
	}
	
	public void enterPassword(String password) {
		log.info("entering password.."+password);
		this.password.sendKeys(password);
		loggedInExtentReport("entering password.."+password);
	}
	
	/*
	 * when we do signIN, we will go to home page. Make a class for this.
	 */
	public HomePage clickOnSubmitButton() {
		log.info("clicking on submit button");
		new JavaScriptHelper(driver).scrollDownVertically();
		submitLogin.click();
		loggedInExtentReport("clicking on submit button");		
		return new HomePage(driver);
	}
	
	public boolean verifySuccessLoginMsg() {
		return new VerificationHelper(driver).isDisplayed(succesMsgObject);
	}
	
	public void enterRegisterationOnEmail() {
		/*
		 * this will help to enter unique email address in registration email address textbox
		 */
		String email=System.currentTimeMillis()+"@gmail.com";
		log.info("entering registration email: "+email);
		registerationEmailAddress.sendKeys(email);
		loggedInExtentReport("entering registration email: "+email);
	}
	
	public RegistrationPage clickOnCreateAccount() {
		createAccount.click();
		return new RegistrationPage(driver);
	}
	
	/*
	 * consolidated method for 1 workflow like signIN
	 */
	public void loginToApplication(String emailAddress, String Password) {
		clickOnSignInLink();
		enterEmailAddress(emailAddress);
		enterPassword(Password);
		clickOnSubmitButton();
	}
	
	public void loggedInExtentReport(String message) {
		TestBase.extentTest.log(Status.INFO,message);
	}
	
		

}
