package com.uiFramework.companyName.projectName.helper.browserConfig;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class InternetExplorerBrowser {

	public InternetExplorerOptions getInternetExplorerOptions() {
		
		DesiredCapabilities explorerCapabilities = DesiredCapabilities.internetExplorer();
		
		/*
		 * Capability that defines how elements are scrolled into view in the InternetExplorerDriver.
		 */
		explorerCapabilities.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
		
		/*
		 * Capability that defines to clean or not browser cache before launching IE by IEDriverServer.
		 */
		explorerCapabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
		
		/*
		 * Capability that defines to ignore ot not browser protected mode settings during starting by IEDriverServer. 
		 * Setting this capability will make your tests unstable and hard to debug
		 */
		explorerCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		
		/*
		 * Capability that defines whether to ignore the browser zoom level or not.
		 */
		explorerCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
		
		InternetExplorerOptions internetExplorerOptions=new InternetExplorerOptions(explorerCapabilities);
		
		return internetExplorerOptions;
		
	}
	
	public WebDriver getInternetExplorereDriver(InternetExplorerOptions op) {
		if(System.getProperty("os.name").contains("Window")){	
			/*
			 * you can set driver in "usr/bin" location in linux
			 */
			System.setProperty("webdriver.chrome.driver","src\\main\\resource\\browserDriver\\IEDriverServer.exe");
			return new InternetExplorerDriver(op);
		}
		return null;
	}
}
