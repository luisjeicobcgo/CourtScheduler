package com.example.courtscheduler.quarter2;
import java.util.Scanner;

public class ConsoleScheduler {

    public static void main(String[] args) {
        SchedulerComponent();
    }
    public static void SchedulerComponent() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter start time: ");
        String startTime = scanner.nextLine();

        System.out.print("Enter end time: ");
        String endTime = scanner.nextLine();

        System.out.print("Enter date: ");
        String date = scanner.nextLine();

        System.out.println("\n--- Schedule Details Captured ---");
        System.out.println("User ID: " + userId);
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Date: " + date);

        scanner.close();
    }
}