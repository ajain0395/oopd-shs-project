package shs;

import java.util.Date;

public class Record {
	private int recid;
	private int pid;
	private Date admit_Date;
	private Date discharge_Date;
	private String patient_Desc;
	private String disease_Identified;
	private String location;
	private int did;
	public int getRecid() {
		return recid;
	}
	public void setRecid(int recid) {
		this.recid = recid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public Date getAdmit_Date() {
		return admit_Date;
	}
	public void setAdmit_Date(Date admit_Date) {
		this.admit_Date = admit_Date;
	}
	public Date getDischarge_Date() {
		return discharge_Date;
	}
	public void setDischarge_Date(Date discharge_Date) {
		this.discharge_Date = discharge_Date;
	}
	public String getPatient_Desc() {
		return patient_Desc;
	}
	public void setPatient_Desc(String patient_Desc) {
		this.patient_Desc = patient_Desc;
	}
	public String getDisease_Identified() {
		return disease_Identified;
	}
	public void setDisease_Identified(String disease_Identified) {
		this.disease_Identified = disease_Identified;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	
}

