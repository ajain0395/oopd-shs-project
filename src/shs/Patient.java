package shs;

import java.util.Date;

public class Patient extends Person {
	
	int pid;
	String password;
	
	public Patient(String name, Date dob,String gender, String address, String phonenumber,int pid,String password) {
		// TODO Auto-generated constructor stub
		super(name, dob, gender, address, phonenumber);
		this.setPid(pid);
		this.setPassword(password);
	}
	void loginSuccess()
	{
		boolean flag = true;
		while(flag)
		{
			System.out.println("Hello " + getName());
			System.out.println("1. See Profile");
			System.out.println("2. Update Profile");
			System.out.println("3. See Profile");
			System.out.println("4. See Profile");
			System.out.println("Enter Your Choice :");
			
	//		if()
			
		}
		
	//	SmartHealthCareSystem.sc
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
