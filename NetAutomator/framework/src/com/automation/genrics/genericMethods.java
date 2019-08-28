package com.automation.genrics;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.automation.TestCaseRunner.TestCaseDataReader;
import com.automation.Utilities.ReportGenrator;
import com.automation.Utilities.loggerLoad;
import com.aventstack.extentreports.ExtentTest;

public class genericMethods 
{
	 static Logger log = loggerLoad.config("genericMethods");
	 ActionsMethods act=new ActionsMethods();
	 ReportGenrator olog = new ReportGenrator();
     ExtentTest test;
	 ArrayList<String> al = new ArrayList<>();
		public  String TestCaseID ;
		public   String Description ;
		public   String Actions ;
		public  String identifier;
		public  String identifierValue ;
		public  String parameter; 
		int index ;
		long time;
		public String iteration;
		public static TestCaseDataReader tcr;
	@SuppressWarnings("static-access")
	public boolean defMthd(int i,String excelpath,String keywords,File img_dest,ExtentTest test)
	{   
		try
		{		
			switch (keywords) {
			case "":
				log.error("Keywords should not be blank");
				act.quitbrowser();
				olog.errorlog(test, "Keywords should not be blank");
				System.exit(0);
				
			case "browse":	
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
				log.info(String.format("Going to browse url - %s", TestCaseID));
			    Description = tcr.sheet.getRow(i).getCell(1).toString();
			    log.info(String.format("Going to browse url - %s", Description));
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
			    log.info(String.format("Going to browse url - %s", parameter));
			    iteration = tcr.sheet.getRow(i).getCell(6).toString();
			    if(iteration.equalsIgnoreCase("yes") && !iteration.equalsIgnoreCase("NA") || iteration.equals("") ){
			    	
			    	al = tcr.getData(excelpath , parameter);
			    	for(String url : al){
			    		act.browse(url , TestCaseID+"_"+url);
			    		log.info(String.format("Going to browse url - %s", url));
			    		olog.passlog(test, Description+" "+url);
			    	}   	
			    	
			    }else{
			    	
			    	act.browse(parameter, TestCaseID);	
			    	olog.passlog(test, Description+" "+parameter);
			    }
				break;
				
			case "click":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    Description = tcr.sheet.getRow(i).getCell(1).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    act.click(identifier, identifierValue , TestCaseID);
			    olog.passlog(test, Description+ " By "+identifier+" value "+identifierValue);
				break;
			
			case "sendkeys":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    Description = tcr.sheet.getRow(i).getCell(1).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
			    iteration = tcr.sheet.getRow(i).getCell(6).toString();
				
			    if(iteration.equalsIgnoreCase("yes") && !iteration.equalsIgnoreCase("NA") || iteration.equals("") ){
			    	al = tcr.getData(excelpath , parameter);
			    	
			    	for(String param : al){
			    		act.sendKeys(identifier, identifierValue , param , TestCaseID+"_"+param);
			    		log.info(String.format("Going to send input data as - %s", param));
			    		olog.passlog(test, Description+ " By "+identifier+" value "+identifierValue+ " parameter "+param);
			    	}
			    }
			    	else{		
			    	act.sendKeys(identifier, identifierValue , parameter , TestCaseID);
			    	olog.passlog(test, Description+ " By "+identifier+" value "+identifierValue+ " parameter "+parameter);
			    	}
			break;
				
			case "waitforVisiblity":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    Description = tcr.sheet.getRow(i).getCell(1).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				
			    if(parameter.equals(""))
			    	parameter = "120";
			    act.waitforVisiblity(identifier, identifierValue , parameter , TestCaseID);
			    olog.passlog(test, Description+ " By "+identifier+" value "+identifierValue+ " parameter "+parameter);
				break;
				
			case "sleep":
				 Description = tcr.sheet.getRow(i).getCell(1).toString();
				 parameter = tcr.sheet.getRow(i).getCell(5).toString();
			  int time1= Integer.parseInt(parameter);
			  long time=time1;
			    if(parameter.equals(""))
			    	 time=5000;
			    
			    log.info(String.format("Test execution stopped for %d milliseconds",time));
			    Thread.sleep(time);				 
			    olog.passlog(test, Description+" parameter "+parameter);
				break;
			
			case "scrollonpage":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    Description = tcr.sheet.getRow(i).getCell(1).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				act.scrollonpage(parameter, TestCaseID);
				log.info(String.format("Going to scroll %s on page", parameter));
				olog.passlog(test, Description+ " parameter "+parameter);
				break;
			
			/*case "scrollforelement":
			 * TestCaseID = sheet.getRow(i).getCell(0).toString();
				    Description = sheet.getRow(i).getCell(1).toString();
				    identifier = sheet.getRow(i).getCell(3).toString();
				    identifierValue = sheet.getRow(i).getCell(4).toString();
				act.scrollforelement(identifier, identifierValue , TestCaseID);
	     		log.info(String.format("Going to scroll %s on page", identifierValue));
	     		olog.passlog(Description+ " By "+identifier+" value "+identifierValue);
				break;*/
			
				
			case "validatetitle":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				
				log.info(String.format("Going to validate title on page as - %s", parameter));
				boolean title = act.validatetitle(parameter, TestCaseID);
				if(title)
					olog.passlog(test, String.format("Title validateion on page done successfully. Title was - %s", parameter));
				else
					olog.faillog(test, String.format("Title validateion on page failed. Title was - %s", parameter));
				
				break;
			
			case "verifycontentonpage":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
			  
				log.info(String.format("Going to verify Content on page %s", parameter));
				boolean contentverify = act.verifycontentonpage(parameter, TestCaseID);
				if(contentverify)
					olog.passlog(test, String.format("Content on page - %s, is verified successfully", parameter));
				else
					olog.faillog(test, String.format("Content on page - %s, failed on verification", parameter));
				break;
				
			case "verifycontentforelement":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				log.info(String.format("Going to verify Content on page %s", parameter));
				boolean contentverifyforElement = act.verifycontentforelement(identifier , identifierValue , parameter, TestCaseID);
				if(contentverifyforElement)
					olog.passlog(test, String.format("Element content - %s, is verified successfully", parameter));
				else
					olog.faillog(test, String.format("Element content - %s, failed on verification", parameter));
				break;
			
				
			case "alertaccept":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
				log.info("Going to accept alert on Current page");
				boolean alert = act.alertaccept(TestCaseID);
				if(alert)
					olog.passlog(test, String.format("Alert accepted successfully on current page"));
				else
					olog.faillog(test, String.format("Alert acceptance failed on current page"));
				break;

			case "alertsendkeys":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
				 parameter = tcr.sheet.getRow(i).getCell(5).toString();			
				log.info("Going to accept alert on Current page");
				boolean alertsend = act.alertsendkeys(parameter , TestCaseID);
				if(alertsend)
					olog.passlog(test, String.format("Input - %s sent during alert is successful" , parameter));
				else
					olog.faillog(test, String.format("Input - %s sent durig alert failed" , parameter));
				break;
			
			
			case "alertdecline":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
				log.info("Going to accept alert on Current page");
				boolean alertdec = act.alertdecline(TestCaseID);
				if(alertdec)
					olog.passlog(test, String.format("Alert dismissal successful"));
				else
					olog.faillog(test, String.format("Alert dismissal failed"));
				break;
			
			case "navigateto":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    Description = tcr.sheet.getRow(i).getCell(1).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				 if(iteration.equalsIgnoreCase("yes") && !iteration.equalsIgnoreCase("NA") || iteration.equals("") ){
				    	al = tcr.getData(excelpath , parameter);
				    	for(String url : al){
				    		act.navigateTo(url , TestCaseID+"_"+url);
				    		log.info(String.format("Navigating to the following url - %s", url));
				    		olog.passlog(test, Description);
				    	}   	
				 }else{
				    	act.navigateTo(parameter, TestCaseID);	
				    	olog.passlog(test, Description);
				 }
			  break;
				
			
			case "getwindowhandles":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
				log.info("Going to switch on latest window");
				boolean getWindowHandles = act.getwindowhandles(TestCaseID);
				if(getWindowHandles)
					olog.passlog(test, String.format("Switch to latest window successful"));
				else
					olog.faillog(test, String.format("Switch to latest window failed"));
				break;
			
			case "gotowindowhandlebyindex":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
				int index = (int) tcr.sheet.getRow(i).getCell(5).getNumericCellValue();
			   log.info(String.format("Going to switch on window with index %s",parameter));
			  // int index =Integer.parseInt(parameter);
			   boolean getwindowHandleIndex = act.gotowindowhandlebyindex(index , TestCaseID);
				if(getwindowHandleIndex)
					olog.passlog(test, String.format("Switch on window with index %s successful",parameter));
				else
					olog.faillog(test, String.format("Switch on window with index %s failed",parameter));
				break;
				
			case "frameswitch":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
		        identifier = tcr.sheet.getRow(i).getCell(3).toString();
		        identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
				log.info(String.format("Going to switch on window with identifier %s , value %s",identifier,identifierValue));
				boolean switchframe = act.frameswitch(identifier,identifierValue, TestCaseID);
				if(switchframe)
					olog.passlog(test, String.format("Switch frame on %s successful",parameter));
				else
					olog.faillog(test, String.format("Switch frame on %s failed",parameter));
				break;
			
			case "selectcheckboxorradiobutton":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				log.info(String.format("Going to select check box and Radio button"));
				boolean selectcheckbox = act.selectfromcheckbox(identifier , identifierValue , TestCaseID );
				if(selectcheckbox)
					olog.passlog(test, String.format("Switch frame on %s successful",parameter));
				else
					olog.faillog(test, String.format("Switch frame on %s failed",parameter));
				break;

			case "rightclick":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
				log.info(String.format("Going to perform right click"));
				act.rightClick(identifier , identifierValue , TestCaseID );
				log.info(String.format("Going to right click  %s on page", identifierValue));
				olog.passlog(test, Description+ " By "+identifier+" value "+identifierValue);
				break;

			case "mousehover":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
				log.info(String.format("Going to perform mouse hover"));
				act.mouseHover(identifier , identifierValue , TestCaseID );
				log.info(String.format("Going to do mouse hover  %s on page", identifierValue));
				olog.passlog(test, Description+ " By "+identifier+" value "+identifierValue);
				break;


			case "doubleClick":
				 TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
					Description = tcr.sheet.getRow(i).getCell(1).toString();
					identifier = tcr.sheet.getRow(i).getCell(3).toString();
					identifierValue = tcr.sheet.getRow(i).getCell(4).toString();	    
				 act.doubleClick(identifier, identifierValue , TestCaseID);
				 log.info(String.format("Going to perform doubleClick %s on page", identifierValue));
				 olog.passlog(test, Description+ " By "+identifier+" value "+identifierValue);
				 break;

					
				
			
			case "selectvaluefromlist":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				log.info(String.format("Going to select data from list data is %s", parameter ));
				boolean selectvaluefromlst = act.selectvaluefromlist(identifier , identifierValue , parameter , TestCaseID );
				if(selectvaluefromlst)
					olog.passlog(test, String.format("Selecting data from list %s successful",parameter));
				else
					olog.faillog(test, String.format("Selecting data from list %s failed",parameter));
				break;
				
			case "selectfromdropdownbyvisibletext":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				log.info(String.format("Going to select data from drop down list data - %s", parameter ));
				boolean selectdatafromvisibletext = act.selectdatafromvisibletext(identifier , identifierValue , parameter , TestCaseID );
				if(selectdatafromvisibletext)
					olog.passlog(test, String.format("Select data from drop down list %s successful",parameter));
				else
					olog.faillog(test, String.format("Select data from drop down list %s failed",parameter));
				break;
			
			case "selectfromdropdownbyindex":
				TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
			    identifier = tcr.sheet.getRow(i).getCell(3).toString();
			    identifierValue = tcr.sheet.getRow(i).getCell(4).toString();
			    parameter = tcr.sheet.getRow(i).getCell(5).toString();
				log.info(String.format("Going to select data from drop down list data - %s", parameter ));
				boolean SelectFromDropDownByIndex = act.selectfromdropdownbyindex(identifier , identifierValue , parameter , TestCaseID );
				if(SelectFromDropDownByIndex)
					olog.passlog(test, String.format("Select data from drop down list with index %s successful",parameter));
				else
					olog.faillog(test, String.format("Select data from drop down list with index %s failed",parameter));
				break;
				
				case "uploadfile":
					TestCaseID = tcr.sheet.getRow(i).getCell(0).toString();
					identifier = tcr.sheet.getRow(i).getCell(3).toString();
					identifierValue =tcr.sheet.getRow(i).getCell(4).toString();
					parameter = tcr.sheet.getRow(i).getCell(5).toString();
				log.info(String.format("Going to upload the following file - %s", parameter ));
				if(parameter.equals("")) {
					olog.faillog(test, String.format("Parameter should not be blank in case of file upload"));
					break;
				}

				boolean uploadFile = act.uploadfile(identifier , identifierValue , parameter , TestCaseID );
				if(uploadFile)
					olog.passlog(test, String.format("File - %s uploaded successfully",parameter));
				else
					olog.faillog(test, String.format("Upload of file - %s has failed",parameter));
				break;
				

			case "closebrowser":
				act.closebrowser();
				olog.passlog(test, Description);
				break;
			
			case "quitbrowser":
				act.quitbrowser();
				olog.passlog(test, Description);
				break;
			}
			
			return true;
		}
		catch (Exception e)
		{
          e.printStackTrace();		
          act.quitbrowser();
		  log.error(String.format("Error in execution of %s test case. Please open TestNG report " , excelpath ));
		  log.error("Error are :  ",e);
		  olog.errorlog(test, e.toString());
		  return false;
			
		}
		
	}
	
}
