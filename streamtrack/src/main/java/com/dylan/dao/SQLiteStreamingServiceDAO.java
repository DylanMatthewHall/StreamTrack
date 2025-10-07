package com.dylan.dao;

import com.dylan.model.StreamingService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteStreamingServiceDAO implements StreamingServiceDAO
{
    private static final String URL = "jdbc:sqlite:streamtrack.db";

    @Override
    public void addService(StreamingService service)
    {
        // TODO: implement INSERT SQL
    }

    @Override
    public void updateService(StreamingService service)
    {
        // TODO: implement UPDATE SQL
    }

    @Override
    public void deleteService(int id)
    {
        // TODO: implement DELETE SQL
    }

    @Override
    public StreamingService getServiceById(int id)
    {
        // TODO: implement SELECT by id SQL
        return null;
    }

    @Override
    public StreamingService getServiceByName(String name)
    {
        // TODO: implement SELECT by name SQL
        return null;
    }

    @Override
    public List<StreamingService> getAllServices()
    {
        // TODO: implement SELECT all SQL
        return new ArrayList<>();
    }
}