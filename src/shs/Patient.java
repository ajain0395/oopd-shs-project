package shs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;

public class Patient extends Person {
	
	int pid;
	String password;
	boolean appointmentflag = false;
	int selectedDoctorId = 0;
	String appointmentDate = null;
	public Logging logger;
	
	public Patient(String name, Date dob,String gender, String address, String phonenumber,int pid,String password) {
		// TODO Auto-generated csrc/shs/Logging.javaonstructor stub
		super(name, dob, gender, address, phonenumber);
		logger = new Logging(this.getClass().getName());
		this.setPid(pid);
		this.setPassword(password);
		logger.info("User "+ name +" Signed in");
	}
	public static Patient getPatientById(int pid,String password)
	{
		Patient instance = null;
     //   System.out.println("Hello ");
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from patient where pid = "+ pid +" and password = '"+ password+"'");
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
		logger.info("seeProfile Entry");
		System.out.println("ID: " + getPid());
		System.out.println("Name: " + getName());
		System.out.println("Date of Birth: " + SmartHealthCareSystem.simpleDateFormat.format(getDob()));
		System.out.println("Address: " + getAddress());
		System.out.println("Phone Number: "+ getPhoneNumber());
		logger.info("seeProfile Exit");
	}
	
	void changePassword()
	{
		logger.info("Change Password Entry");
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
		logger.info("Change Password Exit");
	}
	void updateProfile()
	{
		logger.info("Update Profile Entry");
		 boolean flag2 = true;
	        while(flag2)
	        {
	            System.out.println("What do you want to update?\n");
	            
	            System.out.println("1. Name " );
	            System.out.println("2. Date of Birth ");
	            System.out.println("3. Gender ");
	            System.out.println("4. Address ");
	            System.out.println("5. Contact Number ");
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
	            	updatePatient();
	                flag2 = false;
	            }
	            else
	            {
	                System.out.println("Wrong input ");
	                
	            }
	        }
	        logger.info("Update Profile Exit");
	}
	
	void bookAppointment()
	{
		Record record = new Record();
		System.out.print("Enter description for the problem you are facing: ");
		record.setPatient_Desc(SmartHealthCareSystem.sc.nextLine());
		record.selectLocation();
		appointmentDate = null;
		selectDepartment();
		if(appointmentflag && appointmentDate != null)
		{
			record.setAdmit_Date(SmartHealthCareSystem.stringToJavaDate(appointmentDate));
			record.setDid(selectedDoctorId);
			record.setPid(getPid());
			record.insertRecord();
		}
		
	}
	
	void updatePatient(/*Patient instance*/)
	{
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
		//	boolean status = statement.execute("select * from record where pid = "+ pid);
			
			String query = "update patient set"
			+" name='" + getName()+"'"
			+ ", dob='" + SmartHealthCareSystem.javaDateToString(getDob())+"'"
			+ ", gender='" +getGender()+"'"
			+ ", address='" + getAddress()+"'"
			+ ", contactno='" + getPhoneNumber()+"'"
			+ ", password='" + getPassword()+"'"
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
		
		while(true)
		{
			System.out.print("Enter Gender(M/F): ");
			setGender(SmartHealthCareSystem.sc.nextLine());
			if(getGender().length() == 1 && (getGender().equals("M") || getGender().equals("F")))
			{
				break;
			}
			else{
			// TODO Auto-generated catch block
		System.out.println("Invalid Gender input");
		}
		}
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
	
	private void selectDoctorSchedule(int docId) {
		boolean flag = true;
		int count = 0;
		while (flag) {
		try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
				boolean status = statement.execute("select * from schedule where Did = "+ docId+" and Count_Of_Patients > 0 and date >="+ SmartHealthCareSystem.javaDateToString(new Date()));
	           // System.out.println("Hello " + status);
				if(status){
	                //query is a select query.
	                ResultSet rs = statement.getResultSet();

	                if(rs.next())
	                {
						System.out.println("Select Appointment Schedule");

	                	System.out.println("Sno.\t|\tDate\t\t|\tStart Time\t|\tEnd Time\t|\tCount Left\n");
	    				count = 0;
	                	rs.beforeFirst();
	                }
	                while(rs.next())
	                {
	                	count++;
						System.out.print(count+"\t|\t");
	                	System.out.print(rs.getString("Date")+"\t|\t");
	                	System.out.print(rs.getTime("StartTime")+"\t|\t");
	                	System.out.print(rs.getTime("EndTime")+"\t|\t");
	                	System.out.println(rs.getInt("Count_Of_Patients"));
	                }
	                if(count > 0)
					{
						System.out.println("Enter Choice or 0 to go to previous options: ");
						int ch;
						ch = SmartHealthCareSystem.nextint();
						if(ch <= count && ch >=1)
						{
							rs.beforeFirst();
							int dId = 0;
							int scheduleId = 0;
							int tmpcount = 0;
							while(rs.next())
							{
								tmpcount++;
								dId = rs.getInt("did");
								scheduleId = rs.getInt("ScheduleId");
								if(tmpcount == ch)
								{
									break;
								}
							}
							selectedDoctorId = dId; 
							appointmentDate = rs.getString("Date");
							//System.out.println("ashish_ Date: " + appointmentDate);
							appointmentflag = true;
							Record.updateScedule(scheduleId);
							flag = false;
						}
						else if(ch == 0)
						{
							flag = false;
						}
						else
						{
							System.out.println("Invalid Input");
						}
					}
					else
					{
						System.out.println("No appointments available for Doctor");
						flag = false;
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
		
	}

	void selectDepartment()
	{
		boolean flag = true;
		appointmentflag = false;
		int count = 0;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
			//	Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `department`");
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					if(rs.next())
					{
						System.out.println("Select Department");
						System.out.println("S No.\t|\tDepartment Name\n");
						rs.beforeFirst();
					}
					while (rs.next()) {
						count++;
						System.out.print(count+"\t|\t");
						System.out.println(rs.getString("DeptName"));
					}
					if(count > 0)
					{
						System.out.println("Enter Choice or 0 to go to previous options: ");
						int ch;
						ch = SmartHealthCareSystem.nextint();
						if(ch <= count && ch >=1)
						{
							rs.beforeFirst();
							int deptId = 0;
							int tmpcount = 0;
							while(rs.next())
							{
								tmpcount++;
								deptId = rs.getInt("DeptId");
								if(tmpcount == ch)
								{
									break;
								}
							}
							selectDoctorByDepartment(deptId);
							if(appointmentflag)
								flag = false;
						}
						else if(ch == 0)
						{
							flag = false;
						}
						else
						{
							System.out.println("Invalid Input");
						}
					}
					else
					{
						System.out.println("No Doctors in Department");
						flag = false;
					}
				} else {
					System.out.println("Error in getting Data");
				}
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage().toString());

			}
			
		}

	}
	
	void selectDoctorByDepartment(int deptId)
	{
		boolean flag = true;
		int count = 0;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
			//	Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `doctor` WHERE DeptId=" +deptId);
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					if(rs.next())
					{
						System.out.println("Select Doctor");
						System.out.println("S No.\t|\tName\t|\tHospital Name\n");
						rs.beforeFirst();
					}
					while (rs.next()) {
						count++;
						System.out.print(count+"\t|\t");
						System.out.print(rs.getString("name")+"\t|\t");
						System.out.println(rs.getString("hospital"));
					}
					if(count > 0)
					{
						System.out.println("Enter Choice or 0 to go to previous options: ");
						int ch;
						ch = SmartHealthCareSystem.nextint();
						if(ch <= count && ch >=1)
						{
							rs.beforeFirst();
							int dId = 0;
							int tmpcount = 0;
							while(rs.next())
							{
								tmpcount++;
								dId = rs.getInt("did");
								if(tmpcount == ch)
								{
									break;
								}
							}
							selectedDoctorId = dId;
							selectDoctorSchedule(dId);
							flag = false;
						}
						else if(ch == 0)
						{
							flag = false;
						}
						else
						{
							System.out.println("Invalid Input");
						}
					}
					else
					{
						System.out.println("No Doctors in Department");
						flag = false;
					}
				} else {
					System.out.println("Error in getting Data");
				}
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage().toString());

			}
			
		}
	}
	
	void registerPatient()
	{
		setProfile();
		try {	try {
	//		Statement statement = SmartHealthCareSystem.con.createStatement();
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
	//		Statement statement = SmartHealthCareSystem.con.createStatement();
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
			Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

           // System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();
                while(rs.next())
                {
                	System.out.println("\nRecord Id: " + rs.getInt("RecID"));
                	System.out.println("Admit Date: " + SmartHealthCareSystem.simpleDateFormat.format(rs.getDate("admit_date")));
                	Date date = rs.getDate("discharge_date");
                	if(date!=null)
                	System.out.println("Discharge Date: " + SmartHealthCareSystem.simpleDateFormat.format(date));
                	System.out.println("Record Description: " + rs.getString("patient_desc"));
                	System.out.println("Disease Identified: " + rs.getString("disease_identified"));
                	System.out.println("Location: " + rs.getString("location"));
                	System.out.println("Assigned Doctor Id: " + rs.getString("did"));
                	statementDoctor.execute("select name from doctor where did=" + rs.getInt("did"));
                	ResultSet rsdoc = statementDoctor.getResultSet();
                	rsdoc.next();
        //        	System.out.println("Error here 2");
                	System.out.println("Doctor Name: "+ rsdoc.getString("name"));

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
	void seePrescriptions(int recId)
	{
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

			boolean status = statement.execute("select * from prescription where RecId="+ recId);
			// System.out.println("Hello " + status);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();
                int count = 0;
                while(rs.next())
                {
                	count++;
            //    	System.out.println("Error here 1");
                	System.out.println("\nPrescription Id: " + rs.getInt("Histid"));
                	int did = rs.getInt("did");
                	//System.out.println("Doctor Id: "+ did);
                	Date date = rs.getDate("date");
                	if(date!=null)
                	System.out.println("Date: " + SmartHealthCareSystem.simpleDateFormat.format(date));
                	System.out.println("Test Adviced: " + rs.getString("Test_Adviced"));
                	System.out.println("Medicine Prescribed: " + rs.getString("Medicine_Prescribed"));
                	System.out.println("Patient Status: "+ rs.getString("Patient_Status"));
          //      	System.out.println("Error here 3");
                	if(rs.getString("location") !=null)
                	{
                		System.out.println("Location: " + rs.getString("location"));
                	}
                	
                	System.out.println("Assigned Doctor Id: " + rs.getString("did"));
                	statementDoctor.execute("select name from doctor where did=" + did);
                	ResultSet rsdoc = statementDoctor.getResultSet();
                	rsdoc.next();
        //        	System.out.println("Error here 2");
                	System.out.println("Doctor Name: "+ rsdoc.getString("name"));
         
                       }
                if(count == 0)
                {
                	System.out.println("No prescription's found for the record");
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

	void cancelAppointment() {
		boolean flag = true;
		int count = 0;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
				Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `record` WHERE Discharge_Date is NULL and Admit_Date >= (SELECT SYSDATE()) and pid='"
								+ getPid() + "'");
				// System.out.println("Hello " + status);
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					while (rs.next()) {
						System.out.println("");
						count++;
						System.out.println("\nRecord Number: " + count);
						System.out.println("Appointment Date: "
								+ SmartHealthCareSystem.simpleDateFormat.format(rs.getDate("admit_date")));
						System.out.println("Location: " + rs.getString("location"));
						System.out.println("Assigned Doctor Id: " + rs.getString("did"));
						statementDoctor.execute("select name from doctor where did=" + rs.getInt("did"));
						ResultSet rsdoc = statementDoctor.getResultSet();
						rsdoc.next();
						System.out.println("Doctor Name: " + rsdoc.getString("name"));
						System.out.println("Record Description: " + rs.getString("patient_desc"));
					}
					if(count > 0)
					{
						System.out.println("Enter Record number to be deleted or 0 to go to previous options: ");
						int ch;
						ch = SmartHealthCareSystem.nextint();
						if(ch <= count && ch >=1)
						{
							rs.beforeFirst();
							int recId = 0;
							int tmpcount = 0;
							while(rs.next())
							{
								tmpcount++;
								recId = rs.getInt("RecID");
								if(tmpcount == ch)
								{
									break;
								}
							}
							Record.deleteRecord(recId,rs.getString("admit_date"),rs.getInt("did"));
							flag = false;
						}
						else if(ch == 0)
						{
							flag = false;
						}
						else
						{
							System.out.println("Invalid Input");
						}
					}
					else
					{
						System.out.println("No Future Appointments");
						flag = false;
					}
				} else {
					System.out.println("Error in getting Data");
				}
			} catch (Exception e) {
				System.out.println("Exception " + e.getMessage().toString());

			}
			
		}
	}
	void loginSuccess()
	{
		System.out.println("Hello " + getName());
		boolean flag = true;
		while(flag)
		{
			System.out.println("\n1. See Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. Book Appointment");
			System.out.println("4. Cancel Appointment");
			System.out.println("5. See Records History");
			System.out.println("6. See Detailed Records History");
			System.out.println("7. Logout");
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
				cancelAppointment();
			}
			else if(choice == 5)
			{
				seeRecords();
			}
			else if(choice == 6)
			{
				seeDetailedRecords();
			}
			else if(choice == 7)
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

	private void seeDetailedRecords() {
		// TODO Auto-generated method stub
		int recId;
		
			seeRecords();
			System.out.println("\nEnter Record Id to see details: ");
			recId = SmartHealthCareSystem.nextint();
			seePrescriptions(recId);
		
	}
	public Patient() {
		// TODO Auto-generated constructor stub
		logger = new Logging(this.getClass().getName());
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
