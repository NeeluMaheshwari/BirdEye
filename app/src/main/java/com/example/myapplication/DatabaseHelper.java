package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "customers_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create cusomer table
        db.execSQL(Customer.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Customer.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertCustomer(Customer customer) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Customer.COLUMN_FNAME, customer.getFname());
        values.put(Customer.COLUMN_LNAME, customer.getLname());
        values.put(Customer.COLUMN_EMAIL, customer.getEmail());
        values.put(Customer.COLUMN_NUMBER, customer.getNumber());

        // insert row
        long id = db.insert(Customer.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Customer.TABLE_NAME + " ORDER BY " +
                Customer.COLUMN_FNAME + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Customer customer = new Customer();
                customer.setId(cursor.getInt(cursor.getColumnIndex(Customer.COLUMN_ID)));
                customer.setFname(cursor.getString(cursor.getColumnIndex(Customer.COLUMN_FNAME)));
                customer.setLname(cursor.getString(cursor.getColumnIndex(Customer.COLUMN_LNAME)));
                customer.setEmail(cursor.getString(cursor.getColumnIndex(Customer.COLUMN_EMAIL)));
                customer.setNumber(cursor.getString(cursor.getColumnIndex(Customer.COLUMN_NUMBER)));

                customers.add(customer);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return customer list
        return customers;
    }


    public void deleteCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Customer.TABLE_NAME, Customer.COLUMN_ID + " = ?",
                new String[]{String.valueOf(customer.getId())});
        db.close();
    }
}
