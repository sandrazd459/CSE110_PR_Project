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
public class senario_test {

    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

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

    @Test
    public void senario_test() {
        //given we are logged out
        logout();

        //when a user enter proper account name and password ,then press the log in button
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

        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }

        //then he will come to the main page
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
        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }
        //when the user hit request new ride button on the main navigation page

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.requestList), withText("Request New Ride"),
                        withParent(allOf(withId(R.id.activity_main_navigation),
                                withParent(withId(R.id.bb_user_content_container)))),
                        isDisplayed()));
        appCompatButton3.perform(click());

        //then the user is going to make a post
        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.startText), isDisplayed()));
        appCompatEditText3.perform(replaceText("UCSD"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.destText), isDisplayed()));
        appCompatEditText4.perform(replaceText("Irvine"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.dateBtn), withText("month/day/year"), isDisplayed()));
        appCompatButton4.perform(click());


        //when the user finish posting and hit Submit post button
        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button1), withText("OK")));
        appCompatButton9.perform(scrollTo(), click());

        //then he/she will be able to view the post he/she made
        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.postBtn), withText("Submit Post"),
                        withParent(allOf(withId(R.id.activity_main),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton10.perform(click());

        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }

        //when the user pressed account button
        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.account),
                        withParent(allOf(withId(R.id.bb_bottom_bar_item_container),
                                withParent(withId(R.id.bb_bottom_bar_outer_container)))),
                        isDisplayed()));
        linearLayout.perform(click());

        //and press log out button
        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.logout_button), withText("Log Out"), isDisplayed()));
        appCompatButton11.perform(click());

        //then the user will be back to welcome page
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
