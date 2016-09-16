package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class ClientList1 extends AppCompatActivity {

    //Create instance of database
    CustomerBaseHelper myDb;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button btnNewCustomer;

    String[] c_name, userName, f_name, l_name, unique_id;
    int[] img_res = {R.drawable.user1,R.drawable.user2, R.drawable.user3, R.drawable.user4,
            R.drawable.user5, R.drawable.user6, R.drawable.user7, R.drawable.user8,
            R.drawable.user9, R.drawable.user10};
    ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();


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

    public void viewAll()
    {

        Cursor result = myDb.getAllCustomerData();
        int i = 0;
        String[] myArray = new String[10];

        while (result.moveToNext())
        {
            myArray[i] = result.getString(i);
            i++;
            Toast.makeText(ClientList1.this, myArray[i], Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list);

        //Initialize myDb
        myDb = new CustomerBaseHelper(this);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        c_name = getResources().getStringArray(R.array.customer);
        userName = getResources().getStringArray(R.array.userName);

        viewAll();

        int i = 0;

        for(String name : c_name)
        {
            DataProvider dataProvider = new DataProvider(img_res[i], name, userName[i]);
            arrayList.add(dataProvider);
            i++;
        }

        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.user_login_info, LoggedInFragment.newInstance())
                .commit();

        btnNewCustomer = (Button)findViewById(R.id.new_customer_button);
        btnNewCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ClientList1.this, AddCustomer1.class);
                startActivity(intent);

            }
        });
    }


}
