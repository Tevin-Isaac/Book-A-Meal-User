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
import android.widget.Toast;

import com.jamescliff.mealbooking.Adpters.MenuAdapter;
import com.jamescliff.mealbooking.Api.RetrofitClient;
import com.jamescliff.mealbooking.Menu;
import com.jamescliff.mealbooking.R;
import com.jamescliff.mealbooking.Storage.SessionManager;
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


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MenuAdapter adapter;
    private ArrayList<Menu> menus;
    private static final String TAG = "HomeFragment";

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        menus = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rvMenu);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getFoodMenu();
        return view;
    }

    private void getFoodMenu() {
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setMessage("Please Wait...");
        dialog.show();
        RetrofitClient.getInstance().getApi().getMenu().enqueue(new Callback<ResponseBody>() {
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
                            Log.d(TAG, "onResponse: QWERT "+jsonArray.length());
                            JSONObject object = jsonArray.getJSONObject(i);
                            String name = object.getString("name");
                            String course = object.getString("course");
                            String status = object.getString("status");
                            int price = object.getInt("price");
                            String image = object.getString("image");
                            int num_order = object.getInt("num_order");
                            String content_description = object.getString("content_description");
                            String location = object.getString("location");

                            Menu myMenu = new Menu(name, course, status, price, image, num_order, content_description, location);
                            menus.add(myMenu);
                        }
                        adapter = new MenuAdapter(getActivity(), menus);
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