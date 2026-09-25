package com.example.courtscheduler.quarter2.practicalexam;

import java.util.Scanner;
import java.util.Random;

public class Scheduler {
    private String startTime;
    private String endTime;
    private String date;
    private int scheduleId;
    Random random = new Random();
    private String purpose;
    boolean chooseoptions = true;
    public void Scheduling(Scanner scheduleinput) {

        scheduleId = random.nextInt(900000,1000000);

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
                startTime = scheduleinput.nextLine();
                System.out.println(startTime);

                System.out.print("Enter end time: ");
                endTime = scheduleinput.nextLine();
                System.out.println(endTime);

                System.out.print("Enter date: ");
                date = scheduleinput.nextLine();
                System.out.println(date);
                //
                //PEREZ

                System.out.print("Purpose of using the court: ");
                purpose = scheduleinput.nextLine();
                System.out.print(purpose);
                System.out.println("\n--- Schedule Details Captured ---");
            }
            else if (choice.equals("2")){
                History history = new History();
                history.Logs(this);
                chooseoptions = false;
            }
            else if (choice.equals("3")){
                System.exit(0);
            }
        }while (chooseoptions);
    }
    //GETTERS FOR HISTORY TO DISPLAY THE DETAILS
    public String getStartTime(){
        return startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public String getDate(){
        return date;
    }
    public String getPurpose(){
        return purpose;
    }
    public int getID(){
        return scheduleId;
    }

}
