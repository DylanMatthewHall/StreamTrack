package com.dylan.model;

import java.util.ArrayList;
import java.util.List;

public class StreamingService implements Comparable<StreamingService>
{
    private int id;
    private String name;
    private double monthlyCost;
    private List<ViewingSession> sessions;

    // Constructors
    StreamingService(String name, double monthlyCost)
    {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.sessions = new ArrayList<>();
    }

    StreamingService(int id, String name, double monthlyCost)
    {
        this.id = id;
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.sessions = new ArrayList<>();
    }

    // Getters / Setters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getMonthlyCost()
    {
        return monthlyCost;
    }

    public void setMonthlyCost(double monthlyCost)
    {
        this.monthlyCost = monthlyCost;
    }

    public List<ViewingSession> getSessions()
    {
        return this.sessions;
    }

    // Methods
    public void addSessions(ViewingSession session)
    {
        sessions.add(session);
    }

    public void removeSessions(ViewingSession session)
    {
        sessions.remove(session);
    }

    public int getTotalMinutes()
    {
        int totalMinutes = 0;
        for(ViewingSession session : sessions)
        {
            totalMinutes += session.getDurationMinutes();
        }
        return totalMinutes;
    }

    public double getCostPerHour()
    {
        //totalMinutes / 60 = totalHours
        //totalHours / monthlyCost = cost per hour
        double totalHours = getTotalMinutes() / 60.0;
        return totalHours / monthlyCost;
    }

    @Override
    public String toString()
    {
        return "StreamingService [id=" + id + ", name=" + name + ", monthlyCost=" + monthlyCost + "]";
    }

    @Override
    public int compareTo(StreamingService other)
    {
        return Double.compare(this.getCostPerHour(), other.getCostPerHour());
    }
}