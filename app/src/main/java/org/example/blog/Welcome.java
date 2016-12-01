package org.example.blog;

/**
 * Created by Sandra on 11/3/2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Welcome extends AppCompatActivity {

    private Button mLoginBtn;
    private Button mSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(Welcome.this, MainNavigation.class));
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

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
