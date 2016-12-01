package org.example.blog;

//test the login feature
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

/**
 * Created by julianlin on 11/25/16.
 */

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Login_test {

    @Rule
    public ActivityTestRule<Welcome> mActivityTestRule = new ActivityTestRule<>(Welcome.class);

//    @ClassRule
//    public static DisableAnimationsRule disableAnimationsRule = new DisableAnimationsRule();

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
    public void test_Login() {
//        try {
//            // thread to sleep for 5000 milliseconds
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        //given the user is logged out and on the welcome page
        logout();

        //when the user hit log in button, enter a valid combination of username and password, then hit login
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

        //Then user will be on main_navigation_Test navigation page
        ViewInteraction relativeLayout = onView(
                allOf(withId(R.id.activity_main_navigation),
                        isDisplayed()));
        relativeLayout.check(matches(isDisplayed()));
    }

}
