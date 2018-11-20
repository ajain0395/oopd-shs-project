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
	
	public Patient(String name, Date dob,String gender, String address, String phonenumber/*,int pid*/,String password) {
		super(name, dob, gender, address, phonenumber);
	//	this.setPid(pid);
		this.setPassword(password);
		logger.info("User "+ name +" Signed in");
	}
	public static Patient getPatientById(int pid,String password)
	{
		Patient instance = null;
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from patient where pid = "+ pid +" and password = '"+ password+"'");
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
                    instance = new Patient(rs.getString("name"), rs.getDate("dob"), rs.getString("gender"), rs.getString("address"), rs.getString("ContactNo"), rs.getString("password"));
                    instance.setPid(rs.getInt("pid"));
                }
                }
            }
            else
            {
            	System.out.println("Error in getting Data");
            	
            }
		}
            catch (Exception e) {
            	
/*            	System.out.println("Exception " + e.getMessage().toString());
*/            	
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
		logger.info("bookAppointment Entry");
		Record record = new Record();
		System.out.print("Enter description for the problem you are facing: ");
		record.setPatient_Desc(SmartHealthCareSystem.sc.nextLine());
		record.selectLocation();
		appointmentDate = null;
		boolean flag2 = true;
		while(flag2)
		{
			System.out.println("\nSelect Doctor Menu");
			System.out.println("1. Select Doctor Department automatically by description");
			System.out.println("2. Select Doctor By Department");
			System.out.println("3. Select Doctor by Name");
			System.out.println("4. Select Doctor by Address");
			System.out.println("5. Select Doctor by Id");
			System.out.println("0. Previous Menu");
			System.out.print("Enter Choice: ");
			int choice = SmartHealthCareSystem.nextint();
			if(choice == 0)
			{
				break;
			}
			else if(choice ==1)
			{
				selectDepartmentAI(record.getPatient_Desc());
				break;
			}
			else if(choice == 2)
			{
				selectDepartment();
				break;
			}
			else if(choice == 3)
			{
				selectDoctorByName();
				break;
			}
			else if (choice == 4)
			{
				selectDoctorByAddress();
				break;
			}
			else if(choice == 5)
			{
				selectDoctorById();
				break;
			}
			else
			{
				System.out.println("Invalid choice");
			}
		}
		
		if(appointmentflag && appointmentDate != null)
		{
			record.setAdmit_Date(SmartHealthCareSystem.stringToJavaDate(appointmentDate));
			record.setDid(selectedDoctorId);
			record.setPid(getPid());
			record.insertRecord();
		}
		logger.info("bookAppointment Exit");
	}
	int getScore(String description, String tags)
	{
		logger.info("getScore Entry");
		int score = 0;
		tags = tags.toLowerCase();
		description = description.toLowerCase();
		String []words = tags.split(",");
		for(int i = 0; i < words.length ;i++)
		{
			if(description.indexOf(words[i]) != -1)
			{
				score++;
			}
		}		
		logger.info("getScore Exit");
		return score;
	}
	private void selectDepartmentAI(String description) {
		 	
		logger.info("selectDepartmentAI Entry");
		boolean flag = true;
		appointmentflag = false;
		int count = 0;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `department` ORDER BY DeptId");
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					while (rs.next()) {
						count++;
					}
					if(count > 0)
					{
				//		System.out.print("Enter Choice or 0 to go to previous options: ");
				//		int ch;
				//		ch = SmartHealthCareSystem.nextint();
					//	if(ch <= count && ch >=1)
					//	{
							rs.beforeFirst();
							rs.next();
							int deptId = rs.getInt("deptid");
							String deptName = rs.getString("deptname");
							int score = getScore(description, rs.getString("tags"));
							String tags;
							rs.beforeFirst();
							while(rs.next())
							{
								tags = rs.getString("tags");
								if(score < getScore(description, tags))
								{
									score  = getScore(description, tags);
									deptId = rs.getInt("deptid");
									deptName  = rs.getString("deptname");
								}
							}
							
							System.out.println("Selected Department is " + deptName);
							System.out.print("Press Y to Continue with selected department: ");
							if(!SmartHealthCareSystem.sc.nextLine().equalsIgnoreCase("Y"))
							{
								flag = false;
							}
							else
							{
							selectDoctorByDepartment(deptId);
							}
							if(appointmentflag)
								flag = false;
					//	}
					}
					else
					{
						System.out.println("No Departments Registered");
						flag = false;
					}
				} else {
					logger.warning("Error in getting Data");
				}
			} catch (Exception e) {
				logger.error(e.getMessage().toString());

			}
			
		}


		logger.info("selectDepartmentAI Exit");
	}
	private void selectDoctorById() {

		logger.info("selectDoctorById Entry");
			boolean flag = true;
			while (flag) {
				System.out.print("Enter Doctor Id: ");
				int docid = SmartHealthCareSystem.nextint();
				int count = 0;
				try {
					Statement statement = SmartHealthCareSystem.con.createStatement();

					boolean status = statement.execute(
							"SELECT * FROM `doctor` WHERE did=" +docid);
					count = 0;
					if (status) {
						// query is a select query.
						ResultSet rs = statement.getResultSet();
						if(rs.next())
						{
							count++;
							rs.beforeFirst();
						}
						if(count > 0)
						{
								rs.next();
								selectedDoctorId = docid;
								System.out.println("Selected Doctor\t\t: "+ rs.getString("name"));
								System.out.print("Selected Doctor Rank\t:"+rs.getString("rank"));
								System.out.print("Is Surgeon\t\t:"+ rs.getString("surgeon"));
								System.out.println("Selected Doctor Hospital\t: "+rs.getString("hospital"));
								selectDoctorSchedule(docid);
								flag = false;
						}
						else
						{
							System.out.println("Invalid Doctor id");
							System.out.print("Press 'Y' to search again any other key for previous options: ");
							if(!SmartHealthCareSystem.sc.nextLine().equalsIgnoreCase("y"))
							{
								flag = false;
							}
						}
					} 
					else {
						System.out.println("Error in getting Data");
					}
				} catch (Exception e) {
					logger.error(e.getMessage().toString());
				}
				
			}

		
	}
	private void selectDoctorByAddress() {
		logger.info("selectDoctorByAddress Entry");
		
		boolean flag = true;
		int count = 0;
		String docaddress;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
				System.out.print("Enter Address: ");
				docaddress = SmartHealthCareSystem.sc.nextLine();
				boolean status = statement.execute(
						"SELECT * FROM `doctor` WHERE address='" +docaddress+"'" );
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					if(rs.next())
					{
						System.out.println("Select Doctor");
						System.out.println("S No.\t|\tName\t|\tRank\t\t|\tSurgeon\t|\tHospital Name\n");
						rs.beforeFirst();
					}
					while (rs.next()) {
						count++;
						System.out.print(count+"\t|\t");
						System.out.print(rs.getString("name")+"\t|\t");
						System.out.print(rs.getString("rank")+"\t|\t");
						System.out.print(rs.getString("surgeon")+"\t|\t");
						System.out.println(rs.getString("hospital"));
					}
					if(count > 0)
					{
						System.out.print("Enter Choice or 0 to go to previous options: ");
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
						System.out.println("No Doctors present with Address "+ docaddress);
						System.out.print("Press 'Y' to search again or any other key for previous options: ");
						if(!SmartHealthCareSystem.sc.nextLine().equalsIgnoreCase("y"))
						{
							flag = false;
						}					}
				} else {
					logger.warning("Error in getting Data");
				}
			} catch (Exception e) {
				logger.error(e.getMessage().toString());

			}
			
		}

		logger.info("selectDoctorByAddress Exit");		
	}
	private void selectDoctorByName() {
		logger.info("selectDoctorByName Entry");
		boolean flag = true;
		int count = 0;
		String docname;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
				System.out.print("Enter Name: ");
				docname = SmartHealthCareSystem.sc.nextLine();
				boolean status = statement.execute(
						"SELECT * FROM `doctor` WHERE name='" +docname+"'" );
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					if(rs.next())
					{
						System.out.println("Select Doctor");
						System.out.println("S No.\t|\tName\t|\tRank\t\t|\tSurgeon\t|\tHospital Name\n");
						rs.beforeFirst();
					}
					while (rs.next()) {
						count++;
						System.out.print(count+"\t|\t");
						System.out.print(rs.getString("name")+"\t|\t");
						System.out.print(rs.getString("rank")+"\t|\t");
						System.out.print(rs.getString("surgeon")+"\t|\t");
						System.out.println(rs.getString("hospital"));
					}
					if(count > 0)
					{
						System.out.print("Enter Choice or 0 to go to previous options: ");
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
						System.out.println("No Doctors present with Name "+ docname);
						System.out.print("Press 'Y' to search again or any other key for previous options: ");
						if(!SmartHealthCareSystem.sc.nextLine().equalsIgnoreCase("y"))
						{
							flag = false;
						}					}
				} else {
					logger.warning("Error in getting Data");
				}
			} catch (Exception e) {
				logger.error(e.getMessage().toString());

			}
			
		}

		logger.info("selectDoctorByName Exit");
		
	}
	void updatePatient(/*Patient instance*/)
	{
		logger.info("updatePatient Entry");
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			
			String query = "update patient set"
			+" name='" + getName()+"'"
			+ ", dob='" + SmartHealthCareSystem.javaDateToString(getDob())+"'"
			+ ", gender='" +getGender()+"'"
			+ ", address='" + getAddress()+"'"
			+ ", contactno='" + getPhoneNumber()+"'"
			+ ", password='" + getPassword()+"'"
			+" where pid="+ getPid();
    //count will give you how many records got updated
            int count = statement.executeUpdate(query);
			
			if(count == 1){
                System.out.println("Record Updated");
            }
            else
            {
            	System.out.println("Record not updated try again");
            	logger.warning("issue in updating data");
            }
		}
            catch (Exception e) {
            	
            	logger.error(e.getMessage().toString());
}		
		logger.info("updatePatient Exit");
	}
	void setProfile()
	{
		logger.info("setProfile Entry");
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
		System.out.println("Invalid Date Format");
		logger.warning("Invalid Date Format " + e.getMessage().toString());
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
		if(getPassword().length() > 0)
		{
			break;
		}
		else
		{
			System.out.println("Password length must be atleast of 1 character ");
		}
		}
		logger.info("setProfile Exit");
	}
	
	private void selectDoctorSchedule(int docId) {
		
		logger.info("selectDoctorScedule Entry");
		boolean flag = true;
		int count = 0;
		while (flag) {
		try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
				boolean status = statement.execute("select * from schedule where Did = "+ docId+" and Count_Of_Patients > 0 and date >="+ SmartHealthCareSystem.javaDateToString(new Date()));
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
						System.out.print("Enter Choice or 0 to go to previous options: ");
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
	            	logger.warning("Error in getting Data");
	            }
			}
	            catch (Exception e) {
	            	logger.error(e.getMessage().toString());
	}
	}
		logger.info("selectDoctorScedule Exit");
	}

	void selectDepartment()
	{
		logger.info("selectDepartment Entry");
		boolean flag = true;
		appointmentflag = false;
		int count = 0;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `department`");
				count = 0;
				if (status) {
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
						System.out.print("Enter Choice or 0 to go to previous options: ");
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
					logger.warning("Error in getting Data");
				}
			} catch (Exception e) {
				logger.error(e.getMessage().toString());

			}
			
		}
		logger.info("selectDepartment Exit");
	}
	
	void selectDoctorByDepartment(int deptId)
	{
		logger.info("selectDoctorByDepartment Entry");
		boolean flag = true;
		int count = 0;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `doctor` WHERE DeptId=" +deptId);
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					if(rs.next())
					{
						System.out.println("Select Doctor");
						System.out.println("S No.\t|\tName\t|\tRank\t\t|\tSurgeon\t|\tHospital Name\n");
						rs.beforeFirst();
					}
					while (rs.next()) {
						count++;
						System.out.print(count+"\t|\t");
						System.out.print(rs.getString("name")+"\t|\t");
						System.out.print(rs.getString("rank")+"\t|\t");
						System.out.print(rs.getString("surgeon")+"\t|\t");
						System.out.println(rs.getString("hospital"));
					}
					if(count > 0)
					{
						System.out.print("Enter Choice or 0 to go to previous options: ");
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
					logger.warning("Error in getting Data");
				}
			} catch (Exception e) {
				logger.error(e.getMessage().toString());

			}
			
		}
		logger.info("selectDoctorByDepartment Exit");
	}
	
	public int insertPatient()
	{
		logger.info("registerPatient Entry");
		//setProfile();
		try {	
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
	                setPid(rs.getInt(1));
	                System.out.println("Registration Successful you id is: " + getPid());
	            }
            else
            {
            	System.out.println("Registration Failed");
            }
		}
            catch (Exception e) {
            	System.out.println(e.getMessage().toString());
            	logger.error(e.getMessage().toString());
}		
		
		logger.info("registerPatient Exit");
	return getPid();
	}
	
	void registerPatient()
	{
		logger.info("registerPatient Entry");
		setProfile();
		try {	
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
	                setPid(rs.getInt(1));
	                System.out.println("Registration Successful you id is: " + getPid());
	            }
            else
            {
            	System.out.println("Registration Failed");
            }
		}
            catch (Exception e) {
            	
            	logger.error(e.getMessage().toString());
}		
		logger.info("registerPatient Exit");
	}
	void seeRecords()
	{
		logger.info("seeRecords Entry");
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from record where pid = "+ pid);
			Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

			if(status){
                ResultSet rs = statement.getResultSet();
                while(rs.next())
                {
                	System.out.println("\nRecord Id\t\t:" + rs.getInt("RecID"));
                	System.out.println("Admit Date\t\t:" + SmartHealthCareSystem.simpleDateFormat.format(rs.getDate("admit_date")));
                	Date date = rs.getDate("discharge_date");
                	if(date!=null)
                	System.out.println("Discharge Date\t\t:" + SmartHealthCareSystem.simpleDateFormat.format(date));
                	System.out.println("Record Description\t:" + rs.getString("patient_desc"));
                	if(rs.getString("disease_identified") == null)
                	{
                		System.out.println("Disease Identified\t:" + "Not Updated");
                	}
                	else
                	{
                		System.out.println("Disease Identified\t:" + rs.getString("disease_identified"));
                	}
                	System.out.println("Location\t\t:" + rs.getString("location"));
                	System.out.println("Assigned Doctor Id\t:" + rs.getString("did"));
                	statementDoctor.execute("select name from doctor where did=" + rs.getInt("did"));
                	ResultSet rsdoc = statementDoctor.getResultSet();
                	rsdoc.next();
                	System.out.println("Doctor Name\t\t:"+ rsdoc.getString("name"));

                }
            }
            else
            {
            	logger.warning("Error in getting Data");
            }
		}
            catch (Exception e) {
            	
            	logger.error(e.getMessage().toString());
}
		logger.info("seeRecords Exit");
	}
	void seePrescriptions(int recId)
	{
		logger.info("seePrescription Entry");
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

			boolean status = statement.execute("select * from prescription where RecId="+ recId);
			if(status){
                //query is a select query.
                ResultSet rs = statement.getResultSet();
                int count = 0;
                while(rs.next())
                {
                	count++;
                	System.out.println("\nPrescription Id\t:" + rs.getInt("Histid"));
                	int did = rs.getInt("did");
                	Date date = rs.getDate("date");
                	if(date!=null)
                	System.out.println("Date\t\t\t:" + SmartHealthCareSystem.simpleDateFormat.format(date));
                	System.out.println("Test Adviced\t\t:" + rs.getString("Test_Adviced"));
                	System.out.println("Medicine Prescribed\t:" + rs.getString("Medicine_Prescribed"));
                	System.out.println("Patient Status\t\t:"+ rs.getString("Patient_Status"));
                	if(rs.getString("location") !=null)
                	{
                		System.out.println("Location\t\t:" + rs.getString("location"));
                	}
                	
                	System.out.println("Assigned Doctor Id\t:" + rs.getString("did"));
                	statementDoctor.execute("select name from doctor where did=" + did);
                	ResultSet rsdoc = statementDoctor.getResultSet();
                	rsdoc.next();
                	System.out.println("Doctor Name\t\t:"+ rsdoc.getString("name"));
         
                       }
                if(count == 0)
                {
                	System.out.println("No prescription's found for the record");
                }
            }
            else
            {
            	logger.warning("Error in getting Data");
            }
		}
            catch (Exception e) {
            	
            	logger.error("Exception " + e.getMessage().toString());
}		
		logger.info("seePrescription Exit");
	}

	void cancelAppointment() {
		logger.info("cancelAppointment Entry");
		boolean flag = true;
		int count = 0;
		while (flag) {
			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();
				Statement statementDoctor = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `record` WHERE Discharge_Date is NULL and Admit_Date >= (SELECT SYSDATE()) and pid='"
								+ getPid() + "'");
				count = 0;
				if (status) {
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					while (rs.next()) {
						System.out.println("");
						count++;
						System.out.println("\nRecord Number:\t\t" + count);
						System.out.println("Appointment Date:\t\t"
								+ SmartHealthCareSystem.simpleDateFormat.format(rs.getDate("admit_date")));
						System.out.println("Location:\t\t" + rs.getString("location"));
						System.out.println("Assigned Doctor Id:\t\t" + rs.getString("did"));
						statementDoctor.execute("select name from doctor where did=" + rs.getInt("did"));
						ResultSet rsdoc = statementDoctor.getResultSet();
						rsdoc.next();
						System.out.println("Doctor Name:\t\t" + rsdoc.getString("name"));
						System.out.println("Record Description:\t\t" + rs.getString("patient_desc"));
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
					logger.warning("Error in getting Data");
				}
			} catch (Exception e) {
				logger.error("Exception " + e.getMessage().toString());

			}
			
		}
		logger.info("cancelAppointment Exit");
	}
	void loginSuccess()
	{
		logger.info("loginSuccess Entry");
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
				logger.info("User "+ getName() +" Signed out");

			}
			else
			{
				System.out.println("Invalid Choice try again");
			}
						
		}
		logger.info("loginSuccess Exit");

		
	}

	private void seeDetailedRecords() {
		logger.info("seeDetailedRecords Entry");
		int recId;
		
			seeRecords();
			System.out.print("\nEnter Record Id to see details: ");
			recId = SmartHealthCareSystem.nextint();
			seePrescriptions(recId);
			logger.info("seeDetailedRecords Exit");
	}
	public Patient() {
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