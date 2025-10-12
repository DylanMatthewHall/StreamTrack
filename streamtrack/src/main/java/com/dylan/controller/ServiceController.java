package com.dylan.controller;

import com.dylan.dao.*;
import com.dylan.entity.*;

import java.time.LocalDate;
import java.util.List;

public class ServiceController
{
    private StreamingServiceDAO serviceDAO;
    private ViewingSessionDAO sessionDAO;

    // Controller
    public ServiceController(StreamingServiceDAO serviceDAO, ViewingSessionDAO sessionDAO)
    {
        this.serviceDAO = serviceDAO;
        this.sessionDAO = sessionDAO;
    }

    // Service Methods
    public void addService(String name, double cost)
    {
        StreamingService service = new StreamingService(name, cost);
        serviceDAO.insert(service);
    }

    public List<StreamingService> getAllServices()
    {
        return serviceDAO.getAll();
    }

    public StreamingService getServiceById(int id)
    {
        return serviceDAO.findById(id);
    }

    public void removeService(int id)
    {
        StreamingService service = serviceDAO.findById(id);
        serviceDAO.delete(service);
    }

    // Session Methods
    public void addSession(int serviceId, int minutes, LocalDate date)
    {
        ViewingSession session = new ViewingSession(serviceId, minutes, date);
        sessionDAO.insert(session);
    }

    public List<ViewingSession> getAllSessions()
    {
        return sessionDAO.getAll();
    }

    public List<ViewingSession> getSessionsForService(int serviceId)
    {
        return sessionDAO.getByServiceId(serviceId);
    }

    public void removeSession(int id)
    {
        ViewingSession session = sessionDAO.findById(id);
        sessionDAO.delete(session);
    }

    // --- Business logic (future) ---
    public double calculateCostPerHour(int serviceId)
    {
        // TODO: implement
        return 0.0;
    }
}