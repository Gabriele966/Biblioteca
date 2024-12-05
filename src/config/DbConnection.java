package config;

import java.sql.*;

public class DbConnection {
    public static Connection openConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/esercizioLibri",
                "postgres",
                "1234");
    }
}
