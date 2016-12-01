package org.example.blog;

/**
 * Created by Aman Singh on 11/26/2016.
 */

import org.example.blog.model.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LUTest_User {


    @Test
    public void TestParam() {

        User Aman= new User("ams015","yes sir","408","394","0318");
        assertEquals("ams015", Aman.getUsername());
        assertEquals("yes sir", Aman.getAdditional());

        assertEquals("408", Aman.getFrontPhoneNumber());

        assertEquals("394", Aman.getMidPhoneNumber());

        assertEquals("0318", Aman.getLastPhoneNumber());

        assertEquals("(408)-394-0318", Aman.getStringPhoneNumber());

    }



}
