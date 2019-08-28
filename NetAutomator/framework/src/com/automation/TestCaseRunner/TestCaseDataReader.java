package com.automation.TestCaseRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.automation.TestSuiteRunner.SuiteRunner;
import com.automation.Utilities.ReportGenrator;
import com.automation.Utilities.TestLinkProgram;
import com.automation.Utilities.loggerLoad;
import com.automation.genrics.ActionsMethods;
import com.automation.genrics.customMethods;
import com.automation.genrics.genericMethods;
import com.aventstack.extentreports.ExtentTest;

import testlink.api.java.client.TestLinkAPIResults;

/**
 * @author Automation Team 
 *
 */

public class TestCaseDataReader {
	XSSFWorkbook wb;
	XSSFSheet sh;
	static ExtentTest test;

	static Logger log = loggerLoad.config("TestCaseDataReader");

	private BufferedReader br;
	//ActionsMethods act = new ActionsMethods();  // Creates an object for Chrome driver;
	ReportGenrator olog = new ReportGenrator();
	genericMethods gm=new genericMethods();
	public customMethods cm=new customMethods();
	TestLinkProgram tl = new TestLinkProgram();
	String result = TestLinkAPIResults.TEST_PASSED;
	private static  boolean testStatus;
	public static  XSSFSheet sheet;
	public static String SheetName;
	HashMap< String, Boolean> status =new HashMap<String,Boolean>();
	//private static HashMap< String, Boolean> status;
	public ArrayList<String> getData(String iterationfile , String parameter) {
		ArrayList<String> al = new ArrayList<>();
		try{
			int index = 0 ; 
			br = new BufferedReader(new FileReader(new File("testcases/" + iterationfile + "/iteration.spec")));
			String line ;

			String l = br.readLine();
			String[] l2 = l.split("\\s+");

			index= Arrays.asList(l2).indexOf(parameter);

			while((line = br.readLine()) != null){

				String line2[]  = line.split("\\s+");
				al.add(line2[index]);
			}
		}catch(Exception e ){ e.printStackTrace();}
		System.out.println(al);
		return al;	
	}


	@SuppressWarnings("unused")
	public boolean testCaseDataRead(String excelpath , File img_dest) {
		try {
			olog.createInstance(excelpath);
			File src = new File("testcases/" + excelpath + "/testcaseStep.xlsx");
			log.info(String.format("Going to read data from %s - testcaseStep.xlsx fle",excelpath));
			wb = new XSSFWorkbook(src);
			int sheets=wb.getNumberOfSheets();
			log.info("Number of sheet/workbook : " + sheets);
			for (int j = 2; j <= sheets-1; j++) 
			{
				
				SheetName=wb.getSheetName(j-1);
				sheet=wb.getSheetAt(j-1);
				log.info("SheetName : " + SheetName);

				test = olog.genRateReport(SheetName);
				ActionsMethods.registerDriver(SuiteRunner.getBrowsername() , img_dest);
				int row_size = sheet.getLastRowNum();
				log.info("Size of row : " + row_size);
			for (int i = 1; i <= row_size; i++)
				{
					ArrayList<String> al = new ArrayList<>();
					String  keywords = sheet.getRow(i).getCell(2).toString().toLowerCase();  
					log.info("Actions need to perform : " + keywords);

					if(keywords.contains(":"))
						testStatus=cm.userdefinedMethods(i, excelpath,keywords,img_dest,test);					
					else
						testStatus=gm.defMthd(i,excelpath,keywords,img_dest,test);		
				}
				log.info("SheetName : " + SheetName);
				status.put(SheetName,testStatus);
				ReportGenrator.extent.flush();
				//log.info(SheetName+""+status.get(SheetName));
                
			} 

			 Set<Entry<String, Boolean>> set = status.entrySet();
			 Iterator<Entry<String, Boolean>> iterator = set.iterator();
			 Iterator<Entry<String, Boolean>> hmIterator = status.entrySet().iterator();
		         while(hmIterator.hasNext())
		         {
		          Entry<String, Boolean> pair = hmIterator.next();

		         String TestCaseName=(String) pair.getKey();
		         Boolean TestCaseStatus=(Boolean) pair.getValue();
		         log.info("key is: "+ TestCaseName + " & Value is: "+TestCaseStatus);
		        // TestLinkProgram.TestLinkCall(TestCaseName, TestCaseStatus);
		      }



		}
		catch (Exception e)
		{

			log.error(String.format("Error in execution of %s test case. Please open TestNG report " , excelpath ));
			log.error("Error are :  ",e);
			olog.errorlog(test, e.toString());
			return false;
		}
		//callTestLink();
		return true;
	}


	public int getRowCount(int sheetnumber) {
		int rownumber = wb.getSheetAt(sheetnumber).getLastRowNum();
		rownumber = rownumber + 1;
		return rownumber;
	}

	/*public boolean  callTestLink(HashMap<String, boolean> status)
	{
		Iterator<Entry<String, Boolean>> hmIterator = status.entrySet().iterator();
		while(hmIterator.hasNext())
		{
			Map.Entry pair = hmIterator.next();

			Object TestCaseName=pair.getKey();
			Object TestCaseStatus=pair.getValue();
			log.info("key is: "+ TestCaseName + " & Value is: "+TestCaseStatus);
			// TestLinkProgram.TestLinkCall(TestCaseName, TestCaseStatus);
		}
		return testStatus;

	}*/
}

