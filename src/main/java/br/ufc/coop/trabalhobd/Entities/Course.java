package br.ufc.coop.trabalhobd.Entities;

public class Course {
	private long code;
	private String name;
	private int credits;

	public Course(long code, String name, int credits) {
		super();
		this.code = code;
		this.name = name;
		this.credits = credits;
	}

	public Course(String name, int credits) {
		super();
		this.name = name;
		this.credits = credits;
	}

	@Override
	public String toString() {
		return "Course {code=" + code + ", name=" + name + ", credits=" + credits + "}";
	}

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}
}
