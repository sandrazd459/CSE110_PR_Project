package org.example.blog;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.example.blog.controller.Welcome;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Logout_Test {

    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

    //given the user is loged in and on main navigation page
    public void login(){
        try{
            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.welcome_login), withText("Log in"), isDisplayed()));
            appCompatButton.perform(click());

            ViewInteraction appCompatEditText = onView(
                    withId(R.id.email));
            appCompatEditText.perform(scrollTo(), replaceText("shl455@ucsd.edu"), closeSoftKeyboard());

            ViewInteraction appCompatEditText2 = onView(
                    withId(R.id.password));
            appCompatEditText2.perform(scrollTo(), replaceText("c20070124"), closeSoftKeyboard());

            ViewInteraction appCompatButton2 = onView(
                    allOf(withId(R.id.sign_button), withText("LoginPage")));
            appCompatButton2.perform(scrollTo(), click());

        }
        catch (Exception e){}
        //after log in button is pressed, it takes some times to load the main_navigation_Test navigation page
        //and be ready to display contents
        //So the thread need to sleep for a short time
        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void logout() {
//        try {
//            // thread to sleep for 5000 milliseconds
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        //Given that the "shl455@ucsd.edu" account exists and is logged in
        login();

        //When the user goes to the account page
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.account),
                        withParent(allOf(withId(R.id.bb_bottom_bar_item_container),
                                withParent(withId(R.id.bb_bottom_bar_outer_container)))),
                        isDisplayed()));
        linearLayout.perform(click());

        //and hit log out
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.logout_button), withText("Log Out"), isDisplayed()));
        appCompatButton3.perform(click());

        //Then the welcome page is presented and shows the user to be logged out.
        ViewInteraction button = onView(
                allOf(withId(R.id.welcome_login),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

}