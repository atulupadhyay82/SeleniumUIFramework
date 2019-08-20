package com.uiFramework.companyName.projectName.helper.browserConfig;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeBrowser {

	public ChromeOptions getChromeOptions() {
		
		//Class to manage options specific to ChromeDriver
		ChromeOptions options = new ChromeOptions();
		
		//The arguments to use when starting Chrome.
		options.addArguments("--test-type");
		options.addArguments("--disable-popup-blocking");
		
		DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
		chromeCapabilities.setJavascriptEnabled(true);
		
		//Key used to store a set of ChromeOptions in a Capabilities object.
		options.setCapability(ChromeOptions.CAPABILITY, chromeCapabilities);
		
		//for linux
		/*
		 * we can not launch the application UI when the box is linux and don't use any sandbox environment
		 */
		if(System.getProperty("os.name").contains("Linux")){
			options.addArguments("--headless","window-size=1024,768","--no-sandbox");
		}
		
		return options;
		
	}
	
	public WebDriver getChromeDriver(ChromeOptions op) {
		if(System.getProperty("os.name").contains("Linux")){	
			/*
			 * you can set driver in "usr/bin" location in linux
			 */
			
			System.setProperty("webdriver.chrome.driver","usr/bin/chrome");
			return new ChromeDriver(op);
		}
		else if(System.getProperty("os.name").contains("mac")){	
			System.setProperty("webdriver.chrome.driver","src\\main\\resource\\browserDriver\\chromedriver");
			return new ChromeDriver(op);
		}
		else if(System.getProperty("os.name").contains("Window")){	
			System.setProperty("webdriver.chrome.driver","src\\main\\resource\\browserDriver\\chromedriver.exe");
			return new ChromeDriver(op);
		}
		return null;
	}
}
