package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HamePage extends BasePage 
{

	public HamePage(WebDriver driver) 
	{
		super(driver);
	}

	@FindBy(xpath="//span[@class='caret']")
	WebElement InkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement InkMyRegister;
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement InkLogin;
	
	public void clickMyAccount()
	{
		InkMyAccount.click();
	}

	public void clickRegister()
	{
		InkMyRegister.click();
	}
	
	public void clickLogin()
	{
		InkLogin.click();
	}
}
	