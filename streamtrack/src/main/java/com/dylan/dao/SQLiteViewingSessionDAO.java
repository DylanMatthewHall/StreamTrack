package com.dylan.dao;

import com.dylan.entity.ViewingSession;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.*;

public class SQLiteViewingSessionDAO implements ViewingSessionDAO
{
    private Connection conn;

    public SQLiteViewingSessionDAO(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public void delete(ViewingSession session)
    {
        String sql = "DELETE FROM sessions WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, session.getId());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0)
            {
                System.out.println("Deleted session with ID " + session.getId());
            } else
            {
                System.out.println("No session found with ID " + session.getId());
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<ViewingSession> getAll()
    {
        List<ViewingSession> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))
        {

            while (rs.next())
            {
                ViewingSession session = new ViewingSession(rs.getInt("id"), rs.getInt("service_id"),
                        rs.getInt("minutes_watched"), LocalDate.parse(rs.getString("date")) // stored as TEXT
                                                                                            // "YYYY-MM-DD"
                );
                sessions.add(session);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public List<ViewingSession> getByServiceId(int serviceId)
    {
        List<ViewingSession> sessions = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE service_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, serviceId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                ViewingSession session = new ViewingSession(rs.getInt("id"), rs.getInt("service_id"),
                        rs.getInt("minutes_watched"), LocalDate.parse(rs.getString("date")));
                sessions.add(session);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return sessions;
    }

    @Override
    public ViewingSession findById(int id)
    {
        String sql = "SELECT * FROM sessions WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return new ViewingSession(rs.getInt("id"), rs.getInt("service_id"), rs.getInt("minutes_watched"),
                        LocalDate.parse(rs.getString("date")) // since column is TEXT "YYYY-MM-DD"
                );
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(ViewingSession session)
    {
        String sql = "INSERT INTO sessions (service_id, minutes_watched, date) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            // Bind values
            stmt.setInt(1, session.getServiceId());
            stmt.setInt(2, session.getMinutes());

            if (session.getDate() != null)
            {
                stmt.setString(3, session.getDate().toString()); // store as TEXT "YYYY-MM-DD"
            } else
            {
                stmt.setNull(3, java.sql.Types.VARCHAR);
            }

            stmt.executeUpdate();

            // Retrieve generated ID
            try (ResultSet rs = stmt.getGeneratedKeys())
            {
                if (rs.next())
                {
                    session.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}