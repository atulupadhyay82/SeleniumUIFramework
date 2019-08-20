package com.uiFramework.companyName.projectName.helper.select;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import com.uiFramework.companyName.projectName.helper.logger.LoggerHelper;

/**
 * When ever we work with any UI applications, there are lot many select box. For this, we need separate class as a part of framework
 * @author atupadhy
 *
 */

public class DropdownHelper {
	
	// define logger class object
			private Logger log = LoggerHelper.getLogger(DropdownHelper.class);

			// Define WebDriver class object
			private WebDriver driver;

			// Calling class will supply WebDriver class object to this via the constructor.
			// Hence object defined private.
			public DropdownHelper(WebDriver driver) {
				this.driver = driver;
				log.info("DropdownHelper object is created..");
			}
			
			public void selectUsingValue(WebElement element,String value)
			{
				Select select=new Select(element);
				select.selectByValue(value);
				log.info("selectUsingValue and value is: "+value);
			}
			
			public void selectUsingIndex(WebElement element,int index)
			{
				Select select=new Select(element);
				select.selectByIndex(index);
				log.info("selectUsingIndex and index is: "+index);
			}
			public void selectUsingVisibleText(WebElement element,String visibletText)
			{
				Select select=new Select(element);
				select.selectByVisibleText(visibletText);
				log.info("selectUsingVisibleText and visible text is: "+visibletText);
			}
			
			public void deSelectUsingValue(WebElement element,String value)
			{
				Select select=new Select(element);
				select.deselectByValue(value);
				log.info("deSelectUsingValue and value is: "+value);
			}
			
			public void deSelectUsingIndex(WebElement element,int index)
			{
				Select select=new Select(element);
				select.deselectByIndex(index);
				log.info("deSelectUsingIndex and index is: "+index);
			}
			public void deSelectUsingVisibleText(WebElement element,String visibletText)
			{
				Select select=new Select(element);
				select.deselectByVisibleText(visibletText);
				log.info("deSelectUsingVisibleText and visible text is: "+visibletText);
			}
			
			/**
			 * will return the all dropdown data available as an options in select element
			 * @param element
			 * @return
			 */
			
			public List<String> getAllDropdownData(WebElement element)
			{
				Select select=new Select(element);
				//All options belonging to this select tag
				List<WebElement> elements = select.getOptions(); 
				List<String> valueList= new LinkedList<String>();
				for(WebElement els:elements)
				{
					log.info(els.getText());
					valueList.add(els.getText());
				}
				return valueList;
			}

}
