package shs;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
		Doctor instance = null;
     //   System.out.println("Hello ");
		try {
			Statement statement = SmartHealthCareSystem.con.createStatement();
			boolean status = statement.execute("select * from doctor where Did = "+ docId +" and password = "+ password);
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
		System.out.println("Doctor id -> " + getDocId());
		System.out.println("Doctor name -> " + getName());
		System.out.println("Date of Birth -> " + getDob());
		System.out.println("Gender -> " + getGender());
		System.out.println("Address -> " + getAddress());
		System.out.println("Contact Number -> " + getPassword());
		System.out.println("Department -> " + getDeptId());
		System.out.println("Rank -> " + getRank());
		System.out.println("Surgeon - > " + getSurgeon());
		System.out.println("Opd Fees -> " + getOpdFees());
	}
	
	public void loginSucess()
	{
		boolean flag = true;
		while(flag)
		{
			System.out.println("Welcome Doctor " + getName());
			System.out.println("1. View Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. View Doctor Schedule");
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
	                	System.out.println("Schedule id is" + rs.getInt("ScheduleId"));
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
			
			System.out.println("1. Doctor name -> " );
			System.out.println("2. Date of Birth -> ");
			System.out.println("3. Gender -> ");
			System.out.println("4. Address -> ");
			System.out.println("5. Contact Number -> ");
			System.out.println("6. Password ");
			System.out.println("7. Department -> ");
			System.out.println("8. Rank -> ");
			System.out.println("9. Surgeon - > ");
			System.out.println("10. Opd Fees -> ");
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
				flag2 = false;
			}
			else
			{
				System.out.println("Wrong input ");
				
			}
		}
	}


	private void changeOpdFees() {
		// TODO Auto-generated method stub
		
	}


	private void changeSurgeon() {
		// TODO Auto-generated method stub
		
	}


	private void changeRank() {
		// TODO Auto-generated method stub
		
	}


	private void changeDepartment() {
		// TODO Auto-generated method stub
		
	}


	private void changePassword() {
		// TODO Auto-generated method stub
		
	}


	private void changeContactNumber() {
		// TODO Auto-generated method stub
		
	}


	private void changeAddress() {
		// TODO Auto-generated method stub
		
	}


	private void changeGender() {
		// TODO Auto-generated method stub
		
	}


	private void changeDOB() {
		// TODO Auto-generated method stub
		
	}


	private void changeName() {
		// TODO Auto-generated method stub
		
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
