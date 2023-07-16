package com.saucelabs.dataprovider;

import java.io.IOException;
import org.testng.annotations.DataProvider;

import com.saucelabs.utility.ExcelLibrary;

public class LogindataProvider {
	
	@DataProvider(name="LoginCredentials")
	public String [][] getData() throws IOException
	{
		//get the login credentials from excel file
		String path=".\\src\\test\\resources\\TestData\\testdata.xlsx";
		ExcelLibrary xl = new ExcelLibrary(path);
		
		int totalrows=xl.getRowCount("Sheet1");
		int totalcols=xl.getCellCount("Sheet1",1);	
				
		String loginData[][]=new String[totalrows][totalcols];
			
		for(int i=1;i<=totalrows;i++) 
		{
			for(int j=0;j<totalcols;j++) 
			{
				loginData[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
				
		}
		
		return loginData;
	}
	
}
