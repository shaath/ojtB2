package com.stockAcc.Master;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public class testngMaster 
{
	public WebDriver driver;
	public FileInputStream fi;
	public Properties pr;
	public static String url="http://qedgetech.com/1";
	public static String expval,actval;
	public static String uname="admin",pwd="master";
	public static String supname="Qedge123",add="Ameerpet";
	public static String city="Hyderabad",cntry="INDIA";
	public static String cperson="Rakesh",pno="04022223333";
	public static String email="wwww@gmail.com",mno="9988774455";
	public static String notes="This supplier give Training";
	public static String Uid="UOM897548",Udesc="It contans 100 items";
	@BeforeSuite
	public void stock_Launch() throws IOException
	{
		
		String prPath="E:\\stockAccounting\\stockAcc\\src\\com\\stockAcc\\Properties\\config.properties";
		fi=new FileInputStream(prPath);
		pr=new Properties();
		pr.load(fi);
		
		if (pr.getProperty("br").equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
			
		}
		else if (pr.getProperty("br").equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Dell\\Desktop\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if (pr.getProperty("br").equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", "C:\\Users\\Dell\\Desktop\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
			
		}
		expval="Login";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actval=driver.findElement(By.id(pr.getProperty("login"))).getText();
		Assert.assertEquals(actval, expval,"Launch Failed");
		
		
	}
	
	@AfterSuite
	public void stock_Close()
	{
		driver.close();
	}
	
	@BeforeTest
	public void stock_login()
	{
		expval="Administrator";
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("btnsubmit")).click();
		actval=driver.findElement(By.xpath(".//*[@id='msUserName']/font/strong")).getText();
		Assert.assertEquals(actval, expval,"Login Failed");
	}
	@AfterTest
	public void stock_Logout()
	{
		expval="Login";
		driver.findElement(By.id("logout")).click();
		Sleeper.sleepTight(5000);
		List<WebElement> button=driver.findElements(By.tagName("button"));
		for (int i = 0; i < button.size(); i++) 
		{
			String text=button.get(i).getText();
			if (text.equalsIgnoreCase("ok!"))
			{
				button.get(i).click();
				break;
				
			}
			
		}
//		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button[1]")).click();
//		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button[1]")).click();
		actval=driver.findElement(By.id("btnsubmit")).getText();
		Assert.assertEquals(actval, expval,"Logout Failed");
	}

}
