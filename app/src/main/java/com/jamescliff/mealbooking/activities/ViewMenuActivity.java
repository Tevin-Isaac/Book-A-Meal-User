package com.jamescliff.mealbooking.activities;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jamescliff.mealbooking.Api.RetrofitClient;
import com.jamescliff.mealbooking.Models.Menu;
import com.jamescliff.mealbooking.R;
import com.jamescliff.mealbooking.Storage.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewMenuActivity extends AppCompatActivity {
    private ImageView menu_image;
    private TextView price, name, course, location, desc;
    private Menu menu;
    private Context mContext = ViewMenuActivity.this;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_menu);

        sessionManager =  SessionManager.getInstance(mContext);

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
                .load("https://res.cloudinary.com/dohcjt1gt/"+menu.getImage())
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
        ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setMessage("Please Wait...");
        dialog.show();
        RetrofitClient.getInstance().getApi().addToCart(menu.getId(), sessionManager.getUserId(), menu.getPrice(), menu.getImage()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: HHHHHHH "+sessionManager.getUserId());
                String str = null;
                dialog.hide();
                if (response.code() == 201) {
                    startActivity(new Intent(mContext, HomeActivity.class));
                    Toasty.success(mContext, "Added Successfully", Toast.LENGTH_LONG).show();
                } else if (response.code() == 400) {
                    try {
                        str = response.errorBody().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (str != null) {

                        try {
                            JSONObject jSONObject = new JSONObject(str);
                            if (jSONObject.has("food")) {
                                JSONArray jSONArray = jSONObject.getJSONArray("food");
                                String e_error = jSONArray.getString(0);

                                Toasty.error(mContext, e_error, Toast.LENGTH_LONG).show();

                            }
                            if (jSONObject.has("user")) {
                                JSONArray jSONArray = jSONObject.getJSONArray("user");
                                String e_error = jSONArray.getString(0);
                                Toasty.warning(mContext, e_error, Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                dialog.hide();
            }
        });
    }
}