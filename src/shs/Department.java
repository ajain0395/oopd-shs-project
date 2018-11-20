/*
 * Ashish Jain (MT18052)
 * Aditya Mittal (MT18061)
 * Meenakshi Maindola (MT18081)
 */
package shs;


import java.sql.ResultSet;
import java.sql.Statement;

public class Department {

	private int deptId;
	private String deptartmentName;
	private int dId;
	
	public Department()
	{
		
	}
	
	public Department(int deptId) {
		

			try {
				Statement statement = SmartHealthCareSystem.con.createStatement();

				boolean status = statement.execute(
						"SELECT * FROM `department` where DeptId =" + deptId);
				int count = 0;
				if (status) {
				
					// query is a select query.
					ResultSet rs = statement.getResultSet();
					while (rs.next()) {
						count++;
					}
					rs.beforeFirst();
					rs.next();
					if(count > 0)
					{
						setdId(rs.getInt("did"));
						setDeptId(deptId);
						setDeptartmentName(rs.getString("deptname"));
						System.out.println(getDeptartmentName());
						}
				}
				
				
			} catch (Exception e) {
				System.out.println("Exception:"+e.getMessage().toString());
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
