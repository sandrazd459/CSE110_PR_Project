package org.example.blog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.annotation.NonNull;


import com.firebase.client.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


//password has to be at least 6 bytes long
//check valid email address

public class Login extends AppCompatActivity {
    private static final String TAG = "Email and Password";
    //for login function
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText mEmail;
    private EditText mPassword;
    private EditText mPassword_confirm;
    private Button mLoginBtn;

    //determine which layout after welcome pg
    private String value;

    /**current page of Login*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        value = getIntent().getExtras().getString("Sign");

        if(value.equals("in")){//sign in
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);
        }
        if(value.equals("up")){//sign up
            super.onCreate(savedInstanceState);
            setContentView(R.layout.registrer);
            mPassword_confirm = (EditText) findViewById(R.id.password_confirm);
        }

        mAuth = FirebaseAuth.getInstance();
        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mLoginBtn = (Button) findViewById(R.id.sign_button);

        //triggered when Sign in/up button is clicked
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value.equals("in"))
                    login();
                if(value.equals("up")){
                    createAccount();
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    if(value.equals("in")) {
                        Intent gotoMain = new Intent(Login.this, Main_navigation.class);
                        startActivity(gotoMain);
                    }
                    if(value.equals("up")){
                        Intent gotoSetupProfile = new Intent(Login.this, UserProfileSetupController.class);
                        gotoSetupProfile.putExtra("account","create");
                        startActivity(gotoSetupProfile);
                    }

                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };
    }


    private void createAccount() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        Log.d(TAG, "createAccount:" + email);
        if (!valid()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            new AlertDialog.Builder(Login.this)
                                    //  .setIcon(R.drawable.gong1)
                                    .setTitle("This email is already registered.")
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    // TODO Auto-generated method stub
                                                }
                                            })//.setNegativeButton("Cancel", null).create()
                                    .show();
                        }

                        // [START_EXCLUDE]
                        //hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    private void login() {
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        Log.d(TAG, "signIn:" + email);

        //check if input format is valid
       if (!valid()) {
            return;
        }

        //use email and password to login
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithEmail:failed", task.getException());
                            new AlertDialog.Builder(Login.this)
                                    //  .setIcon(R.drawable.gong1)
                                    .setTitle("Invalid email/password combination.")
                                    .setPositiveButton("Get it!",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    // TODO Auto-generated method stub
                                                }
                                            })//.setNegativeButton("Cancel", null).create()
                                    .show();
                        }
                    }
                });
        //end of sign in with email
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private boolean valid() {
        String errMessage = "";
        boolean valid = true;

        String email = mEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Required.");
            errMessage = "No blank field is allowed";
            valid = false;
        }else{
            int len = mEmail.length();
            int at = 0;
            int pt = 0;
            for(int i = 0; i < len ; i++){
                if(email.charAt(i) == '@')
                    at = i;
                if(email.charAt(i) =='.')
                    pt = i;
            }
            if(at >= pt&& valid == true){
                errMessage = "Invalid email";
                valid = false;

            }else{
                mPassword.setError(null);
            }
        }
        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Required.");
            mPassword.setText("");
            if(value.equals("up"))
                mPassword_confirm.setText("");

            errMessage = "No blank field is allowed";
            valid = false;
        }else if(password.length() < 6 && valid == true){
            mPassword.setText("");
            if(value.equals("up"))
                mPassword_confirm.setText("");
            errMessage = "Min-length for password is 6 chars";
            valid = false;
        }else {
            mPassword.setError(null);
        }
        if(value.equals("up")){
            String passwordC = mPassword_confirm.getText().toString();
            if (TextUtils.isEmpty(passwordC)) {
                mPassword_confirm.setError("Required.");
                mPassword_confirm.setText("");
                mPassword.setText("");
                errMessage = "No blank field is allowed";
                valid = false;
            }
            else{
                if(!password.equals(passwordC)&& valid == true) {
                    mPassword_confirm.setText("");
                    mPassword.setText("");
                    errMessage = "password and its confirmation do not match.";
                    valid = false;
                }else {
                    mPassword_confirm.setError(null);
                }
            }
        }

        if(valid == false){
            //dialog
            new AlertDialog.Builder(this)
                    //  .setIcon(R.drawable.gong1)
                    .setTitle(errMessage)
                    .setPositiveButton("Get it!",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub
                                }
                            })//.setNegativeButton("Cancel", null).create()
                    .show();
        }

        return valid;
    }
}
