package com.dylan.entity;

import java.time.LocalDate;

public class ViewingSession
{
    private int id;
    private int serviceId;
    private int minutes;
    private LocalDate date;

    // Constructor
    public ViewingSession(int id, int serviceId, int minutes, LocalDate date)
    {
        this.id = id;
        this.serviceId = serviceId;
        this.minutes = minutes;
        this.date = date;
    }

    public ViewingSession(int serviceId, int minutes, LocalDate date)
    {
        this.serviceId = serviceId;
        this.minutes = minutes;
        this.date = date;
    }

    // Getters/Setters
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    public int getMinutes()
    {
        return minutes;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return String.format("Session %d | Service ID: %d | %d minutes | Date: %s", id, serviceId, minutes,
                date.toString());
    }
}