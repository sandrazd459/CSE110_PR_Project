package org.example.blog;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by hankliu on 11/16/16.
 */

public class User {
    private String username, additional, frontPhoneNumber, midPhoneNumber, lastPhoneNumber;
    private ArrayList<String> ownPostsId;

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
    public void setUsername(String username) { this.username = username; }

    public String getAdditional() { return additional; }
    public void setAdditional(String additional) { this.additional = additional; }

    public String getFrontPhoneNumber() { return frontPhoneNumber; }
    public void setFrontPhoneNumber(String frontPhoneNumber) { this.frontPhoneNumber = frontPhoneNumber; }

    public String getMidPhoneNumber() { return midPhoneNumber; }
    public void setMidPhoneNumber(String midPhoneNumber) { this.midPhoneNumber = midPhoneNumber; }

    public String getLastPhoneNumber() { return lastPhoneNumber; }
    public void setLastPhoneNumber(String lastPhoneNumber) { this.lastPhoneNumber = lastPhoneNumber; }

    public String getStringPhoneNumber() {
        return "(" + getFrontPhoneNumber()+ ")-" + getMidPhoneNumber() + "-" + getLastPhoneNumber();
    }

    public ArrayList<String> getOwnPostsId() { return ownPostsId; }
    public void setOwnPostsId(ArrayList<String> ownPosts) { this.ownPostsId = ownPostsId; }

    public int describeContents() {
        return this.hashCode();
    }

    public void writeToParcel(Parcel desti, int flags) {

        Log.v(TAG, "writeToParcel..."+ flags);
        desti.writeString(username);
        desti.writeString(additional);
        desti.writeString(frontPhoneNumber);
        desti.writeString(midPhoneNumber);
        desti.writeString(lastPhoneNumber);

    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        public User[] newArray(int size) { return new User[size]; }
    };
}
