package com.dylan.view;

import com.dylan.controller.ServiceController;
import com.dylan.dao.*;
import com.dylan.util.DatabaseInitializer;
import com.dylan.entity.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Application
{
    private final Scanner scanner;
    private final ServiceController controller;

    public Application(ServiceController controller)
    {
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    public void run()
    {
        boolean running = true;
        while (running)
        {
            System.out.println("\n--- StreamTrack Menu ---");
            System.out.println("1. Add Streaming Service");
            System.out.println("2. List Services");
            System.out.println("3. Remove Service");
            System.out.println("4. Add Viewing Session");
            System.out.println("5. List Sessions");
            System.out.println("6. Remove Session");
            System.out.println("7. Calculate Cost Per Hour");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice)
            {
                case 1 -> addServiceUI();
                case 2 -> listServicesUI();
                case 3 -> removeServiceUI();
                case 4 -> addSessionUI();
                case 5 -> listSessionsUI();
                case 6 -> removeSessionUI();
                case 7 -> costPerHourUI();
                case 8 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    // UI Methods
    private void addServiceUI()
    {
        System.out.print("Enter service name: ");
        String name = scanner.nextLine();
        System.out.print("Enter monthly cost: ");
        double cost = scanner.nextDouble();
        controller.addService(name, cost);
    }

    private void listServicesUI()
    {
        List<StreamingService> services = controller.getAllServices();
        services.forEach(System.out::println);
    }

    private void removeServiceUI()
    {
        listServicesUI();
        System.out.print("Enter service ID to remove: ");
        int id = scanner.nextInt();
        controller.removeService(id);
    }

    private void addSessionUI()
    {
        listServicesUI();
        System.out.print("Enter service ID: ");
        int serviceId = scanner.nextInt();
        System.out.print("Enter minutes watched: ");
        int minutes = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateStr = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateStr);

        controller.addSession(serviceId, minutes, date);
    }

    private void listSessionsUI()
    {
        List<ViewingSession> sessions = controller.getAllSessions();
        sessions.forEach(System.out::println);
    }

    private void removeSessionUI()
    {
        listSessionsUI();
        System.out.print("Enter session ID to remove: ");
        int id = scanner.nextInt();
        controller.removeSession(id);
    }

    private void costPerHourUI()
    {
        listServicesUI();
        System.out.print("Enter service ID: ");
        int id = scanner.nextInt();
        double cost = controller.calculateCostPerHour(id);
        System.out.printf("Cost per hour: $%.2f%n", cost);
    }

    private static void welcomeBanner()
    {
        String banner = """
                      _____ _                         _______             _
                     / ____| |                       |__   __|           | |
                    | (___ | |_ _ __ ___  __ _ _ __ ___ | |_ __ __ _  ___| | __
                     \\___ \\| __| '__/ _ \\/ _` | '_ ` _ \\| | '__/ _` |/ __| |/ /
                     ____) | |_| | |  __/ (_| | | | | | | | | | (_| | (__|   <
                    |_____/ \\__|_|  \\___|\\__,_|_| |_| |_|_|_|  \\__,_|\\___|_|\\_\\
                """;

        System.out.println(banner);
    }

    public static void main(String[] args)
    {
        welcomeBanner();
        DatabaseInitializer.initializeDatabase();
        try (Connection conn = DatabaseInitializer.getConnection())
        {
            StreamingServiceDAO serviceDAO = new SQLiteStreamingServiceDAO(conn);
            ViewingSessionDAO sessionDAO = new SQLiteViewingSessionDAO(conn);
            ServiceController controller = new ServiceController(serviceDAO, sessionDAO);

            Application app = new Application(controller);
            app.run();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}