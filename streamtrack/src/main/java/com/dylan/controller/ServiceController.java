package com.dylan.controller;

import com.dylan.dao.StreamingServiceDAO;
import com.dylan.dao.ViewingSessionDAO;
import com.dylan.model.StreamingService;
import com.dylan.model.ViewingSession;

import java.time.LocalDate;
import java.util.List;

public class ServiceController 
{
    private StreamingServiceDAO serviceDAO;
    private ViewingSessionDAO sessionDAO;

    // Constructor injection
    public ServiceController(StreamingServiceDAO serviceDAO, ViewingSessionDAO sessionDAO) 
    {
        this.serviceDAO = serviceDAO;
        this.sessionDAO = sessionDAO;
    }

    // Service operations
    public void addService(String name, double cost) 
    {
        // TODO: create StreamingService, call DAO
    }

    public void removeService(int id) 
    {
        // TODO: call DAO to delete service
    }

    public List<StreamingService> listServices() 
    {
        // TODO: call DAO to return all services
        return null;
    }

    // Session operations
    public void logSession(int serviceId, LocalDate date, int minutes) 
    {
        // TODO: create ViewingSession, call DAO
    }

    public void removeSession(int sessionId) 
    {
        // TODO: call DAO to delete session
    }

    public List<ViewingSession> getSessionsForService(int serviceId) 
    {
        // TODO: call DAO to return all sessions for service
        return null;
    }

    // Reports
    public void generateReport() 
    {
        // TODO: compute $/hr and total usage across services
    }
}