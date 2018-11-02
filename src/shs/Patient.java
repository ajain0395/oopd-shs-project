package shs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;

public class Patient extends Person {
	
	int pid;
	String password;
	
	public Patient(String name, Date dob,String gender, String address, String phonenumber,int pid,String password) {
		// TODO Auto-generated constructor stub
		super(name, dob, gender, address, phonenumber);
		this.setPid(pid);
		this.setPassword(password);
	}
	public static Patient getPatientById(int pid,String password)
	{
		Patient instance = null;
     //   System.out.println("Hello ");
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from patient where pid = "+ pid +" and password = "+ password);
           // System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();
                if(rs.next() == false)
                {
                	rs.close();
                	return null;
                }
                else
                {
                rs.beforeFirst();
                while(rs.next())
                {
                    instance = new Patient(rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("address"), rs.getString("ContactNo"), rs.getInt("pid"), rs.getString("password"));
                }
                }
            }
            else
            {
            	System.out.println("Error in getting Data");
            }
		}
            catch (Exception e) {
            	
            	System.out.println("Exception " + e.getMessage().toString());
}
		return instance;
	}
	void seeProfile()
	{
		System.out.println("ID: " + getPid());
		System.out.println("Name: " + getName());
		System.out.println("Date of Birth: " + SmartHealthCareSystem.simpleDateFormat.format(getDob()));
		//System.out.println("Gender: " + getGender());
		System.out.println("Address: " + getAddress());
		System.out.println("Phone Number: "+ getPhoneNumber());
	}
	void updateProfile()
	{
		 boolean flag2 = true;
	        while(flag2)
	        {
	            System.out.println("What do you want to update?\n");
	            
	            System.out.println("1. Name -> " );
	            System.out.println("2. Date of Birth -> ");
	            System.out.println("3. Gender -> ");
	            System.out.println("4. Address -> ");
	            System.out.println("5. Contact Number -> ");
	            System.out.println("6. Password ");
	            System.out.println("7. Done all Changes");
	            
	            int choice = SmartHealthCareSystem.nextint();
	            if(choice == 1)
	            {
	                changeName();
	                
	            }
	            else if(choice ==2)
	            {
	                changeDOB();
	            }
	            else if(choice == 3)
	            {

	                changeGender();
	            }
	            else if(choice ==4 )
	            {

	                changeAddress();
	            }
	            else if(choice ==5)
	            {

	                changeContactNumber();
	            }
	            else if(choice ==6 )
	            {

	                changePassword();
	            }
	            else if(choice ==7 )
	            {
	                flag2 = false;
	            }
	            else
	            {
	                System.out.println("Wrong input ");
	                
	            }
	        }
		
	}
	void bookAppointment()
	{
		
	}
	
	void updatePatient(/*Patient instance*/)
	{
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
		//	boolean status = statement.execute("select * from record where pid = "+ pid);
			
			/*String query = "update table patient set"
					+" name=" + instance.getName()
					+ " dob=" + instance.getDob()
					+ " gender=" +instance.getGender()
					+ " address=" + instance.getAddress()
					+ " contactno=" + instance.getPhoneNumber()
					+ " password=" + instance.getPassword()
					//+"salary"=2000 
					+" where pid="+ instance.getPid();
            //count will give you how many records got updated*/
			String query = "update table patient set"
			+" name=" + getName()
			+ " dob=" + getDob()
			+ " gender=" +getGender()
			+ " address=" + getAddress()
			+ " contactno=" + getPhoneNumber()
			+ " password=" + getPassword()
			//+"salary"=2000 
			+" where pid="+ getPid();
    //count will give you how many records got updated
            int count = statement.executeUpdate(query);
			
           // System.out.println("Hello " + status);
			if(count == 1){
                System.out.println("Record Updated");
            }
            else
            {
            	System.out.println("Record not updated try again");
            }
		}
            catch (Exception e) {
            	
            	System.out.println("Exception " + e.getMessage().toString());
}		
	}
	void setProfile()
	{
		System.out.print("Enter Name: ");
		setName(SmartHealthCareSystem.sc.nextLine());
		System.out.print("Enter Gender(M/F): ");
		setGender(SmartHealthCareSystem.sc.nextLine());
		while(true)
		{
		System.out.print("Enter Date of Birth(YYYY-MM-DD): ");
		try {
			
			setDob(SmartHealthCareSystem.simpleDateFormat.parse(SmartHealthCareSystem.sc.nextLine()));
			break;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		System.out.println("Invalid Date Format");
		}
		}
		
		while(true) {
			System.out.print("Enter Phone Number: ");
			setPhoneNumber(SmartHealthCareSystem.nextintString());
			if(getPhoneNumber().length() == 10)
			{
				break;
			}
			else
			{
				System.out.println("Invalid Phone Number length");
			}
			}
		System.out.print("Enter Address: ");
		setAddress(SmartHealthCareSystem.sc.nextLine());
		while(true) {
		System.out.print("Enter Password: ");
		setPassword(SmartHealthCareSystem.sc.nextLine());
		if(getPassword().length() >= 8)
		{
			break;
		}
		else
		{
			System.out.println("Password length must be atleast of 8 character ");
		}
		}
	}
	
	void registerPatient()
	{
		setProfile();
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
		//	boolean status = statement.execute("select * from record where pid = "+ pid);
			
			//INSERT INTO `patient`(`Pid`, `Name`, `DOB`, `Gender`, `Address`, `ContactNo`, `Password`) VALUES ([value-1],[value-2],[value-3],[value-4],[value-5],[value-6],[value-7])
			
			/*String query = "update table patient set"
					+" name=" + getName()
					+ " dob=" + getDob()
					+ " gender=" +getGender()
					+ " address=" + getAddress()
					+ " contactno=" + getPhoneNumber()
					+ " password=" + getPassword()
					//+"salary"=2000 
					+" where pid="+ getPid();*/
			String query = "INSERT INTO patient( Name, DOB, Gender, Address, ContactNo , Password ) VALUES "
					+ "(?,?,?,?,?,?)";
			PreparedStatement pstmt = null;
            pstmt = SmartHealthCareSystem.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, getName());
            pstmt.setString(3, getGender());
            pstmt.setDate(2, java.sql.Date.valueOf(SmartHealthCareSystem.simpleDateFormat.format(getDob())));
            pstmt.setString(4, getAddress());
            pstmt.setString(5, getPhoneNumber());
            pstmt.setString(6, getPassword());
            pstmt.executeUpdate();
	           ResultSet rs = pstmt.getGeneratedKeys();
	            if(rs != null && rs.next()){
	            //    System.out.println("Generated Emp Id: "+rs.getInt(1));
	                setPid(rs.getInt(1));
	                System.out.println("Registration Successful you id is: " + getPid());
	            }
            else
            {
            	System.out.println("Registration Failed");
            }
		}
            catch (Exception e) {
            	
            	System.out.println("Exception " + e.getMessage().toString());
}		
	}
	void seeRecords()
	{
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from record where pid = "+ pid);
           // System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();
                while(rs.next())
                {
                	System.out.println("Record Id: " + rs.getInt("RecID"));
                	System.out.println("Admit Date: " + SmartHealthCareSystem.simpleDateFormat.format(rs.getDate("admit_date")));
                	Date date = rs.getDate("discharge_date");
                	if(date!=null)
                	System.out.println("Discharge Date: " + SmartHealthCareSystem.simpleDateFormat.format(date));
                	System.out.println("Record Description: " + rs.getString("patient_desc"));
                	System.out.println("Disease Identified: " + rs.getString("disease_identified"));
                	System.out.println("Location: " + rs.getString("location"));
                	System.out.println("Assigned Doctor Id: " + rs.getString("did"));
                	//                    instance = new Patient(rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("address"), rs.getString("ContactNo"), rs.getInt("pid"), rs.getString("password"));
                }
            }
            else
            {
            	System.out.println("Error in getting Data");
            }
		}
            catch (Exception e) {
            	
            	System.out.println("Exception " + e.getMessage().toString());
}
	}
	void seePrescriptions()
	{
		
	}
	void loginSuccess()
	{
		System.out.println("Hello " + getName());
		boolean flag = true;
		while(flag)
		{
			System.out.println("1. See Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. Book Appointment");
			System.out.println("4. See Records History");
			System.out.println("5. Logout");
			System.out.print("Enter Your Choice : ");
			int choice = SmartHealthCareSystem.nextint();
			if(choice == 1)
			{
				seeProfile();
			}
			else if(choice == 2)
			{
				updateProfile();
			}
			else if(choice == 3)
			{
				bookAppointment();
			}
			else if(choice == 4)
			{
				seeRecords();
			}
			else if(choice == 5)
			{
				flag = false;
			}
			else
			{
				System.out.println("Invalid Choice try again");
			}
			
	//		if()
			
		}
		
	//	SmartHealthCareSystem.sc
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}
	
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
