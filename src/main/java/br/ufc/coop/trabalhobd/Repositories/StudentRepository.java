package br.ufc.coop.trabalhobd.Repositories;

import br.ufc.coop.trabalhobd.Entities.Student;
import br.ufc.coop.trabalhobd.VOs.SchoolRecord;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRepository extends BaseRepository<Student> {

	@Override
	public void Insert(Student entity) {
		try {
			CreateConnection();

			String sqlTemplate = "INSERT INTO aluno (nome, email, telefone, data_nasc, sexo) VALUES (''{0}'',''{1}'',''{2}'',''{3}'',{4})";

			String sqlCommand = MessageFormat.format(sqlTemplate, entity.getName(), entity.getEmail(), entity.getCellphone(),
					entity.getBirth_date().toString(), entity.isGender());

			stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public List<Student> SelectAll() {
		List<Student> alunoList = new ArrayList<Student>();

		try {
			CreateConnection();

			String sqlCommand = "SELECT A.*, CASE WHEN (AVG(AD.nota) IS NULL) THEN 0 WHEN (AVG(AD.nota) IS NOT NULL)THEN AVG(AD.nota) END AS media FROM aluno AS A LEFT JOIN aluno_disciplina AS AD on AD.aluno_matr = A.matricula GROUP BY A.matricula";

			stmt.execute(sqlCommand);

			ResultSet rs = stmt.getResultSet();
			Student aluno = null;

			while (rs.next()) {
				aluno = new Student(rs.getLong("matricula"), rs.getString("nome"), rs.getString("email"),
						rs.getString("telefone"), rs.getDate("data_nasc"), rs.getBoolean("sexo"), rs.getString("distincao"),
						rs.getFloat("media"));

				alunoList.add(aluno);
			}

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

		return alunoList;
	}

	@Override
	public void Update(Student entity) {
		try {
			CreateConnection();

			String sqlTemplate = "UPDATE aluno SET nome = ''{0}'', email = ''{1}'', telefone = ''{2}'', data_nasc = ''{3}'', sexo = {4} WHERE matricula = {5}";

			String sqlCommand = MessageFormat.format(sqlTemplate, entity.getName(), entity.getEmail(), entity.getCellphone(),
					entity.getBirth_date().toString(), entity.isGender(), entity.getRegistration());

			stmt.execute(sqlCommand);

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void Delete(long identifier) {
		try {
			CreateConnection();

			String sqlTemplate = "DELETE FROM aluno WHERE matricula = {0}";
			String sqlCommand = MessageFormat.format(sqlTemplate, identifier);

			stmt.execute(sqlCommand);

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<Student> SearchStudents(String filter) {
		List<Student> studentList = new ArrayList<Student>();

		try {
			CreateConnection();

			String sqlTemplate = "SELECT * FROM aluno WHERE nome like ''%{0}%'' OR email like ''%{0}%''";

			String sqlCommand = MessageFormat.format(sqlTemplate, filter);

			stmt.execute(sqlCommand);

			Student student = null;

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				student = new Student(rs.getLong("matricula"), rs.getString("nome"), rs.getString("email"),
						rs.getString("telefone"), rs.getDate("data_nasc"), rs.getBoolean("sexo"));

				studentList.add(student);
			}

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

		return studentList;
	}

	public List<SchoolRecord> GetSchoolRecords(long studentRegistration) {
		List<SchoolRecord> SchoolRecords = new ArrayList<SchoolRecord>();

		try {
			CreateConnection();

			String sqlTemplate = "SELECT D.nome, AD.periodo, AD.nota, AD.frequencia FROM disciplina AS D JOIN aluno_disciplina AS AD ON AD.disciplina_cod = D.codigo WHERE AD.aluno_matr = {0}";

			String sqlCommand = MessageFormat.format(sqlTemplate, studentRegistration);

			stmt.execute(sqlCommand);

			SchoolRecord course = null;

			ResultSet rs = stmt.getResultSet();

			while (rs.next()) {
				course = new SchoolRecord(rs.getString("nome"), rs.getString("periodo"), rs.getFloat("nota"),
						rs.getInt("frequencia"));

				SchoolRecords.add(course);
			}

			CloseConnection();

		} catch (SQLException ex) {
			Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
		}

		return SchoolRecords;
	}

}
