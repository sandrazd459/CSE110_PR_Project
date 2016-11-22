package org.example.blog;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by hankliu on 11/22/16.
 */

public class PostsLoader {
    DatabaseReference ref;
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

    public ArrayList<Post> getSortedReqPosts() {
        return sortByDate(reqPosts);
    }

    private ArrayList<Post> sortByDate(ArrayList<Post> arr){
        ArrayList<Post> copy = new ArrayList<>(arr);
        ArrayList<Post> sorted = new ArrayList<>();

        int currMinIndex = 0;
        int currMinDate = 0;
        int changingSize = copy.size();
        int max = copy.size();

        for(int i=0; i < max; i++){
            for(int j=0; j < changingSize; j++) {

                //find min
                Post tmp = copy.get(j);
                String tempDay = "" + tmp.getDay();
                String tempMonth = "" + tmp.getMonth();
                String tempYear = "" + tmp.getYear();

                //if its a single digit
                if (tmp.getDay() < 10) {
                    tempDay = "0" + tmp.getDay();
                }

                int tempDate = Integer.parseInt(tempYear + tempMonth + tempDay);
                System.out.println("ParseInt "+ tempDate);


                //at the first iteration
                if (j == 0) {
                    currMinDate = tempDate;
                    currMinIndex = 0;
                } else if (tempDate < currMinDate) {
                    currMinDate = tempDate;
                    currMinIndex = j;
                }

            }
            changingSize--;
            sorted.add(copy.get(currMinIndex));
            copy.remove(currMinIndex);

        }
        return sorted;
    }
}
