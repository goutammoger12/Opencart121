package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClass.BaseClass;

public class ExtentreportManager implements ITestListener
{
     public ExtentSparkReporter sparkReporter;
     public ExtentReports extent;
     public ExtentTest test;
     
     String repname;
     
     public void onStart(ITestContext testContext)
     {
    	/* SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.mm.ss");
    	 Date dt=new Date();
    	 String currentdatetimestamp=df.format(dt);  */
    	 
    	 String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	 repname="test-Report-"+timeStamp+".html";
    	 sparkReporter=new ExtentSparkReporter(".\\reports\\"+repname);
    	 
    	 sparkReporter.config().setDocumentTitle("opencart Automation Report");
    	 sparkReporter.config().setReportName("opencart functional Testing");
    	 sparkReporter.config().setTheme(Theme.DARK);
    	 
    	 extent=new ExtentReports();
    	 extent.attachReporter(sparkReporter);
    	 extent.setSystemInfo("Application", "opencart");
    	 extent.setSystemInfo("Module","Admin");
    	 extent.setSystemInfo("Sub module", "Customers");
    	 extent.setSystemInfo("User Name", System.getProperty("user.name"));
    	 extent.setSystemInfo("Environment", "QA");
    	 
    	 String os=testContext.getCurrentXmlTest().getParameter("os");
    	 extent.setSystemInfo("Operating System", os);
    	 
    	 String browser=testContext.getCurrentXmlTest().getParameter("browser");
    	 extent.setSystemInfo("Browser", browser);
    	 
    	 List<String> includeGroups=testContext.getCurrentXmlTest().getIncludedGroups();
    	 if(!includeGroups.isEmpty())
    	 {
    		 extent.setSystemInfo("Groups", includeGroups.toString());
    	 }
    }
     public void onTestSuccess(ITestResult result)
     {
    	 test=extent.createTest(result.getTestClass().getName());
    	 test.assignCategory(result.getMethod().getGroups());
    	 test.log(Status.PASS, result.getName()+"got successfully executed");
     }
     public void onTestFailure(ITestResult result)
     {
    	 test=extent.createTest(result.getTestClass().getName());
    	 test.assignCategory(result.getMethod().getGroups());
    	 
    	 test.log(Status.FAIL,result.getName()+"got failed");
    	 test.log(Status.INFO, result.getThrowable().getMessage());
    	 
    	 try {
    		 String imgPath=new BaseClass().captureScreen(result.getName());
    		 test.addScreenCaptureFromPath(imgPath);
    	 } catch (IOException e1)
    	 {
    		 e1.printStackTrace();
    	 }
     }
     public void onTestSkipped(ITestResult result)
     {
    	 test=extent.createTest(result.getTestClass().getName());
    	 test.assignCategory(result.getMethod().getGroups());
    	 test.log(Status.SKIP, result.getName()+"got skipped");
    	 test.log(Status.INFO, result.getThrowable().getMessage());
     }
     
     public void onFinish(ITestContext testContext)
     {
    	 extent.flush();
    	 String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repname;
    	 File extentreport=new File(pathOfExtentReport);
    	 try
    	 {
    		 Desktop.getDesktop().browse(extentreport.toURI());
    	 } catch(IOException e)
    	 {
    		 e.printStackTrace();
    	 }
     }
}
