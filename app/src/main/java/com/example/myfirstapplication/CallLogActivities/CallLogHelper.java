package com.example.myfirstapplication.CallLogActivities;

import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;

import com.example.myfirstapplication.entites.CallLogEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CallLogHelper {
    public static List<CallLogEntity> getCallLogs(Context context) {
        List<CallLogEntity> callLogs = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                long dateMillis = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                String date = formatDate(dateMillis);
                String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));
                int typeInt = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String type = getCallTypeString(typeInt);

                callLogs.add(new CallLogEntity(number, date, duration, type));
            }
            cursor.close();
        }
        return callLogs;
    }

    private static String formatDate(long dateMillis) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(dateMillis));
    }

    private static String getCallTypeString(int type) {
        switch (type) {
            case CallLog.Calls.INCOMING_TYPE:
                return "Incoming";
            case CallLog.Calls.OUTGOING_TYPE:
                return "Outgoing";
            case CallLog.Calls.MISSED_TYPE:
                return "Missed";
            case CallLog.Calls.VOICEMAIL_TYPE:
                return "Voicemail";
            case CallLog.Calls.REJECTED_TYPE:
                return "Rejected";
            case CallLog.Calls.BLOCKED_TYPE:
                return "Blocked";
            default:
                return "Unknown";
        }
    }
}
