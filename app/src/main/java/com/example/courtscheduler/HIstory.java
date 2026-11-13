package com.example.courtscheduler;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HIstory extends AppCompatActivity {

    ListView historyListView;

    DrawerLayout drawerLayout;
    NavigationView side_nv;
    ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        historyListView = findViewById(R.id.historyListView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
            return insets;
        });

        drawerLayout = findViewById(R.id.main);
        side_nv = findViewById(R.id.nv_side);
        menu = findViewById(R.id.HistoryMenu);

        menu.setOnClickListener(v ->
                drawerLayout.openDrawer(side_nv));

        side_nv.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.nav_dashboard) {

                startActivity(new Intent(
                        HIstory.this, Dashboard.class));

            } else if (id == R.id.nav_history) {

                Toast.makeText(HIstory.this,
                        "You are already on History.",
                        Toast.LENGTH_SHORT
                ).show();

            } else if (id == R.id.nav_scheduler) {

                startActivity(new Intent(
                        HIstory.this, Scheduler.class
                ));
            }

            drawerLayout.closeDrawer(side_nv);
            return true;

        });

        TextView emptyTextView = findViewById(R.id.EmptyHIstory);
        historyListView.setEmptyView(emptyTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<String> savedSchedules = ScheduleStorage.getSchedules(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.schedule_card,
                R.id.cardScheduleText,
                savedSchedules
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                ImageButton rescheduleBtn = view.findViewById(R.id.btnReschedule);
                ImageButton deleteBtn = view.findViewById(R.id.btnDeleteSchedule);

                // Hover listener for mouse/stylus (shows/hides both buttons together)
                view.setOnHoverListener((v, event) -> {
                    if (event.getAction() == MotionEvent.ACTION_HOVER_ENTER) {
                        rescheduleBtn.setVisibility(View.VISIBLE);
                        deleteBtn.setVisibility(View.VISIBLE);
                    } else if (event.getAction() == MotionEvent.ACTION_HOVER_EXIT) {
                        rescheduleBtn.setVisibility(View.INVISIBLE);
                        deleteBtn.setVisibility(View.INVISIBLE);
                    }
                    return false;
                });

                // Reschedule button click
                rescheduleBtn.setOnClickListener(v ->
                        startRescheduleFlow(position, savedSchedules, this)
                );

                // Remove schedule button click
                deleteBtn.setOnClickListener(v -> {
                    ScheduleStorage.removeSchedule(HIstory.this, position);
                    savedSchedules.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(HIstory.this, "Schedule removed", Toast.LENGTH_SHORT).show();
                });

                return view;
            }
        };
        historyListView.setAdapter(adapter);
    }

    private void startRescheduleFlow(int position, List<String> savedSchedules, ArrayAdapter<String> adapter) {
        Calendar cal = Calendar.getInstance();

        // 1. Pick New Date
        DatePickerDialog datePicker = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    Calendar pickedDate = Calendar.getInstance();
                    pickedDate.set(year, month, dayOfMonth);
                    SimpleDateFormat dateFmt = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                    String newDate = dateFmt.format(pickedDate.getTime());

                    // 2. Pick New Start Time
                    new TimePickerDialog(
                            this,
                            (startView, startHour, startMinute) -> {
                                Calendar startCal = Calendar.getInstance();
                                startCal.set(Calendar.HOUR_OF_DAY, startHour);
                                startCal.set(Calendar.MINUTE, startMinute);
                                startCal.set(Calendar.SECOND, 0);
                                startCal.set(Calendar.MILLISECOND, 0);

                                SimpleDateFormat timeFmt = new SimpleDateFormat("h:mm a", Locale.US);
                                String newStart = timeFmt.format(startCal.getTime());

                                // 3. Pick New End Time
                                new TimePickerDialog(
                                        this,
                                        (endView, endHour, endMinute) -> {
                                            Calendar endCal = Calendar.getInstance();
                                            endCal.set(Calendar.HOUR_OF_DAY, endHour);
                                            endCal.set(Calendar.MINUTE, endMinute);
                                            endCal.set(Calendar.SECOND, 0);
                                            endCal.set(Calendar.MILLISECOND, 0);

                                            if (!endCal.after(startCal)) {
                                                Toast.makeText(this, "End Time must be after Start Time", Toast.LENGTH_LONG).show();
                                                return;
                                            }

                                            String newEnd = timeFmt.format(endCal.getTime());

                                            // Check if the new slot conflicts with any other booking
                                            if (ScheduleStorage.isTimeSlotTakenExcluding(this, position, newDate, newStart, newEnd)) {
                                                Toast.makeText(this, "Time conflict! That slot is already reserved.", Toast.LENGTH_LONG).show();
                                                return;
                                            }

                                            // Update the saved schedule text with the new Date, Start, End, and Status
                                            String updatedEntry = savedSchedules.get(position)
                                                    .replaceFirst("Start Time: .*", "Start Time: " + newStart)
                                                    .replaceFirst("End Time: .*", "End Time: " + newEnd)
                                                    .replaceFirst("Schedule Date: .*", "Schedule Date: " + newDate)
                                                    .replaceFirst("Cancellation/Rescheduling: .*", "Cancellation/Rescheduling: Rescheduled");

                                            ScheduleStorage.updateSchedule(this, position, updatedEntry);
                                            savedSchedules.set(position, updatedEntry);
                                            adapter.notifyDataSetChanged();

                                            Toast.makeText(this, "Schedule rescheduled!", Toast.LENGTH_SHORT).show();
                                        },
                                        startHour, startMinute, false
                                ).show();
                            },
                            cal.get(Calendar.HOUR_OF_DAY),
                            cal.get(Calendar.MINUTE),
                            false
                    ).show();
                },
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
        );

        datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePicker.show();
    }
}
