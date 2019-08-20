package com.uiFramework.companyName.projectName.helper.browserConfig.config;


import com.uiFramework.companyName.projectName.helper.browserConfig.BrowserType;

/*
 * Will remove hardcoded browser configuration (like implicit wait, explicit wait) 
 * wait.setImplictWait(30, TimeUnit.SECONDS);
 * wait.pageLoadtime(30, TimeUnit.SECONDS);
 * so that we need to make changes in the future in one file, not in every test scripts. making them in interface, these configuration will be there and need to be there in the framework
 * can be implemented later on in the class.You can have more configuration here based on your requirement
 */

public interface ConfigReader {
	
	public int getImplicitWait();
	public int getExplicitWait();
	public int getPageLoadTime();
	public BrowserType getBrowserType();
	
}
