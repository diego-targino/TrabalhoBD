package br.ufc.coop.trabalhobd.Managers;

import br.ufc.coop.trabalhobd.Repositories.StudentRepository;
import br.ufc.coop.trabalhobd.VOs.SchoolRecord;
import br.ufc.coop.trabalhobd.Entities.Student;
import java.util.List;

public class StudentManager {

	private StudentRepository repository;

	public StudentManager() {
		this.repository = new StudentRepository();
	}

	public void addStudent(Student student) {
		this.repository.Insert(student);
	}

	public List<Student> getStudents() {
		return this.repository.SelectAll();
	}

	public void updateStudent(Student student) {
		this.repository.Update(student);
	}

	public void deleteStudent(Student student) {
		this.repository.Delete(student.getRegistration());
	}

	public List<Student> SearchStudents(String filter) {
		return this.repository.SearchStudents(filter);
	}
	
	public List<SchoolRecord> GetSchoolRecord(long studentRegistration) {
		return this.repository.GetSchoolRecords(studentRegistration);
	}
}
