package br.ufc.coop.trabalhobd.VOs;

public class CoursesPeriod {
	private String courseName;
	private String period;
	private int studentAmount;
	private float highestGrade;
	private float lowestGrade;
	private float media;

	public CoursesPeriod(String courseName, String period, int studentAmount, float highestGrade, float lowestGrade,
			float media) {
		super();
		this.courseName = courseName;
		this.period = period;
		this.studentAmount = studentAmount;
		this.highestGrade = highestGrade;
		this.lowestGrade = lowestGrade;
		this.media = media;
	}

	@Override
	public String toString() {
		return "CoursesPeriod {courseName=" + courseName + ", period=" + period + ", studentAmount=" + studentAmount
				+ ", highestGrade=" + highestGrade + ", lowestGrade=" + lowestGrade + ", media=" + media + "}";
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

	public int getStudentAmount() {
		return studentAmount;
	}

	public void setStudentAmount(int studentAmount) {
		this.studentAmount = studentAmount;
	}

	public float getHighestGrade() {
		return highestGrade;
	}

	public void setHighestGrade(float highestGrade) {
		this.highestGrade = highestGrade;
	}

	public float getLowestGrade() {
		return lowestGrade;
	}

	public void setLowestGrade(float lowestGrade) {
		this.lowestGrade = lowestGrade;
	}

	public float getMedia() {
		return media;
	}

	public void setMedia(float media) {
		this.media = media;
	}
}
