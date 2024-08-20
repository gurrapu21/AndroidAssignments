package com.example.myfirstapplication.Fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.myfirstapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private Spinner spinnerGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editName = findViewById(R.id.editName);
        spinnerGender = findViewById(R.id.spinnerGender);
    }

    public void sendData(View view) {
        String name = editName.getText().toString();
        String gender = spinnerGender.getSelectedItem().toString();

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("gender", gender);

        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);

        getSupportFragmentManager()
        .beginTransaction().
        replace(R.id.fragmentContainer, fragment).commit();
    }
}
