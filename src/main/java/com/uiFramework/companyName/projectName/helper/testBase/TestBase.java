package com.uiFramework.companyName.projectName.helper.testBase;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.uiFramework.companyName.projectName.helper.browserConfig.BrowserType;
import com.uiFramework.companyName.projectName.helper.browserConfig.ChromeBrowser;
import com.uiFramework.companyName.projectName.helper.browserConfig.FirefoxBrowser;
import com.uiFramework.companyName.projectName.helper.browserConfig.InternetExplorerBrowser;
import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.config.PropertyReader;
import com.uiFramework.companyName.projectName.helper.excel.ExceLHelper;
import com.uiFramework.companyName.projectName.helper.javaScript.JavaScriptHelper;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.resource.ResourceHelper;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.utils.ExtentManager;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

public class TestBase {

	public static ExtentReports extentReports;
	private Logger log = LoggerHelper.getLogger(TestBase.class);
	public WebDriver driver;

	/*
	 * where we are gonna store all the screenshots
	 */
	public static File reportDirectory;

	/*
	 * Defines a test. You can add logs, snapshots, assign author and categories to
	 * a test and its children.
	 */
	public static ExtentTest extentTest;

	@BeforeSuite
	public void beforeSuite() {
		/*
		 * get the instance of ExtentReports from the ExtentManager class. It will run
		 * for just 1 time.
		 */
		extentReports = ExtentManager.getInstance();
	}

	

	@BeforeTest
	public void BeforeTest() {
		/*
		 * called before all of your test in the class. ObjectReader.reader is a
		 * reference of ConfigReader interface.
		 */
		ObjectReader.reader = new PropertyReader();
		reportDirectory = new File(ResourceHelper.getResourceHelper("src\\main\\resource\\screenShots"));
		setupDriver(ObjectReader.reader.getBrowserType());
		/*
		 * Create a test with the classname. Classname here is for which you are running
		 * the test
		 */
		extentTest = extentReports.createTest(getClass().getSimpleName());
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		/*
		 * A Method provides information about, and access to, a single method on a
		 * class or interface. Before every method it will execute and print this log
		 * "XYX method is started"
		 */
		extentTest.log(Status.INFO, method.getName() + " test started");

	}

	/*
	 * After test execution, we have pass and fail report for that method. log that
	 * thing with customized message getThrowable()- print the exception in the
	 * logs/report
	 */

	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, result.getThrowable());
			/*
			 * Will attach the screenshot of window with the test. Also its need to run on
			 * the class which launch the browser.
			 */
			String imagePath = captureScreen(result.getName(), driver);
			extentTest.addScreenCaptureFromPath(imagePath);

		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, result.getName() + " is passed");
			String imagePath = captureScreen(result.getName(), driver);
			extentTest.addScreenCaptureFromPath(imagePath);
		} else if (result.getStatus() == ITestResult.SKIP)
			extentTest.log(Status.SKIP, result.getThrowable());

		log.info("************" + result.getName() + "Finished**********");
		extentTest.log(Status.INFO, "************" + result.getName() + "Finished**********");
		/*
		 * Writes test information from the started reporters to their output view
		 */

		extentReports.flush();

	}

	@AfterTest
	public void afterTest() {
		if (driver != null)
			driver.quit();
	}

	/*
	 * doing browser config based on the parameter.
	 */
	public WebDriver getBroswerType(BrowserType btype) {
		try {
			switch (btype) {

			case Chrome:
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions option = chrome.getChromeOptions();
				return chrome.getChromeDriver(option);
			case Iexplorer:
				InternetExplorerBrowser ie = InternetExplorerBrowser.class.newInstance();
				InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
				return ie.getInternetExplorereDriver(internetExplorerOptions);
			case Firefox:
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions firefoxOptions = new FirefoxOptions();
				return firefox.getFirefoxDriver(firefoxOptions);
			default:
				throw new Exception("Driver not found: " + btype.name());
			}

		} catch (Exception e) {

			log.info(e.getMessage());

		}
		return null;

	}

	/*
	 * will setup any driver
	 */
	public void setupDriver(BrowserType btype) {
		driver = getBroswerType(btype);
		log.info("Initialize web driver: " + driver.hashCode());
		WaitHelper wait = new WaitHelper(driver);
		/**
		 * wait.setImplictWait(30, TimeUnit.SECONDS); wait.pageLoadtime(30,
		 * TimeUnit.SECONDS); here it is hardcoded
		 **/

		wait.setImplictWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadtime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);

		driver.manage().window().maximize();
	}

	/*
	 * will capture the screenshot on the runtime
	 */

	public String captureScreen(String fileName, WebDriver driver2) {
		if (driver == null) {
			log.info("driver is null");
			return null;
		}

		if (fileName == "") {
			/*
			 * user is not supplying the filenmae
			 */
			fileName = "Blank";
		}
		Reporter.log("capturescreen method is called");
		loggedInExtentReport("capturescreen method is called");
		File destFile = null;

		/*
		 * to store the screenshot with"dd/mm/yy hh:mm:ss" in the destfilenam
		 */
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

		/*
		 * Indicates a driver that can capture a screenshot and store it in different
		 * ways. Obtain the screenshot into a temporary file that will be deleted once
		 * the JVM exits. It is up to users to make a copy of this file.
		 */
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			destFile = new File(reportDirectory + "/" + fileName + "_" + formatter.format(calendar.getTime()) + ".png");
			/*
			 * Copy a file to a target file.
			 */
			Files.copy(scrFile.toPath(), destFile.toPath());

			/*
			 * now we want to attach the screenshot in the testNG report
			 * 
			 */
			Reporter.log("<a href='" + destFile.getAbsolutePath() + "'><img src='" + destFile.getAbsolutePath()
					+ "'height='100' width='100'/></a>");
		} catch (Exception e) {
			e.printStackTrace();

		}

		return destFile.toString();
	}

	/*
	 * Adds a snapshot to test or log. You will not write this code everywhere, this
	 * method will help you to do the same task for you
	 */
	public void getNavigationScreen(WebDriver driver) {
		log.info("capturing UI navigation screen..");
		new JavaScriptHelper(driver).zoomBy60Percent();

		// Will capture screen as well as add it to testNG report
		String screen = captureScreen("", driver);
		new JavaScriptHelper(driver).zoomBy100Percent();

		try {
			// Will capture screen as well as add it to extent report
			extentTest.addScreenCaptureFromPath(screen);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void loggedInExtentReport(String message) {
		TestBase.extentTest.log(Status.INFO, message);
	}

	public void getApplicationURL(String url) {
		driver.get(url);
		loggedInExtentReport("navigating to .." + url);
	}
	
	public Object[][] getExcelData(String excelName,String sheetName){
		ExceLHelper excelHelper = new ExceLHelper();
		String  excelLocation= ResourceHelper.getResourceHelper("src/main/resource/configFile/")+excelName;
		log.info("excel file location is: "+excelLocation);
		Object[][] data = excelHelper.getExcelData(excelLocation, sheetName);
		return data;
	}
}
