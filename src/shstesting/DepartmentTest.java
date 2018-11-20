/*
 * Ashish Jain (MT18052)
 * Aditya Mittal (MT18061)
 * Meenakshi Maindola (MT18081)
 */
package shstesting;

import java.sql.DriverManager;

import org.junit.Assert;
import org.junit.Test;

import shs.Department;
import shs.SmartHealthCareSystem;

public class DepartmentTest {
	
	public DepartmentTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			SmartHealthCareSystem.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
		
		} catch (Exception e) {
			//logger.error(e.getMessage().toString());
		}
	}

	Department dept = new Department(0);
	@Test
	public void test() {
		
		System.out.println(dept.getdId());
		System.out.println(dept.getDeptartmentName());
		Assert.assertEquals(6, dept.getdId());
		//fail("Not yet implemented");
	}

}
