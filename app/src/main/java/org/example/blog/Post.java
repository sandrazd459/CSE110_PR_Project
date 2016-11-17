package org.example.blog;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Alan Beas on 10/18/2016.
 */

public class Post implements Parcelable{

    private String start, destination, price, additional;
    private int month, day, year;

    public Post(){
    }

    public Post(String price, String destination, String start, String additonal, int month, int day, int year) {
        this.price = price;
        this.additional = additonal;
        this.destination = destination;
        this.start = start;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Post(Parcel source){
        Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
        start = source.readString();
        destination = source.readString();
        price = source.readString();
        additional = source.readString();
        month = source.readInt();
        day = source.readInt();
        year = source.readInt();
    }

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

    public int getMonth() {
        return month;
    }
    public void setMonth(int dMonth) {
        month = dMonth;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int dDay) {
        day = dDay;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int dYear) {
        year = dYear;
    }


    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel desti, int flags) {

        Log.v(TAG, "writeToParcel..."+ flags);
        desti.writeString(start);
        desti.writeString(destination);
        desti.writeString(price);
        desti.writeString(additional);

        desti.writeInt(month);
        desti.writeInt(day);
        desti.writeInt(year);

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
}
