package com.example.myfirstapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class SmsDisplayActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 100;
    private TextView smsTextView;
    private Button readSmsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_display);

        smsTextView = findViewById(R.id.smsTextView);
        readSmsButton = findViewById(R.id.readSmsButton);

        readSmsButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, SMS_PERMISSION_CODE);
            } else {
                readSms();
            }
        });
    }

    private void readSms() {
        Uri inboxUri = Uri.parse("content://sms/inbox");
        Cursor cursor = getContentResolver().query(inboxUri, null, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                StringBuilder smsBuilder = new StringBuilder();
                do {
                    String address = cursor.getString(cursor.getColumnIndex("address"));
                    String body = cursor.getString(cursor.getColumnIndex("body"));
                    smsBuilder.append("From: ").append(address).append("\nMessage: ").append(body).append("\n\n");
                } while (cursor.moveToNext());
                smsTextView.setText(smsBuilder.toString());
            } else {
                smsTextView.setText("No SMS available");
            }
            cursor.close();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readSms();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
