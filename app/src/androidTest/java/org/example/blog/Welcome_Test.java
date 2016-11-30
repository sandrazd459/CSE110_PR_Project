package org.example.blog;


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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Welcome_Test {
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
    public void welcomeTest() {
//        try {
//            // thread to sleep for 5000 milliseconds
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        //given the user is logged out and on the welcome page
        logout();
        //then the user will be able to see the login button on the main_navigation_Test page
        ViewInteraction button = onView(
                allOf(withId(R.id.welcome_login),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        //and the sign up button on the main_navigation_Test page
        ViewInteraction button2 = onView(
                allOf(withId(R.id.welcome_signup),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        //testing the layout of buttons on the main_navigation_Test page
        ViewInteraction linearLayout = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

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
