
package com.example.courtscheduler;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    Button myButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myButton = findViewById(R.id.button3);

        myButton.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Dashboard.class);
            startActivity(intent);
        });
    }
}
