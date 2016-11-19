package org.example.blog;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.core.Tag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfileSetupController extends AppCompatActivity {

    private DatabaseReference mDatabaseRef;
    private EditText mUsername, mAdditional, frontPhoneNum, midPhoneNum, lastPhoneNum;
    private Button mUserProfileSetupBtn;
    private static final String TAG = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.user_profile_setup);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users");

        mUsername = (EditText) findViewById(R.id.user_name);
        mAdditional = (EditText) findViewById(R.id.additional_info);
        frontPhoneNum = (EditText) findViewById(R.id.front_num);
        midPhoneNum = (EditText) findViewById(R.id.mid_num);
        lastPhoneNum = (EditText) findViewById(R.id.last_num);

        mUserProfileSetupBtn = (Button) findViewById(R.id.user_create);
        mUserProfileSetupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPosting();
            }
        });
    }

    private void startPosting() {

        String textUsername = mUsername.getText().toString().trim();
        String textAdditional = mAdditional.getText().toString();
        String textFrontPhoneNum = frontPhoneNum.getText().toString();
        String textMidPhoneNum = midPhoneNum.getText().toString();
        String textLastPhoneNum = lastPhoneNum.getText().toString();

        // validation for forms.
        if(!TextUtils.isEmpty(textUsername) && !TextUtils.isEmpty(textFrontPhoneNum)
                && !TextUtils.isEmpty(textMidPhoneNum) && !TextUtils.isEmpty(textLastPhoneNum)){

            User user = new User(textUsername, textAdditional, textFrontPhoneNum, textMidPhoneNum, textLastPhoneNum);
            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            DatabaseReference newUser = mDatabaseRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            newUser.setValue(user);
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(textUsername)
                    .build();

            firebaseUser.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "User profile updated.");
                            }
                        }
                    });

            //TODO bug:for now send to main_page to get the firebase list again
            startActivity(new Intent(UserProfileSetupController.this, Main_navigation.class));
        }
        else {
            Toast.makeText(this,"Please Fill In Required Fields",Toast.LENGTH_SHORT).show();
        }
    }
}
