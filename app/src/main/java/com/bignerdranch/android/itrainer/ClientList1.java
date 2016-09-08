package com.bignerdranch.android.itrainer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by mperez5 on 9/5/2016.
 */
public class ClientList1 extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    String[] c_name, userName;
    int[] img_res = {R.drawable.personal_trainer,R.drawable.personal_trainer};
    ArrayList<DataProvider> arrayList = new ArrayList<DataProvider>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        c_name = getResources().getStringArray(R.array.customer);
        userName = getResources().getStringArray(R.array.userName);

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
    }


}
