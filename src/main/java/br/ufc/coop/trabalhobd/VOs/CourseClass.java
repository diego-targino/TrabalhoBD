package br.ufc.coop.trabalhobd.VOs;

public class CourseClass {
	private long studentRegistration;
	private String studentName;
	private float grade;
	private int attendance;

	public CourseClass(long studentRegistration, String studentName, float grade, int attendance) {
		super();
		this.studentRegistration = studentRegistration;
		this.studentName = studentName;
		this.grade = grade;
		this.attendance = attendance;
	}

	@Override
	public String toString() {
		return "CourseClass {studentRegistration=" + studentRegistration + ", studentName=" + studentName + ", grade="
				+ grade + ", attendance=" + attendance + "}";
	}

	public long getStudentRegistration() {
		return studentRegistration;
	}

	public void setStudentRegistration(long studentRegistration) {
		this.studentRegistration = studentRegistration;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
}
