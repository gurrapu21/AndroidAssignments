package com.example.myfirstapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myfirstapplication.R;

public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        TextView tvName = view.findViewById(com.example.myfirstapplication.R.id.tvName);
        TextView tvGender = view.findViewById(R.id.tvGender);

        // Retrieve data from arguments
        Bundle args = getArguments();
        if (args != null) {
            String name = args.getString("name", "Name Placeholder");
            String gender = args.getString("gender", "Gender Placeholder");

            tvName.setText("Name: " + name);
            tvGender.setText("Gender: " + gender);
        }

        return view;
    }
}
