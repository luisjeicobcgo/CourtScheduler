package com.example.courtscheduler.quarter2.practicalexam;

import java.util.ArrayList;
//FOR STORING MULTIPLE SCHEDULES
public class ScheduleManager {
    private ArrayList<Schedules> schedules = new ArrayList<>();
    public void addSchedule(Schedules schedule){
        schedules.add(schedule);
    }
    public ArrayList<Schedules> getSchedules(){
        return schedules;
    }
}
