package com.bignerdranch.android.itrainer.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.bignerdranch.android.itrainer.database.TrainerDbSchema.CustomerTable;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema.PaymentInfoTable;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema.SessionsTable;
import com.bignerdranch.android.itrainer.database.TrainerDbSchema.UsersTable;

/**
 * Created by Marco on 9/11/2016.
 */
public class CustomerBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 3;
    private static final String DATABASE_NAME = "customers.db";

    public CustomerBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CustomerTable.NAME + "("+ CustomerTable.Cols.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + CustomerTable.Cols.F_NAME + " TEXT," + CustomerTable.Cols.L_NAME + " TEXT, " + CustomerTable.Cols.DOB_DAY + " TEXT, " + CustomerTable.Cols.DOB_YEAR + " TEXT," + CustomerTable.Cols.UNIQUE_ID + " TEXT, " + CustomerTable.Cols.DOB_MONTH + " TEXT, " + CustomerTable.Cols.PICTURE + " BLOB)");
        db.execSQL("create table " + SessionsTable.NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, UNIQUE_ID TEXT, TOTAL_SESSIONS TEXT, SESSIONS_COMPLETED TEXT)");
        db.execSQL("create table " + PaymentInfoTable.NAME + "( ID  INTEGER PRIMARY KEY AUTOINCREMENT, ADDRESS  TEXT, UNIQUE_ID  TEXT,PHONE TEXT, ADDED_SESSIONS TEXT, PRICE TEXT, CC_INFO TEXT, EXP_DATE TEXT)");
        db.execSQL("create table " + TrainerDbSchema.UsersTable.NAME + "(ID  INTEGER PRIMARY KEY AUTOINCREMENT, " + UsersTable.Cols.F_NAME + " TEXT," + UsersTable.Cols.L_NAME + " TEXT, " + UsersTable.Cols.USER_NAME + " TEXT," + UsersTable.Cols.PASSWORD + " TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + CustomerTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SessionsTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PaymentInfoTable.NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UsersTable.NAME);
        onCreate(db);

    }

    public boolean insertCustomerData(String f_name, String l_name, String dob_year, String dob_month, String dob_day, String unique_id, byte[] picture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CustomerTable.Cols.F_NAME, f_name);
        contentValues.put(CustomerTable.Cols.L_NAME, l_name);
        contentValues.put(CustomerTable.Cols.DOB_YEAR, dob_year);
        contentValues.put(CustomerTable.Cols.DOB_MONTH, dob_month);
        contentValues.put(CustomerTable.Cols.DOB_DAY, dob_day);
        contentValues.put(CustomerTable.Cols.UNIQUE_ID, unique_id);
        contentValues.put(CustomerTable.Cols.PICTURE, picture);

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

    public boolean insertUser(String f_name, String l_name, String user_name, String password)
    {
       SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TrainerDbSchema.UsersTable.Cols.F_NAME, f_name);
        contentValues.put(TrainerDbSchema.UsersTable.Cols.L_NAME, l_name);
        contentValues.put(TrainerDbSchema.UsersTable.Cols.USER_NAME, user_name);
        contentValues.put(TrainerDbSchema.UsersTable.Cols.PASSWORD, password);

        long result = db.insert(TrainerDbSchema.UsersTable.NAME, null, contentValues);
        if(result == -1){
            return false;
        } else return true;
    }



    //Used to retrieve all data
    public Cursor getAllCustomerData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(CustomerTable.NAME, new String[] {"rowid _id",CustomerTable.Cols.F_NAME, CustomerTable.Cols.L_NAME, CustomerTable.Cols.UNIQUE_ID, CustomerTable.Cols.DOB_DAY, CustomerTable.Cols.DOB_MONTH, CustomerTable.Cols.DOB_YEAR, CustomerTable.Cols.PICTURE},null,null,null,null,null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Retrive details for one customer by unique ID
    public Cursor getOneCustomerData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{CustomerTable.Cols.F_NAME, CustomerTable.Cols.L_NAME, CustomerTable.Cols.DOB_DAY, CustomerTable.Cols.DOB_MONTH, CustomerTable.Cols.DOB_YEAR, CustomerTable.Cols.PICTURE};
        Cursor cursor = db.query(CustomerTable.NAME, columns, CustomerTable.Cols.UNIQUE_ID + " = '"+name+"' " ,null,null,null,null);
        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Get order details where unique ID is equal to arg passed in
    public Cursor getAllOrderData(String name){
        String[] columns = new String[]{PaymentInfoTable.Cols.UNIQUE_ID, PaymentInfoTable.Cols.ADDRESS, PaymentInfoTable.Cols.ADDED_SESSIONS, PaymentInfoTable.Cols.PRICE, PaymentInfoTable.Cols.PHONE, PaymentInfoTable.Cols.CC_INFO, PaymentInfoTable.Cols.EXP_DATE};
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(PaymentInfoTable.NAME, columns, PaymentInfoTable.Cols.UNIQUE_ID + " = '"+name+"' " ,null,null,null,null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Get session data where unique id is equal to args passed in
    public Cursor getAllSessionData(String name){
        String [] columns = new String[] {SessionsTable.Cols.UNIQUE_ID, SessionsTable.Cols.TOTAL_SESSIONS, SessionsTable.Cols.SESSIONS_COMPLETED};
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(SessionsTable.NAME, columns, SessionsTable.Cols.UNIQUE_ID + " = '"+name+"'", null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    //Get user info where user is equal to arg passed in
    public Cursor getUserData(String user){
        String [] columns = new String[] {UsersTable.Cols.USER_NAME, UsersTable.Cols.PASSWORD};
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(UsersTable.NAME, columns, UsersTable.Cols.USER_NAME + " = '"+user+"'", null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }

    public boolean updateSessionsCompleted(String sessions){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SessionsTable.Cols.SESSIONS_COMPLETED, sessions);

        long result = db.insert(TrainerDbSchema.SessionsTable.NAME, null, contentValues);
        if(result == -1){
            return false;
        } else return true;
    }

}



