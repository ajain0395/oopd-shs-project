package shs;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Admin extends Person 
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
		Date dob = new Date();
		int i=1;
		try{
		Statement stmt = SmartHealthCareSystem.con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Name FROM Doctor");
		while (rs.next()) {
			  String DName = rs.getString("Name");
			  System.out.println(i+". "+DName + "\n");
			  i++;
			}
		System.out.println("Enter Doctor ID for more details: ");
		int did=SmartHealthCareSystem.nextint();
		ResultSet detail = stmt.executeQuery("SELECT * FROM Doctor where Did="+did);
        while (detail.next()) {
           int id = detail.getInt("Did");
           String name = detail.getString("Name");
           dob = detail.getDate("DOB");
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
           System.out.println("DOB: "+dob.toString());
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
		int i=1;
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Name FROM patient");
			while (rs.next()) {
				  String pName = rs.getString("Name");
				  System.out.println(i+". "+pName + "\n");
				  i++;
				}
			System.out.println("Enter your choice: ");
			System.out.println("1.View patient personal details\n2.View patient history\n");
			int c=SmartHealthCareSystem.nextint();
			if(c==1){
				showPatientDetails();
			}
			else if(c==2){
				showPatientHistory();
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	void registerDoctor()
	{
		System.out.println("Enter the following registration details: ");
		System.out.println("Doctor Id: ");
		int id=SmartHealthCareSystem.nextint();
		System.out.println("Name");
		String name=sc.nextLine();
		System.out.println("DOB");
		String dob=sc.nextLine();
		System.out.println("Gender: ");
		String gen=sc.nextLine();
        System.out.println("Address: ");
        String add=sc.nextLine();
        System.out.println("Contact No: ");
        String cno=SmartHealthCareSystem.nextintString();
        System.out.println("Password: ");
        int pass=sc.nextInt();
        System.out.println("Department ID: ");
        String depid=sc.nextLine();
        System.out.println("Rank: ");
        String rank=sc.nextLine();
        System.out.println("Surgeon: ");
        String sur=sc.nextLine();
        System.out.println("OPD Fees: ");
        int fee=SmartHealthCareSystem.nextint();
        try{
        	Statement stmt = SmartHealthCareSystem.con.createStatement();
    		ResultSet rs = stmt.executeQuery("Insert into Doctor values("+id+", "+name+", "+dob+", "+gen+", "+add+", "+cno+", "+pass+", "+depid+", "+rank+", "+sur+", "+fee+")");
    		System.out.println("Doctor has been successfully registered.");
        }
        catch(Exception e){
        	System.out.println(e);
        }

	}
	void reassignDoctor()
	{
		System.out.println("Enter Record no where you want to reassign doctor: ");
		int rno=SmartHealthCareSystem.nextint();
		System.out.println("Enter ID of doctor to assign to record no:"+rno);
		int did=SmartHealthCareSystem.nextint();
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			ResultSet detail = stmt.executeQuery("UPDATE record SET Did="+did+" WHERE RecId="+rno);
			System.out.println("You have successfully reassiged doctor to Record No: "+rno);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	void changeCredentials()
	{
		System.out.println("Verify credentials to continue further: ");
		System.out.println("Username: ");
		String uname=sc.nextLine();
		System.out.println("Password: ");
		String pass=sc.nextLine();
		if(uname=="ADMIN@123" && pass=="admin@123"){
			System.out.println("Enter new Username:");
			String u=sc.nextLine();
			System.out.println("Enter new Password:");
			String p=sc.nextLine();
			this.loginCredentials.setId(u);
			this.loginCredentials.setPassword(p);
			System.out.println("Credentials successfully updated!");
			
		}
		else{
			System.out.println("Invalid username or password!");
		}
		
	}
	void showAdminOptions()
	{
		int c=6;
		System.out.println("Select an option: ");
		while(c!=0){
			System.out.println("1. View doctor details\n2. View patient details\n3. Register Doctor\n4. Reassign a doctor\n5. Change login credentials\n0.Log-out");
			c=sc.nextInt();
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
	void showPatientDetails()
	{
		Date dob = new Date();
		System.out.println("Enter patient ID whose details you want to view: ");
		int i=SmartHealthCareSystem.nextint();
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			ResultSet detail = stmt.executeQuery("SELECT * FROM patient where Pid="+i);
			while (detail.next()) {
		           int id = detail.getInt("Pid");
		           String name = detail.getString("Name");
		           dob=detail.getDate("DOB");
		           String gen = detail.getString("Gender");
		           String add = detail.getString("Address");
		           String cno = detail.getString("ContactNo");
		           String pass = detail.getString("Password");
		           System.out.println("ID: "+id);
		           System.out.println("Name: "+name);
		           System.out.println("DOB: "+dob);
		           System.out.println("Gender: "+gen);
		           System.out.println("Address: "+add);
		           System.out.println("Contact No: "+cno);
		           System.out.println("Password: "+pass);
		        }
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	void showPatientHistory(){
		Date ddate = new Date();
		Date adate = new Date();
		System.out.println("Enter patient ID whose history you want to view: ");
		int i=SmartHealthCareSystem.nextint();
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			ResultSet detail = stmt.executeQuery("SELECT * FROM record where Pid="+i);
			System.out.println("History for patient ID: "+i+"is shown below: ");
			while (detail.next()) {
				   int recid=detail.getInt("RecId");
				   int pid=detail.getInt("Pid");
				   adate=detail.getDate("Admit_Date");
		           ddate=detail.getDate("Discharge_date");
		           String desc= detail.getString("Patient_Desc");
		           String diden = detail.getString("Disease_Identified");
		           String loc = detail.getString("Location");
		           int did = detail.getInt("Did");
		           System.out.print("Record no: "+recid);
		           System.out.print("Admit date: "+adate.toString());
		           System.out.print("Discharge date: "+ddate.toString());
		           System.out.print("Description: "+desc);
		           System.out.print("Disease Identified: "+diden);
		           System.out.print("Location: "+loc);
		           System.out.print("Doctor ID: "+did);
		        }
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
