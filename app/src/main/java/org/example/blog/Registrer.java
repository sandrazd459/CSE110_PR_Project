package org.example.blog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class Registrer extends AppCompatActivity {

    private DatabaseReference mDatabase;

    //private EditText mUserName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordConfirm;
    private Button mSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrer);


        //mDatabase = FirebaseDatabase.getInstance().getReference().child("Sell Posts");


        mEmail = (EditText) findViewById(R.id.registrer_email);
        mPassword = (EditText) findViewById(R.id.registrer_password);
        mPasswordConfirm = (EditText) findViewById(R.id.registrer_password_confirm);
        mSignupBtn = (Button) findViewById(R.id.registrer_button);

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignin();
            }
        });
    }

    //fake login
    private void startSignin(){
        String text_email = mEmail.getText().toString().trim();
        String text_password = mPassword.getText().toString().trim();
        String text_passwordc = mPassword.getText().toString().trim();

        //if all fields not empty
        if(!TextUtils.isEmpty(text_email) && !TextUtils.isEmpty(text_password)&& !TextUtils.isEmpty(text_passwordc)){
            Intent gotoMain = new Intent(Registrer.this, Main_navigation.class);
            startActivity(gotoMain);
        }
    }
}
