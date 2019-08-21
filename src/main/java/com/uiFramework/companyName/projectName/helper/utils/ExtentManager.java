package com.uiFramework.companyName.projectName.helper.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * To enable automated reporting. 
 * Extent Reports is a customizable HTML report Although the built-in reports provide information on the steps
 * that are executed as part of the test case, they need more customization to be shared with all the major project stakeholders.
 * Extent Reports offer several advantages when compared to the built-in reports that are generated through JUnit and TestNG 
 * such as pie chart representation, test stepwise report generation, adding screenshots with each test step etc., 
 * at every test step and a presentable user interface that can be shared with all stakeholders of the project.
 * @author atupadhy
 * Drawback- If the page is big, it will not able to take the screenhot of entire page. Here you can use JavaScriptHelper class
 * to zoomin the page by 40 percent.
 */
public class ExtentManager {
	
	private static ExtentReports extent;
	
	/*
	 * to get the object of ExtentReports. Currently being saved in following location- "test-output/extent.html"
	 */
	public static ExtentReports getInstance()
	{
		if(extent == null)
			return createInstance(System.getProperty("user.dir")+"\\src\\main\\resource\\report\\extent.html");
		else 
			return extent;
	}
	/*
	 *Create an instance of Extentreports 
	 */
	public static ExtentReports createInstance(String filename) {
		ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(filename);
		
		//sets the configuration to allow viewing or hiding charts on report open
		htmlReport.config().setChartVisibilityOnOpen(true);
		
		//sets the location of charts 
		htmlReport.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		
		//Sets the Theme of the report
		htmlReport.config().setTheme(Theme.STANDARD);
		
		//Sets the document title denoted by the title tag
		htmlReport.config().setDocumentTitle(filename);
		htmlReport.config().setEncoding("utf-8");
		htmlReport.config().setReportName("Automation report");
		
		/**
		 * The ExtentReports report client for starting reporters and building reports. For most applications, you should have one ExtentReports instance for the entire JVM. 
		 * ExtentReports itself does not build any reports, but allows reporters to access information, which in turn build the said reports.
		 */
		extent=new ExtentReports();
		
		//Attach a ExtentReporter reporter, allowing it to access all started tests, nodes and logs 
		extent.attachReporter(htmlReport);
		return extent;
		
	}
}
