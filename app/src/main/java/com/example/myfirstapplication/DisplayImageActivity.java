package com.example.myfirstapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DisplayImageActivity extends AppCompatActivity {

    private Button getImage;

    public static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        getImage = findViewById(R.id.getImage);

        Intent explicitIntent = new Intent(DisplayImageActivity.this,BrowseActivity.class);

        getImage.setOnClickListener(v->
        startActivityForResult(explicitIntent, REQUEST_CODE));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            Uri imageUri = data.getParcelableExtra("image_uri");
            if (imageUri != null) {
                ImageView imageView = findViewById(R.id.image_View);
                imageView.setImageURI(imageUri);
            }
        }
        else {
            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
        }

    }
}