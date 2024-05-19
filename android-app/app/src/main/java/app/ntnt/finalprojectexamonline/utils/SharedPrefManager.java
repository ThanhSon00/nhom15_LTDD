package app.ntnt.finalprojectexamonline.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.ntnt.finalprojectexamonline.activity.LoginActivity;
import app.ntnt.finalprojectexamonline.model.entites.User;
import app.ntnt.finalprojectexamonline.model.response.AuthResponse;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "retrofitregisterlogin";
    private static final String KEY_USER = "keyuser";
    private static final String KEY_AUTH_TOKEN = "keyauthtoken";
    private static SharedPrefManager instance;
    private static Context ctx;

    public SharedPrefManager(Context context) {
        ctx = context;
    }
    public static synchronized  SharedPrefManager getInstance(Context context){
        if(instance == null){
            instance = new SharedPrefManager(context);
        }
        return instance;
    }
    //this method will store the user data in shared preferences
    public void saveUser (String userName) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String currentUser = gson.toJson(userName);
        editor.putString(KEY_USER, currentUser);
        editor.apply();
    }

    public void saveUser (Long userId) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String currentUser = gson.toJson(userId);
        editor.putString(KEY_USER, currentUser);
        editor.apply();
    }



    public void saveAuthToken(AuthResponse authResponse){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String currentUser = gson.toJson(authResponse);
        editor.putString(KEY_AUTH_TOKEN, currentUser);
        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USER,null) != null;
    }
    //this method will give the logged in user
    public String getUser() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String currentUser = sharedPreferences.getString(KEY_USER,"");
        return gson.fromJson(currentUser, String.class);
    }

    public Long getUserId() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String currentUser = sharedPreferences.getString(KEY_USER,"");
        return gson.fromJson(currentUser, Long.class);
    }





    public AuthResponse getAuthToken() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences (SHARED_PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String authToken = sharedPreferences.getString(KEY_AUTH_TOKEN,"");
        return gson.fromJson(authToken, AuthResponse.class);
    }
    public void logout() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(ctx, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

}


