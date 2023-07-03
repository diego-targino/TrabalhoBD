package br.ufc.coop.trabalhobd.Repositories;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufc.coop.trabalhobd.Entities.StudentCourse;

public class StudentCourseRepository extends BaseRepository<StudentCourse> {

	@Override
	public void Insert(StudentCourse entity) {
		try {
			CreateConnection();

			String sqlTemplate = "INSERT INTO aluno_disciplina (aluno_matr, disciplina_cod , periodo , nota, frequencia) VALUES ({0}, {1}, {2}, ''{3}'', {4})";

			String sqlCommand = MessageFormat.format(sqlTemplate, entity.getStudentRegistration(), entity.getCourseCode(),
					entity.getPeriod(), entity.getGrade(), entity.getAttendance());

			stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(StudentCourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public List<StudentCourse> SelectAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void Update(StudentCourse entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void Delete(long identifier) {
		throw new UnsupportedOperationException();
	}
}
