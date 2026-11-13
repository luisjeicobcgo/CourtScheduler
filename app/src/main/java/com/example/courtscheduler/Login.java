

package com.example.courtscheduler;

import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button myButton;
    EditText emailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myButton = findViewById(R.id.button3);
        emailAddress = findViewById(R.id.editTextTextEmailAddress2);

        myButton.setOnClickListener(v -> {

            String email = emailAddress.getText().toString().trim();

            if (email.isEmpty()) {

                emailAddress.setError("Email is required!");
                Toast.makeText(Login.this,
                        "Login Denied! Please enter your email.",
                        Toast.LENGTH_SHORT).show();

            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

                emailAddress.setError("Enter a valid email address!");
                Toast.makeText(Login.this,
                        "Invalid email address!",
                        Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(Login.this,
                        "Login Successful!",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Login.this, Scheduler.class);
                startActivity(intent);
            }
        });
    }
}
