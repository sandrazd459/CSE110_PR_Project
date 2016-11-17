package org.example.blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.util.Log;
import android.widget.Button;
import android.support.annotation.NonNull;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


/**
 * Created by Sandra on 11/10/2016.
 */

public class User_Account extends AppCompatActivity{
    private static final String TAG = "Email and Password";
    //for login function
    private Button mLogoutBtn;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;

    BottomBar mBottomBar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        //initialize auth
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String email = mAuth.getCurrentUser().getEmail();
        TextView userEmailView = (TextView)findViewById(R.id.user_email);
        userEmailView.setText(email);

        Bundle b = this.getIntent().getExtras();
        final ArrayList<Post> req = b.getParcelableArrayList("reqArr");
        final ArrayList<Post> sell = b.getParcelableArrayList("sellArr");

        //trig logout button
        mLogoutBtn = (Button) findViewById(R.id.logout_button);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        //bottom navigation bar
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.lists) {
                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(User_Account.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.submenuSell:
                                    //mDataBase = mDataBase.child("Sell Posts");
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(User_Account.this, List_of_Sells.class);
                                    sBundle.putParcelableArrayList("sellArr", sell);
                                    sBundle.putParcelableArrayList("reqArr", req);

                                    intentSell.putExtras(sBundle);
                                    startActivity(intentSell);
                                    return true;
                                case R.id.submenuReq:
                                    Bundle rBundle = new Bundle();
                                    Intent intentReq = new Intent(User_Account.this, List_of_Requests.class);
                                    rBundle.putParcelableArrayList("reqArr", req);
                                    rBundle.putParcelableArrayList("sellArr", sell);

                                    //intentReq.putParcelableArrayListExtra("custom_data_list", req);
                                    intentReq.putExtras(rBundle);
                                    startActivity(intentReq);
                                    return true;
                            }
                            return false;
                        }
                    });
                }
                if(menuItemId == R.id.home){
                    startActivity(new Intent(User_Account.this, Main_navigation.class));
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.lists) {
                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(User_Account.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.submenuSell:
                                    //mDataBase = mDataBase.child("Sell Posts");
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(User_Account.this, List_of_Sells.class);
                                    sBundle.putParcelableArrayList("sellArr", sell);
                                    sBundle.putParcelableArrayList("reqArr", req);

                                    intentSell.putExtras(sBundle);
                                    startActivity(intentSell);
                                    return true;
                                case R.id.submenuReq:
                                    Bundle rBundle = new Bundle();
                                    Intent intentReq = new Intent(User_Account.this, List_of_Requests.class);
                                    rBundle.putParcelableArrayList("reqArr", req);
                                    rBundle.putParcelableArrayList("sellArr", sell);

                                    //intentReq.putParcelableArrayListExtra("custom_data_list", req);
                                    intentReq.putExtras(rBundle);
                                    startActivity(intentReq);
                                    return true;
                            }
                            return false;
                        }
                    });
                }
                if(menuItemId == R.id.home){
                    startActivity(new Intent(User_Account.this, Main_navigation.class));
                }
            }
        });//end of bottom bar

        //auth state listener
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // [START_EXCLUDE]
                //updateUI(user);
                // [END_EXCLUDE]
            }
        };
        // [END auth_state_listener]
    }

    private void logout() {
        mAuth.signOut();
        startActivity(new Intent(User_Account.this, Welcome.class));
    }

    //add listener
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    //remove listener
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
