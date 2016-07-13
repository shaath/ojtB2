package com.stockAcc.Tests;

import java.io.IOException;

import com.stockAcc.Master.stockAccMaster;

public class logintest {

	public static void main(String[] args) throws IOException
	{
		stockAccMaster sm=new stockAccMaster();
		String res=sm.stockAcc_Launch(sm.url);
		System.out.println(res);

	}

}