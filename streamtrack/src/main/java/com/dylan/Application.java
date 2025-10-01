package com.dylan;

import java.util.List;
import java.util.Scanner;
import java.security.Provider.Service;
import java.util.ArrayList;

public class Application {
    private List<StreamingService> services;
    private Scanner scanner;

    // Constructor
    public Application() {
        this.services = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Core Methods
    public static void main(String[] args) {
        Application app = new Application();
        app.run();

        // Exit Message
        System.out.println("Thankyou for using my application, Goodbye.");
    }

    public void run() {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = handleInput();

            switch (choice) {
                case 1 -> addService();
                case 2 -> logSession();
                case 3 -> generateReport();
                case 4 -> listServices();
                case 5 -> running = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n1. Add Service");
        System.out.println("2. Log Service Session");
        System.out.println("3. Generate Report");
        System.out.println("4. List Services");
        System.out.println("5. Exit");
        System.out.print("Please Select An Option Via Number: ");
    }

    private int handleInput() {
        int userInput = scanner.nextInt(); // get user input
        scanner.nextLine(); // consume leftover newline
        return userInput;
    }

    // user actions
    private void addService() {
        System.out.println("Type the Name of the Service Please");
        String serviceName = scanner.nextLine();

        System.out.println("Type the monthly rate for the service");
        double serviceRate = scanner.nextDouble();
        scanner.nextLine();

        services.add(new StreamingService(serviceName, serviceRate));
        System.out.println(serviceName + " added successfully!");
    }

    private void logSession() {

    }

    private void generateReport() {

    }

    private void listServices() {
        if (services.isEmpty()) {
            System.out.println("No services added");
            System.out.println("Select option 'Add Service' to add a service");
            return;
        }

        for (StreamingService Service : services) {
            System.out.println(Service.getName());
        }
    }
}