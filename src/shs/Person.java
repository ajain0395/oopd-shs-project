package shs;

import java.text.ParseException;
import java.util.Date;

public class Person {
	
	String name;
	Date dob;
	String gender;
	private String address;
	private String phoneNumber;
	
	
	public Person() {
		// TODO Auto-generated constructor stub
	}
	public Person(String name, Date dob,String gender, String address, String phonenumber) {
		// TODO Auto-generated constructor stub
		this.setAddress(address);
		this.setDob(dob);
		this.setGender(gender);
		this.setName(name);
		this.setPhoneNumber(phonenumber);
	}
	
	protected void changeContactNumber() {
		// TODO Auto-generated method stub
		System.out.println("current contact number is -> " + getPhoneNumber());
		System.out.println("Enter new contact number ");
		String newPhoneNumber = SmartHealthCareSystem.sc.nextLine();
		setPhoneNumber(newPhoneNumber);
	}


	protected void changeAddress() {
		// TODO Auto-generated method stub
		System.out.println("current Address is -> " + getAddress());
		System.out.println("Enter new Address ");
		String newAddress = SmartHealthCareSystem.sc.nextLine();
		setAddress(newAddress);
	}


	protected void changeGender() {
		// TODO Auto-generated method stub

		System.out.println("current Gender is -> " + getGender());
		System.out.println("Enter new Gender ");
		String newGender = SmartHealthCareSystem.sc.nextLine();
		setGender(newGender);
	}


	protected void changeDOB() {
		// TODO Auto-generated method stub

		System.out.println("current DOB is -> " + getDob());
		//System.out.println("Enter new DOB ");
		while(true)
		{
		System.out.print("Enter new Date of Birth(YYYY-MM-DD): ");
		try {

		setDob(SmartHealthCareSystem.simpleDateFormat.parse(SmartHealthCareSystem.sc.nextLine()));
		break;

		} catch (ParseException e) {
		// TODO Auto-generated catch block
		System.out.println("Invalid Date Format");
		}
		}
	}


	protected void changeName() {
		// TODO Auto-generated method stub
		System.out.println("current name is -> " + getName());
		System.out.println("Enter new name ");
		String newName = SmartHealthCareSystem.sc.nextLine();
		setName(newName);
		
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
