package Repositories;

import java.util.List;

public abstract class BaseRepository<T> {

    protected static String DB_URL = "jdbc:mysql://localhost/school";
    protected static String DB_USER = "root";
    protected static String DB_PASSWORD = "root";

    public abstract void Insert(T entity);

    public abstract List<T> SelectAll();

    public abstract void Update(T entity);

    public abstract void Delete(long id);
}
