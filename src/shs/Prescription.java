package shs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class Prescription {
	
	private int histId;
	private int dId;
	private int recId;
	private String testAdviced;
	private String testReport;
	private String medicinePrescribed;
	private Date date;
	private String status;
	private int feesPerDay;
	private String location;
	private int wardId;
	public int getHistId() {
		return histId;
	}
	public void setHistId(int histId) {
		this.histId = histId;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getTestAdviced() {
		return testAdviced;
	}
	public void setTestAdviced(String testAdviced) {
		this.testAdviced = testAdviced;
	}
	public String getTestReport() {
		return testReport;
	}
	public void setTestReport(String testReport) {
		this.testReport = testReport;
	}
	public String getMedicinePrescribed() {
		return medicinePrescribed;
	}
	public void setMedicinePrescribed(String medicinePrescribed) {
		this.medicinePrescribed = medicinePrescribed;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getFeesPerDay() {
		return feesPerDay;
	}
	public void setFeesPerDay(int feesPerDay) {
		this.feesPerDay = feesPerDay;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getWardId() {
		return wardId;
	}
	public void setWardId(int wardId) {
		this.wardId = wardId;
	}
	
	public void inputPrescription(int docId , int recorId , Date admitDate , int fee)
	{
		
		setdId(docId);	
		setRecId(recorId);		
		System.out.print("\nAdvise Test: ");
		setTestAdviced(SmartHealthCareSystem.sc.nextLine());
		
		System.out.print("Advise Medicine: ");
		setMedicinePrescribed(SmartHealthCareSystem.sc.nextLine());
		
		setDate(admitDate);
		while(true)
		{
		System.out.println("\nEnter patient's status\n1. critical\n2. non-critical");
		int cric = SmartHealthCareSystem.nextint();
		if(cric==1)
		{
			setStatus("critical");
			break;
		}
		else if(cric ==2 )
		{
			setStatus("non-critical");
			break;
		}
		else
		{
			System.out.println("Wrong choice");
		}
		}
		
		setFeesPerDay(fee);
		while(true)
		{
		System.out.println("\nEnter Location of patient\n1. OPD\n2. Local ");
		int loc = SmartHealthCareSystem.nextint();
		if(loc==1)
		{
			setLocation("OPD");
			break;
		}
		else if(loc ==2 )
		{
			setLocation("Local");
			break;
		}
		else
		{
			System.out.println("Wrong choice");
		}
		}	
		
		
	}
	
	public void updatePriscription()
	{
		try {
		//	Statement statement = SmartHealthCareSystem.con.createStatement();
		
			String query = "INSERT INTO prescription( Did, RecId, Test_Adviced, Medicine_Prescribed, Date , Patient_Status , Fees_Per_Day, Location ) VALUES "
					+ "(?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = null;
            pstmt = SmartHealthCareSystem.con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, getdId());
            pstmt.setInt(2, getRecId());
            pstmt.setString(3, getTestAdviced());
            pstmt.setString(4, getMedicinePrescribed());
            pstmt.setString(6, getStatus());
            pstmt.setString(5, SmartHealthCareSystem.javaDateToString(new Date()));
            pstmt.setString(8, getLocation());
            pstmt.setInt(7, getFeesPerDay());
            
//            pstmt.setDate(2, appointDate);
//            pstmt.setTime(4, endTime);
//            pstmt.setInt(5, countPatinets);
//            //pstmt.setString(6, getPassword());
            pstmt.executeUpdate();
	           ResultSet rs = pstmt.getGeneratedKeys();
	            if(rs != null && rs.next()){
	            //    System.out.println("Generated Emp Id: "+rs.getInt(1));
	                //setPid(rs.getInt(1));
	                System.out.println("New Prescription added ");
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
	
	
}
