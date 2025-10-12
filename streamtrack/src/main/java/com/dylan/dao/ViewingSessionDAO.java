package com.dylan.dao;

import com.dylan.entity.ViewingSession;
import java.util.List;

public interface ViewingSessionDAO
{
    void insert(ViewingSession session);

    void delete(ViewingSession session);

    List<ViewingSession> getAll();

    List<ViewingSession> getByServiceId(int serviceId);

    ViewingSession findById(int id);
}