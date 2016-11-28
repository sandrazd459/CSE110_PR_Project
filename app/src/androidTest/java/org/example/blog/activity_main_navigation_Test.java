package org.example.blog;


import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by julianlin on 11/25/16.
 */

@RunWith(AndroidJUnit4.class)
public class activity_main_navigation_Test {

    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

    @Test
    public void mainTest() {
        //This test is to be run when loggout
        //login to the main page with email and password
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
                allOf(withId(R.id.sign_button), withText("Login")));
        appCompatButton2.perform(scrollTo(), click());
    }

    public void testTheSellingListButton() {
        //check if it exists
        ViewInteraction button = onView(
                allOf(withId(R.id.sellingList),
                        childAtPosition(
                                allOf(withId(R.id.activity_main_navigation),
                                        childAtPosition(
                                                withId(R.id.bb_user_content_container),
                                                0)),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    public void testTheRequestListButton() {
        ViewInteraction button2 = onView(
                allOf(withId(R.id.requestList),
                        childAtPosition(
                                allOf(withId(R.id.activity_main_navigation),
                                        childAtPosition(
                                                withId(R.id.bb_user_content_container),
                                                0)),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    public void testTheSettingButton() {
        ViewInteraction button3 = onView(
                allOf(withId(R.id.account_setting),
                        childAtPosition(
                                allOf(withId(R.id.activity_main_navigation),
                                        childAtPosition(
                                                withId(R.id.bb_user_content_container),
                                                0)),
                                2),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));
    }

    public void testTheLogoutButton(){
        ViewInteraction button4 = onView(
                allOf(withId(R.id.logout_button),
                        childAtPosition(
                                allOf(withId(R.id.activity_main_navigation),
                                        childAtPosition(
                                                withId(R.id.bb_user_content_container),
                                                0)),
                                3),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));
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
