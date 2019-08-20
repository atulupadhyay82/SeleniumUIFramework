package com.uiFramework.companyName.projectName.helper.listener;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * when you are running testscript, due to network issue or your server is down, script failure occurs.
 * Now to distinguish whether it is an actual failure or its a object failure, we need to retry the script run and that too automatically.
 * Need to implement IRetryAnalyzer (Comes from the testNG)
 * Interface to implement to be able to have a chance to retry a failed test.
 */
public class RetryCount implements IRetryAnalyzer{
	
	private int retryCount=0;
	private int maxCount=3;
	//define logger class object
	private Logger log= LoggerHelper.getLogger(RetryCount.class);
	
	/**
	 * Returns true if the test method has to be retried, false otherwise.
	 * ITestResult- This class describes the result of a test.
	 */
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(retryCount < maxCount)
		{
			log.info("Retrying test: "+result.getName()+" with status "+getResultStatusName(result.getStatus())+ " for "+ (retryCount+1)+" times");
			retryCount++;
			return true;
		}
		
		return false;
	}
/**
 * here SKIP status means retry of testcase. In the last attempt we will get SUCCESS or Failure
 * @param status
 * @return
 */
	private String getResultStatusName(int status) {
		// TODO Auto-generated method stub
		String resultStatus=null;
		
		if(status ==1)
			resultStatus="SUCCESS";
		if(status ==2)
			resultStatus="FAILED";
		if(status ==3)
			resultStatus="SKIP";
		
		return resultStatus;
	}

}
