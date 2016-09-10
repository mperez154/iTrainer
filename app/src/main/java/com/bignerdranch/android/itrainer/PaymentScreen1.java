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
public class PaymentScreen1 extends AppCompatActivity {

    Button btnCancel;
    Button btnBack;
    Button btnContinue;

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
        setContentView(R.layout.payment_screen);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        btnContinue = (Button)findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PaymentScreen1.this, CustomerSignature1.class);
                startActivity(intent);
            }
        });

        btnBack = (Button)findViewById(R.id.back_paymentScreen);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PaymentScreen1.this, AddSessions1.class);
                startActivity(intent);
            }
        });

        btnCancel = (Button)findViewById(R.id.cancel_paymentScreen);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PaymentScreen1.this, ClientList1.class);
                startActivity(intent);
            }
        });
    }
}
