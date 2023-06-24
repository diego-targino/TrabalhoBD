package br.ufc.coop.trabalhobd.VOs;

public class SchoolRecord {
	private String courseName;
	private String period;
	private float grade;
	private int attendance;

	public SchoolRecord(String courseName, String period, float grade, int attendance) {
		super();
		this.courseName = courseName;
		this.period = period;
		this.grade = grade;
		this.attendance = attendance;
	}

	@Override
	public String toString() {
		return "SchoolRecord { courseName=" + courseName + ", period=" + period + ", grade=" + grade + ", attendance="
				+ attendance + " }";
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
