package com.automation.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import com.automation.TestCaseRunner.TestCaseDriveClass;

/**
 * @author Automation Team 
 *
 */

public class SuiteDriveClass {
	public TestCaseDriveClass testcasedrive;
	private BufferedReader br;
	private StringBuffer sbr;
	
	public SuiteDriveClass()
	{
		
		testcasedrive = new TestCaseDriveClass();
		
	}

	public boolean suiteRunner(String name) throws IOException{
		
		try{
		sbr = new StringBuffer();
		br = new BufferedReader(new FileReader(new File(name)));
		String line;
		while((line = br.readLine()) != null ){
			if(! line.startsWith("#")){
				boolean bl = testcasedrive.testcaseRunner(line);
				if(bl == false){
					System.out.println("TestCase mentioned as not to continue");
					System.exit(0);
				}
			}
		}
		}catch(Exception e){
			return false;
		}
		return true;
	}
}
