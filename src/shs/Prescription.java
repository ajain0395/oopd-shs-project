package shs;

import java.util.Date;

public class Prescription {
	
	private int histId;
	private int dId;
	private int recId;
	private String testAdviced;
	private String testReport;
	private String medicinePrescribed;
	private Date date;
	private String status;
	private String feesPerDay;
	private String location;
	private int wardId;
	public int getHistId() {
		return histId;
	}
	public void setHistId(int histId) {
		this.histId = histId;
	}
	public int getdId() {
		return dId;
	}
	public void setdId(int dId) {
		this.dId = dId;
	}
	public int getRecId() {
		return recId;
	}
	public void setRecId(int recId) {
		this.recId = recId;
	}
	public String getTestAdviced() {
		return testAdviced;
	}
	public void setTestAdviced(String testAdviced) {
		this.testAdviced = testAdviced;
	}
	public String getTestReport() {
		return testReport;
	}
	public void setTestReport(String testReport) {
		this.testReport = testReport;
	}
	public String getMedicinePrescribed() {
		return medicinePrescribed;
	}
	public void setMedicinePrescribed(String medicinePrescribed) {
		this.medicinePrescribed = medicinePrescribed;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFeesPerDay() {
		return feesPerDay;
	}
	public void setFeesPerDay(String feesPerDay) {
		this.feesPerDay = feesPerDay;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getWardId() {
		return wardId;
	}
	public void setWardId(int wardId) {
		this.wardId = wardId;
	}
	
	
	
}
