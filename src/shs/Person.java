/*
 * Ashish Jain (MT18052)
 * Aditya Mittal (MT18061)
 * Meenakshi Maindola (MT18081)
 */
package shs;

import java.text.ParseException;
import java.util.Date;

public class Person {
	
	String name;
	Date dob;
	String gender;
	private String address;
	private String phoneNumber;
	public Logging logger;

	
	
	public Person() {
		
		logger = new Logging(this.getClass().getName());

	}
	public Person(String name, Date dob,String gender, String address, String phonenumber) {
		this.setAddress(address);
		this.setDob(dob);
		this.setGender(gender);
		this.setName(name);
		this.setPhoneNumber(phonenumber);
		logger = new Logging(this.getClass().getName());

	}
	
	protected void changeContactNumber() {
		logger.info("Change Contact Number Entry");
		System.out.println("current contact number is -> " + getPhoneNumber());
		System.out.print("Enter new contact number: ");
		String newPhoneNumber = SmartHealthCareSystem.nextintString();
		setPhoneNumber(newPhoneNumber);
		logger.info("Change Contact Number Exit");
	}


	protected void changeAddress() {
		logger.info("Change Addess Entry");
		System.out.println("current Address is -> " + getAddress());
		System.out.print("Enter new Address: ");
		String newAddress = SmartHealthCareSystem.sc.nextLine();
		setAddress(newAddress);
		logger.info("Change Addess Exit");
	}


	protected void changeGender() {
		logger.info("Change Gender Entry");
		String newGender = null;
		while(true)
		{
		System.out.println("current Gender is -> " + getGender());
		System.out.println("Enter Gender");
		System.out.print("1. Male\n2. Female\n: ");
		int ch = 0;
		ch = SmartHealthCareSystem.nextint();
		if(ch == 1)
		{
			newGender = "M";
			break;
		}
		else if(ch == 2)
		{
			newGender = "F";
			break;
		}
		else
		{
			System.out.println("Invalid Input");
			logger.warning("Invalid gender choice");
		}
		}
		setGender(newGender);
		logger.info("Change Gender Exit");
	}


	protected void changeDOB() {
		logger.info("Change Date of Birth Entry");

		System.out.println("current DOB is -> " + getDob());
		//System.out.println("Enter new DOB ");
		while(true)
		{
		System.out.print("Enter new Date of Birth(YYYY-MM-DD): ");
		try {

		setDob(SmartHealthCareSystem.simpleDateFormat.parse(SmartHealthCareSystem.sc.nextLine()));
		break;

		} catch (ParseException e) {
		System.out.println("Invalid Date Format");
		logger.warning("Invalid DOB input");
		}
		}
		logger.info("Change Date of Birth Exit");
	}


	protected void changeName() {
		logger.info("Update Name Entry");
		System.out.println("current name is -> " + getName());
		System.out.print("Enter new name: ");
		String newName = SmartHealthCareSystem.sc.nextLine();
		setName(newName);
		logger.info("Update Name Exit");

		
	}
	

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
