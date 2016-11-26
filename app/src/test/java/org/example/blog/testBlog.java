package org.example.blog;

/**
 * Created by p on 11/26/2016.
 */

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertTrue;
public class testBlog {

    @Before
    /**
     * sets up things before each test
     */

    public void setUp() {


    }


    @Test
    public void TestParam() {
        //constructor is   public Blog(String price, String destination, String start,int month,int day,int year
        Blog Bob = new Blog("50", "LA", "SD", 7, 11, 1993);


        assertEquals("SD", Bob.getStart());
        assertEquals("50",Bob.getPrice());
        assertEquals("LA",Bob.getDestination());

        assertEquals("Jul",Bob.getMonth());

        assertEquals(11,Bob.getDay());

        assertEquals(1993,Bob.getYear());

        Bob.setStart("Egypt");
        Bob.setDestination("South Africa");
        Bob.setPrice("80");
        Bob.setMonth(10);
        Bob.setDay(12);
        Bob.setYear(1995);

        assertEquals("Egypt", Bob.getStart());
        assertEquals("80",Bob.getPrice());
        assertEquals("South Africa",Bob.getDestination());

        assertEquals("Oct",Bob.getMonth());

        assertEquals(12,Bob.getDay());

        assertEquals(1995,Bob.getYear());
        Bob.setMonth(20);

        assertEquals("",Bob.getMonth());
        String array[]={ "","Jan","Feb","Mar","Apr","May","Jun","Jul",
                "Aug","Sep","Oct","Nov","Dec", ""};
        int i=0;
        for(i=1;i<14;i++){
            Bob.setMonth(i);
            assertEquals(array[i],Bob.getMonth());

        }


    }
    public void TestParam2() {
        //constructor is   public Blog(String price, String destination, String start,int month,int day,int year
        Blog Bob2 = new Blog();

        Bob2.setStart("Egypt");
        Bob2.setDestination("Niger");
        Bob2.setPrice("70");
        Bob2.setMonth(8);
        Bob2.setDay(12);
        Bob2.setYear(1991);

        assertEquals("Egypt", Bob2.getStart());
        assertEquals("70",Bob2.getPrice());
        assertEquals("Niger",Bob2.getDestination());
        assertEquals("Aug",Bob2.getMonth());
        assertEquals(12,Bob2.getDay());
        assertEquals(1991,Bob2.getYear());




    }
}
