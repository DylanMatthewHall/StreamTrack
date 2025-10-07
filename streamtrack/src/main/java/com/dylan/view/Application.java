package com.dylan.view;

import com.dylan.controller.ServiceController;
import com.dylan.dao.SQLiteStreamingServiceDAO;
import com.dylan.dao.SQLiteViewingSessionDAO;

import java.util.Scanner;

public class Application 
{
    private Scanner scanner;
    private ServiceController controller;

    public Application() 
    {
        this.scanner = new Scanner(System.in);

        // Wire dependencies here
        this.controller = new ServiceController
        (
            new SQLiteStreamingServiceDAO(),
            new SQLiteViewingSessionDAO()
        );
    }

    public static void main(String[] args) 
    {
        Application app = new Application();
        app.run();
    }

    public void run() 
    {
        boolean running = true;
        while (running) {
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
        // TODO: collect input, call controller.addService()
    }

    private void removeServiceUI() 
    {
        // TODO: collect input, call controller.removeService()
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
}
