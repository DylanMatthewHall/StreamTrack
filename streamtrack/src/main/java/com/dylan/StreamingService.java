package com.dylan;

import java.util.List;

public class StreamingService {
    private String name;
    private double monthlyCost;
    private List<ViewingSession> sessions;

    public StreamingService(String name, double monthlyCost) {
        this.name = name;
        this.monthlyCost = monthlyCost;
    }

    public String getName() {
        return this.name;
    }
}
