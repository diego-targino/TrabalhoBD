package br.ufc.coop.trabalhobd.Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseRepository<T> {

	protected static String DB_URL = "jdbc:mysql://localhost/school";
	protected static String DB_USER = "root";
	protected static String DB_PASSWORD = "root";

	protected static Connection conn = null;
	protected static Statement stmt = null;

	public void CreateConnection() throws SQLException {
		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

		stmt = conn.createStatement();
	}

	public void CloseConnection() throws SQLException {
		conn.close();

		conn = null;
		stmt = null;
	}

	public abstract void Insert(T entity);

	public abstract List<T> SelectAll();

	public abstract void Update(T entity);

	public abstract void Delete(long identifier);
}
