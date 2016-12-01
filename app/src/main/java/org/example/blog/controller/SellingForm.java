package org.example.blog.controller;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.example.blog.R;
import org.example.blog.model.Post;

import java.util.Calendar;

public class SellingForm extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private DatabaseReference mDatabase;
    private EditText mStart;
    private EditText mDestination;
    private EditText mPrice;
    private EditText mAdditional;
    private Button mPostBtn;
    Button dateBtn;
    int year, month, day, finDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_post_activity);

        dateBtn = (Button) findViewById(R.id.dateBtn);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Sell Posts");

        mStart = (EditText) findViewById(R.id.startText);
        mDestination = (EditText) findViewById(R.id.destText);
        //mDate = (EditText) findViewById(R.id.dateText);
        mPrice = (EditText) findViewById(R.id.priceText);
        mAdditional = (EditText) findViewById(R.id.additText);
        mPostBtn = (Button) findViewById(R.id.postBtn);

        mPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });

        dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(SellingForm.this, SellingForm.this, year, month, day);
                datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePicker.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        finDate = (year * 10000) + ((month + 1) * 100) + dayOfMonth;
        ((TextView)(findViewById(R.id.dateBtn))).setText((month + 1) + "/" + dayOfMonth + "/" + year);
    }

    private void startPosting(){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String text_start = mStart.getText().toString().trim();
        String text_dest = mDestination.getText().toString().trim();
        String price = "$" + mPrice.getText().toString();

        String text_addit = mAdditional.getText().toString();

        if(!TextUtils.isEmpty(text_start) && !TextUtils.isEmpty(text_dest) && finDate != 0){

            Post tmp = new Post(uid, price, text_dest, text_start, text_addit, finDate);
            DatabaseReference newPost = mDatabase.push();
            newPost.setValue(tmp);
            startActivity(new Intent(SellingForm.this, LoadingSellPosts.class));
        }
        else {
            Toast.makeText(this,"Please Fill In Required Fields",Toast.LENGTH_SHORT).show();
        }
    }
}
