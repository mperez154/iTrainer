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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema;

import java.util.ArrayList;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class ClientList1 extends AppCompatActivity{

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

    private void populateListView() {
        Cursor cursor = myDb.getAllCustomerData();

        //byte[] test1 = cursor.getBlob(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.PICTURE));
        //Bitmap test = BitmapFactory.decodeByteArray(test1, 0, test1.length);  //Converting from byte[] to Bitmap

        String[] fromFieldNames = new String[]{TrainerDbSchema.CustomerTable.Cols.F_NAME, TrainerDbSchema.CustomerTable.Cols.L_NAME, TrainerDbSchema.CustomerTable.Cols.UNIQUE_ID, TrainerDbSchema.CustomerTable.Cols.DOB_DAY, TrainerDbSchema.CustomerTable.Cols.DOB_MONTH, TrainerDbSchema.CustomerTable.Cols.DOB_YEAR, TrainerDbSchema.CustomerTable.Cols.PICTURE};
        int[] toViewIds = new int[]{R.id.fName_ItemLayout, R.id.lName_ItemLayout};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(), R.layout.item_layout, cursor, fromFieldNames, toViewIds, 0);
        ListView myList = (ListView) findViewById(R.id.listViewCustomers);
        myList.setAdapter(myCursorAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> myList, View view, int position, long id) {

                Cursor cursor = (Cursor)myList.getItemAtPosition(position);
                String firstName = cursor.getString(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.F_NAME));
                String lastName = cursor.getString(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.L_NAME));
                String uniqueID = cursor.getString(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.UNIQUE_ID));
                String dob_d = cursor.getString(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.DOB_DAY));
                String dob_m = cursor.getString(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.DOB_MONTH));
                String dob_y = cursor.getString(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.DOB_YEAR));
                byte[] customerImage = cursor.getBlob(cursor.getColumnIndexOrThrow(TrainerDbSchema.CustomerTable.Cols.PICTURE));    //Grabbing byte[] from database
                Intent intent = new Intent(ClientList1.this, CustomerDetails.class);
                intent.putExtra("unique_id", uniqueID);   //This will make the unique ID available in the next activity
                intent.putExtra("f_name", firstName);
                intent.putExtra("l_name", lastName);
                intent.putExtra("dob_d", dob_d);
                intent.putExtra("dob_m", dob_m);
                intent.putExtra("dob_y", dob_y);
                intent.putExtra("customerImage", customerImage); //putting in intent so its available in the customerDetails activity
                startActivity(intent);

            }
        });
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