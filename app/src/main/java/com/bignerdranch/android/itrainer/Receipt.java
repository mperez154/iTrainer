package com.bignerdranch.android.itrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Marco on 9/4/2016.
 */
public class Receipt extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();
    }
}
