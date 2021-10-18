package com.jamescliff.mealbooking.Storage;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.jamescliff.mealbooking.activities.LoginActivity;


public class SessionManager {
    public static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String PREFER_NAME = "mealBooking";
    private static SessionManager instance;
    Context _context;
    SharedPreferences.Editor editor;
    SharedPreferences pref;

    private SessionManager(Context context) {
        this._context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFER_NAME, 0);
        this.pref = sharedPreferences;
        this.editor = sharedPreferences.edit();
    }

    public static SessionManager getInstance(Context context) {
        if (instance == null) {
            instance = new SessionManager(context);
        }
        return instance;
    }

    public void saveUser(String email, String username) {
        this.editor.putString("email", email);
        this.editor.putString("username", username);
        this.editor.commit();
    }

    public String getUsername() {
        return this.pref.getString("username", null);
    }

    public String getEmail() {
        return this.pref.getString("email", null);
    }

    public void setFirstTimeLaunch(boolean z) {
        this.editor.putBoolean(IS_FIRST_TIME_LAUNCH, z);
        this.editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return this.pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isLoggedIn() {
        return this.pref.getBoolean(IS_LOGIN, false);
    }

    public void createLoginSession() {
        this.editor.putBoolean(IS_LOGIN, true);
        this.editor.commit();
    }

    public void logoutUser() {
        this.editor.putBoolean(IS_LOGIN, false);
        this.editor.commit();
        Intent intent = new Intent(this._context, LoginActivity.class);
//       // intent.addFlags(PagedChannelRandomAccessSource.DEFAULT_TOTAL_BUFSIZE);
//        intent.setFlags(268435456);
        this._context.startActivity(intent);
    }
}
