package com.dylan.dao;

import com.dylan.model.ViewingSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteViewingSessionDAO implements ViewingSessionDAO
{
    private static final String URL = "jdbc:sqlite:streamtrack.db";

    @Override
    public void addSession(ViewingSession session)
    {
        // TODO: implement INSERT SQL

    }

    @Override
    public void deleteSession(int id)
    {
        // TODO: implement DELETE SQL
    }

    @Override
    public ViewingSession getSessionById(int id)
    {
        // TODO: implement SELECT by id SQL
        return null;
    }

    @Override
    public List<ViewingSession> getSessionsByService(int serviceId)
    {
        // TODO: implement SELECT by serviceId SQL
        return new ArrayList<>();
    }
}
