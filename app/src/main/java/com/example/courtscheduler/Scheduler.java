package com.example.courtscheduler;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Scheduler extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton menu;

    EditText userID, startTime, endTime, scheduleDate;
    EditText scheduleID, courtPurpose;
    Button addSchedule;

    // Stores real time and date instead of strings
    Calendar selectedDateCal = Calendar.getInstance();
    Calendar startTimeCal = null;
    Calendar endTimeCal = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        menu = findViewById(R.id.menu);

        menu.setOnClickListener(v ->
                drawerLayout.openDrawer(navigationView));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_dashboard) {
                startActivity(new Intent(Scheduler.this, Dashboard.class));
            } else if (id == R.id.nav_history) {
                startActivity(new Intent(Scheduler.this, HIstory.class));
            } else if (id == R.id.nav_scheduler) {
                Toast.makeText(
                        Scheduler.this,
                        "You are already on Scheduler.",
                        Toast.LENGTH_SHORT
                ).show();
            }

            drawerLayout.closeDrawer(navigationView);
            return true;
        });

        userID = findViewById(R.id.userID);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        scheduleDate = findViewById(R.id.scheduleDate);
        scheduleID = findViewById(R.id.scheduleID);
        courtPurpose = findViewById(R.id.courtPurpose);


        addSchedule = findViewById(R.id.add_schedule);


        setupPickerOnlyField(scheduleDate);
        setupPickerOnlyField(startTime);
        setupPickerOnlyField(endTime);


        scheduleDate.setOnClickListener(v -> showDatePicker());


        startTime.setOnClickListener(v -> showTimePicker(true));
        endTime.setOnClickListener(v -> showTimePicker(false));

        addSchedule.setOnClickListener(v -> addSchedule());
    }

    private void setupPickerOnlyField(EditText field) {
        field.setFocusable(false);
        field.setClickable(true);
        field.setLongClickable(false);
    }

    private void showDatePicker() {
        int year = selectedDateCal.get(Calendar.YEAR);
        int month = selectedDateCal.get(Calendar.MONTH);
        int day = selectedDateCal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    selectedDateCal.set(selectedYear, selectedMonth, selectedDay);
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    scheduleDate.setText(sdf.format(selectedDateCal.getTime()));
                    scheduleDate.setError(null);
                },
                year, month, day
        );

        // Stops being able to pick previous days
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePicker.show();
    }

    private void showTimePicker(boolean isStartTime) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(
                this,
                (view, selectedHour, selectedMinute) -> {
                    Calendar pickedTime = Calendar.getInstance();
                    pickedTime.set(Calendar.HOUR_OF_DAY, selectedHour);
                    pickedTime.set(Calendar.MINUTE, selectedMinute);
                    pickedTime.set(Calendar.SECOND, 0);
                    pickedTime.set(Calendar.MILLISECOND, 0);

                    SimpleDateFormat sdf = new SimpleDateFormat("h:mm a", Locale.US);
                    String formattedTime = sdf.format(pickedTime.getTime());

                    if (isStartTime) {
                        startTimeCal = pickedTime;
                        startTime.setText(formattedTime);
                        startTime.setError(null);
                    } else {
                        endTimeCal = pickedTime;
                        endTime.setText(formattedTime);
                        endTime.setError(null);
                    }
                },
                hour, minute, false // false = 12 hours, true = military time/24 hrs
        );
        timePicker.show();
    }

    private void addSchedule() {
        String user = userID.getText().toString().trim();
        String start = startTime.getText().toString().trim();
        String end = endTime.getText().toString().trim();
        String date = scheduleDate.getText().toString().trim();
        String schedID = scheduleID.getText().toString().trim();
        String purpose = courtPurpose.getText().toString().trim();

        if (user.isEmpty()) {
            userID.setError("Please enter User ID");
            userID.requestFocus();
            return;
        }

        if (start.isEmpty() || startTimeCal == null) {
            startTime.setError("Please select a Start Time");
            Toast.makeText(this, "Tap Start Time to pick a time", Toast.LENGTH_SHORT).show();
            return;
        }

        if (end.isEmpty() || endTimeCal == null) {
            endTime.setError("Please select an End Time");
            Toast.makeText(this, "Tap End Time to pick a time", Toast.LENGTH_SHORT).show();
            return;
        }



        if (date.isEmpty()) {
            scheduleDate.setError("Please select a Schedule Date");
            Toast.makeText(this, "Tap Schedule Date to pick a date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (schedID.isEmpty()) {
            scheduleID.setError("Please enter Schedule ID");
            scheduleID.requestFocus();
            return;
        }

        if (purpose.isEmpty()) {
            courtPurpose.setError("Please enter Court Purpose");
            courtPurpose.requestFocus();
            return;
        }


        if (ScheduleStorage.isTimeSlotTaken(Scheduler.this, date, start, end)) {
            startTime.setError("Time slot already reserved on " + date);
            Toast.makeText(
                    Scheduler.this,
                    "Time conflict! That date and time is already reserved.",
                    Toast.LENGTH_LONG
            ).show();
            return;
        }

        String scheduleDetails =
                "User ID: " + user +
                        "\n\nStart Time: " + start +
                        "\nEnd Time: " + end +
                        "\nSchedule Date: " + date +
                        "\n\nSchedule ID: " + schedID +
                        "\nCourt Purpose: " + purpose;

        new AlertDialog.Builder(Scheduler.this)
                .setTitle("Schedule Details")
                .setMessage(scheduleDetails)
                .setPositiveButton("Confirm", (dialog, which) -> {
                    ScheduleStorage.addSchedule(Scheduler.this, scheduleDetails);
                    Toast.makeText(
                            Scheduler.this,
                            "Schedule details confirmed!",
                            Toast.LENGTH_LONG
                    ).show();
                })
                .setNegativeButton("Edit", (dialog, which) ->
                        dialog.dismiss()
                )
                .show();
    }
}
