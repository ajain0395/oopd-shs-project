package shs;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SmartHealthCareSystem implements Billing {
	
	/*
	 * Ashish Jain
	 * Date date = simpleDateFormat.parse("12-01-2018");
	 *  
	 *  String pattern = "MM-dd-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
	 */
	public static Scanner sc = new Scanner(System.in);
	static String pattern = "yyyy-MM-dd";
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	public static int nextint()
	{
		boolean flag = true;
		String str = null;
		while(flag)
		{
			str = sc.nextLine();
			if(!str.matches("[0-9]+"))
			{
				System.out.println("Invalid input enter numbers only");
			}
			else
			{
				break;
			}
		}
		return Integer.parseInt('0'+ str);
	}
	
	Doctor registerDoctor()
	{
		
		return null;
	}
	
	Patient admitPatient()
	{
		
		return null;
	}
	
	void login()
	{
		
	}
	public void generateBill(Patient p)
	{
		
	}
	void displayDoctors()
	{
		
	}
	void displayWards()
	{
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

	}

}
