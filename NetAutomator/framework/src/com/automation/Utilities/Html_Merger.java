package com.automation.Utilities;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class Html_Merger {
	static String desktopDir=ReportGenrator.path;
	static File[] paths ;
	static String outFileName=ReportGenrator.path+"\\index.html";
	static File dir = new File(desktopDir);
	 static FilenameFilter fileNameFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
        	if (name.equalsIgnoreCase("index.html"))
        	{
        		return false;
        	}
            if (name.lastIndexOf('.') > 0) {
                int lastIndex = name.lastIndexOf('.');
                String extension = name.substring(lastIndex);
                if (extension.equals(".html")) {
                    return true;
                }
            }
            return false;
        }
};
	
 public static  ArrayList<String> links()
{
	paths = dir.listFiles(fileNameFilter);
	ArrayList<String> str = new ArrayList<String>();
	try {
        //String file = outputFile.getAbsolutePath();
        //FileWriter fw = new FileWriter(file, true);
        for (File x : paths) {
            //Document doc = Jsoup.parse(x, "UTF-8");
            String path=x.getAbsolutePath();
            System.out.println(path);
            //String name_of_file=path.substring(path.lastIndexOf("\\") +1  , path.lastIndexOf(".html")) ;
            str.add(path);
            
            //fw.write("<a href = "+x.toString() +">"+ name_of_file+"</a>"); 
        }
        //fw.close();
    }
		catch (Exception ioe) {
        System.err.println(ioe.getMessage());
		}
	return str; 
}


	
		    
		  }
		