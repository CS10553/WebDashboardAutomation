
package com.automation.Driver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.log4j.Logger;

import com.automation.Utilities.loggerLoad;

/**
 * @author Automation Team 
 *
 */

public class DriverClass {
	
   public static WebDriver driver ;  
   static Logger log = loggerLoad.config("Driver");
  
   
   public  static WebDriver registerDriver(String name){
	   if (name.equalsIgnoreCase("chrome")){
		   driver = chromeDriver();
	   }else if (name.equalsIgnoreCase("firefox")){
		   driver = firefoxDriver();
	   }else if (name.equalsIgnoreCase("headless")){
		   driver = headless();
	   }
	   
	   return driver ;
   }
   
	public  static WebDriver chromeDriver() {
		try {
			
			String os = System.getProperty("os.name");			
			if(os.equalsIgnoreCase("linux")){
				//For Ubuntu machines, use the below line
				System.out.println("Running Test On Ubuntu Machine");
			//	String path = "/home/netstorm/Desktop/chromedriver";
				String path = System.getProperty("user.dir")+"/../framework/src/com/automation/Driver/chromedriver";
				System.setProperty("webdriver.chrome.driver", path);		
			}else{
				//For Windows machines use the following line
				System.out.println("Running Test On Window Machine");
				String path = System.getProperty("user.dir")+"/../framework/src/com/automation/Driver/chromedriver.exe";	
				System.setProperty("webdriver.chrome.driver", path);
			}
			
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			log.info("Chrome driver initialized");
		} catch (Exception e) {
			log.error("Error : " +e);
		}
		return driver;
	}
  
  public static WebDriver firefoxDriver() {
	 try {
		String path = System.getProperty("user.dir")+"/../framework/src/com/automation/Driver/geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", path);
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	  driver.manage().window().maximize();
	  log.info("Firefox driver initialized ");
	 }catch (Exception e) {
		log.error("Error : "+e);}
	  return driver;
    }
  
  public static WebDriver headless() {
	  try {
		  //For Windows machines use the following line
		  String path = System.getProperty("user.dir")+"/../framework/src/com/automation/Driver/chromedriver.exe";

		  //For Ubuntu machines, uncomment the below line
		  //String path = System.getProperty("user.dir")+"/../framework/src/com/automation/Driver/chromedriver";

		  System.setProperty("webdriver.chrome.driver", path);
		  //System.setProperty("webdriver.chrome.driver", "/home/netstorm/Desktop/chromedriver");

		 DesiredCapabilities caps = new DesiredCapabilities();
		  ChromeOptions chromeOptions = new ChromeOptions();
		  chromeOptions.addArguments("--headless"); // For head less feature we need chrome 65 and above version
		  chromeOptions.addArguments("--no-sandbox"); // chromedriver should be 2.35 or latest
		  chromeOptions.addArguments("--disable-gpu");
		  chromeOptions.addArguments("--ignore-certificate-errors");
		  chromeOptions.addArguments("--window-size=1325x744");

		  caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		  caps.setCapability("acceptInsecureCert", true);
		  caps.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

	      driver = new ChromeDriver(caps);
          log.info("Headless driver initiated");
	  }catch (Exception e) {
		log.error("Error : "+e);
	}
	  return driver ; 
  }
  
}
