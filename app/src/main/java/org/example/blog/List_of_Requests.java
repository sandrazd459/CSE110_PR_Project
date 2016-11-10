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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.authentication.Constants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class List_of_Requests extends AppCompatActivity {

    private RecyclerView mBlogList;

    private DatabaseReference mDatabase;

    BottomBar mBottomBar;

    SearchView sv;

    ArrayList<Blog> list;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Request Posts");




        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setItemAnimator(new DefaultItemAnimator());

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.home) {
                    startActivity(new Intent(List_of_Requests.this, Main_navigation.class));
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
                                    startActivity(new Intent(List_of_Requests.this, List_of_Sells.class));
                                    return true;
                            }
                            return false;
                        }
                    });
                }
            }
        });


        sv = (SearchView) findViewById(R.id.mSearchBar);

        //print test array
        System.out.println("HELOOOOOOOOOOOOO");
        /*for (Blog b : list) {

            Log.d("DEST:", b.getDestination());
        }*/
        /*final Requests_Adapter myAdapter = new Requests_Adapter(this, list);
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
        */
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {

        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

        /*ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Blog post = dataSnapshot.getValue(Blog.class);
                System.out.println("DEST: " + post.getDestination());
                if(post.getDestination() != null){
                    list.add(post);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w(, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };
        mDatabase.addValueEventListener(postListener);*/

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> fbRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_list,
                BlogViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {
                viewHolder.setFrom(model.getStart());
                viewHolder.setTo(model.getDestination());
                String s = stringMonth(model.getMonth()) + " " + model.getDay() + ", " + model.getYear();
                viewHolder.setDate(s);
                viewHolder.setPrice(model.getPrice());
            }
        };
        mBlogList.setAdapter(fbRecyclerAdapter);
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

    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView mFrom;
        TextView mTo;
        TextView mDate;
        TextView mPric;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            this.mFrom = (TextView) itemView.findViewById(R.id.post_from);
            this.mTo = (TextView) itemView.findViewById(R.id.post_to);
            this.mDate = (TextView) itemView.findViewById(R.id.post_date);
            this.mPric = (TextView) itemView.findViewById(R.id.post_price);

        }

        public void setFrom(String fromText) {
            TextView post_from = (TextView) mView.findViewById(R.id.post_from);
            post_from.setText(fromText);
        }

        public void setTo(String toText) {
            TextView post_to = (TextView) mView.findViewById(R.id.post_to);
            post_to.setText(toText);
        }


        public void setDate(String dateText) {
            TextView post_date = (TextView) mView.findViewById(R.id.post_date);
            post_date.setText(dateText);
        }


        public void setPrice(String priceText) {
            TextView post_price = (TextView) mView.findViewById(R.id.post_price);
            post_price.setText(priceText);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            startActivity(new Intent(List_of_Requests.this, Request_Form.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public String stringMonth(int m){
        switch (m){
            case 1:
                return "Jan";
            case 2:
                return "Feb";
            case 3:
                return "Mar";
            case 4:
                return "Apr";
            case 5:
                return "May";
            case 6:
                return "Jun";
            case 7:
                return "Jul";
            case 8:
                return "Oct";
            case 9:
                return "Aug";
            case 10:
                return "Sep";
            case 11:
                return "Nov";
            case 12:
                return "Dec";
        }
        return "";
    }

}
