package com.example.courtscheduler.quarter2.practicalexam;

import java.util.Scanner;

public class MainMenu {
    public void Menu(Scanner menuinput){
        boolean choosing = true;

        do{
            System.out.println("=====MAIN MENU=====");
            System.out.println("1. Scheduler");
            System.out.println("2. History");
            System.out.println("3. Exit");
            System.out.print("Please choose an option (1, 2, 3): ");
            String choice = menuinput.nextLine();
            System.out.println(choice);
            if (choice.equals("1")) {
                Scheduler scheduler = new Scheduler();
                scheduler.Scheduling(menuinput);
                choosing = false;
            }
            else if (choice.equals("2")){
                History history = new History();
                history.Logs();
                choosing = false;
            }
            else if (choice.equals("3")){
                System.out.println("Goodbye!");
                break;

            }
            else{
                System.out.println("===================");
                System.out.println("Invalid choice please try again.");
                System.out.println("===================");
            }

        }while (choosing);





    }
}
