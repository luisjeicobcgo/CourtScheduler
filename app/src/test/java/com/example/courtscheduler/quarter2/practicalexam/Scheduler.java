package com.example.courtscheduler.quarter2.practicalexam;

import java.util.Scanner;

public class Scheduler {
    boolean chooseoptions = true;
    public void Scheduling(Scanner scheduleinput, ScheduleManager scheduleManager) {



        do{
            System.out.println("===================");
            System.out.println("1. Set a schedule");
            System.out.println("2. History");
            System.out.println("3. Exit");
            System.out.print("Please choose an option (1, 2, 3): ");
            String choice = scheduleinput.nextLine();
            System.out.println(choice);
            System.out.println("===================");

            if (choice.equals("1")){
                //ALMONTE
                System.out.print("Enter start time: ");
                String startTime = scheduleinput.nextLine();
                System.out.println(startTime);

                System.out.print("Enter end time: ");
                String endTime = scheduleinput.nextLine();
                System.out.println(endTime);

                System.out.print("Enter date: ");
                String date = scheduleinput.nextLine();
                System.out.println(date);
                //
                //PEREZ

                System.out.print("Purpose of using the court: ");
                String purpose = scheduleinput.nextLine();
                System.out.print(purpose);

                Schedules schedules = new Schedules(
                        startTime,
                        endTime,
                        date,
                        purpose
                );
                scheduleManager.addSchedule(schedules);

                System.out.println("\n--- Schedule Details Captured ---");
            }
            else if (choice.equals("2")){
                History history = new History();
                history.Logs(scheduleManager, scheduleinput);
                chooseoptions = false;
            }
            else if (choice.equals("3")){
                System.exit(0);
            }
        }while (chooseoptions);
    }
}
