package org.example.blog;

/**
 * Created by hankliu on 11/13/16.
 */


public class Post {

    String username;
    String start;
    String destination;
    String price;
    String date;
    String additional;

    // empty constructor needed by the Parceler library:
    public Post() {
    }

    public Post(String username, String from, String destination, String price, String comments) {
        this.username = username;
        this.start = from;
        this.destination = destination;
        this.price = price;
        this.additional = comments;
    }

    public String getUsername() {
        return username;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public String getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getAdditional() {
        return additional;
    }
}
