package com.and;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeData_Postgres {

    String DB_URL;
    String DB_Server;
    String USER;
    String PASS;

    String FILE_NAME;

    public MakeData_Postgres(
            String dbName,
            String DB_Server,
            String USER,
            String PASS) {
        this.DB_URL = "jdbc:postgresql://" + DB_Server + ":5432/" + dbName;
        this.DB_Server = DB_Server;
        this.USER = USER;
        this.PASS = PASS;
    }

    public void run(String readFileName) {

        FILE_NAME = readFileName;

        System.out.println("Testing connection to SQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("JDBC Driver successfully connected");
        Connection connection = null;

        System.out.println("Testing connection to SQL JDBC");
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

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
