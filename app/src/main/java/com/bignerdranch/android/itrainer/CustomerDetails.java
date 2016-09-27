package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;

/**
 * Created by mperez5 on 9/20/2016.
 */

public class CustomerDetails extends AppCompatActivity {
    ImageView profilePic;
    Button bkButton;
    Button btCompleteS;
    Bitmap customerImage2;
    String address;
    String totalSessions;
    String completedSessions;

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

            //Carry over from last activity
            Intent intent = getIntent();
            final String unique_id = intent.getStringExtra("unique_id");
            final String f_name = intent.getStringExtra("f_name");
            final String l_name = intent.getStringExtra("l_name");
            final String dob_d = intent.getStringExtra("dob_d");
            final String dob_m = intent.getStringExtra("dob_m");
            final String dob_y = intent.getStringExtra("dob_y");
            final byte[] customerImage = intent.getByteArrayExtra("customerImage"); //image coming from Customer List activity

            //Database queries from CustomerBaseHelper class
            Cursor cursor = myDb.getAllOrderData(unique_id);
            Cursor cursor1 = myDb.getAllSessionData(unique_id);

            //Values retrieved from database
            address = cursor.getString(1);
            String phone = cursor.getString(4);
            totalSessions = cursor1.getString(1);
            completedSessions = cursor1.getString(2);

            customerImage2 = BitmapFactory.decodeByteArray(customerImage, 0, customerImage.length);  //Converting from byte[] to Bitmap

            //Set all activity widgets
            TextView fName = (TextView) findViewById(R.id.fN_customerDetails);
            fName.setText(f_name + " " + l_name);
            TextView tvAddress = (TextView)findViewById(R.id.add_cust_details);
            tvAddress.setText(address);
            TextView dob = (TextView)findViewById(R.id.dob_cust_details);
            dob.setText(dob_m + "/" + dob_d + "/" + dob_y);
            TextView tvPhone = (TextView)findViewById(R.id.phone_cust_details);
            tvPhone.setText(phone);
            TextView tvTotSessions = (TextView)findViewById(R.id.tvTotalSessions_cust_details);
            tvTotSessions.setText(getResources().getString(R.string.totSessionsMsg01)+ " " + totalSessions);
            TextView tvComSessions = (TextView)findViewById(R.id.tvCompSessions_customerDetails);
            tvComSessions.setText(getResources().getString(R.string.completedMsg01)+ " " + completedSessions);
            profilePic = (ImageView)findViewById(R.id.profilePic);
            profilePic.setImageBitmap(customerImage2);

            bkButton = (Button)findViewById(R.id.btBack);
            bkButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(CustomerDetails.this, ClientList1.class);
                    startActivity(intent);
                }
            });

            btCompleteS = (Button)findViewById(R.id.btCompleteSession);
            btCompleteS.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    FragmentManager manager = getSupportFragmentManager();
                    CompleteSessionsFragment dialog = new CompleteSessionsFragment();
                    dialog.show(manager, "Session Complete");

                    /*Cursor cursor = myDb.getAllSessionData(unique_id);

                    completedSessions = cursor.getString(2);
                    int intCompletedSessions = Integer.parseInt(completedSessions);

                    intCompletedSessions = intCompletedSessions - 1;
                    completedSessions = String.valueOf(intCompletedSessions);



                    boolean isInserted = myDb.updateSessionsCompleted(completedSessions);
                    if(isInserted)
                    {
                        Toast.makeText(CustomerDetails.this, "UPDATE SUCCESSFUL", Toast.LENGTH_LONG).show();
                    }
                    else  Toast.makeText(CustomerDetails.this, "FAILED TO ADD CUSTOMER", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(CustomerDetails.this, CustomerDetails.class);
                    startActivity(intent);*/

                }
            });

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.user_login_info, LoggedInFragment.newInstance())
                    .commit();

    }
}
