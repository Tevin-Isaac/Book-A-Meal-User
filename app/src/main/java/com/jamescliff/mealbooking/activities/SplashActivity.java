package com.jamescliff.mealbooking.activities;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jamescliff.mealbooking.R;

import java.time.Instant;




public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private TextView appname;
    private ImageView logo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.logo = (ImageView) findViewById(R.id.logo);
        this.appname = (TextView) findViewById(R.id.appname);
        YoYo.with(Techniques.Bounce).duration(7000).playOn(findViewById(R.id.logo));
        YoYo.with(Techniques.FadeInUp).duration(5000).playOn(findViewById(R.id.appname));
        new Handler().postDelayed(new Runnable() {

            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        }, (long) SPLASH_TIME_OUT);
    }
}