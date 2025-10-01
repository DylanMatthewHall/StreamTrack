package com.dylan;

import java.time.LocalDate;

public class ViewingSession {
    private LocalDate date;
    private int durationMinutes;

    // constructor
    public ViewingSession(LocalDate date, int durationMinutes) {
        this.date = date;
        this.durationMinutes = durationMinutes;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public double getHours() {
        return this.durationMinutes / 60.0;
    }

    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    @Override
    public String toString() {
        return date + " - " + durationMinutes + " minutes (" + String.format("%.2f", getHours()) + " hrs)";
    }
}