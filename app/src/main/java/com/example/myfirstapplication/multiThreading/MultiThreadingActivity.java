// MainActivity.java
package com.example.myfirstapplication.multiThreading;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.R;

public class MultiThreadingActivity extends AppCompatActivity {

    private EditText number1EditText;
    private EditText number2EditText;
    private TextView resultTextView;
    private SideThread workerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_threading);

        number1EditText = findViewById(R.id.number1);
        number2EditText = findViewById(R.id.number2);
        resultTextView = findViewById(R.id.resultTextView);
        Button calculateButton = findViewById(R.id.calculateButton);

        workerThread = new SideThread(this);
        workerThread.start();

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = Integer.parseInt(number1EditText.getText().toString());
                int number2 = Integer.parseInt(number2EditText.getText().toString());

                Message msg = workerThread.handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("number1", number1);
                bundle.putInt("number2", number2);

                msg.setData(bundle);
                workerThread.handler.sendMessage(msg);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        workerThread.handler.getLooper().quit();
    }



    protected final Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int result = msg.getData().getInt("result");
            resultTextView.setText("Result: " + result);
        }
    };
}
