package com.automation.TestSuiteRunner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.automation.Utilities.SuiteDriveClass;
import com.automation.Utilities.Html_Merger;
import com.automation.Utilities.Html_file;
import com.automation.Utilities.loggerLoad;


/**
 * @author Automation Team 
 *
 */

public class SuiteRunner {
   
public SuiteDriveClass suitedrive;

    static Logger log = loggerLoad.config("SuiteRunner");
    static SimpleDateFormat sdf =new SimpleDateFormat("dd_MM_YYYY_hh_mm");
    BufferedReader br;
    BufferedWriter bw ;
    String strDate ;
    private static String browsername ;
    private static String dateAndTime;
    
    public static String getDateandTime() {
		 System.out.println("dateAndTime : "+dateAndTime);
    	return dateAndTime;
	}
    	 
   public  void setDateAndTime() {
	     Calendar cal = Calendar.getInstance();
		 dateAndTime = sdf.format(cal.getTime());
		 SuiteRunner.dateAndTime = dateAndTime;
	}
    
	@Parameters({"suitename" , "browsername"})
	@Test(description="Going to run Suite")
	public void suiteRunner(String suitename , String browsername) {
		try{
		this.setBrowsername(browsername);
	    this.setDateAndTime();
		System.out.println("suitename : "+suitename);
		suitedrive = new SuiteDriveClass();
		boolean br = suitedrive.suiteRunner(suitename);
	   // Html_Merger.mergeReport();
		System.out.println("suite runner : "+br);
		Html_file.merger();
		
		System.out.println("done ");
		}catch(Exception e ){
			e.printStackTrace();
			log.error(String.format("Error : ", e));
		}
		
	}

	public static String getBrowsername() {
		return browsername;
	}
	


	public void setBrowsername(String browsername) {
		SuiteRunner.browsername = browsername;
	}
	
	
	  @Test(description="Going to generate report" , enabled=false)
	  public void genreport( String build , String version) throws IOException{	
		 try{
		  br = new BufferedReader(new FileReader(new File("batFile/index.html")));
		  bw = new BufferedWriter(new FileWriter(new File("log/error_index.html")));
		  Calendar cal = Calendar.getInstance();
		  String endDateTime = sdf.format(cal.getTime());
		  log.info("EndDate Date and Time :"+endDateTime); 
		  String readline = null;
		  StringBuffer stbrerr = new StringBuffer();
		  while( (readline = br.readLine()) != null){
				if(readline.contains("<p>error</p>")){
					readline = readline.replace("<p>error</p>", "<p><b>Build (" +build+" #B"+version+ ") Made Successfully and Uploaded on Buildhub </b></p>");			
				}else if(readline.contains("<td>errorcount</td>")){
					readline = readline.replace("<td>errorcount</td>", "<td>0</td>");
			
				}else if (readline.contains("<td>starttime</td>")){
					readline = readline.replace("<td>starttime</td>", "<td>"+strDate+"</td>");
				
				}else if (readline.contains("<td>endtime</td>")){
				  readline = readline.replace("<td>endtime</td>", "<td>"+endDateTime+"</td>");
				 
				}else if( readline.contains("<td style=\"background-color: #cc8a87\">Failed</td>")){
					readline = readline.replace("<td style=\"background-color: #cc8a87\">Failed</td>", "<td style=\"background-color: #5BC362\">Success</td>");
			
				}else if (readline.contains("<p style=\"text-decoration: none; color: #7d0404;text-align: left; margin-left: 15px\"><b>Error Details : - </b></p>")){
					readline = readline.replace("<p style=\"text-decoration: none; color: #7d0404;text-align: left; margin-left: 15px\"><b>Error Details : - </b></p>", " ");
				
				}else if (readline.contains("<a href=\"https://www.w3schools.com\">Click Here to Download DotNet MSI for 32 bit.</a>")){
					String buildname  = String.format("http://10.10.30.16:8992/others/ND-DOTNET-MSI/NDDotNet_%s_%s.msi", build , version);
					readline = readline.replace("<a href=\"https://www.w3schools.com\">Click Here to Download DotNet MSI for 32 bit.</a>", "<a href="+buildname+">Click Here to Download Report.</a>");
				}else if (readline.contains("<a href=\"https://www.w3schools.com\">Click Here to Download DotNet MSI for 64 bit.</a>")){
					String buildname = String.format("http://10.10.30.16:8992/others/ND-DOTNET-MSI/NDDotNet2_%s_%s.msi", build , version);
					readline = readline.replace("<a href=\"https://www.w3schools.com\">Click Here to Download DotNet MSI for 64 bit.</a>", "<a href="+buildname+">Click Here to Download DotNet MSI for 64 bit.</a>");
				}
				stbrerr.append(readline+"\n");
			}
		    
		    bw.write(stbrerr.toString());  
			bw.flush();
			bw.close();
		
		  System.out.println("All Steps Done");
		 }catch(Exception e ){
				log.error(String.format("Error : ", e));
			}
	  }
}
