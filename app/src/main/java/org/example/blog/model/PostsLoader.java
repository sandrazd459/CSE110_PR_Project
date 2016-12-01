package org.example.blog.model;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by hankliu on 11/22/16.
 */

public class PostsLoader {
    ArrayList<Post> sellPosts = new ArrayList<>();
    ArrayList<Post> reqPosts = new ArrayList<>();

    public PostsLoader(DatabaseReference sellRef, DatabaseReference reqRef) {
        sellRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post post = child.getValue(Post.class);
                    if (post.getDestination() != null) {
                        //call another function
                        sellPosts.add(post);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        reqRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Post post = child.getValue(Post.class);
                    if (post.getDestination() != null) {
                        //call another function
                        reqPosts.add(post);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public ArrayList<Post> getSortedSellPosts() {
        return sortByDate(sellPosts);
    }

    public ArrayList<Post> getSortedReqPosts() { return sortByDate(reqPosts);}

    private ArrayList<Post> sortByDate(ArrayList<Post> arr) {
        ArrayList<Post> sorted = new ArrayList<>();
        for (Post newPost : arr) {
            if (sorted.size() == 0) {
                sorted.add(newPost);
            } else {
                for (int i = 0; i <= sorted.size(); i++) {
                    if (i == sorted.size()) {
                        sorted.add(i, newPost);
                        break;
                    }
                    if (newPost.getDate() <= sorted.get(i).getDate()) {
                        sorted.add(i, newPost);
                        break;
                    }
                }
            }
        }
        return sorted;
    }
}
