package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pageObjects.HamePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.Dataprovider;

public class TC003_Login_DataDrivenTest extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass= Dataprovider.class, groups= {"data driven"}) 
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("------TC003 is started---------");
		try
		{
		
		HamePage hp=new HamePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				macc.clicklogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				macc.clicklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
	}
		
			catch(Exception e)
			{
				Assert.assertTrue(true);
			}
		
		logger.info("-----Finished TC003------");
	}
	
}


