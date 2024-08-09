package com.example.myfirstapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.entites.Form;

public class DisplayFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_form);

        TextView nameTextView = findViewById(R.id.display_name);
        TextView emailTextView = findViewById(R.id.display_email);
        TextView phoneTextView = findViewById(R.id.display_phone);
        TextView passwordTextView = findViewById(R.id.display_password);
        ImageView imageView = findViewById(R.id.image);

        Form form =  getIntent().getParcelableExtra("form");

        imageView.setImageURI(form.getImageUri());
        nameTextView.setText("Name: " + form.getName());
        emailTextView.setText("Email: " + form.getEmail());
        phoneTextView.setText("Phone: " + form.getPhone());
        passwordTextView.setText("Password: " + form.getPassword());


    }
}
