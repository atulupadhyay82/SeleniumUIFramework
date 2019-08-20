package com.uiFramework.companyName.projectName.helper.listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

/**
 * This class will be invoke by testnG in case if script/method get failed
 * We need to register this class as a listener in testNG.xml
 * @author atupadhy
 *
 */

public class RetryListener implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// TODO Auto-generated method stub
		IRetryAnalyzer retry = annotation.getRetryAnalyzer();
		if(retry == null)
			annotation.setRetryAnalyzer(RetryCount.class);
	}

}
