package com.uiFramework.companyName.projectName.helper.testScripts;

import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;

import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

public class ExtentTestScript2 extends TestBase {
	
	@Test
	public void testLogin4() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin5() {
		AssertionHelper.makeFalse();
	}
	
	@Test
	public void testLogin6() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin7() {
		AssertionHelper.makeFalse();
	}

}
