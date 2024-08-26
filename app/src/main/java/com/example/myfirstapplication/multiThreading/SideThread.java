package com.example.myfirstapplication.multiThreading;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class SideThread extends Thread {
    public Handler handler;
    private MultiThreadingActivity mainActivity;
    SideThread(MultiThreadingActivity mainActivity){
        this.mainActivity = mainActivity;
    }


    @Override
    public void run() {
        Looper.prepare();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                int number1 = msg.getData().getInt("number1");
                int number2 = msg.getData().getInt("number2");
                int result = number1 * number2;

                Message resultMsg = mainActivity.mainHandler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("result", result);
                resultMsg.setData(bundle);
                mainActivity.mainHandler.sendMessage(resultMsg);
            }
        };
        Looper.loop();
    }
}