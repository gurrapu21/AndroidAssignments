package com.example.myfirstapplication.CallLogActivities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.CallLogActivities.CallLogAdapter;
import com.example.myfirstapplication.R;
import com.example.myfirstapplication.entites.CallLogEntity;

import java.util.List;

public class CallLogActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_READ_CALL_LOG = 1;
    private RecyclerView recyclerView;
    private CallLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_log);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, REQUEST_CODE_READ_CALL_LOG);
        } else {
            loadCallLogs();
        }
    }

    private void loadCallLogs() {
        List<CallLogEntity> callLogs = CallLogHelper.getCallLogs(this);
        adapter = new CallLogAdapter(callLogs, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_READ_CALL_LOG) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadCallLogs();
            } else {
                Toast.makeText(this, "Permission denied to read call logs", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
