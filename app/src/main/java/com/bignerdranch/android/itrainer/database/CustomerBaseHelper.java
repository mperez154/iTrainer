package com.bignerdranch.android.itrainer.database;

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
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "customerBase.db";

    public CustomerBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table" + CustomerTable.NAME + "(" +
        "_id integer primary key autoincrement, " +
        CustomerTable.Cols.ID + ", " +
        CustomerTable.Cols.F_NAME + ", " +
        CustomerTable.Cols.L_NAME + ", " +
        CustomerTable.Cols.AGE + ", " +
        CustomerTable.Cols.DOB_DAY + ", " +
        CustomerTable.Cols.DOB_MONTH + ", " +
        CustomerTable.Cols.DOB_YEAR + ", " +
        CustomerTable.Cols.DESIRED_WEIGHT + ", " +
        CustomerTable.Cols.START_WEIGHT + ", " +
        CustomerTable.Cols.HEIGHT + ")"
        );

        db.execSQL("create table" + SessionsTable.NAME + "(" +
        "_id integer primary key autoincrement, " +
        SessionsTable.Cols.ID + ", " +
        SessionsTable.Cols.DATE + ", " +
        SessionsTable.Cols.TYPE + ", " +
        SessionsTable.Cols.CURRENT_WEIGHT + ", " +
        SessionsTable.Cols.AMT_TO_TARGET + ")"
        );

        db.execSQL("create table" + PaymentInfoTable.NAME + "(" +
        "_id integer primary key autoincrement, " +
        PaymentInfoTable.Cols.ID + ", " +
        PaymentInfoTable.Cols.ADDRESS_CITY + ", " +
        PaymentInfoTable.Cols.ADDRESS_STREET + ", " +
        PaymentInfoTable.Cols.ADDRESS_STATE + ", " +
        PaymentInfoTable.Cols.CC_INFO + ", " +
        PaymentInfoTable.Cols.EXP_DATE + ", " +
        PaymentInfoTable.Cols.ADDRESS_ZIP + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
