package com.jamescliff.mealbooking.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jamescliff.mealbooking.Fragment.CartFragment;
import com.jamescliff.mealbooking.Fragment.HomeFragment;
import com.jamescliff.mealbooking.Fragment.OrderFragment;
import com.jamescliff.mealbooking.R;
import com.jamescliff.mealbooking.Storage.SessionManager;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private SessionManager sessionManager;
    private Context mContext = HomeActivity.this;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.toolbar);
        sessionManager = SessionManager.getInstance(mContext);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Meal Booking");

        BottomNavigationView navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.PendingOrders:
                fragment = new CartFragment();
                break;
            case R.id.Orders:
                fragment = new OrderFragment();
                break;
            case R.id.chefProfile:
                fragment = new HomeFragment();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.logout:
                return true;
            case R.id.cart:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}