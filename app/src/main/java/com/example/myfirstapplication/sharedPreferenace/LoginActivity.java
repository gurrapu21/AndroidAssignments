package com.example.myfirstapplication.sharedPreferenace;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.R;

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginBtn;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.contains("username") && sharedPreferences.contains("password")) {
            // User is already logged in, launch HomeActivity
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish(); // Finish LoginActivity to prevent going back to it
        } else {
            // User is not logged in, show login screen
            setContentView(R.layout.activity_login);

            username = findViewById(R.id.etUsername);
            password = findViewById(R.id.etPassword);
            loginBtn = findViewById(R.id.btnLogin);

            loginBtn.setOnClickListener(view -> {
                login();
            });
        }
    }

    private void login() {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
        } else if (user.equals("admin") && pass.equals("admin")) {
            editor.putString("username", user);
            editor.putString("password", pass);
            editor.apply();
            editor.commit();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            finish(); // Finish LoginActivity to prevent going back to it
        } else {
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }
}
