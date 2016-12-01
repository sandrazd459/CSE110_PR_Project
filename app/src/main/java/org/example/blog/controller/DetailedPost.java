package org.example.blog.controller;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.example.blog.R;
import org.example.blog.model.User;

/**
 * Created by Alan Beas on 11/13/2016.
 */

public class DetailedPost extends Activity {
    final String TAG = "User Profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detailed_post);

        Bundle bun = this.getIntent().getExtras();
        String uid = bun.getString("uid");

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                ((TextView)findViewById(R.id.post_username)).setText(user.getUsername());
                ((TextView)findViewById(R.id.post_phone_num)).setText(user.getStringPhoneNumber());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        userRef.addValueEventListener(postListener);

        ((TextView)findViewById(R.id.post_departure)).setText(bun.getString("start"));
        ((TextView)findViewById(R.id.post_destination)).setText(bun.getString("dest"));
        ((TextView)findViewById(R.id.post_price)).setText(bun.getString("price"));
        ((TextView)findViewById(R.id.post_additional)).setText(bun.getString("addit"));
        ((TextView)findViewById(R.id.post_date)).setText(bun.getString("date"));

    }

    public void goBack(View view){
        super.onBackPressed();
    }

}
