package org.example.blog;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Alan Beas on 10/18/2016.
 */

public class Post implements Parcelable{

    private String start, dest, pric, addit;
    private int month, day, year;

    public Post(){
    }

    public Post(String Price, String Destination, String Start, String Additional, int Month, int Day, int Year) {
        pric = Price;
        addit = Additional;
        dest = Destination;
        start = Start;
        month = Month;
        day = Day;
        year = Year;
    }

    public Post(Parcel source){
        Log.v(TAG, "ParcelData(Parcel source): time to put back parcel data");
        start = source.readString();
        dest = source.readString();
        pric = source.readString();
        addit = source.readString();

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
        return addit;
    }
    public void setAdditional(String additional) {
        addit = additional;
    }

    public String getDestination() {
        return dest;
    }
    public void setDestination(String destination) { dest = destination; }

    public String getPrice() {
        return pric;
    }
    public void setPrice(String price) {
        pric = price;
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
        desti.writeString(dest);
        desti.writeString(pric);
        desti.writeString(addit);

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
