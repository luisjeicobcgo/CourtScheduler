package com.example.courtscheduler.quarter2.practicalexam;

import java.util.Scanner;

public class MainMenu {
    public void Menu(Scanner menuinput){
        boolean choosing = true;
        int choice = menuinput.nextInt();

        do{
            System.out.print("Enter Choice: ");
            System.out.println(choice);
            if(!menuinput.hasNextInt()){
                System.out.print("Invalid Choice\n");
            }
            else{
                choosing = false;
            }
        }while (choosing);



        System.out.println("1. Scheduler");
        System.out.println("2. History");
        System.out.println("3. Exit");

        if (choice == 1) {
            Scheduler scheduler = new Scheduler();
            scheduler.Scheduling(menuinput);
        }
        else if (choice == 2){
            History history = new History();
            history.Logs();
        }
    }
}
