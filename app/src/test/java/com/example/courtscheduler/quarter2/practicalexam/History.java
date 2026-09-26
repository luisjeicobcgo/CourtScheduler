package com.example.courtscheduler.quarter2.practicalexam;

import java.util.ArrayList;
import java.util.Scanner;

public class History {
    public void Logs(ScheduleManager schedulerManager, Scanner historyoptions){


        ArrayList<Schedules> schedules = schedulerManager.getSchedules();

        boolean choosing = true;

        do{
            if (schedules.isEmpty()){
                System.out.println("===================");
                System.out.println("History is empty.");
                System.out.println("===================");
                ;        }
            else{
                for (Schedules schedules1 : schedules){
                    //ALMONTE
                    System.out.println("Schedule ID: " + schedules1.getID()); // PEREZ
                    System.out.println("Start Time: " + schedules1.getStartTime());
                    System.out.println("End Time: " + schedules1.getEndTime());
                    System.out.println("Date: " + schedules1.getDate());
                    //
                    //PEREZ
                    System.out.println("Purpose of using the court: " + schedules1.getPurpose());
                    System.out.println("===================");

                }
            }
            System.out.println("Would you like to cancel your schedule appointment?");
            System.out.println("1. Cancel Schedule");
            System.out.println("2. Add Schedule");
            System.out.println("3. Exit");
            System.out.print("Please state your option: ");
            String choose = historyoptions.nextLine();
            System.out.println(choose);
            if (choose.equals("1")){
                System.out.print("Which schedule do you want to cancel? (Schedule ID): ");
                int id = Integer.parseInt(historyoptions.nextLine());
                System.out.println(id);
                boolean removed = false;

                for (int i = 0; i < schedules.size(); i++) {
                    if (schedules.get(i).getID() == id ) {
                        schedules.remove(i);
                        removed = true;
                        System.out.print("Schedule cancelled successfully.");
                    }
                }
                if (!removed) {
                    System.out.println("Schedule ID not found.");
                }

            }
            else if (choose.equals("2")){
                Scheduler scheduler1 = new Scheduler();
                scheduler1.Scheduling(historyoptions, schedulerManager);
                choosing = false;
            }
            else if (choose.equals("3")){
                System.exit(0);
            }
            else{
                System.out.println("Invalid choice.");
            }
            System.out.println("\n===================");
        }while (choosing);

    }
}
