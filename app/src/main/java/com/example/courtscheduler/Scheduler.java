package com.example.courtscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.Toast;
import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;

public class Scheduler extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageButton menu;

    EditText userID, startTime, endTime, scheduleDate;
    EditText scheduleID, courtPurpose;

    Spinner cancellationRescheduling, reservation;

    Button addSchedule;

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

                startActivity(new Intent(
                        Scheduler.this, Dashboard.class));

            } else if (id == R.id.nav_history) {

                startActivity(new Intent(
                        Scheduler.this, HIstory.class));

            } else if (id == R.id.nav_scheduler) {

                Toast.makeText(
                        Scheduler.this,
                        "You are already on Scheduler",
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

        cancellationRescheduling =
                findViewById(R.id.cancellationRescheduling);

        reservation = findViewById(R.id.reservation);

        addSchedule = findViewById(R.id.add_schedule);

        addSchedule.setOnClickListener(v -> addSchedule());

    }
    private void addSchedule() {

        String user = userID.getText().toString().trim();
        String start = startTime.getText().toString().trim();
        String end = endTime.getText().toString().trim();
        String date = scheduleDate.getText().toString().trim();
        String schedID = scheduleID.getText().toString().trim();
        String purpose = courtPurpose.getText().toString().trim();

        String cancellation =
                cancellationRescheduling.getSelectedItem().toString();

        String reservationOption =
                reservation.getSelectedItem().toString();

        if (user.isEmpty()) {
            userID.setError("Please enter User ID");
            userID.requestFocus();
            return;
        }

        if (start.isEmpty()) {
            startTime.setError("Please enter Start Time");
            startTime.requestFocus();
            return;
        }

        if (end.isEmpty()) {
            endTime.setError("Please enter End Time");
            endTime.requestFocus();
            return;
        }

        if (date.isEmpty()) {
            scheduleDate.setError("Please enter Schedule Date");
            scheduleDate.requestFocus();
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

        String scheduleDetails =
                "User ID: " + user +
                        "\n\nStart Time: " + start +
                        "\nEnd Time: " + end +
                        "\nSchedule Date: " + date +
                        "\n\nCancellation/Rescheduling: " + cancellation +
                        "\nReservation: " + reservationOption +
                        "\n\nSchedule ID: " + schedID +
                        "\nCourt Purpose: " + purpose;

        new AlertDialog.Builder(Scheduler.this)
                .setTitle("Schedule Details")
                .setMessage(scheduleDetails)
                .setPositiveButton("Confirm", (dialog, which) ->
                        Toast.makeText(
                                Scheduler.this,
                                "Schedule details confirmed!",
                                Toast.LENGTH_LONG
                        ).show()
                )
                .setNegativeButton("Edit", (dialog, which) ->
                        dialog.dismiss()
                )
                .show();
    }
}
