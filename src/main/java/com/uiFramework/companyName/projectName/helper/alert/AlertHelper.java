package com.uiFramework.companyName.projectName.helper.alert;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;


import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

/**
 * To work with alert or java script alert.
 * It is different from AssertionHelper 
 * here we are returning true of false or the string value of element
 * @author atupadhy
 *
 */

public class AlertHelper {
	
	// define logger class object
		private Logger log = LoggerHelper.getLogger(AlertHelper.class);

		// Define WebDriver class object
		private WebDriver driver;

		// Calling class will supply WebDriver class object to this via the constructor.
		// Hence object defined private.
		public AlertHelper(WebDriver driver) {
			this.driver = driver;
		}
		/**
		 * Switches to the currently active modal dialog for this particular driver instance.
		 * Returns A handle to the dialog.
		 * @return
		 */
		public Alert getAlert() {
			log.info("alert test: "+ driver.switchTo().alert().getText());
			return driver.switchTo().alert();
		}
		
		public void acceptAlert() {
			getAlert().accept();
			log.info("accept alert is done..");
		}
		
		public void dismissAlert() {
			getAlert().dismiss();
			log.info("dismiss alert is done..");
			
		}
		
		public String getAlertText() {
			String text=driver.switchTo().alert().getText();
			log.info("Alert text is: "+text);
			return text;
			
		}
		/**
		 * Indicates that a user has tried to access an alert when one is not present.
		 * @return
		 */
		public boolean isAlertPresent()
		{
			try {
				driver.switchTo().alert();
				log.info("alert is present..");
				return true;
			}
			catch(NoAlertPresentException e) {
				log.info(e.getCause());
				return false;
			}
		}
		
		public void acceptAlertIfPresent()
		{
			if(isAlertPresent()) {
				acceptAlert();
			}
			else {
				log.info("Alert box is not present..");
			}
		}
		
		public void dismissAlertIfPresent()
		{
			if(isAlertPresent()) {
				dismissAlert();
			}
			else {
				log.info("Alert box is not present..");
			}
		}
		
		/**
		 * Sometime we have to enter some text in the alert box before accepting it
		 * @param text
		 */
		
		public void acceptPrompt(String text)
		{
			if(isAlertPresent()) {
				Alert alert = getAlert();
				alert.sendKeys(text);
				alert.accept();
				log.info("Alert text is : "+text);
				
			}
		}
}
