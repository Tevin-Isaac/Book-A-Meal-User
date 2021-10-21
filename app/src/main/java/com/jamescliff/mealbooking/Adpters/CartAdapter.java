package com.jamescliff.mealbooking.Adpters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jamescliff.mealbooking.Models.Cart;
import com.jamescliff.mealbooking.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {
    private Context mContext;
    private ArrayList<Cart> carts;

    public CartAdapter(Context mContext, ArrayList<Cart> carts) {
        this.mContext = mContext;
        this.carts = carts;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.cart, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Glide
            .with(mContext)
            .load("https://res.cloudinary.com/dohcjt1gt/"+carts.get(position).getImage())
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(holder.menu_image);
//        holder.dishname.setText(carts.get(position).getFood_name());
        holder.dishprice.setText("Ksh. "+carts.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private ImageView menu_image;
        private TextView dishname, dishprice;
        public Holder(@NonNull View itemView) {
            super(itemView);
            menu_image = itemView.findViewById(R.id.menu_image);
//            dishname = itemView.findViewById(R.id.dishname);
            dishprice = itemView.findViewById(R.id.dishprice);
        }
    }
}
