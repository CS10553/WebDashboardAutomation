package com.automation.Utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @author Automation Team 
 *
 */

public class loggerLoad {
   
	 public static  Logger config(String classname ){
		    Logger log = Logger.getLogger(classname);
		    String log_properties = System.getProperty("user.dir");
		    System.out.println(log_properties);
		    String log4jConfPath = log_properties+"/../framework/src/com/automation/config/log4j.properties";
		    PropertyConfigurator.configure(log4jConfPath);
		   return log;
	   }
}
