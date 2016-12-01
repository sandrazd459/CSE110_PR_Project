package org.example.blog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class UserPosts extends AppCompatActivity {
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
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        for(int i = 0; i<list.size();i++){
            if(!list.get(i).getUid().equals(uid)) {
                list.remove(i);
                i--;
            }
        }

        final ListAdapter myAdapter = new ListAdapter(this, list);
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
