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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;


public class User_Posts extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private RecyclerView mBlogList;

    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);


        Bundle b = this.getIntent().getExtras();

        final ArrayList<Post> rList = b.getParcelableArrayList("reqArr");
        final ArrayList<Post> sList = b.getParcelableArrayList("sellArr");
        final ArrayList<Post> list;
        String option = b.getString("option");

        sv = (SearchView) findViewById(R.id.mSearchBar);
        mBlogList = (RecyclerView) findViewById(R.id.recyclerList);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setItemAnimator(new DefaultItemAnimator());

        if(option.equals("sell"))
            list = sList;
        else
            list = rList;

        //filter to only posts with connected uid
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        String uid = user.getUid();

        for(int i = 0; i<list.size();i++){
            if(!list.get(i).getUid().equals(uid)) {
                list.remove(i);
                i--;
            }
        }

        final List_Adapter myAdapter = new List_Adapter(this, list);
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
}
