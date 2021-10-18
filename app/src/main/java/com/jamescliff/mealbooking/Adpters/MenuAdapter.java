package com.jamescliff.mealbooking.Adpters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jamescliff.mealbooking.Menu;
import com.jamescliff.mealbooking.R;
import com.jamescliff.mealbooking.activities.ViewMenuActivity;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.Holder> {
    private Context mContext;
    private ArrayList<Menu> menus;

    public MenuAdapter(Context mContext, ArrayList<Menu> menus) {
        this.mContext = mContext;
        this.menus = menus;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.foodmenu, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Menu menu = menus.get(position);
        Glide
            .with(mContext)
            .load(menus.get(position).getImage())
            .centerCrop()
            .placeholder(R.drawable.placeholder)
            .into(holder.menu_image);
        holder.dishname.setText(menus.get(position).getName());
        holder.dishprice.setText("Ksh "+menus.get(position).getPrice());
        holder.desc.setText(menus.get(position).getContent_description());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ViewMenuActivity.class);
                intent.putExtra("menu_item", menu);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        ImageView menu_image;
        TextView dishname, dishprice, desc;
        public Holder(@NonNull View itemView) {
            super(itemView);
            menu_image = itemView.findViewById(R.id.menu_image);
            dishname = itemView.findViewById(R.id.dishname);
            dishprice = itemView.findViewById(R.id.dishprice);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
