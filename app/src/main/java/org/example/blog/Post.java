package org.example.blog;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Alan Beas on 10/18/2016.
 */

public class Post implements Parcelable{

    private String uid, start, destination, price, additional;
    private int date;

    public Post(){
    }

    public Post(String uid, String price, String destination, String start, String additional, int date) {
        this.uid = uid;
        this.price = price;
        this.additional = additional;
        this.destination = destination;
        this.start = start;
        this.date = date;
    }

    public Post(Parcel source){
        Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
        uid = source.readString();
        start = source.readString();
        destination = source.readString();
        price = source.readString();
        additional = source.readString();
        date = source.readInt();
    }

    public String getUid() { return uid; }

    public int getDate() { return date; }
    public void setDate(int date) { this.date = date;}

    public String getStart() {
        return start;
    }
    public void setStart(String s) {
        start = s;
    }

    public String getAdditional() {
        return additional;
    }
    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) { this.destination = destination; }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getStringDate() {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;
        return stringMonth(month)+" "+ day + ", " + year;
    }


    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel desti, int flags) {

        Log.v(TAG, "writeToParcel..."+ flags);
        desti.writeString(uid);
        desti.writeString(start);
        desti.writeString(destination);
        desti.writeString(price);
        desti.writeString(additional);

        desti.writeInt(date);

    }
    public static final Parcelable.Creator CREATOR
            = new Parcelable.Creator() {
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    private String stringMonth(int m){
        switch (m){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Aug";
            case 9:
                return "Sep";
            case 10:
                return "Oct";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "";
    }
}
