package org.example.blog;

import java.lang.*;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
public class request_post_activity_Test {

    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

    @Test
    public void rTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.welcome_login), withText("Log in"), isDisplayed()));
        appCompatButton.perform(click());

        //Because the firebase will remember your session, this test may fail if the user is currently log in
        //So run logout or disable the following code to solve the problem
        //#==================================================================#
        ViewInteraction appCompatEditText = onView(
                withId(R.id.email));
        appCompatEditText.perform(scrollTo(), replaceText("shl455@ucsd.edu"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                withId(R.id.password));
        appCompatEditText2.perform(scrollTo(), replaceText("c20070124"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.sign_button), withText("Login")));
        appCompatButton2.perform(scrollTo(), click());

        try {
            // thread to sleep for 3000 milliseconds
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e);
        }
        //#==================================================================#
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.requestList), withText("Request New Ride"),
                        withParent(allOf(withId(R.id.activity_main_navigation),
                                withParent(withId(R.id.bb_user_content_container)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

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
