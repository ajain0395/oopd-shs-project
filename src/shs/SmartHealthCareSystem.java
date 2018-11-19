package shs;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class SmartHealthCareSystem {

	public static Scanner sc = new Scanner(System.in);
	public static Connection con;
	static String pattern = "yyyy-MM-dd";
	static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	static Date stringToSqlDate(String dateString)
	{
		Date date = Date.valueOf(dateString);
		
		return date;
	}
	
	static java.util.Date stringToJavaDate(String dateString)
	{
		java.util.Date date = null;
		try {
			date = simpleDateFormat.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return date;
		
	}
	
	static String sqlDateToString(Date date)
	{
		String s = null;
		s = date.toString();
		
		return s;
	}
	static String javaDateToString(java.util.Date date)
	{
		String s = null;
		s = simpleDateFormat.format(date);
		return s;
		
	}

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

		File theDir = new File("logs");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		   // System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		     //   System.out.println("DIR created");  
		    }
		}
		else
		{
			for(File file: theDir.listFiles()) 
			    if (!file.isDirectory()) 
			        file.delete();
		
		}
		
		
		Logging logger = new Logging("MainLogger");
		logger.info("Main Method Entry");
		Doctor doctor = null;
		Patient patient = null;
		int id;
		String password;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SHSDB", "root", "abcd1234");
		
		} catch (Exception e) {
			logger.error(e.getMessage().toString());
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
				if(admin.adminLogin() == 1)
				{
					admin.showAdminOptions();	
				}
				else
				{
					System.out.println("Admin Login Failed");
				}
				
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
					System.out.print("Enter Id: ");
					id = nextint();
					System.out.print("Enter Password: ");
					password = sc.nextLine();
				patient = Patient.getPatientById(id, password);
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
				
				System.out.print("Enter Id: ");
				id = nextint();
				System.out.print("Enter Password: ");
				password = sc.nextLine();
				doctor = Doctor.getDoctorById(id, password);
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
		logger.info("Main Method Exit");

	}

}
