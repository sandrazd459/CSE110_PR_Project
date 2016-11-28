package org.example.blog;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;

public class Main_navigation extends AppCompatActivity {

    private Button offerBtn;
    private Button requestBtn;
    private Button profileSettingBtn;
    private Button logoutBtn;


    BottomBar mBottomBar;

    PostsLoader postsLoader;
    ArrayList<Post> sortedReq = new ArrayList<>();
    ArrayList<Post> sortedSell = new ArrayList<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        mBottomBar = BottomBar.attach(this, savedInstanceState);

        //set default highlight to be the home button
        mBottomBar.setDefaultTabPosition(1);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        postsLoader = new PostsLoader(mDatabase.child("Sell Posts"), mDatabase.child("Request Posts"));

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                // sorts list here
                sortedReq = postsLoader.getSortedReqPosts();
                sortedSell = postsLoader.getSortedSellPosts();

                if (menuItemId == R.id.lists) {
                    //sort the list by date

                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(Main_navigation.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.submenuSell:
                                    //sort the list by date

                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(Main_navigation.this, List_of_Sells.class);
                                    sBundle.putParcelableArrayList("sellArr", sortedSell);
                                    sBundle.putParcelableArrayList("reqArr", sortedReq);

                                    intentSell.putExtras(sBundle);
                                    startActivity(intentSell);
                                    return true;
                                case R.id.submenuReq:

                                    Bundle rBundle = new Bundle();
                                    Intent intentReq = new Intent(Main_navigation.this, List_of_Requests.class);
                                    rBundle.putParcelableArrayList("reqArr", sortedReq);
                                    rBundle.putParcelableArrayList("sellArr", sortedSell);

                                    intentReq.putExtras(rBundle);
                                    startActivity(intentReq);
                                    return true;
                            }
                            return false;
                        }
                    });
                }
                if (menuItemId == R.id.account) {
                    Bundle bundle = new Bundle();
                    Intent intentAccount = new Intent(Main_navigation.this, User_Account.class);
                    bundle.putParcelableArrayList("sellArr", sortedSell);
                    bundle.putParcelableArrayList("reqArr", sortedReq);
                    intentAccount.putExtras(bundle);
                    startActivity(intentAccount);
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                // sorts list here
                sortedReq = postsLoader.getSortedReqPosts();
                sortedSell = postsLoader.getSortedSellPosts();
                if (menuItemId == R.id.lists) {
                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(Main_navigation.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.submenuSell:
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(Main_navigation.this, List_of_Sells.class);
                                    sBundle.putParcelableArrayList("sellArr", sortedSell);
                                    sBundle.putParcelableArrayList("reqArr", sortedReq);
                                    intentSell.putExtras(sBundle);
                                    startActivity(intentSell);
                                    return true;
                                case R.id.submenuReq:
                                    Bundle rBundle = new Bundle();
                                    Intent intentReq = new Intent(Main_navigation.this, List_of_Requests.class);
                                    rBundle.putParcelableArrayList("reqArr", sortedReq);
                                    rBundle.putParcelableArrayList("sellArr", sortedSell);
                                    intentReq.putExtras(rBundle);
                                    startActivity(intentReq);
                                    return true;
                            }
                            return false;
                        }
                    });
                }
            }
        });

        offerBtn = (Button) findViewById(R.id.sellingList);

        offerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_navigation.this, Selling_Form.class));
            }
        });

        requestBtn = (Button) findViewById(R.id.requestList);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_navigation.this, Request_Form.class));
            }
        });

        profileSettingBtn = (Button) findViewById(R.id.account_setting);

        profileSettingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_navigation.this, UserProfileSetupController.class));
            }
        });

        logoutBtn = (Button) findViewById(R.id.logout_button);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Main_navigation.this, Welcome.class));
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main_navigation Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
