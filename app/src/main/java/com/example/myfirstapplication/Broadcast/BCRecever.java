package com.example.myfirstapplication.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

public class BCRecever extends BroadcastReceiver {

    private SendNums sendNums;

    public BCRecever(SendNums sendNums) {
        this.sendNums = sendNums;
    }

    @Override
    public void onReceive(Context context, Intent intent) {



        Intent intent1 = new Intent(context, BroadcastActivity.class);

        int num1 = Integer.parseInt(intent.getStringExtra("num1"));
        int num2 = Integer.parseInt(intent.getStringExtra("num2"));
        sendNums.ops(num1, num2);

//        intent1.putParcelableArrayListExtra("ops",ops);

//        context.startActivity(intent1);


    }
}
