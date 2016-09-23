package com.bignerdranch.android.itrainer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.itrainer.database.TrainerDbSchema.CustomerTable;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema.PaymentInfoTable;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema.SessionsTable;

/**
 * Created by Marco on 9/11/2016.
 */
public class CustomerBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "customers.db";

    public CustomerBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CustomerTable.NAME + "("+ CustomerTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CustomerTable.Cols.F_NAME + " TEXT," + CustomerTable.Cols.L_NAME + " TEXT, " + CustomerTable.Cols.DOB_DAY + " TEXT, " + CustomerTable.Cols.DOB_YEAR + " TEXT," + CustomerTable.Cols.UNIQUE_ID + " TEXT, " + CustomerTable.Cols.DOB_MONTH + " TEXT)");
        db.execSQL("create table " + SessionsTable.NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, UNIQUE_ID TEXT, TOTAL_SESSIONS TEXT, SESSIONS_COMPLETED TEXT)");
        db.execSQL("create table " + PaymentInfoTable.NAME + "( ID  INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS  TEXT, UNIQUE_ID  TEXT,PHONE TEXT, ADDED_SESSIONS TEXT, PRICE TEXT, CC_INFO TEXT, EXP_DATE TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CustomerTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SessionsTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PaymentInfoTable.NAME);
        onCreate(db);

    }

    public boolean insertCustomerData(String f_name, String l_name, String dob_year, String dob_month, String dob_day, String unique_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomerTable.Cols.F_NAME, f_name);
        contentValues.put(CustomerTable.Cols.L_NAME, l_name);
        contentValues.put(CustomerTable.Cols.DOB_YEAR, dob_year);
        contentValues.put(CustomerTable.Cols.DOB_MONTH, dob_month);
        contentValues.put(CustomerTable.Cols.DOB_DAY, dob_day);
        contentValues.put(CustomerTable.Cols.UNIQUE_ID, unique_id);

        long result = db.insert(CustomerTable.NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else return true;
    }

    public boolean insertSessionsData(String unique_id, String total_sessions, String sessions_completed) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SessionsTable.Cols.UNIQUE_ID, unique_id);
        contentValues.put(SessionsTable.Cols.TOTAL_SESSIONS, total_sessions);
        contentValues.put(SessionsTable.Cols.SESSIONS_COMPLETED, sessions_completed);

        long result = db.insert(SessionsTable.NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else return true;
    }

    public boolean insertOrderData(String unique_id, String address, String added_sessions, String price, String cc_info, String exp_date, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PaymentInfoTable.Cols.UNIQUE_ID, unique_id);
        contentValues.put(PaymentInfoTable.Cols.ADDRESS, address);
        contentValues.put(PaymentInfoTable.Cols.ADDED_SESSIONS, added_sessions);
        contentValues.put(PaymentInfoTable.Cols.PRICE, price);
        contentValues.put(PaymentInfoTable.Cols.CC_INFO, cc_info);
        contentValues.put(PaymentInfoTable.Cols.EXP_DATE, exp_date);
        contentValues.put(PaymentInfoTable.Cols.PHONE, phone);

        long result = db.insert(PaymentInfoTable.NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else return true;
    }

    //Used to retrieve all data
    public Cursor getAllCustomerData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(CustomerTable.NAME, new String[] {"rowid _id",CustomerTable.Cols.F_NAME, CustomerTable.Cols.L_NAME, CustomerTable.Cols.UNIQUE_ID, CustomerTable.Cols.DOB_DAY, CustomerTable.Cols.DOB_MONTH, CustomerTable.Cols.DOB_YEAR},null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getAllSessionData(){
        String[] columns = new String[]{"row _id", PaymentInfoTable.Cols.UNIQUE_ID, PaymentInfoTable.Cols.ADDRESS, PaymentInfoTable.Cols.ADDED_SESSIONS, PaymentInfoTable.Cols.PRICE, PaymentInfoTable.Cols.PHONE, PaymentInfoTable.Cols.CC_INFO, PaymentInfoTable.Cols.EXP_DATE};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(PaymentInfoTable.NAME, columns,null,null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getAllSessionData(String selection){
        String[] columns = new String[]{"row _id", PaymentInfoTable.Cols.UNIQUE_ID, PaymentInfoTable.Cols.ADDRESS, PaymentInfoTable.Cols.ADDED_SESSIONS, PaymentInfoTable.Cols.PRICE, PaymentInfoTable.Cols.PHONE, PaymentInfoTable.Cols.CC_INFO, PaymentInfoTable.Cols.EXP_DATE};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(PaymentInfoTable.NAME, columns, selection,null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

}



