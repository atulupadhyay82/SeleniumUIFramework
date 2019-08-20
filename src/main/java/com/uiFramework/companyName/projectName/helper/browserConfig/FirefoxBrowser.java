package com.uiFramework.companyName.projectName.helper.browserConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxBrowser {

	public FirefoxOptions getFirefoxOptions() {
		
		FirefoxProfile profile= new FirefoxProfile();
		/*
		 * Sets whether Firefox should accept SSL certificates which have expired, 
		 * signed by an unknown authority or are generally untrusted. This is set to true by default.
		 */
		profile.setAcceptUntrustedCertificates(true);
		
		/*
		 * By default, it is assumed that the certificates were not be issued from a trusted CA. 
		 */
		profile.setAssumeUntrustedCertificateIssuer(true);
		
		DesiredCapabilities firfoxCapabilities = DesiredCapabilities.firefox();
		firfoxCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
		firfoxCapabilities.setCapability("marionette",true);
		
		FirefoxOptions firefoxOptions= new FirefoxOptions(firfoxCapabilities);
		
		
		//for linux
		/*
		 * we can not launch the application UI when the box is linux and don't use any sandbox environment
		 */
		if(System.getProperty("os.name").contains("Linux")){
			firefoxOptions.addArguments("--headless","window-size=1024,768","--no-sandbox");
		}
		
		return firefoxOptions;
		
	}
	
	public WebDriver getFirefoxDriver(FirefoxOptions op) {
		/*
		 * you can set driver in "usr/bin" location in linux
		 */
		if(System.getProperty("os.name").contains("Linux")){	
			System.setProperty("webdriver.chrome.driver","usr/bin/geckodriver");
			return new FirefoxDriver(op);
		}
		else if(System.getProperty("os.name").contains("mac")){	
			System.setProperty("webdriver.chrome.driver","src\\main\\resource\\browserDriver\\geckodriver");
			return new FirefoxDriver(op);
		}
		else if(System.getProperty("os.name").contains("Window")){				
			System.setProperty("webdriver.chrome.driver","src\\main\\resource\\browserDriver\\geckodriver.exe");
			return new FirefoxDriver(op);
		}
		return null;
	}
}
