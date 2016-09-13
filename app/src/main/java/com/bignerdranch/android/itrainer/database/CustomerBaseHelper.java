package com.bignerdranch.android.itrainer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.itrainer.database.TrainerDbSchema.CustomerTable;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema.PaymentInfoTable;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema.SessionsTable;

/**
 * Created by Marco on 9/11/2016.
 */
public class CustomerBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 3;
    private static final String DATABASE_NAME = "customers.db";

    public CustomerBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CustomerTable.NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, F_NAME TEXT, L_NAME TEXT, AGE INTEGER, DOB_DAY INTEGER, DOB_MONTH INTEGER, DOB_YEAR INTEGER, DESIRED_WEIGHT INTEGER, START_WEIGHT INTEGER)");
        db.execSQL("create table " + SessionsTable.NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE DATE, TYPE TEXT, CURRENT_WEIGHT INTEGER, AMT_TO_TARGET INTEGER)");
        db.execSQL("create table " + PaymentInfoTable.NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS_CITY TEXT, ADDRESS_STREET TEXT, ADDRESS_STATE TEXT, ADDRESS_ZIP INTEGER, CC_INFO STRING, EXP_DATE STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CustomerTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SessionsTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PaymentInfoTable.NAME);
        onCreate(db);

    }

    public boolean insertCustomerData(String f_name, String l_name, int age, int dob_year, int dob_month, int dob_day, int start_weight, int target_weight)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomerTable.Cols.F_NAME,f_name);
        contentValues.put(CustomerTable.Cols.L_NAME, l_name);
        contentValues.put(CustomerTable.Cols.AGE, age);
        contentValues.put(CustomerTable.Cols.DOB_YEAR, dob_year);
        contentValues.put(CustomerTable.Cols.DOB_MONTH, dob_month);
        contentValues.put(CustomerTable.Cols.DOB_DAY, dob_day);
        contentValues.put(CustomerTable.Cols.START_WEIGHT, start_weight);
        contentValues.put(CustomerTable.Cols.DESIRED_WEIGHT, target_weight);

        long result = db.insert(CustomerTable.NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else return true;
    }

}
