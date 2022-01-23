package com.rubick.moneyapp.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rubick.moneyapp.Model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class PreferenceData {

    private static final String USER_KEY = "user_key";

    public static void saveUer(Context context, String data){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_KEY, data);
        editor.apply();
    }

    public static User getUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        return gson.fromJson(preferences.getString(USER_KEY, ""), User.class);
    }
}
