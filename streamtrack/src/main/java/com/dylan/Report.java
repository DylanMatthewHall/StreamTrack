package com.dylan;

import java.util.List;

public class Report {
    public static void generateSummary(List<StreamingService> services) {

        for (StreamingService service : services) {
            double totalHours = service.getTotalHours();
            double costPerHour = service.getCostPerHour();

            System.out.printf("%s%n", service.getName());
            System.out.printf("  Monthly Cost: $%.2f%n", service.getMonthlyCost());
            System.out.printf("  Total Hours: %.2f hrs%n", totalHours);
            System.out.printf("  Cost per Hour: %s%n",
                    totalHours == 0 ? "N/A" : String.format("$%.2f/hr", costPerHour));
            System.out.println();
        }
    }
}
