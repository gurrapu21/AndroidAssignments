package com.example.myfirstapplication.CallLogActivities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.entites.CallLogEntity;

public class CallLogDetailsActivity extends AppCompatActivity {
    private TextView number, date, duration, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log_details);

        number = findViewById(R.id.number);
        date = findViewById(R.id.date);
        duration = findViewById(R.id.duration);
        type = findViewById(R.id.type);

        CallLogEntity callLog = getIntent().getParcelableExtra("callLog");

        if (callLog != null) {
            number.setText(callLog.getNumber());
            date.setText(callLog.getDate());
            duration.setText(callLog.getDuration());
            type.setText(callLog.getType());
        }
    }
}
