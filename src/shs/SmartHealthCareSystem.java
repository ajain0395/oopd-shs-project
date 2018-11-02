package shs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SmartHealthCareSystem implements Billing {

	/*
	 * Ashish Jain Date date = simpleDateFormat.parse("12-01-2018");
	 * 
	 * String pattern = "MM-dd-yyyy"; SimpleDateFormat simpleDateFormat = new
	 * SimpleDateFormat(pattern); String date = simpleDateFormat.format(new Date());
	 */
	public static Scanner sc = new Scanner(System.in);
	public static Connection con;
	static String pattern = "yyyy-MM-dd";
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	public static String nextintString() {
		boolean flag = true;
		String str = null;
		while (flag) {
			str = sc.nextLine();
			if (!str.matches("[0-9]+")) {
				System.out.println("Invalid input enter numbers only");
			} else {
				break;
			}
		}
		return str;
//		return Integer.parseInt('0' + str);
	}
	
	public static int nextint() {
		boolean flag = true;
		String str = null;
		while (flag) {
			str = sc.nextLine();
			if (!str.matches("[0-9]+")) {
				System.out.println("Invalid input enter numbers only");
			} else {
				break;
			}
		}
		return Integer.parseInt('0' + str);
	}

	Doctor registerDoctor() {

		return null;
	}

	Patient admitPatient() {

		return null;
	}

	void login() {

	}

	public void generateBill(Patient p) {

	}

	void displayDoctors() {

	}

	void displayWards() {

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Doctor doctor = null;
		Patient patient = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
			/*
			 * ResultSet rs = stmt.executeQuery("select * from dummy"); while (rs.next()) {
			 * System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " +
			 * rs.getString(3)); }
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}

		boolean flag = true;
		while (flag) {
			System.out.println("1. Admin Login");
			System.out.println("2. Patient Options");
			System.out.println("3. Doctor Login");
			System.out.println("4. Exit");
			int choice = nextint();
			if(choice == 1)
			{
				Admin admin = new Admin();
				admin.showAdminOptions();
			}
			else if (choice == 2) {
			
				boolean flag2 = true;
				while(flag2) {
				System.out.println("Patient Options");
				System.out.println("1. Login");
				System.out.println("2. Register");
				System.out.println("3. Previous Menu");
				System.out.print("Enter Choice: ");
				choice = nextint();
				if(choice == 1)
				{
				patient = Patient.getPatientById(1, "123");
				if (patient == null) {
					System.out.println("Record Not Found");
				} else {
					patient.loginSuccess();
				}
				}
				else if(choice == 2)
				{
					patient = new Patient();
					patient.registerPatient();
				}
				else if (choice == 3)
				{
					flag2 = false;
				}
				else {
					System.out.println("Invaid Choice");
				}
				}
			
			} else if (choice == 3) {
				doctor = Doctor.getDoctorById(1, "123");
				if (doctor == null) {
					System.out.println("Record Not Found");
				} else {
					doctor.loginSuccess();
				}
			}
			else if(choice == 4)
			{
				flag = false;
			}
			else
			{
				System.out.println("Invalid Choice");
			}
		}
	}

}
