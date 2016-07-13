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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.Select;

public class stockAccMaster 
{
	public static WebDriver driver;
	public static String expval,actval;
	public static String url="http://webapp.qedgetech.com";
	public FileInputStream fi=null;
	public Properties pr=null;
	public static boolean flag=false;
	public Actions act;
	/*Function Name:stockAcc_Launch
	 * Arguments: url
	 * Description: It will launch the stock accounting application
	 * created date:18/6/2016
	 * Author:Sharath
	 * Return Type: String 
	 */
	public String stockAcc_Launch(String url) throws IOException
	{
		
		fi=new FileInputStream("E:\\stockAccounting\\stockAcc\\src\\com\\stockAcc\\Properties\\config.properties");
		pr=new Properties();
		pr.load(fi);
		expval="btnsubmit";
		driver=new FirefoxDriver();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		actval=driver.findElement(By.id(pr.getProperty("login"))).getAttribute("id");
		
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
		
	}
	/*FunctionName			 :stockAcc_Login
	 * Arguments 			 :Uname,pwd
	 * Description			 :It will login the stockaccounting Application
	 * Return Type			 :String
	 * Author     			 :Sharath
	 * Date of Implementation:09/06/2016 
	 */
	
	public String stockAcc_Login(String uname,String pwd)
	{
		expval="administrator";
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(uname);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(pwd);
		driver.findElement(By.id("btnsubmit")).click();
		actval=driver.findElement(By.xpath(".//*[@id='msUserName']/font/strong")).getText();
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
		
	}
	
	/*FunctionName			 :stockAcc_Logout
	 * Arguments 			 :N/A
	 * Description			 :It will logout the stockaccounting Application
	 * Return Type			 :String
	 * Author     			 :Sharath
	 * Date of Implementation:09/06/2016 
	 */
	
	public String stockAcc_Logout()
	{
		expval="login";
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
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
	}
	
	/*FunctionName			 :stockAcc_Close
	 * Arguments 			 :N/A
	 * Description			 :It will close the stockaccounting Application
	 * Return Type			 :N/A
	 * Author     			 :Sharath
	 * Date of Implementation:09/06/2016 
	 */
	public void stockAcc_Close()
	{
		driver.close();
	}
	/*FunctionName			 :stockAcc_SupplierCreation
	 * Arguments 			 :supname,add,city,cntry,cperson,pno,email,mno,notes
	 * Description			 :It will close the stockaccounting Application
	 * Return Type			 :String
	 * Author     			 :Sharath
	 * Date of Implementation:09/06/2016 
	 */
	
