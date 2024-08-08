package com.example.myfirstapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView nameTextView = findViewById(R.id.display_name);
        TextView emailTextView = findViewById(R.id.display_email);
        TextView phoneTextView = findViewById(R.id.display_phone);
        TextView passwordTextView = findViewById(R.id.display_password);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String password = getIntent().getStringExtra("password");

        nameTextView.setText("Name: " + name);
        emailTextView.setText("Email: " + email);
        phoneTextView.setText("Phone: " + phone);
        passwordTextView.setText("Password: " + password);
    }
}
