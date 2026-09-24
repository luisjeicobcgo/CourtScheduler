package com.example.courtscheduler.quarter2.practicalexam;
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

        boolean cancelorresched = true;
        boolean validInput;

        do {
            System.out.print("Cancellation/Rescheduling? (true/false): ");

            if (!scanner.hasNextBoolean()) {
                System.out.println("Invalid input. Please enter true or false.");
                scanner.next();
                validInput = false;
            } else {
                cancelorresched = scanner.nextBoolean();
                validInput = true;
            }

        } while (!validInput);


        boolean reservation = true;

        do {
            System.out.print("Reservation? (true/false): ");

            if (!scanner.hasNextBoolean()) {
                System.out.println("Invalid input. Please enter true or false.");
                scanner.next();
                validInput = false;
            } else {
                reservation = scanner.nextBoolean();
                validInput = true;
            }

        } while (!validInput);

        System.out.print("Enter Schedule ID: ");
        int scheduleId = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Purpose of using the court: ");
        String purpose = scanner.nextLine();

        System.out.println("\n--- Schedule Details Captured ---");
        System.out.println("User ID: " + userId);
        System.out.println("Start Time: " + startTime);
        System.out.println("End Time: " + endTime);
        System.out.println("Date: " + date);
        System.out.println("Cancellation/Rescheduling: " + cancelorresched);
        System.out.println("Reservation: " + reservation);
        System.out.println("Schedule ID: " + scheduleId);
        System.out.println("Purpose of using the court: " + purpose);
        scanner.close();
    }
}