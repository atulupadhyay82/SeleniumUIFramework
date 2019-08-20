package com.uiFramework.companyName.projectName.helper.testScripts;

import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

/*
 * will test the screenshot function "captureScreen" of the testbase class
 */
public class TestScreenShot extends TestBase{
	
	@Test
	public void takeScreens() {
		driver.get("https://www.youtube.com/");
		captureScreen("firstScreen");
	}

}
