package com.automation.Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import com.automation.TestSuiteRunner.SuiteRunner;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ReportGenrator {

	static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	ExtentTest test;
	private BufferedReader br;
	public BufferedWriter bw;

	///static ExtentReports extent;
   static String path ="reports/"+SuiteRunner.getDateandTime();
	public static ExtentReports getInstance() {
		return extent;
	}
	/*public void  ReportGenratorInit(){*/
	public ExtentReports createInstance(String fileName) 
	{
		
		htmlReporter = new ExtentHtmlReporter(path+"/"+fileName+".html");
		extent = new ExtentReports();
		
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", "UBUNTU");
		extent.setSystemInfo("Host Name","automation");
		extent.setSystemInfo("Environment", "QA-Automation");	
		extent.setSystemInfo("Developed By", "Automation");
		htmlReporter.config().setDocumentTitle("Automation GUI Test Result");	
		htmlReporter.config().setReportName("GUI Automation Result");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("utf-8");
		extent.attachReporter(htmlReporter);
		htmlReporter.setAppendExisting(true);
		return extent;

	}
	public ExtentTest genRateReport(String TestCaseName){
		test = extent.createTest(TestCaseName, String.format("TestCase %s steps", TestCaseName));
		return test;

	}
	
	

	
	public void passlog(ExtentTest test , String steps){
		test.log(Status.PASS, MarkupHelper.createLabel(steps, ExtentColor.GREEN));
	}

	public void faillog(ExtentTest test , String steps){
		test.log(Status.FAIL, MarkupHelper.createLabel(steps, ExtentColor.RED));
	}

	public void errorlog(ExtentTest test , String steps){
		test.log(Status.FAIL, MarkupHelper.createLabel(steps, ExtentColor.RED));
	}


}
