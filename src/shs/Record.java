/*
 * Ashish Jain (MT18052)
 * Aditya Mittal (MT18061)
 * Meenakshi Maindola (MT18081)
 */
package shs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Record {
	private int recid;
	private int pid;
	private Date admit_Date;
	private Date discharge_Date;
	private String patient_Desc;
	private String disease_Identified;
	private String location;
	private int did;
	Logging logger;
	
	public Record() {
	logger =  new Logging(getClass().getName());
	}
	
	
	public int getRecid() {
		return recid;
	}
	
	void selectLocation()
	{
		logger.info("selectLocation Entry");
		while(true)
		{
			System.out.println("Select Location");
			System.out.println("1. OPD\n2. LOCAL");
			System.out.print("Enter Choice: ");
			int ch = SmartHealthCareSystem.nextint(); 
			if(ch == 1)
			{
				setLocation("OPD");
				break;
			}
			else if(ch == 2)
			{
				setLocation("LOCAL");
				break;
			}
			else
			{
				System.out.println("Invalid Input");
			}
		}
		logger.info("selectLocation Exit");
	}
	
	public void insertRecord()
	{
		logger.info("insertRecord Entry");
		String query = "INSERT INTO `record`(`Pid`, `Admit_Date`, `Patient_Desc`, `Location`, `Did`) VALUES "+
	"(?,?,?,?,?)";
		

    	try {
    				PreparedStatement pstmt = null;
    	            pstmt = SmartHealthCareSystem.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
    	            pstmt.setInt(1, getPid());
    	            pstmt.setDate(2, java.sql.Date.valueOf(SmartHealthCareSystem.simpleDateFormat.format(getAdmit_Date())));
    	            pstmt.setString(3, getPatient_Desc());
    	            pstmt.setString(4, getLocation());
    	            pstmt.setInt(5, getDid());
    	            
    	            pstmt.executeUpdate();
    	            
    		           ResultSet rs = pstmt.getGeneratedKeys();
    		            if(rs != null && rs.next()){
    		                setRecid(rs.getInt(1));
    		            }
    	            else
    	            {
    	            	System.out.println("Registration Failed");
    	            }
    			}
    	            catch (Exception e) {
    	            	
    	            	logger.error("Exception " + e.getMessage().toString());
    	}		        
    	logger.info("insertRecord Exit");
	}
	public static void updateScedule(int scheduleId) {

		String query2 = "UPDATE `schedule` SET `Count_Of_Patients`=Count_Of_Patients - 1 WHERE ScheduleId="+scheduleId;
	
        Statement statement;
		try {
			statement = SmartHealthCareSystem.con.createStatement();
			
            int count = statement.executeUpdate(query2);
            if(count == 1)
            {
            	System.out.println("Appointment Booked Successfully");
            }
	        
		} catch (SQLException e) {
			System.out.println("issue in updating Schedule");
			//e.printStackTrace();
		}
        	 

		
	}
	
	public static void deleteRecord(int recId,String date,int dId)
	{
		String query = "DELETE FROM `record` WHERE RecId=?";
		String query2 = "UPDATE `schedule` SET `Count_Of_Patients`=Count_Of_Patients + 1 WHERE Did="+dId+" and Date='"+date+"'";
		
        PreparedStatement pstmt;
        Statement statement;
		try {
			pstmt = SmartHealthCareSystem.con.prepareStatement(query);
			statement = SmartHealthCareSystem.con.createStatement();
			pstmt.setInt(1, recId);
	        // execute the delete statement
	        pstmt.executeUpdate();
            int count = statement.executeUpdate(query2);
            if(count == 1)
            {
            	System.out.println("Appointment Cancelled Successfully");
            }

	        
		} catch (SQLException e) {
			e.printStackTrace();
		}
        	 
        // set the corresponding param
        
	}
	
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getAdmit_Date() {
		return admit_Date;
	}
	public void setAdmit_Date(Date admit_Date) {
		this.admit_Date = admit_Date;
	}
	public Date getDischarge_Date() {
		return discharge_Date;
	}
	public void setDischarge_Date(Date discharge_Date) {
		this.discharge_Date = discharge_Date;
	}
	public String getPatient_Desc() {
		return patient_Desc;
	}
	public void setPatient_Desc(String patient_Desc) {
		this.patient_Desc = patient_Desc;
	}
	public String getDisease_Identified() {
		return disease_Identified;
	}
	public void setDisease_Identified(String disease_Identified) {
		this.disease_Identified = disease_Identified;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}


	
}

