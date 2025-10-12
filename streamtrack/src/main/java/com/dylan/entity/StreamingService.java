package com.dylan.entity;

public class StreamingService
{
    private int id;
    private String name;
    private double cost;

    // Constructor
    public StreamingService(String name, double cost)
    {
        this.name = name;
        this.cost = cost;
    }

    public StreamingService(int id, String name, double cost)
    {
        this.id = id;
        this.name = name;
        this.cost = cost;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        this.cost = cost;
    }

    @Override
    public String toString()
    {
        return String.format("%d: %s ($%.2f)", id, name, cost);
    }
}