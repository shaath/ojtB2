package com.stockAcc.Examples;

public class functionsdemo 
{
	public static void main(String[] args)
	{
		function1();
		String res=function2();
		System.out.println(res);
		
		functionsdemo fd=new functionsdemo();
		
		fd.function3();
		
		res=fd.function4();
		System.out.println(res);
				
	}
	
	//static funcion without returning any value
	public static void function1()
	{
		System.out.println("This is funcion1 code");
	}
	
	//static function with returning value
	public static String function2()
	{
		System.out.println("This is function2 code");
		return "Pass";
	}
	
	//non static function without returning value
	
	public void function3()
	{
		System.out.println("This is function3 code");
	}
	
	//non static function with returning value
	public String function4()
	{
		System.out.println("This is function4 code");
		return "Pass";
	}
	

}
