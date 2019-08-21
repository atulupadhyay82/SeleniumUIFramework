package com.uiFramework.companyName.projectName.helper.testScripts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;
import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.javaScript.JavaScriptHelper;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.pageObject.NavigationMenu;
import com.uiFramework.companyName.projectName.helper.pageObject.ProductCategoryPage;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;

/**
 * Verify if i am sorting the item by price filter then the 1st item price
 * should be less than the rest of item.
 * 
 * @author atupadhy
 *
 */
public class VerifySortFilter extends TestBase {

	private Logger log = LoggerHelper.getLogger(VerifySortFilter.class);

	@Test
	public void verifyLowestFirstPriceInProductList() throws InterruptedException {
		
		getApplicationURL(ObjectReader.reader.getURL());
		
		//We are going to navigate menu hence need to create an object of this class
		
		NavigationMenu navigationMenu=new NavigationMenu(driver);
		
		//when you click on women navigation, you will move productcategprypage class
		ProductCategoryPage productCategoryPage= navigationMenu.clickOnMenu(navigationMenu.womenMenu);
		
		//select price filter based on the string passed in (can be seen on UI)
		productCategoryPage.selectSortByFilter("Price: Lowest first");
		
		//Wait for some time to get price filter selected
		Thread.sleep(8000);
		
		//now get the price of all the element
		List<WebElement> prices = productCategoryPage.allPriceElements;
		Iterator<WebElement> iterator = prices.iterator();
		
		
		ArrayList<Integer> data = productCategoryPage.getPriceMassagedData(iterator);
		boolean status = productCategoryPage.verifyArrayHasAscendingData(data);
		AssertionHelper.verfiyTrue(status);
		
		//to store the price in integer format
		ArrayList<Integer> array=new ArrayList<Integer>();
		
		/*
		 * price come in $16.55 so remove $ from this and change to int for sorting order verification
		 * store it in arraylist
		 */
		while(iterator.hasNext()) {
			String temp=iterator.next().getText();
			
			if(temp.contains("$"))
			{
				//The substring begins with the character at the specified index and extends to the end of this string. 
				String actualData=temp.substring(1);
				log.info(actualData);
				double decNo=Double.parseDouble(actualData);
				int productPrice= (int)decNo;
				array.add(productPrice);
				
			}
		}
		log.info(array);
		
		//loop to check if all the next price is smaller than the previous one and if not throw an error
		for(int i=0;i<array.size()-1;i++) {
			if(array.get(i)<array.get(i+1)) {
				log.info("obj.get(i): "+array.get(i));
				log.info("obj.get(i+1): "+array.get(i+1));
			}
			else {
				
				AssertionHelper.makeFalse("price filter is not working");
			}
				
				
			
		}
	}

}
