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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

    @Test
    public void mainActivityTest() {
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

        ViewInteraction viewGroup = onView(
                allOf(withId(R.id.action_bar),
                        childAtPosition(
                                allOf(withId(R.id.action_bar_container),
                                        childAtPosition(
                                                withId(R.id.decor_content_parent),
                                                0)),
                                0),
                        isDisplayed()));
        viewGroup.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withText("Main Navigation"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Main Navigation")));

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

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.lists),
                        childAtPosition(
                                allOf(withId(R.id.bb_bottom_bar_item_container),
                                        childAtPosition(
                                                withId(R.id.bb_bottom_bar_outer_container),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.home),
                        childAtPosition(
                                allOf(withId(R.id.bb_bottom_bar_item_container),
                                        childAtPosition(
                                                withId(R.id.bb_bottom_bar_outer_container),
                                                0)),
                                1),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.account),
                        childAtPosition(
                                allOf(withId(R.id.bb_bottom_bar_item_container),
                                        childAtPosition(
                                                withId(R.id.bb_bottom_bar_outer_container),
                                                0)),
                                2),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.activity_main_navigation),
                        childAtPosition(
                                allOf(withId(R.id.bb_user_content_container),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));

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
