package com.stockAcc.Tests;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.server.browserlaunchers.Sleeper;

public class webtable {

	public static void main(String[] args)
	{
		int cnt=0;
		boolean flag=false;
		List<WebElement> cols;
		String expval="Supplier-00000000355";
		WebDriver driver=new FirefoxDriver();
		driver.get("http://webapp.qedgetech.com");
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("master");
		driver.findElement(By.id("btnsubmit")).click();

		driver.findElement(By.linkText("Suppliers")).click();
		List<WebElement> rows=driver.findElements(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr"));
		System.out.println(rows.size());
		
		String s=driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/span[2]")).getText();
		String[] split=s.split(" ");
		System.out.println(split.length);
		System.out.println(split[0]+"---"+split[1]+"----"+split[2]);
		int pcnt=Integer.parseInt(split[2]);
		do 
		{
			for (int i = 0; i < rows.size(); i++)
				{
					cols=rows.get(i).findElements(By.tagName("td"));
					String sId=cols.get(5).getText();
					if (sId.equalsIgnoreCase(expval)) 
					{
						flag=true;
						break;
					}
					
				}
			if (flag==false)
			{
				driver.findElement(By.xpath(".//*[@id='ewContentColumn']/div[3]/div[1]/form/div[2]/div/div/div[2]/a[1]")).click();
				Sleeper.sleepTightInSeconds(5);
				rows=driver.findElements(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr"));
				
			}
			else
			{
				System.out.println("Pass");
				break;
			}
			
			cnt++;
		} 
		while (cnt<=pcnt);
//		for (int i = 0; i < rows.size(); i++)
//		{
//			List<WebElement> cols=rows.get(i).findElements(By.tagName("td"));
//			
////			System.out.println(cols.size());
////			System.out.println(cols.get(5).getText());
//			String sId=cols.get(5).getText();
//			if (sId.equalsIgnoreCase(expval)) 
//			{
//				System.out.println("Pass");
//			}
//			
//		}
	}

}
