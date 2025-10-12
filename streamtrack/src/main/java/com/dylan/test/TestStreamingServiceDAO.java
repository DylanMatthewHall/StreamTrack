package com.dylan.test;

import com.dylan.dao.*;
import com.dylan.entity.*;
import com.dylan.util.DatabaseInitializer;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class TestStreamingServiceDAO
{
    public static void main(String[] args)
    {
        DatabaseInitializer.initializeDatabase();

        try (Connection conn = DatabaseInitializer.getConnection())
        {
            // --- Test StreamingServiceDAO ---
            StreamingServiceDAO serviceDAO = new SQLiteStreamingServiceDAO(conn);
            ViewingSessionDAO sessionDAO = new SQLiteViewingSessionDAO(conn);

            // Insert services
            StreamingService netflix = new StreamingService("Netflix", 15.99);
            StreamingService hulu = new StreamingService("Hulu", 7.99);
            serviceDAO.insert(netflix);
            serviceDAO.insert(hulu);

            // Get all services
            System.out.println("All services:");
            for (StreamingService s : serviceDAO.getAll())
            {
                System.out.println(s);
            }

            // Find by ID
            System.out.println("\nFind ID " + netflix.getId() + ": " + serviceDAO.findById(netflix.getId()));

            // --- Test ViewingSessionDAO ---
            ViewingSession session1 = new ViewingSession(0, netflix.getId(), 120, LocalDate.of(2025, 10, 11));
            ViewingSession session2 = new ViewingSession(0, netflix.getId(), 90, LocalDate.of(2025, 10, 12));
            ViewingSession session3 = new ViewingSession(0, hulu.getId(), 60, LocalDate.of(2025, 10, 12));

            sessionDAO.insert(session1);
            sessionDAO.insert(session2);
            sessionDAO.insert(session3);

            // Get all sessions
            System.out.println("\nAll sessions:");
            for (ViewingSession v : sessionDAO.getAll())
            {
                System.out.println(v);
            }

            // Get sessions by serviceId (Netflix)
            System.out.println("\nSessions for Netflix:");
            for (ViewingSession v : sessionDAO.getByServiceId(netflix.getId()))
            {
                System.out.println(v);
            }

            // Delete a session
            sessionDAO.delete(session1);
            System.out.println("\nAfter deleting session1:");
            for (ViewingSession v : sessionDAO.getAll())
            {
                System.out.println(v);
            }

            // --- Clean up: delete services ---
            serviceDAO.delete(netflix);
            serviceDAO.delete(hulu);
            System.out.println("\nAfter deleting services:");
            for (StreamingService s : serviceDAO.getAll())
            {
                System.out.println(s);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}