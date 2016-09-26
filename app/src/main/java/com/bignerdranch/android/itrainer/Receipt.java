package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static java.net.Proxy.Type.HTTP;

/**
 * Created by Marco on 9/4/2016.
 */
public class Receipt extends AppCompatActivity {
    Button btnPrint;
    Button btnEmail;
    Button btnHome;
    TextView customerName;
    TextView customerAddress;
    TextView numSessions;
    TextView price;

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
        setContentView(R.layout.receipt);

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
        final String num_sessions = intent.getStringExtra("num_sessions");
        final Double finalPrice = Double.parseDouble(intent.getStringExtra("finalPrice"));
        final String new_session_count = intent.getStringExtra("new_session_count");
        final String address_tf = intent.getStringExtra("address_tf");
        final String phone_tf = intent.getStringExtra("phone_tf");
        final String cc_tf = intent.getStringExtra("cc_tf");
        final String exp_date_tf = intent.getStringExtra("exp_date_tf");
        final GestureOverlayView signature = (GestureOverlayView)findViewById(R.id.signature_box);

        customerName = (TextView)findViewById(R.id.receipt_name);
        customerName.setText(getResources().getString(R.string.receipt_name) + " " + f_name + " " + l_name);

        customerAddress = (TextView)findViewById(R.id.customer_address);
        customerAddress.setText(getResources().getString(R.string.customer_address) + "\n" + address_tf);

        numSessions = (TextView)findViewById(R.id.receipt_num_sessions);
        numSessions.setText(getResources().getString(R.string.num_sessions) + " " + new_session_count);

        price = (TextView)findViewById(R.id.receipt_price);
        String finalPrice2 = NumberFormat.getCurrencyInstance().format(finalPrice); //Format the double so that it looks like currence (i.e. two decimal places)
        price.setText(getResources().getString(R.string.total_price_sig_screen) + " " + finalPrice2);

        btnPrint = (Button)findViewById(R.id.btnPrint);
        btnPrint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(Receipt.this, "Print feature coming soon!", Toast.LENGTH_LONG).show();
                //Intent intent = new Intent(Receipt.this, ClientList1.class);
                //startActivity(intent);
            }
        });

        btnEmail = (Button)findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Toast.makeText(Receipt.this, "Email feature coming soon!", Toast.LENGTH_LONG).show();
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"mperez154@verizon.net"}); // recipients
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "iTrainer Receipt: " + f_name + " " + l_name);
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Customer: " + f_name + " " + l_name + "\n" + "Price: " + NumberFormat.getCurrencyInstance().format(finalPrice));
                startActivity(emailIntent);
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
