package com.example.myfirstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {

    private TextInputEditText nameEditText, emailEditText, phoneEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        passwordEditText = findViewById(R.id.password);
        Button clickButton = findViewById(R.id.Click);

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Intent intent = new Intent(MainActivity2.this, DisplayActivity.class);
                intent.putExtra("name", name.isEmpty() ? "Name is empty" : name);
                intent.putExtra("email", email.isEmpty() ? "Email is empty" : email);
                intent.putExtra("phone", phone.isEmpty() ? "Phone is empty" : phone);
                intent.putExtra("password", password.isEmpty() ? "Password is empty" : password);
                startActivity(intent);
            }
        });
    }
}
