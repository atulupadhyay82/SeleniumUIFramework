package com.uiFramework.companyName.projectName.helper.javaScript;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;
import com.uiFramework.companyName.projectName.helper.wait.WaitHelper;

/*
 * To perform certain actions on the object (scroll down/up, zoom in/out)
 */
public class JavaScriptHelper {
	
	//define logger class object
			private Logger log= LoggerHelper.getLogger(JavaScriptHelper.class);
			
			//Define WebDriver class object
			private WebDriver driver;
			
			//Calling class will supply WebDriver class object to this via the constructor. Hence object defined private.	
			public JavaScriptHelper(WebDriver driver) {
				this.driver=driver;
				log.info("JavaScriptHelper has been initialised");
			}
			
			/*
			 * Will execute some java script
			 */
			public Object executeScript(String script) {
				log.info("executing the script");
				JavascriptExecutor exec = (JavascriptExecutor)driver;
				return exec.executeScript(script);
			}
			
			/*
			 * Will execute some java script, also take multiple arguments
			 */
			
			public Object executeScript(String script,Object...objects) {
				log.info("executing the script with mutliple arguments");
				JavascriptExecutor exec = (JavascriptExecutor)driver;
				return exec.executeScript(script,objects);
			}
			
			/*
			 * Will scroll to element based on element x and y coordinate.
			 * getlocation() is selenium API method
			 */
			public void scrollToElement(WebElement element)
			{
				log.info("scroll to webelement: "+element.toString());
				executeScript("window.scrollTo(arguments[0],arguments[1]", element.getLocation().x,element.getLocation().y);
			}
			
			/*
			 * will scroll to that element and then click that element
			 */
			public void scrollToElementAndClick(WebElement element)
			{
				
				scrollToElement(element);
				element.click();
				log.info("element is clicked: "+element.toString());
			}
			/*
			 * will perform the same thing, just via different method.
			 */
			public void scrollIntoView(WebElement element)
			{
				log.info("scroll to webelement: "+element.toString());
				executeScript("arguments[0].scrollIntoView()",element);
				
			}
			
			public void scrollIntoElementAndClick(WebElement element)
			{
				
				scrollIntoView(element);
				element.click();
				log.info("element is clicked: "+element.toString());
			}
			
			public void scrollDownVertically()
			{
				log.info("scrolling donw vertically");
				executeScript("window.scrollTo(0,document.body.scrollHeight)");
			}
			
			public void scrollUpVertically()
			{
				log.info("scrolling up vertically");
				executeScript("window.scrollTo(0,-document.body.scrollHeight)");
			}
			/**
			 * will scroll down till given pixel(eg-1500)
			 * @param pixel
			 */
			public void scrollDownByPixel(int pixel)
			{
				log.info("scrolling down vertically by :"+pixel);
				executeScript("window.scrollBY(0,"+pixel+")");
			}
			
			/**
			 * will scroll up till given pixel(eg-1500)
			 * @param pixel
			 */
			public void scrollUpByPixel(int pixel)
			{
				log.info("scrolling up vertically by :"+pixel);
				
				executeScript("window.scrollBY(0,-"+pixel+")");
			}
			/**
			 * will zoom by 100 percent
			 */
			public void zoomBy100Percent() {
				log.info("zooming in by100 percent");
				executeScript("document.body.style.zoom='100%'");
			}
			
			/**
			 * will zoom by 60 percent
			 */
			public void zoomBy60Percent() {
				log.info("zooming in by60 percent");
				executeScript("document.body.style.zoom='60%'");
			}
			
			/**
			 * will click on element
			 * @param element
			 */
			public void clickElement(WebElement element) {
				executeScript("arguments[0].click();", element);
			}
}
