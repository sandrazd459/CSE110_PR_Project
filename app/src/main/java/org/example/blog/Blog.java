package org.example.blog;

/**
 * Created by Alan Beas on 10/18/2016.
 */

public class Blog {

    private String Start, Destination, Date, Price;

    public Blog(){

    }

    public Blog(String price, String date, String destination, String start) {
        Price = price;
        Date = date;
        Destination = destination;
        Start = start;
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

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
