package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//Data Provider1
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//testData//UserData.xlsx"; //taking xl file from test data
		ExcelUtility xl= new ExcelUtility(path);  // creating the object for excel utilities
		
		int rownum=xl.getRowCount("Sheet1");
		int colcount=xl.getCellCount("Sheet1", 1);
		
		String apidata[][]= new String[rownum][colcount];  // created 2 dimensional array to store data
		
		for(int i=1; i<=rownum; i++)  // reading 2 dimensional array value of i=row 
		{
			for(int j=0; j<colcount; j++)  // reading 2 dimensional array value of j=column 
			{
				apidata[i-1][j]= xl.getCellData("Sheet1", i, j);  //1,0  i=row j=column
			}
		}
		return apidata;  // returning 2 dimensional array
		
	}
	
	
	//Data Provider2
		@DataProvider(name="UserName")
		public String[] getUserName() throws IOException
		{
			String path=System.getProperty("user.dir")+"//testData//UserData.xlsx"; //taking xl file from test data
			ExcelUtility xl= new ExcelUtility(path);  // creating the object for excel utilities
			
			int rownum=xl.getRowCount("Sheet1");
			
			
			String apidata[]= new String[rownum];  // created 1 dimensional array to store data
			
			for(int i=1; i<=rownum; i++)  // reading 2 dimensional array value of i=row 
			{
				
					apidata[i-1]= xl.getCellData("Sheet1", i,1);  //1,0  i=row j=column
				
			}
			return apidata;  // returning 2 dimensional array
			
		}
	
	
	
	
	
	
	
	
}
