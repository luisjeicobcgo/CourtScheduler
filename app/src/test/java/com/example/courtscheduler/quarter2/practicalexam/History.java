package com.example.courtscheduler.quarter2.practicalexam;

public class History {
    public void Logs(Scheduler scheduler){
        //ALMONTE
        System.out.println("Schedule ID: " + scheduler.getID()); // PEREZ
        System.out.println("Start Time: " + scheduler.getStartTime());
        System.out.println("End Time: " + scheduler.getEndTime());
        System.out.println("Date: " + scheduler.getDate());
        //
        //PEREZ
        System.out.println("Purpose of using the court: " + scheduler.getPurpose());
        System.out.println("===================");
    }
}
