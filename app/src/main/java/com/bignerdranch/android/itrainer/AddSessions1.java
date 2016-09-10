package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class AddSessions1 extends AppCompatActivity {
    Button btnAdd;
    Button btnBack;
    Button btnCancel;

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
        setContentView(R.layout.add_sessions);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        btnAdd = (Button)findViewById(R.id.add_button);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddSessions1.this, PaymentScreen1.class);
                startActivity(intent);
            }
        });

        btnBack = (Button)findViewById(R.id.back_button);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddSessions1.this, AddCustomer1.class);
                startActivity(intent);
            }
        });

        btnCancel = (Button)findViewById(R.id.cancel_newSessions);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddSessions1.this, ClientList1.class);
                startActivity(intent);
            }
        });
    }
}
