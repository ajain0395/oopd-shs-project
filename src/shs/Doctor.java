package shs;

import java.util.ArrayList;

public class Doctor extends Person {

	Credentials loginCredentials;
	//public String id = "DOC";
	String designation;
	int rank; // if set 1 then HOD 2 then senior 3 then junior
	Department department;
	String schedule;
	int consultationFees;
	ArrayList<Patient> patientList = new ArrayList<>();
	int salary;
	Doctor reference;//doctor that can be referred

	
	
	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public int getRank() {
		return rank;
	}



	public void setRank(int rank) {
		this.rank = rank;
	}



	public Department getDepartment() {
		return department;
	}



	public void setDepartment(Department department) {
		this.department = department;
	}



	public String getSchedule() {
		return schedule;
	}



	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}



	public int getConsultationFees() {
		return consultationFees;
	}



	public void setConsultationFees(int consultationFees) {
		this.consultationFees = consultationFees;
	}



	public int getSalary() {
		return salary;
	}



	public void setSalary(int salary) {
		this.salary = salary;
	}



	public Doctor getReference() {
		return reference;
	}



	public void setReference(Doctor reference) {
		this.reference = reference;
	}



	public Doctor() {
		// TODO Auto-generated constructor stub
		this.loginCredentials.setId("DOC");
	}
	
}
