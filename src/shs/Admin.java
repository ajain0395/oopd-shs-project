package shs;

import java.sql.*;
import java.util.*;

public class Admin 
{
	Scanner sc=new Scanner(System.in);
	Credentials loginCredentials;
	public Admin() {
		// TODO Auto-generated constructor stub
		this.loginCredentials.setId("ADMIN@123");
		this.loginCredentials.setPassword("admin@123");
	}
	
	void getDoctorDetails()
	{
		int i=1;
		try{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Name FROM Doctor;");
		while (rs.next()) {
			  String DName = rs.getString("Name");
			  System.out.println(i+". "+DName + "\n");
			  i++;
			}
		System.out.println("Enter Doctor ID for more details: ");
		int did=sc.nextInt();
		ResultSet detail = stmt.executeQuery("SELECT * FROM Doctor");
        while (detail.next()) {
           int id = detail.getInt("Did");
           String name = detail.getString("Name");
           String dob = detail.getString("DOB");
           String gen = detail.getString("Gender");
           String add = detail.getString("Address");
           String cno = detail.getString("ContactNo");
           String pass = detail.getString("Password");
           int dpid = detail.getInt("DeptId");
           String rank = detail.getString("Rank");
           String sur = detail.getString("Surgeon");
           int fee = detail.getInt("OpdFees");
           System.out.println("ID: "+id);
           System.out.println("Name: "+name);
           System.out.println("DOB: "+dob);
           System.out.println("Gender: "+gen);
           System.out.println("Address: "+add);
           System.out.println("Contact No: "+cno);
           System.out.println("Password: "+pass);
           System.out.println("Department ID: "+dpid);
           System.out.println("Rank: "+rank);
           System.out.println("Surgeon: "+sur);
           System.out.println("OPD Fees: "+fee+" Rs");
        }
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
	void getPatientDetails()
	{

	}
	void registerDoctor()
	{
		
	}
	void reassignDoctor()
	{
		
	}
	void changeCredentials()
	{
		
	}
	void showAdminOptions()
	{
		System.out.println("Select an option: ");
		System.out.println("1. View doctor details\n2. View patient details\n3. Register Doctor\n4. Reassign a doctor\n5. Change login credentials");
		int c=sc.nextInt();
		if(c==1)
			getDoctorDetails();
		else if(c==2)
			getPatientDetails();
		else if(c==3)
			registerDoctor();
		else if(c==4)
			reassignDoctor();
		else if(c==5)
			changeCredentials();
		else
			System.out.println("You entered wrong choice!");
	}
}
