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

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;


public class ListOfSells extends AppCompatActivity {

    private RecyclerView mBlogList;

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
                if (menuItemId == R.id.home) {
                    startActivity(new Intent(ListOfSells.this, MainNavigation.class));
                }
                if (menuItemId == R.id.account) {
                    Bundle bundle = new Bundle();
                    Intent intentAccount = new Intent(ListOfSells.this, UserAccount.class);
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
                    PopupMenu popupMenu = new PopupMenu(ListOfSells.this, listView);
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                //TODO: BUG HERE solution send list around all activities
                                case R.id.submenuReq:
                                    Bundle bundle = new Bundle();
                                    Intent intentSell = new Intent(ListOfSells.this, ListOfRequests.class);
                                    bundle.putParcelableArrayList("sellArr", sList);
                                    bundle.putParcelableArrayList("reqArr", rList);
                                    intentSell.putExtras(bundle);
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
        final ListAdapter myAdapter = new ListAdapter(this, sList);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add){
            startActivity(new Intent(ListOfSells.this, SellingForm.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
