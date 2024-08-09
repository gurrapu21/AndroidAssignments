package com.example.myfirstapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.entites.Form;
import com.google.android.material.textfield.TextInputEditText;

public class InputFormActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, emailEditText, phoneEditText, passwordEditText;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_form);

        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        passwordEditText = findViewById(R.id.password);
        Button clickButton = findViewById(R.id.submit);

        Button selectImageButton = findViewById(R.id.selectImageButton);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Intent intent = new Intent(InputFormActivity.this, DisplayFormActivity.class);
                intent.putExtra("form", new Form(name, imageUri, password, phone, email));
                startActivity(intent);
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();



            String imageUriString = imageUri.toString();
            if (imageUriString != null) {
                imageUri = Uri.parse(imageUriString);

                ImageView iv = findViewById(R.id.imageView);
                iv.setImageURI(imageUri);

            }
        }
    }
}
