package com.uiFramework.companyName.projectName.helper.resource;

public class ResourceHelper {

	public static String getResourceHelper(String Path)
	{
		//will return absolute path of this project
		String basePath= System.getProperty("user.dir");
		
		
		return basePath+"/"+Path;
	}
}
