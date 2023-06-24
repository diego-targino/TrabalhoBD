package br.ufc.coop.trabalhobd.Entities;

public class StudentCourse {
	private long studentRegistration;
	private long courseCode;
	private String period;
	private float grade;
	private int attendance;

	public StudentCourse(long studentRegistration, long courseCode, String period, float grade, int attendance) {
		super();
		this.studentRegistration = studentRegistration;
		this.courseCode = courseCode;
		this.period = period;
		this.grade = grade;
		this.attendance = attendance;
	}

	public long getStudentRegistration() {
		return studentRegistration;
	}

	public void setStudentRegistration(long studentRegistration) {
		this.studentRegistration = studentRegistration;
	}

	public long getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(long courseCode) {
		this.courseCode = courseCode;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
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
