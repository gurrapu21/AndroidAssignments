package com.example.myfirstapplication.Broadcast;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.myfirstapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BroadcastActivity extends AppCompatActivity implements SendNums {


    private TextView add, sub, div, mul;
    private BCRecever bcRecever;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_broadcast);

        add = findViewById(R.id.add);
        mul = findViewById(R.id.mult);
        sub = findViewById(R.id.sub);
        div = findViewById(R.id.divd);

        bcRecever = new BCRecever(this);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.GET_TWO_NUMBERS");
//        registerReceiver(bcRecever, intentFilter);

        EditText num1 = findViewById(R.id.num1), num2 = findViewById(R.id.num2);

        findViewById(R.id.boardcastBtn).setOnClickListener(view -> {
            Intent intent = new Intent("android.intent.action.GET_TWO_NUMBERS");
            intent.putExtra("num1", num1.getText().toString());
            intent.putExtra("num2", num2.getText().toString());
            sendBroadcast(intent);
        });
    }

    @Override
    public void ops(int num1, int num2) {

        int add = num1 + num2;
        int sub = num1 - num2;
        int div = num1 / num2;
        int mul = num1 * num2;

        this.add.setText(String.valueOf(add));
        this.sub.setText(String.valueOf(sub));
        this.div.setText(String.valueOf(div));
        this.mul.setText(String.valueOf(mul));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the BroadcastReceiver when the activity is destroyed
//        unregisterReceiver(bcRecever);
    }
}