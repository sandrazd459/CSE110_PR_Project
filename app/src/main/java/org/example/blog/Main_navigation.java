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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;

public class Main_navigation extends AppCompatActivity {

    private Button offerBtn;
    private Button requestBtn;
    private Button profileSettingBtn;
    private Button logoutBtn;

    private DatabaseReference mDataBase;

    BottomBar mBottomBar;

    ArrayList<Post> req = new ArrayList<>();
    ArrayList<Post> sell = new ArrayList<>();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);

        mDataBase = FirebaseDatabase.getInstance().getReference();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println("uid:" + uid);

        mBottomBar = BottomBar.attach(this, savedInstanceState);

        //set default highlight to be the home button
        mBottomBar.setDefaultTabPosition(1);

        mDataBase.child("Request Posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post post = child.getValue(Post.class);
                    if (post.getDestination() != null) {
                        //call another function
                        createBlog(req, post.getUsername(), post.getUid(), post.getStart(),post.getDestination(), post.getPrice(),post.getAdditional(), post.getMonth(), post.getDay(),post.getYear() );
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mDataBase.child("Sell Posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post post = child.getValue(Post.class);
                    if (post.getDestination() != null) {
                        //call another function
                        createBlog(sell, post.getUsername(), post.getUid(), post.getStart(),post.getDestination(), post.getPrice(),post.getAdditional(), post.getMonth(), post.getDay(),post.getYear() );
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.lists){
                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(Main_navigation.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.submenuSell:
                                    //mDataBase = mDataBase.child("Sell Posts");
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(Main_navigation.this, List_of_Sells.class);
                                    sBundle.putParcelableArrayList("sellArr", sell);
                                    sBundle.putParcelableArrayList("reqArr", req);

                                    intentSell.putExtras(sBundle);
                                    startActivity(intentSell);
                                    return true;
                                case R.id.submenuReq:
                                    Bundle rBundle = new Bundle();
                                    Intent intentReq = new Intent(Main_navigation.this, List_of_Requests.class);
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
                if (menuItemId == R.id.account) {
                    Bundle bundle = new Bundle();
                    Intent intentAccount = new Intent(Main_navigation.this, User_Account.class);
                    bundle.putParcelableArrayList("sellArr", sell);
                    bundle.putParcelableArrayList("reqArr", req);
                    intentAccount.putExtras(bundle);
                    startActivity(intentAccount);
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if(menuItemId == R.id.lists){
                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(Main_navigation.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch(item.getItemId()){
                                case R.id.submenuSell:
                                    //mDataBase = mDataBase.child("Sell Posts");
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(Main_navigation.this, List_of_Sells.class);
                                    sBundle.putParcelableArrayList("sellArr", sell);
                                    sBundle.putParcelableArrayList("reqArr", req);

                                    intentSell.putExtras(sBundle);
                                    startActivity(intentSell);
                                    return true;
                                case R.id.submenuReq:
                                    Bundle rBundle = new Bundle();
                                    Intent intentReq = new Intent(Main_navigation.this, List_of_Requests.class);
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
            }
        });

        offerBtn = (Button) findViewById(R.id.sellingList);

        offerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoSellList = new Intent(Main_navigation.this, Selling_Form.class);
                startActivity(gotoSellList);
            }
        });

        requestBtn = (Button) findViewById(R.id.requestList);

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotorequestList = new Intent(Main_navigation.this, Request_Form.class);
                startActivity(gotorequestList);
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
    public void createBlog(ArrayList<Post> array, String username, String uid, String start, String dest, String pric, String add, int m, int d, int y) {
        Post newPost = new Post();
        newPost.setUsername(username);
        newPost.setUid(uid);
        newPost.setDestination(dest);
        newPost.setStart(start);
        newPost.setPrice(pric);
        newPost.setAdditional(add);
        newPost.setMonth(m);
        newPost.setDay(d);
        newPost.setYear(y);

        if (array == req) {
            req.add(newPost);
        } else {
            sell.add(newPost);
        }
    }
}
