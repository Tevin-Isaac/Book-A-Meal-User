package com.jamescliff.mealbooking.Adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jamescliff.mealbooking.Models.Orders;
import com.jamescliff.mealbooking.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.Holder> {
    private Context mContext;
    private ArrayList<Orders> orders;

    public OrderAdapter(Context mContext, ArrayList<Orders> orders) {
        this.mContext = mContext;
        this.orders = orders;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.orders, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.dishname.setText(orders.get(position).getDelivery_status());
        holder.dishprice.setText("Ksh. "+orders.get(position).getTotal_amount());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private ImageView menu_image;
        private TextView dishname, dishprice;
        public Holder(@NonNull View itemView) {
            super(itemView);
            menu_image = itemView.findViewById(R.id.menu_image);
            dishname = itemView.findViewById(R.id.dishname);
            dishprice = itemView.findViewById(R.id.dishprice);
        }
    }
}
