package com.uiFramework.companyName.projectName.helper.browserConfig.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.uiFramework.companyName.projectName.helper.browserConfig.BrowserType;
import com.uiFramework.companyName.projectName.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;

	private static Properties prop;

	public PropertyReader() {
		String filepath = ResourceHelper.getResourceHelper("src/main/resource/configFile/config.properties");
		try {
			file = new FileInputStream(new File(filepath));
			prop = new Properties();
			prop.load(file);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public int getImplicitWait() {
		// TODO Auto-generated method stub
		return Integer.parseInt(prop.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		// TODO Auto-generated method stub
		return Integer.parseInt(prop.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		// TODO Auto-generated method stub
		return Integer.parseInt(prop.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		// TODO Auto-generated method stub
		return BrowserType.valueOf(prop.getProperty("browserType"));
	}

}
