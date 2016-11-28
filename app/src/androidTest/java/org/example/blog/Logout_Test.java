package org.example.blog;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
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

    @Test
    public void logout1() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.welcome_login), withText("Log in"), isDisplayed()));
        appCompatButton.perform(click());

//        ViewInteraction appCompatEditText = onView(
//                withId(R.id.email));
//        appCompatEditText.perform(scrollTo(), replaceText("shl455@ucsd.edu"), closeSoftKeyboard());
//
//        ViewInteraction appCompatEditText2 = onView(
//                withId(R.id.password));
//        appCompatEditText2.perform(scrollTo(), replaceText("c20070124"), closeSoftKeyboard());
//
//        ViewInteraction appCompatButton2 = onView(
//                allOf(withId(R.id.sign_button), withText("Login")));
//        appCompatButton2.perform(scrollTo(), click());

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

}
