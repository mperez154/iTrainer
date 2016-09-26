package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    Button button;  //Button object used to control button (see onCreate method for more)

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        //Code below will gives the the "onClick" functionality
        button=(Button)findViewById(R.id.email_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset errors.
                mEmailView.setError(null);
                mPasswordView.setError(null);

                // Store values at the time of the login attempt.
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                boolean cancel = false;
                View focusView = mEmailView;

                // Check for an empty username.
                if (TextUtils.isEmpty(email) || !isEmailValid(email)) {
                    mEmailView.setError("Please correct username field");
                    focusView = mEmailView;
                    cancel = true;
                } else attemptLogin();
            }
        });

    }
    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = mEmailView;

        if (TextUtils.isEmpty(password))
        {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        else if(!isPasswordValid(password))
        {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            focusView = mEmailView;
            cancel = true;
        }
        else //if(password == getResources().getString(R.string.password) && email == getResources().getString(R.string.user_name))
        {
            Intent i = new Intent(getApplicationContext(),ClientList1.class);
            startActivity(i);
        }
    }
    private boolean isEmailValid(String email) {
        String userName = (String) getText(R.string.user_name);
        return email.equals(userName);
    }
    private boolean isPasswordValid(String password) {
        String userPassword = (String) getText(R.string.password);
        return password.equals(userPassword);
    }
}

