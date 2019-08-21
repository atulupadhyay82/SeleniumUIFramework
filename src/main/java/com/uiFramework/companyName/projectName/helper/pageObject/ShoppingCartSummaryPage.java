package com.uiFramework.companyName.projectName.helper.pageObject;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.companyName.projectName.helper.assertion.VerificationHelper;
import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * This page is very very important in ecommerce website automation
 * Here we need to make sure all the products are added, verify the total prices and shipping prices
 * if deleting an item from there, then item should get invisible and price should get changed
 * http://automationpractice.com/index.php?controller=order
 */

public class ShoppingCartSummaryPage {
	
	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(ProductCategoryPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//a[@title='Delete']")
	List<WebElement> deleteProductList;
	
	@FindBy(xpath="//*[@id='columns']/div[1]/span[2]")
	WebElement yourShopingCart;
	
	
	@FindBy(xpath="//*[contains(text(),'Your shopping cart is empty')]")
	WebElement emptyShopingCartMsg;
	
	public ShoppingCartSummaryPage(WebDriver driver) {
			
			/*
			 * PageFactory- Factory class to make using Page Objects simpler and easier.
			 */
			PageFactory.initElements(driver, this);
			waitHelper=new WaitHelper(driver);
			waitHelper.waitForElement(yourShopingCart, ObjectReader.reader.getExplicitWait());
			new TestBase().getNavigationScreen(driver);
			TestBase.loggedInExtentReport("ShoppingCartSummaryPage class object is created");
		}
	
	/*
	 * Delete all the element fromt the cart
	 */
	public void deleteProductFromShoppingCart() throws InterruptedException {
		Iterator<WebElement> iterator = deleteProductList.iterator();
		while(iterator.hasNext()) {
			WebElement delete=iterator.next();
			delete.click();
			Thread.sleep(2000);
		}
	}
	
	public boolean verifyEmptyCartMessage() {
		log.info("verifying deleted shopping cart message");
		return new VerificationHelper(driver).isDisplayed(emptyShopingCartMsg);
	}

	public boolean verifyProductInTheCart(String item) {
		log.info("check for the product in the cart: "+item);
		WebElement product = driver.findElement(By.xpath("//*[contains(text(),'"+item+"')]"));
		return new VerificationHelper(driver).isDisplayed(product);
		
	}
	/*
	 * You can write here lot of methods-
	 * 1- once it is empty, you dont get option to continue the shopping
	 * 2- once the product is added, verify the total price
	 */
}
