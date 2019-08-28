package com.automation.Utilities;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.TestCaseRunner.TestCaseDriveClass;
import com.automation.TestCaseRunner.TestLinkDataReader;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;

public class TestLinkProgram 
{  
	static TestLinkDataReader tr = new TestLinkDataReader();
	static Logger log = loggerLoad.config("TestLinkProgram");
	public static String result = TestLinkAPIResults.TEST_PASSED;
	public static String DEV_KEY = "a3eba952e88e275e9051ccbe0b0a5606";
	//public static String DEV_KEY = "5ebc1cd54cbd2b45fb7b4196ad9548fed1712ce772fb4600a821be8dc8474229"
	public static String SERVER_URL = "http://10.10.30.104/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
	
	public static TestLinkAPIClient api=new TestLinkAPIClient(DEV_KEY, SERVER_URL);
	public void reportResult(String TestProject,String TestPlan,String Testcase,String Build,String Notes,String Result) throws TestLinkAPIException{
		TestLinkAPIClient api=new TestLinkAPIClient(DEV_KEY, SERVER_URL);
		System.out.println("Execute");
		api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);
		System.out.println("Done");
	}

	@SuppressWarnings("unused")
	public static boolean reportResult(String TestcaseName,String result,ArrayList<String> arr,String time) throws TestLinkAPIException
	{
		 String notes = "Executed by NetAutomator_" + TestcaseName + "\nOther notes : " + arr.get(3)+ "\n"+time;
		if(arr!=null)
		{		
			 log.info(notes);
			 api.reportTestCaseResult(arr.get(0), arr.get(1), TestcaseName, arr.get(2), notes, result);
		     return true;
		}
		else
		{
			log.info("Unable to load "+notes);
			return false; 
		}
		
	}
	
	public static boolean TestLinkCall(String testcaseName,boolean testcaseStatus ) throws TestLinkAPIException
	{
		if(testcaseStatus == false)
		{
			log.error(String.format("%s execution failed going to generate Error report \n", testcaseName));
			result= TestLinkAPIResults.TEST_FAILED;
			
		}else
		{
			log.info(String.format("%s executed sucessfully\n", testcaseName));
		}
		
		log.info("Going to make testlink call");
		reportResult(testcaseName,result,tr.getTestLinkData(),TestCaseDriveClass.executionTime);
		log.info("Going to make testlink call Completed");
		return true;
	}

	public static void main(String[] args) throws TestLinkAPIException {
		String testProject="Demo";
		String testPlan="Archchat";
		String testCase="Demo-1093";
		String build="Build 1";
		String notes=null;
		String result=null;
		TestLinkProgram a = new TestLinkProgram();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 600);

		try {
			driver.manage().window().maximize();
			driver.get("https://www.google.com");

			System.out.println("Divyansh");           
			result= TestLinkAPIResults.TEST_PASSED;
			System.out.println("Divyansh"+result);  
			notes="Executed successfully new ";
			System.out.println("Divyansh"+notes);  
		}
		catch(Exception e){
			result=TestLinkAPIResults.TEST_FAILED;
			notes="Execution failed";
		}
		finally{

			a.reportResult(testProject, testPlan, testCase, build, notes, result);
			System.out.println("data inserted");
			driver.quit();

		}
	}
}
