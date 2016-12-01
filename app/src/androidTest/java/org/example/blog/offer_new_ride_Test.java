package org.example.blog;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.example.blog.controller.Welcome;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
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

/**
 * Created by julianlin on 11/25/16.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class offer_new_ride_Test {

    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

//    @ClassRule
//    public static DisableAnimationsRule disableAnimationsRule = new DisableAnimationsRule();

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
    public void offer_new_ride_test() {
//        try {
//            // thread to sleep for 5000 milliseconds
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        //given the user is logged in and on the main_navigation_Test navigation page
        login();
        //when the user press offer New Ride button
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.sellingList), withText("Offer New Ride"),
                        withParent(allOf(withId(R.id.activity_main_navigation),
                                withParent(withId(R.id.bb_user_content_container)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        //then the offer ride page will be present and the user will be able to offer new ride
        ViewInteraction editText = onView(
                allOf(withId(R.id.startText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main),
                                        0),
                                0),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.destText),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.activity_main),
                                        0),
                                1),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction button = onView(
                allOf(withId(R.id.dateBtn),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                1),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.postBtn),
                        childAtPosition(
                                allOf(withId(R.id.activity_main),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        logout();
    }

    public void logout(){
        try {
            ViewInteraction linearLayout = onView(
                    allOf(withId(R.id.account),
                            withParent(allOf(withId(R.id.bb_bottom_bar_item_container),
                                    withParent(withId(R.id.bb_bottom_bar_outer_container)))),
                            isDisplayed()));
            linearLayout.perform(click());

            ViewInteraction appCompatButton3 = onView(
                    allOf(withId(R.id.logout_button), withText("Log Out"), isDisplayed()));
            appCompatButton3.perform(click());
        }
        catch (Exception e){}
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
