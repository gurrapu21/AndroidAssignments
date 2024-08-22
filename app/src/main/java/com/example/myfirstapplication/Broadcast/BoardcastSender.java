package com.example.myfirstapplication.Broadcast;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myfirstapplication.R;

public class BoardcastSender extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_boardcast_sender);

        findViewById(R.id.sendbtn).setOnClickListener(view -> {

            Intent intent = new Intent("com.example.myfirstapplication.MY_ACTION");
            intent.setComponent(new ComponentName("com.example.broadcastreceiver", "com.example.broadcastreceiver.BRReceiver"));
            sendBroadcast(intent);
        });

    }
}