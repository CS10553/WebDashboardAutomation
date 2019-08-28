package com.automation.TestCaseRunner;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.automation.TestSuiteRunner.SuiteRunner;
import com.automation.Utilities.ReportGenrator;
import com.automation.Utilities.TestLinkProgram;
import com.automation.Utilities.loggerLoad;

import testlink.api.java.client.TestLinkAPIResults;

/**
 * @author Automation Team 
 *
 */

public class TestCaseDriveClass 
{
	static Logger log = loggerLoad.config("TestCaseDriveClass");
	static TestLinkDataReader tr = new TestLinkDataReader();
	public static String result = TestLinkAPIResults.TEST_PASSED;
	TestCaseDataReader dr = new TestCaseDataReader();
	static TestLinkProgram tl = new TestLinkProgram();
	public static String executionTime;
	public static String Imagedest_path;
	public static File img_dest;
	public static String[] testcase;
	public  static Boolean testcase_status;
	public static String testCaseName;
	ReportGenrator olog = new ReportGenrator();
	public boolean testcaseRunner(String testcasename)
	{
		int count=0;

		try{
			testcase = testcasename.split("\\s+");
			testCaseName=testcasename.split(" ")[0];
			int iterator  = Integer.parseInt(testcase[2]);
			for(int i= 1 ; i <= iterator ; i++){
				Imagedest_path = "reports/"+SuiteRunner.getDateandTime()+"/images/"+testcase[0];
				img_dest = new File(Imagedest_path);
				if(!img_dest.exists()){
					System.out.println("going to create directory :"+Imagedest_path);
					img_dest.mkdirs();
			}
				long startTime = System.currentTimeMillis();
				olog.createInstance(testCaseName+"_"+startTime);
				testcase_status = dr.testCaseDataRead(testcase[0] ,img_dest );
				long endTime = System.currentTimeMillis();
				long time = TimeUnit.MILLISECONDS.toMinutes(endTime - startTime);
				long sec = TimeUnit.MILLISECONDS.toSeconds(endTime - startTime-(time*60000));
				executionTime = "Execution Time = "+Long.toString(time)+"."+Long.toString(sec);

				/*if(testcase_status == false){ log.error(String.format("%s execution failed going to generate Error report \n",testcase[0])); result= TestLinkAPIResults.TEST_FAILED;

				}
				else
				{
					log.info(String.format("%s executed sucessfully\n", testcase[0]));
				}*/

			}
			/*log.info("Going to make testlink call");
			tl.reportResult(testcasename.split(" ")[0],result,tr.getTestLinkData(),executionTime);
			log.info("Going to make testlink call Completed");*/

			if(testcase[1].equalsIgnoreCase("terminate")){
				return false;

			}

		}catch(Exception e){
			log.error(String.format("Error : %s", e.getMessage())); 
		}
		return true;
	}	

}