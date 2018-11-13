package shs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;



public class Doctor extends Person {


	int docId;
	String password;
	int deptId;
	String rank;
	String surgeon;
	int opdFees;


	public int getDocId() {
		return docId;
	}


	public void setDocId(int docId) {
		this.docId = docId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getDeptId() {
		return deptId;
	}


	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public String getSurgeon() {
		return surgeon;
	}


	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}


	public int getOpdFees() {
		return opdFees;
	}


	public void setOpdFees(int opdFees) {
		this.opdFees = opdFees;
	}
	
	public static Doctor getDoctorById(int docId,String password)
	{
		//Date date;
		Doctor instance = null;
     //   System.out.println("Hello ");
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from doctor where Did = "+ docId +" and password = '"+ password + "'");
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
                    instance = new Doctor(rs.getInt("Did"),rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("address"), rs.getString("ContactNo"),  rs.getString("password") , rs.getInt("DeptId") , rs.getString("Rank") , rs.getString("Surgeon") , rs.getInt("OpdFees"));
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
	
	
	public void viewProfile()
	{
		System.out.println("Doctor id \t\t\t-> " + getDocId());
		System.out.println("Doctor name \t\t\t-> " + getName());
		System.out.println("Date of Birth \t\t\t-> " + getDob());
		System.out.println("Gender \t\t\t\t-> " + getGender());
		System.out.println("Address \t\t\t-> " + getAddress());
		System.out.println("Contact Number \t\t\t-> " + getPhoneNumber());
		System.out.println("Department \t\t\t-> " + getDeptId());
		System.out.println("Rank \t\t\t\t-> " + getRank());
		System.out.println("Surgeon \t\t\t-> " + getSurgeon());
		System.out.println("Opd Fees \t\t\t-> " + getOpdFees());
	}
	
	public void loginSuccess()
	{
		boolean flag = true;
		while(flag)
		{
			System.out.println("\n\nWelcome Doctor " + getName());
			System.out.println("1. View Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. View Doctor Schedule");
			System.out.println("4. Add Appointment Schedule");
			System.out.println("5. View Record of Patients Assigned");
			System.out.println("6. Assist Patient");
			System.out.println("7. LogOut");
			
			int choice = SmartHealthCareSystem.nextint();
			if(choice == 1)
			{
				viewProfile();
			}
			else if(choice == 2)
			{
				updateProfile();
			}
			else if(choice == 3)
			{
				getDoctorSchedule();
			}
			else if(choice == 4)
			{
				addSchedule();
			}
			else if(choice == 5)
			{
				viewRecord();
			}
			else if(choice == 6)
			{
				assistPatient();
			}
			else if(choice == 7) 
			{
				flag = false;
			}
			else
			{
				System.out.println("Wrong choice");
			}
		}
		
	}


	private void assistPatient() {
		// TODO Auto-generated method stub
		viewRecord();
		int patientId = 0;
		int recordId =0;
		int flag=0;
		Date admitDate = null;
		System.out.println("Enter record id of patient you want to assist");
		int recId = SmartHealthCareSystem.nextint();
		
		try 
		{
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select Pid,Admit_Date  from record where RecId = "+ recId );
         // System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();

                while(rs.next())
                {
                	
                	patientId = rs.getInt("Pid");
                	admitDate = rs.getDate("Admit_Date");
                }
    
            }
            else
            {
            	System.out.println("Error in getting Data");
            }
		}
        catch (Exception e) 
		{    	
        	System.out.println("Exception " + e.getMessage().toString());
        }
		
		if(patientId != 0)
			viewPatient(patientId);
		
		
		try 
		{
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select RecId from prescription" );
         // System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();

                while(rs.next())
                {
                	
                	recordId = rs.getInt("RecId");
                	if(recordId == recId)
                	{
                		flag = 1;
                	}
                }
    
            }
            else
            {
            	System.out.println("Error in getting Data");
            }
		}
        catch (Exception e) 
		{    	
        	System.out.println("Exception " + e.getMessage().toString());
        }

		if(flag==1)
		{
			System.out.println("\n\n Prescription history exists\n\n");
			Patient patinetObject = new Patient();
			patinetObject.seePrescriptions(recId);
		}
		else
		{
			System.out.println("\n\nNo prescription history exists");
		}
		
		
		System.out.println("\nEnter new Priscription\n");
		Prescription pres =  new Prescription();
		pres.inputPrescription(getDocId() , recId , admitDate , getOpdFees());
		
