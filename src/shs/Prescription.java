package shs;

import java.util.Date;

public class Prescription {
	
	Date dateofAppointment;//current date
	String medicineName;
	String medicalTests;
	String remarks;
	Date nextAppointment;//next Date
	
	public Date getDateofAppointment() {
		return dateofAppointment;
	}
	public void setDateofAppointment(Date dateofAppointment) {
		this.dateofAppointment = dateofAppointment;
	}
	public String getMedicineName() {
		return medicineName;
	}
	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}
	public String getMedicalTests() {
		return medicalTests;
	}
	public void setMedicalTests(String medicalTests) {
		this.medicalTests = medicalTests;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getNextAppointment() {
		return nextAppointment;
	}
	public void setNextAppointment(Date nextAppointment) {
		this.nextAppointment = nextAppointment;
	}
	
}
