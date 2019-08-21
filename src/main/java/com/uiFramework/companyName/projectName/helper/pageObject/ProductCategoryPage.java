package com.uiFramework.companyName.projectName.helper.pageObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.companyName.projectName.helper.assertion.AssertionHelper;
import com.uiFramework.companyName.projectName.helper.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.javaScript.JavaScriptHelper;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.select.DropdownHelper;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * Page object for http://automationpractice.com/index.php?id_category=3&controller=category
 * There should be method to select category, style, color and for remaining things, to verify the links and to click on those links
 */
public class ProductCategoryPage {

	/*
	 * To add the product to cart, you need to do mouseover the product then click
	 * on "Add to cart" and then "proceed to checkout" Xpath for totalProduct is
	 * find out by locating the 1st element on the page, then start removing the tag
	 * from it one by one and when it is become common for all elements displayed on
	 * UI, then it will return list of webelements which is basically totalProdcuts
	 */
	private WebDriver driver;
	private Logger log = LoggerHelper.getLogger(ProductCategoryPage.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//*[@id='layered_block_left']/p")
	public WebElement catlogTextObj;

	@FindBy(xpath = "//*[@id='layer_cart']/div[1]/div[1]/h2")
	public WebElement productAddedSuccessfullyMessage;

	@FindBy(xpath = "//*[@id='center_column']/ul/li[1]/div/div[2]/div[2]/a[1]/span")
	public WebElement addToCart;

	@FindBy(xpath = "//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	public WebElement proceedToCheckout;

	@FindBy(xpath = "//*[@id='center_column']/ul/li")
	public List<WebElement> totalProducts;

	@FindBy(xpath="//*[@id='selectProductSort']")
	public WebElement sortBy;

	/*
	 * Will return the current price of all the elements. For some of element, we
	 * have three price- discounted,actual, discount
	 */
	@FindBy(xpath = "//*[@id='center_column']/ul/li/div/div[2]/div[1]/span[1]")
	public List<WebElement> allPriceElements;

	public ProductCategoryPage(WebDriver driver) {

		/*
		 * PageFactory- Factory class to make using Page Objects simpler and easier.
		 */
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(catlogTextObj, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.loggedInExtentReport("ProductCategoryPage class object is created");
	}

	/*
	 * To click on add to cart, we need to do mouseover on the element Based on
	 * index in li tag, we can traverse from one element to another.
	 * //*[@id="center_column"]/ul/li[2] <- > /div/div[1]/div/a[1]/img
	 * 
	 * so we need to divide this xpath in 2 part to make this generic
	 * 
	 */
	public void mouseOverOnProduct(int index) {
		String firstPart = "//*[@id='center_column']/ul/li[";
		String secondPart = "]/div/div[1]/div/a[1]/img";
		Actions action = new Actions(driver);
		log.info("doing mouse over on the " + index + " product");
		action.moveToElement(driver.findElement(By.xpath(firstPart + index + secondPart))).build().perform();
	}

	/*
	 * will add the highlighted product in the cart
	 */
	public void clickOnAddToCart() {
		log.info("Adding to cart");
		addToCart.click();
		;
	}

	public void clickOnProceedToCheckout() {
		log.info("proceed to checkout");
		proceedToCheckout.click();
	}

	/*
	 * Based on color supplied from the script using ApplicationText class, we will
	 * select the checkbox.
	 * http://automationpractice.com/index.php?id_category=3&controller=category but
	 * 1st we need to scrolldown to see color header on the UI
	 */
	public void selectColor(String data) {
		new JavaScriptHelper(driver).scrollIntoView(driver
				.findElement(By.xpath("//*[contains(text(),'" + data + "')]parent::*/preceding-sibling::input[1]")));
		driver.findElement(By.xpath("//*[contains(text(),'" + data + "')]parent::*/preceding-sibling::input[1]"))
				.click();
		try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * There only 3 sizes (small,medium ,large) will write individual method for
	 * them If already selected, then do nothing else checked it
	 */

	public void selectSmallSize() {
		log.info("selecting small size..");
		boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).isSelected();
		if (!selected) {
			driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
			log.info("checkbox is selected corresponding to small size..");
		} else {
			log.info("checkbox is not selected corresponding to small size as it was already selected..");
		}

	}

	public void selectMediumSize() {
		log.info("selecting small size..");
		boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']")).isSelected();
		if (!selected) {
			driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']")).click();
			log.info("checkbox is selected corresponding to medium size..");
		} else {
			log.info("checkbox is not selected corresponding to medium size as it was already selected..");
		}

	}

	public void selectLargeSize() {
		log.info("selecting small size..");
		boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).isSelected();
		if (!selected) {
			driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();
			log.info("checkbox is selected corresponding to large size..");
		} else {
			log.info("checkbox is not selected corresponding to large size as it was already selected..");
		}

	}

	public void selectFirstProduct() {
		/*
		 * can be performed in 2 ways .No matter what it will always select 1st product.
		 * We do mainly this in automation as we just to have the check functionality.
		 * 
		 */
		/*
		 * Option-1 mouseOverOnProduct(1); clickOnAddToCart();
		 * 
		 * Option-2
		 */
		Actions actions = new Actions(driver);
		log.info("performing mouse over on the first product of the page..");
		TestBase.loggedInExtentReport("performing mouse over on the first product of the page..");
		actions.moveToElement(totalProducts.get(0)).build().perform();
		log.info("clicking on add the product to the cart..");
		TestBase.loggedInExtentReport("clicking on add the product to the cart..");
		addToCart.click();

	}

	public int getTotalProducts() {
		return totalProducts.size();
	}

	public List<WebElement> getAllProductPrice() {
		return allPriceElements;
	}

	public void selectSortByFilter(String dataToSelect) {
		//new JavaScriptHelper(driver).scrollIntoView(sortBy);
		DropdownHelper dropdownHelper = new DropdownHelper(driver);
		dropdownHelper.selectUsingVisibleText(sortBy, dataToSelect);
	}

	public ArrayList<Integer> getPriceMassagedData(Iterator<WebElement> iterator) {

		// to store the price in integer format
		ArrayList<Integer> array = new ArrayList<Integer>();

		/*
		 * price come in $16.55 so remove $ from this and change to int for sorting
		 * order verification store it in arraylist
		 */
		while (iterator.hasNext()) {
			String temp = iterator.next().getText();

			if (temp.contains("$")) {
				// The substring begins with the character at the specified index and extends to
				// the end of this string.
				String actualData = temp.substring(1);
				log.info(actualData);
				double decNo = Double.parseDouble(actualData);
				int productPrice = (int) decNo;
				array.add(productPrice);

			}
		}
		return array;
	}

	/*
	 * loop to check if all the next price is smaller than the previous one and if
	 * not throw an error
	 */
	public boolean verifyArrayHasAscendingData(ArrayList<Integer> array) {
		log.info(array);

		for (int i = 0; i < array.size() - 1; i++) 
		{
			if (array.get(i) <= array.get(i + 1)) {
				log.info("obj.get(i): " + array.get(i));
				log.info("obj.get(i+1): " + array.get(i + 1));
			} 
			else {

				AssertionHelper.makeFalse("price filter is not working");
				return false;
			}

		}	// TODO Auto-generated method stub
		return true;
	}
	
}
