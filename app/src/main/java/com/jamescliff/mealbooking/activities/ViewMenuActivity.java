package com.jamescliff.mealbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jamescliff.mealbooking.Menu;
import com.jamescliff.mealbooking.R;

public class ViewMenuActivity extends AppCompatActivity {
    private ImageView menu_image;
    private TextView price, name, course, location, desc;
    private Menu menu;
    private Context mContext = ViewMenuActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        menu_image = findViewById(R.id.menu_image);
        price = findViewById(R.id.price);
        name = findViewById(R.id.name);
        course = findViewById(R.id.course);
        location = findViewById(R.id.location);
        desc = findViewById(R.id.desc);

        if (getIntent() != null) {
            getIntentData();
        }

    }

    private void getIntentData() {
        Intent intent = getIntent();
        menu = (Menu) intent.getSerializableExtra("menu_item");

        Glide
                .with(mContext)
                .load(menu.getImage())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(menu_image);

        price.setText("Ksh. "+menu.getPrice());
        name.setText(menu.getName());
        course.setText(menu.getCourse());
        location.setText(menu.getLocation());
        desc.setText(menu.getContent_description());

    }

    public void btnBack(View view) {
        finish();
    }

    public void btnAddCart(View view) {
    }
}