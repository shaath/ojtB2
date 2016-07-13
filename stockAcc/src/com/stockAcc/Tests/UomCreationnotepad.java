package com.stockAcc.Tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.stockAcc.Master.stockAccMaster;

public class UomCreationnotepad {

	public static void main(String[] args) throws IOException
	{
		stockAccMaster sam=new stockAccMaster();
		String uIn="E:\\stockAccounting\\stockAcc\\src\\com\\stockAcc\\Testdata\\UOM.txt";
		String Uout="E:\\stockAccounting\\stockAcc\\src\\com\\stockAcc\\Results\\UOMOUT.txt";
		FileReader fi=new FileReader(uIn);
		BufferedReader br=new BufferedReader(fi);
		String x="";
		System.out.println(br.readLine());
//		System.out.println(br.readLine());
		
		FileWriter fw=new FileWriter(Uout);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("Uid&Desc&Result");
		bw.newLine();
		sam.stockAcc_Launch("http://webapp.qedgetech.com");
		sam.stockAcc_Login("admin", "master");
		while ((x=br.readLine())!=null)
		{
			System.out.println(x);
			String[] split=x.split("&");
			String Uid=split[0];
			String Udesc=split[1];
			System.out.println(Uid+"----------"+Udesc);
			String res=sam.stockAcc_UnitofMcreation(Uid, Udesc);
			
			bw.write(x+"&"+res);
			bw.newLine();
		}
		bw.close();
		br.close();
		sam.stockAcc_Logout();
		sam.stockAcc_Close();
	}

}
