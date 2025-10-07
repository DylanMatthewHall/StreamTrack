package com.dylan.model;

import java.time.LocalDate;

public class ViewingSession
{
    private int id;
    private LocalDate date;
    private int durationMinutes;
    private int serviceId;

    // Constructors
    ViewingSession(LocalDate date, int durationMinutes, int serviceId)
    {
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.serviceId = serviceId;
    }

    ViewingSession(int id, LocalDate date, int durationMinutes, int serviceId)
    {
        this.id = id;
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.serviceId = serviceId;
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

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public int getDurationMinutes()
    {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes)
    {
        this.durationMinutes = durationMinutes;
    }

    public int getServiceId()
    {
        return serviceId;
    }

    public void setServiceId(int serviceId)
    {
        this.serviceId = serviceId;
    }

    // Methods
    @Override
    public String toString()
    {
        return "ViewingSession [id=" + id + ", date=" + date + ", durationMinutes" + durationMinutes + ", serviceId=" + serviceId + "]"; 
    }
}