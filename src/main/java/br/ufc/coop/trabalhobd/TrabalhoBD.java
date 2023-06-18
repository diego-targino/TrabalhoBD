package br.ufc.coop.trabalhobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TrabalhoBD {

    private static String DB_URL = "jdbc:mysql://localhost/school";
    private static String DB_USER = "root";
    private static String DB_PASSWORD = "root";

    public static void main(String[] args) {
//        InsertStudent();
        SelectStudent();
//        UpdateStudent();
        DeleteStudent();
        SelectStudent();
    }

    public static void InsertStudent() {
        try {
            Connection conn = null;
            Statement stmt = null;
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            stmt = conn.createStatement();
            String sqlCommand = "INSERT INTO student (name, email) VALUES ('Jose', 'jose@gmail.com')";
            
            stmt.executeUpdate(sqlCommand, Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = null;
            
            rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }

            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void SelectStudent() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            String sqlCommand = "SELECT * FROM student";
            stmt.execute(sqlCommand);

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println("Id: " + id + " - Name: " + name + " - Email: " + email);
            }

            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void UpdateStudent() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            int id = 1;
            String newName = "Maria";
            String newEmail = "maria@gmail.com";

            String sqlCommand = "UPDATE student SET name = '" + newName + "', email = '" + newEmail + "' WHERE id = " + id;
            stmt.execute(sqlCommand);

            int rowsUpdated = stmt.getUpdateCount();

            System.out.println("Rows updated: " + rowsUpdated);

            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void DeleteStudent() {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            int id = 1;

            String sqlCommand = "DELETE FROM student WHERE id = " + id;
            stmt.execute(sqlCommand);

            int rowsUpdated = stmt.getUpdateCount();

            System.out.println("Rows updated: " + rowsUpdated);

            conn.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
