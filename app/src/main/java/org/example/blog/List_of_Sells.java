package org.example.blog;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;


public class List_of_Sells extends AppCompatActivity {

    private RecyclerView mBlogList;

    //private DatabaseReference mDatabase;

    BottomBar mBottomBar;

    SearchView sv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);

        Bundle b = this.getIntent().getExtras();

        final ArrayList<Post> rList = b.getParcelableArrayList("reqArr");
        final ArrayList<Post> sList = b.getParcelableArrayList("sellArr");

        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItemsFromMenu(R.menu.bottombar_menu, new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.lists) {
                    View listView = findViewById(R.id.lists);
                    PopupMenu popupMenu = new PopupMenu(List_of_Sells.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                //TODO: BUG HERE solution send list around all activities
                                case R.id.submenuReq:
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(List_of_Sells.this, List_of_Requests.class);
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
                if (menuItemId == R.id.home) {
                    startActivity(new Intent(List_of_Sells.this, Main_navigation.class));
                }
                if (menuItemId == R.id.account) {
                    Bundle bundle = new Bundle();
                    Intent intentAccount = new Intent(List_of_Sells.this, User_Account.class);
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
                    PopupMenu popupMenu = new PopupMenu(List_of_Sells.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                //TODO: BUG HERE solution send list around all activities
                                case R.id.submenuReq:
                                    Bundle sBundle = new Bundle();
                                    Intent intentSell = new Intent(List_of_Sells.this, List_of_Requests.class);
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
                if (menuItemId == R.id.home) {
                    startActivity(new Intent(List_of_Sells.this, Main_navigation.class));
                }
                if (menuItemId == R.id.account) {
                    Bundle bundle = new Bundle();
                    Intent intentAccount = new Intent(List_of_Sells.this, User_Account.class);
                    bundle.putParcelableArrayList("sellArr", sList);
                    bundle.putParcelableArrayList("reqArr", rList);
                    intentAccount.putExtras(bundle);
                    startActivity(intentAccount);
                }
            }
        });

        sv = (SearchView) findViewById(R.id.mSearchBar);
        mBlogList = (RecyclerView) findViewById(R.id.recyclerList);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setItemAnimator(new DefaultItemAnimator());
        final List_Adapter myAdapter = new List_Adapter(this, sList);
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
    }



    @Override
    protected void onStart() {
        super.onStart();
    }



    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setFrom(String fromText){
            TextView post_from = (TextView)mView.findViewById(R.id.post_from);
            post_from.setText(fromText);
        }

        public void setTo(String toText){
            TextView post_to = (TextView)mView.findViewById(R.id.post_to);
            post_to.setText(toText);
        }
        /*public void setDate(String dateText){
            TextView post_date = (TextView)mView.findViewById(R.id.post_date);
            post_date.setText(dateText);
        }*/
        public void setPrice(String priceText){
            TextView post_price = (TextView)mView.findViewById(R.id.post_price);
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

        if(item.getItemId() == R.id.action_add){
            startActivity(new Intent(List_of_Sells.this, Selling_Form.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
