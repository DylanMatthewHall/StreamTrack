package com.dylan;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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

    public void removeSession(Scanner scanner)
    {
        if (sessions.isEmpty())
        {
            System.out.println("No sessions logged for " + name);
            return;
        }

        listSessions();

        System.out.print("Select a session to remove: ");
        try
        {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            ViewingSession removed = sessions.remove(choice - 1);
            System.out.println("Removed: " + removed.getDate() + " - " + removed.getDurationMinutes() + " minutes");
        } catch (Exception e)
        {
            System.out.println("Invalid choice. No session removed.");
        }
    }

    private void listSessions()
    {
        if (sessions.isEmpty())
        {
            System.out.println("No logged sessions.");
            return;
        }
        int num = 1;
        for (ViewingSession session : sessions)
        {
            System.out.println(num + ". " + session.toString());
            num++;
        }
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