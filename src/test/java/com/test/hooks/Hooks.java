package com.test.hooks;

import java.text.DateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Hooks 
{
	
	/**
	 * Global variables and methods
	 */

	
	public static DateFormat format =new java.text.SimpleDateFormat("_yyyy-MMM-dd_h-mm");
	public static Date date=new Date();
	public static String Execution_Time =format.format(date);
	
	
	public static ExtentReports er=new ExtentReports(System.getProperty("user.dir")+"\\Test_Reports\\Log_"+Hooks.Execution_Time+".html");
	public static ExtentTest et=er.startTest("API Test Automation Reports");

	public static void close_reports()
	{
		et.log(LogStatus.INFO,"API Test is successfully finished");
		er.flush();
		er.endTest(et);
	}
	
	/**
	 * Declare the resource and other data
	 */
	
	public static String Election_Api_Resource ="/civicinfo/v2/elections";
	
	public static void verifyBody(String expected, String Actual)
	{
		if(expected.equals(Actual))
		{
			System.out.println("Test Passed: Both Actual and Expected response is same.");
			et.log(LogStatus.PASS, "Test Passed: Both Actual and Expected response is same.");
		}
		else 
		{
			System.out.println("Test Failed: Both Actual and Expected response is different.");
			et.log(LogStatus.PASS, "Test Failed: Both Actual and Expected response is different.");
		}
	}
}
