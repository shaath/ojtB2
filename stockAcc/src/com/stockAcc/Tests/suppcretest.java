package com.stockAcc.Tests;

import java.io.IOException;

import com.stockAcc.Master.stockAccMaster;

public class suppcretest {

	public static void main(String[] args) throws IOException {
stockAccMaster sam=new stockAccMaster();
		
		String res=sam.stockAcc_Launch("http://webapp.qedgetech.com");
		System.out.println(res);
		
		res=sam.stockAcc_Login("admin", "master");
		System.out.println(res);
		
		res=sam.stockAcc_SupplierCreation("sup1234567", "Ameerpet", "Hyderabad", "INDIA", "Vignan", "04022222222", "vignan@gmail.com","9898989898", "this upplier supplies raw material");
		System.out.println(res);
		
		res=sam.stockAcc_Logout();
		System.out.println(res);
		
		sam.stockAcc_Close();

	}

}
