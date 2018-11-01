package shs;

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
		this.setName(name);
		this.setPhoneNumber(phonenumber);
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
