package com.and;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        String DB_Server = "win-f4urcheiq8i";
        String USER = "postgres";
        String PASS = "9210603060";
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


