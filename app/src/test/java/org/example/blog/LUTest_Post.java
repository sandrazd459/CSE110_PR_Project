package org.example.blog;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


import org.junit.Test;
import org.junit.Before;
/**
 * Created by Aman Singh on 11/26/2016.
 */


import static org.junit.Assert.assertEquals;

public class LUTest_Post {

  /*  public Post(String uid, String price, String destination,
   String start, String additional, int date)

    */

    @Test
    public void TestParam() {
        Post newPost =new Post("5000", "50",
                "LA", "SD", "yes sir", 19930711);

        assertEquals("5000", newPost.getUid());
        assertEquals(19930711,newPost.getDate());
        assertEquals("SD",newPost.getStart());
        assertEquals("LA",newPost.getDestination());

        assertEquals("yes sir",newPost.getAdditional());
        assertEquals("50",newPost.getPrice());
        int i;

        assertEquals("Jul 11, 1993",newPost.getStringDate());

        newPost.setDate(19920711);

       newPost.setAdditional("yes sirs");
        newPost.setPrice("500");
        newPost.setStart("LA");
        newPost.setDestination("SD");

        assertEquals("5000", newPost.getUid());
        assertEquals(19920711,newPost.getDate());
        assertEquals("LA",newPost.getStart());
        assertEquals("SD",newPost.getDestination());

        assertEquals("yes sirs",newPost.getAdditional());
        assertEquals("500",newPost.getPrice());


        assertEquals("Jul 11, 1992",newPost.getStringDate());

    }
}
