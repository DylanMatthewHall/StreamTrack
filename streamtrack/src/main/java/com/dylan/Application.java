package com.dylan;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application
{

    private List<StreamingService> services;
    private Scanner scanner;

    // Constructor
    public Application()
    {
        this.services = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // Core Methods
    public static void main(String[] args)
    {
        welcomeBanner();
        Application app = new Application();
        app.run();

        // Exit Message
        System.out.println("Thankyou for using my application, Goodbye.");
    }

    private void run()
    {
        boolean running = true;
        while (running)
        {
            showMenu();

            int choice = handleInput();
            switch (choice)
            {
                case 1 -> addService();
                case 2 -> removeService();
                case 3 -> logSession();
                case 4 -> unlogSession();
                case 5 -> generateReport();
                case 6 -> listServices();
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
        System.out.print("Please select an option by its number: ");
    }

    private int handleInput()
    {
        int userInput = 0;
        try
        {
            userInput = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e)
        {
            System.out.println("Please enter a valid input option. (e.g. 1)");
            userInput = 0;
        }
        return userInput;
    }

    // user actions
    private void addService()
    {
        String serviceName = "";
        while (true)
        {
            System.out.print("\nType the Name of the Service Please: ");
            serviceName = scanner.nextLine().trim();

            if (serviceName.isEmpty())
            {
                System.out.println("Service Name cannot be empty.");
            } else if (serviceExists(serviceName))
            {
                System.out.println("Service Name already exists. Please use a different name.");
            } else
            {
                break;
            }
        }

        double serviceRate = 0.0;
        boolean valid = false;

        while (!valid)
        {
            System.out.print("\nType the monthly rate of the service: ");

            try
            {
                serviceRate = Double.parseDouble(scanner.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a valid number (e.g., 12.99)");
            }
        }
        services.add(new StreamingService(serviceName, serviceRate));
        System.out.println("\n" + serviceName + " at " + serviceRate + " added successfully!");
    }

    private void removeService()
    {
        if (services.isEmpty())
        {
            System.out.println("\nNo services added");
            System.out.println("Select option 'Add Service' to add a service");
            return;
        }
        listServices();

        boolean valid = false;
        int userChoice = -1;

        while (!valid)
        {
            System.out.println(
                    "\n[WARNING] Removing a service will permanently delete its logs as well. This cannot be undone.");
            System.out.print("\nEnter the number of the service you want to remove or 0 to cancel: ");

            try
            {
                userChoice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a whole number.");
            }

            if (userChoice >= 1 && userChoice <= services.size())
            {
                valid = true;
            } else if (userChoice == 0)
            {
                System.out.println("Service removal canceled.");
                return;
            }

            // confirmation
            StreamingService service = services.get(userChoice - 1);
            String serviceName = service.getName();
            System.out.println("confirm you want to remove " + serviceName
                    + " by typing the name of the service. (Case Sensitive)");
            String userConfirmation = scanner.nextLine().trim();
            if (!serviceName.equals(userConfirmation))
            {
                valid = false;
                System.out.println("\nNames did not match.");
            }
        }

        services.remove(userChoice - 1);
    }

    private void logSession()
    {
        // List Services
        listServices();
        if (services.isEmpty())
        {
            return;
        }

        boolean valid = false;
        int userChoice = -1;
        while (!valid)
        {
            System.out.print("\nSelect a serice by its number: ");

            try
            {
                userChoice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e)
            {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }

            if (userChoice >= 1 && userChoice <= services.size())
            {
                valid = true;
            }
        }

        // Select Service
        StreamingService selectedService = services.get(userChoice - 1);

        // Get Date
        LocalDate date = null;
        valid = false;
        while (!valid)
        {
            System.out.print("\nEnter the date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();
            try
            {
                date = LocalDate.parse(dateInput);
                valid = true;
            } catch (DateTimeParseException e)
            {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }

        // How many minutes
        int minutes = 0;
        valid = false;
        while (!valid)
        {
            System.out.print("\nEnter how many Minutes you watched: ");
            try
            {
                minutes = Integer.parseInt(scanner.nextLine());
                valid = true;
            } catch (NumberFormatException e)
            {
                System.out.println("Enter a whole number please.");
            }
        }

        // Create the session with date and minutes
        ViewingSession session = new ViewingSession(date, minutes);
        selectedService.addSession(session);

        System.out.println("\nLogged " + minutes + " minutes on " + date + " for " + selectedService.getName());
    }

    private void unlogSession()
    {
        // TODO: write a way to remove logged session

    }

    private void generateReport()
    {
        if (services.isEmpty())
        {
            System.out.println("\nNo services available. Please add a service first");
            return;
        }

        Report.generateSummary(services);
    }

    private void listServices()
    {
        if (services.isEmpty())
        {
            System.out.println("\nNo services added");
            System.out.println("Select option 'Add Service' to add a service");
            return;
        }

        System.out.println("\nListed Services:");
        int num = 1;
        for (StreamingService service : services)
        {
            System.out.println(num + ". " + service.getName());
            num++;
        }
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

    private boolean serviceExists(String serviceName)
    {
        for (StreamingService service : services)
        {
            if (service.getName().toLowerCase().equals(serviceName.toLowerCase()))
            {
                return true;
            }
        }
        return false;
    }
}