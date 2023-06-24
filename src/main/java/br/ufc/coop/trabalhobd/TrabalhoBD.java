package br.ufc.coop.trabalhobd;

import br.ufc.coop.trabalhobd.Entities.Course;
import br.ufc.coop.trabalhobd.Entities.Student;
import br.ufc.coop.trabalhobd.Entities.StudentCourse;
import br.ufc.coop.trabalhobd.Managers.CourseManager;
import br.ufc.coop.trabalhobd.Managers.StudentCourseManager;
import br.ufc.coop.trabalhobd.Managers.StudentManager;
import br.ufc.coop.trabalhobd.Util.Converter;
import br.ufc.coop.trabalhobd.VOs.CourseClass;
import br.ufc.coop.trabalhobd.VOs.SchoolRecord;

public class TrabalhoBD {

	public static void main(String[] args) {
		testStudent();
	}

	public static void testStudentCourse() {
		StudentCourseManager studentCourseManager = new StudentCourseManager();
		// long studentRegistration, long courseCode, String period, float grade, int
		// attendance
		StudentCourse studentCourse = new StudentCourse(2, 3, "2021.1", 8, 90);

		studentCourseManager.addStudentCourse(studentCourse);
	}

	public static void testCourse() {
		CourseManager courseManager = new CourseManager();

		Course course = new Course("Química avançada do baiao de dois", 7);

		//courseManager.addCourse(course);

		for (CourseClass c : courseManager.GetCourseClass(1, "2021.2")) {
			System.out.println(c.toString() + "\n");
		}
	}

	public static void testStudent() {
		StudentManager studentManager = new StudentManager();

		Student student = new Student( "joao dos quebra quixos", "joao.quebra.queixos@gmail.com", "2193232312",
				Converter.StringToDate("23/12/2001"), true);

		studentManager.addStudent(student);
//		for (Student s : studentManager.SearchStudents("paulo")) {
//			System.out.println(s.toString() + "\n");
//		}
//		for (SchoolRecord s : studentManager.GetSchoolRecord(2)) {
//			System.out.println(s.toString() + "\n");
//		}

	}
}
