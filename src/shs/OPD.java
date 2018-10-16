package shs;

public class OPD implements Location{

	public Doctor getAssignedDoctor() {
		return assignedDoctor;
	}
	public void setAssignedDoctor(Doctor assignedDoctor) {
		this.assignedDoctor = assignedDoctor;
	}
	public int getType() {
		return type;
	}
	static final int type = 1;
	Doctor assignedDoctor;

}
