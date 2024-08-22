package com.example.myfirstapplication.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BCRecever extends BroadcastReceiver {

    private SendNums sendNums;

    public BCRecever(SendNums sendNums) {
        this.sendNums = sendNums;
    }

    public BCRecever() {}

    @Override
    public void onReceive(Context context, Intent intent) {


        if(intent.getAction().equals("android.intent.action.GET_TWO_NUMBERS")) {
            Intent intent1 = new Intent(context, BroadcastActivity.class);

            int num1 = Integer.parseInt(intent.getStringExtra("num1"));
            int num2 = Integer.parseInt(intent.getStringExtra("num2"));
            sendNums.ops(num1, num2);
        }
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus != null) {
                    for (Object pdu : pdus) {
                        SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                        String sender = smsMessage.getDisplayOriginatingAddress();
                        String message = smsMessage.getDisplayMessageBody();
//                        Log.d(TAG, "SMS received from: " + sender + ", message: " + message);
                        Toast.makeText(context, "SMS received from: " + sender + ", message: " + message, Toast.LENGTH_LONG).show();
                    }
                }
            }
        }

//        intent1.putParcelableArrayListExtra("ops",ops);

//        context.startActivity(intent1);


    }
}
