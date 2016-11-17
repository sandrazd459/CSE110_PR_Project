package org.example.blog;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileSetupController extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private EditText mUid, mUsername, mAdditional, frontPhoneNum, midPhoneNum, lastPhoneNum;
    private Button mUserProfileSetupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: modify the view here.
        setContentView(R.layout.user_profile_setup);

        // TODO: modify the firebase reference here.
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        // TODO: modify the item id here.
        mUsername = (EditText) findViewById(R.id.destText);
        mAdditional = (EditText) findViewById(R.id.additText);
        frontPhoneNum = (EditText) findViewById();
        midPhoneNum = (EditText) findViewById();
        lastPhoneNum = (EditText) findViewById();

        mUserProfileSetupBtn = (Button) findViewById(R.id.postBtn);
        mUserProfileSetupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }

    private void startPosting() {

        String textUid = mUid.getText().toString().trim();
        String textUsername = mUsername.getText().toString().trim();
        String textAdditional = mAdditional.getText().toString();
        String textFrontPhoneNum = frontPhoneNum.getText().toString();
        String textMidPhoneNum = midPhoneNum.getText().toString();
        String textLastPhoneNum = lastPhoneNum.getText().toString();

        User user = new User(textUid, textUsername, textAdditional, textFrontPhoneNum, textMidPhoneNum, textLastPhoneNum);

        if(!TextUtils.isEmpty(textUsername) && !TextUtils.isEmpty(textFrontPhoneNum)
                && !TextUtils.isEmpty(textMidPhoneNum) && !TextUtils.isEmpty(textLastPhoneNum)){
            DatabaseReference newUser = mDatabase.push();
            newUser.setValue(user);
            //TODO bug:for now send to main_page to get the firebase list again
            startActivity(new Intent(UserProfileSetupController.this, Main_navigation.class));
        }
        else {
            Toast.makeText(this,"Please Fill In Required Fields",Toast.LENGTH_SHORT).show();
        }
    }
}
