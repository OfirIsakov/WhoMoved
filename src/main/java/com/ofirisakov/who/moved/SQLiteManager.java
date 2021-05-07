package com.ofirisakov.who.moved;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteManager {
    static Connection connection = null;

    public static void connectToSQLiteDatabase(String fileName) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(fileName);
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

	public static void initTable() {
        // SQLite connection string
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS block_enteties (\n"
                + "	player_name TEXT NOT NULL,\n"
                + "	block_type TEXT NOT NULL,\n"
                + "	x INTEGER NOT NULL,\n"
                + "	y INTEGER NOT NULL,\n"
                + "	z INTEGER NOT NULL,\n"
                + "	time BIGINT NOT NULL\n"
                + ");";
        
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertLog(String player_name, String block_type, int x, int y, int z) {
        String sql = "INSERT INTO block_enteties(player_name, block_type, x, y, z, time) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, player_name);
            pstmt.setString(2, block_type);
            pstmt.setInt(3, x);
            pstmt.setInt(4, y);
            pstmt.setInt(5, z);
            pstmt.setLong(6, System.currentTimeMillis() / 1000L);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
