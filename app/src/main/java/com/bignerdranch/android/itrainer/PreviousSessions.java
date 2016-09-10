package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by Marco on 9/4/2016.
 */
public class PreviousSessions extends AppCompatActivity {
    Button btnBack;
    Button btnAdd;

    //This inflates main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return  true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previous_sessions);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        btnAdd = (Button)findViewById(R.id.btnAddSessions_customerScreen);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PreviousSessions.this, AddSessions1.class);
                startActivity(intent);
            }
        });

        btnBack = (Button)findViewById(R.id.btnBack_customerScreen);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PreviousSessions.this, ClientList1.class);
                startActivity(intent);
            }
        });

    }
}
