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
        String promptMessage = "Select a Session to remove.";
        boolean allowCancel = true;
        ViewingSession session = selectSession(promptMessage, allowCancel, scanner);

        this.sessions.remove(session);
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

    private ViewingSession selectSession(String promptMessage, boolean allowCancel, Scanner scanner)
    {
        if (this.sessions.isEmpty())
        {
            System.out.println("\nNo sessions added");
            System.out.println("Select option 'Log Session' to add a service");
            return null;
        }

        listSessions();

        ViewingSession session = null;

        while (session == null)
        {
            System.out.print("\n" + promptMessage + (allowCancel ? " (0 to cancel): " : ": "));

            String input = scanner.nextLine().trim();
            try
            {
                int choice = Integer.parseInt(input);

                if (allowCancel && choice == 0)
                {
                    System.out.println("Action canceled.");
                    return null;
                }

                session = this.sessions.get(choice - 1);
            } catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a whole number.");
            } catch (IndexOutOfBoundsException e)
            {
                System.out.println("Invalid choice. Please pick a number between 1 and " + this.sessions.size() + ".");
            }
        }
        return session;
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