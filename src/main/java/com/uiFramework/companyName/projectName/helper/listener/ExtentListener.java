package com.uiFramework.companyName.projectName.helper.listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.utils.ExtentManager;


/*
 * A listener for test running.
 * Same thing we are doing in testbase also. those annotation method also logging the same data in the report.
 * it will log for same method twice, one by testbase and one by listener class in the extent report. Need to avoid this duplication here.
 * So comment out all extent,just keep reporter.log
 */
public class ExtentListener implements ITestListener {

	public static ExtentReports  extentReports ;
	public static ExtentTest extentTest;
	private Logger log=LoggerHelper.getLogger(ExtentListener.class);
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.log(Status.INFO, result.getName()+" started..");
		
		/*
		 * will write in testNg report
		 * 
		 */
		
		Reporter.log(result.getMethod().getMethodName()+" Test started..");
		log.info(result.getMethod().getMethodName()+" Test started..");
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.log(Status.INFO, result.getName()+" passed..");
		Reporter.log(result.getMethod().getMethodName()+" Test passed..");
		log.info(result.getMethod().getMethodName()+" Test passed..");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.log(Status.FAIL, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test failure..");
		log.error(result.getMethod().getMethodName()+" Test failure..");
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//extentTest.log(Status.SKIP, result.getThrowable());
		Reporter.log(result.getMethod().getMethodName()+" Test skipped.."+result.getThrowable());
		log.warn(result.getMethod().getMethodName()+" Test skipped.."+result.getThrowable());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//extentReports= ExtentManager.getInstance();
		//extentTest=extentReports.createTest(context.getName());
		
		Reporter.log(context.getName()+" Test started..");
		log.info(context.getName()+" Test started..");
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		//extentReports.flush();
		
		Reporter.log(context.getName()+" Test finished..");
		log.info(context.getName()+" Test finished..");
		
	}

}
