package com.and;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        String DB_Server = "win-f4urcheiq8i";           // Сервер БД
        String USER = "postgres";                       // User (postgres или cuba)
        String PASS = "9210603060";                     // пароль
        String DB_Name = "hh01";                        // имя БД
        String DB_Num = "4";                            // версия набора данных

        new MakeData_Postgres(
                DB_Name,
                DB_Server,
                USER,
                PASS)
                .run("P:\\honors\\SQL\\nagrady_Postgres_" + DB_Num + "_данные.sql");
        new MakeData_Postgres(
                DB_Name,
                DB_Server,
                USER,
                PASS)
                .run("P:\\honors\\SQL\\nagrady_Postgres_" + DB_Num + "_модификация.sql");

    }
}


