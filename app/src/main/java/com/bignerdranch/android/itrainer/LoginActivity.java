package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    //Create instance of database
    CustomerBaseHelper myDb;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    Button button;  //Button object used to control button (see onCreate method for more)
    EditText registerLink;
    String email;
    String password;
    String dbPassword;
    String dbUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize myDb
        myDb = new CustomerBaseHelper(this);

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        registerLink = (EditText)findViewById(R.id.registerLink);
        registerLink.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Register.class);
                startActivity(i);
            }

        });

        //Code below will gives the the "onClick" functionality
        button=(Button)findViewById(R.id.email_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset errors.
                mEmailView.setError(null);
                mPasswordView.setError(null);

                // Store values at the time of the login attempt.
                email = mEmailView.getText().toString();
                password = mPasswordView.getText().toString();

                boolean cancel = false;
                View focusView = mEmailView;

                // Check for an empty username.
                if (TextUtils.isEmpty(email)) {
                    mEmailView.setError("Please enter username field");
                    focusView = mEmailView;
                    cancel = true;
                }
                else if (TextUtils.isEmpty(password))
                {
                    mPasswordView.setError(getString(R.string.error_invalid_password));
                    focusView = mPasswordView;
                    cancel = true;

                } else attemptLogin();
            }
        });

    }
    /**
     * Attempts to sign in or Register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        Cursor cursor = myDb.getUserData(email);
        if(cursor.getCount() != 0)
        {
            dbUserName = cursor.getString(0);
            dbPassword = cursor.getString(1);

            boolean cancel = false;
            View focusView = mEmailView;

            if(!isPasswordValid(password))
            {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                focusView = mEmailView;
                cancel = true;
            }
            else
            {
                Intent i = new Intent(getApplicationContext(),ClientList1.class);
                startActivity(i);
            }

        }
        else Toast.makeText(LoginActivity.this, "USERNAME DOES NOT EXIST", Toast.LENGTH_LONG).show();

    }

    private boolean isPasswordValid(String password) {
        String userPassword = dbPassword;
        return password.equals(userPassword);
    }
}

