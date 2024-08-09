package com.example.myfirstapplication.entites;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Form implements Parcelable {


    private String name;
    private String email;
    private String phone;
    private String password;
    private Uri imageUri;


    private Bitmap imageBitmap;


    public Form(String name, String email, String phone, String password, Uri imageUri, Bitmap imageBitmap) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.imageUri = imageUri;
        this.imageBitmap = imageBitmap;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }




    public Form(String name, Uri imageUri, String password, String phone, String email) {
        this.name = name;
        this.imageUri = imageUri;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public Form(String name, Bitmap imageBitmap, String password, String phone, String email){
        this.name = name;
        this.imageBitmap = imageBitmap;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }


    protected Form(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
        password = in.readString();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
        imageBitmap = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Form> CREATOR = new Creator<Form>() {
        @Override
        public Form createFromParcel(Parcel in) {
            return new Form(in);
        }

        @Override
        public Form[] newArray(int size) {
            return new Form[size];
        }
    };

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(password);
        parcel.writeParcelable(imageUri, i);
        parcel.writeParcelable(imageBitmap, i);
    }
}
