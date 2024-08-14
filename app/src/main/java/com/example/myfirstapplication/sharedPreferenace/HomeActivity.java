package com.example.myfirstapplication.sharedPreferenace;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfirstapplication.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btnLogout).setOnClickListener(v -> {
            getSharedPreferences("MyPrefs", MODE_PRIVATE).edit().clear().apply();

            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        });
    }
}