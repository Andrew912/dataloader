package com.and;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeData_MSSQL {

    String USER;
    String PASS;
    String FILE_NAME;
    String DB_Server;
    String DB_Name;
    String DB_URL;
    String ConnectionString;

    public MakeData_MSSQL(
            String dbName,
            String DB_Server,
            String USER,
            String PASS) {
        this.DB_URL = "jdbc:postgresql://" + DB_Server + ":5432/" + dbName;
        this.DB_Server = DB_Server;
        this.USER = USER;
        this.PASS = PASS;
        String DB_URL =
                "jdbc:sqlserver://" + DB_Server + ":1433;" +
                        "database=" + DB_Name + ";" +
                        "user=" + USER + ";" +
                        "password=" + PASS + ";" +
                        "encrypt=false;" +
                        "trustServerCertificate=false;" +
                        "loginTimeout=30;";
        ConnectionString = String.format(DB_URL, DB_Server, DB_Name, USER, PASS);
    }

    public void run(String readFileName) {

        FILE_NAME = readFileName;

        System.out.println("Testing connection to SQL JDBC");
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(ConnectionString);

            DataOutputClass d = new DataOutputClass(connection);
            Files.lines(Paths.get(FILE_NAME), StandardCharsets.UTF_8).forEach((p) -> {
                try {
                    d.out(p);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            /*d.commit();*/

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }


    }

}
