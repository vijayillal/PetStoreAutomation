package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;



public class ExtentReportManager implements ITestListener 
{

	public ExtentSparkReporter sparkreporter;  // UI of the report
	public ExtentReports extent;   // populate common information about the report
	public ExtentTest test;  // responsible for test passed, failed or skipped
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		/*SimpleDateFormat df= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt= new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp
		repName="Test-Report-" +timestamp +".html";
		
	    sparkreporter= new ExtentSparkReporter(".\\reports\\" +repName);  // location of the report
	    sparkreporter.config().setDocumentTitle("RestAssured Automation Project");  //title of the report
	    sparkreporter.config().setReportName("Pet Store Users API");  // name of the report
	    sparkreporter.config().setTheme(Theme.DARK);   // theme of the report
	    
	    extent= new ExtentReports();
	    extent.attachReporter(sparkreporter);
	    
	    extent.setSystemInfo("Application", "Pet Store");
	    extent.setSystemInfo("Module", "Admin");
	    extent.setSystemInfo("SubModule", "Customers");
	    extent.setSystemInfo("UserName", System.getProperty("user.name"));
	    extent.setSystemInfo("Environment", "QA");
	    extent.setSystemInfo("user", "Vijay");
	    
	 /*   String os=testContext.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);
	    
	    String browser=testContext.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);
	    
	   List <String> includedgroups= testContext.getCurrentXmlTest().getIncludedGroups();
	   if(!includedgroups.isEmpty())
	   {
		   extent.setSystemInfo("Groups", includedgroups.toString());
	   }
	   */  
	  }
	
	 public void onTestSuccess(ITestResult result) 
	 {
	    test=extent.createTest(result.getName()) ;   //create new entry in the report
	    test.assignCategory(result.getMethod().getGroups()); // to display group name
	    test.createNode(result.getName());
	    test.log(Status.PASS,result.getName() +"got successfully executed"); //update status
	    
     }
	 
	 public void onTestFailure(ITestResult result) 
	 {
		 test=extent.createTest(result.getName());
		 test.createNode(result.getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 
		 test.log(Status.FAIL,result.getName() +"Test case Failed");  
		 test.log(Status.INFO, result.getThrowable().getMessage());
		 
		  }
	
	 public void onTestSkipped(ITestResult result) {
		 
		    test=extent.createTest(result.getName());
		    test.createNode(result.getName());
		    test.assignCategory(result.getMethod().getGroups());
		    test.log(Status.SKIP, result.getName() +"test case skipped");
		    test.log(Status.INFO, result.getThrowable().getMessage());
		  }
	 
	 public void onFinish(ITestContext testContext) {
		    extent.flush();
		    
		    // Automatically opens the report if you use below codes
		    String pathOfExtentReport=System.getProperty("user.dir") + "\\reports\\" + repName;
		    File extentReport= new File(pathOfExtentReport);
		    
		    try {
		    	Desktop.getDesktop().browse(extentReport.toURI());
		    	
		    }catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
		  
		    /*
		    try {
		    	URL url= new URL("file:///" + System.getProperty("user.dir" +"\\reports\\" +repName));
		    	
		    	// create the email message
		    	ImageHtmlEmail email= new ImageHtmlEmail();
		    	email.setDataSourceResolver(new DataSourceUrlResolver(url));
		    	email.setHostName("smtp.googleemail.com");
		    	email.setSmtpPort(465);
		    	email.setAuthentication("illalvijay6@gmail.com", "vijay6illal");
		    	email.setSSLOnConnect(true);
		    	email.setFrom("illalvijay6@gmail.com"); //sender email
		    	email.setSubject("Test Results");
		    	email.setMsg("Please find Attached Report....");
		    	email.addTo("illalvikram@gmail.com");   // receiver email
		    	email.attach(url,"extent report", "please check report");
		    	email.send();  // send email
		    }catch(Exception e) 
		    {
		    	e.printStackTrace();
		    	
		    }
		    */
		    
		  }
}
