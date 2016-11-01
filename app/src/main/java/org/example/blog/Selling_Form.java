package org.example.blog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Selling_Form extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private EditText mStart;
    private EditText mDestination;
    private EditText mDate;
    private EditText mPrice;
    private EditText mAdditional;

    private Button mPostBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_post_activity);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Sell Posts");


        mStart = (EditText) findViewById(R.id.startText);
        mDestination = (EditText) findViewById(R.id.destText);
        mDate = (EditText) findViewById(R.id.dateText);
        mPrice = (EditText) findViewById(R.id.priceText);
        mAdditional = (EditText) findViewById(R.id.additText);
        mPostBtn = (Button) findViewById(R.id.postBtn);


        mPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }


    private void startPosting(){

        String text_start = mStart.getText().toString().trim();
        String text_dest = mDestination.getText().toString().trim();
        String text_date = mDate.getText().toString().trim();
        String _price = mPrice.getText().toString();
        String text_addit = mAdditional.getText().toString();

        if(!TextUtils.isEmpty(text_start) && !TextUtils.isEmpty(text_dest) && !TextUtils.isEmpty(text_date)){

            DatabaseReference newPost = mDatabase.push();
            newPost.child("Start").setValue(text_start);
            newPost.child("Destination").setValue(text_dest);
            newPost.child("Date").setValue(text_date);
            newPost.child("Price").setValue(_price);
            newPost.child("Additional").setValue(text_addit);


            startActivity(new Intent(Selling_Form.this, List_of_Sells.class));
        }

    }
}
