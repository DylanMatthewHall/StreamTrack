package com.dylan.dao;

import com.dylan.model.ViewingSession;
import java.util.List;

public interface ViewingSessionDAO 
{

    // Create
    void addSession(ViewingSession session);

    // Delete
    void deleteSession(int id);

    // Read
    ViewingSession getSessionById(int id);

    List<ViewingSession> getSessionsByService(int serviceId);
}
