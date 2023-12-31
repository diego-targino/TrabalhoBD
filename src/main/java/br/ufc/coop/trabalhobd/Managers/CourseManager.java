package br.ufc.coop.trabalhobd.Managers;

import java.util.List;

import br.ufc.coop.trabalhobd.Entities.Course;
import br.ufc.coop.trabalhobd.Repositories.CourseRepository;
import br.ufc.coop.trabalhobd.VOs.CourseClass;
import br.ufc.coop.trabalhobd.VOs.CoursesPeriod;

public class CourseManager {
	private CourseRepository repository;

	public CourseManager() {
		this.repository = new CourseRepository();
	}

	public void addCourse(Course course) {
		this.repository.Insert(course);
	}

	public List<Course> getCourse() {
		return this.repository.SelectAll();
	}

	public void updateCourse(Course course) {
		this.repository.Update(course);
	}

	public void deleteCourse(long codigo) {
		this.repository.Delete(codigo);
	}

	public List<CourseClass> GetCourseClass(long CourseCode, String period) {
		return this.repository.GetCourseClass(CourseCode, period);
	}

	public List<String> getPeriods(long courseCode) {
		return this.repository.getPeriods(courseCode);
	}

	public List<CoursesPeriod> SelectCoursesPeriod() {
		return this.repository.SelectCoursesPeriod();
	}
}
