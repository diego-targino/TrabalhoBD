package br.ufc.coop.trabalhobd.Repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.ufc.coop.trabalhobd.Entities.Course;
import br.ufc.coop.trabalhobd.VOs.CourseClass;
import br.ufc.coop.trabalhobd.VOs.CoursesPeriod;

public class CourseRepository extends BaseRepository<Course> {

	@Override
	public void Insert(Course entity) {
		try {
			CreateConnection();

			String sqlTemplate = "INSERT INTO disciplina (nome, creditos) VALUES (''{0}'',{1})";

			String sqlCommand = MessageFormat.format(sqlTemplate, entity.getName(), entity.getCredits());

			stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(CourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@Override
	public List<Course> SelectAll() {
		List<Course> courseList = new ArrayList<Course>();

		try {
			CreateConnection();

			String sqlCommand = "SELECT * FROM disciplina";
			stmt.execute(sqlCommand);

			ResultSet rs = stmt.getResultSet();
			Course course = null;

			while (rs.next()) {
				course = new Course(rs.getLong("codigo"), rs.getString("nome"), rs.getInt("creditos"));

				courseList.add(course);
			}

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(CourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

		return courseList;
	}

	@Override
	public void Update(Course entity) {
		try {
			CreateConnection();

			String sqlTemplate = "UPDATE disciplina SET nome = ''{0}'', creditos = ''{1}'' WHERE codigo = {2}";

			String sqlCommand = MessageFormat.format(sqlTemplate, entity.getName(), entity.getCredits(), entity.getCode());

			stmt.execute(sqlCommand);

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(CourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void Delete(long identifier) {
		try {
			CreateConnection();

			String sqlTemplate = "DELETE FROM disciplina WHERE codigo = {0}";
			String sqlCommand = MessageFormat.format(sqlTemplate, identifier);

			stmt.execute(sqlCommand);

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(CourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<CourseClass> GetCourseClass(long CourseCode, String period) {
		List<CourseClass> courseClass = new ArrayList<CourseClass>();

		try {
			CreateConnection();

			String sqlTemplate = "SELECT A.matricula, A.nome, AD.nota, AD.frequencia FROM aluno AS A JOIN aluno_disciplina AS AD ON AD.aluno_matr = A.matricula WHERE AD.disciplina_cod = {0} AND AD.periodo = ''{1}''";
			String sqlCommand = MessageFormat.format(sqlTemplate, CourseCode, period);

			stmt.execute(sqlCommand);

			ResultSet rs = stmt.getResultSet();
			CourseClass courseStudent = null;

			while (rs.next()) {
				courseStudent = new CourseClass(rs.getLong("matricula"), rs.getString("nome"), rs.getFloat("nota"),
						rs.getInt("frequencia"));

				courseClass.add(courseStudent);
			}

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(CourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

		return courseClass;
	}

	public List<String> getPeriods(long courseCode) {
		List<String> periodsList = new ArrayList<String>();
		try {
			CreateConnection();
			String sqlTemplate = "SELECT DISTINCT periodo FROM aluno_disciplina WHERE disciplina_cod = {0}";
			String sqlCommand = MessageFormat.format(sqlTemplate, courseCode);

			stmt.execute(sqlCommand);

			String period;

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				period = rs.getString("periodo");

				periodsList.add(period);
			}

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(CourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

		return periodsList;
	}

	public List<CoursesPeriod> SelectCoursesPeriod() {
		List<CoursesPeriod> courseList = new ArrayList<CoursesPeriod>();

		try {
			CreateConnection();

			String sqlCommand = "SELECT  D.nome, AD.periodo, COUNT(AD.aluno_matr) AS qtd_alunos, MAX(AD.nota) AS maior_nota, MIN(AD.nota) AS menor_nota, AVG(AD.nota) AS media FROM disciplina AS D JOIN aluno_disciplina AS AD ON AD.disciplina_cod = D.codigo GROUP BY AD.disciplina_cod, AD.periodo";
			stmt.execute(sqlCommand);

			ResultSet rs = stmt.getResultSet();
			CoursesPeriod course = null;

			while (rs.next()) {
				course = new CoursesPeriod(rs.getString("nome"), rs.getString("periodo"), rs.getInt("qtd_alunos"),
						rs.getFloat("maior_nota"), rs.getFloat("menor_nota"), rs.getFloat("media"));

				courseList.add(course);
			}

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(CourseRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

		return courseList;
	}
}
