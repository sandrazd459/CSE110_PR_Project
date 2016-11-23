package org.example.blog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.Calendar;

public class Request_Form extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

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
        setContentView(R.layout.request_post_activity);

        dateBtn = (Button) findViewById(R.id.dateBtn);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Request Posts");

        mStart = (EditText) findViewById(R.id.startText);
        mDestination = (EditText) findViewById(R.id.destText);
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

                DatePickerDialog datePicker = new DatePickerDialog(Request_Form.this, Request_Form.this, year, month, day);
                datePicker.show();
            }
        });
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        finDate = (year * 10000) + ((month + 1) * 100) + dayOfMonth;
        ((TextView)(findViewById(R.id.dateBtn))).setText((month + 1) + "/" + dayOfMonth + "/" + year);
    }

    private void startPosting() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String text_start = mStart.getText().toString().trim();
        String text_dest = mDestination.getText().toString().trim();
        String price = mPrice.getText().toString();
        String text_addit = mAdditional.getText().toString();

        Post tmp = new Post(uid, price, text_dest, text_start, text_addit, finDate);

        if(!TextUtils.isEmpty(text_start) && !TextUtils.isEmpty(text_dest) && finDate != 0 ){

            DatabaseReference newPost = mDatabase.push();
            newPost.setValue(tmp);
            //TODO bug:for now send to main_page to get the firebase list again
            startActivity(new Intent(Request_Form.this, Main_navigation.class));
        }
        else{
            Toast.makeText(this,"Please Fill In Required Fields",Toast.LENGTH_SHORT).show();
        }
    }
}
