package com.uiFramework.companyName.projectName.helper.wait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

//going to write all selenium wait methods here
public class WaitHelper 
{
	
	//define logger class object
	private Logger log= LoggerHelper.getLogger(WaitHelper.class);
	
	//Define WebDriver class object
	private WebDriver driver;
	
	//Calling class will supply WebDriver class object to this via the constructor. Hence object defined private.	
	public WaitHelper(WebDriver driver) {
		this.driver=driver;
	}
	
	
	//define implicit wait method	
	public void setImplictWait(long timeout, TimeUnit unit) {
		log.info("Implicit wait has been set to:"+timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	/**
	 * This will help us to get WebDriverWait class object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilisecond
	 * @return
	 */
	//define explict wait with polling and make this method private as it will be called in multiple wait conditions method
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMilisecond) {
		WebDriverWait wait= new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(Duration.ofMillis(pollingEveryInMilisecond));
		//ignore exceptions which can occur while waiting for an element
		wait.ignoring(NoSuchElementException.class); //the element being requested does not exist.
		wait.ignoring(ElementNotVisibleException.class);// an element is present on the DOM, it is not visible, and so is not able to be interacted with.
		wait.ignoring(StaleElementReferenceException.class);// reference to an element is now "stale" --- the element no longer appears on the DOM of the page
		wait.ignoring(NoSuchFrameException.class); 
		return wait;
	}
	
	//define 1st wait conditon method- wait till element get visible
	public void waitForElementVisiblityWithPolling(WebElement element,int timeOutInSeconds, int pollingEveryInMilisecond)
	{
		log.info("Waiting for "+element.toString()+" for :"+timeOutInSeconds+"seconds");
		WebDriverWait wait= getWait(timeOutInSeconds, pollingEveryInMilisecond);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	
	//define 1st wait conditon method- wait till element get clickable
	public void waitForElementClickable(WebElement element,int timeOutInSeconds)
	{
		log.info("Waiting for "+element.toString()+" for :"+timeOutInSeconds+"seconds");
		WebDriverWait wait= new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("element is clickable now");
	}

	//define 1st wait conditon method- wait for element to get disappear from the UI
	public void waitForElementNotPresent(WebElement element,int timeOutInSeconds)
	{
		log.info("Waiting for "+element.toString()+" for :"+timeOutInSeconds+"seconds");
		WebDriverWait wait= new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.invisibilityOf(element));
		log.info("element is invisible now");
	}
	//define 1st wait conditon method- wait for frame element to get disappear from the UI
	public void waitForFrameToAvailbleAndSwitchToIt(WebElement element,int timeOutInSeconds)
	{
		log.info("Waiting for "+element.toString()+" for :"+timeOutInSeconds+"seconds");
		WebDriverWait wait= new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("frame element is visible now");
	}
	/**
	 * this will give us FluentWait<WebDriver> class object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMilisecond
	 * @return
	 */
	public FluentWait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInMilisecond) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingEveryInMilisecond))
				.ignoring(NoSuchElementException.class);
		return wait;
	}
	
	public void waitForElementUsingFluent(WebElement element,int timeOutInSeconds, int pollingEveryInMilisecond)
	{
		log.info("Waiting for "+element.toString()+" for :"+timeOutInSeconds+"seconds");
		Wait<WebDriver> wait = getFluentWait(timeOutInSeconds, pollingEveryInMilisecond);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	
	/**
	 * Sets the amount of time to wait for a page load to complete before throwing an error. If the timeout is negative, page loads can be indefinite.
	 * @param timeout
	 * @param unit
	 */
	public void pageLoadtime(long timeout, TimeUnit unit)
	{
		log.info("waiting for page to load for: "+timeout+"seconds");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("page is loaded now");
	}


	/**
	 * will wait till the element gets visible on the UI
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds) {
		// TODO Auto-generated method stub
		
		log.info("Waiting for :"+element.toString() + " for :"+timeOutInSeconds+" seconds");
		WebDriverWait wait= new WebDriverWait(driver,timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("element is visible now");
	}
	
	
}
