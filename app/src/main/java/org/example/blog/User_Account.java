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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private DatabaseReference mDatabaseRef;

    TextView userEmailView;
    TextView userNameView;
    TextView phoneView;
    TextView additionalInfoView;

    BottomBar mBottomBar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_account);

        //initialize auth
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        Bundle b = this.getIntent().getExtras();
        final ArrayList<Post> req = b.getParcelableArrayList("reqArr");
        final ArrayList<Post> sell = b.getParcelableArrayList("sellArr");

        userEmailView = (TextView)findViewById(R.id.user_email);
        userNameView = (TextView)findViewById(R.id.user_name);
        phoneView = (TextView)findViewById(R.id.user_phone);
        additionalInfoView = (TextView)findViewById(R.id.additional_info);

        final Button mLogoutBtn = (Button) findViewById(R.id.logout_button);
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

        ValueEventListener userListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                // [START_EXCLUDE]
                userNameView.setText(user.getUsername());
                userEmailView.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                String phone = "(" + user.getFrontPhoneNumber()+ ")-" +user.getMidPhoneNumber()
                        + "-" + user.getLastPhoneNumber();
                phoneView.setText(phone);
                additionalInfoView.setText(user.getAdditional());
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabaseRef.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(userListener);


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
