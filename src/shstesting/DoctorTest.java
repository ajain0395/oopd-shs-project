/*
 * Ashish Jain (MT18052)
 * Aditya Mittal (MT18061)
 * Meenakshi Maindola (MT18081)
 */
package shstesting;

import static org.junit.Assert.*;

import java.sql.DriverManager;

import org.junit.Assert;
import org.junit.Test;

import shs.Doctor;
import shs.SmartHealthCareSystem;

public class DoctorTest {

	public DoctorTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			SmartHealthCareSystem.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
		
		} catch (Exception e) {
			//logger.error(e.getMessage().toString());
		}
	}
	
	
	@Test
	public void checkgetdatafromdoctor() {

		Doctor doctor = Doctor.getDoctorById(1, "123");
		Assert.assertTrue(doctor.getName().equalsIgnoreCase("A"));
		Assert.assertTrue(doctor.getDob().equals(SmartHealthCareSystem.stringToJavaDate("1990-01-17")));
		Assert.assertTrue(doctor.getGender().equalsIgnoreCase("M"));
		Assert.assertTrue(doctor.getAddress().equalsIgnoreCase("Delhi"));
		Assert.assertTrue(doctor.getPhoneNumber().equalsIgnoreCase("7896541234"));
		Assert.assertTrue(doctor.getDeptId() == 1);
		Assert.assertTrue(doctor.getRank().equalsIgnoreCase("senior specialist"));
		Assert.assertTrue(doctor.getSurgeon().equalsIgnoreCase("YES"));
		assertTrue(doctor.getOpdFees() == 700);
		Assert.assertTrue(doctor.getHospital().equalsIgnoreCase("AIMS"));
	}

}
