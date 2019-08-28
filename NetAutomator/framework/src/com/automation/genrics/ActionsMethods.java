package com.automation.genrics;

import java.io.File;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.Driver.DriverClass;
import com.automation.TestCaseRunner.TestCaseDriveClass;
import com.automation.Utilities.loggerLoad;

/**
 * @author Automation Team 
 *
 */

public class ActionsMethods {

	public static WebDriver driver;
	public WebElement ele;
	public List<WebElement> eles;
	public Select sl;
	static DriverClass driverclass = new DriverClass();
	static Logger log = loggerLoad.config("ActionsMethods");
	JavascriptExecutor js = (JavascriptExecutor) driver;
    public static Actions action;	
    public static String ActualText;
	public static void registerDriver(String browser , File img_dest) {
	
		log.info("browser name : " + browser);
		driver = DriverClass.registerDriver(browser);
		action = new Actions(driver); 
		log.info("driver : " + driver);

	}

	public void waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver wdriver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		});
	}

	public void browse(String url ,String  image_name){
	 try{	
		log.info(String.format("%s going to open", url));
		driver.get(url);
		waitForPageLoad();
		screenCapture(image_name);
	 }catch(Exception e ){ screenCapture(image_name);log.error("Error : ", e); }
	}
	
	public void navigateTo(String url , String  image_name){
		try{
			log.info(String.format("%s going to navigate", url));
			driver.navigate().to(url);
			waitForPageLoad();
			screenCapture(image_name);
		}catch(Exception e){  screenCapture(image_name);log.error("Error : ", e);}
	}

	public void closebrowser() throws Exception {
		log.info(String.format("%s going to close browser", driver));
		driver.close();
	}

	public void quitbrowser() {
		log.info(String.format("%s going to quit browser", driver));
		driver.quit();
	}

	public void click(String identifier, String identifiervalue , String image_name) {
		try {
			switch (identifier) {
			case "ByName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.name(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			case "ByXpath":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.xpath(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			case "ById":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.id(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			case "ByClassName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.className(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			case "ByTagName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.tagName(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			case "ByLinkText":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.linkText(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			case "ByPartialLinkText":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By
								.partialLinkText(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			case "ByCssSelector":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.cssSelector(identifiervalue)));
				ele.click();
				log.info(String.format("sucessfully click on %s with value %s",
						identifier, identifiervalue));
				screenCapture(image_name);
				break;
				
			default:
				log.error(String.format("%s is not a valid identifier",
						identifier));
				break;
			}
			waitForPageLoad();
		} catch (Exception e) {screenCapture(image_name);log.error("Error : ", e);
		}
	}

	public void sendKeys(String identifier, String identifiervalue,String values , String image_name) {
		try {
			switch (identifier) {
			case "ByName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.name(identifiervalue)));
                                ele.clear();
				ele.sendKeys(values);
				log.info(String.format("sucessfully SendKeys on %s with value %s",identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByXpath":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.xpath(identifiervalue)));
                                ele.clear();
				ele.sendKeys(values);
				log.info(String.format(
						"sucessfully SendKeys on %s with value %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ById":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.id(identifiervalue)));
                                ele.clear();
				ele.sendKeys(values);
				log.info(String.format(
						"sucessfully SendKeys on %s with value %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByClassName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.className(identifiervalue)));
                                ele.clear();
				ele.sendKeys(values);
				log.info(String.format(
						"sucessfully SendKeys on %s with value %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByTagName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.tagName(identifiervalue)));
				ele.clear();
				ele.sendKeys(values);
				log.info(String.format("sucessfully SendKeys on %s with value %s",identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByLinkText":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.linkText(identifiervalue)));
				ele.clear();
				ele.sendKeys(values);
				log.info(String.format(
						"sucessfully SendKeys on %s with value %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByPartialLinkText":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By
								.partialLinkText(identifiervalue)));
				ele.clear();
				ele.sendKeys(values);
				log.info(String.format(
						"sucessfully SendKeys on %s with value %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByCssSelector":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.cssSelector(identifiervalue)));
				ele.clear();
				ele.sendKeys(values);
				log.info(String.format(
						"sucessfully SendKeys on %s with value %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			default:
				log.error(String.format("%s is not a valid identifier",
						identifier));
				break;
			}
		} catch (Exception e) {screenCapture(image_name);
			log.error("Error : ", e);
		}

	}
    
	
	public void waitforVisiblity(String identifier, String identifiervalue, String values , String image_name) {
		try {
			switch (identifier) {
			case "ByName":
				new WebDriverWait(driver, Integer.parseInt(values))
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.name(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with value %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByXpath":
				new WebDriverWait(driver, Integer.parseInt(values))
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.xpath(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with time %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ById":
				new WebDriverWait(driver, Integer.parseInt(values))
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.id(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with time %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByClassName":
				new WebDriverWait(driver, Integer.parseInt(values))
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.className(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with time %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByTagName":
				new WebDriverWait(driver, Integer.parseInt(values))
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.tagName(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with time %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByLinkText":
				new WebDriverWait(driver, Integer.parseInt(values))
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.linkText(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with time %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByPartialLinkText":
				new WebDriverWait(driver, Integer.parseInt(values))
						.until(ExpectedConditions.visibilityOfElementLocated(By
								.partialLinkText(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with time %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			case "ByCssSelector":
				new WebDriverWait(driver, 120).until(ExpectedConditions
						.visibilityOfElementLocated(By
								.cssSelector(identifiervalue)));
				log.info(String.format(
						"sucessfully element visible on %s with time %s",
						identifiervalue, values));
				screenCapture(image_name);
				break;
				
			default:
				log.error(String.format("%s is not a valid identifier",
						identifier));
				break;
			}

		} catch (Exception e) {screenCapture(image_name);
			log.error("Error : ", e);
		}
	}

	public void screenCapture(String img_name) {
		try {
			Thread.sleep(3000);
			String destpath = TestCaseDriveClass.Imagedest_path+"//"+img_name+".png";
			System.out.println("destpath :"+destpath);
			TakesScreenshot tc = (TakesScreenshot) driver;
			File dest = new File(destpath);
			File src = tc.getScreenshotAs(OutputType.FILE);
			FileUtils.moveFile(src, dest);
			log.info(String.format("Screen Capture %s" , destpath));
		} catch (Exception e) {
			log.error(e.fillInStackTrace());
			log.error("Error in capture screenshot");
		}
	}
	
	public void scrollonpage(String cordinate, String image_name){
		try {
			js.executeScript("window.scrollBy("+cordinate+")");
			log.info(String.format("Page scrolled by %s",cordinate ));
			screenCapture(image_name);
		} catch (Exception e) {screenCapture(image_name);
			log.error("Error : " , e);
		}
	}
	public java.util.Date getCurrentDateTime(){
	
			java.util.Date date=java.util.Calendar.getInstance().getTime();  
			
			System.out.println(date);
			return date;
			//screenCapture(image_name);
		
	}
	
	public boolean validatetitle(String title, String image_name){
			boolean data = driver.getTitle().equals(title);
			log.info(String.format("Page Title %s",driver.getTitle()));
			screenCapture(image_name);
		return data;
	}
	
	public boolean verifycontentonpage(String content , String image_name){
		boolean data = driver.getPageSource().equals(content);
		log.info(String.format("Page Content exists %s",data));
		screenCapture(image_name);
	return data;
	}
	
	public boolean alertaccept(String image_name){		
	
		try{
		Alert alt = new WebDriverWait(driver, 60).until(ExpectedConditions.alertIsPresent());
		alt.accept();
		screenCapture(image_name);
		return true;
		}catch(Exception e ){ log.error("Alert not Present");
		screenCapture(image_name);
		return false;
		}
	}
	
	
	public boolean alertdecline(String image_name){
		try{
		Alert alt = new WebDriverWait(driver, 60).until(ExpectedConditions.alertIsPresent());
		alt.dismiss();
		screenCapture(image_name);
		return true;
		}catch(Exception e ){ log.error("Alert not Present");
		screenCapture(image_name);
		return false;
		}
	}
	public boolean alertsendkeys(String data , String image_name){
		try{
			Alert alt = new WebDriverWait(driver, 60).until(ExpectedConditions.alertIsPresent());
			alt.sendKeys(data);
			screenCapture(image_name);
			return true;
			}catch(Exception e ){ log.error("Alert not Present");
			screenCapture(image_name);
			return false;
			}
	}
	
	public boolean getwindowhandles(String image_name){
		try{
		     Set<String> windows = driver.getWindowHandles();
		     for(String window : windows){
		    	 driver.switchTo().window(window);
		     }
		     screenCapture(image_name);
		     return true;
		}catch(Exception e ){ screenCapture(image_name); log.error("Error in switch on latest window");return false;}
	}
	
	public boolean gotowindowhandlebyindex(int index, String image_name){
		try{
			Set<String> windows = driver.getWindowHandles();
			System.out.println("windows : "+windows);
			System.out.println("window 1 : "+(String)windows.toArray()[index]);
			driver.switchTo().window((String)windows.toArray()[index]);
			screenCapture(image_name);
		  return true;
		}catch(Exception e){ screenCapture(image_name); log.error("Error in switch on latest window");return false; }
	}
	
	public boolean frameswitch(String identifier , String identifierValue , String image_name){
		try{
			//driver.switchTo().frame(framename);
			driver.switchTo().frame(driver.findElement(By.xpath(identifierValue)));
			screenCapture(image_name);
		  return true;
		}catch(Exception e){ log.error("Error in switch on frame");
		screenCapture(image_name);
		return false; }
	}
	
	public boolean selectfromcheckbox(String identifier, String identifiervalue, String image_name){
		try {
			
			switch (identifier) {
			case "ByName":
				 ele = driver.findElement(By.name(identifiervalue));
				 if(! ele.isSelected())
					 ele.click();
				 screenCapture(image_name);
				return true;
			case "ByXpath":
			    ele = driver.findElement(By.xpath(image_name));
			    if(!ele.isSelected())
			    	ele.click();
			    screenCapture(image_name);
				return true;
								
			case "ById":
				ele = driver.findElement(By.id(identifiervalue));
				if(! ele.isSelected())
					ele.click();
				screenCapture(image_name);
				return true;
				
			case "ByClassName":
				ele  = driver.findElement(By.cssSelector(identifiervalue));
				if(! ele.isSelected())
					ele.click();
				screenCapture(image_name);
				return true;
				
			case "ByTagName":
				ele = driver.findElement(By.tagName(identifiervalue));
				if(! ele.isSelected())
					ele.click();
				screenCapture(image_name);
				return true;
				
			case "ByLinkText":
				ele = driver.findElement(By.linkText(identifiervalue));
				if(! ele.isSelected())
					ele.click();
				screenCapture(image_name);
				return true;
				
			case "ByPartialLinkText":
				ele = driver.findElement(By.partialLinkText(identifiervalue));
				if(! ele.isSelected())
					ele.click();
				screenCapture(image_name);
				return true;
				
			case "ByCssSelector":
				ele = driver.findElement(By.cssSelector(identifiervalue));
				if(! ele.isSelected())
					ele.click();
				screenCapture(image_name);
				return true;
			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				return false;
			}
			
		} catch (Exception e) {screenCapture(image_name);
			log.error("Error : ", e);
			return false;
		}
		
		
	}
	
	public boolean verifycontentforelement(String identifier, String identifiervalue,String content , String image_name){
		try {
			boolean data = false;
			
			switch (identifier) {
			
			case "ByName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.name(identifiervalue)));
				data = ele.getText().toString().equals(content);	
				screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				return data;
				
			case "ByXpath":
			    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.xpath(identifiervalue)));
			    data = ele.getText().toString().equals(content);
			    screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				
				return data;
								
			case "ById":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.id(identifiervalue)));
				data = ele.getText().toString().equals(content);
				screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				return data;
				
			case "ByClassName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.className(identifiervalue)));
				data = ele.getText().toString().equals(content);
				screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				return data;
				
			case "ByTagName":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.tagName(identifiervalue)));
				data = ele.getText().toString().equals(content);
				screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				return data;
				
			case "ByLinkText":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions
						.elementToBeClickable(By.linkText(identifiervalue)));
				data = ele.getText().toString().equals(content);
				screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				return data;
				
			case "ByPartialLinkText":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.partialLinkText(identifiervalue)));
				data = ele.getText().toString().equals(content);
				screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				return data;
				
			case "ByCssSelector":
				ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.cssSelector(identifiervalue)));
				data = ele.getText().toString().equals(content);
				screenCapture(image_name);
				if(data)
				{
					log.info(String.format("sucessfully validate content for element with %s content",content));
				}
				else
				{
					log.error(String.format("Verfication unsuccessfull as Expected - "+content + " and Actual conetnt -" + ele.getText().toString()+" does not match"));
				}
				return data;
				
			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				screenCapture(image_name);
				break;
			}
			waitForPageLoad();
		} catch (Exception e) {screenCapture(image_name);
			log.error("Error : ", e);
		}
		return false;
		
	}
	
	public boolean selectvaluefromlist(String identifier, String identifiervalue,String value, String image_name){
         try {	
			switch (identifier) {
			case "ByName":
				 eles = driver.findElements(By.name(identifiervalue));
                 for(int i=0; i <eles.size(); i++){
                	 if(eles.get(i).getText().toString().contains(value)){
                		 eles.get(i).click();
                	 }
                 }
				 screenCapture(image_name);
				return true;
				
			case "ByXpath":
				 eles = driver.findElements(By.xpath(identifiervalue));
                 for(int i=0; i <eles.size(); i++){
                	 if(eles.get(i).getText().toString().contains(value)){
                		 eles.get(i).click();
                	 }
                 }
			    screenCapture(image_name);
				return true;
								
			case "ById":
				eles = driver.findElements(By.id(identifiervalue));
                for(int i=0; i <eles.size(); i++){
               	 if(eles.get(i).getText().toString().contains(value)){
               		 eles.get(i).click();
               	 }
                }
			    screenCapture(image_name);
				return true;
				
			case "ByClassName":
				eles = driver.findElements(By.className(identifiervalue));
                for(int i=0; i <eles.size(); i++){
               	 if(eles.get(i).getText().toString().contains(value)){
               		 eles.get(i).click();
               	 }
                }
			    screenCapture(image_name);
				return true;
				
			case "ByTagName":
				eles = driver.findElements(By.tagName(identifiervalue));
                for(int i=0; i <eles.size(); i++){
               	 if(eles.get(i).getText().toString().contains(value)){
               		 eles.get(i).click();
               	 }
                }
			    screenCapture(image_name);
				return true;
				
			case "ByLinkText":
				eles = driver.findElements(By.linkText(identifiervalue));
                for(int i=0; i <eles.size(); i++){
               	 if(eles.get(i).getText().toString().contains(value)){
               		 eles.get(i).click();
               	 }
                }
			    screenCapture(image_name);
				return true;
				
			case "ByPartialLinkText":
				eles = driver.findElements(By.partialLinkText(identifiervalue));
                for(int i=0; i <eles.size(); i++){
               	 if(eles.get(i).getText().toString().contains(value)){
               		 eles.get(i).click();
               	 }
                }
			    screenCapture(image_name);
				return true;
				
			case "ByCssSelector":
				eles = driver.findElements(By.cssSelector(identifiervalue));
                for(int i=0; i <eles.size(); i++){
               	 if(eles.get(i).getText().toString().contains(value)){
               		 eles.get(i).click();
               	 }
                }
				 screenCapture(image_name);
				return true;
			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				return false;
			}
			
		} catch (Exception e) {screenCapture(image_name);
			log.error("Error : ", e);
			return false;
		}
	}
	
	public boolean selectfromdropdownbyindex(String identifier, String identifiervalue,String value, String image_name){
		  try {	
				switch (identifier) {
				case "ByName":
					 eles = driver.findElements(By.name(identifiervalue));
	                 eles.get(Integer.parseInt(value)).click();
					 screenCapture(image_name);
					return true;
					
				case "ByXpath":
					 eles = driver.findElements(By.xpath(identifiervalue));
					 eles.get(Integer.parseInt(value)).click();
				    screenCapture(image_name);
					return true;
									
				case "ById":
					eles = driver.findElements(By.id(identifiervalue));
					eles.get(Integer.parseInt(value)).click();
				    screenCapture(image_name);
					return true;
					
				case "ByClassName":
					eles = driver.findElements(By.className(identifiervalue));
					eles.get(Integer.parseInt(value)).click();
				    screenCapture(image_name);
					return true;
					
				case "ByTagName":
					eles = driver.findElements(By.tagName(identifiervalue));
					eles.get(Integer.parseInt(value)).click();
				    screenCapture(image_name);
					return true;
					
				case "ByLinkText":
					eles = driver.findElements(By.linkText(identifiervalue));
					eles.get(Integer.parseInt(value)).click();
				    screenCapture(image_name);
					return true;
					
				case "ByPartialLinkText":
					eles = driver.findElements(By.partialLinkText(identifiervalue));
					eles.get(Integer.parseInt(value)).click();
				    screenCapture(image_name);
					return true;
					
				case "ByCssSelector":
					eles = driver.findElements(By.cssSelector(identifiervalue));
					eles.get(Integer.parseInt(value)).click();
					screenCapture(image_name);
					return true;
				default:
					log.error(String.format("%s is not a valid identifier",identifier));
					return false;
				}
				
			} catch (Exception e) {
				screenCapture(image_name);
				log.error("Error : ", e);
				return false;
			}
	}
	public boolean selectdatafromvisibletext(String identifier, String identifiervalue,String value, String image_name){
		try {	
			
			switch (identifier) {
			case "ByName":
			     sl = new Select(driver.findElement(By.name(identifiervalue)));
				 sl.selectByVisibleText(value);
				 screenCapture(image_name);
				return true;
				
			case "ByXpath":
				 sl = new Select(driver.findElement(By.xpath(identifiervalue)));
				 sl.selectByVisibleText(value);
				 screenCapture(image_name);
				return true;
								
			case "ById":
				 sl = new Select(driver.findElement(By.id(identifiervalue)));
				 sl.selectByVisibleText(value);
			    screenCapture(image_name);
				return true;
				
			case "ByClassName":
				sl = new Select(driver.findElement(By.className(identifiervalue)));
				 sl.selectByVisibleText(value);
			    screenCapture(image_name);
				return true;
				
			case "ByTagName":
				 sl = new Select(driver.findElement(By.tagName(identifiervalue)));
				 sl.selectByVisibleText(value);
			    screenCapture(image_name);
				return true;
				
			case "ByLinkText":
				 sl = new Select(driver.findElement(By.linkText(identifiervalue)));
				 sl.selectByVisibleText(value);
			    screenCapture(image_name);
				return true;
				
			case "ByPartialLinkText":
				 sl = new Select(driver.findElement(By.partialLinkText(identifiervalue)));
				 sl.selectByVisibleText(value);
			    screenCapture(image_name);
				return true;
				
			case "ByCssSelector":
				sl = new Select(driver.findElement(By.cssSelector(identifiervalue)));
				 sl.selectByVisibleText(value);
				 screenCapture(image_name);
				return true;
			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				return false;
			}
			
		} catch (Exception e) {screenCapture(image_name);
			log.error("Error : ", e);
			return false;
		}
	}

	public boolean uploadfile(String identifier, String identifiervalue,String value, String image_name) {
        try {

            switch (identifier) {
                case "ByName":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.name(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                case "ByXpath":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.xpath(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                case "ById":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.id(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                case "ByClassName":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.className(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                case "ByTagName":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.tagName(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                case "ByLinkText":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.linkText(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                case "ByPartialLinkText":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.partialLinkText(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                case "ByCssSelector":
                    ele = new WebDriverWait(driver, 120).until(ExpectedConditions.elementToBeClickable(By.cssSelector(identifiervalue)));
                    ele.clear();
                    ele.sendKeys(value);
                    screenCapture(image_name);
                    return true;

                default:
                    log.error(String.format("%s is not a valid identifier", identifier));
                    return false;
            }

        } catch (Exception e) {
            screenCapture(image_name);
            log.error("Error : ", e);
            return false;
        }
    }
		public void doubleClick(String identifier, String identifierValue, String image_name)  
	{
		try {
			switch (identifier) 
			{
			case "ByName":
				ele = driver.findElement(By.name(identifierValue));	  
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByXpath":
				ele = driver.findElement(By.xpath(identifierValue));	
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ById":
				ele = driver.findElement(By.id(identifierValue));	
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByClassName":
				ele = driver.findElement(By.className(identifierValue));	
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByTagName":
				ele = driver.findElement(By.tagName(identifierValue));	
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByLinkText":
				ele = driver.findElement(By.linkText(identifierValue));	
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByPartialLinkText":
				ele = driver.findElement(By.partialLinkText(identifierValue));	
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByCssSelector":
				ele = driver.findElement(By.cssSelector(identifierValue));	
				action.doubleClick(ele).perform();
				log.info(String.format("sucessfully double click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				break;
			}
		}
		catch(Exception e)
		{
			screenCapture(image_name);
			log.error("Error : ", e);
		}
		
	}


		public void mouseHover(String identifier, String identifierValue, String image_name)  
	{
		try {
			switch (identifier) 
			{
			case "ByName":
				ele = driver.findElement(By.name(identifierValue));	  
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByXpath":
				ele = driver.findElement(By.xpath(identifierValue));	
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ById":
				ele = driver.findElement(By.id(identifierValue));	
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByClassName":
				ele = driver.findElement(By.className(identifierValue));	
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByTagName":
				ele = driver.findElement(By.tagName(identifierValue));	
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByLinkText":
				ele = driver.findElement(By.linkText(identifierValue));	
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByPartialLinkText":
				ele = driver.findElement(By.partialLinkText(identifierValue));	
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByCssSelector":
				ele = driver.findElement(By.cssSelector(identifierValue));	
				action.moveToElement(ele).build().perform();
				log.info(String.format("sucessfully moved to the element on %s ",identifierValue));
				screenCapture(image_name);
				break;

			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				break;
			}
		}
		catch(Exception e)
		{
			screenCapture(image_name);
			log.error("Error : ", e);
		}
		
	}




		public void rightClick(String identifier, String identifierValue, String image_name)  
	{
		try {
			switch (identifier) 
			{
			case "ByName":
				ele = driver.findElement(By.name(identifierValue));	  
				action.contextClick(ele).build().perform();
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByXpath":
				ele = driver.findElement(By.xpath(identifierValue));
				action.contextClick(ele).build().perform();	
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ById":
				ele = driver.findElement(By.id(identifierValue));	
				action.contextClick(ele).build().perform();
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByClassName":
				ele = driver.findElement(By.className(identifierValue));	
				action.contextClick(ele).build().perform();
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByTagName":
				ele = driver.findElement(By.tagName(identifierValue));	
				action.contextClick(ele).build().perform();
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByLinkText":
				ele = driver.findElement(By.linkText(identifierValue));	
				action.contextClick(ele).build().perform();
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByPartialLinkText":
				ele = driver.findElement(By.partialLinkText(identifierValue));	
				action.contextClick(ele).build().perform();
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			case "ByCssSelector":
				ele = driver.findElement(By.cssSelector(identifierValue));	
				action.contextClick(ele).build().perform();
				log.info(String.format("sucessfully right click on %s ",identifierValue));
				screenCapture(image_name);
				break;

			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				break;
			}
		}
		catch(Exception e)
		{
			screenCapture(image_name);
			log.error("Error : ", e);
		}
		
	}


            public void scrollforelement(String identifier, String identifiervalue, String image_name) {
		try {
			switch (identifier) {
			case "ByName":
				    ele = driver.findElement(By.name(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
				break;
				
			case "ByXpath":
				    ele = driver.findElement(By.xpath(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
				break;
				
			case "ById":
				    ele = driver.findElement(By.id(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
				 break;
				 
			case "ByClassName":
				    ele = driver.findElement(By.className(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
				 break;
			
			case "ByTagName":
				    ele = driver.findElement(By.tagName(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
				break;
			
			case "ByLinkText":
				    ele = driver.findElement(By.linkText(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
			   break;
			   
			case "ByPartialLinkText":
				    ele = driver.findElement(By.partialLinkText(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
			   break;
			   
			case "ByCssSelector":
				    ele = driver.findElement(By.cssSelector(identifiervalue));	
			        js.executeScript("arguments[0].scrollIntoView();", ele);
				    log.info(String.format("sucessfully scroll on %s ",identifiervalue));
				    screenCapture(image_name);
			  break;
				    
			default:
				log.error(String.format("%s is not a valid identifier",identifier));
				break;
			}
		}catch(Exception e){
			screenCapture(image_name);
			log.error("Error : ", e);
		}
		
	}
}
