package com.automation.TestCaseRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.automation.Utilities.loggerLoad;
import org.apache.log4j.Logger;


public class TestLinkDataReader {
   
    
	static Logger log = loggerLoad.config("TestLinkDataReader");
	ArrayList<String> al;
	BufferedReader br;
	public ArrayList<String> getTestLinkData() {
		System.out.println("abc");
		al = new ArrayList<String>();
		try {
			File file = new File("./conf/TestLink.conf");
			br = new BufferedReader(new FileReader(file));
			String line;
			if(file.exists()){
				log.info("TestLink.conf file is available");
				while ((line = br.readLine()) != null) {
		
					al.add(line.split(":")[1]);

				}
			}
			else{
				log.error("TestLink.conf File not found");
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return al;

	}



}
