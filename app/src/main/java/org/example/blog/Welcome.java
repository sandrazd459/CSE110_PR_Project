package org.example.blog;

/**
 * Created by Sandra on 11/3/2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Welcome extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private Button mLoginBtn;
    private Button mSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        //mDatabase = FirebaseDatabase.getInstance().getReference().child("Sell Posts");

        mLoginBtn = (Button) findViewById(R.id.welcome_login);
        mSignupBtn = (Button) findViewById(R.id.welcome_signup);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(Welcome.this,Login.class);
                goToLogin.putExtra("Sign", "in");
                startActivity(goToLogin);
            }
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToLogin = new Intent(Welcome.this,Login.class);
                goToLogin.putExtra("Sign", "up");
                startActivity(goToLogin);
            }
        });

    }

    //prevent to get back after log out
    @Override
    public void onBackPressed() {
    }
}
