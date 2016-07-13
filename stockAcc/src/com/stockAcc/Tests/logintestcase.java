package com.stockAcc.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.server.browserlaunchers.Sleeper;
import org.openqa.selenium.support.ui.Select;

import com.stockAcc.Master.stockAccMaster;

public class logintestcase {

	public static void main(String[] args) throws IOException
	{
		stockAccMaster sam=new stockAccMaster();
		
		String res=sam.stockAcc_Launch("http://webapp.qedgetech.com");
		System.out.println(res);
		
		res=sam.stockAcc_Login("admin", "master");
		System.out.println(res);
		
		sam.driver.findElement(By.linkText("Stock Items")).click();
		sam.driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		WebElement cat=sam.driver.findElement(By.id("x_Category"));
		Select scat=new Select(cat);
		scat.selectByVisibleText("boll");
		String stItemno=sam.driver.findElement(By.id("x_Stock_Number")).getText();
		
		WebElement sNum=sam.driver.findElement(By.id("x_Supplier_Number"));
		Select s=new Select(sNum);
		s.selectByVisibleText("Suresh");
		
		sam.driver.findElement(By.id("x_Stock_Name")).sendKeys("sharath1234");
		
		WebElement uom=sam.driver.findElement(By.id("x_Unit_Of_Measurement"));
		Select suom=new Select(uom);
		suom.selectByVisibleText("1kg");
		
		sam.driver.findElement(By.id("x_Purchasing_Price")).sendKeys("2000");
		sam.driver.findElement(By.id("x_Selling_Price")).sendKeys("3000");
		sam.driver.findElement(By.id("x_Notes")).sendKeys("This is item creation");
		
		sam.driver.findElement(By.id("btnAction")).click();
		sam.driver.findElement(By.xpath("//div[@class='ajs-footer']/div[2]/button[1]")).click();
		
		Sleeper.sleepTight(5000);
		String actval=sam.driver.findElement(By.xpath("//*[@class='ajs-dialog']/div[3]/div/div")).getText();
		System.out.println(actval);
		sam.driver.findElement(By.xpath("//div[@class='ajs-footer']/div[2]/button")).click();
		
		Sleeper.sleepTightInSeconds(5);
		//validation
		String count=sam.driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[2]/form/div[2]/span[2]")).getText();
		String[] split=count.split(" ");
//		System.out.println(split.length);
		int count1=Integer.parseInt(split[2]);
		int pcount=0;
boolean flag = false;
		//		System.out.println(split[0]+"--"+split[1]+"--"+split[2]);
		do 
		{
			List<WebElement> rows=sam.driver.findElements(By.xpath("//*[@id='tbl_a_stock_categorieslist']/tbody/tr"));
			for (int i = 0; i < rows.size(); i++) 
			{
				List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
				String acttext=cols.get(6).getText();
//				System.out.println(acttext);
				if (acttext.equalsIgnoreCase(stItemno)) 
				{
//					System.out.println("category available");
					flag=true;
					break;
				}
			
			}
			if (flag==false) 
			{
				sam.driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/div/div/div[2]/a[1]")).click();
			}
			pcount++;
			
		} 
		while (pcount<=count1);
		if (flag==true) 
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("Fail");;
		}
		res=sam.stockAcc_Logout();
		System.out.println(res);
		
		sam.stockAcc_Close();
	}

}
