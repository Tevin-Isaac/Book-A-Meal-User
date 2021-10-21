package com.jamescliff.mealbooking.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jamescliff.mealbooking.Adpters.OrderAdapter;
import com.jamescliff.mealbooking.Api.RetrofitClient;
import com.jamescliff.mealbooking.Models.Menu;
import com.jamescliff.mealbooking.Models.Orders;
import com.jamescliff.mealbooking.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderFragment extends Fragment {
    private RecyclerView recyclerView;
    private OrderAdapter adapter;
    private ArrayList<Orders> orders;
    private static final String TAG = "CartFragment";
    private ArrayList<Menu> menus;
    private int total = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        orders = new ArrayList<>();
        menus = new ArrayList<>();

        recyclerView = view.findViewById(R.id.rvOrders);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getOrders();
        return view;
    }

    private void getOrders() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait...");
        dialog.show();
        RetrofitClient.getInstance().getApi().getOrder().enqueue(new Callback<ResponseBody>() {
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
                            int id = object.getInt("id");
                            int customer = object.getInt("customer");
                            int total_amount = object.getInt("total_amount");
                            String order_timestamp = object.getString("order_timestamp");
                            String delivery_timestamp = object.getString("delivery_timestamp");
                            String payment_status = object.getString("payment_status");
                            String delivery_status = object.getString("delivery_status");
                            boolean if_cancelled = object.getBoolean("if_cancelled");
                            String payment_method = object.getString("payment_method");
                            String location = object.getString("location");
                            String delivery_boy = object.getString("delivery_boy");

                            total += total_amount;

                            Orders myCart = new Orders(id, customer, order_timestamp, delivery_timestamp, payment_status, delivery_status, if_cancelled, total_amount, payment_method, location, delivery_boy);
                            orders.add(myCart);

                        }
                        adapter = new OrderAdapter(getActivity(), orders);
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