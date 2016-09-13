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
    private static final int VERSION = 4;
    private static final String DATABASE_NAME = "customers.db";

    public CustomerBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + CustomerTable.NAME + "(" + CustomerTable.Cols.ID + "INTEGER PRIMARY KEY AUTOINCRMENT," + CustomerTable.Cols.F_NAME + "TEXT ," + CustomerTable.Cols.L_NAME + " TEXT," + CustomerTable.Cols.DOB_DAY + "TEXT," + CustomerTable.Cols.DOB_YEAR + "TEXT," + CustomerTable.Cols.UNIQUE_ID + "TEXT," + CustomerTable.Cols.DOB_MONTH + "TEXT)");
        db.execSQL("create table " + SessionsTable.NAME + "(" + SessionsTable.Cols.ID + "INTEGER PRIMARY KEY AUTOINCRMENT," + SessionsTable.Cols.UNIQUE_ID + "TEXT ," + SessionsTable.Cols.TOTAL_SESSIONS + " TEXT," + SessionsTable.Cols.SESSIONS_COMPLETED + "TEXT)");
        db.execSQL("create table " + PaymentInfoTable.NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS_CITY TEXT, ADDRESS_STREET TEXT, ADDRESS_STATE TEXT, ADDRESS_ZIP INTEGER, CC_INFO STRING, EXP_DATE STRING)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CustomerTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SessionsTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PaymentInfoTable.NAME);
        onCreate(db);

    }

    public boolean insertCustomerData(String f_name, String l_name, String dob_year, String dob_month, String dob_day, String unique_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomerTable.Cols.F_NAME,f_name);
        contentValues.put(CustomerTable.Cols.L_NAME, l_name);
        contentValues.put(CustomerTable.Cols.DOB_YEAR, dob_year);
        contentValues.put(CustomerTable.Cols.DOB_MONTH, dob_month);
        contentValues.put(CustomerTable.Cols.DOB_DAY, dob_day);
        contentValues.put(CustomerTable.Cols.UNIQUE_ID, unique_id);

        long result = db.insert(CustomerTable.NAME, null, contentValues);

        if (result == -1)
        {
            return false;
        }
        else return true;
    }

    public boolean insertSessionsData(String unique_id, String type, String total_sessions, String sessions_completed)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SessionsTable.Cols.UNIQUE_ID, unique_id);
        contentValues.put(SessionsTable.Cols.TOTAL_SESSIONS, total_sessions);
        contentValues.put(SessionsTable.Cols.SESSIONS_COMPLETED, sessions_completed);

        long result = db.insert(SessionsTable.NAME, null, contentValues);

        if(result == -1)
        {
            return false;
        }
        else return true;
    }

}







