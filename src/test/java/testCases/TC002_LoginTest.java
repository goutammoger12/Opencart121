package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pageObjects.HamePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass 
{
	@Test (groups= {"sanity","master"})
	public void verify_login()
	{
		logger.info("------starting TC002_loginTest-----------");
		
		try
		{
		//Home page
		HamePage hp=new HamePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login page
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccountPage
		MyAccountPage map=new MyAccountPage(driver);
		boolean target=map.isMyAccountPageExists();
		Assert.assertTrue(target);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("---------------TC002_logintest is completed-----------------");
	}
	}
