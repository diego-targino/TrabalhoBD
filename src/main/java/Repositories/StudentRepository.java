package Repositories;

import br.ufc.coop.trabalhobd.Entities.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentRepository extends BaseRepository<Student> {

    @Override
    public void Insert(Student entity) {
        try {
            Connection conn = null;
            Statement stmt = null;
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            stmt = conn.createStatement();
            String sqlCommand = "INSERT INTO student (name, email) VALUES ('" + entity.getName() + "', '" + entity.getEmail() + "')";

            stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = null;

            rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Student> SelectAll() {
        List<Student> studentList = new ArrayList<Student>();

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            String sqlCommand = "SELECT * FROM student";
            stmt.execute(sqlCommand);

            ResultSet rs = stmt.getResultSet();
            Student student = null;

            while (rs.next()) {
                student = new Student(rs.getLong("id"), rs.getString("name"), rs.getString("email"));
                studentList.add(student);
            }

            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentList;
    }

    @Override
    public void Update(Student entity) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            String sqlCommand = "UPDATE student SET name = '" + entity.getName() + "', email = '" + entity.getEmail() + "' WHERE id = " + entity.getId();
            stmt.execute(sqlCommand);

            int rowsUpdated = stmt.getUpdateCount();

            System.out.println("Rows updated: " + rowsUpdated);

            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Delete(long id) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            String sqlCommand = "DELETE FROM student WHERE id = " + id;
            stmt.execute(sqlCommand);

            int rowsUpdated = stmt.getUpdateCount();

            System.out.println("Rows updated: " + rowsUpdated);

            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(StudentRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
