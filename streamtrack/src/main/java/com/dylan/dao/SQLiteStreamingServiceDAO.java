package com.dylan.dao;

import com.dylan.entity.StreamingService;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SQLiteStreamingServiceDAO implements StreamingServiceDAO
{
    private Connection conn;

    public SQLiteStreamingServiceDAO(Connection conn)
    {
        this.conn = conn;
    }

    @Override
    public void delete(StreamingService service)
    {
        String sql = "DELETE FROM services WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, service.getId());
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0)
            {
                System.out.println("Deleted service with ID " + service.getId());
            } else
            {
                System.out.println("No service found with ID " + service.getId());
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public StreamingService findById(int id)
    {
        String sql = "SELECT * FROM services WHERE id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next())
            {
                return new StreamingService(rs.getInt("id"), rs.getString("name"), rs.getDouble("cost"));
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StreamingService> getAll()
    {
        List<StreamingService> services = new ArrayList<>();
        String sql = "SELECT * FROM services";

        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next())
            {
                StreamingService s = new StreamingService(rs.getInt("id"), rs.getString("name"), rs.getDouble("cost"));
                services.add(s);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return services;
    }

    @Override
    public void insert(StreamingService service)
    {
        String sql = "INSERT INTO services (name, cost) VALUES (?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            // Set values for placeholders
            stmt.setString(1, service.getName());
            stmt.setDouble(2, service.getCost());

            // Execute the insert
            stmt.executeUpdate();

            // Optionally get the generated id back from the DB
            try (ResultSet keys = stmt.getGeneratedKeys())
            {
                if (keys.next())
                {
                    service.setId(keys.getInt(1)); // assign auto-generated id back into object
                }
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}