package com.dylan;

import java.util.List;
import java.util.ArrayList;

public class StreamingService implements Comparable<StreamingService>
{
    private String name;
    private double monthlyCost;
    private List<ViewingSession> sessions;

    // Constructor
    public StreamingService(String name, double monthlyCost)
    {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.sessions = new ArrayList<>();
    }

    // Methods
    public String getName()
    {
        return this.name;
    }

    public double getTotalHours()
    {
        double hours = 0.0;
        for (ViewingSession session : sessions)
        {
            hours += session.getHours();
        }
        return hours;
    }

    public double getCostPerHour()
    {
        double hours = this.getTotalHours();
        if (hours == 0)
        {
            return 0.0;
        }
        return monthlyCost / hours;
    }

    public void addSession(ViewingSession session)
    {
        this.sessions.add(session);
    }

    public double getMonthlyCost()
    {
        return this.monthlyCost;
    }

    @Override
    public String toString()
    {
        return name + " ($" + monthlyCost + "/month, " + String.format("%.2f", getTotalHours()) + " hrs watched)";
    }

    @Override
    public int compareTo(StreamingService other)
    {
        return Double.compare(this.getCostPerHour(), other.getCostPerHour());
    }
}