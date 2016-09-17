package com.bignerdranch.android.itrainer;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema;

import java.util.ArrayList;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class ClientList1 extends AppCompatActivity {

    //Create instance of database
    CustomerBaseHelper myDb;

    Button btnNewCustomer;

    String[] c_name, userName;
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

    private void populateListView()
    {
        Cursor cursor = myDb.getAllCustomerData();
        String[] fromFieldNames = new String[]{TrainerDbSchema.CustomerTable.Cols.UNIQUE_ID, TrainerDbSchema.CustomerTable.Cols.F_NAME};
        int[] toViewIds = new int[]{R.id.c_name, R.id.userName};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.item_layout, cursor, fromFieldNames, toViewIds, 0);
        ListView myList = (ListView)findViewById(R.id.listViewCustomers);
        myList.setAdapter(myCursorAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list);

        //Initialize myDb
        myDb = new CustomerBaseHelper(this);

        populateListView();

        c_name = getResources().getStringArray(R.array.customer);
        userName = getResources().getStringArray(R.array.userName);

        int i = 0;

        for(String name : c_name)
        {
            DataProvider dataProvider = new DataProvider(img_res[i], name, userName[i]);
            arrayList.add(dataProvider);
            i++;
        }


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
