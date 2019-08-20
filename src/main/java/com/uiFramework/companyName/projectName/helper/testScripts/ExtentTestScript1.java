package com.uiFramework.companyName.projectName.helper.testScripts;

import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;

import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

public class ExtentTestScript1 extends TestBase {
	
	@Test
	public void testLogin() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin1() {
		AssertionHelper.makeFalse();
	}
	
	@Test
	public void testLogin3() {
		AssertionHelper.makeTrue();
	}
	
	@Test
	public void testLogin4() {
		AssertionHelper.makeFalse();
	}

}
