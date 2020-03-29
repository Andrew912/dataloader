package com.and;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataOutputClass {

    Connection c;
    Statement s;
    String sql;

    public DataOutputClass(Connection connection) throws SQLException {
        c = connection;
        s = c.createStatement();
    }

    public void out(String sqlSrting) throws SQLException {
        sql = sqlSrting;
        s.executeUpdate(sql);
    }

    public void commit() throws SQLException {
        c.commit();
    }

}
