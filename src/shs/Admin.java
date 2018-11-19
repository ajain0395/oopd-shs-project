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
		logger.info("adminLogin Entry");
		System.out.println("Enter Username: ");
		String u=SmartHealthCareSystem.sc.nextLine();
		System.out.println("Enter Password: ");
		String p=SmartHealthCareSystem.sc.nextLine();
		if(u.equals("ADMIN@123") && p.equals("admin@123"))
			return 1;
		else 
			return 0;
	}
	
	void getDoctorDetails()
	{
		logger.info("getDoctorDetails Entry");
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
                    logger.warning("Invalid doctor ID entered");
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
				logger.warning("DB returned empty set");

			}
        }
		else{
			System.out.println("Error in getting Data");
			logger.warning("SELECT query returned false status");

		}
		}
		catch(Exception e){
			System.out.println("Exception " + e.getMessage().toString());
		}
		logger.info("getDoctorDetails Exit");
		
	}
	void getPatientDetails()
	{
		logger.info("getPatientDetails Entry");
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
						logger.warning("Invalid choice");
					}
				}
			}
			else
			{
				System.out.println("Error in getting Data");
				logger.warning("Could not get data from DB");
			}
		}
		catch(Exception e){
			System.out.println("Exception " + e.getMessage().toString());
		}
		logger.info("getPatientDetails Exit");
	}
	void registerDoctor()
	{
		logger.info("registerDoctor Entry");
		System.out.println("Enter the following registration details: ");
		System.out.println("Name");
		String name=SmartHealthCareSystem.sc.nextLine();
		Date dob;
		while(true){
			System.out.print("DOB(YYYY-MM-DD): ");
			try{
				dob=SmartHealthCareSystem.simpleDateFormat.parse(SmartHealthCareSystem.sc.nextLine());
				break;
			}
			catch(ParseException e){
				System.out.println("Invalid Date Format");
				logger.warning("Invalid Date entered");
			}
		}
		String gen;
		while(true)
		{
			System.out.print("1. Male\n2. Female\n: ");
			int ch = 0;
			ch = SmartHealthCareSystem.nextint();
			if(ch == 1)
			{
				gen= "M";
				break;
			}
			else if(ch == 2)
			{
				gen= "F";
				break;
			}
			else
			{
				System.out.println("Invalid Input");
				logger.warning("Invalid gender choice");
			}
		}
        System.out.println("Address: ");
        String add=SmartHealthCareSystem.sc.nextLine();
        System.out.println("Contact No: ");
        String cno=SmartHealthCareSystem.nextintString();
        System.out.println("Password: ");
        String pass=SmartHealthCareSystem.sc.nextLine();
        System.out.println("Select Department ID: ");
        int depid=0;
        try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = stmt.execute("SELECT DeptId,DeptName FROM department");
			if(status){
				ResultSet rs = stmt.getResultSet();
				while(rs.next()) {
				  String dName = rs.getString("DeptName");
				  int did=rs.getInt("DeptId");
				  System.out.println(did+". "+dName + "\n");
				}
				depid=SmartHealthCareSystem.nextint();
			}
            else
            {
            	System.out.println("Unable to fetch data from table department");
            	logger.warning("Could not get data from department");

            }
		
		}
		catch(Exception e)
		{
			System.out.println("Exception " + e.getMessage().toString());
		}
        String rank;
        while(true){
        	System.out.println("Select rank: 1. Resident\n2. Senior Resident\n3. Specialist\n4. Senior Specialist\n");
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
        		logger.warning("Invalid Rank choice");
        }
        String sur;
        while(true){
        	System.out.println("Surgeon : 1. NO\n2.Surgeon\n3.Senior Surgeon\n");
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
        		logger.warning("Invalid Surgeon choice");
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
            pstmt.setString(2,SmartHealthCareSystem.javaDateToString(dob));
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
        logger.info("registerDoctor Exit");
		

	}
	void reassignDoctor()
	{
		logger.info("reassignDoctor Entry");
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = stmt.execute("SELECT * FROM record WHERE Discharge_Date IS NULL");
			if(status){
				ResultSet rs = stmt.getResultSet();
				System.out.println("Record ID\tPatient ID\tAdmit Date\tPatient Description\tDisease Identified\tLocation");
				while(rs.next()) {
				  int rid= rs.getInt("RecId");
				  int pid= rs.getInt("Pid");
				  Date adate= rs.getDate("Admit_Date");
				  String desc= rs.getString("Patient_Desc");
				  String diden=rs.getString("Disease_Identified");
				  String loc= rs.getString("Location");
				  int did= rs.getInt("Did");
				  System.out.println(rid+"\t\t\t"+pid+"\t"+adate+"\t\t"+desc+"\t"+diden+"\t"+loc+"\t"+did);
				}
			}
            else
            {
            	System.out.println("No record found to be eligible for reassigning doctor");
            	logger.warning("No record with NULL discharge date in DB");
            }
		
		}
		catch(Exception e)
		{
			System.out.println("Exception " + e.getMessage().toString());
		}
		int c=0,rno;
		while(true)
		{
			System.out.println("Enter Record no where you want to reassign doctor: ");
			rno=SmartHealthCareSystem.nextint();
			try{
				Statement s= SmartHealthCareSystem.con.createStatement();
				boolean stat = s.execute("SELECT * FROM record WHERE RecId="+rno);
				if(stat){
					ResultSet rs = s.getResultSet();
					while(rs.next()) {
						++c;
					}
					if(c==1)
						break;
					else{
						System.out.println("You entered wrong Record ID, please try again");
						logger.warning("Invalid record id entered");
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		int did,o=0;
		while(true)
		{
			System.out.println("Enter ID of doctor to assign to record no:"+rno);
			did=SmartHealthCareSystem.nextint();
			try{
				Statement s= SmartHealthCareSystem.con.createStatement();
				boolean stat=s.execute("SELECT * FROM doctor WHERE Did="+did);
				if(stat){
					ResultSet rs = s.getResultSet();
					while(rs.next()) {
						++o;
					}
					if(o==1)
						break;
					else{
						System.out.println("You entered wrong Doctor ID, please try again");
						logger.warning("Invalid doctor id entered");
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			String query = "update record set"
					+" Did='" +did + "'"
					+" where Recid="+ rno;
		    int count = stmt.executeUpdate(query);		
			if(count == 1){
	            System.out.println("Record Updated");
	        }
			else
			{
				System.out.println("Record not updated try again");
		    }
		}
		catch(Exception e){
			System.out.println(e);
		}
		logger.info("reassignDoctor Exit");
	}
	void changeCredentials()
	{
		logger.info("changeCredentials Entry");
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
			logger.warning("Invalid username or password entered");
		}
		logger.info("changeCredentials Exit");
		
	}
	void showAdminOptions()
	{
		logger.info("showAdminOptions Entry");
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
				logger.warning("Invalid admin option choice");
		}
		logger.info("showAdminOptions Exit");
	}
	void registerDept()
	{
		logger.info("registerDept Entry");
		System.out.println("To register a new department, enter the following details: ");
		System.out.println("Department Name: ");
		String dname=SmartHealthCareSystem.sc.nextLine();
		try{
			Statement stmt = SmartHealthCareSystem.con.createStatement();
			boolean status = stmt.execute("SELECT Did,Name FROM doctor WHERE Rank='senior specialist' AND Did NOT IN(Select Did from department)");
			if(status){
				ResultSet rs = stmt.getResultSet();
				while(rs.next()) {
				  String dName = rs.getString("Name");
				  int did=rs.getInt("Did");
				  System.out.println(did+". "+dName + "\n");
				}
			}
            else
            {
            	System.out.println("Unable to fetch data from table department");
            }
			System.out.println("Select ID of HOD from list: ");
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
		logger.info("registerDept Exit");
		
	}
	void removeDoctor()
	{
		logger.info("removeDoctor Entry");
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
		logger.info("removeDoctor Exit");
	}
	void showPatientDetails()
	{
		logger.info("showPatientDetails Entry");
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
		logger.info("showPatientDetails Exit");
	}
	void showPatientHistory(){
		logger.info("showPatientHistory Entry");
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
		logger.info("showPatientHistory Exit");
	}
}
