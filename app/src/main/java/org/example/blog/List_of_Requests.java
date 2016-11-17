package org.example.blog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;


public class List_of_Requests extends AppCompatActivity {

    private RecyclerView mBlogList;

    //private DatabaseReference mDatabase;

    BottomBar mBottomBar;

    SearchView sv;

    /*Bundle b = this.getIntent().getExtras();
    ArrayList<Post> transferReqList = b.getParcelableArrayList("reqArr");
    ArrayList<Post> transferSelList = b.getParcelableArrayList("sellArr");*/



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        Bundle bun = this.getIntent().getExtras();
        final ArrayList<Post> rList = bun.getParcelableArrayList("reqArr");
        final ArrayList<Post> sList = bun.getParcelableArrayList("sellArr");

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.home) {
                    startActivity(new Intent(List_of_Requests.this, Main_navigation.class));
                }
                if (menuItemId == R.id.account) {
                    Bundle bundle = new Bundle();
                    Intent intentAccount = new Intent(List_of_Requests.this, User_Account.class);
                    bundle.putParcelableArrayList("sellArr", sList);
                    bundle.putParcelableArrayList("reqArr", rList);
                    intentAccount.putExtras(bundle);
                    startActivity(intentAccount);
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.lists) {
                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(List_of_Requests.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.submenuSell:
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(List_of_Requests.this, List_of_Sells.class);
                                    sBundle.putParcelableArrayList("sellArr", sList);
                                    sBundle.putParcelableArrayList("reqArr", rList);
                                    intentSell.putExtras(sBundle);
                                    startActivity(intentSell);
                                    return true;
                            }
                            return false;
                        }
                    });
                }
            }
        });

        sv = (SearchView) findViewById(R.id.mSearchBar);
        mBlogList = (RecyclerView) findViewById(R.id.recyclerList);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setItemAnimator(new DefaultItemAnimator());
        final List_Adapter myAdapter = new List_Adapter(this, rList);
        mBlogList.setAdapter(myAdapter);
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {

        super.onStart(); // ATTENTION: This was auto-generated to implement the App Indexing API.
                         // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();



        /*
        FirebaseRecyclerAdapter<Post, BlogViewHolder> fbRecyclerAdapter = new FirebaseRecyclerAdapter<Post, BlogViewHolder>(
                Post.class,
                R.layout.blog_list,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Post model, int position) {
                viewHolder.setFrom(model.getStart());
                viewHolder.setTo(model.getDestination());
                String s = stringMonth(model.getMonth()) + " " + model.getDay() + ", " + model.getYear();
                viewHolder.setDate(s);
                viewHolder.setPrice(model.getPrice());
            }
        };
        mBlogList.setAdapter(fbRecyclerAdapter);
        */

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("List_of_Requests Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            //Bundle tBundle = new Bundle();
            Intent intentSell = new Intent(List_of_Requests.this, Request_Form.class);
            /*tBundle.putParcelableArrayList("sellArr", transferSelList);
            tBundle.putParcelableArrayList("reqArr", transferReqList);
            intentSell.putExtras(tBundle);*/
            startActivity(intentSell);
        }
        return super.onOptionsItemSelected(item);
    }
    public ArrayList<Post> fakeList(){
        ArrayList<Post> n = new ArrayList<>();
        Post newPost;

        newPost=new Post();
        newPost.setDestination("NIGGAS");
        newPost.setStart("BYE");
        newPost.setPrice("23");
        newPost.setAdditional("hello");
        newPost.setMonth(3);
        newPost.setDay(4);
        newPost.setYear(2017);
        n.add(newPost);

        newPost=new Post();
        newPost.setDestination("HI");
        newPost.setStart("BYE");
        newPost.setPrice("23");
        newPost.setAdditional("hello");
        newPost.setMonth(3);
        newPost.setDay(4);
        newPost.setYear(2017);
        n.add(newPost);

        newPost=new Post();
        newPost.setDestination("whites");
        newPost.setStart("BYE");
        newPost.setPrice("23");
        newPost.setAdditional("hello");
        newPost.setMonth(3);
        newPost.setDay(4);
        newPost.setYear(2017);
        n.add(newPost);

        return n;
    }
}
