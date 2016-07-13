package com.stockAcc.Examples;

import java.util.Scanner;

public class stringrev {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		String original=sc.next();
		
		System.out.println(original);
		System.out.println(original.length());
		for (int i = original.length()-1; i >= 0; i--) 
		{
			char s=original.charAt(i);
			System.out.print(s);
		}
	}
}
