package org.example.blog;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


/**
 * Created by julianlin on 11/21/16.
 */

@RunWith(AndroidJUnit4.class)
public class Main_navigationTest {

    @Rule
    public ActivityTestRule<Main_navigation> mActivityRule = new ActivityTestRule<>(
            Main_navigation.class);

    @Test
    //check if the view of activity_main_navigation is successfully presented
    public void shouldBeAbleToLaunchMainScreen() {
        onView(withId(R.id.activity_main_navigation))
                .check(ViewAssertions
                        .matches(isDisplayed()));
    }

    //check if testRequestNewRide Button exists
//    public void testRequestNewRideButtonExists(){
////        ViewInteraction button = onView(
////                AllOf(WithId(R.id.)).
////                        childAtPosition(
////                                AllOf(withId(R.Id.requestList)).
////                                        childAtPosition
////                        )
////
////        )
//        onView(allOf(withId(R.Id.requestList).withText("request_new_ride")));
//    }
    public void testRequestNewRideButton() {
        //Attempt to press the request new ride button
        //See if it brings us to Ride Sell Form activity
        onView(withId(R.id.requestList))
                .perform(click());
    }
//

    public void testOfferNewRideButton(){
        //Attempt to press the offer new ride button
        onView(withId(R.id.sellingList))
                .perform(click());
    }
//
    public void testUpdateProfileButton(){
        //Attempt to press the update profile button
        onView(withId(R.id.activity_main_navigation))
                .perform(click());
    }
}
