package br.ufc.coop.trabalhobd.Managers;

import br.ufc.coop.trabalhobd.Entities.StudentCourse;
import br.ufc.coop.trabalhobd.Repositories.StudentCourseRepository;

public class StudentCourseManager {
	private StudentCourseRepository repository;

	public StudentCourseManager() {
		this.repository = new StudentCourseRepository();
	}

	public void addStudentCourse(StudentCourse studentCourse) {
		this.repository.Insert(studentCourse);
	}
}
