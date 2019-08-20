package com.uiFramework.companyName.projectName.helper.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {

	
	private static boolean root=false;
	
	public static Logger getLogger(Class cls)
	{
		if(root) // some other class already instantiated this
		{
			return Logger.getLogger(cls);
		}
		//will create ResourceHelper class to avoid using hardcoded path for any resources
		//then will use relative path which will not break framework in future
		//PropertyConfigurator.configure("D:\\Adobe_Workspace\\uiFramework\\src\\main\\resource\\configFile\\log4j.properties");
		PropertyConfigurator.configure("src\\main\\resource\\configFile\\log4j.properties");
		root=true;
		return Logger.getLogger(cls);
	}
	
	public static void main(String[] args) {
		Logger log= LoggerHelper.getLogger(LoggerHelper.class);
		log.info("logger is configured");
		log.info("logger is configured");
		log.info("logger is configured");
	}
}
