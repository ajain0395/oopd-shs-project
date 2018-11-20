package shstesting;


import java.sql.DriverManager;

import org.junit.Assert;
import org.junit.Test;

import shs.Patient;
import shs.SmartHealthCareSystem;

public class PatientTest {

	
	public PatientTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			SmartHealthCareSystem.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
		
		} catch (Exception e) {
			//logger.error(e.getMessage().toString());
		}
	}
	
	Patient patient;
	int pid;
	@Test
	public void checkinsertDataPatint() {
		 patient = new Patient("ABC",SmartHealthCareSystem.stringToJavaDate("1995-14-13"), "M", "New Delhi", "8477966422", "12345678");
		pid = patient.insertPatient();
	//	System.out.println(pid);
		
	//	checkgetdataPatient(pid);

	}
	@Test
	public void checkgetdataPatient()
	{
		Patient patient2 = Patient.getPatientById(12, "12345678");
		
		Assert.assertTrue(patient2.getAddress().equals("New Delhi"));
		Assert.assertTrue(patient2.getName().equals("ABC"));
		Assert.assertTrue(patient2.getDob().equals(SmartHealthCareSystem.stringToJavaDate("1995-14-13")));
		Assert.assertTrue(patient2.getGender().equals("M"));
		Assert.assertTrue(patient2.getPassword().equals("12345678"));
		Assert.assertTrue(patient2.getPhoneNumber().equals("8477966422"));
		Assert.assertEquals(12, patient2.getPid());
	}

}
