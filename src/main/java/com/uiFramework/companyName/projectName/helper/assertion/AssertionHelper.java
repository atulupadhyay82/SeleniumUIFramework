package com.uiFramework.companyName.projectName.helper.assertion;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

/**
 * Here we are making things false or true. we are putting assertion.
 * no need to import Assertion package in every testscript.
 * @author atupadhy
 *
 */

public class AssertionHelper {

	// define logger class object
		private static Logger log = LoggerHelper.getLogger(AssertionHelper.class);
		
		/*
		 * compare two element text
		 */
		public static void verifyText(String s1,String s2) {
			log.info("verifying text with :"+s1+" with "+s2);
			Assert.assertEquals(s1,s2);
		}
		
		/*
		 * Not verifying true here
		 */
		public static void makeTrue() {
			log.info("making script pass..");
			Assert.assertTrue(true);
		}
		/*
		 * Asserts that a condition is true. If it isn't, an AssertionError, with the given message, is thrown.
		 */
		public static void makeTrue(String message) {
			log.info("making script pass..");
			Assert.assertTrue(true,message);
		}
		
		public static void makeFalse() {
			log.info("making script fail..");
			Assert.assertTrue(false);
		}

		public static void makeFalse(String message) {
			log.info("making script fail..");
			Assert.assertTrue(false,message);
		}
		
		/*
		 *to verify whether method is returning true or false
		 */
		
		public static void verfiyTrue(boolean status) {
			Assert.assertTrue(status);
		}
		
		public static void verfiyFalse(boolean status) {
			Assert.assertFalse(status);
		}
		
		public static void verifyNull(String element) {
			log.info("verifying object is null...");
			Assert.assertNull(element);
		}
		
		public static void verifyNotNull(String element) {
			log.info("verifying object is not null...");
			Assert.assertNotNull(element);
		}

}
