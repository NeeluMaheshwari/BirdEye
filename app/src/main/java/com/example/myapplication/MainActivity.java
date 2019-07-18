package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.clans.fab.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add_cust;
    RecyclerView recyclerView;
    private CustomerAdapter mAdapter;
    private List<Customer> customerList = new ArrayList<>();
    private DatabaseHelper db;
    EditText textsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        db = new DatabaseHelper(this);
        textsearch = findViewById(R.id.text_search);
        textsearch.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/roboto.regular.ttf"));

        customerList.addAll(db.getAllCustomers());

        mAdapter = new CustomerAdapter(this, customerList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        /**
         * On long press/click on RecyclerView item,
         *  Delete
         * */
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                showActionsDialog(position);
            }

            @Override
            public void onLongClick(View view, int position) {
                showActionsDialog(position);
            }
        }));



        add_cust = (FloatingActionButton)findViewById(R.id.creat_customer);
        add_cust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), checkIn.class);
                startActivity(intent);

            }
        });

        textsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

                filter(s.toString());
            }
        });


    }

    void filter(String text){
        List<Customer> temp = new ArrayList();
        for(Customer customer: customerList){
            // name match condition
            // here we are looking for first name or last name match
            if(customer.getFname().toLowerCase().contains(text.toLowerCase()) || customer.getLname().toLowerCase().contains(text.toLowerCase())){
                temp.add(customer);
            }
        }
        //update recyclerview
        mAdapter.updateList(temp);
    }


    private void showActionsDialog(final int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        // Dialog Message
        alertDialog.setMessage("This entry would be deleted. Are you sure you want to delete?");

        // On pressing button
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                deleteCustomer(position);
            }
        });

        // on pressing cancel button
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();


    }




    /**
     * Deleting customer from SQLite and removing the
     * item from the list by its position
     */
    private void deleteCustomer(int position) {
        // deleting the customer from db
        db.deleteCustomer(customerList.get(position));

        // removing the customer from the list
        customerList.remove(position);
        mAdapter.notifyItemRemoved(position);

    }


}
