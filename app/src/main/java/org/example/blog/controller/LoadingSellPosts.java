package org.example.blog.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.example.blog.model.Post;
import org.example.blog.model.PostsLoader;

import java.util.ArrayList;

/**
 * Created by Alan Beas on 11/28/2016.
 */


public class LoadingSellPosts extends Activity {

    ArrayList<Post> sortedReq = new ArrayList<>();
    ArrayList<Post> sortedSell = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        launchProgressDialog(mDatabase.child("Sell Posts"), mDatabase.child("Request Posts"));
    }

    public void launchProgressDialog(final DatabaseReference dbSell,final DatabaseReference dbReq) {
        final ProgressDialog ringProgressDialog = ProgressDialog.show(LoadingSellPosts.this, "Loading List ...", "Please wait ...", true);
        ringProgressDialog.setCancelable(false);
        final PostsLoader postsLoader = new PostsLoader(dbSell,dbReq);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // write your time consuming task...
                    // Let the progress ring for 3 seconds...
                    Thread.sleep(1000);
                    sortedReq = postsLoader.getSortedReqPosts();
                    sortedSell = postsLoader.getSortedSellPosts();
                    Thread.sleep(500);
                    Bundle sBundle = new Bundle();
                    Intent intent = new Intent(LoadingSellPosts.this, ListOfSells.class);
                    sBundle.putParcelableArrayList("sellArr", sortedSell);
                    sBundle.putParcelableArrayList("reqArr", sortedReq);

                    intent.putExtras(sBundle);
                    Thread.sleep(500);
                    startActivity(intent);

                } catch (Exception e) {

                }
                ringProgressDialog.dismiss();
            }
        }).start();
    }
}
