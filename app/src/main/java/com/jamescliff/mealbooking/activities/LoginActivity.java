package com.jamescliff.mealbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.jamescliff.mealbooking.Api.Api;
import com.jamescliff.mealbooking.Api.LoginResponse;
import com.jamescliff.mealbooking.Models.LoginRequest;
import com.jamescliff.mealbooking.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewResult;
    private Api theAwesomeApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://book-meal.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api theApi = retrofit.create(Api.class);

        LoginRequest loginRequest = new LoginRequest("admin@admon.com", "admin123");

        Call<LoginResponse> call = theAwesomeApi.login(loginRequest.getEmail(), loginRequest.getPassword());

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.body() + response.code());
                    return;
                } else {
                    textViewResult.setText("error login");
                }
            }


            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                textViewResult.setText("error connection");
            }

        });
//
//        public void btnLogin (View view){
//        }
    }
}