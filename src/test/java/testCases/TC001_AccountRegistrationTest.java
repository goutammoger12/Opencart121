package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClass.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HamePage;

public class TC001_AccountRegistrationTest extends BaseClass
{
@Test (groups= {"Regression","master"})
public void verifyAcc_regestration()
{
	logger.info("**** Starting TC001_AccountregistrationTest  ****");
	try
	{
	
	HamePage hp=new HamePage(driver);
			hp.clickMyAccount();
			logger.info("**** Clicked on myAccount link ****");
	        hp.clickRegister();
	        logger.info("**** Clicked on Register link ****");
	        
	AccountRegistrationPage rp=new AccountRegistrationPage(driver); 
	
	        logger.info("**** Providing customer details ****");	
			rp.setFirstName(randomString().toUpperCase());
	        rp.setLastName(randomString().toUpperCase());
	        rp.setemail(randomString()+"@gmail.com");
	        rp.settelephone(randomnumber());
	        
	        String password=randomAlphaNumeric();
			rp.setpassword(password);
			rp.setconfirmpassword(password);
			rp.setprivacy(password);
			rp.clickcontinue();
			logger.info("**** Validating the expected methods ****");
String confirmmsg=rp.getconfirmation();
if(confirmmsg.equals("Your Account Has Been Created!00"))
{
	Assert.assertTrue(true);
}
else
{
	logger.error("Test failed .....");
	logger.debug("Debug logs....");
	Assert.assertTrue(false);
}

//Assert.assertEquals(confirmmsg, "Your Account Has Been Created!00");
}
	catch(Exception e)
	{
		Assert.fail();
	}
			
	logger.info("**** finished TC001_AccountregistrationTest  ****");
}
}
