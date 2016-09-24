package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema;

/**
 * Created by mperez5 on 9/20/2016.
 */

public class CustomerDetails extends AppCompatActivity {
    ImageView profilePic;
    Button bkButton;

    CustomerBaseHelper myDb = new CustomerBaseHelper(this);

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
            setContentView(R.layout.customer_details);

            profilePic = (ImageView)findViewById(R.id.profilePic);
            profilePic.setImageResource(R.drawable.user9);

            //Carry over from last activity
            Intent intent = getIntent();
            final String unique_id = intent.getStringExtra("unique_id");
            final String f_name = intent.getStringExtra("f_name");
            final String l_name = intent.getStringExtra("l_name");
            final String dob_d = intent.getStringExtra("dob_d");
            final String dob_m = intent.getStringExtra("dob_m");
            final String dob_y = intent.getStringExtra("dob_y");
            final String position = intent.getStringExtra("position");

            Cursor cursor = myDb.getAllSessionData(unique_id);

            String uniqueID2 = cursor.getString(0);
            String address = cursor.getString(1);
            String added_sessions = cursor.getString(2);
            String price = cursor.getString(3);
            String phone = cursor.getString(4);
            String cc_info = cursor.getString(5);
            String exp_date = cursor.getString(6);

            Toast.makeText(CustomerDetails.this, "TEST " + exp_date, Toast.LENGTH_LONG).show();

            TextView fName = (TextView) findViewById(R.id.fN_customerDetails);
            fName.setText(f_name + " " + l_name);
            TextView tvAddress = (TextView)findViewById(R.id.add_cust_details);
            tvAddress.setText(address);
            TextView dob = (TextView)findViewById(R.id.dob_cust_details);
            dob.setText(dob_m + "/" + dob_d + "/" + dob_y);
            TextView tvPhone = (TextView)findViewById(R.id.phone_cust_details);
            tvPhone.setText(phone);

            bkButton = (Button)findViewById(R.id.btBack);
            bkButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(CustomerDetails.this, ClientList1.class);
                    startActivity(intent);
                }
            });

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.user_login_info, LoggedInFragment.newInstance())
                    .commit();

    }
}
