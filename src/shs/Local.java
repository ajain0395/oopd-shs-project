package shs;

public class Local implements Location {

	public Ward getWard() {
		return ward;
	}
	public void setWard(Ward ward) {
		this.ward = ward;
	}
	public Doctor getAssignedDoctor() {
		return assignedDoctor;
	}
	public void setAssignedDoctor(Doctor assignedDoctor) {
		this.assignedDoctor = assignedDoctor;
	}
	public int getType() {
		return type;
	}
	static final int type = 2;
	Ward ward;
	Doctor assignedDoctor;
	
	
}