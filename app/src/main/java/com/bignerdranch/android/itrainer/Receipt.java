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
public class Receipt extends AppCompatActivity {
    Button btnPrint;
    Button btnEmail;
    Button btnHome;

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
        setContentView(R.layout.receipt);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        btnPrint = (Button)findViewById(R.id.btnPrint);
        btnPrint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Receipt.this, ClientList1.class);
                startActivity(intent);
            }
        });

        btnEmail = (Button)findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Receipt.this, ClientList1.class);
                startActivity(intent);
            }
        });

        btnHome = (Button)findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Receipt.this, ClientList1.class);
                startActivity(intent);
            }
        });
    }
}
