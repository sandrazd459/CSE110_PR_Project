package org.example.blog;

import android.widget.Switch;

/**
 * Created by Alan Beas on 10/18/2016.
 */

public class Blog {

    private String start, dest, pric, addit;
    private int month, day, year;

    public Blog(){
    }

    public Blog(String Price, String Destination, String Start, String Additional,int Month,int Day,int Year) {
        pric = Price;
        addit = Additional;
        dest = Destination;
        start = Start;
        month = Month;
        day = Day;
        year = Year;
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

    public void setDestination(String destination) {
        dest = destination;
    }


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
}
