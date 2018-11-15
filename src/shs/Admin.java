package shs;

import java.sql.*;
import java.util.*;

public class Admin extends Person 
{
	
	Scanner sc=new Scanner(System.in);
	Credentials loginCredentials;
	public Admin() {
		loginCredentials = new Credentials();
		// TODO Auto-generated constructor stub
		this.loginCredentials.setId("ADMIN@123");
		this.loginCredentials.setPassword("admin@123");
		
		
	}
	int adminLogin(){
		System.out.println("Enter Username: ");
		String u=SmartHealthCareSystem.nextintString();
		System.out.println("Enter Password: ");
		String p=SmartHealthCareSystem.nextintString();
		if(u=="ADMIN@123" && p=="admin@123")
			return 1;
		else 
			return 0;
	}
	
	void getDoctorDetails()
	{
		Date dob = new Date();
		int i=1;
		try{
		Statement stmt = SmartHealthCareSystem.con.createStatement();
		boolean status = stmt.execute("SELECT Name, Did FROM Doctor");
		if(status){
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String DName = rs.getString("Name");
				int id=rs.getInt("Did");
				System.out.println(id+". "+DName + "\n");
			}
			System.out.println("Enter Doctor ID for more details or press 0 to exit: ");
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
		else{
			System.out.println("Error in getting Data");
		}
		}
		catch(Exception e){
			System.out.println("Exception " + e.getMessage().toString());
		}
		
	}
	void getPatientDetails()
	{
		int i=1;
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = stmt.execute("SELECT Name, Pid FROM patient");
			if(status){
				ResultSet rs = stmt.getResultSet();
				while (rs.next()) {
				  String pName = rs.getString("Name");
				  int pid=rs.getInt("Pid");
				  System.out.println(pid+". "+pName + "\n");
				}
				int c=0;
				while(c!=3){
					System.out.println("Enter your choice: ");
					System.out.println("1.View patient personal details\n2.View patient history\n3.Exit\n");
					c=SmartHealthCareSystem.nextint();
					if(c==1){
						showPatientDetails();
					}
					else if(c==2){
						showPatientHistory();
					}
				}
			}
			else
			{
				System.out.println("Error in getting Data");
			}
		}
		catch(Exception e){
			System.out.println("Exception " + e.getMessage().toString());
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
        int pass=sc.SmartHealthCareSystem.nextint();
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
        	boolean status = statement.execute("Insert into Doctor values("+id+", "+name+", "+dob+", "+gen+", "+add+", "+cno+", "+pass+", "+depid+", "+rank+", "+sur+", "+fee+")");
        	if(status)
        	{
        		System.out.println("Doctor has been successfully registered.");
        	}
        	else
        	{
        		System.out.println("Could not register doctor. Please try again");
        	}
        }
        catch(Exception e){
        	System.out.println("Exception " + e.getMessage().toString());
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
			String query = "update table patient set"
					+" name=" + getName()
					+ " dob=" + getDob()
					+ " gender=" +getGender()
					+ " address=" + getAddress()
					+ " contactno=" + getPhoneNumber()
					+ " password=" + getPassword()
					+" where pid="+ getPid();
		            int count = statement.executeUpdate(query);
					if(count == 1){
		                System.out.println("Record has been updated successfully");
		            }
		            else
		            {
		            	System.out.println("Record not updated.Please try again");
		            }
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
		else
		{
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
			boolean status = statement.execute("SELECT * FROM patient where Pid="+i);
			if(status){
                ResultSet detail= stmt.getResultSet();
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
			else
			{
				System.out.println("Error in getting Data");
			}
		}
		catch(Exception e){
			System.out.println("Exception " + e.getMessage().toString());
		}
	}
	void showPatientHistory(){
		Date ddate = new Date();
		Date adate = new Date();
		System.out.println("Enter patient ID whose history you want to view: ");
		int i=SmartHealthCareSystem.nextint();
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("SELECT * FROM record where Pid="+i);
			if(status){
                ResultSet detail= stmt.getResultSet();
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
			else{
				System.out.println("Error in getting Data");
			}
		}
		catch(Exception e){
			System.out.println("Exception " + e.getMessage().toString());
		}
	}
}
