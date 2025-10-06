package com.dylan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHelper
{
    private static final String URL = "jdbc:sqlite:streamtrack.db";

    public static void initializeDatabase()
    {
        try
        {
            Connection conn = DriverManager.getConnection(URL);
            Statement stmt = conn.createStatement();
            // Services table
            String createServices = """
                        CREATE TABLE IF NOT EXISTS services (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT UNIQUE NOT NULL,
                            monthlyCost REAL NOT NULL
                        );
                    """;
            stmt.execute(createServices);
            // Sessions table
            String createSessions = """
                        CREATE TABLE IF NOT EXISTS sessions (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            serviceId INTEGER NOT NULL,
                            date TEXT NOT NULL,
                            durationMinutes INTEGER NOT NULL,
                            FOREIGN KEY (serviceId) REFERENCES services(id) ON DELETE CASCADE
                        );
                    """;
            stmt.execute(createSessions);

        } catch (SQLException e)
        {
            System.out.println("Error initializing DB: " + e.getMessage());
        }
    }
}