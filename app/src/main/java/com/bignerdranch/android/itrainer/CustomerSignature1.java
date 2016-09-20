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

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class CustomerSignature1 extends AppCompatActivity {
    Button btnCancel;
    Button btnBack;
    Button btnSubmit;
    TextView sessionSigScreen;
    TextView totalPrice;

    //Create instance of database
    CustomerBaseHelper myDb;

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
            LogOffFragment dialog = new LogOffFragment();
            dialog.show(manager, "Settings");
        }
        else if (id == R.id.about_us_id)
        {
            FragmentManager manager = getSupportFragmentManager();
            LogOffFragment dialog = new LogOffFragment();
            dialog.show(manager, "About Us");
        }
        return true;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_signature);

        //Initialize myDb
        myDb = new CustomerBaseHelper(this);


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
        final String finalPrice = intent.getStringExtra("finalPrice");
        final String new_session_count = intent.getStringExtra("new_session_count");
        final String address_tf = intent.getStringExtra("address_tf");
        final String phone_tf = intent.getStringExtra("phone_tf");
        final String cc_tf = intent.getStringExtra("cc_tf");
        final String exp_date_tf = intent.getStringExtra("exp_date_tf");

        final GestureOverlayView signature = (GestureOverlayView)findViewById(R.id.signature_box);

        sessionSigScreen = (TextView)findViewById(R.id.sessions_sig_screen);
        sessionSigScreen.setText((getResources().getString(R.string.signature_screen_sessions) + " " + new_session_count + ", "));
        totalPrice = (TextView)findViewById(R.id.total_price_sig_screen);
        totalPrice.setText((getResources().getString(R.string.total_price_sig_screen) + " $" + finalPrice));

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

                boolean isInserted = myDb.insertCustomerData(f_name, l_name, dob_y, dob_m, dob_d, unique_id);
                if(isInserted)
                {
                    Toast.makeText(CustomerSignature1.this, "Customer added to database", Toast.LENGTH_SHORT).show();
                }
                else  Toast.makeText(CustomerSignature1.this, "FAILED TO ADD CUSTOMER", Toast.LENGTH_SHORT).show();


                boolean sessionDataInserted = myDb.insertSessionsData(unique_id, new_session_count, "0");
                if(sessionDataInserted)
                {
                    Toast.makeText(CustomerSignature1.this, "Session data added to database", Toast.LENGTH_SHORT).show();
                }
                else  Toast.makeText(CustomerSignature1.this, "FAILED TO ADD SESSIONS", Toast.LENGTH_SHORT).show();


                boolean orderDataInserted = myDb.insertOrderData(unique_id, address_tf, new_session_count, finalPrice, cc_tf, exp_date_tf, phone_tf);
                if(orderDataInserted)
                {
                    Toast.makeText(CustomerSignature1.this, "Order added to database", Toast.LENGTH_SHORT).show();
                }
                else  Toast.makeText(CustomerSignature1.this, "FAILED TO ADD ORDER", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(CustomerSignature1.this, Receipt.class);
                intent.putExtra("unique_id", unique_id);   //This will make the unique ID available in the next activity
                intent.putExtra("f_name", f_name);
                intent.putExtra("l_name", l_name);
                intent.putExtra("dob_d", dob_d);
                intent.putExtra("dob_m", dob_m);
                intent.putExtra("dob_y", dob_y);
                intent.putExtra("num_sessions", num_sessions);
                intent.putExtra("finalPrice", finalPrice);
                intent.putExtra("new_session_count", new_session_count);
                intent.putExtra("address_tf", address_tf);
                intent.putExtra("phone_tf", phone_tf);
                intent.putExtra("cc_tf", cc_tf);
                intent.putExtra("exp_date_tf", exp_date_tf);
                intent.putExtra("signature", signature.getGesture());
                startActivity(intent);
            }
        });
    }
}
