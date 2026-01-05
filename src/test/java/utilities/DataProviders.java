package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="loginData")
	public String[][] getData() throws IOException{
		
		String path=".\\testData\\dataDrivenTest.xlsx";   //taking excel file from testData
		
		ExcelUtility xlUtil=new ExcelUtility(path);  //creating object of excel utility
		int totalRows=xlUtil.getRowCount("Sheet1");
		int totalCols=xlUtil.getCellCount("Sheet1",1);
		
		String[][] loginData=new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++) {
			for(int j=0;j<totalCols;j++) {
				
				loginData[i-1][j]=xlUtil.getCellData("Sheet1", i, j);
				
			}
		}
		
		return loginData;	  //return two dimensional array	
		
	}
	
	//dataProvider methods2
	
	//dataProvider methods3
	
	//dataProvider methods4

}

