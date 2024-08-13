package com.example.myfirstapplication.entites;

import android.os.Parcel;
import android.os.Parcelable;

public class CallLogEntity implements Parcelable {
    private String number;
    private String date;
    private String duration;
    private String type;

    public CallLogEntity(String number, String date, String duration, String type) {
        this.number = number;
        this.date = date;
        this.duration = duration;
        this.type = type;
    }

    protected CallLogEntity(Parcel in) {
        number = in.readString();
        date = in.readString();
        duration = in.readString();
        type = in.readString();
    }

    public static final Creator<CallLogEntity> CREATOR = new Creator<CallLogEntity>() {
        @Override
        public CallLogEntity createFromParcel(Parcel in) {
            return new CallLogEntity(in);
        }

        @Override
        public CallLogEntity[] newArray(int size) {
            return new CallLogEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(number);
        dest.writeString(date);
        dest.writeString(duration);
        dest.writeString(type);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
