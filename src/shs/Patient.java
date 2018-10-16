package shs;

import java.util.ArrayList;

public class Patient extends Person {

	

	ArrayList<Record> recordHistory = new ArrayList<>();
	Credentials loginCredentials;
	public Patient() {
		// TODO Auto-generated constructor stub
		this.loginCredentials.setId("PAT");
	}
	void addRecord(Record r)
	{
		this.recordHistory.add(r);
	}
}
