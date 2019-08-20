package com.uiFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

public class B {
	
	int i= 1;
	/**
	 * will pass in 3rd attempt
	 */
	@Test
	public void testMethodB1()
	{
		
		if(i==3)
			Assert.assertTrue(true);
		else {
			i++;
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testMethodB2()
	{
		Assert.assertTrue(false);
	}
}
