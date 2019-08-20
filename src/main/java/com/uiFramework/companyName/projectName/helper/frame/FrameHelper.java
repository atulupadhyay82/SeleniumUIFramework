package com.uiFramework.companyName.projectName.helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

public class FrameHelper {
	
	//define logger class object
		private Logger log= LoggerHelper.getLogger(WaitHelper.class);
		
		//Define WebDriver class object
		private WebDriver driver;
		
		//Calling class will supply WebDriver class object to this via the constructor. Hence object defined private.	
		public FrameHelper(WebDriver driver) {
			this.driver=driver;
		}

		/**
		 * this method will switch to frame based on Index
		 * @param index
		 */
		public void switchToFrame(int index)
		{
			driver.switchTo().frame(index);
			log.info("switched to: "+index+" frame");
		}
		
		/**
		 * this method will switch to frame based on frame name
		 * @param frameName
		 */
		public void switchToFrame(String frameName)
		{
			driver.switchTo().frame(frameName);
			log.info("switched to: "+frameName+" frame");
		}
		 /**
		  * this method will switch to frame based on Web Element
		  * @param element
		  */
		public void switchToFrame(WebElement element)
		{
			driver.switchTo().frame(element);
			log.info("switched to: "+element.toString());
		}
}