		pres.updatePriscription();
		
	}


	private void viewPatient(int patientId) {
		// TODO Auto-generated method stub
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from patient where Pid = "+ patientId );
           // System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();

                while(rs.next())
                {
                	
                	//patientId = rs.getInt("Pid");
                	System.out.println("\n\nPatient name is \t\t-> " + rs.getString("Name"));
                	System.out.println("Date of Birth is \t\t-> " + rs.getDate("DOB"));
                	System.out.println("Gender is \t\t\t-> " + rs.getString("Gender"));
                	System.out.println("Address is \t\t\t-> " + rs.getString("Address"));
                	System.out.println("Phone number is \t\t-> " + rs.getString("ContactNo"));
//                	System.out.println("Start time is \t\t\t-> " + rs.getTime("StartTime"));
  //              	System.out.println("End time is \t\t\t-> " + rs.getTime("EndTime"));
                	//System.out.println("Patient Location is \t\t-> " + rs.getString("Location"));
                	//Time tm = new Time(deptId, deptId, deptId);
                    //instance = new Doctor(rs.getInt("Did"),rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("address"), rs.getString("ContactNo"),  rs.getString("password") , rs.getInt("DeptId") , rs.getString("Rank") , rs.getString("Surgeon") , rs.getInt("OpdFees"));
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


	private void viewRecord() {
		// TODO Auto-generated method stub
		
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from record where Did = "+ docId );
           // System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();

                while(rs.next())
                {
                	System.out.println("\n\nRecord id is \t\t\t-> " + rs.getInt("RecId"));
                	System.out.println("Patient id is \t\t\t-> " + rs.getInt("Pid"));
                	System.out.println("Admit Date is \t\t\t-> " + rs.getDate("Admit_Date"));
//                	System.out.println("Start time is \t\t\t-> " + rs.getTime("StartTime"));
  //              	System.out.println("End time is \t\t\t-> " + rs.getTime("EndTime"));
                	System.out.println("Patient Location is \t\t-> " + rs.getString("Location"));
                	//Time tm = new Time(deptId, deptId, deptId);
                    //instance = new Doctor(rs.getInt("Did"),rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("address"), rs.getString("ContactNo"),  rs.getString("password") , rs.getInt("DeptId") , rs.getString("Rank") , rs.getString("Surgeon") , rs.getInt("OpdFees"));
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


	private void addSchedule() {
		// TODO Auto-generated method stub
		java.sql.Date appointDate;
		while(true)
		{
			System.out.print("Enter new Appointment Date(YYYY-MM-DD): ");
			appointDate = java.sql.Date.valueOf(SmartHealthCareSystem.sc.nextLine());
			break;
		}
		System.out.println("Enter start time");
		Time startTime = getTime();
		System.out.println("Enter end time");
		Time endTime = getTime();
		System.out.println("Enter number of patients you want to accomodate");
		int countPatinets = SmartHealthCareSystem.nextint();
		
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
		
			String query = "INSERT INTO schedule( Did, Date, StartTime, EndTime, Count_Of_Patients ) VALUES "
					+ "(?,?,?,?,?)";
			PreparedStatement pstmt = null;
            pstmt = SmartHealthCareSystem.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, getDocId());
            pstmt.setTime(3, startTime);
            pstmt.setDate(2, appointDate);
            pstmt.setTime(4, endTime);
            pstmt.setInt(5, countPatinets);
            //pstmt.setString(6, getPassword());
            pstmt.executeUpdate();
	           ResultSet rs = pstmt.getGeneratedKeys();
	            if(rs != null && rs.next()){
	            //    System.out.println("Generated Emp Id: "+rs.getInt(1));
	                //setPid(rs.getInt(1));
	                System.out.println("New Appointment schedule added ");
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


	private Time getTime() {
		// TODO Auto-generated method stub
		Time sampleTime;
		while(true)
		{
		
			
			System.out.println("enter hours in 24 hr format");
			int hour = SmartHealthCareSystem.nextint();
			if(hour>23 || hour<0)
			{
				System.out.println("not in 24 hr format");
				continue;
			}
			System.out.println("Enter minutes ");
			int minute = SmartHealthCareSystem.nextint();
			if(minute>59 || minute<0)
			{
				System.out.println("minute should be between 0 and 59");
				continue;
			}
			
			sampleTime = new Time(hour, minute, 0);
			break;
			
		}
		return sampleTime;
	}


	private void getDoctorSchedule() {
		// TODO Auto-generated method stub
	/*	Doctor instance = null;*/
	     //   System.out.println("Hello ");
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
				boolean status = statement.execute("select * from schedule where Did = "+ docId );
	           // System.out.println("Hello " + status);
				if(status){
	                //query is a select query.
	                ResultSet rs = statement.getResultSet();

	                while(rs.next())
	                {
	                	System.out.println("\n\nSchedule id is \t\t\t-> " + rs.getInt("ScheduleId"));
	                	System.out.println("Doctor id is \t\t\t-> " + rs.getInt("Did"));
	                	System.out.println("Date is \t\t\t-> " + rs.getDate("Date"));
	                	System.out.println("Start time is \t\t\t-> " + rs.getTime("StartTime"));
	                	System.out.println("End time is \t\t\t-> " + rs.getTime("EndTime"));
	                	System.out.println("Count of patient is \t\t-> " + rs.getInt("Count_Of_Patients"));
	                	//Time tm = new Time(deptId, deptId, deptId);
	                    //instance = new Doctor(rs.getInt("Did"),rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("address"), rs.getString("ContactNo"),  rs.getString("password") , rs.getInt("DeptId") , rs.getString("Rank") , rs.getString("Surgeon") , rs.getInt("OpdFees"));
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


	public void updateProfile() {
		// TODO Auto-generated method stub
		boolean flag2 = true;
		while(flag2)
		{
			System.out.println("What do you want to update?\n");
			
			System.out.println("1. Doctor name" );
			System.out.println("2. Date of Birth");
			System.out.println("3. Gender");
			System.out.println("4. Address");
			System.out.println("5. Contact Number");
			System.out.println("6. Password");
			System.out.println("7. Department");
			System.out.println("8. Rank");
			System.out.println("9. Surgeon");
			System.out.println("10. Opd Fees");
			System.out.println("11. Done all Changes");
			
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

				changeDepartment();
			}
			else if(choice ==8 )
			{

				changeRank();

			}
			else if(choice ==9)
			{
				changeSurgeon();
			}
			else if(choice == 10)
			{

				changeOpdFees();
			}
			else if(choice == 11)
			{
				updateDoctorChanges();
				flag2 = false;
			}
			else
			{
				System.out.println("Wrong input ");
				
			}
		}
	}


	private void updateDoctorChanges() {
		// TODO Auto-generated method stub
	
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
			//	boolean status = statement.execute("select * from record where pid = "+ pid);
				
				/*String query = "update table patient set"
						+" name=" + getName()
						+ ", dob=" + getDob()
						+ ", gender=" +getGender()
						+ ", address=" + getAddress()
						+ ", contactno=" + getPhoneNumber()
						+ ", password=" + getPassword()
						//+"salary"=2000 
						+" where pid="+ getD();*/
	            //count will give you how many records got updated
				String query = "update doctor set"
				+" name='" + getName() + "'"
				+ ", dob='" + SmartHealthCareSystem.javaDateToString(getDob()) + "'"
				+ " , gender='" +getGender().charAt(0) + "'"
				+ " , address='" + getAddress() + "'"
				+ " , contactno='" + getPhoneNumber() + "'"
				+ " , password='" + getPassword() + "'"
				+ " , DeptId='" + getDeptId() + "'"
				+ " , Rank='" + getRank() + "'"
				+ " , Surgeon='" + getSurgeon() + "'"
				+ " , OpdFees='" + getOpdFees() + "'"
				+" where Did="+ getDocId();
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


	private void changeOpdFees() {
		// TODO Auto-generated method stub
		System.out.println("current OPD is -> " + getOpdFees());
		System.out.println("Enter new Opd fees ");
		int newFees = SmartHealthCareSystem.nextint();
		setOpdFees(newFees);
	}


	private void changeSurgeon() {
		// TODO Auto-generated method stub
		System.out.println("current surgeon status is -> " + getSurgeon());
		System.out.println("Enter new surgeon status ");
		String newSurgeon = SmartHealthCareSystem.sc.nextLine();
		setSurgeon(newSurgeon);
	}


	private void changeRank() {
		// TODO Auto-generated method stub
		System.out.println("current Rank is -> " + getRank());
		System.out.println("Enter new rank ");
		String newRank = SmartHealthCareSystem.sc.nextLine();
		setRank(newRank);
	}


	private void changeDepartment() {
		// TODO Auto-generated method stub
		System.out.println("current Department is -> " + getDeptId());
		System.out.println("Enter new department ");
		int newDept = SmartHealthCareSystem.nextint();
		setDeptId(newDept);
	}


	private void changePassword() {
		// TODO Auto-generated method stub
		System.out.println("current password is -> " + getPassword());
		System.out.println("Enter new password ");
		String newPassword = SmartHealthCareSystem.sc.nextLine();
		setPassword(newPassword);
	}


	


	public Doctor(int docId , String name , Date date , String gender , String address , String contactNumber , String password , int deptId , String rank , String Surgeon , int opdFees) {
		// TODO Auto-generated constructor stub
		super(name , date , gender , address , contactNumber);
		setDocId(docId);
		setPassword(password);
		setDeptId(deptId);
		setRank(rank);
		setSurgeon(Surgeon);
		setOpdFees(opdFees);
		
	}
	
}
