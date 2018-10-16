package shs;

import java.util.*;

public class Staff extends Person {
	
	String type;//management or technical or Cleaning staff
	Date dateofJoining;
	int salary;
	String timing;
	int shift;
	String qualification;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDateofJoining() {
		return dateofJoining;
	}
	public void setDateofJoining(Date dateofJoining) {
		this.dateofJoining = dateofJoining;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public int getShift() {
		return shift;
	}
	public void setShift(int shift) {
		this.shift = shift;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	
	
}
