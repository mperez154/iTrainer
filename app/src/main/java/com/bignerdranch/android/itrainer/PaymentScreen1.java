package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class PaymentScreen1 extends AppCompatActivity {

    Button btnCancel;
    Button btnBack;
    Button btnContinue;
    TextView paymentHeader;


    //This inflates main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id == R.id.logOut)
        {
            FragmentManager manager = getSupportFragmentManager();
            LogOffFragment dialog = new LogOffFragment();
            dialog.show(manager, "Log Out");
        }
        else if(id == R.id.settings_id)
        {
            FragmentManager manager = getSupportFragmentManager();
            SettingsFragment dialog = new SettingsFragment();
            dialog.show(manager, "SettingsFragment");
        }
        else if (id == R.id.about_us_id)
        {
            FragmentManager manager = getSupportFragmentManager();
            AboutUsFragment dialog = new AboutUsFragment();
            dialog.show(manager, "About Us");
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_screen);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        //Carry over from last activity
        Intent intent = getIntent();
        final String unique_id = intent.getStringExtra("unique_id");
        final String f_name = intent.getStringExtra("f_name");
        final String l_name = intent.getStringExtra("l_name");
        final String dob_d = intent.getStringExtra("dob_d");
        final String dob_m = intent.getStringExtra("dob_m");
        final String dob_y = intent.getStringExtra("dob_y");
        final String num_sessions = intent.getStringExtra("num_sessions");  //Used to set new_session_count below

        final EditText new_session_count = (EditText)findViewById(R.id.new_session_count);
            new_session_count.setText(num_sessions);    //Sets newSessionCount variable to info fom previous screen
        final EditText address_tf = (EditText)findViewById(R.id.address_tf);
        final EditText phone_tf = (EditText)findViewById(R.id.phone_tf);
        final EditText cc_tf = (EditText)findViewById(R.id.cc_tf);
        final EditText exp_date_tf = (EditText)findViewById(R.id.exp_date_tf);
        final TextView price = (TextView) findViewById(R.id.total_price_payment_screen);

        Integer intSessions = Integer.parseInt(num_sessions);   //Parse String to Int
        double totPrice = intSessions * 29.99;  //Number of sessions times $29.99
        final String finalPrice = String.valueOf(totPrice);

        paymentHeader = (TextView)findViewById(R.id.payment_screen_header);
        paymentHeader.setText(f_name + " " + l_name + ": " + getResources().getString(R.string.payment_screen_header)); //Sets the activity header to the new client name

        price.setText("$" + totPrice);



        btnContinue = (Button)findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PaymentScreen1.this, CustomerSignature1.class);
                intent.putExtra("unique_id", unique_id);   //This will make the unique ID available in the next activity
                intent.putExtra("f_name", f_name);
                intent.putExtra("l_name", l_name);
                intent.putExtra("dob_d", dob_d);
                intent.putExtra("dob_m", dob_m);
                intent.putExtra("dob_y", dob_y);
                intent.putExtra("num_sessions", num_sessions);
                intent.putExtra("new_session_count", new_session_count.getText().toString());
                intent.putExtra("address_tf", address_tf.getText().toString());
                intent.putExtra("phone_tf", phone_tf.getText().toString());
                intent.putExtra("cc_tf", cc_tf.getText().toString());
                intent.putExtra("exp_date_tf", exp_date_tf.getText().toString());
                intent.putExtra("finalPrice", finalPrice);
                startActivity(intent);
            }
        });

        btnBack = (Button)findViewById(R.id.back_paymentScreen);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(PaymentScreen1.this, AddSessions1.class);
                intent.putExtra("unique_id", unique_id);   //This will make the unique ID available in the next activity
                intent.putExtra("f_name", f_name);
                intent.putExtra("l_name", l_name);
                intent.putExtra("dob_d", dob_d);
                intent.putExtra("dob_m", dob_m);
                intent.putExtra("dob_y", dob_y);
                intent.putExtra("num_sessions", num_sessions);
                intent.putExtra("new_session_count", new_session_count.getText().toString());
                intent.putExtra("address_tf", address_tf.getText().toString());
                intent.putExtra("phone_tf", phone_tf.getText().toString());
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
