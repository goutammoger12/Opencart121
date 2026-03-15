package BaseClass;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters; 

public class BaseClass {
	
public static WebDriver driver;

public org.apache.logging.log4j.Logger logger;  //Log4j
public Properties p;

@BeforeClass(groups= {"sanity","Regression","master"})
@Parameters({"os","browser"})
public void setup(String os, String br) throws IOException
{
	logger=LogManager.getLogger(this.getClass());    //Log4j
	
	FileReader file= new FileReader("./src//test//resources//config.properties");
	p=new Properties();
	p.load(file);
	
	
	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	{
		
		DesiredCapabilities cap=new DesiredCapabilities();
		
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			cap.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac"))
		{
			cap.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("No matching OS");
			return;
		}
		//browser
		switch(br.toLowerCase())
		{
		case "chrome": cap.setBrowserName("chrome");break;
		case "edge":cap.setBrowserName("MicrosoftEdge");break;
		default:System.out.println("No matching browser"); return;
		}
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		}
	if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	{
		switch(br.toLowerCase())
		{
		case "chrome": driver=new ChromeDriver(); break;
		case "edge": driver= new EdgeDriver(); break;
		default:System.out.println("Invalid browser"); return;
		}
		
	}
	
	

	driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.get(p.getProperty("AppURl"));
	driver.manage().window().maximize();
}
@AfterClass(groups={"sanity","Regression","master"})
public void tearDown()
{
	driver.quit();
}

public String randomString()
{
	String generatedString=RandomStringUtils.randomAlphabetic(5);
	return generatedString;
}
public String randomnumber()
{
	String generatedNumber=RandomStringUtils.randomNumeric(10);
	return generatedNumber;
}
public String randomAlphaNumeric()
{
	String generatedString=RandomStringUtils.randomAlphabetic(3);
	String generatedNumber=RandomStringUtils.randomNumeric(3);
	return(generatedString+"@"+generatedNumber);
}
public String captureScreen(String tname) throws IOException
{
	String timeStamp=new SimpleDateFormat("yyyyMMddmmss").format(new Date());
	
	TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
	File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
	
	String targetFilePath=System.getProperty("user.dir")+"\\screenshots"+tname+"_"+timeStamp+".png";
	File targetFile=new File(targetFilePath);
	
	sourceFile.renameTo(targetFile);
	return targetFilePath;
}

}
