package com.uiFramework.companyName.projectName.helper.pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * Page object for http://automationpractice.com/index.php?id_category=3&controller=category
 * There should be method to select category, style, color and for remaining things, to verify the links and to click on those links
 */
public class ProductCategoryPage {
	
	/*
	 * To add the product to cart, you need to do mouseover the product then click on "Add to cart" and then "proceed to checkout"
	 * 
	 */
	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(ProductCategoryPage.class);
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='layered_block_left']/p")
	public WebElement catlogTextObj;
	
	
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[1]/h2")
	public WebElement productAddedSuccessfullyMessage;
	
		
	@FindBy(xpath="//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")
	public WebElement addToCart;


	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	public WebElement proceedToCheckout;
	
	@FindBy(xpath="//*[@id='layered_block_left']/p")
	public List<WebElement> totalProducts;
	
	@FindBy(xpath="//*[@id='layered_block_left']/p")
	public WebElement sortBYy;
	
	@FindBy(xpath="//*[@id='layered_block_left']/p")
	public WebElement catlogTextObj;
	
	@FindBy(xpath="//*[@id='layered_block_left']/p")
	public WebElement catlogTextObj;
	
	@FindBy(xpath="//*[@id='layered_block_left']/p")
	public WebElement catlogTextObj;
	
	
	public ProductCategoryPage(WebDriver driver) {}

}
