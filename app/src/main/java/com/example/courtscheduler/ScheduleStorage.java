package com.example.courtscheduler;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONArray;
import org.json.JSONException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ScheduleStorage {
    private static final String PREF_NAME = "CourtSchedulerPrefs";
    private static final String KEY_SCHEDULES = "saved_schedules";

    // Save a new schedule permanently
    public static void addSchedule(Context context, String scheduleDetails) {
        List<String> currentList = getSchedules(context);
        currentList.add(scheduleDetails);

        JSONArray jsonArray = new JSONArray();
        for (String item : currentList) {
            jsonArray.put(item);
        }

        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_SCHEDULES, jsonArray.toString()).apply();
    }

    // Load all saved schedules
    public static List<String> getSchedules(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String jsonString = prefs.getString(KEY_SCHEDULES, null);
        List<String> list = new ArrayList<>();

        if (jsonString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);
                for (int i = 0; i < jsonArray.length(); i++) {
                    list.add(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // Remove a schedule by position
    public static void removeSchedule(Context context, int position) {
        List<String> currentList = getSchedules(context);
        if (position >= 0 && position < currentList.size()) {
            currentList.remove(position);

            JSONArray jsonArray = new JSONArray();
            for (String item : currentList) {
                jsonArray.put(item);
            }

            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            prefs.edit().putString(KEY_SCHEDULES, jsonArray.toString()).apply();
        }
    }

    // Check if the date and time slot is already reserved
    public static boolean isTimeSlotTaken(Context context, String date, String newStart, String newEnd) {
        List<String> savedList = getSchedules(context);

        for (String entry : savedList) {
            String existingDate = extractValue(entry, "Schedule Date: ");
            String existingStart = extractValue(entry, "Start Time: ");
            String existingEnd = extractValue(entry, "End Time: ");

            if (existingDate.equalsIgnoreCase(date.trim())) {
                if (existingStart.equalsIgnoreCase(newStart.trim()) ||
                        existingEnd.equalsIgnoreCase(newEnd.trim())) {
                    return true;
                }

                if (isOverlapping(existingStart, existingEnd, newStart, newEnd)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String extractValue(String text, String label) {
        int startIdx = text.indexOf(label);
        if (startIdx == -1) return "";
        startIdx += label.length();
        int endIdx = text.indexOf("\n", startIdx);
        if (endIdx == -1) endIdx = text.length();
        return text.substring(startIdx, endIdx).trim();
    }

    private static boolean isOverlapping(String start1, String end1, String start2, String end2) {
        long s1 = parseTimeToMillis(start1);
        long e1 = parseTimeToMillis(end1);
        long s2 = parseTimeToMillis(start2);
        long e2 = parseTimeToMillis(end2);

        if (s1 == -1 || e1 == -1 || s2 == -1 || e2 == -1) {
            return false;
        }
        return s1 < e2 && e1 > s2;
    }

    private static long parseTimeToMillis(String timeStr) {
        String[] patterns = {"h:mm a", "hh:mm a", "H:mm", "HH:mm", "h:mma", "ha"};
        for (String pattern : patterns) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.US);
                Date d = sdf.parse(timeStr.trim().toUpperCase(Locale.US));
                if (d != null) return d.getTime();
            } catch (ParseException ignored) {}
        }
        return -1;
    }
    public static void updateSchedule(Context context, int position, String updatedDetails) {
        List<String> currentList = getSchedules(context);
        if (position >= 0 && position < currentList.size()) {
            currentList.set(position, updatedDetails);

            JSONArray jsonArray = new JSONArray();
            for (String item : currentList) {
                jsonArray.put(item);
            }

            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            prefs.edit().putString(KEY_SCHEDULES, jsonArray.toString()).apply();
        }
    }

    public static boolean isTimeSlotTakenExcluding(Context context, int ignorePosition, String date, String newStart, String newEnd) {
        List<String> savedList = getSchedules(context);

        for (int i = 0; i < savedList.size(); i++) {
            if (i == ignorePosition) continue; // Skip the schedule currently being rescheduled

            String entry = savedList.get(i);
            String existingDate = extractValue(entry, "Schedule Date: ");
            String existingStart = extractValue(entry, "Start Time: ");
            String existingEnd = extractValue(entry, "End Time: ");

            if (existingDate.equalsIgnoreCase(date.trim())) {
                if (existingStart.equalsIgnoreCase(newStart.trim()) ||
                        existingEnd.equalsIgnoreCase(newEnd.trim())) {
                    return true;
                }
                if (isOverlapping(existingStart, existingEnd, newStart, newEnd)) {
                    return true;
                }
            }
        }
        return false;
    }
}
