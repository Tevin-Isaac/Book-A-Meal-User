package com.jamescliff.mealbooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jamescliff.mealbooking.Api.RetrofitClient;
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

public class MainActivity extends AppCompatActivity {
    private EditText name, email_address, password;
    private Context mContext = MainActivity.this;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        email_address = findViewById(R.id.email_address);
        password = findViewById(R.id.password);
    }

    private void registerUser() {
        String email = email_address.getText().toString().trim();
        String fullname = name.getText().toString().trim();
        String pass = password.getText().toString().trim();

        RetrofitClient.getInstance().getApi().createUser(email, fullname, pass).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                String str;
                try {
                    if (response.code() == 201) {
                        str = response.body().string();
                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                String res_email = jSONObject.getString("email");
                                String res_username = jSONObject.getString("username");

                                SessionManager.getInstance(mContext).createLoginSession();
                                SessionManager.getInstance(mContext).saveUser(res_email, res_username);

                                startActivity(new Intent(mContext, HomeActivity.class));
                                Toasty.success(mContext, "Registered successfully", Toast.LENGTH_LONG).show();

                            } catch (NullPointerException | JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (response.code() == 400) {
                        str = response.errorBody().string();
                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);

                                if (jSONObject.has("email")) {
                                    JSONArray jSONArray = jSONObject.getJSONArray("email");
                                    String e_error = jSONArray.getString(0);
                                    if (!e_error.equals("")) {
                                        email_address.setError(e_error);
                                    }
                                }
                                if (jSONObject.has("username")) {
                                    JSONArray jSONArray2 = jSONObject.getJSONArray("username");
                                    String u_error = jSONArray2.getString(0);
                                    if (!u_error.equals("")) {
                                        name.setError(u_error);
                                    }
                                }
                                if (jSONObject.has("password")) {
                                    JSONArray jSONArray3= jSONObject.getJSONArray("password");
                                    String p_error = jSONArray3.getString(0);
                                    if (!p_error.equals("")) {
                                        password.setError(p_error);
                                    }
                                }

                                Toasty.error(mContext, "Please enter correct values", Toast.LENGTH_LONG).show();
                            } catch (NullPointerException | JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                    str = null;
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void btnRegister(View view) {
        registerUser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (SessionManager.getInstance(mContext).isLoggedIn()) {
            startActivity(new Intent(mContext, HomeActivity.class));
        }
    }
}