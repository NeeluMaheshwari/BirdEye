package com.example.myapplication;

public class Customer {
    public static final String TABLE_NAME = "customers";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FNAME = "fname";
    public static final String COLUMN_LNAME = "lname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_NUMBER = "number";


    private int id;
    private String fname;
    private String lname;
    private String email;
    private String number;



    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_FNAME + " TEXT,"
                    + COLUMN_LNAME + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_NUMBER + " TEXT"
                    + ")";

    public Customer() {
    }

    public Customer(int id, String fname, String lname,  String email, String number) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.number = number;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
