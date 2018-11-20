package shs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Department {

	private int deptId;
	private String deptartmentName;
	private int dId;
	Connection con;
	
	public Department()
	{
		
	}
	
	public Department(int deptId) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
			
			try {
				Statement statement = con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `department` where DeptId =" + deptId);
				int count = 0;
				if (status) {
				
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					while (rs.next()) {
						count++;
					}
					if(count > 0)
					{
						setdId(rs.getInt("did"));
						setDeptId(deptId);
						setDeptartmentName(rs.getString("deptname"));
						System.out.println(getDeptartmentName());
						}
				}
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage().toString());
			}
		
		} catch (Exception e) {
			
		}

	}
	
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getDeptartmentName() {
		return deptartmentName;
	}
	public void setDeptartmentName(String deptartmentName) {
		this.deptartmentName = deptartmentName;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	
	
}
