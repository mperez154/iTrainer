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
import java.util.Date;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class AddCustomer1 extends AppCompatActivity {
    Button btnCancel;
    Button btnNext;
    Long uniqueID = (new Date().getTime())/10000;    //Get the current time() and divides by 100,000 to create 8 digit unique ID


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
        setContentView(R.layout.add_customer);

        final EditText f_name = (EditText)findViewById(R.id.f_name_tf);
        final EditText l_name = (EditText)findViewById(R.id.l_name_tf);
        final EditText dob_d = (EditText)findViewById(R.id.dob_d_tf);
        final EditText dob_m = (EditText)findViewById(R.id.dob_m_tf);
        final EditText dob_y = (EditText)findViewById(R.id.dob_y_tf);
        final EditText unique_id = (EditText)findViewById(R.id.unique_id_tf);

        unique_id.setText(uniqueID.toString()); //Sets this field using the uniqueID variable created above

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();


        btnCancel = (Button)findViewById(R.id.btnCancel_new_customer);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddCustomer1.this, ClientList1.class);
                startActivity(intent);
            }

        });

        btnNext = (Button)findViewById(R.id.next_button);
        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent intent = new Intent(AddCustomer1.this, AddSessions1.class);
                intent.putExtra("unique_id", unique_id.getText().toString());   //This will make the unique ID available in the next activity
                intent.putExtra("f_name", f_name.getText().toString());
                intent.putExtra("l_name", l_name.getText().toString());
                intent.putExtra("dob_d", dob_d.getText().toString());
                intent.putExtra("dob_m", dob_m.getText().toString());
                intent.putExtra("dob_y", dob_y.getText().toString());
                startActivity(intent);
            }

        });


    }

}
