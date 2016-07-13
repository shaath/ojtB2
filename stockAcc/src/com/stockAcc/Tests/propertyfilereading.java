package com.stockAcc.Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class propertyfilereading 
{

	public static void main(String[] args) throws IOException 
	{
		FileInputStream fi=new FileInputStream("E:\\stockAccounting\\stockAcc\\src\\com\\stockAcc\\Properties\\config.properties");
		Properties pr=new Properties();
		pr.load(fi);
		
		System.out.println(pr.getProperty("url"));
		
		System.out.println(pr.getProperty("login"));

	}

}
