package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage
	{
		public AccountRegistrationPage (WebDriver driver)
	{
		super(driver);
	}

@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstName;
@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastName;
@FindBy(xpath="//input[@id='input-email']")
WebElement txtemail;
@FindBy(xpath="//input[@id='input-telephone']")
WebElement txttelephone;
@FindBy(xpath="//input[@id='input-password']")
WebElement txtpassword;
@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtconfirmpassword;
@FindBy(xpath="//input[@name='agree']")
WebElement checkpolicy;
@FindBy(xpath="//input[@value='Continue']")
WebElement btncontinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgconfirmation;

public void setFirstName(String fname)
{
	txtFirstName.sendKeys(fname);
}
public void setLastName(String lname)
{
	txtLastName.sendKeys(lname);
}
public void setemail(String email)
{
	txtemail.sendKeys(email);
}
public void settelephone(String tele)
{
	txttelephone.sendKeys(tele);
}
public void setpassword(String pwd)
{
	txtpassword.sendKeys(pwd);
}
public void setconfirmpassword(String cpwd)
{
	txtconfirmpassword.sendKeys(cpwd);
}
public void setprivacy(String pwd)
{
	checkpolicy.click();
}
public void clickcontinue()
{
	btncontinue.click();
	//btncontinue.submit();
}


public String getconfirmation()
{
	try {
		return(msgconfirmation.getText());
	}
	catch(Exception e)
	{
		return (e.getMessage());
	}
	
}
	}		

