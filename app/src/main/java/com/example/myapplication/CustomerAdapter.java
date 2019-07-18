package com.example.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;



public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private Context context;
    private List<Customer> customerList;

    //This method will filter the list
    //here we are passing the filtered data
    //and assigning it to the list with notifydatasetchanged method
    public void updateList(List<Customer> list) {
        customerList = list;
        notifyDataSetChanged();
        if(customerList.size()==0){
            Toast.makeText(context, "No Matching records found", Toast.LENGTH_SHORT).show();
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name,email,number;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            email = view.findViewById(R.id.email);
            number = view.findViewById(R.id.number);
        }
    }



    public CustomerAdapter(Context context, List<Customer> customerList) {
        this.context = context;
        this.customerList = customerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.customerlayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Customer customer = customerList.get(position);
        holder.name.setText(customer.getFname() + " " + customer.getLname());
        holder.email.setText(customer.getEmail());
        holder.number.setText(customer.getNumber());

        holder.name.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto.regular.ttf"));
        holder.email.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto.regular.ttf"));
        holder.number.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/roboto.regular.ttf"));



    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }




}
