package shs;

import java.util.ArrayList;

public class Doctor extends Person {


	int docId;
	String password;
	int deptId;
	String rank;
	String surgeon;
	int opdFees;


	public int getDocId() {
		return docId;
	}


	public void setDocId(int docId) {
		this.docId = docId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getDeptId() {
		return deptId;
	}


	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}


	public String getRank() {
		return rank;
	}


	public void setRank(String rank) {
		this.rank = rank;
	}


	public String getSurgeon() {
		return surgeon;
	}


	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}


	public int getOpdFees() {
		return opdFees;
	}


	public void setOpdFees(int opdFees) {
		this.opdFees = opdFees;
	}


	public Doctor(int docId , String name , String date , String gender , String address , String contactNumber , String password , int deptId , String rank , String Surgeon , int opdFees) {
		// TODO Auto-generated constructor stub
		this.super(name , date , gender , address , contactNumber);
		setDocId(docId);
		setPassword(password);
		setDeptId(deptId);
		setRank(rank);
		setSurgeon(Surgeon);
		setOpdFees(opdFees);
		
	}
	
}
