package org.example.blog;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

import com.google.firebase.database.IgnoreExtraProperties;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by hankliu on 11/16/16.
 */

@IgnoreExtraProperties
public class User {
    private String username, additional, frontPhoneNumber, midPhoneNumber, lastPhoneNumber;
    private ArrayList<String> ownPostsId;

    public User() {};
    public User(String username, String additional,
                String frontPhoneNumber, String midPhoneNumber, String lastPhoneNumber) {
        this.username = username;
        this.additional = additional;
        this.frontPhoneNumber = frontPhoneNumber;
        this.midPhoneNumber = midPhoneNumber;
        this.lastPhoneNumber = lastPhoneNumber;
    }

    public User(Parcel source){
        Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
        username = source.readString();
        additional = source.readString();
        frontPhoneNumber = source.readString();
        midPhoneNumber = source.readString();
        lastPhoneNumber = source.readString();
    }

    public String getUsername() { return username; }

    public String getAdditional() { return additional; }

    public String getFrontPhoneNumber() {
        return frontPhoneNumber;
    }

    public String getMidPhoneNumber() {
        return midPhoneNumber;
    }

    public String getLastPhoneNumber() {
        return lastPhoneNumber;
    }

    public String getStringPhoneNumber() {
        return "(" + frontPhoneNumber + ")-" + midPhoneNumber + "-" + lastPhoneNumber;
    }

    public void updateUser(String username, String additional,
                String frontPhoneNumber, String midPhoneNumber, String lastPhoneNumber) {
        this.username = username;
        this.additional = additional;
        this.frontPhoneNumber = frontPhoneNumber;
        this.midPhoneNumber = midPhoneNumber;
        this.lastPhoneNumber = lastPhoneNumber;
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) { return new User[size]; }
    };
}
