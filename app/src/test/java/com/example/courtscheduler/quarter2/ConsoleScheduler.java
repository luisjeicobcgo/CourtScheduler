package com.example.courtscheduler.quarter2;



import java.util.Scanner;
public class ConsoleScheduler {

    public static void main(String[] args) {
        LoginComponent();
    }

    public static void LoginComponent() {

        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter username: ");
        String username = scanner.nextLine();


        System.out.print("Enter password: ");
        String password = scanner.nextLine();


        System.out.println("\n--- Login Details Captured ---");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        scanner.close();
    }
}