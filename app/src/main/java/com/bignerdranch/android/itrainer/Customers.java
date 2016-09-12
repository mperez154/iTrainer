package com.bignerdranch.android.itrainer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bignerdranch.android.itrainer.database.CustomerBaseHelper;

import java.util.List;

/**
 * Created by Marco on 9/11/2016.
 */
public class Customers {
    //Here is where the database will be built and stored
    public List<Customers> mCustomers;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    private Customers(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new CustomerBaseHelper(mContext)
                .getWritableDatabase();


    }
}
