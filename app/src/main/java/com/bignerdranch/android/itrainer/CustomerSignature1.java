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
public class CustomerSignature1 extends AppCompatActivity {
    Button btnCancel;
    Button btnBack;
    Button btnSubmit;

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
        setContentView(R.layout.customer_signature);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        btnBack = (Button)findViewById(R.id.btnBack_signatureScreen);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CustomerSignature1.this, PaymentScreen1.class);
                startActivity(intent);
            }
        });

        btnCancel = (Button)findViewById(R.id.btnCancel_signatureScreen);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CustomerSignature1.this, ClientList1.class);
                startActivity(intent);
            }
        });

        btnSubmit = (Button)findViewById(R.id.submit_button);
        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CustomerSignature1.this, Receipt.class);
                startActivity(intent);
            }
        });
    }
}
