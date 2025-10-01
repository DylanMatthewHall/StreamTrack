package com.dylan;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        // List Services
        listServices();
        if (services.isEmpty()) {
            return;
        }

        // Select Service
        System.out.print("Select a service by number: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice >= services.size() + 1) {
            System.out.println("Invalid choice.");
            return;
        }

        StreamingService selectedService = services.get(choice - 1);

        // Get Date
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String dateInput = scanner.nextLine();

        LocalDate date;
        try {
            date = LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        // How many minutes
        System.out.print("Enter how many minutes you watched: ");
        int minutes = scanner.nextInt();
        scanner.nextLine();

        // Create the session with date and minutes
        ViewingSession session = new ViewingSession(date, minutes);
        selectedService.addSession(session);

        System.out.println("Logged " + minutes + " minutes on " + date + " for " + selectedService.getName());
    }

    private void generateReport() {
        if (services.isEmpty()) {
            System.out.println("No services available. Please add a service first");
            return;
        }

        Report.generateSummary(services);
    }

    private void listServices() {
        if (services.isEmpty()) {
            System.out.println("No services added");
            System.out.println("Select option 'Add Service' to add a service");
            return;
        }
        int num = 1;
        for (StreamingService service : services) {
            System.out.println(num + ". " + service.getName());
            num++;
        }
    }
}