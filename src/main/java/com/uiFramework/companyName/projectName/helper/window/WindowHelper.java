package com.uiFramework.companyName.projectName.helper.window;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

//All window related method, how to switch to parent/child tab, how to navigate backward/forward
public class WindowHelper {

	// define logger class object
	private Logger log = LoggerHelper.getLogger(WindowHelper.class);

	// Define WebDriver class object
	private WebDriver driver;

	// Calling class will supply WebDriver class object to this via the constructor.
	// Hence object defined private.
	public WindowHelper(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method will switch to parent window
	 */
	public void switchToParentWindow() {
		log.info("switching to parent window");
		driver.switchTo().defaultContent();
	}

	/**
	 * 
	 * This method will switch to child window based on the index param
	 * 
	 * @param index
	 */
	public void switchToWindow(int index) {
		Set<String> windows = driver.getWindowHandles();
		int i = 1;
		for (String window : windows) {
			if (i == index) {
				log.info("Switch to: " + index + " window");
				driver.switchTo().window(window);
			} else {
				i++;
			}
		}
	}

	/**
	 * This will close all the tabbed windows except main
	 */

	public void closeAllTabsAndSwitchToMainWindow() {
		Set<String> windows = driver.getWindowHandles();
		String mainWindow = driver.getWindowHandle();
		for (String window : windows) {
			if (!window.equalsIgnoreCase(mainWindow)) {
				driver.close();
			}

		}
		log.info("switched to main window");
		driver.switchTo().window(mainWindow);
	}

	/**
	 * this method will do browser to naqvigate back
	 */
	public void navigateBack() {
		log.info("navigating back");
		driver.navigate().back();
	}

	/**
	 * this method will do browser to navigate forward
	 */
	public void navigateForward() {
		log.info("naviagting forward");
		driver.navigate().forward();
	}

}
