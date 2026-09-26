package com.example.courtscheduler.quarter2.practicalexam;

import java.util.Random;
//FOR MULTIPLE SCHEDULE DETAILS
public class Schedules {
    private String startTime;
    private String endTime;
    private String date;
    public static int nextscheduleId = 1;
    public int scheduleId;
    private String purpose;

    Random random = new Random();

    public Schedules(String startTime, String endTime, String date, String purpose) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.purpose = purpose;
        this.scheduleId = nextscheduleId++;

    }
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
