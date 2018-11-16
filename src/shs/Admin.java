package shs;

import java.sql.*;
import java.text.ParseException;
import java.util.*;
import java.util.Date;

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
		boolean status = stmt.execute("SELECT Name, Did FROM doctor");
		if(status){
			ResultSet rs = stmt.getResultSet();
			while (rs.next()) {
				String DName = rs.getString("Name");
				int id=rs.getInt("Did");
				System.out.println(id+". "+DName + "\n");
			}
			System.out.println("Enter Doctor ID for more details or press 0 to exit: ");
			int did=SmartHealthCareSystem.nextint();
			if(did==0)
				return;
			Statement statement= SmartHealthCareSystem.con.createStatement();
			boolean stat=statement.execute("SELECT * FROM doctor where Did="+did);
			if(stat){
				ResultSet detail= statement.getResultSet();
                if(!detail.isBeforeFirst()){
                    System.out.println("This Doctor ID does not exist, please try again");
                    return;
                }
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
					String hosp=detail.getString("Hospital");
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
					System.out.print("Hospital: "+hosp+"\n");
				}
			}
			else
			{
				System.out.println("Error in getting Data");
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
				while(rs.next()) {
				  String pName = rs.getString("Name");
				  int pid=rs.getInt("Pid");
				  System.out.println(pid+". "+pName + "\n");
				}
				int c=7;
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
					else if(c==3)
					{
						return;
					}
					else
					{
						System.out.println("Wrong choice. Please enter a valid no.");
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
		System.out.println("Name");
		String name=SmartHealthCareSystem.sc.nextLine();
		java.sql.Date dob;
		while(true)
		{
			System.out.print("Enter new Appointment Date(YYYY-MM-DD): ");
			dob= java.sql.Date.valueOf(SmartHealthCareSystem.sc.nextLine());
			break;
		}
		String gen;
		while(true)
		{
			System.out.print("Gender(M/F): ");
			gen=SmartHealthCareSystem.sc.nextLine();
			if(gen.equalsIgnoreCase("female")||gen.equalsIgnoreCase("male")||gen.equalsIgnoreCase("m")||gen.equalsIgnoreCase("f"))
			{
				if(gen.equalsIgnoreCase("female")||gen.equalsIgnoreCase("f"))
					gen="F";
				else
					gen="M";
				break;
			}
			else{
			// TODO Auto-generated catch block
				System.out.println("Invalid Gender input");
			}
		}
        System.out.println("Address: ");
        String add=SmartHealthCareSystem.sc.nextLine();
        System.out.println("Contact No: ");
        String cno=SmartHealthCareSystem.nextintString();
        System.out.println("Password: ");
        String pass=SmartHealthCareSystem.sc.nextLine();
        System.out.println("Department ID: ");
        int depid=SmartHealthCareSystem.nextint();
        String rank;
        while(true){
        	System.out.println("Select rank: 1. Resident\n2. Senior Resident\n3. Specialist\n4. Senior Specialist");
        	int r=SmartHealthCareSystem.nextint();
        	if(r==1){
        		rank="Resident";
        		break;
        	}
        	else if(r==2){
        		rank="Senior Resident";
        		break;
        	}
        	else if(r==3){
        		rank="Specialist";
        		break;
        	}
        	else if(r==4){
        		rank="Senior Specialist";
        		break;
        	}
        	else
        		System.out.println("Invalid input. Please try again.");
        }
        String sur;
        while(true){
        	System.out.println("Surgeon : 1. NO\n2.Surgeon\n3.Senior Surgeon");
        	int r=SmartHealthCareSystem.nextint();
        	if(r==1){
        		sur="NO";
        		break;
        	}
        	else if(r==2){
        		sur="Surgeon";
        		break;
        	}
        	else if(r==3){
        		sur="Senior Surgeon";
        		break;
        	}
        	else
        		System.out.println("Invalid input. Please try again.");
        }
        System.out.println("OPD Fees: ");
        int fee=SmartHealthCareSystem.nextint();
        System.out.println("Hospital: ");
        String hosp=SmartHealthCareSystem.sc.nextLine();
        try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
		
			String query ="INSERT INTO doctor(Name, DOB, Gender, Address, Contactno, Password, DeptId, Rank, Surgeon, OpdFees, Hospital) VALUES "
					+ "(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = null;
            pstmt = SmartHealthCareSystem.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,name);
            pstmt.setString(3,gen);
            pstmt.setDate(2,dob);
            pstmt.setString(4,add);
            pstmt.setString(5,cno);
            pstmt.setString(6,pass);
            pstmt.setInt(7,depid);
            pstmt.setString(8,rank);
            pstmt.setString(9,sur);
            pstmt.setInt(10,fee);
            pstmt.setString(11,hosp);
            pstmt.executeUpdate();
	           ResultSet rs = pstmt.getGeneratedKeys();
	            if(rs != null && rs.next()){
	                System.out.println("New Doctor registered.");
	            }
            else
            {
            	System.out.println("Registration could not be done.");
            }
		}
        catch (Exception e) {
            	
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
					+ " password=" + loginCredentials.getPassword();
		            int count = stmt.executeUpdate(query);
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
		String uname=SmartHealthCareSystem.sc.nextLine();
		System.out.println("Password: ");
		String pass=SmartHealthCareSystem.sc.nextLine();
		if(uname.equals("ADMIN@123") && pass.equals("admin@123")){
			System.out.println("Enter new Username:");
			String u=SmartHealthCareSystem.sc.nextLine();
			System.out.println("Enter new Password:");
			String p=SmartHealthCareSystem.sc.nextLine();
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
		int c=10;
		System.out.println("Select an option: ");
		while(c!=0){
			System.out.println("1. View doctor details\n2. View patient details\n3. Register Doctor\n4. Reassign a doctor\n5. Change login credentials\n6. Remove Doctor\n7. Register new department\n0. Log-out");
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
			else if(c==6)
				removeDoctor();
			else if(c==7)
				registerDept();
			else if(c==0)
			{
				System.out.println("You have successfully logged out!");
				return;
			}
			else
				System.out.println("You entered wrong choice!");
		}
	}
	void registerDept()
	{
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			System.out.println("To register a new department, enter the following details: ");
			System.out.println("Department Name: ");
			String dname=SmartHealthCareSystem.sc.nextLine();
			System.out.println("Doctor ID of HOD: ");
			int id=SmartHealthCareSystem.nextint();
			String query = "INSERT INTO department(DeptName, Did) VALUES "+ "(?,?)";
			PreparedStatement pstmt = null;
            pstmt = SmartHealthCareSystem.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(2,id);
            pstmt.setString(1,dname);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs != null && rs.next()){
	                System.out.println("New Department added.");
	            }
            else
            {
            	System.out.println("Unable to register new department");
            }
		
		}
		catch(Exception e)
		{
			System.out.println("Exception " + e.getMessage().toString());
		}
		
	}
	void removeDoctor()
	{
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = stmt.execute("SELECT Did,Name,DeptId,OpdFees,Hospital,Rank FROM doctor");
			if(status){
				ResultSet rs = stmt.getResultSet();
				System.out.println("ID\tName\tDeptID\tFees\tHospital\t\tRank");
				while (rs.next()) {
					String DName = rs.getString("Name");
					int id=rs.getInt("Did");
					int did=rs.getInt("DeptId");
					int fee=rs.getInt("OpdFees");
					String hosp=rs.getString("Hospital");
					String rank=rs.getString("Rank");
					System.out.println(id+"\t"+DName+"\t"+did+"\t"+fee+"\t"+hosp+"\t"+rank);
				}
				System.out.println("Enter Doctor ID to remove or press 0 to exit: ");
				int id=SmartHealthCareSystem.nextint();
				if(id==0)
					return;
				Statement statement= SmartHealthCareSystem.con.createStatement();
				statement.execute("DELETE from doctor WHERE Did="+id);
				ResultSet detail= statement.getResultSet();
	            System.out.println("Doctor successfully removed");
					
			}
			else{
				System.out.println("Unable to perform operation");
			}
			
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	void showPatientDetails()
	{
		Date dob = new Date();
		System.out.println("Enter patient ID whose details you want to view: ");
		int i=SmartHealthCareSystem.nextint();
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = stmt.execute("SELECT * FROM patient where Pid="+i);
			if(status){
                ResultSet detail= stmt.getResultSet();
                if(!detail.isBeforeFirst()){
                    System.out.println("This Patient ID does not exist, please try again");
                    return;
                }
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
		System.out.println("Enter patient ID whose history you want to view: ");
		int i=SmartHealthCareSystem.nextint();
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = stmt.execute("SELECT * FROM record where Pid="+i);
			if(status){
                ResultSet detail= stmt.getResultSet();
                if(!detail.isBeforeFirst()){
                    System.out.println("This Patient ID does not exist, please try again");
                    return;
                }
                System.out.println("History for patient ID "+i+" is shown below: ");
                while (detail.next()) {
                	//System.out.println("hello");
				   int recid=detail.getInt("RecId");
				   int pid=detail.getInt("Pid");
				   Date date = detail.getDate("discharge_date");
		           String desc= detail.getString("Patient_Desc");
		           String diden = detail.getString("Disease_Identified");
		           String loc = detail.getString("Location");
		           int did = detail.getInt("Did"); 
		           System.out.println("Record no: "+recid);
		           System.out.println("Admit date: "+SmartHealthCareSystem.simpleDateFormat.format(detail.getDate("admit_date")));
		           if(date!=null)
              			System.out.println("Discharge Date: " + SmartHealthCareSystem.simpleDateFormat.format(date));
		           System.out.println("Description: "+desc);
		           System.out.println("Disease Identified: "+diden);
		           System.out.println("Location: "+loc);
		           System.out.println("Doctor ID: "+did);
		           System.out.println("----------x----------x----------x----------x----------x----------");
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
