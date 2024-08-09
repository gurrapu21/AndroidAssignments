package com.example.myfirstapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapplication.entites.Form;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;

public class InputFormActivity extends AppCompatActivity {

    private TextInputEditText nameEditText, emailEditText, phoneEditText, passwordEditText;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private Uri imageUri;
    private Bitmap imageBitmap;
    private Form form;

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

        Button capturePhotoButton = findViewById(R.id.captureButton);

        capturePhotoButton.setOnClickListener(view -> openCamera());

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Intent intent = new Intent(InputFormActivity.this, DisplayFormActivity.class);

                form = new Form(name, email, phone, password, imageUri, imageBitmap);

//                Log.d("imageBitmap", form.getImageBitmap().toString());

                intent.putExtra("form", form);

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

    private void openCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, CAMERA_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageBitmap = null;
            imageUri = data.getData();
            displayImage();
        } else if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = null;
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            // Display the image in the ImageView
            ImageView iv = findViewById(R.id.imageView);
            iv.setImageBitmap(imageBitmap);



            // Convert Bitmap to Uri for passing to other activity (implementation not provided here)
            //imageUri = getImageUri(InputFormActivity.this, imageBitmap);
        }
    }

    private void displayImage() {
        ImageView iv = findViewById(R.id.imageView);
        iv.setImageURI(imageUri);
    }


}
