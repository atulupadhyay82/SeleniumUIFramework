package com.uiFramework.companyName.projectName.helper.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiFramework.companyName.projectName.helper.browserConfig.config.ObjectReader;
import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.testBase.TestBase;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * for menu header to navigate over the website 
 */
public class NavigationMenu {
	

	private WebDriver driver;
	private Logger log=LoggerHelper.getLogger(NavigationMenu.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[2]/a")
	public WebElement dresesMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtsMenu;
	
	public NavigationMenu(WebDriver driver) {
		this.driver=driver;
		
		/*
		 * this will initialize all the web element above at the runtime
		 * this- current class object
		 */
		PageFactory.initElements(driver, this);
		waitHelper=new WaitHelper(driver);
		waitHelper.waitForElement(womenMenu, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		TestBase.loggedInExtentReport("NavigationMenu class object is created");
	}
	
	/*
	 * Moves the mouse to the middle of the element. The element is scrolled into view and
	 *  its location is calculated using getBoundingClientRect.
	 *  Based on param, it will do the mouseover
	 */
	public void mouseOver(String data) {
		log.info("doing mouse over on: "+data);
		TestBase.loggedInExtentReport("doing mouse over on: "+data);
		Actions actions=new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
	}
	
	/*
	 * Generalized message for clicking the item, come after doing mouseover on the navigation header.
	 * you cannot write individual method for all the menu item
	 */
	
	public ProductCategoryPage clickOnItem(String data) {
			log.info("clicking on :"+data);
			TestBase.loggedInExtentReport("clicking on :"+data);
			driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
			return new ProductCategoryPage(driver);
			
	}
	
	/*
	 * Generalized message for clicking on navigation header, after that it will take you to the page like clicking
	 * on women header, it will take you to women page
	 */
	public ProductCategoryPage clickOnMenu(WebElement element) {
		log.info("clicking on :"+element.getText());
		TestBase.loggedInExtentReport("clicking on :"+element.getText());
		element.click();
		return new ProductCategoryPage(driver);
		
}

}
