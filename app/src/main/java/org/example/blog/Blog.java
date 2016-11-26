package org.example.blog;

import android.widget.Switch;

/**
 * Created by Alan Beas on 10/18/2016.
 */

public class Blog {

    private String Start, Destination, Price;
    private int Month,Day, Year;

    public Blog(){
    }

    public Blog(String price, String destination, String start,int month,int day,int year) {
        Destination = destination;
        Start = start;
        Month = month;
        Day = day;
        Year = year;
        Price = price;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getMonth() {

        switch (Month){
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

    public void setMonth(int dMonth) {
        Month = dMonth;
    }
    public int getDay() {
        return Day;
    }

    public void setDay(int dDay) {
        Day = dDay;
    }
    public int getYear() {
        return Year;
    }

    public void setYear(int dYear) {
        Year = dYear;
    }
}