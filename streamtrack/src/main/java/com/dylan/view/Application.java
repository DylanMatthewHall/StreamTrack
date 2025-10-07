package com.dylan.view;

import com.dylan.controller.ServiceController;
import com.dylan.dao.SQLiteStreamingServiceDAO;
import com.dylan.dao.SQLiteViewingSessionDAO;
import com.dylan.util.DatabaseInitiliazer;

import java.util.Scanner;

public class Application
{
    private Scanner scanner;
    private ServiceController controller;

    public Application()
    {
        this.scanner = new Scanner(System.in);
        DatabaseInitiliazer.intialize("jdbc:sqlite:streamtrack.db");
        this.controller = new ServiceController(new SQLiteStreamingServiceDAO(), new SQLiteViewingSessionDAO());
    }

    public static void main(String[] args)
    {
        Application app = new Application();
        app.run();
    }

    public void run()
    {
        boolean running = true;
        welcomeBanner();
        while (running)
        {
            showMenu();
            int choice = handleInput();

            switch (choice)
            {
                case 1 -> addServiceUI();
                case 2 -> removeServiceUI();
                case 3 -> logSessionUI();
                case 4 -> unlogSessionUI();
                case 5 -> generateReportUI();
                case 6 -> listServicesUI();
                case 7 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void showMenu()
    {
        System.out.println("\n=== StreamTrack Menu ===");
        System.out.println("1. Add Service");
        System.out.println("2. Remove Service");
        System.out.println("3. Log Service Session");
        System.out.println("4. Unlog a Session");
        System.out.println("5. Generate Report");
        System.out.println("6. List Services");
        System.out.println("7. Exit");
        System.out.print("Please select an option: ");
    }

    private int handleInput()
    {
        try
        {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e)
        {
            return 0; // invalid input
        }
    }

    // --- UI Wrappers for controller calls ---
    private void addServiceUI()
    {
        String serviceName = promptForServiceName();
        double serviceCost = promptForServiceCost(serviceName);
        controller.addService(serviceName, serviceCost);
    }

    private String promptForServiceName()
    {
        String serviceName = null;
        do
        {
            System.out.println("Enter the name of the new service.");
            serviceName = scanner.nextLine();
        } while (!isValidServiceName(serviceName));
        return serviceName;
    }

    private boolean isValidServiceName(String serviceName)
    {
        boolean isValid = true;
        if (serviceName == null || serviceName.trim().isEmpty())
        {
            System.out.println("Service Name cannot be empty.");
            isValid = false;
        } else if (controller.serviceExists(serviceName))
        {
            System.out.println("Service Name cannot be a duplicate.");
            isValid = false;
        }
        return isValid;
    }

    private double promptForServiceCost(String serviceName)
    {
        String serviceCostStr = null;
        do
        {
            System.out.println("Enter the monthly cost of " + serviceName + ".");
            serviceName = scanner.nextLine();
        } while (!isValidServiceCost(serviceCostStr));
        double serviceCost = Double.parseDouble(serviceCostStr);
        return serviceCost;
    }

    private boolean isValidServiceCost(String serviceCost)
    {
        boolean isValid = true;
        try
        {
            double cost = Double.parseDouble(serviceCost);
            if (cost < 0)
            {
                System.out.println("Service Cost cannot be negative");
                isValid = false;
            }
        } catch (NumberFormatException e)
        {
            System.out.println("Service Cost must be a valid number.");
            isValid = false;
        }
        return isValid;
    }

    private void removeServiceUI()
    {
        // TODO: collect input, call controller.removeService()
        // list services

        // prompt user for service id
        // validate

        // remove service
    }

    private void logSessionUI()
    {
        // TODO: collect input, call controller.logSession()
    }

    private void unlogSessionUI()
    {
        // TODO: collect input, call controller.removeSession()
    }

    private void listServicesUI()
    {
        // TODO: call controller.listServices() and print
    }

    private void generateReportUI()
    {
        // TODO: call controller.generateReport()
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
}
