package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class checkIn extends AppCompatActivity {

    EditText firstname,lastname,email,number;
    Button checkin;
    private DatabaseHelper db;
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        db = new DatabaseHelper(this);
        firstname = (EditText)findViewById(R.id.edittext_firstname);
        lastname = (EditText)findViewById(R.id.edittext_lastname);
        email = (EditText)findViewById(R.id.editText_email);
        number = (EditText)findViewById(R.id.editText_num);
        checkin = (Button)findViewById(R.id.button_checkin);

        firstname.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto.regular.ttf"));
        lastname.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto.regular.ttf"));
        email.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto.regular.ttf"));
        number.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto.regular.ttf"));
        checkin.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto.regular.ttf"));


        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (email.getText().toString().length() > 0  &&  firstname.getText().toString().trim().length() > 0 && lastname.getText().toString().trim().length() > 0) {

                        if (number.getText().toString().length() == 10) {

                            if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
                                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                                customer = new Customer();

                                customer.setFname(firstname.getText().toString().trim());
                                customer.setLname(lastname.getText().toString().trim());
                                customer.setEmail(email.getText().toString().trim());
                                customer.setNumber(number.getText().toString().trim());

                                db.insertCustomer(customer);

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(getApplicationContext(), "Enter valid email address", Toast.LENGTH_SHORT).show();
                            }



                        } else {
                            Toast.makeText(getApplicationContext(), "Contact number must have 10 digits", Toast.LENGTH_SHORT).show();
                        }

                } else {
                    Toast.makeText(getApplicationContext(), "Please enter all details", Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
