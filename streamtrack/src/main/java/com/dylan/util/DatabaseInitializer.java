package com.dylan.util;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer
{
    private static final String DB_URL = "jdbc:sqlite:streamtrack.db";

    public static Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e)
        {
            System.out.println("Error connecting to database: " + e.getMessage());
            return null;
        }
    }

    public static void initializeDatabase()
    {
        try (Connection conn = DriverManager.getConnection(DB_URL); Statement stmt = conn.createStatement())
        {
            // StreamingService table
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS services (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name TEXT NOT NULL,
                        cost REAL NOT NULL
                        )
                    """);
            // ViewingSession table
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS sessions (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        service_id INTEGER NOT NULL,
                        minutes_watched INTEGER NOT NULL,
                        date TEXT NOT NULL,
                        FOREIGN KEY(service_id) REFERENCES StreamingService(id)
                        )
                    """);

            System.out.println("Database intitialized successfully.");
        } catch (SQLException e)
        {
            System.out.println("Error intializing database: " + e.getMessage());
        }
    }
}