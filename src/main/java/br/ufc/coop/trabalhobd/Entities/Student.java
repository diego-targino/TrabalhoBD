package br.ufc.coop.trabalhobd.Entities;

import java.sql.Date;
import java.time.LocalDate;

public class Student {
	private long registration;
	private String name;
	private String email;
	private String cellphone;
	private Date birth_date;
	private boolean gender;

	public Student(long registration, String name, String email) {
		this.registration = registration;
		this.name = name;
		this.email = email;
		this.birth_date = Date.valueOf(LocalDate.now());
	}

	public Student(String name, String email) {
		this.name = name;
		this.email = email;
		this.birth_date = Date.valueOf(LocalDate.now());
	}

	public Student(long registration, String name, String email, String cellphone, Date birth_date, boolean gender) {
		super();
		this.registration = registration;
		this.name = name;
		this.email = email;
		this.cellphone = cellphone;
		this.birth_date = birth_date;
		this.gender = gender;
	}
	
	public Student(String name, String email, String cellphone, Date birth_date, boolean gender) {
		super();
		this.name = name;
		this.email = email;
		this.cellphone = cellphone;
		this.birth_date = birth_date;
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student {registration=" + registration + ", name=" + name + ", email=" + email + ", cellphone=" + cellphone
				+ ", birth_date=" + birth_date + ", gender=" + gender + "}";
	}

	public long getRegistration() {
		return registration;
	}

	public void setRegistration(long registration) {
		this.registration = registration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

}
