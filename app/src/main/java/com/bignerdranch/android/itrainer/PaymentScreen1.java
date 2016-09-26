package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class PaymentScreen1 extends AppCompatActivity {

    Button btnCancel;
    Button btnBack;
    Button btnContinue;
    TextView paymentHeader;
    ImageView customerPicture;


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

        //New variables on this screen
        final EditText new_session_count = (EditText)findViewById(R.id.new_session_count);
        final EditText address_tf = (EditText)findViewById(R.id.address_tf);
        final EditText phone_tf = (EditText)findViewById(R.id.phone_tf);
        final EditText cc_tf = (EditText)findViewById(R.id.cc_tf);
        final EditText exp_date_tf = (EditText)findViewById(R.id.exp_date_tf);
        final TextView price = (TextView) findViewById(R.id.total_price_payment_screen);

        //Carry over from customer Signature activity (Clicking back)
        Intent intent2 = getIntent();
        final String unique_id_back = intent2.getStringExtra("unique_id");
        final String f_name_back = intent2.getStringExtra("f_name");
        final String l_name_back = intent2.getStringExtra("l_name");
        final String dob_d_back = intent2.getStringExtra("dob_d");
        final String dob_m_back = intent2.getStringExtra("dob_m");
        final String dob_y_back = intent2.getStringExtra("dob_y");
        final String num_sessions_back = intent2.getStringExtra("num_sessions");
        final String finalPrice_back = intent2.getStringExtra("finalPrice");
        final String new_session_count_back = intent2.getStringExtra("new_session_count");
        final String address_tf_back = intent2.getStringExtra("address_tf");
        final String phone_tf_back = intent2.getStringExtra("phone_tf");
        final String cc_tf_back = intent2.getStringExtra("cc_tf");
        final String exp_date_tf_back = intent2.getStringExtra("exp_date_tf");

        //Carry over from last Add Sessions activity
        Intent intent = getIntent();
        final String unique_id = intent.getStringExtra("unique_id");
        final String f_name = intent.getStringExtra("f_name");
        final String l_name = intent.getStringExtra("l_name");
        final String dob_d = intent.getStringExtra("dob_d");
        final String dob_m = intent.getStringExtra("dob_m");
        final String dob_y = intent.getStringExtra("dob_y");
        final String num_sessions = intent.getStringExtra("num_sessions");  //Used to set new_session_count below
        final Bitmap customerImage = (Bitmap)intent.getParcelableExtra("customerImage");

        if(unique_id_back !=  null)
        {
            new_session_count.setText(num_sessions_back);    //Sets newSessionCount variable to info fom previous screen
            address_tf.setText(address_tf_back);
            phone_tf.setText(phone_tf_back);
            cc_tf.setText(cc_tf_back);
            exp_date_tf.setText(exp_date_tf_back);

        }

        Integer intSessions = Integer.parseInt(num_sessions);   //Parse String to Int
        double totPrice = intSessions * 29.99;  //Number of sessions times $29.99
        final String finalPrice = String.valueOf(totPrice);

        paymentHeader = (TextView)findViewById(R.id.payment_screen_header);
        paymentHeader.setText(f_name + " " + l_name + ": " + getResources().getString(R.string.payment_screen_header)); //Sets the activity header to the new client name

        customerPicture = (ImageView)findViewById(R.id.picture_id);
        customerPicture.setImageBitmap(customerImage);

        price.setText(NumberFormat.getCurrencyInstance().format(totPrice));

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
                intent.putExtra("customerImage", customerImage);
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
