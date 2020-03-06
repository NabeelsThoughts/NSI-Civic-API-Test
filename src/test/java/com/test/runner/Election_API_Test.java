package com.test.runner;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.test.hooks.Hooks;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Election_API_Test 
{

	Properties pr=new Properties();
	
	@BeforeTest
	public void initializeTest() throws Exception
	{
		FileInputStream fin=new FileInputStream("src/test/resources\\env.properties");
		pr.load(fin);
		System.out.println("Properties loaded");
		
	}
	
	
	@Test
	public void Goolge_API()
	{	
		try{
			
				
				//Base URL Or Host name
				RestAssured.baseURI = pr.getProperty("HOST");
				System.out.println("Base URL is: "+ pr.getProperty("HOST"));
				Hooks.et.log(LogStatus.INFO, "The Base URL is: "+ pr.getProperty("HOST"));
				
				Response res= given().
				
						
				//we pass parameters or headers
				given().
				param("key",pr.getProperty("KEY")).
				header("Content-Type","application/json").
				
				
				//Type of request
				when().
				get(Hooks.Election_Api_Resource). //the value coming from Hooks class
				
				//Result part
				then().
				
				//Verify or Assert with expected value
				assertThat().statusCode(200).
				
				and().
				
				//extract the response
				extract().response();
				
				//printing the response output in console
				String Body=res.asString();
				System.out.println("The response body for the create block API is---> "+ Body);
				Hooks.et.log(LogStatus.PASS,"The Response of this API is---> "+ Body);
				
				/**
				 * Validate specified filed in api response
				 */
				
				JsonPath js=new JsonPath(Body);
				//getting kind and validating
				String kind = js.getString("kind");
				Hooks.verifyBody(kind, "civicinfo#electionsQueryResponse");
				
			}catch(Exception e)
			{
				System.out.println("Test Failed, Error is ---> "+ e);
				Hooks.et.log(LogStatus.INFO, "Test Failed, Error is ---> "+ e);
			}
	}
	
	@AfterTest
	public void end_Test()
	{
		Hooks.close_reports();
	}
}
