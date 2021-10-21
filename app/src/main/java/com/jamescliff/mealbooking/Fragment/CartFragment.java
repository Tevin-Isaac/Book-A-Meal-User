package com.jamescliff.mealbooking.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jamescliff.mealbooking.Adpters.CartAdapter;
import com.jamescliff.mealbooking.Api.RetrofitClient;
import com.jamescliff.mealbooking.Models.Cart;
import com.jamescliff.mealbooking.Models.Menu;
import com.jamescliff.mealbooking.R;
import com.jamescliff.mealbooking.activities.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private Button btnCheckout;
    private ArrayList<Cart> carts;
    private static final String TAG = "CartFragment";
    private ArrayList<Menu> menus;
    private int total = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        carts = new ArrayList<>();
        menus = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rvCart);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        btnCheckout = view.findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeOrder();
            }
        });

        getCart();
        return view;
    }


    private void makeOrder() {
        Log.d(TAG, "makeOrder: TTTTTTTTTTTTTTT"+total);
        RetrofitClient.getInstance().getApi().makeOrder(3, "Pending", "Pending", true, total, "Cash On Delivery", null, null).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "onResponse: WEWS "+response.code());
                ResponseBody responseBody = response.body();
                String str;

                try {
                    if (response.code() == 201) {
                        str = response.body().string();
                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);


                                startActivity(new Intent(getActivity(), HomeActivity.class));
                                Toasty.success(getActivity(), "Ordered successfully", Toast.LENGTH_LONG).show();

                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    } else if (response.code() == 400) {
                        str = response.errorBody().string();
                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);

                                if (jSONObject.has("delivery_boy")) {
                                    JSONArray jSONArray = jSONObject.getJSONArray("delivery_boy");
                                    String e_error = jSONArray.getString(0);
                                    Log.d(TAG, "onResponse: DEL " +e_error);
                                }
                                if (jSONObject.has("location")) {
                                    JSONArray jSONArray2 = jSONObject.getJSONArray("location");
                                    String u_error = jSONArray2.getString(0);
                                    Log.d(TAG, "onResponse: LOC " +u_error);
                                }
                                if (jSONObject.has("customer")) {
                                    JSONArray jSONArray3= jSONObject.getJSONArray("customer");
                                    String p_error = jSONArray3.getString(0);
                                    Log.d(TAG, "onResponse: CUS " +p_error);
                                }

                                if (jSONObject.has("order_timestamp")) {
                                    JSONArray jSONArray = jSONObject.getJSONArray("order_timestamp");
                                    String e_error = jSONArray.getString(0);
                                    Log.d(TAG, "onResponse: ODTT " +e_error);
                                }
                                if (jSONObject.has("delivery_timestamp")) {
                                    JSONArray jSONArray2 = jSONObject.getJSONArray("delivery_timestamp");
                                    String u_error = jSONArray2.getString(0);
                                    Log.d(TAG, "onResponse: DEL " +u_error);
                                }
                                if (jSONObject.has("payment_status")) {
                                    JSONArray jSONArray3= jSONObject.getJSONArray("payment_status");
                                    String p_error = jSONArray3.getString(0);
                                    Log.d(TAG, "onResponse: PAY " +p_error);
                                }

                                if (jSONObject.has("delivery_status")) {
                                    JSONArray jSONArray = jSONObject.getJSONArray("delivery_status");
                                    String e_error = jSONArray.getString(0);
                                    Log.d(TAG, "onResponse: STAT " +e_error);
                                }
                                if (jSONObject.has("if_cancelled")) {
                                    JSONArray jSONArray2 = jSONObject.getJSONArray("if_cancelled");
                                    String u_error = jSONArray2.getString(0);
                                    Log.d(TAG, "onResponse: CAN " +u_error);
                                }
                                if (jSONObject.has("total_amount")) {
                                    JSONArray jSONArray3= jSONObject.getJSONArray("total_amount");
                                    String p_error = jSONArray3.getString(0);
                                    Log.d(TAG, "onResponse: TAT " +p_error);
                                }

                                if (jSONObject.has("payment_method")) {
                                    JSONArray jSONArray = jSONObject.getJSONArray("payment_method");
                                    String e_error = jSONArray.getString(0);
                                    Log.d(TAG, "onResponse: PAY " +e_error);
                                }


                                Toasty.error(getContext(), "Please enter correct values", Toast.LENGTH_LONG).show();
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


    private void getCart() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait...");
        dialog.show();
        RetrofitClient.getInstance().getApi().getCart().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                dialog.hide();
                String str = null;
                if (response.code() == 200) {
                    try {
                        str = response.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (str != null) {
                    try {
                        JSONArray jsonArray = new JSONArray(str);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            int food = object.getInt("food");
                            int user = object.getInt("user");
                            int amount = object.getInt("amount");
                            String image = object.getString("image");

                            total += amount;

                            Cart myCart = new Cart(food, user, " ", amount, image);
                            carts.add(myCart);

                        }
                        adapter = new CartAdapter(getActivity(), carts);
                        recyclerView.setAdapter(adapter);


                    } catch (NullPointerException | JSONException e2) {
                        e2.printStackTrace();
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