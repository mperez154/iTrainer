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
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class AddSessions1 extends AppCompatActivity {
    Button btnAdd;
    Button btnBack;
    Button btnCancel;
    TextView newSessionsHeader;

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
        setContentView(R.layout.add_sessions);

        //Carry over from AddCustomer screen
        Intent intent = getIntent();
        final String unique_id = intent.getStringExtra("unique_id");
        final String f_name = intent.getStringExtra("f_name");
        final String l_name = intent.getStringExtra("l_name");
        final String dob_d = intent.getStringExtra("dob_d");
        final String dob_m = intent.getStringExtra("dob_m");
        final String dob_y = intent.getStringExtra("dob_y");

        //Carry over from last Payment Screen (Clicking back)
        Intent intent2 = getIntent();
        final String unique_id_back = intent.getStringExtra("unique_id");
        final String f_name_back = intent.getStringExtra("f_name");
        final String l_name_back = intent.getStringExtra("l_name");
        final String dob_d_back = intent.getStringExtra("dob_d");
        final String dob_m_back = intent.getStringExtra("dob_m");
        final String dob_y_back = intent.getStringExtra("dob_y");
        final String num_sessions_back = intent.getStringExtra("num_sessions");

        //New Variables
        final EditText num_sessions = (EditText)findViewById(R.id.sessions_add_tf);

        if(num_sessions_back !=  null)
        {
            num_sessions.setText(num_sessions_back);
        }

        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        newSessionsHeader = (TextView)findViewById(R.id.new_sessions_header);
        newSessionsHeader.setText(f_name + " " + l_name + ": " + getResources().getString(R.string.add_sessions_header)); //Sets the activity header to the new client name



                btnAdd = (Button)findViewById(R.id.add_button);
                btnAdd.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent = new Intent(AddSessions1.this, PaymentScreen1.class);
                    intent.putExtra("unique_id", unique_id);   //This will make the unique ID available in the next activity
                    intent.putExtra("f_name", f_name);
                    intent.putExtra("l_name", l_name);
                    intent.putExtra("dob_d", dob_d);
                    intent.putExtra("dob_m", dob_m);
                    intent.putExtra("dob_y", dob_y);
                    intent.putExtra("num_sessions", num_sessions.getText().toString());
                    startActivity(intent);
            }
        });

        btnBack = (Button)findViewById(R.id.back_button);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddSessions1.this, AddCustomer1.class);
                intent.putExtra("unique_id", unique_id);   //This will make the unique ID available in the next activity
                intent.putExtra("f_name", f_name);
                intent.putExtra("l_name", l_name);
                intent.putExtra("dob_d", dob_d);
                intent.putExtra("dob_m", dob_m);
                intent.putExtra("dob_y", dob_y);
                startActivity(intent);
            }
        });

        btnCancel = (Button)findViewById(R.id.cancel_newSessions);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddSessions1.this, ClientList1.class);
                startActivity(intent);
            }
        });
    }
}
