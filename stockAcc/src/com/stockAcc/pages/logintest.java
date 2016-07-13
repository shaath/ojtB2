package com.stockAcc.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class logintest
{
	@Test
	public void login()
	{
		WebDriver driver=new FirefoxDriver();
		driver.get("http://webapp.qedgetech.com");
		
		loginpage lp=PageFactory.initElements(driver, loginpage.class);
		lp.stock_Login(lp.u, lp.p);
		
		adminpage ap=PageFactory.initElements(driver, adminpage.class);
		ap.logout1mclick();
		
		driver.close();
	}
	

}