	public String stockAcc_SupplierCreation(String supname,String add,String city,String cntry,String cperson,String pno,String email,String mno,String notes)
	{
		expval="Add succeeded";
		driver.findElement(By.linkText("Suppliers")).click();
		driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(supname);
		driver.findElement(By.id("x_Address")).sendKeys(add);
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).sendKeys(cntry);
		driver.findElement(By.id("x_Contact_Person")).sendKeys(cperson);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(pno);
		driver.findElement(By.id("x__Email")).sendKeys(email);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mno);
		driver.findElement(By.id("x_Notes")).sendKeys(notes);
		driver.findElement(By.id("btnAction")).click();
		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button")).click();
		Sleeper.sleepTight(5000);
		actval=driver.findElement(By.xpath("//*[@class='ajs-dialog']/div[3]/div/div")).getText();
		
		driver.findElement(By.xpath("//div[@class='ajs-footer']/div[2]/button")).click();
		
		if (expval.equalsIgnoreCase(actval))
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
	}
	
	

	/*FunctionName			 :stockAcs_stockcatCreation
	 * Arguments 			 :catname
	 * Description			 :It will create the stockaccounting category creation
	 * Return Type			 :String
	 * Author     			 :Sharath
	 * Date of Implementation:09/06/2016 
	 */
	public String stockAcc_stockcatCreation(String catname)
	{
		WebElement sitem=driver.findElement(By.linkText("Stock Items"));
		
		act=new Actions(driver);
		act.moveToElement(sitem).build().perform();
		
		driver.findElement(By.linkText("Stock Categories")).click();
		driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		driver.findElement(By.id("x_Category_Name")).sendKeys(catname);
		driver.findElement(By.id("btnAction")).click();
		Sleeper.sleepTightInSeconds(10);
		List<WebElement> button=driver.findElements(By.tagName("button"));
//		System.out.println(button.size());
		for (int i = 0; i < button.size(); i++) 
		{
			String text=button.get(i).getText();
			
			if (text.equalsIgnoreCase("OK!"))
			{
				button.get(i).click();
				break;
			}
			
		}
//		actval=driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[3]/div/div")).getText();
//		System.out.println(actval);
		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button")).click();
		Sleeper.sleepTightInSeconds(5);
		//validation
		String count=driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[2]/form/div[2]/span[2]")).getText();
		String[] split=count.split(" ");
//		System.out.println(split.length);
		int count1=Integer.parseInt(split[2]);
		int pcount=0;
//		System.out.println(split[0]+"--"+split[1]+"--"+split[2]);
		do 
		{
			List<WebElement> rows=driver.findElements(By.xpath("//*[@id='tbl_a_stock_categorieslist']/tbody/tr"));
			for (int i = 0; i < rows.size(); i++) 
			{
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String acttext=cols.get(3).getText();
//				System.out.println(acttext);
				if (acttext.equalsIgnoreCase(catname)) 
				{
//					System.out.println("category available");
					flag=true;
					break;
				}
			
			}
			if (flag==false) 
			{
				driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/div/div/div[2]/a[1]")).click();
			}
			pcount++;
			
		} 
		while (pcount<=count1);
		if (flag==true) 
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}

	}
	
	/*FunctionName			 :stockAcs_UnitofMCreation
	 * Arguments 			 :catname
	 * Description			 :It will create the stockaccounting Unit of measurement creation
	 * Return Type			 :String
	 * Author     			 :Sharath
	 * Date of Implementation:09/06/2016 
	 */
	public String stockAcc_UnitofMcreation(String Uid,String Udesc)
	{
		WebElement sitem=driver.findElement(By.linkText("Stock Items"));
		
		Actions act=new Actions(driver);
		act.moveToElement(sitem).build().perform();
		
		driver.findElement(By.linkText("Unit of Measurement")).click();
		driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		driver.findElement(By.id("x_UOM_ID")).sendKeys(Uid);
		driver.findElement(By.id("x_UOM_Description")).sendKeys(Udesc);
		driver.findElement(By.id("btnAction")).click();
		Sleeper.sleepTightInSeconds(10);
		List<WebElement> button=driver.findElements(By.tagName("button"));
		System.out.println(button.size());
		for (int i = 0; i < button.size(); i++) 
		{
			String text=button.get(i).getText();
			
			if (text.equalsIgnoreCase("OK!"))
			{
				button.get(i).click();
				break;
			}
			
		}
		String actval=driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[3]/div/div")).getText();
//		System.out.println(actval);
		driver.findElement(By.xpath("html/body/div[17]/div[2]/div/div[4]/div[2]/button")).click();
		Sleeper.sleepTightInSeconds(5);
		String count=driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[2]/form/div[2]/span[2]")).getText();
		String[] split=count.split(" ");
//		System.out.println(split.length);
		int count1=Integer.parseInt(split[2]);
		int pcount=0;
		System.out.println(split[0]+"--"+split[1]+"--"+split[2]);
		do 
		{
			List<WebElement> rows=driver.findElements(By.xpath(".//*[@id='tbl_a_unit_of_measurementlist']/tbody/tr"));
			for (int i = 0; i < rows.size(); i++) 
			{
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String acttext=cols.get(2).getText();
//				System.out.println(acttext);
				if (acttext.equalsIgnoreCase(Uid)) 
				{
					System.out.println("category available");
					flag=true;
					break;
				}
			
			}
			if (flag==false) 
			{
				driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/div/div/div[2]/a[1]")).click();
			}
			pcount++;
			
		} 
		while (pcount<=count1);

		if (flag==true) 
		{
			return "Pass";
		}
		else
		{
			return "Fail";
		}
	}
	
	
	
}
